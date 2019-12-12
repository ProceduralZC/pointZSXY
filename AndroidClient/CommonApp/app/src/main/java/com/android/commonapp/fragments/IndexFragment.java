package com.android.commonapp.fragments;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.android.commonapp.R;
import com.android.commonapp.activity.LoginActivity;
import com.android.commonapp.activity.Video_Activity;
import com.android.commonapp.activity.boutique.Boutique_Activity;
import com.android.commonapp.activity.hot.HotInfo_Activity;
import com.android.commonapp.activity.kngcourse.KnowledgeCourse_Activity;
import com.android.commonapp.activity.kngpath.KnowledgePath_Activity;
import com.android.commonapp.adapter.RecyclerViewBaseAdapter;
import com.android.commonapp.config.HttpConfig;
import com.android.commonapp.config.URLConfig;
import com.android.commonapp.contact.IndexContact;
import com.android.commonapp.manager.ScreenManager;
import com.android.commonapp.models.MainPageModel;
import com.android.commonapp.presenter.IndexPresenter;
import com.android.commonapp.views.CustomerListView;
import com.android.commonapp.views.FlowIndicator;
import com.android.commonapp.views.GuideGallery;
import com.android.commonapp.views.MyGridView;
import com.android.commonapp.views.NavigationBar;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.List;
/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe: 首页
 */
public class IndexFragment extends BaseFragment<IndexPresenter> implements IndexContact.view
        ,CustomerListView.Callback{
    private List<MainPageModel> modelList = new ArrayList<MainPageModel>();//首页类型
    private List<MainPageModel> modelluncher = new ArrayList<MainPageModel>();//首页轮播图
    private View rootView,header,normalHeader;
    private Activity activity;
    private SwipeRefreshLayout refreshLayout;
    public CustomerListView mainpage_listview;
    public FrameLayout lunbo_image_liner;
    public LinearLayout normal_luncher_liner,havedate_liner,normal_liner;
    public Handler mHandler;
    private DisplayImageOptions options;
    private DisplayImageOptions options2;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        activity = (Activity) getActivity();
        options= new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设置
                //如Bitmap.Config.ARGB_8888
                .showImageOnLoading(R.mipmap.mian_meun_image)   //默认图片
                .showImageForEmptyUri(R.mipmap.mian_meun_image)    //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.mipmap.mian_meun_image)// 加载失败显示的图片
                .build();
        options2 = new DisplayImageOptions.Builder()
                .cacheInMemory(true).cacheOnDisc(true)
                .imageScaleType(ImageScaleType.IN_SAMPLE_INT)
                .bitmapConfig(Bitmap.Config.RGB_565)// 防止内存溢出的，图片太多就这这个。还有其他设置
                //如Bitmap.Config.ARGB_8888
                .showImageOnLoading(R.mipmap.normal_bigs_image)   //默认图片
                .showImageForEmptyUri(R.mipmap.normal_bigs_image)    //url爲空會显示该图片，自己放在drawable里面的
                .showImageOnFail(R.mipmap.normal_bigs_image)// 加载失败显示的图片
                .build();

        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_index, container, false);
        NavigationBar navigationBar = (NavigationBar) rootView.findViewById(R.id.navigationBar);
        navigationBar.setTitle("知识学院");
        navigationBar.hideBackButton();

        lunbo_image_liner = (FrameLayout) rootView.findViewById(R.id.lunbo_image_liner);
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
        mainpage_listview = (CustomerListView) rootView.findViewById(R.id.mainpage_listview);
        mainpage_listview.setCallback(this);

        header = View.inflate(getActivity(), R.layout.view_luncherpage,null);
        lunbo_image_liner = (FrameLayout) header.findViewById(R.id.lunbo_image_liner);
        havedate_liner = (LinearLayout) header.findViewById(R.id.havedate_liner);
        mainpage_listview.addHeaderView(header);

        mHandler = new Handler();
        //初始化接口数据
        initData();
        mianLuncher();
        return rootView;
    }
    @Override
    public void downData() {//刷新
        myHandler.sendEmptyMessageDelayed(0, 100);
//        onLoad();
    }
    @Override
    public void loadData() {//加载更多
//        myHandler.sendEmptyMessageDelayed(1, 100);
        onLoad();
    }

    //获取主页分类
    private void initData(){
        presenter.getData(1);
    }
    //获取首页轮播图
    private void mianLuncher(){
        presenter.getMainLuncher(2);
    }

    @Override
    public void showLoadingDialog(String msg,boolean ifcancel,boolean showloading) {
        showProgressDialog(msg,ifcancel,showloading);
    }

    Handler myHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 0://刷新
                    initData();
