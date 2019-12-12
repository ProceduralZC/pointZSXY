package com.example.gsyvideoplayer;

import android.annotation.TargetApi;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.AppCompatActivity;
import android.transition.Transition;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.example.gsyvideoplayer.listener.OnTransitionListener;
import com.example.gsyvideoplayer.listener.SampleListener;
import com.example.gsyvideoplayer.model.SwitchVideoModel;
import com.example.gsyvideoplayer.video.LandLayoutVideo;
import com.shuyu.gsyvideoplayer.GSYPreViewManager;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.GSYVideoPlayer;

import com.shuyu.gsyvideoplayer.listener.LockClickListener;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;


public class DetailPlayer extends AppCompatActivity {
    NestedScrollView postDetailNestedScroll;
    LandLayoutVideo detailPlayer;
    RelativeLayout activityDetailPlayer;
    private boolean isPlay;
    private boolean isPause;
    public final static String IMG_TRANSITION = "IMG_TRANSITION";
    public final static String TRANSITION = "TRANSITION";
    private boolean isTransition;
    private Transition transition;
    private OrientationUtils orientationUtils;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_player);
        ButterKnife.bind(this);
        detailPlayer = (LandLayoutVideo) findViewById(R.id.detail_player);
        activityDetailPlayer = (RelativeLayout) findViewById(R.id.activity_detail_player);
        postDetailNestedScroll = (NestedScrollView) findViewById(R.id.post_detail_nested_scroll);

//        String url = "http://glearnqd.oss-cn-qingdao.aliyuncs.com/max%2Fmanman%2Fvedio%2FPDCA.mp4";
//        String url = "http://glearnqd.oss-cn-qingdao.aliyuncs.com/max%2Fmanman%2Fvedio%2F%E5%86%B2%E7%AA%81%E7%AE%A1%E7%90%86.mp4";
//        String url = "http://ivi.bupt.edu.cn/hls/cctv1hd.m3u8";
//        detailPlayer.setUp(url, false, null, "测试视频");//默认一条路径

        //借用了jjdxm_ijkplayer的URL
//        String source1 = "http://glearnqd.oss-cn-qingdao.aliyuncs.com/max%2Fmanman%2Fvedio%2F%E5%95%86%E5%8A%A1%E8%8B%B1%E8%AF%AD%E5%86%99%E4%BD%9C.mp4";
        String source1 = "http://keer.oss-cn-qingdao.aliyuncs.com/salesystem/03%E5%A5%97%E8%A3%85%E8%A7%86%E9%A2%91/03%E5%A5%97%E8%A3%85%E8%A7%86%E9%A2%91.m3u8";
        String name = "普通";
        SwitchVideoModel switchVideoModel = new SwitchVideoModel(name, source1);

//        String source2 = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f30.mp4";
//        String source2 = "rtmp://live.hkstv.hk.lxdns.com/live/hks";
        String source2 = "http://glearnqd.oss-cn-qingdao.aliyuncs.com/max%2Fmanman%2Fvedio%2F%E5%95%86%E5%8A%A1%E8%8B%B1%E8%AF%AD%E5%86%99%E4%BD%9C.mp4";
        String name2 = "清晰";
        SwitchVideoModel switchVideoModel2 = new SwitchVideoModel(name2, source2);

        List<SwitchVideoModel> list = new ArrayList<>();
        list.add(switchVideoModel);
        list.add(switchVideoModel2);

        detailPlayer.setUp(list, true, "一点知识学院开启");

        GSYVideoManager.instance().setTimeOut(4000, true);

        //增加封面
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.xxx1);
        detailPlayer.setThumbImageView(imageView);

