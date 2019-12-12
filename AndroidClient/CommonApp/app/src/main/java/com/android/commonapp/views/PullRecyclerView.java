package com.android.commonapp.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.android.commonapp.interfaces.EndlessRecyclerOnScrollListener;

/**
 * @date: 2017/11/29.
 * @author: CHEN
 * @describe: RecyclerView中的所有方法都可以在此类中设置，暴露出去以供调用
 */

public class PullRecyclerView extends PullBaseView<RecyclerView>{

    public PullRecyclerView(Context context) {
        this(context, null);
    }

    public PullRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected RecyclerView createRecyclerView(Context context, AttributeSet attrs) {
        return new RecyclerView(context, attrs);
    }

    public void setLayoutManager(RecyclerView.LayoutManager manager) {
        mRecyclerView.setLayoutManager(manager);
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mRecyclerView.setAdapter(adapter);
    }

    public void addOnScrollListener(EndlessRecyclerOnScrollListener listener){
        mRecyclerView.addOnScrollListener(listener);
    }

    public void setHasFixedSize(boolean b){
        mRecyclerView.setHasFixedSize(b);
    }

    public void addItemDevice(RecyclerViewDivider itemDecoration){
        mRecyclerView.addItemDecoration(itemDecoration);
    }

}
