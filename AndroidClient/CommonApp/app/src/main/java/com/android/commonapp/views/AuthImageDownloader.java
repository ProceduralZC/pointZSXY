package com.android.commonapp.views;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import android.content.Context;
import android.net.Uri;
import com.android.commonapp.util.SharedPreferencesUtilDb;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

public class AuthImageDownloader extends BaseImageDownloader {
	public static SharedPreferencesUtilDb preferencesUtil;

	private static final int MAX_REDIRECT_COUNT = 5;

	public AuthImageDownloader(Context context) {
		super(context);
		preferencesUtil = new SharedPreferencesUtilDb(context);
	}

	public AuthImageDownloader(Context context, int connectTimeout,
			int readTimeout) {
		super(context, connectTimeout, readTimeout);
	}

	protected InputStream getStreamFromNetwork(String imageUri, Object extra)
			throws IOException {
		HttpURLConnection conn = connectTo(imageUri);

		int redirectCount = 0;
		while (conn.getResponseCode() / 100 == 3
				&& redirectCount < MAX_REDIRECT_COUNT) {
			conn = connectTo(conn.getHeaderField("Location"));
			redirectCount++;
		}

		return new BufferedInputStream(conn.getInputStream(), BUFFER_SIZE);
	}

	/**
	 * 获取带有用户验证信息的HttpURLConnection
	 * 
	 * @param url
	 * @return
	 * @throws IOException
	 */
	private HttpURLConnection connectTo(String url) throws IOException {
		String encodedUrl = Uri.encode(url, ALLOWED_URI_CHARS);
		HttpURLConnection conn = (HttpURLConnection) new URL(encodedUrl).openConnection();
                //这句话为urlconnection加入身份验证信息
//		conn.addRequestProperty("Cookie",preferencesUtil.getaccess_token());
		conn.setConnectTimeout(connectTimeout);
		conn.setReadTimeout(readTimeout);
		conn.connect();
		return conn;
	}
}