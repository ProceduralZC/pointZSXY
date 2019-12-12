package com.android.commonapp.activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.contact.HotContact;
import com.android.commonapp.contact.LoginContact;
import com.android.commonapp.interfaces.PermissionListener;
import com.android.commonapp.models.HotModel;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.presenter.HotPresenter;
import com.android.commonapp.presenter.LoginPresenter;
import com.android.commonapp.views.NavigationBar;
import com.example.gsyvideoplayer.video.LandLayoutVideo;

import java.text.SimpleDateFormat;
import java.util.List;
/**
 * @author
 * 我的信息
 */
public class MyInfo_Activity extends BaseActivity<LoginContact.presenter> implements LoginContact.view{
	private Intent intent;
	public TextView myinfo_sex_text,myinfo_account_text,myinfo_phone_text,myinfo_addtime_text;
	public EditText myinfo_nickname_text,myinfo_zhiwei_text,myinfo_detail_text;
	private Button updateinfo_but;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_myinfo);
		NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
		navigationBar.setTitle("个人信息");
		intent = getIntent();

		myinfo_nickname_text = (EditText)findViewById(R.id.myinfo_nickname_text);
		myinfo_zhiwei_text = (EditText)findViewById(R.id.myinfo_zhiwei_text);
		myinfo_sex_text = (TextView)findViewById(R.id.myinfo_sex_text);
		myinfo_detail_text = (EditText)findViewById(R.id.myinfo_detail_text);
		myinfo_account_text = (TextView)findViewById(R.id.myinfo_account_text);
		myinfo_phone_text = (TextView)findViewById(R.id.myinfo_phone_text);
		myinfo_addtime_text = (TextView)findViewById(R.id.myinfo_addtime_text);
		updateinfo_but = (Button) findViewById(R.id.updateinfo_but);

		myinfo_nickname_text.setText(preferencesUtil.getNickname());
		if(!preferencesUtil.getzhiwei().equals("")) {
			myinfo_zhiwei_text.setText(preferencesUtil.getzhiwei());
		}else{
			myinfo_zhiwei_text.setText("暂无职位");
		}
		if(preferencesUtil.getsex().equals("0")) {
			myinfo_sex_text.setText("男");
		}else{
			myinfo_sex_text.setText("女");
		}

		if(!preferencesUtil.getzhiwei().equals("")) {
			myinfo_detail_text.setText(preferencesUtil.getdetail());
		}else{
			myinfo_detail_text.setText("暂无简介");
		}

		myinfo_account_text.setText(preferencesUtil.getacount());
		myinfo_phone_text.setText(preferencesUtil.getphone());

		SimpleDateFormat format =  new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Long time = new Long(preferencesUtil.getaddtime());
		String addtime = format.format(time);
		myinfo_addtime_text.setText(addtime);

		//修改信息操作
		updateinfo_but.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				updateUserinfo();
			}
		});
	}

	@Override
	public void loginSuccess(LoginEntity listbean,String code ,Integer type) {
		if(type == 2){
			showToastMessage("更新用户信息成功！");
			preferencesUtil.setNickname(myinfo_nickname_text.getText().toString());
			preferencesUtil.setzhiwei(myinfo_zhiwei_text.getText().toString());
			preferencesUtil.setdetail(myinfo_detail_text.getText().toString());
		}
	}

	public void updateUserinfo(){
//		@RequestMapping(value="/updateUserDate/{id}",method={RequestMethod.GET,RequestMethod.POST})
//		public JsonResponse updateUser(@PathVariable Integer id,@RequestParam String nickname,@RequestParam String zhiwei
//				,@RequestParam String jiesao,@RequestParam Integer sex,HttpServletRequest request, HttpServletResponse response){

		presenter.updateUserInfo(preferencesUtil.getUid(),myinfo_nickname_text.getText().toString(),myinfo_zhiwei_text.getText().toString()
				,myinfo_detail_text.getText().toString(),"");

	}

	@Override
	public void loginFailure(Throwable e, boolean isNetWorkError, String msg) {
		showFailureMessage(isNetWorkError, msg, msg);
	}

	@Override
	public LoginContact.presenter initPresenter() {
		return new LoginPresenter(this);
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

	@Override
	protected void onPause() {
		super.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}
}