package com.android.commonapp.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.contact.VersionContact;
import com.android.commonapp.interfaces.PermissionListener;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.VersionModel;
import com.android.commonapp.presenter.VersionPresenter;
import com.android.commonapp.views.NavigationBar;
import com.android.commonapp.views.ProgressWebView;
import com.example.gsyvideoplayer.video.LandLayoutVideo;

import java.util.ArrayList;
import java.util.List;

/**
 * @author
 * 关于我们
 */
public class AboutMy_Activity extends BaseActivity<VersionContact.presenter> implements VersionContact.view, View.OnClickListener{
	LandLayoutVideo detailPlayer;
	private  int start = 1, end = 100;
	private Intent intent;
	public int  positionid = 0;
	private ProgressWebView webview;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_aboutmy);
		NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
		navigationBar.setTitle("关于我们");
		intent = getIntent();

		setWebView();
	}

	private void setWebView() {
		webview = (ProgressWebView) findViewById(R.id.webview);
		webview.getSettings().setJavaScriptEnabled(true);
		WebSettings s = webview.getSettings();
//		s.setBuiltInZoomControls(true);
		s.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.NARROW_COLUMNS);
		s.setUseWideViewPort(true);
		s.setLoadWithOverviewMode(true);
		s.setSavePassword(true);
		s.setSaveFormData(true);
		// enable navigator.geolocation
		s.setGeolocationEnabled(true);
		s.setGeolocationDatabasePath("/data/data/org.itri.html5webview/databases/");
		// enable Web Storage: localStorage, sessionStorage
		s.setDomStorageEnabled(true);
		webview.requestFocus();
		webview.setScrollBarStyle(0);
		s.setTextSize(WebSettings.TextSize.SMALLER);
//		//优先使用缓存：
//		webview.getSettings().setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
		//在js中调用本地java方法
		//不使用缓存：
		webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
		s.setBuiltInZoomControls(true); // 显示放大缩小 controler
		s.setSupportZoom(true); // 可以缩放
		s.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);// 默认缩放模式

		// 全屏显示
//		webview.getSettings().setLoadWithOverviewMode(true);
//		webview.getSettings().setUseWideViewPort(true);
		webview.setDownloadListener(new DownloadListener() {
			@Override
			public void onDownloadStart(String url, String userAgent,
										String contentDisposition, String mimetype,
										long contentLength) {
				if (url != null && url.startsWith("http://"))
					startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
			}
		});

		webview.loadUrl("file:///android_asset/aboutmy.html");

//		http://hola.mymax.cn/max/hola/zh_CN/openpage/circle.html?circleId=MTJAbWF4dGVjaGhvbGE=

		webview.setWebViewClient(new WebViewClient() {
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}

			@Override
			public void onPageStarted(WebView view, String url, Bitmap favicon) {
				super.onPageStarted(view, url, favicon);
//				showProgress.showProgress(OrganizationStructure_Activity.this);
			}

			@Override
			public void onPageFinished(WebView view, String url) {
				super.onPageFinished(view, url);
//				showProgress.dismissProgress(OrganizationStructure_Activity.this);
			}

			@Override
			public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
				handler.proceed(); // 接受所有网站的证书
			}
		});
	}

	@Override
	public void success(List<VersionModel> list,String code ,String message) {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.user_outlogin://
				break;
			default:
				break;
		}
	}

	@Override
	public void failure(Throwable e, boolean isNetWorkError, String msg) {
		showFailureMessage(isNetWorkError, msg, msg);
	}

	@Override
	public VersionContact.presenter initPresenter() {
		return new VersionPresenter(this);
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