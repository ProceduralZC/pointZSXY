package com.android.commonapp.activity.kngcourse;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.activity.BaseActivity;
import com.android.commonapp.activity.LoginActivity;
import com.android.commonapp.activity.Video_Activity;
import com.android.commonapp.activity.kngpath.KnowledgePath_Activity;
import com.android.commonapp.config.HttpConfig;
import com.android.commonapp.config.URLConfig;
import com.android.commonapp.contact.KnowledgeCourseContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.KnowledgeModel;
import com.android.commonapp.presenter.KnowledgePresenter;
import com.android.commonapp.views.CustomerListView;
import com.android.commonapp.views.NavigationBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * 知识课件
 */
public class KnowledgeCourse_Activity extends BaseActivity<KnowledgeCourseContact.presenter> implements KnowledgeCourseContact.view,
OnClickListener,CustomerListView.Callback {
	private ProgressBar progressBar;
	private TextView restartTextView,p_textView,restart_textView2;
	private RelativeLayout initLayout;
	private CustomerListView mListView;
	private ListAdapter listAdapter;
	private Handler mHandler,RefreshHandler;
	private  int start = 1, end = 10;
	private ArrayList<KnowledgeModel> kngcourseArrayList = new ArrayList<KnowledgeModel>();
	private boolean isRefresh = true;
	private boolean isSucceed = false;
	private boolean isLoadMore = false;
	private Intent intent;
	private String id = "";
	private String name = "";
	private int pisition_id = -1;
	private SwipeRefreshLayout refreshLayout;
	private DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_knowledgecourse);
		options= new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设置
				//如Bitmap.Config.ARGB_8888
				.showImageOnLoading(R.mipmap.normal_big_image)   //默认图片
				.showImageForEmptyUri(R.mipmap.normal_big_image)    //url爲空會显示该图片，自己放在drawable里面的
				.showImageOnFail(R.mipmap.normal_big_image)// 加载失败显示的图片
				.build();
		RefreshHandler = new MHandler(KnowledgeCourse_Activity.this);
		NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
		intent = getIntent();
		if(intent.hasExtra("id")){
			id = intent.getStringExtra("id");
		}
		if(intent.hasExtra("name")){
			name = intent.getStringExtra("name");
		}
		navigationBar.setTitle(name);
		setTitle();
		initContent();

		refreshLayout.setRefreshing(true);
		initzhishiType();
	}
	
	private void setTitle() {

	}

	private void initContent(){
		progressBar = (ProgressBar) findViewById(R.id.progress);
		p_textView = (TextView) findViewById(R.id.p_textView);
		restart_textView2 = (TextView) findViewById(R.id.restart_textView2);
		restartTextView = (TextView) findViewById(R.id.restart_textView);
		restartTextView.setOnClickListener(this);

		initLayout = (RelativeLayout) findViewById(R.id.initView_layout);
		refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_id);
		refreshLayout.setColorSchemeResources(
				R.color.loading_color1, R.color.loading_color1,
				R.color.loading_color2, R.color.loading_color2);

		refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
			@Override
			public void onRefresh() {
				downData();
			}
		});
		mListView = (CustomerListView) findViewById(R.id.xListView);
		mListView.setCallback(this);

		mHandler = new Handler();
	}

	private void setContent() {
		progressBar.setVisibility(View.GONE);
		initLayout.setVisibility(View.GONE);

		if(isRefresh){
			listAdapter = new ListAdapter();
			mListView.setAdapter(listAdapter);
		}else{
			listAdapter.notifyDataSetChanged();
		}
	}

	@Override
	public void downData() {//刷新
		RefreshHandler.sendEmptyMessageDelayed(0, 500);
	}

	@Override
	public void loadData() {//加载更多
		RefreshHandler.sendEmptyMessageDelayed(1, 500);
	}

	@Override
	public void showLoadingDialog(String msg,boolean ifcancel,boolean showloading) {
		showProgressDialog(msg,ifcancel,showloading);
	}

	@Override
	public void dismissLoadingDialog() {
		hideProgressDialog();
	}

	@Override
	public void success(List<KnowledgeModel> list) {
		onLoad();
		linkDead();
		isSucceed = true;
		if(isRefresh)kngcourseArrayList.clear();
		if(list.size() != 0){
			kngcourseArrayList.addAll(list);
			setContent();

			isRefresh = false;
		}else{
			linkDead();
			if(isLoadMore) start -= 1;
		}
		isLoadMore = false;
	}

	@Override
	public void failure(Throwable e, boolean isNetWorkError, String msg) {
		showFailureMessage(isNetWorkError, msg, msg);

	}

	@Override
	public KnowledgeCourseContact.presenter initPresenter() {
		return new KnowledgePresenter(this);
	}

	class MHandler extends Handler {
		final WeakReference<KnowledgeCourse_Activity> activityWeakReference;

		MHandler(KnowledgeCourse_Activity activity) {
			this.activityWeakReference = new WeakReference<>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			KnowledgeCourse_Activity activity = activityWeakReference.get();
			if (activity == null) {
				return;
			}
			switch (msg.what) {
				case 0://刷新
					if(isSucceed){
						isRefresh = true;
						start = 1;
						initzhishiType();

						isSucceed = false;
					}
					break;
				case 1://加载更多
					if(isSucceed){
						isRefresh = false;
						start += 1;
						initzhishiType();

						isSucceed = false;
						isLoadMore = true;
				    }
					break;
			}
		}
	}

	//获取知识类型
	private void initzhishiType(){
		presenter.getKnowledgeCourse(id,String.valueOf(start), String.valueOf(end));
	}

	private void onLoad() {
		refreshLayout.setRefreshing(false);
		mListView.hideFootView();
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.item1:
			ScreenManager.getScreenManager().goBlackPage();
			finish();
			break;
		case R.id.back_image_left:
			ScreenManager.getScreenManager().goBlackPage();
			finish();
			break;
		case R.id.restart_textView:
			restartTextView.setVisibility(View.GONE);
			restart_textView2.setVisibility(View.GONE);
			progressBar.setVisibility(View.VISIBLE);
			p_textView.setVisibility(View.VISIBLE);
			break;
		}
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
	
	class ListAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return kngcourseArrayList.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return kngcourseArrayList.get(arg0);
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
			view = LayoutInflater.from(KnowledgeCourse_Activity.this).inflate(R.layout.adapter_knowledge_item, null);
			viewHolder.examination_logo_image = (ImageView) view.findViewById(R.id.examination_logo_image);
			viewHolder.knowledgecouese_name_text = (TextView) view.findViewById(R.id.knowledgecouese_name_text);//名称
			viewHolder.zskj_kstime_text = (TextView) view.findViewById(R.id.zskj_kstime_text);//课时
			viewHolder.zskj_timelong_text = (TextView) view.findViewById(R.id.zskj_timelong_text);//时长
			viewHolder.zskj_stumember_text = (TextView) view.findViewById(R.id.zskj_stumember_text);//学习人数

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.knowledgecouese_name_text.setText(kngcourseArrayList.get(arg0).getCoursewarename());//名称
		viewHolder.zskj_kstime_text.setText(kngcourseArrayList.get(arg0).getCoursewarenum()+"课时  ");//课时
		viewHolder.zskj_timelong_text.setText(kngcourseArrayList.get(arg0).getCoursewaretimelong());//时长
		viewHolder.zskj_stumember_text.setVisibility(View.GONE);//学习人数

		String image = HttpConfig.BASE_URL+ URLConfig.loadimage+kngcourseArrayList.get(arg0 % kngcourseArrayList.size()).getCollegeimage();
		System.out.print("image=="+image);

		mImagerLoader.displayImage(HttpConfig.BASE_URL+URLConfig.loadimage+kngcourseArrayList
				.get(arg0 % kngcourseArrayList.size()).getCollegeimage(), viewHolder.examination_logo_image,options);


		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			if(preferencesUtil.getIsLog()) {
				Intent intent = new Intent(KnowledgeCourse_Activity.this, Video_Activity.class);//添加学习记录  /addStudentHistory/{id}/{type}/{userid}  type 1.知识课件，2知识路径 3精品系列知识4 热门知识 5 精选课程
				intent.putExtra("id",kngcourseArrayList.get(arg0).getId());
				intent.putExtra("type","zhishikejian");
				KnowledgeCourse_Activity.this.startActivity(intent);
			}else{
				Intent intent3 = new Intent(KnowledgeCourse_Activity.this, LoginActivity.class);
				ScreenManager.getScreenManager().startPage(KnowledgeCourse_Activity.this, intent3, true);
			}
			}
		});
		return view;
		}
	}

	public class ViewHolder {
		private TextView knowledgecouese_name_text,zskj_kstime_text,zskj_timelong_text,zskj_stumember_text;
		private ImageView examination_logo_image;
	}

	private void linkDead(){
		restartTextView.setVisibility(View.VISIBLE);
		restart_textView2.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.GONE);
		p_textView.setVisibility(View.GONE);

		if(null == listAdapter){
			listAdapter = new ListAdapter();
			mListView.setAdapter(listAdapter);
		}else{
			listAdapter.notifyDataSetChanged();
		}

		if(kngcourseArrayList.size() > 0){
			initLayout.setVisibility(View.GONE);
		}else{
			initLayout.setVisibility(View.VISIBLE);
		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(null != data){
			switch(resultCode){
				case 1:

					break;
			}
		}
	}
}