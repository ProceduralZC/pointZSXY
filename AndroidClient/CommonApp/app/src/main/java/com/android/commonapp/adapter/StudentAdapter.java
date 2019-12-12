package com.android.commonapp.adapter;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import com.android.commonapp.R;
import com.android.commonapp.models.StudentModel;
import java.util.List;
/**
 * Created by ck on 2016/12/25.
 * 知识课程 适配器
 */
public class StudentAdapter extends RecyclerViewBaseAdapter<StudentAdapter.ViewHolder> {
    private List<StudentModel> list;

    public StudentAdapter(Context context, OnViewClickListener onViewClickListener, List<StudentModel> list) {
        super(context, onViewClickListener);
        this.list = list;
    }

    @Override
    public StudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //Log.e(TAG, "onCreateViewHolder: " );
        return new StudentAdapter.ViewHolder(mInflater.inflate(R.layout.student_course_item, parent, false));
    }

    @Override
    public void onBindViewHolder(StudentAdapter.ViewHolder holder, int position) {
        //Log.e(TAG, "onBindViewHolder: " + position);
        super.onBindViewHolder(holder, position);
        StudentModel studentModel = list.get(position);
        holder.getView(R.id.test_name).setOnClickListener(new StudentAdapter.ViewListener(onViewClickListener, position, 1));
        holder.getView(R.id.test_view).setOnClickListener(new StudentAdapter.ViewListener(onViewClickListener, position, 2));

        holder.setText(R.id.test_name, studentModel.getKnowledgename());
        holder.setText(R.id.test_view, studentModel.getKnowledgeaddtime());
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