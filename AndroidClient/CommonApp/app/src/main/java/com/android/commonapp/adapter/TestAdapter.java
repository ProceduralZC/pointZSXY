package com.android.commonapp.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import com.android.commonapp.R;
import com.android.commonapp.models.TestModel;

import java.util.List;

/**
 * Created by ck on 2016/12/25.
 * 测试使用
 */

public class TestAdapter extends RecyclerViewBaseAdapter<TestAdapter.ViewHolder> {

    //private static final String TAG = "TestAdapter";
    private List<TestModel> list;

    public TestAdapter(Context context, OnViewClickListener onViewClickListener, List<TestModel> list) {
        super(context, onViewClickListener);
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.e(TAG, "onCreateViewHolder: " );
        return new ViewHolder(mInflater.inflate(R.layout.test_item, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //Log.e(TAG, "onBindViewHolder: " + position);
        super.onBindViewHolder(holder, position);
        TestModel testModel = list.get(position);
        holder.setText(R.id.test_name, testModel.getTeamName());
        holder.setText(R.id.test_view, testModel.getHouseOutMan());
        holder.getView(R.id.test_name).setOnClickListener(new ViewListener(onViewClickListener, position, 1));
        holder.getView(R.id.test_view).setOnClickListener(new ViewListener(onViewClickListener, position, 2));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    class ViewHolder extends RecyclerViewBaseAdapter.RecyclerViewBaseViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }

    }


    /**
     * item的view点击事件
     */
    class ViewListener implements View.OnClickListener {

        OnViewClickListener onViewClickListener;
        int position;
        int type;

        public ViewListener(OnViewClickListener onViewClickListener, int position, int type) {
            this.onViewClickListener = onViewClickListener;
            this.position = position;
            this.type = type;
        }

        @Override
        public void onClick(View v) {
            if (onViewClickListener != null) {
                onViewClickListener.onViewClick(position, type);
            }
        }
    }


}
