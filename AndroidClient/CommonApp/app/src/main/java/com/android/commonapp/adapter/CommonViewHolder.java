//package com.android.commonapp.adapter;
//
//import android.support.v7.widget.RecyclerView;
//import android.text.TextUtils;
//import android.util.SparseArray;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.TextView;
//
///**
// * @date: 2017/12/11.
// * @author: CHEN
// * @describe: RecyclerView使用的公共ViewHolder
// */
//
//public class CommonViewHolder extends RecyclerView.ViewHolder {
//
//    private SparseArray<View> mViews;
//    private View mConvertView;
//
//    public CommonViewHolder(View itemView) {
//        super(itemView);
//        this.mConvertView = itemView;
//        mViews = new SparseArray<>();
//    }
//
//    public static CommonViewHolder create(LayoutInflater layoutInflater, int layoutId, ViewGroup parent) {
//        View itemView = layoutInflater.inflate(layoutId, parent, false);
//        return new CommonViewHolder(itemView);
//    }
//
//    public static CommonViewHolder create(View itemView) {
//        return new CommonViewHolder(itemView);
//    }
//
//    /**
//     * 通过id获得控件
//     *
//     * @param viewId
//     * @param <T>
//     * @return
//     */
//    public <T extends View> T getView(int viewId) {
//        View view = mViews.get(viewId);
//        if (view == null) {
//            view = mConvertView.findViewById(viewId);
//            mViews.put(viewId, view);
//        }
//        return (T) view;
//    }
//
//    public View getConvertView() {
//        return mConvertView;
//    }
//
//    public void setText(int viewId, String text) {
//        TextView textView = getView(viewId);
//        if (TextUtils.isEmpty(text)){
//            textView.setText("");
//        }else {
//            textView.setText(text);
//        }
//    }
//
//    public void setText(int viewId, int textId) {
//        TextView textView = getView(viewId);
//        textView.setText(textId);
//    }
//
//    public void setOnClickListener(int viewId, View.OnClickListener clickListener) {
//        View view = getView(viewId);
//        view.setOnClickListener(clickListener);
//    }
//
//}
