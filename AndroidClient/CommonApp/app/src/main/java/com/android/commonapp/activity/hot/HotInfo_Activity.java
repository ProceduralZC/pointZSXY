package com.android.commonapp.activity.hot;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.transition.Transition;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.android.commonapp.R;
import com.android.commonapp.activity.BaseActivity;
import com.android.commonapp.activity.Video_Activity;
import com.android.commonapp.contact.HotContact;
import com.android.commonapp.contact.VideoContact;
import com.android.commonapp.interfaces.PermissionListener;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.HotModel;
import com.android.commonapp.models.VideoModel;
import com.android.commonapp.presenter.HotPresenter;
import com.android.commonapp.presenter.StudentPresenter;
import com.android.commonapp.presenter.VideoPresenter;
import com.example.gsyvideoplayer.listener.OnTransitionListener;
import com.example.gsyvideoplayer.listener.SampleListener;
import com.example.gsyvideoplayer.model.SwitchVideoModel;
import com.example.gsyvideoplayer.video.LandLayoutVideo;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;
import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @author
 * 热门知识
 */
public class HotInfo_Activity extends BaseActivity<HotContact.presenter> implements HotContact.view{
	NestedScrollView postDetailNestedScroll;
	LandLayoutVideo detailPlayer;
	RelativeLayout activityDetailPlayer;
	private ArrayList<HotModel> videoArrayList = new ArrayList<HotModel>();
	private boolean isPlay;
	private boolean isPause;
	public final static String IMG_TRANSITION = "IMG_TRANSITION";
	public final static String TRANSITION = "TRANSITION";
	private boolean isTransition;
	private Transition transition;
	private OrientationUtils orientationUtils;
	private  int start = 1, end = 100;
	public ListView mListView;
	public ListAdapter listAdapter;
	private String id = "";
	private Intent intent;
	public int  positionid = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hotinfo);
		ButterKnife.bind(this);
		intent = getIntent();
		if(intent.hasExtra("id")){
			id = intent.getStringExtra("id");
		}

		detailPlayer = (LandLayoutVideo) findViewById(R.id.detail_player);
		mListView = (ListView) findViewById(R.id.xListView);

		activityDetailPlayer = (RelativeLayout) findViewById(R.id.activity_detail_player);
		postDetailNestedScroll = (NestedScrollView) findViewById(R.id.post_detail_nested_scroll);

		//增加封面
		ImageView imageView = new ImageView(this);
		imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
		imageView.setImageResource(com.example.gsyvideoplayer.R.mipmap.xxx1);
		detailPlayer.setThumbImageView(imageView);

		resolveNormalVideoUI();

		//外部辅助的旋转，帮助全屏
		orientationUtils = new OrientationUtils(this, detailPlayer);
		//初始化不打开外部的旋转
		orientationUtils.setEnable(false);

		detailPlayer.setIsTouchWiget(true);
		//detailPlayer.setIsTouchWigetFull(false);
		//关闭自动旋转
		detailPlayer.setRotateViewAuto(false);
		detailPlayer.setLockLand(false);
		detailPlayer.setShowFullAnimation(false);
		detailPlayer.setNeedLockFull(true);
		//过渡动画
		initTransition();//默认开始播放，注销 默认不开启播放
		detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				//直接横屏
				orientationUtils.resolveByClick();
				//第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
				detailPlayer.startWindowFullscreen(HotInfo_Activity.this, true, true);
			}
		});

		detailPlayer.setStandardVideoAllCallBack(new SampleListener() {
			@Override
			public void onPrepared(String url, Object... objects) {
				super.onPrepared(url, objects);
				//开始播放了才能旋转和全屏
				orientationUtils.setEnable(true);
				isPlay = true;
			}

			@Override
			public void onAutoComplete(String url, Object... objects) {
				System.out.print("objects=="+objects.length);
				if(videoArrayList.size()-1 >= positionid) {
					positionid ++;
					videoPlay(positionid);
				}else{
					ScreenManager.getScreenManager().goBlackPage();
					finish();
				}
				super.onAutoComplete(url, objects);
			}

			@Override
			public void onClickStartError(String url, Object... objects) {
				super.onClickStartError(url, objects);
			}

			@Override
			public void onQuitFullscreen(String url, Object... objects) {
				super.onQuitFullscreen(url, objects);
				if (orientationUtils != null) {
					orientationUtils.backToProtVideo();
				}
			}
		});
		detailPlayer.setLockClickListener(new LockClickListener() {
			@Override
			public void onClick(View view, boolean lock) {
				if (orientationUtils != null) {
					//配合下方的onConfigurationChanged
					orientationUtils.setEnable(!lock);
				}
			}
		});
		//设置返回按键功能
		detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				onBackPressed();
			}
		});

		getVideo();
	}
	private void resolveNormalVideoUI() {
		//增加title
		detailPlayer.getTitleTextView().setVisibility(View.VISIBLE);
		detailPlayer.getTitleTextView().setText("一点知识学院");
		detailPlayer.getBackButton().setVisibility(View.VISIBLE);
	}

	//播放视频
	public void videoPlay(int id){
		if(null != listAdapter) {
			listAdapter.notifyDataSetChanged();
		}

		String source1 = videoArrayList.get(id).getHotsubclassurl();
		String name = "普通";
		SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, source1);
		String source2 = videoArrayList.get(id).getHotsubclassurl();
		String name2 = "清晰";
		SwitchVideoModel switchVideoModel2 = new SwitchVideoModel(name2, source2);

		List<SwitchVideoModel> list = new ArrayList<>();
		list.add(switchVideoModel);
		list.add(switchVideoModel2);

		detailPlayer.setUp(list, true, videoArrayList.get(id).getHotsubclasstimename());
		detailPlayer.startPlayLogic();
		orientationUtils.setEnable(true);
		isPlay = true;
		GSYVideoManager.instance().setTimeOut(4000, true);
	}

	//获取视频资源
	private void getVideo(){
		presenter.getKnowledgeHot(id,String.valueOf(start), String.valueOf(end));
	}

	@Override
	public void success(List<HotModel> list) {
		videoArrayList.clear();
		videoArrayList.addAll(list);
		if(videoArrayList.size() > 0) {
			videoPlay(0);
		}

		listAdapter = new ListAdapter();
		mListView.setAdapter(listAdapter);
	}

	@Override
	public void failure(Throwable e, boolean isNetWorkError, String msg) {
		showFailureMessage(isNetWorkError, msg, msg);

	}

	@Override
	public HotContact.presenter initPresenter() {
		return new HotPresenter(this);
	}

	@Override
	public void showProgressDialog(String msg, boolean ifcancel, boolean showloading) {
		super.showProgressDialog(msg, ifcancel, showloading);
	}
	@Override
	public void showLoadingDialog(String msg,boolean ifcancel,boolean showloading) {
		showProgressDialog(msg,ifcancel,showloading);
	}

	@Override
	public void hideProgressDialog() {
		super.hideProgressDialog();
	}

	@Override
	public void dismissLoadingDialog() {
		hideProgressDialog();
	}

	@Override
	public void showToastMessage(String message) {
		super.showToastMessage(message);
	}

	@Override
	public void hideToastMessage() {
		super.hideToastMessage();
	}

	class ListAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return videoArrayList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return videoArrayList.get(arg0);
		}

		@Override
		public long getItemId(int arg0) {
			// TODO Auto-generated method stub
			return 0;
		}

		@Override
		public View getView(final int arg0, View view, ViewGroup arg2) {
			ViewHolder viewHolder = null;
			if (view == null) {
				viewHolder = new ViewHolder();
				view = LayoutInflater.from(HotInfo_Activity.this).inflate(R.layout.adapter_videolist_item, null);
				viewHolder.video_name_text = (TextView) view.findViewById(R.id.video_name_text);//名称
				viewHolder.video_kstime_text = (TextView) view.findViewById(R.id.video_kstime_text);
				viewHolder.video_start_image = (ImageView) view.findViewById(R.id.video_start_image);

				view.setTag(viewHolder);
			} else {
				viewHolder = (ViewHolder) view.getTag();
			}

			if(positionid == arg0){
				viewHolder.video_start_image.setVisibility(View.VISIBLE);
			}else{
				viewHolder.video_start_image.setVisibility(View.INVISIBLE);
			}

			viewHolder.video_name_text.setText(videoArrayList.get(arg0).getHotsubclasstimename());//名称
			viewHolder.video_kstime_text.setText(videoArrayList.get(arg0).getHotsubclasstimenum()+"分钟");//课时

			return view;
		}
	}

	public class ViewHolder {
		private TextView video_name_text,video_kstime_text;
		public ImageView video_start_image;
	}

	@Override
	public void requestRuntimePermission(String[] permissions, PermissionListener listener) {
		super.requestRuntimePermission(permissions, listener);
	}

	@Override
	public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
	}

	@Override
	public void showFailureMessage(boolean isNetWorkError, String msg, String defaults) {
		super.showFailureMessage(isNetWorkError, msg, defaults);
	}

	private void initTransition() {
		if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			postponeEnterTransition();
			ViewCompat.setTransitionName(detailPlayer, IMG_TRANSITION);
			addTransitionListener();
			startPostponedEnterTransition();
		} else {
			detailPlayer.startPlayLogic();
		}
	}

	@TargetApi(Build.VERSION_CODES.LOLLIPOP)
	private boolean addTransitionListener() {
		transition = getWindow().getSharedElementEnterTransition();
		if (transition != null) {
			transition.addListener(new OnTransitionListener(){
				@Override
				public void onTransitionEnd(Transition transition) {
					super.onTransitionEnd(transition);
					detailPlayer.startPlayLogic();
					transition.removeListener(this);
				}
			});
			return true;
		}
		return false;
	}

	@Override
	public void onBackPressed() {

		if (orientationUtils != null) {
			orientationUtils.backToProtVideo();
		}

		if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
			return;
		}
		super.onBackPressed();
	}

	@Override
	protected void onPause() {
		super.onPause();
		detailPlayer.onVideoPause();
		isPause = true;
	}

	@Override
	protected void onResume() {
		super.onResume();
		isPause = false;
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		GSYVideoPlayer.releaseAllVideos();
		//GSYPreViewManager.instance().releaseMediaPlayer();
		if (orientationUtils != null)
			orientationUtils.releaseListener();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		//如果旋转了就全屏
		if (isPlay && !isPause) {
			if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
				if (!detailPlayer.isIfCurrentIsFullscreen()) {
					detailPlayer.startWindowFullscreen(HotInfo_Activity.this, true, true);
				}
			} else {
				//新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
				if (detailPlayer.isIfCurrentIsFullscreen()) {
					StandardGSYVideoPlayer.backFromWindowFull(this);
				}
				if (orientationUtils != null) {
					orientationUtils.setEnable(true);
				}
			}
		}
	}
}