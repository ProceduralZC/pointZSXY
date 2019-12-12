package com.android.commonapp.fragments;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.commonapp.R;
import com.android.commonapp.activity.LoginActivity;
import com.android.commonapp.activity.MainActivity;
import com.android.commonapp.contact.IndexContact;
import com.android.commonapp.contact.LiveContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.presenter.IndexPresenter;
import com.android.commonapp.presenter.LivePresenter;
import com.android.commonapp.views.NavigationBar;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 直播
 */
public class LiveFragment extends BaseFragment<LiveContact.presenter> implements LiveContact.view, View.OnClickListener {
    private View rootView;
    private MainActivity activity;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        super.onCreate(savedInstanceState);
    }

    @Override
    public LiveContact.presenter initPresenter() {
        return new LivePresenter(this);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_live, container, false);
        NavigationBar navigationBar = (NavigationBar) rootView.findViewById(R.id.navigationBar);
        navigationBar.setTitle("直播课程");
        navigationBar.hideBackButton();

        initViews(rootView);
        return rootView;
    }

    private void initViews(View rootView) {
//        rootView.findViewById(R.id.btn_index).setOnClickListener(this);
//        rootView.findViewById(R.id.btn_index1).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
//            case R.id.btn_index:
//                Intent intent = new Intent(activity, LoginActivity.class);
//                ScreenManager.getScreenManager().startPage(activity, intent, true);
//                break;
            default:
                break;
        }
    }

    @Override
    public void showLoadingDialog(String msg,boolean ifcancel,boolean showloading) {
        showProgressDialog(msg,ifcancel,showloading);
    }

    @Override
    public void dismissLoadingDialog() {
        if (activity != null) {
            activity.dismissLoadingDialog();
        }
    }
}
