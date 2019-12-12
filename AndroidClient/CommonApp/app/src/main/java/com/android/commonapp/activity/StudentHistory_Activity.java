package com.android.commonapp.activity;
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
import com.android.commonapp.activity.boutique.Boutique_Activity;
import com.android.commonapp.activity.kngcourse.KnowledgeCourse_Activity;
import com.android.commonapp.activity.kngpath.KnowledgePath_Activity;
import com.android.commonapp.config.HttpConfig;
import com.android.commonapp.config.URLConfig;
import com.android.commonapp.contact.KnowledgePathContact;
import com.android.commonapp.contact.StudentHistoryContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.KnowledgePathModel;
import com.android.commonapp.models.StudentHistoryModel;
import com.android.commonapp.presenter.KnowledgePathPresenter;
import com.android.commonapp.presenter.StudentHistoryPresenter;
import com.android.commonapp.views.CustomerListView;
import com.android.commonapp.views.NavigationBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
/**
 * @author
 * 学习记录
 */
public class StudentHistory_Activity extends BaseActivity<StudentHistoryContact.presenter> implements StudentHistoryContact.view,
OnClickListener,CustomerListView.Callback {
	private ProgressBar progressBar;
	private TextView restartTextView,p_textView,restart_textView2;
	private RelativeLayout initLayout;
	private CustomerListView mListView;
	private ListAdapter listAdapter;
	private Handler mHandler,RefreshHandler;
	private  int start = 1, end = 10;
	private ArrayList<StudentHistoryModel> kngcourseArrayList = new ArrayList<StudentHistoryModel>();
	private boolean isRefresh = true;
	private boolean isSucceed = false;
	private boolean isLoadMore = false;
	private Intent intent;
	private int pisition_id = -1;
	private SwipeRefreshLayout refreshLayout;
	private DisplayImageOptions options;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_studenthistory);
		options= new DisplayImageOptions.Builder()
				.cacheInMemory(true).cacheOnDisc(true)
				.imageScaleType(ImageScaleType.IN_SAMPLE_INT)
				.bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设置
				//如Bitmap.Config.ARGB_8888
				.showImageOnLoading(R.mipmap.normal_big_image)   //默认图片
				.showImageForEmptyUri(R.mipmap.normal_big_image)    //url爲空會显示该图片，自己放在drawable里面的
				.showImageOnFail(R.mipmap.normal_big_image)// 加载失败显示的图片
				.build();
		RefreshHandler = new MHandler(StudentHistory_Activity.this);
		NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
		navigationBar.setTitle("最近学习");
		intent = getIntent();
		if(intent.hasExtra("id")){
//			id = intent.getStringExtra("id");
		}

		initContent();
		refreshLayout.setRefreshing(true);
		initzhishiType();
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
	public void success(List<StudentHistoryModel> list,String code ,String message) {
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
	public StudentHistoryContact.presenter initPresenter() {
		return new StudentHistoryPresenter(this);
	}

	class MHandler extends Handler {
		final WeakReference<StudentHistory_Activity> activityWeakReference;

		MHandler(StudentHistory_Activity activity) {
			this.activityWeakReference = new WeakReference<>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			StudentHistory_Activity activity = activityWeakReference.get();
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
		presenter.getStudentHistory(preferencesUtil.getUid(),String.valueOf(start), String.valueOf(end));
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
			view = LayoutInflater.from(StudentHistory_Activity.this).inflate(R.layout.adapter_studenthistory_item, null);
			viewHolder.studenthistory_logo_image = (ImageView) view.findViewById(R.id.studenthistory_logo_image);
			viewHolder.studenthistory_name_text = (TextView) view.findViewById(R.id.studenthistory_name_text);//名称
			viewHolder.studenthistory_kstime_text = (TextView) view.findViewById(R.id.studenthistory_kstime_text);//课时
			viewHolder.studenthistory_sctime_text = (TextView) view.findViewById(R.id.studenthistory_sctime_text);//时长
			viewHolder.studenthistory_menber_text = (TextView) view.findViewById(R.id.studenthistory_menber_text);//学习人数

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}

		viewHolder.studenthistory_name_text.setText(kngcourseArrayList.get(arg0).getTitle());//名称
		viewHolder.studenthistory_kstime_text.setText(kngcourseArrayList.get(arg0).getKeshi()+"课时  ");//课时
		viewHolder.studenthistory_sctime_text.setText(kngcourseArrayList.get(arg0).getShichang());//时长
		viewHolder.studenthistory_menber_text.setVisibility(View.GONE);//学习人数

		String image = HttpConfig.BASE_URL+ URLConfig.loadimage+kngcourseArrayList.get(arg0 % kngcourseArrayList.size()).getCollegeimage();
		System.out.print("image=="+image);

		mImagerLoader.displayImage(HttpConfig.BASE_URL+URLConfig.loadimage+kngcourseArrayList
				.get(arg0 % kngcourseArrayList.size()).getCollegeimage(), viewHolder.studenthistory_logo_image,options);

		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			if(preferencesUtil.getIsLog()) {
				if(kngcourseArrayList.get(arg0).getType().equals("1")){//知识课件
					Intent intent = new Intent(StudentHistory_Activity.this, Video_Activity.class);
					intent.putExtra("id",kngcourseArrayList.get(arg0).getCourseid());
					intent.putExtra("type","zhishikejian");
					StudentHistory_Activity.this.startActivity(intent);
				}else if(kngcourseArrayList.get(arg0).getType().equals("2")){//知识路径
					Intent intent = new Intent(StudentHistory_Activity.this, Video_Activity.class);
					intent.putExtra("id",kngcourseArrayList.get(arg0).getCourseid());
					intent.putExtra("type","zhishipath");
					StudentHistory_Activity.this.startActivity(intent);
				}else if(kngcourseArrayList.get(arg0).getType().equals("3")){//精品系列知识
					Intent intent = new Intent(StudentHistory_Activity.this, Video_Activity.class);
					intent.putExtra("id",kngcourseArrayList.get(arg0).getCourseid());
					intent.putExtra("type","jingpin");
					StudentHistory_Activity.this.startActivity(intent);
				}else if(kngcourseArrayList.get(arg0).getType().equals("4")){//热门

				}else if(kngcourseArrayList.get(arg0).getType().equals("5")){//精选课程
					Intent intent = new Intent(StudentHistory_Activity.this, Video_Activity.class);
					intent.putExtra("id",kngcourseArrayList.get(arg0).getCourseid());
					StudentHistory_Activity.this.startActivity(intent);
				}

			}else{
				Intent intent3 = new Intent(StudentHistory_Activity.this, LoginActivity.class);
				ScreenManager.getScreenManager().startPage(StudentHistory_Activity.this, intent3, true);
			}
			}
		});
		return view;
		}
	}

	public class ViewHolder {
		private TextView studenthistory_name_text,studenthistory_kstime_text,studenthistory_sctime_text,studenthistory_menber_text;
		private ImageView studenthistory_logo_image;
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