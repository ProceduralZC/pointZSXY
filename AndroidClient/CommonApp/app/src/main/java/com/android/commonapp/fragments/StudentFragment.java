package com.android.commonapp.fragments;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.commonapp.R;
import com.android.commonapp.activity.LoginActivity;
import com.android.commonapp.activity.MainActivity;
import com.android.commonapp.activity.MyInfo_Activity;
import com.android.commonapp.activity.Video_Activity;
import com.android.commonapp.activity.kngpath.KnowledgePath_Activity;
import com.android.commonapp.adapter.LoadMoreWrapperAdapter;
import com.android.commonapp.adapter.RecyclerViewBaseAdapter;
import com.android.commonapp.adapter.StudentAdapter;
import com.android.commonapp.config.HttpConfig;
import com.android.commonapp.config.URLConfig;
import com.android.commonapp.contact.KnowledgePathContact;
import com.android.commonapp.contact.StudentContact;
import com.android.commonapp.interfaces.EndlessRecyclerOnScrollListener;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.KnowledgePathModel;
import com.android.commonapp.models.StudentModel;
import com.android.commonapp.presenter.KnowledgePathPresenter;
import com.android.commonapp.presenter.StudentPresenter;
import com.android.commonapp.views.CustomerListView;
import com.android.commonapp.views.NavigationBar;
import com.android.commonapp.views.PullBaseView;
import com.android.commonapp.views.PullRecyclerView;
import com.android.commonapp.views.RecyclerViewDivider;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 知识课程
 */
