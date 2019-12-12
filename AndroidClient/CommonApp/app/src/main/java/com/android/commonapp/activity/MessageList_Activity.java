package com.android.commonapp.activity;
import android.content.Intent;
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
import com.android.commonapp.contact.KnowledgePathContact;
import com.android.commonapp.contact.SystemMsgContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.KnowledgePathModel;
import com.android.commonapp.models.MessageModel;
import com.android.commonapp.presenter.KnowledgePathPresenter;
import com.android.commonapp.presenter.SystemMsgPresenter;
import com.android.commonapp.views.CustomerListView;
import com.android.commonapp.views.NavigationBar;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * 系统消息
 */
public class MessageList_Activity extends BaseActivity<SystemMsgContact.presenter> implements SystemMsgContact.view,
OnClickListener,CustomerListView.Callback {
	private ProgressBar progressBar;
	private TextView restartTextView,p_textView,restart_textView2;
	private RelativeLayout initLayout;
	private CustomerListView mListView;
	private ListAdapter listAdapter;
	private Handler mHandler,RefreshHandler;
	private  int start = 1, end = 10;
	private ArrayList<MessageModel> kngcourseArrayList = new ArrayList<MessageModel>();
	private boolean isRefresh = true;
	private boolean isSucceed = false;
	private boolean isLoadMore = false;
	private Intent intent;
	private String id = "";
	private int pisition_id = -1;
	private SwipeRefreshLayout refreshLayout;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_systemmsg);
		RefreshHandler = new MHandler(MessageList_Activity.this);
		NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
		navigationBar.setTitle("消息");
		intent = getIntent();
		if(intent.hasExtra("id")){
			id = intent.getStringExtra("id");
		}
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
	public void success(List<MessageModel> list) {
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
	public SystemMsgContact.presenter initPresenter() {
		return new SystemMsgPresenter(this);
	}

	class MHandler extends Handler {
		final WeakReference<MessageList_Activity> activityWeakReference;

		MHandler(MessageList_Activity activity) {
			this.activityWeakReference = new WeakReference<>(activity);
		}

		@Override
		public void handleMessage(Message msg) {
			MessageList_Activity activity = activityWeakReference.get();
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

	//获取消息
	private void initzhishiType(){
		presenter.getSystemMsg(String.valueOf(start), String.valueOf(end));
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
			view = LayoutInflater.from(MessageList_Activity.this).inflate(R.layout.adapter_systemmsg_item, null);
			viewHolder.message_title_text = (TextView) view.findViewById(R.id.message_title_text);//名称
			viewHolder.message_time_text = (TextView) view.findViewById(R.id.message_time_text);//时间
			viewHolder.message_content_text = (TextView) view.findViewById(R.id.message_content_text);//内容

			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		viewHolder.message_title_text.setText(kngcourseArrayList.get(arg0).getMsgtitle());//名称
		viewHolder.message_time_text.setText(kngcourseArrayList.get(arg0).getMsgaddtime());//时间
		viewHolder.message_content_text.setText(kngcourseArrayList.get(arg0).getMsgcontent());//内容

		view.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MessageList_Activity.this, MessageDetail_Activity.class);
				intent.putExtra("id",kngcourseArrayList.get(arg0).getId());
				intent.putExtra("name",kngcourseArrayList.get(arg0).getMsgtitle());
				MessageList_Activity.this.startActivity(intent);
			}
		});
		return view;
		}
	}

	public class ViewHolder {
		private TextView message_title_text,message_time_text,message_content_text;
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