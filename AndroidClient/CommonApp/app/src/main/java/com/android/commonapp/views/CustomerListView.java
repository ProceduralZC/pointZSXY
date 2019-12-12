package com.android.commonapp.views;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;
import com.android.commonapp.R;
/**
 * 编码人 ，仿照知乎刷新效果
 * 日期 2016/7/25
 */
public class CustomerListView extends ListView implements AbsListView.OnScrollListener {

    private Context context;
    private Callback callback;
    private View footView;


    public CustomerListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        initUI();
    }

    private void initUI() {
        footView = LayoutInflater.from(context).inflate(R.layout.foot_view, null);
        footView.setVisibility(View.GONE);
        this.addFooterView(footView);
        this.setFooterDividersEnabled(false);
        this.setOnScrollListener(this);
    }

    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == OnScrollListener.SCROLL_STATE_IDLE
                &&  this.getLastVisiblePosition() == this.getCount() - 1) {
            footView.setVisibility(View.VISIBLE);

            callback.loadData();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        //int lastIndex = firstVisibleItem + visibleItemCount - 1 - 1;
    }

    public void hideFootView() {
        footView.setVisibility(View.GONE);
    }

    public void setCallback(Callback callback) {
        this.callback = callback;
    }

    public interface Callback {
        void downData();
        void loadData();
    }
}