//                    mianLuncher();

                    break;
                case 1://加载更多
                    onLoad();

                    break;
            }
            super.handleMessage(msg);
        }
    };

    @Override
    public void dismissLoadingDialog() {
        hideProgressDialog();
    }

    @Override
    public void success(List<MainPageModel> list,int type) {
        onLoad();
        if(type == 1) {//首页分类
            modelList.clear();
            modelList.addAll(list);
            String[] title1 = {"知识课件", "知识路径", "精品系列知识", "热门知识"};
            int[] image1 = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
            ListAdapter1 listAdap = new ListAdapter1(title1, image1);
            mainpage_listview.setAdapter(listAdap);
        }else if(type == 2){
            modelluncher.clear();
            modelluncher.addAll(list);
            if(modelluncher.size() > 0){
                havedate_liner.setVisibility(View.VISIBLE);
                if(null != normal_liner) {
                    normal_liner.setVisibility(View.GONE);
                    normalHeader.setVisibility(View.GONE);
                }

                initFocus();
            }else{
                if(null == normalHeader) {
                    normalHeader = View.inflate(getActivity(), R.layout.view_lunchernormal, null);
                    normal_liner = (LinearLayout) normalHeader.findViewById(R.id.normal_liner);
                    mainpage_listview.addHeaderView(normalHeader);
                }

                if(null != havedate_liner) {
                    havedate_liner.setVisibility(View.GONE);
                }
                if(null != normal_liner) {
                    normal_liner.setVisibility(View.VISIBLE);
                    normalHeader.setVisibility(View.VISIBLE);
                }
            }
        }

    }

    private void onLoad() {
        refreshLayout.setRefreshing(false);
        mainpage_listview.hideFootView();
    }

    @Override
    public void failure(Throwable e, boolean isNetWorkError, String msg) {
        System.out.print("");
        if(null == normalHeader) {
            normalHeader = View.inflate(getActivity(), R.layout.view_lunchernormal, null);
            normal_liner = (LinearLayout) normalHeader.findViewById(R.id.normal_liner);
            mainpage_listview.addHeaderView(normalHeader);
        }

        if(null != havedate_liner) {
            havedate_liner.setVisibility(View.GONE);
        }
        if(null != normal_liner) {
            normal_liner.setVisibility(View.VISIBLE);
            normalHeader.setVisibility(View.VISIBLE);
        }

        String[] title1 = {"知识课件", "知识路径", "精品系列知识", "热门知识"};
        int[] image1 = {R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher, R.mipmap.ic_launcher};
        ListAdapter1 listAdap = new ListAdapter1(title1, image1);
        mainpage_listview.setAdapter(listAdap);
    }

    @Override
    public IndexPresenter initPresenter() {
        return new IndexPresenter(this);
    }

    //所有列表信息
    ListAdapter listAdapter = null;
    ListAdapter12 listAdapter12 = null;
    ListAdapter13 listAdapter13 = null;
    ListAdapter14 listAdapter14 = null;

    class ListAdapter1 extends BaseAdapter {
        public String[] title;
        public int[] image;
        public ListAdapter1(String[] title,int[] image){
            this.title = title;
            this.image = image;
        }
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return title.length;
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return title[arg0];
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int arg0, View view, ViewGroup arg2) {
            ViewHolder1 viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder1();
                view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_mainpagelist_item, null);
                viewHolder.mainpage_logo_image = (ImageView) view.findViewById(R.id.mainpage_logo_image);
                viewHolder.mainpage_title_text = (TextView) view.findViewById(R.id.mainpage_title_text);
                viewHolder.maianpage_info_gridview = (MyGridView) view.findViewById(R.id.maianpage_info_gridview);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder1) view.getTag();
            }

            viewHolder.mainpage_logo_image.setVisibility(View.GONE);
            viewHolder.mainpage_title_text.setText(title[arg0]+"");//名称

            viewHolder.maianpage_info_gridview.setClickable(false);
            viewHolder.maianpage_info_gridview.setPressed(false);
            viewHolder.maianpage_info_gridview.setEnabled(false);

            if(arg0 == 0) {//知识课件
                if(null != modelList &&  modelList.size() > 0) {
                    LinearLayout.LayoutParams params31 = (LinearLayout.LayoutParams) viewHolder.maianpage_info_gridview.getLayoutParams();
                    Display display = getActivity().getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
                    Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
                    display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
                    int width = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
                    int height = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
                    System.out.print("" + width);
                    params31.width = (width * 5 / 6) * modelList.get(0).getCourseware().size();
                    params31.height = height * 6 / 10;
                    viewHolder.maianpage_info_gridview.setLayoutParams(params31);
                }
            }else if(arg0 == 1) {
                if(null != modelList &&  modelList.size() > 0) {
                    LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) viewHolder.maianpage_info_gridview.getLayoutParams();
                    Display display = getActivity().getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
                    Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
                    display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
                    int width = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
                    int height = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
                    System.out.print("" + width);
                    params3.width = (width * 5 / 6) * modelList.get(0).getPath().size();
                    params3.height = height * 6 / 10;
                    viewHolder.maianpage_info_gridview.setLayoutParams(params3);
                }
            }else if(arg0 == 2) {
                if(null != modelList &&  modelList.size() > 0) {
                    LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) viewHolder.maianpage_info_gridview.getLayoutParams();
                    Display display = getActivity().getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
                    Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
                    display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
                    int width = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
                    int height = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
                    System.out.print("" + width);
                    params3.width = (width * 5 / 6) * modelList.get(0).getBoutique().size();
                    params3.height = height * 6 / 10;
                    viewHolder.maianpage_info_gridview.setLayoutParams(params3);
                }
            }else if(arg0 == 3) {
                if(null != modelList &&  modelList.size() > 0) {
                    LinearLayout.LayoutParams params3 = (LinearLayout.LayoutParams) viewHolder.maianpage_info_gridview.getLayoutParams();
                    Display display = getActivity().getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
                    Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
                    display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
                    int width = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
                    int height = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
                    System.out.print("" + width);
                    params3.width = (width * 5 / 6) * modelList.get(0).getHot().size();
                    params3.height = height * 6 / 10;
                    viewHolder.maianpage_info_gridview.setLayoutParams(params3);
                }
            }

            if(arg0 == 0) {//知识课件
                if(null != modelList &&  modelList.size() > 0) {
                    listAdapter = new ListAdapter();
                    viewHolder.maianpage_info_gridview.setNumColumns(modelList.get(0).getCourseware().size());
                    viewHolder.maianpage_info_gridview.setAdapter(listAdapter);
                }
            }else if(arg0 == 1){//知识路径
                if(null != modelList &&  modelList.size() > 0) {
                    listAdapter12 = new ListAdapter12();
                    viewHolder.maianpage_info_gridview.setNumColumns(modelList.get(0).getPath().size());
                    viewHolder.maianpage_info_gridview.setAdapter(listAdapter12);
                }
            } else if(arg0 == 2){//精选
                if(null != modelList &&  modelList.size() > 0) {
                    listAdapter13 = new ListAdapter13();
                    viewHolder.maianpage_info_gridview.setNumColumns(modelList.get(0).getBoutique().size());
                    viewHolder.maianpage_info_gridview.setAdapter(listAdapter13);
                }
            }else if(arg0 == 3){//热门
                if(null != modelList &&  modelList.size() > 0) {
                    listAdapter14 = new ListAdapter14();
                    viewHolder.maianpage_info_gridview.setNumColumns(modelList.get(0).getHot().size());
                    viewHolder.maianpage_info_gridview.setAdapter(listAdapter14);
                }
            }

            return view;
        }
    }

    public class ViewHolder1 {
        private ImageView mainpage_logo_image;
        private TextView mainpage_title_text;
        private MyGridView maianpage_info_gridview;
    }

    //知识课件
    class ListAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return modelList.get(0).getCourseware().size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return modelList.get(0).getCourseware().get(arg0);
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
                view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_maianpagelist_item, null);
                viewHolder.mainpage_info_logo_image = (ImageView) view.findViewById(R.id.mainpage_info_logo_image);
                viewHolder.mainpage_info_name_text = (TextView) view.findViewById(R.id.mainpage_info_name_text);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            LinearLayout.LayoutParams params3 =(LinearLayout.LayoutParams) viewHolder.mainpage_info_logo_image.getLayoutParams();
            Display display = getActivity().getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
            Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
            display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
            int width = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
            int height = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
            params3.width = width*5/6;
            params3.height = height*6/10;
            viewHolder.mainpage_info_logo_image.setLayoutParams(params3);
            viewHolder.mainpage_info_name_text.setText(modelList.get(0).getCourseware().get(arg0).getCollegename());//名称

            String image = HttpConfig.BASE_URL+ URLConfig.loadimage+modelList.get(0).getCourseware().get(arg0).getCollegeimage();
            System.out.print("image=="+image);

            mImagerLoader.displayImage(HttpConfig.BASE_URL+URLConfig.loadimage+modelList.get(0).getCourseware()
                    .get(arg0).getCollegeimage(), viewHolder.mainpage_info_logo_image,options2);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent3 = new Intent(getActivity(), KnowledgeCourse_Activity.class);
                    intent3.putExtra("id",modelList.get(0).getCourseware().get(arg0).getId());
                    intent3.putExtra("name",modelList.get(0).getCourseware().get(arg0).getCollegename());
                    getActivity().startActivity(intent3);
                }
            });

            return view;
        }
    }

    public class ViewHolder {
        private ImageView mainpage_info_logo_image;
        private TextView mainpage_info_name_text;
    }
    //知识路径
    class ListAdapter12 extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return modelList.get(0).getPath().size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return modelList.get(0).getPath().get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int arg0, View view, ViewGroup arg2) {
            ViewHolder12 viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder12();
                view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_coresepath_item, null);
                viewHolder.coursepath_info_logo_image = (ImageView) view.findViewById(R.id.coursepath_info_logo_image);
                viewHolder.coursepath_info_name_text = (TextView) view.findViewById(R.id.coursepath_info_name_text);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder12) view.getTag();
            }

            LinearLayout.LayoutParams params3 =(LinearLayout.LayoutParams) viewHolder.coursepath_info_logo_image.getLayoutParams();
            Display display = getActivity().getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
            Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
            display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
            int width = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
            int height = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
            System.out.print(""+width);
            params3.width = width*5/6;
            params3.height = height*6/10;
            viewHolder.coursepath_info_logo_image.setLayoutParams(params3);

