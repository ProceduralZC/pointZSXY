package com.android.commonapp.activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.contact.StudentHistoryContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.StudentHistoryModel;
import com.android.commonapp.presenter.StudentHistoryPresenter;
import com.android.commonapp.views.CustomerListView;
import com.android.commonapp.views.NavigationBar;
import com.example.gsyvideoplayer.DetailPlayer;
import com.example.gsyvideoplayer.ListVideo2Activity;
import com.example.gsyvideoplayer.PlayActivity;
import com.example.gsyvideoplayer.utils.JumpUtils;

import java.io.File;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * 我的下载
 */
public class MyDownload_Activity extends BaseActivity<StudentHistoryContact.presenter> implements StudentHistoryContact.view,
OnClickListener{
	private ProgressBar progressBar;
	private TextView restartTextView,p_textView,restart_textView2;
	private RelativeLayout initLayout;
	private ListView mListView;
	private ListAdapter listAdapter;
	private  int start = 1, end = 10;
	private ArrayList<StudentHistoryModel> kngcourseArrayList = new ArrayList<StudentHistoryModel>();
	private boolean isRefresh = true;
	private boolean isSucceed = false;
	private boolean isLoadMore = false;
	private Intent intent;
	private int pisition_id = -1;
	private List<String> listinfo = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mydownload);
		NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
		navigationBar.setTitle("离线缓存");
		intent = getIntent();
		if(intent.hasExtra("id")){
//			id = intent.getStringExtra("id");
		}

		initContent();
		initzhishiType();
	}

	private void initContent(){
		progressBar = (ProgressBar) findViewById(R.id.progress);
		p_textView = (TextView) findViewById(R.id.p_textView);
		restart_textView2 = (TextView) findViewById(R.id.restart_textView2);
		restartTextView = (TextView) findViewById(R.id.restart_textView);
		restartTextView.setOnClickListener(this);

		initLayout = (RelativeLayout) findViewById(R.id.initView_layout);
		mListView = (ListView) findViewById(R.id.xListView);

	}

	private void setContent() {
		progressBar.setVisibility(View.GONE);
		initLayout.setVisibility(View.GONE);

//		if(isRefresh){
//			listAdapter = new ListAdapter();
//			mListView.setAdapter(listAdapter);
//		}else{
//			listAdapter.notifyDataSetChanged();
//		}
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

	//获取知识类型
	private void initzhishiType(){
		restartTextView.setVisibility(View.GONE);
		restart_textView2.setVisibility(View.GONE);
		progressBar.setVisibility(View.GONE);
		p_textView.setVisibility(View.GONE);
//		presenter.getStudentHistory(preferencesUtil.getUid(),String.valueOf(start), String.valueOf(end));
		final String filePath = Environment.getExternalStorageDirectory().getPath()+"/video";
		listinfo = getImagePathFromSD(filePath);

		listAdapter = new ListAdapter();
		mListView.setAdapter(listAdapter);
	}

	/**
	 * 从sd卡获取图片资源
	 * @return
	 */
	private List<String> getImagePathFromSD(String filePath) {
		// 图片列表
		List<String> imagePathList = new ArrayList<String>();
		// 得到sd卡内image文件夹的路径   File.separator(/)
		// 得到该路径文件夹下所有的文件
		File fileAll = new File(filePath);
		File[] files = fileAll.listFiles();
		// 将所有的文件存入ArrayList中,并过滤所有图片格式的文件
		for (int i = 0; i < files.length; i++) {
			File file = files[i];
			imagePathList.add(file.getName());
		}
		// 返回得到的图片列表
		return imagePathList;
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
			return listinfo.size();
		}

		@Override
		public Object getItem(int arg0) {
			// TODO Auto-generated method stub
			return listinfo.get(arg0);
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
			view = LayoutInflater.from(MyDownload_Activity.this).inflate(R.layout.adapter_mydownload_item, null);
			viewHolder.mydownload_name_text = (TextView) view.findViewById(R.id.mydownload_name_text);//名称

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}


		viewHolder.mydownload_name_text.setText(listinfo.get(arg0).toString()+"");//名称
//		viewHolder.mydownload_menber_text.setVisibility(View.GONE);//时间

		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
			if(preferencesUtil.getIsLog()) {
//				Intent intent = new Intent(MyDownload_Activity.this, DetailPlayer.class);
//				MyDownload_Activity.this.startActivity(intent);

				Intent intent = new Intent(MyDownload_Activity.this, PlayActivity.class);
				intent.putExtra("name",listinfo.get(arg0).toString());
				MyDownload_Activity.this.startActivity(intent);

			}else{
				Intent intent3 = new Intent(MyDownload_Activity.this, LoginActivity.class);
				ScreenManager.getScreenManager().startPage(MyDownload_Activity.this, intent3, true);
			}
			}
		});
		return view;
		}
	}

	public class ViewHolder {
		private TextView mydownload_name_text;
	}

	private void linkDead(){
		restartTextView.setVisibility(View.VISIBLE);
		restart_textView2.setVisibility(View.VISIBLE);
		progressBar.setVisibility(View.GONE);
		p_textView.setVisibility(View.GONE);

//		if(null == listAdapter){
//			listAdapter = new ListAdapter();
//			mListView.setAdapter(listAdapter);
//		}else{
//			listAdapter.notifyDataSetChanged();
//		}

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