package com.android.commonapp.activity;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.webkit.DownloadListener;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.config.HttpConfig;
import com.android.commonapp.config.URLConfig;
import com.android.commonapp.contact.HotContact;
import com.android.commonapp.interfaces.PermissionListener;
import com.android.commonapp.models.HotModel;
import com.android.commonapp.presenter.HotPresenter;
import com.android.commonapp.views.NavigationBar;
import com.android.commonapp.views.ProgressWebView;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author
 * 消息详情
 */
public class MessageDetail_Activity extends BaseActivity<HotContact.presenter> implements HotContact.view{
	private Intent intent;
	public ProgressWebView webview;
	private String id = "";
	private String name = "";

	@SuppressLint("WrongConstant")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_messagedetail);
		NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
		intent = getIntent();
		if(intent.hasExtra("id")){
			id = intent.getStringExtra("id");
		}
		if(intent.hasExtra("name")){
			name = intent.getStringExtra("name");
		}
		navigationBar.setTitle(name);

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
		//不使用缓存：
		webview.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
//		s.setBuiltInZoomControls(true); // 显示放大缩小 controler
//		s.setSupportZoom(true); // 可以缩放
		s.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);// 默认缩放模式
//		android自带的五种字体大小：
//		SMALLEST(50%),
//				SMALLER(75%),
//				NORMAL(100%),
//				LARGER(150%),
//				LARGEST(200%);
		s.setTextSize(WebSettings.TextSize.LARGER);


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

		webview.loadUrl(HttpConfig.BASE_URL+ URLConfig.messagedetail+id);

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
	public void success(List<HotModel> list) {

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