//			viewHolder.mainpage_info_logo_image.setImageResource(image[arg0]);

            viewHolder.coursepath_info_name_text.setText(modelList.get(0).getPath().get(arg0).getCollegename());//名称

            String image = HttpConfig.BASE_URL+ URLConfig.loadimage+modelList.get(0).getPath().get(arg0).getCollegeimage();
            System.out.print("image=="+image);

            mImagerLoader.displayImage(HttpConfig.BASE_URL+URLConfig.loadimage+modelList.get(0).getPath()
                    .get(arg0).getCollegeimage(), viewHolder.coursepath_info_logo_image,options2);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent3 = new Intent(getActivity(), KnowledgePath_Activity.class);
                    intent3.putExtra("id",modelList.get(0).getPath().get(arg0).getId());
                    intent3.putExtra("name",modelList.get(0).getPath().get(arg0).getCollegename());
                    getActivity().startActivity(intent3);
                }
            });

            return view;
        }
    }

    public class ViewHolder12 {
        private ImageView coursepath_info_logo_image;
        private TextView coursepath_info_name_text;
    }

    //精品系列知识
    class ListAdapter13 extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return modelList.get(0).getBoutique().size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return modelList.get(0).getBoutique().get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int arg0, View view, ViewGroup arg2) {
            ViewHolder13 viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder13();
                view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_boutique_item, null);
                viewHolder.boutique_info_logo_image = (ImageView) view.findViewById(R.id.boutique_info_logo_image);
                viewHolder.boutique_info_name_text = (TextView) view.findViewById(R.id.boutique_info_name_text);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder13) view.getTag();
            }

            LinearLayout.LayoutParams params3 =(LinearLayout.LayoutParams) viewHolder.boutique_info_logo_image.getLayoutParams();
            Display display = getActivity().getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
            Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
            display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
            int width = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
            int height = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
            System.out.print(""+width);
            params3.width = width*5/6;
            params3.height = height*6/10;
            viewHolder.boutique_info_logo_image.setLayoutParams(params3);

            viewHolder.boutique_info_name_text.setText(modelList.get(0).getBoutique().get(arg0).getCollegename());//名称

            String image = HttpConfig.BASE_URL+ URLConfig.loadimage+modelList.get(0).getBoutique().get(arg0).getCollegeimage();
            System.out.print("image=="+image);

            mImagerLoader.displayImage(HttpConfig.BASE_URL+URLConfig.loadimage+modelList.get(0).getBoutique()
                    .get(arg0).getCollegeimage(), viewHolder.boutique_info_logo_image,options2);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    Intent intent3 = new Intent(getActivity(), Boutique_Activity.class);
                    intent3.putExtra("id",modelList.get(0).getBoutique().get(arg0).getId());
                    intent3.putExtra("name",modelList.get(0).getBoutique().get(arg0).getCollegename());
                    getActivity().startActivity(intent3);
                }
            });

            return view;
        }
    }

    public class ViewHolder13 {
        private ImageView boutique_info_logo_image;
        private TextView boutique_info_name_text;
    }

    //热门知识
    class ListAdapter14 extends BaseAdapter {
        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return modelList.get(0).getHot().size();
        }

        @Override
        public Object getItem(int arg0) {
            // TODO Auto-generated method stub
            return modelList.get(0).getHot().get(arg0);
        }

        @Override
        public long getItemId(int arg0) {
            // TODO Auto-generated method stub
            return 0;
        }

        @Override
        public View getView(final int arg0, View view, ViewGroup arg2) {
            ViewHolder14 viewHolder = null;
            if (view == null) {
                viewHolder = new ViewHolder14();
                view = LayoutInflater.from(getActivity()).inflate(R.layout.adapter_hot_item, null);
                viewHolder.hot_info_logo_image = (ImageView) view.findViewById(R.id.hot_info_logo_image);
                viewHolder.hot_info_name_text = (TextView) view.findViewById(R.id.hot_info_name_text);

                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder14) view.getTag();
            }

            LinearLayout.LayoutParams params3 =(LinearLayout.LayoutParams) viewHolder.hot_info_logo_image.getLayoutParams();
            Display display = getActivity().getWindowManager().getDefaultDisplay();//得到当前屏幕的显示器对象
            Point size = new Point();//创建一个Point点对象用来接收屏幕尺寸信息
            display.getSize(size);//Point点对象接收当前设备屏幕尺寸信息
            int width = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
            int height = size.x / 2;//从Point点对象中获取屏幕的宽度(单位像素)
            System.out.print(""+width);
            params3.width = width*5/6;
            params3.height = height*6/10;
            viewHolder.hot_info_logo_image.setLayoutParams(params3);

            viewHolder.hot_info_name_text.setText(modelList.get(0).getHot().get(arg0).getCollegename());//名称

            String image = HttpConfig.BASE_URL+ URLConfig.loadimage+modelList.get(0).getHot().get(arg0).getCollegeimage();
            System.out.print("image=="+image);

            mImagerLoader.displayImage(HttpConfig.BASE_URL+URLConfig.loadimage+modelList
                    .get(0).getHot().get(arg0).getCollegeimage(), viewHolder.hot_info_logo_image,options2);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO Auto-generated method stub
                    if(preferencesUtil.getIsLog()) {
                        Intent intent3 = new Intent(getActivity(), HotInfo_Activity.class);
                        intent3.putExtra("id",modelList.get(0).getHot().get(arg0).getId());
                        getActivity().startActivity(intent3);
                    }else{
                        Intent intent3 = new Intent(getActivity(), LoginActivity.class);
                        ScreenManager.getScreenManager().startPage(getActivity(), intent3, true);
                    }

                }
            });

            return view;
        }
    }

    public class ViewHolder14 {
        private ImageView hot_info_logo_image;
        private TextView hot_info_name_text;
    }

    /**
     * 轮播图
     */
    private GuideGallery mGallery;//自定义图片预览
    private FlowIndicator mMyView;// 图片滚动点
    private static final int PHOTO_CHANGE_TIME = 10000;
    private static final int MSG_CHANGE_PHOTO = 1;
    private void initFocus() {
        if(null != mGallery){
            mGallery = null;
        }
        if(null != mMyView){
            mMyView = null;
        }
        Display currDisplay = getActivity().getWindowManager().getDefaultDisplay();//获取屏幕宽高
        int displayWidth = currDisplay.getWidth();
        int displayheight = currDisplay.getHeight();
        int one = displayWidth; //
        int two = displayWidth; //
        LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) lunbo_image_liner.getLayoutParams();
        params.width = one+40;
        params.height = (one / 2)-10;
        lunbo_image_liner.setLayoutParams(params);

        mGallery = (GuideGallery) header.findViewById(R.id.home_gallery);
        mGallery.setVisibility(View.VISIBLE);
        mGallery.setSize(modelluncher.size());
        mMyView = (FlowIndicator) header.findViewById(R.id.myView);
        mHandler = new Handler(getActivity().getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                // TODO Auto-generated method stub
                super.handleMessage(msg);
                switch (msg.what) {
                    case MSG_CHANGE_PHOTO:
                        System.out.println(""+MSG_CHANGE_PHOTO);
                        mGallery.onKeyDown(KeyEvent.KEYCODE_DPAD_RIGHT, null);
                        mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO,PHOTO_CHANGE_TIME);
                }
            }
        };
        GalleryAdapter mGalleryAdapter = new GalleryAdapter(modelluncher);