//        back = (ImageView) findViewById(R.id.back);//返回

        resolveNormalVideoUI();

        //外部辅助的旋转，帮助全屏
        orientationUtils = new OrientationUtils(this, detailPlayer);
        //初始化不打开外部的旋转
        orientationUtils.setEnable(false);

        detailPlayer.setIsTouchWiget(true);
        //detailPlayer.setIsTouchWigetFull(false);
        //关闭自动旋转
        detailPlayer.setRotateViewAuto(false);
        detailPlayer.setLockLand(false);
        detailPlayer.setShowFullAnimation(false);
        detailPlayer.setNeedLockFull(true);

        //过渡动画
        initTransition();//默认开始播放，注销 默认不开启播放

        //detailPlayer.setOpenPreView(false);
        detailPlayer.getFullscreenButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //直接横屏
                orientationUtils.resolveByClick();

                //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
                detailPlayer.startWindowFullscreen(DetailPlayer.this, true, true);
            }
        });

        detailPlayer.setStandardVideoAllCallBack(new SampleListener() {
            @Override
            public void onPrepared(String url, Object... objects) {
                super.onPrepared(url, objects);
                //开始播放了才能旋转和全屏
                orientationUtils.setEnable(true);
                isPlay = true;
            }

            @Override
            public void onAutoComplete(String url, Object... objects) {
                System.out.print("objects=="+objects.length);
                finish();
                super.onAutoComplete(url, objects);
            }

            @Override
            public void onClickStartError(String url, Object... objects) {
                super.onClickStartError(url, objects);
            }

            @Override
            public void onQuitFullscreen(String url, Object... objects) {
                super.onQuitFullscreen(url, objects);
                if (orientationUtils != null) {
                    orientationUtils.backToProtVideo();
                }
            }
        });
        detailPlayer.setLockClickListener(new LockClickListener() {
            @Override
            public void onClick(View view, boolean lock) {
                if (orientationUtils != null) {
                    //配合下方的onConfigurationChanged
                    orientationUtils.setEnable(!lock);
                }
            }
        });
        //设置返回按键功能
        detailPlayer.getBackButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }

    private void initTransition() {
        if (isTransition && Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            postponeEnterTransition();
            ViewCompat.setTransitionName(detailPlayer, IMG_TRANSITION);
            addTransitionListener();
            startPostponedEnterTransition();
        } else {
            detailPlayer.startPlayLogic();
        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private boolean addTransitionListener() {
        transition = getWindow().getSharedElementEnterTransition();
        if (transition != null) {
            transition.addListener(new OnTransitionListener(){
                @Override
                public void onTransitionEnd(Transition transition) {
                    super.onTransitionEnd(transition);
                    detailPlayer.startPlayLogic();
                    transition.removeListener(this);
                }
            });
            return true;
        }
        return false;
    }

    @Override
    public void onBackPressed() {

        if (orientationUtils != null) {
            orientationUtils.backToProtVideo();
        }

        if (StandardGSYVideoPlayer.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onPause() {
        super.onPause();
        detailPlayer.onVideoPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        GSYVideoPlayer.releaseAllVideos();
        //GSYPreViewManager.instance().releaseMediaPlayer();
        if (orientationUtils != null)
            orientationUtils.releaseListener();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            if (newConfig.orientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
                if (!detailPlayer.isIfCurrentIsFullscreen()) {
                    detailPlayer.startWindowFullscreen(DetailPlayer.this, true, true);
                }
            } else {
                //新版本isIfCurrentIsFullscreen的标志位内部提前设置了，所以不会和手动点击冲突
                if (detailPlayer.isIfCurrentIsFullscreen()) {
                    StandardGSYVideoPlayer.backFromWindowFull(this);
                }
                if (orientationUtils != null) {
                    orientationUtils.setEnable(true);
                }
            }
        }
    }

    private void resolveNormalVideoUI() {
        //增加title
        detailPlayer.getTitleTextView().setVisibility(View.VISIBLE);
        detailPlayer.getTitleTextView().setText("一点知识学院开启");
        detailPlayer.getBackButton().setVisibility(View.VISIBLE);
    }
}
