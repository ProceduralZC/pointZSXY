package com.android.commonapp.views;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.View;

import com.android.commonapp.adapter.RecyclerWrapAdapter;

import java.util.ArrayList;

/**
 * 可添加头部和底部的RecyclerView
 */
public class HRecyclerView extends RecyclerView {

    private ArrayList<View> mHeaderViews = new ArrayList<>();

    private ArrayList<View> mFootViews = new ArrayList<>();

    private Adapter mAdapter;

    public HRecyclerView(Context context) {
        super(context);
    }

    public HRecyclerView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HRecyclerView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void addHeaderView(View view) { // 添加Head
        mHeaderViews.clear();
        mHeaderViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    public void addFootView(View view) { // 添加Foot
        mFootViews.clear();
        mFootViews.add(view);
        if (mAdapter != null) {
            if (!(mAdapter instanceof RecyclerWrapAdapter)) {
                mAdapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, mAdapter);
//                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void setAdapter(Adapter adapter) {
        if (mHeaderViews.isEmpty() && mFootViews.isEmpty()) {
            super.setAdapter(adapter);
        } else {
            // 装饰者模式
            adapter = new RecyclerWrapAdapter(mHeaderViews, mFootViews, adapter);
            super.setAdapter(adapter);
        }
        mAdapter = adapter;
    }

    /**
     * 添加head后notifyDataSetChanged()刷新数据时要使用方法才可以
     */
    public void notifyDataSetChanged(){
        if (mAdapter != null){
            mAdapter.notifyDataSetChanged();
        }
    }

}