//        设置内容大小
        mMyView.setCount(modelluncher.size());
        // 设置
        mHandler.sendEmptyMessageDelayed(MSG_CHANGE_PHOTO, PHOTO_CHANGE_TIME);
        // 设置GalleryAdapter
        mGallery.setAdapter(mGalleryAdapter);
        mGallery.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> arg0, View arg1,
                                       int arg2, long arg3) {
                mMyView.setSeletion(arg2 % modelluncher.size());
            }
            @Override
            public void onNothingSelected(AdapterView<?> arg0) {

            }
        });
        // 点击每一条事件
        mGallery.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
//                Intent intent = new Intent(MainPage_Activity.this,MainPageLuncherDetailActivity.class);
//                intent.putExtra("activityName",commonality.getLunchImageBean().get(arg2 % commonality.getLunchImageBean().size()).getActivityName());
//                intent.putExtra("accessUrl",commonality.getLunchImageBean().get(arg2 % commonality.getLunchImageBean().size()).getAccessUrl());
//                intent.putExtra("createDate",commonality.getLunchImageBean().get(arg2 % commonality.getLunchImageBean().size()).getCreateDate());
//                ScreenManager.getScreenManager().StartPage(MainPage_Activity.this,intent, true);
            }
        });
    }

    /** 轮播图Adapter */
    class GalleryAdapter extends BaseAdapter {
        private List<MainPageModel> modelluncher;
        public GalleryAdapter(List<MainPageModel> modelLists){
            this.modelluncher = modelLists;

        }
        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object getItem(int position) {
            return modelluncher.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View view, ViewGroup arg2) {
            final ViewHolderFocus holder;
            if (view == null) {
                holder = new ViewHolderFocus();
                view = LayoutInflater.from(getActivity()).inflate(R.layout.focus_gallery_item, null);
                holder.imageView = (ImageView) view.findViewById(R.id.home_img);
                view.setTag(holder);
            } else {
                holder = (ViewHolderFocus) view.getTag();
            }

            Display currDisplay = getActivity().getWindowManager().getDefaultDisplay();
            int displayWidth = currDisplay.getWidth();
            int displayheight = currDisplay.getHeight();
            int one = displayWidth;
            int two = displayWidth;
            LinearLayout.LayoutParams params=(LinearLayout.LayoutParams) holder.imageView.getLayoutParams();
            params.width = one -90;
            params.height = (one / 2) - 30;
            holder.imageView.setLayoutParams(params);

           String image = HttpConfig.BASE_URL+URLConfig.loadimage+modelluncher.get(position % modelluncher.size()).getCollegeimage();
            System.out.print("image=="+image);

            mImagerLoader.displayImage(HttpConfig.BASE_URL+URLConfig.loadimage+modelluncher
                    .get(position % modelluncher.size()).getCollegeimage(), holder.imageView,options);

            return view;
        }
    }

    private static class ViewHolderFocus {
        ImageView imageView;
    }
}