package com.android.commonapp.activity;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import com.android.commonapp.R;
import com.android.commonapp.fragments.LiveFragment;
import com.android.commonapp.fragments.StudentFragment;
import com.android.commonapp.interfaces.BasePresenter;
import com.android.commonapp.views.CustomViewPager;
import com.android.commonapp.views.TabGroupLayout;
import com.android.commonapp.fragments.IndexFragment;
import com.android.commonapp.fragments.MeFragment;
import com.android.commonapp.receiver.NetworkChangedReceiver;
import java.util.ArrayList;
/**
 * 主页
 */
public class MainActivity extends BaseActivity implements View.OnClickListener {
    private final String TAG = getClass().getSimpleName();
    private ArrayList<Fragment> fragmentList;
    private TabGroupLayout tabGroupLayout;
    private CustomViewPager viewPager;
    private NetworkChangedReceiver networkConnectChangedReceiver;

    @Override
    public void showLoadingDialog(String msg,boolean ifcancel,boolean showloading) {
        showProgressDialog(msg,ifcancel,showloading);
    }

    @Override
    public void dismissLoadingDialog() {
        hideProgressDialog();
    }

    private enum TabTag {
        Index,Student, Live, Me
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        registerNetworkReceiver();
        initViews();
    }

    @Override
    public BasePresenter initPresenter() {
        return null;
    }

    private void registerNetworkReceiver() {
        IntentFilter filter = new IntentFilter();
        filter.addAction("android.net.conn.CONNECTIVITY_CHANGE");
        filter.addAction("android.net.wifi.WIFI_STATE_CHANGED"); // WiFi开启或关闭触发该广播
        filter.addAction("android.net.wifi.STATE_CHANGE");
        networkConnectChangedReceiver = new NetworkChangedReceiver();
        registerReceiver(networkConnectChangedReceiver, filter);
    }

    public void initViews() {
        fragmentList = new ArrayList<>();
        fragmentList.add(new IndexFragment());//首页
        fragmentList.add(new StudentFragment());//知识
        fragmentList.add(new LiveFragment());//直播
        fragmentList.add(new MeFragment());//我的
        viewPager = (CustomViewPager) findViewById(R.id.tab_viewpager);
        // ViewPager设置适配器
        viewPager.setAdapter(new MyFragmentPagerAdapter(getSupportFragmentManager(), fragmentList));
        //_viewPager.setCurrentItem(0);//设置当前显示标签页为第一页
        viewPager.addOnPageChangeListener(new MyOnPageChangeListener());//页面变化时的监听器
        viewPager.setOffscreenPageLimit(4);
        tabGroupLayout = (TabGroupLayout) findViewById(R.id.main_tabs_layout);
        tabGroupLayout.setOnClickListener(this);
        setCurTab(TabTag.Index);
    }

    public void setCurTab(TabTag tag) {
        int index = tag.ordinal();
        setCurTab(index);
        viewPager.setCurrentItem(index, false);
    }

    private void setCurTab(int index) {
        tabGroupLayout.select(index);
    }

    private class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {
            setCurTab(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    }

    private class MyFragmentPagerAdapter extends FragmentPagerAdapter {
        ArrayList<Fragment> list;

        public MyFragmentPagerAdapter(FragmentManager fm, ArrayList<Fragment> list) {
            super(fm);
            this.list = list;
        }

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Fragment getItem(int arg0) {
            return list.get(arg0);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.main_tab1_layout:
                setCurTab(TabTag.Index);
                break;
            case R.id.main_tab2_layout:
                setCurTab(TabTag.Student);
                break;
            case R.id.main_tab21_layout:
                setCurTab(TabTag.Live);
                break;
            case R.id.main_tab22_layout:
                setCurTab(TabTag.Me);
                break;
            default:
                break;
        }
    }

    @Override
    protected void onDestroy() {
        if (networkConnectChangedReceiver != null) {
            unregisterReceiver(networkConnectChangedReceiver);
        }
        super.onDestroy();
    }
}
