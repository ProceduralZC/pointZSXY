package com.android.commonapp.activity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import com.android.commonapp.R;
import com.android.commonapp.adapter.LoadMoreWrapperAdapter;
import com.android.commonapp.adapter.RecyclerViewBaseAdapter;
import com.android.commonapp.adapter.TestAdapter;
import com.android.commonapp.contact.TestContact;
import com.android.commonapp.interfaces.EndlessRecyclerOnScrollListener;
import com.android.commonapp.models.TestModel;
import com.android.commonapp.presenter.TestPresenter;
import com.android.commonapp.views.NavigationBar;
import com.android.commonapp.views.PullBaseView;
import com.android.commonapp.views.PullRecyclerView;
import com.android.commonapp.views.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.List;

import static com.android.commonapp.CommonApplication.getContext;

/**
 * @date: 2017/11/22.
 * @author: CHEN
 * @describe: 测试用的
 */
public class TestActivity extends BaseActivity<TestContact.presenter> implements TestContact.view,
        RecyclerViewBaseAdapter.OnItemClickListener, RecyclerViewBaseAdapter.OnItemLongClickListener
        , RecyclerViewBaseAdapter.OnViewClickListener, PullBaseView.OnRefreshListener {

    private List<TestModel> modelList = new ArrayList<>();
    private PullRecyclerView pullRecyclerView;
    private TestAdapter testAdapter;
    private boolean isRefresh;

    private final int size = 20;
    private int page = 1;
    private LoadMoreWrapperAdapter loadMoreWrapperAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        NavigationBar navigationBar = (NavigationBar) findViewById(R.id.navigationBar);
        navigationBar.setTitle("列表");
        pullRecyclerView = (PullRecyclerView) findViewById(R.id.pullRecyclerView);
        pullRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        pullRecyclerView.setOnRefreshListener(this);
        // 禁用手动上拉加载更多
        pullRecyclerView.setCanPullUp(false);
        pullRecyclerView.setHasFixedSize(true);
        // 添加自定义分割线
        pullRecyclerView.addItemDevice(new RecyclerViewDivider(getContext(), LinearLayoutManager.VERTICAL, 1
                , ContextCompat.getColor(getContext(), R.color.colorhuivalue)));
        // 绑定数据的adapter
        testAdapter = new TestAdapter(this, this, modelList);
        testAdapter.setOnItemClickListener(this);
        testAdapter.setOnItemLongClickListener(this);
        // 处理自动加载更多的adapter
        loadMoreWrapperAdapter = new LoadMoreWrapperAdapter(testAdapter);
        pullRecyclerView.setAdapter(loadMoreWrapperAdapter);

        // 自动加载更多
        pullRecyclerView.addOnScrollListener(new EndlessRecyclerOnScrollListener() {
            @Override
            public void onLoadMore() {
                if (modelList.size() == 200){ //TODO 此处需要一个当前获取的列表的总数的一个值进行判断是否加载到底 否则每次滑动到底部都会多请求一次接口
                    // 显示加载到底的提示
                    loadMoreWrapperAdapter.setLoadState(loadMoreWrapperAdapter.LOADING_END);
                }else {
                    loadMoreWrapperAdapter.setLoadState(loadMoreWrapperAdapter.LOADING);
                    page++;
                    initData();
                }
            }
        });


        initData();

    }

    private void initData(){
        presenter.getData(String.valueOf(page), String.valueOf(size));
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
    public void success(List<TestModel> list) {
        if (isRefresh){
            pullRecyclerView.onHeaderRefreshComplete();
            isRefresh = false;
            modelList.clear();
            modelList.addAll(list);
            loadMoreWrapperAdapter.setLoadState(loadMoreWrapperAdapter.LOADING_COMPLETE);
//        }else if (list.size() == 0 && page != 1){
//            page--;
//            loadMoreWrapperAdapter.setLoadState(loadMoreWrapperAdapter.LOADING_END);
        }else {
            modelList.addAll(list);
            loadMoreWrapperAdapter.setLoadState(loadMoreWrapperAdapter.LOADING_COMPLETE);
        }

    }

    @Override
    public void failure(Throwable e, boolean isNetWorkError, String msg) {
        if (isRefresh){
            isRefresh = false;
            pullRecyclerView.onHeaderRefreshComplete();
        }
        loadMoreWrapperAdapter.setLoadState(loadMoreWrapperAdapter.LOADING_COMPLETE);
        showFailureMessage(isNetWorkError, msg, "未获取到数据!");
    }

    @Override
    public TestContact.presenter initPresenter() {
        return new TestPresenter(this);
    }

    @Override
    public void onItemClick(int position) { // item点击事件
        showToastMessage("点击了位置 " + position + " " + modelList.get(position).getTeamName());
    }

    @Override
    public void onItemLongClick(int position) { // item长按事件
        showToastMessage("长按了位置 " + position + " " + modelList.get(position).getTeamName());
    }

    @Override
    public void onViewClick(int position, int type) {// item的子View点击事件
        switch (type) {
            case 1:
                showToastMessage("子View点击 " + modelList.get(position).getTeamName());
                break;
            case 2:
                showToastMessage("点击 " + modelList.get(position).getHouseOutMan());
                break;
            default:
                break;
        }
    }

    @Override
    public void onHeaderRefresh(PullBaseView view) { // 下拉刷新
        isRefresh = true;
        page = 1;
        initData();

        //pullRecyclerView.onHeaderRefreshComplete(); // 刷新完成
    }

    @Override
    public void onFooterRefresh(PullBaseView view) { // 上拉加载
        //isLoadMore = true;
//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Toast.makeText(TestActivity.this, "模拟上拉加载更多，此处没写上拉加载更多的处理", Toast.LENGTH_LONG).show();
//                pullRecyclerView.onFooterRefreshComplete(); // 加载完成
//            }
//        }, 1000);
    }

}
