package com.android.commonapp.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.transition.Transition;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.commonapp.R;
import com.android.commonapp.contact.VideoContact;
import com.android.commonapp.interfaces.PermissionListener;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.VideoModel;
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

import org.wlf.filedownloader.DownloadFileInfo;
import org.wlf.filedownloader.FileDownloader;
import org.wlf.filedownloader.listener.OnDetectBigUrlFileListener;
import org.wlf.filedownloader.listener.OnFileDownloadStatusListener;
import org.wlf.filedownloader.listener.simple.OnSimpleFileDownloadStatusListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;

/**
 * @author
 * 课时资源
 */
public class Video_Activity extends BaseActivity<VideoContact.presenter> implements VideoContact.view{
	NestedScrollView postDetailNestedScroll;
	LandLayoutVideo detailPlayer;
	RelativeLayout activityDetailPlayer;
	private ArrayList<VideoModel> videoArrayList = new ArrayList<VideoModel>();
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
	private String type = "";
	private Intent intent;
	public int  positionid = 0;
	public TextView download_option;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_video);
		ButterKnife.bind(this);
		intent = getIntent();
		if(intent.hasExtra("id")){
			id = intent.getStringExtra("id");
		}
		if(intent.hasExtra("type")){
			type = intent.getStringExtra("type");
		}

		detailPlayer = (LandLayoutVideo) findViewById(R.id.detail_player);
		mListView = (ListView) findViewById(R.id.xListView);

		activityDetailPlayer = (RelativeLayout) findViewById(R.id.activity_detail_player);
		postDetailNestedScroll = (NestedScrollView) findViewById(R.id.post_detail_nested_scroll);
		download_option = (TextView) findViewById(R.id.download_option);

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
				detailPlayer.startWindowFullscreen(Video_Activity.this, true, true);
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

		//下载
		download_option.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if(type.equals("jingpin")) {//精品系列知识
					downLoad(videoArrayList.get(positionid).getJpsubclassurl(),videoArrayList.get(positionid).getJpsubclasstimename());
				}else if(type.equals("zhishipath")) {//知识路径
					downLoad(videoArrayList.get(positionid).getPathsubclassurl(),videoArrayList.get(positionid).getPathsubclasstimename());
				}else if(type.equals("zhishikejian")) {//知识课件
					downLoad(videoArrayList.get(positionid).getSubclassurl(),videoArrayList.get(positionid).getSubclasstimename());
				}else{//精选课程
					downLoad(videoArrayList.get(positionid).getKnowledgesubclassurl(),videoArrayList.get(positionid).getKnowledgesubclasstimename());
				}
			}
		});
	}

	private void downLoad(final String videopath,final String videoname){
		final String filePath = Environment.getExternalStorageDirectory().getPath()+"/video";
		String videoPath = videopath;
//		FileDownloader.start(videoPath);// 如果文件没被下载过，将创建并开启下载，否则继续下载，自动会断点续传（如果服务器无法支持断点续传将从头开始下载）
		FileDownloader.registerDownloadStatusListener(mOnFileDownloadStatusListener);

		FileDownloader.detect(videoPath, new OnDetectBigUrlFileListener() {
			@Override
			public void onDetectNewDownloadFile(String url, String fileName, String saveDir, long fileSize) {
				// 如果有必要，可以改变文件名称fileName和下载保存的目录saveDir
				FileDownloader.createAndStart(url, filePath, videoname+".mp4");
			}
			@Override
			public void onDetectUrlFileExist(String url) {
				// 继续下载，自动会断点续传（如果服务器无法支持断点续传将从头开始下载）
				FileDownloader.start(url);
			}
			@Override
			public void onDetectUrlFileFailed(String url, DetectBigUrlFileFailReason failReason) {
				// 探测一个网络文件失败了，具体查看failReason
			}
		});
	}



	//下载监听
	private OnFileDownloadStatusListener mOnFileDownloadStatusListener = new OnSimpleFileDownloadStatusListener() {
		@Override
		public void onFileDownloadStatusRetrying(DownloadFileInfo downloadFileInfo, int retryTimes) {
			// 正在重试下载（如果你配置了重试次数，当一旦下载失败时会尝试重试下载），retryTimes是当前第几次重试
		}
		@Override
		public void onFileDownloadStatusWaiting(DownloadFileInfo downloadFileInfo) {
			// 等待下载（等待其它任务执行完成，或者FileDownloader在忙别的操作）
		}
		@Override
		public void onFileDownloadStatusPreparing(DownloadFileInfo downloadFileInfo) {
			// 准备中（即，正在连接资源）
		}
		@Override
		public void onFileDownloadStatusPrepared(DownloadFileInfo downloadFileInfo) {
			// 已准备好（即，已经连接到了资源）
		}
		@Override
		public void onFileDownloadStatusDownloading(DownloadFileInfo downloadFileInfo, float downloadSpeed, long
				remainingTime) {
			// 正在下载，downloadSpeed为当前下载速度，单位KB/s，remainingTime为预估的剩余时间，单位秒
//			Toast.makeText(Video_Activity.this,"下载进度="+downloadSpeed,0).show();
		}
		@Override
		public void onFileDownloadStatusPaused(DownloadFileInfo downloadFileInfo) {
			// 下载已被暂停
		}
		@Override
		public void onFileDownloadStatusCompleted(DownloadFileInfo downloadFileInfo) {
			// 下载完成（整个文件已经全部下载完成）
			Toast.makeText(Video_Activity.this,"下载完成",0).show();
		}
		@Override
		public void onFileDownloadStatusFailed(String url, DownloadFileInfo downloadFileInfo, FileDownloadStatusFailReason failReason) {
			// 下载失败了，详细查看失败原因failReason，有些失败原因你可能必须关心

			String failType = failReason.getType();
			String failUrl = failReason.getUrl();// 或：failUrl = url，url和failReason.getType()会是一样的

			if(FileDownloadStatusFailReason.TYPE_URL_ILLEGAL.equals(failType)){
				// 下载failUrl时出现url错误
			}else if(FileDownloadStatusFailReason.TYPE_STORAGE_SPACE_IS_FULL.equals(failType)){
				// 下载failUrl时出现本地存储空间不足
			}else if(FileDownloadStatusFailReason.TYPE_NETWORK_DENIED.equals(failType)){
				// 下载failUrl时出现无法访问网络
			}else if(FileDownloadStatusFailReason.TYPE_NETWORK_TIMEOUT.equals(failType)){
				// 下载failUrl时出现连接超时
			}else{
				// 更多错误....
			}

			// 查看详细异常信息
			Throwable failCause = failReason.getCause();// 或：failReason.getOriginalCause()

			// 查看异常描述信息
			String failMsg = failReason.getMessage();// 或：failReason.getOriginalCause().getMessage()
		}
	};

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

		if(type.equals("jingpin")) {//精品系列知识
			setVideo(videoArrayList.get(id).getJpsubclassurl(),videoArrayList.get(id).getJpsubclasstimename());
		}else if(type.equals("zhishipath")) {//知识路径
			setVideo(videoArrayList.get(id).getPathsubclassurl(),videoArrayList.get(id).getPathsubclasstimename());
		}else if(type.equals("zhishikejian")) {//知识课件
			setVideo(videoArrayList.get(id).getSubclassurl(),videoArrayList.get(id).getSubclasstimename());
		}else{//精选课程
			setVideo(videoArrayList.get(id).getKnowledgesubclassurl(),videoArrayList.get(id).getKnowledgesubclasstimename());
		}
	}

	/**
	 * 设置视频
	 * @param url
	 * @param title
	 */
	public void setVideo(String url,String title){
		String source1 = url;
		String name = "普通";
		SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, source1);
		String source2 = url;
		String name2 = "清晰";
		SwitchVideoModel switchVideoModel2 = new SwitchVideoModel(name2, source2);

		List<SwitchVideoModel> list = new ArrayList<>();
		list.add(switchVideoModel);
		list.add(switchVideoModel2);

		detailPlayer.setUp(list, true, title);
		detailPlayer.startPlayLogic();
		orientationUtils.setEnable(true);
		isPlay = true;
		GSYVideoManager.instance().setTimeOut(4000, true);
	}

	//获取视频资源
	private void getVideo(){
		if(type.equals("jingpin")) {//精品系列知识
			presenter.getJPXLZSVideoInfo(id, String.valueOf(start), String.valueOf(end));
		}else if(type.equals("zhishipath")) {//知识路径
			presenter.getZSLJVideoInfo(id, String.valueOf(start), String.valueOf(end));
		}else if(type.equals("zhishikejian")) {//知识课件
			presenter.getZSKJVideoInfo(id, String.valueOf(start), String.valueOf(end));
		}else{//精选课程
			presenter.getVideoInfo(id, String.valueOf(start), String.valueOf(end));
		}

		if(type.equals("jingpin")) {//精品系列知识
			presenter.addStudentHistory(id, "3", preferencesUtil.getUid());
		}else if(type.equals("zhishipath")) {//知识路径
			presenter.addStudentHistory(id, "2", preferencesUtil.getUid());
		}else if(type.equals("zhishikejian")) {//知识课件
			presenter.addStudentHistory(id, "1", preferencesUtil.getUid());
		}else{//精选课程
			presenter.addStudentHistory(id, "5", preferencesUtil.getUid());
		}
	}

	@Override
	public void success(List<VideoModel> list) {
		videoArrayList.clear();
		videoArrayList.addAll(list);
		if(videoArrayList.size() > 0) {
			videoPlay(0);
		}

		listAdapter = new ListAdapter();
		mListView.setAdapter(listAdapter);
	}
	@Override
	public void addsuccess(List<VideoModel> list,String code ,String message) {
		//添加记录是否成功
	}

	@Override
	public void failure(Throwable e, boolean isNetWorkError, String msg) {
		showFailureMessage(isNetWorkError, msg, msg);

	}

	@Override
	public VideoContact.presenter initPresenter() {
		return new VideoPresenter(this);
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
				view = LayoutInflater.from(Video_Activity.this).inflate(R.layout.adapter_videolist_item, null);
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

			if(type.equals("jingpin")) {//精品系列知识
				viewHolder.video_name_text.setText(videoArrayList.get(arg0).getJpsubclasstimename());//名称
				viewHolder.video_kstime_text.setText(videoArrayList.get(arg0).getJpsubclasstimenum()+"分钟");//课时
			}else if(type.equals("zhishipath")) {//知识路径
				viewHolder.video_name_text.setText(videoArrayList.get(arg0).getPathsubclasstimename());//名称
				viewHolder.video_kstime_text.setText(videoArrayList.get(arg0).getPathsubclasstimenum()+"分钟");//课时
			}else if(type.equals("zhishikejian")) {//知识课件
				viewHolder.video_name_text.setText(videoArrayList.get(arg0).getSubclasstimename());//名称
				viewHolder.video_kstime_text.setText(videoArrayList.get(arg0).getSubclasstimenum()+"分钟");//课时
			}else{//精选课程
				viewHolder.video_name_text.setText(videoArrayList.get(arg0).getKnowledgesubclasstimename());//名称
				viewHolder.video_kstime_text.setText(videoArrayList.get(arg0).getKnowledgesubclasstimenum()+"分钟");//课时
			}

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
					detailPlayer.startWindowFullscreen(Video_Activity.this, true, true);
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