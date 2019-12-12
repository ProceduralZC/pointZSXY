package com.android.commonapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.config.HttpConfig;
import com.android.commonapp.contact.VersionContact;
import com.android.commonapp.interfaces.PermissionListener;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.VersionModel;
import com.android.commonapp.presenter.VersionPresenter;
import com.android.commonapp.receiver.DownloadService2;
import com.android.commonapp.util.DataCleanManager;
import com.android.commonapp.views.NavigationBar;
import com.example.gsyvideoplayer.video.LandLayoutVideo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 设置信息
 */
public class Setinfo_Activity extends BaseActivity<VersionContact.presenter> implements VersionContact.view, View.OnClickListener {
    LandLayoutVideo detailPlayer;
    private int start = 1, end = 100;
    private Intent intent;
    public int positionid = 0;
    public ListView myinfo_listview;
    public ListAdapter listAdapter;
    public Button user_outlogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setinginfo);
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setTitle("设置");
        intent = getIntent();

		myinfo_listview = (ListView)findViewById(R.id.myinfo_listview);
		String[] title ={"清除缓存"/*,"离线视频管理"*/,"检查新版本","帮助与反馈","关于我们"};

        listAdapter = new ListAdapter(title);
        myinfo_listview.setAdapter(listAdapter);

        user_outlogin = (Button) findViewById(R.id.user_outlogin);
        user_outlogin.setOnClickListener(this);

        if (preferencesUtil.getIsLog()) {
            user_outlogin.setVisibility(View.VISIBLE);
        } else {
            user_outlogin.setVisibility(View.GONE);
        }
    }

    private ArrayList<VersionModel> versionArrayList = new ArrayList<VersionModel>();

    @Override
    public void success(List<VersionModel> list, String code, String message) {
        versionArrayList.clear();
        if (code.equals("ok") && list.size() > 0) {
            Setinfo_Activity.this.showToastMessage("有新版本正在更新");
            versionArrayList.clear();
            versionArrayList.addAll(list);

            String apkurl = HttpConfig.BASE_URL+"onepoint_college/ms/downloadApk?fileUrl="+list.get(0).getApk().getSource();

            Intent intent=new Intent(Setinfo_Activity.this, DownloadService2.class);
            intent.putExtra("url",apkurl);
            Setinfo_Activity.this.startService(intent);

        } else {
            Setinfo_Activity.this.showToastMessage(message);
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.user_outlogin://退出登录
                dialogUtils.showTwoButtonDialog("确认是否退出当前账户？", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) { // 确认
                        dialogUtils.dismissDialog();
                        preferencesUtil.setIsLog(false);
                        preferencesUtil.setusername("");
                        preferencesUtil.setphone("");
                        preferencesUtil.setemail("");
                        preferencesUtil.setzhiwei("");
                        preferencesUtil.setdetail("");
                        ScreenManager.getScreenManager().goBlackPage();
                        finish();
                    }
                }, new View.OnClickListener() { // 取消
                    @Override
                    public void onClick(View v) {
                        dialogUtils.dismissDialog();
                    }
                });
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
    public void showLoadingDialog(String msg, boolean ifcancel, boolean showloading) {
        showProgressDialog(msg, ifcancel, showloading);
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

    class ListAdapter extends BaseAdapter {
        public String[] title;

        public ListAdapter(String[] title) {
            this.title = title;
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return title.length;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return title[arg0];
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
                view = LayoutInflater.from(Setinfo_Activity.this).inflate(R.layout.adapter_setinfo_item, null);
                viewHolder.myinfo_title_text = (TextView) view.findViewById(R.id.myinfo_title_text);
                viewHolder.info_item_text = (TextView) view.findViewById(R.id.info_item_text);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            if (arg0 == 0) {
                final String filePath = Environment.getExternalStorageDirectory().getPath()+"/video";
                File file = new File(filePath);
                //获取缓存大小
                try {
                    viewHolder.info_item_text.setText(DataCleanManager.getCacheSize(file));
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

                viewHolder.info_item_text.setVisibility(View.VISIBLE);
            } else {
                viewHolder.info_item_text.setVisibility(View.GONE);
            }

            viewHolder.myinfo_title_text.setText(title[arg0] + "");//名称

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (arg0 == 0) {//清理缓存
                        final String filePath = Environment.getExternalStorageDirectory().getPath()+"/video";
                        DataCleanManager.cleanApplicationData(Setinfo_Activity.this, filePath);
                        try {
                            //获取缓存大小
                            notifyDataSetChanged();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }

                    } else if (arg0 == 1) {//检查新版本
                        presenter.getVersionInfo();
                    } else if (arg0 == 2) { // 帮助与反馈
                        Intent feedBackIntent = new Intent(Setinfo_Activity.this, FeedbackActivity.class);
                        ScreenManager.getScreenManager().startPage(Setinfo_Activity.this, feedBackIntent, true);
                    }else if(arg0 == 3){//关于我们
						Intent intent3 = new Intent(Setinfo_Activity.this,AboutMy_Activity.class);
						ScreenManager.getScreenManager().startPage(Setinfo_Activity.this, intent3, true);
					}
                }
            });

            return view;
        }
    }

    public class ViewHolder {
        private TextView myinfo_title_text, info_item_text;
    }
}