public class StudentFragment extends BaseFragment<StudentContact.presenter>  implements StudentContact.view,
        View.OnClickListener,CustomerListView.Callback {
    private ProgressBar progressBar;
    private TextView restartTextView,p_textView,restart_textView2;
    private RelativeLayout initLayout;
    private CustomerListView mListView;
    private ListAdapter listAdapter;
    private View rootView;
    private Handler mHandler;
    private  int start = 1, end = 10;
    private ArrayList<StudentModel> studentArrayList = new ArrayList<StudentModel>();
    private boolean isRefresh = true;
    private boolean isSucceed = false;
    private boolean isLoadMore = false;
    private SwipeRefreshLayout refreshLayout;
    private Activity activity;
    private DisplayImageOptions options;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        activity = (MainActivity) getActivity();
        options= new DisplayImageOptions.Builder()
                .cacheInMemory(true)
                .cacheOnDisc(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设置
                //如Bitmap.Config.ARGB_8888
                .showImageOnLoading(R.mipmap.normal_big_image)   //默认图片
                .showImageForEmptyUri(R.mipmap.normal_big_image)    //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.mipmap.normal_big_image)// 加载失败显示的图片
                .build();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_student, container, false);
        NavigationBar navigationBar = (NavigationBar) rootView.findViewById(R.id.navigationBar);
        navigationBar.setTitle("精选课程");
        navigationBar.hideBackButton();

        progressBar = (ProgressBar) rootView.findViewById(R.id.progress);
        p_textView = (TextView) rootView.findViewById(R.id.p_textView);
        restart_textView2 = (TextView) rootView.findViewById(R.id.restart_textView2);
        restartTextView = (TextView) rootView.findViewById(R.id.restart_textView);
        restartTextView.setOnClickListener(this);

        initLayout = (RelativeLayout) rootView.findViewById(R.id.initView_layout);
        refreshLayout = (SwipeRefreshLayout) rootView.findViewById(R.id.swipe_id);
        refreshLayout.setColorSchemeResources(
                R.color.loading_color1, R.color.loading_color1,
                R.color.loading_color2, R.color.loading_color2);

        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                downData();
            }
        });
        mListView = (CustomerListView) rootView.findViewById(R.id.xListView);
        mListView.setCallback(this);

        mHandler = new Handler();

        refreshLayout.setRefreshing(true);
        getStudent();
        return rootView;
    }
    private void getStudent(){
//        1.知识课程-精选
//        @RequestMapping(value="/queryAllPhoneKnowledgeCoursepage/{start}/{size}",method={RequestMethod.GET,RequestMethod.POST})
        presenter.getStudent(String.valueOf(start), String.valueOf(end));
    }

    private void setContent() {
        progressBar.setVisibility(View.GONE);
        initLayout.setVisibility(View.GONE);

        if(isRefresh){
            listAdapter = new ListAdapter();
            mListView.setAdapter(listAdapter);
        }else{
            listAdapter.notifyDataSetChanged();
        }
    }

    @Override
    public void downData() {//刷新
        myHandler.sendEmptyMessageDelayed(0, 500);
    }

    @Override
    public void loadData() {//加载更多
        myHandler.sendEmptyMessageDelayed(1, 500);
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
    public void success(List<StudentModel> list) {
        onLoad();
        linkDead();
        isSucceed = true;
        if(isRefresh)studentArrayList.clear();
        if(list.size() != 0){
            studentArrayList.addAll(list);
            setContent();

            isRefresh = false;
        }else{
            linkDead();
            if(isLoadMore) start -= 1;
        }
        isLoadMore = false;
    }

    @Override
    public void failure(Throwable e, boolean isNetWorkError, String msg) {
        showFailureMessage(isNetWorkError, msg, msg);

    }

    @Override
    public StudentContact.presenter initPresenter() {
        return new StudentPresenter(this);
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://刷新
                    if(isSucceed){
                        isRefresh = true;
                        start = 1;
                        getStudent();

                        isSucceed = false;
                    }
                    break;
                case 1://加载更多
                    if(isSucceed){
                        isRefresh = false;
                        start += 1;
                        getStudent();

                        isSucceed = false;
                        isLoadMore = true;
                    }
                    break;
            }
            super.handleMessage(msg);
        }
    };

    private void onLoad() {
        refreshLayout.setRefreshing(false);
        mListView.hideFootView();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.restart_textView:
                restartTextView.setVisibility(View.GONE);
                restart_textView2.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                p_textView.setVisibility(View.VISIBLE);
                break;
        }
    }

    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return studentArrayList.size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return studentArrayList.get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int arg0, View view, ViewGroup arg2) {
            ViewHolder viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder();
                view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_student_item, null);
                viewHolder.examination_logo_image = (ImageView) view.findViewById(R.id.examination_logo_image);
                viewHolder.student_name_text = (TextView) view.findViewById(R.id.student_name_text);//名称
                viewHolder.knowledge_kstime_text = (TextView) view.findViewById(R.id.knowledge_kstime_text);//课时
                viewHolder.knowledge_sctime_text = (TextView) view.findViewById(R.id.knowledge_sctime_text);//时长
                viewHolder.knpwledge_xxmember_text = (TextView) view.findViewById(R.id.knpwledge_xxmember_text);//学习人数

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }
            viewHolder.student_name_text.setText(studentArrayList.get(arg0).getKnowledgename());//名称
            viewHolder.knowledge_kstime_text.setText(studentArrayList.get(arg0).getKnowledgenum()+"课时  ");//课时
            viewHolder.knowledge_sctime_text.setText(studentArrayList.get(arg0).getKnowledgetimelong());//时长
            viewHolder.knpwledge_xxmember_text.setText("共"+studentArrayList.get(arg0).getKnowledgepeople()+"人学习");//学习人数

            String image = HttpConfig.BASE_URL+ URLConfig.loadimage+studentArrayList.get(arg0).getCollegeimage();
            System.out.print("image=="+image);

            mImagerLoader.displayImage(HttpConfig.BASE_URL+URLConfig.loadimage+studentArrayList
                    .get(arg0).getCollegeimage(), viewHolder.examination_logo_image,options);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(preferencesUtil.getIsLog()) {
                        Intent intent = new Intent(getActivity(), Video_Activity.class);
                        intent.putExtra("id",studentArrayList.get(arg0).getId());
                        getActivity().startActivity(intent);
                    }else{
                        Intent intent3 = new Intent(activity, LoginActivity.class);
                        ScreenManager.getScreenManager().startPage(activity, intent3, true);
                    }
                }
            });
            return view;
        }
    }

    public class ViewHolder {
        private TextView student_name_text,knowledge_kstime_text,knowledge_sctime_text,knpwledge_xxmember_text;
        private ImageView examination_logo_image;
    }

    private void linkDead(){
        restartTextView.setVisibility(View.VISIBLE);
        restart_textView2.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        p_textView.setVisibility(View.GONE);

        if(null == listAdapter){
            listAdapter = new ListAdapter();
            mListView.setAdapter(listAdapter);
        }else{
            listAdapter.notifyDataSetChanged();
        }

        if(studentArrayList.size() > 0){
            initLayout.setVisibility(View.GONE);
        }else{
            initLayout.setVisibility(View.VISIBLE);
        }
    }
}
