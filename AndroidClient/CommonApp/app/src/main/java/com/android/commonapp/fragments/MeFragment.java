package com.android.commonapp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.activity.LoginActivity;
import com.android.commonapp.activity.MainActivity;
import com.android.commonapp.activity.MessageList_Activity;
import com.android.commonapp.activity.MyDownload_Activity;
import com.android.commonapp.activity.MyInfo_Activity;
import com.android.commonapp.activity.MyTaskActivity;
import com.android.commonapp.activity.RegisterActivity;
import com.android.commonapp.activity.Setinfo_Activity;
import com.android.commonapp.activity.StudentHistory_Activity;
import com.android.commonapp.contact.MeContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.LoginEntity;
import com.android.commonapp.presenter.MePresenter;

import java.io.File;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 我的
 */

public class MeFragment extends BaseFragment<MeContact.presenter> implements MeContact.view, View.OnClickListener {

    private View rootView;
    private MainActivity activity;
    private File file;
    public ListView myinfo_listview;
    public ListAdapter listAdapter;
    public TextView set_option_text, student_name_text, register_option_text, registerlogin_option_text, login_option_text, myinfo_zhiwei_text, myinfo_huiyuan_text;
    public ImageView myinfo_logo_image;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_me, container, false);
        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
        myinfo_listview = (ListView) rootView.findViewById(R.id.myinfo_listview);
        String[] title = {"离线缓存", "最近学习", "我的任务"/*,"VIP购买","我的赞"*/, "我的消息"};
        int[] image = {R.mipmap.icon_mydownload_image, R.mipmap.icon_myfortune_image, R.mipmap.icon_article_image
                , R.mipmap.icon_exam_image, R.mipmap.icon_mycollection_image, R.mipmap.icon_myquestion_image};

        listAdapter = new ListAdapter(title, image);
        myinfo_listview.setAdapter(listAdapter);

        myinfo_logo_image = (ImageView) rootView.findViewById(R.id.myinfo_logo_image);
        myinfo_logo_image.setOnClickListener(this);

        set_option_text = (TextView) rootView.findViewById(R.id.set_option_text);
        set_option_text.setOnClickListener(this);

        student_name_text = (TextView) rootView.findViewById(R.id.student_name_text);//用户名
        register_option_text = (TextView) rootView.findViewById(R.id.register_option_text);//注册
        register_option_text.setOnClickListener(this);
        registerlogin_option_text = (TextView) rootView.findViewById(R.id.registerlogin_option_text);//分割
        login_option_text = (TextView) rootView.findViewById(R.id.login_option_text);//登录
        login_option_text.setOnClickListener(this);
        myinfo_zhiwei_text = (TextView) rootView.findViewById(R.id.myinfo_zhiwei_text);//职位
        myinfo_huiyuan_text = (TextView) rootView.findViewById(R.id.myinfo_huiyuan_text);//会员

    }

    @Override
    public void uploadSuccess(LoginEntity loginEntity) {
        activity.showToastMessage("上传成功 ");
    }

    @Override
    public void uploadFailure(Throwable e, boolean isNetWorkError, String msg) {
        activity.showToastMessage("上传失败");
    }

    @Override
    public MeContact.presenter initPresenter() {
        return new MePresenter(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.set_option_text://设置
                Intent intent = new Intent(activity, Setinfo_Activity.class);
                ScreenManager.getScreenManager().startPage(activity, intent, true);
                break;
            case R.id.myinfo_logo_image://我的信息
                if (preferencesUtil.getIsLog()) {
                    Intent intent2 = new Intent(activity, MyInfo_Activity.class);
                    ScreenManager.getScreenManager().startPage(activity, intent2, true);
                } else {
                    Intent intent3 = new Intent(activity, LoginActivity.class);
                    ScreenManager.getScreenManager().startPage(activity, intent3, true);
                }
                break;
            case R.id.login_option_text://登录
                Intent intent3 = new Intent(activity, LoginActivity.class);
                ScreenManager.getScreenManager().startPage(activity, intent3, true);
                break;
            case R.id.register_option_text: // 注册
                Intent registerIntent = new Intent(activity, RegisterActivity.class);
                ScreenManager.getScreenManager().startPage(activity, registerIntent, true);
                break;
            default:
                break;
        }
    }

    @Override
    public void onResume() {
        if (preferencesUtil.getIsLog()) {//判断是否登录
            student_name_text.setVisibility(View.VISIBLE);//用户名
            register_option_text.setVisibility(View.GONE);//注册
            registerlogin_option_text.setVisibility(View.GONE);//分割
            login_option_text.setVisibility(View.GONE);//登录
            myinfo_zhiwei_text.setVisibility(View.VISIBLE);//职位
            myinfo_huiyuan_text.setVisibility(View.VISIBLE);//会员
            student_name_text.setText(preferencesUtil.getusername());//用户名
            if (!preferencesUtil.getzhiwei().equals("")) {
                myinfo_zhiwei_text.setText(preferencesUtil.getzhiwei());//职位
            } else {
                myinfo_zhiwei_text.setText("暂无职位");//职位
            }
            myinfo_huiyuan_text.setText("会员到期: 无限期");//会员

        } else {
            student_name_text.setVisibility(View.GONE);//用户名
            register_option_text.setVisibility(View.VISIBLE);//注册
            registerlogin_option_text.setVisibility(View.VISIBLE);//分割
            login_option_text.setVisibility(View.VISIBLE);//登录
            myinfo_zhiwei_text.setVisibility(View.VISIBLE);//职位
            myinfo_huiyuan_text.setVisibility(View.GONE);//会员
            myinfo_zhiwei_text.setText("一秒登录，免费学习所有课程");
        }
        super.onResume();
    }

    @Override
    public void showLoadingDialog(String msg, boolean ifcancel, boolean showloading) {
        showProgressDialog(msg, ifcancel, showloading);
    }

    @Override
    public void dismissLoadingDialog() {
        if (activity != null) {
            activity.dismissLoadingDialog();
        }
    }

    class ListAdapter extends BaseAdapter {
        public String[] title;
        public int[] image;

        public ListAdapter(String[] title, int[] image) {
            this.title = title;
            this.image = image;
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
                view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_mainmyinfo_item, null);
                viewHolder.myinfo_logo_image = (ImageView) view.findViewById(R.id.myinfo_logo_image);
                viewHolder.myinfo_title_text = (TextView) view.findViewById(R.id.myinfo_title_text);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            viewHolder.myinfo_logo_image.setImageResource(image[arg0]);
            viewHolder.myinfo_title_text.setText(title[arg0] + "");//名称

//		mImagerLoader.displayImage(Static.getImgUrl(totalArrayList.get(arg0).getPicture()+"/showImage")
//				, viewHolder.establish_image,options);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if (arg0 == 0) {//离线缓存
                        if (preferencesUtil.getIsLog()) {
                            Intent intent3 = new Intent(getActivity(),MyDownload_Activity.class);
                            ScreenManager.getScreenManager().startPage(activity, intent3, true);
                        } else {
                            Intent intent3 = new Intent(activity, LoginActivity.class);
                            ScreenManager.getScreenManager().startPage(activity, intent3, true);
                        }

                    }/* else if (arg0 == 1) { // vip购买
                        // todo 没判断是否登录
						if (preferencesUtil.getIsLog()) {
                            Intent vipPurchaseIntent = new Intent(activity, VipPurchaseActivity.class);
							ScreenManager.getScreenManager().startPage(activity, vipPurchaseIntent, true);
                        } else {
                            Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                            ScreenManager.getScreenManager().startPage(getActivity(), intent3, true);
                        }
                    }*/ else if (arg0 == 1) {//最近学习
                        if (preferencesUtil.getIsLog()) {
                            Intent intent3 = new Intent(getActivity(), StudentHistory_Activity.class);
                            ScreenManager.getScreenManager().startPage(getActivity(), intent3, true);
                        } else {
                            Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                            ScreenManager.getScreenManager().startPage(getActivity(), intent3, true);
                        }
                    } else if (arg0 == 2) { // 我的任务
                        // todo 没判断是否登录
                        if (preferencesUtil.getIsLog()) {
                            Intent taskIntent = new Intent(activity, MyTaskActivity.class);
                            ScreenManager.getScreenManager().startPage(activity, taskIntent, true);
                        } else {
                            Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                            ScreenManager.getScreenManager().startPage(getActivity(), intent3, true);
                        }
                    } else if (arg0 == 5) { // 我的赞

                    } else if (arg0 == 3) {//消息
                        if (preferencesUtil.getIsLog()) {
                            Intent intent3 = new Intent(getActivity(), MessageList_Activity.class);
                            ScreenManager.getScreenManager().startPage(getActivity(), intent3, true);
                        } else {
                            Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                            ScreenManager.getScreenManager().startPage(getActivity(), intent3, true);
                        }
                    }
                }
            });

            return view;
        }
    }

    public class ViewHolder {
        private ImageView myinfo_logo_image;
        private TextView myinfo_title_text;
    }
}
