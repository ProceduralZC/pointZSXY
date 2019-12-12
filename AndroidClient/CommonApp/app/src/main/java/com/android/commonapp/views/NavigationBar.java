package com.android.commonapp.views;
import android.app.Activity;
import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.util.FragmentManagerUtil;

import java.util.ArrayList;
/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 公共头部
 */
public class NavigationBar extends FrameLayout {
    protected LayoutInflater layoutInflater;
    protected LinearLayout leftLinearLayout;
    protected LinearLayout centerLinearlayout;
    protected LinearLayout rightLinearLayout;
    protected ImageView backButton;
    protected ArrayList<View> leftViews;
    protected ArrayList<View> rightViews;
    protected TextView titleTextView;
    private LinearLayout root;

    public NavigationBar(Context context) {
        super(context);
        initComponent();
    }

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        initComponent();
    }

    public void addRightView(View view) {
        rightLinearLayout.addView(view);
    }

    public void clearRightViews() {
        rightLinearLayout.removeAllViews();
    }

    public void addLeftView(View view) {
        leftLinearLayout.addView(view);
    }

    public void clearLeftViews() {
        leftLinearLayout.removeAllViews();
    }

    public void showBackButton() {
        backButton.setVisibility(View.VISIBLE);
    }

    public void hideBackButton() {
        backButton.setVisibility(GONE);
    }

    public View getBackBackButton() {
        return backButton;
    }

    protected void initComponent() {
        leftViews = new ArrayList<View>();
        rightViews = new ArrayList<View>();

        layoutInflater = LayoutInflater.from(getContext());

        root = (LinearLayout) layoutInflater.inflate(R.layout.navigation_bar, this, false);

        leftLinearLayout = (LinearLayout) root.findViewById(R.id.leftLinearLayout);
        centerLinearlayout = (LinearLayout) root.findViewById(R.id.centerLinearLayout);
        rightLinearLayout = (LinearLayout) root.findViewById(R.id.rightLinearLayout);

        titleTextView = (TextView) root.findViewById(R.id.titleTextView);

        // 默认左侧
        backButton = (ImageView) root.findViewById(R.id.navigation_tite_bar_back);
        backButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                backButtonOnClick(v);
            }
        });
        addView(root);
    }


    public void setNavigationBarBackgroundColor(int color) {
        root.setBackgroundColor(color);

    }


    protected void backButtonOnClick(View v) {

        if (getContext() instanceof FragmentActivity) {
            FragmentActivity activity = ((FragmentActivity) getContext());
            if (activity.getSupportFragmentManager().getBackStackEntryCount() == 0) {
                activity.finish();
            } else {
                activity.getSupportFragmentManager().popBackStack();
            }
        } else {
            ((Activity) getContext()).finish();
        }
        FragmentManagerUtil.hidenSoftInput((Activity) getContext());


    }

    public void setTitle(String title) {
        titleTextView.setText(title);
    }

    public void setBackButtonOnClickListener(View.OnClickListener onClickListener) {
        backButton.setOnClickListener(onClickListener);
    }
}
