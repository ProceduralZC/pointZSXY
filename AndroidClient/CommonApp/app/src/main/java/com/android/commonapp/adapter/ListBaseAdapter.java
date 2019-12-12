package com.android.commonapp.adapter;

import android.content.Context;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * @date: 2017/12/3.
 * @author: CHEN
 * @describe: listView通用简洁的Adapter
 */

public abstract class ListBaseAdapter<T> extends BaseAdapter {
    private static final String TAG = "ListBaseAdapter";
    protected Context context;
    protected List<T> data;

    public ListBaseAdapter(Context context, List<T> data) {
        this.context = context;
        this.data = data == null ? new ArrayList<T>() : data;
        // new ArrayList<T>(data);
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public T getItem(int position) {
        if (position >= data.size()) {
            return null;
        }
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        Log.e(TAG, "getItemId: " + position);
        return position;
    }

    @SuppressWarnings("unchecked")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.e(TAG, "getView: " + position);
        ViewHolder holder;
        if (null == convertView) {
//            convertView = View.inflate(context, getItemResource(), null);
            convertView = LayoutInflater.from(context).inflate(getItemResource(), parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        return getItemView(position, convertView, holder);
    }

    public class ViewHolder {
        private SparseArray<View> views = new SparseArray<View>();
        private View convertView;

        public ViewHolder(View convertView) {
            this.convertView = convertView;
        }

        @SuppressWarnings("unchecked") // 告诉编译器忽略指定的警告，不用在编译完成后出现警告信息。
        public <T extends View> T getView(int resId) {
            View v = views.get(resId);
            if (null == v) {
                v = convertView.findViewById(resId);
                views.put(resId, v);
            }
            return (T) v;
        }

    }

    /**
     * 该方法需要子类实现，需要返回item布局的resource id
     *
     * @return
     */
    public abstract int getItemResource();

    /**
     * 使用该getItemView方法替换原来的getView方法，需要子类实现
     *
     * @param position
     * @param convertView
     * @param holder
     * @return
     */
    public abstract View getItemView(int position, View convertView, ViewHolder holder);

    /**
     * 添加全部
     * @param elem
     */
    public void addAll(List<T> elem) {
        data.addAll(elem);
        notifyDataSetChanged();
    }

    /**
     * 添加一条
     * @param elem
     */
    public void addItem(T elem) {
        data.add(elem);
        notifyDataSetChanged();
    }

    /**
     * 指定移除
     * @param elem
     */
    public void remove(T elem) {
        data.remove(elem);
        notifyDataSetChanged();
    }

    /**
     * 根据索(位置)引移除
     * @param index
     */
    public void remove(int index) {
        data.remove(index);
        notifyDataSetChanged();
    }

    /**
     * 替换所有
     * @param elem
     */
    public void replaceAll(List<T> elem) {
        data.clear();
        data.addAll(elem);
        notifyDataSetChanged();
    }

    /**
     * 清除全部
     */
    public void clear() {
        data.clear();
        notifyDataSetChanged();
    }

}
