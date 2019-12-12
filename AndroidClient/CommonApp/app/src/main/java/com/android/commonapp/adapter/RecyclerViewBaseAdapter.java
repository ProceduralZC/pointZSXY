package com.android.commonapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * @date: 2017/11/29.
 * @author: CHEN
 * @describe: RecyclerView的BaseAdapter
 */

public class RecyclerViewBaseAdapter<T extends RecyclerViewBaseAdapter.RecyclerViewBaseViewHolder> extends RecyclerView.Adapter<T> {

    public Context context;
    public LayoutInflater mInflater;
    public OnItemClickListener onItemClickListener; // item点击事件
    public OnViewClickListener onViewClickListener; // item子view点击事件
    public OnItemLongClickListener onItemLongClickListener; // item长按事件

    /**
     * item的子View没有点击事件，使用该构造方法
     *
     * @param context
     */
    public RecyclerViewBaseAdapter(Context context) {
        init(context);
    }

    /**
     * 如果item的子View有点击事件，使用该构造方法
     *
     * @param context
     * @param onViewClickListener
     */
    public RecyclerViewBaseAdapter(Context context, OnViewClickListener onViewClickListener) {
        init(context);
        this.onViewClickListener = onViewClickListener;
    }

    /**
     * 初始化
     *
     * @param context
     */
    void init(Context context) {
        this.context = context;
        this.mInflater = LayoutInflater.from(context);
    }

    @Override
    public T onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(T holder, final int position) {
//        if (onItemClickListener != null){ // 当来回滑动时本方法会执行再次进行new的操作
//            holder.itemView.setOnClickListener(new View.OnClickListener() { // item点击事件
//
//                @Override
//                public void onClick(View v) {
//                    onItemClickListener.onItemClick(position);
//                }
//            });
//        }
//
//        if (onItemLongClickListener != null){
//            holder.itemView.setOnLongClickListener(new View.OnLongClickListener() { // item长按事件
//                @Override
//                public boolean onLongClick(View v) {
//                    onItemLongClickListener.onItemLongClick(position);
//                    return true;
//                }
//            });
//        }

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class RecyclerViewBaseViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener {

        private SparseArray<View> mViews;
        private View mConvertView;

        public RecyclerViewBaseViewHolder(View itemView) {
            super(itemView);
            this.mConvertView = itemView;
            mViews = new SparseArray<>();
            if (onItemClickListener != null) {
                itemView.setOnClickListener(this);
            }
            if (onItemLongClickListener != null) {
                itemView.setOnLongClickListener(this);
            }
        }

        @Override
        public void onClick(View v) {
            onItemClickListener.onItemClick(getAdapterPosition());
        }

        @Override
        public boolean onLongClick(View v) {
            onItemLongClickListener.onItemLongClick(getAdapterPosition());
            return true;
        }

        /**
         * 通过id获得控件
         *
         * @param viewId
         * @param <T>
         * @return
         */
        public <T extends View> T getView(int viewId) {
            View view = mViews.get(viewId);
            if (view == null) {
                view = mConvertView.findViewById(viewId);
                mViews.put(viewId, view);
            }
            return (T) view;
        }

        public void setText(int viewId, String text) {
            TextView textView = getView(viewId);
            if (TextUtils.isEmpty(text)) {
                textView.setText("");
            } else {
                textView.setText(text);
            }
        }

    }

    /**
     * item点击事件
     */
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    /**
     * 设置item点击事件
     *
     * @param onItemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    /**
     * item长按事件
     */
    public interface OnItemLongClickListener {
        void onItemLongClick(int position);
    }

    /**
     * 设置item长按事件
     *
     * @param onItemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    /**
     * item中子view的点击事件（回调）
     */
    public interface OnViewClickListener {
        /**
         * @param position item position
         * @param type     点击的view的类型，调用时根据不同的view传入不同的值加以区分
         */
        void onViewClick(int position, int type);
    }

}
