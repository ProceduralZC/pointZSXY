package com.android.commonapp.views;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.commonapp.R;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @date: 2017/11/29.
 * @author: CHEN
 * @describe: RecyclerView下拉刷新 上拉加载更多
 */

public abstract class PullBaseView<T extends RecyclerView> extends LinearLayout {
    protected T mRecyclerView;
    private boolean isCanScrollAtRefreshing = false; // 刷新时是否可滑动
    private boolean isCanPullDown = true; // 是否可下拉
    private boolean isCanPullUp = true; // 是否可上拉
    // 下拉状态
    private static final int PULL_UP_STATE = 0;
    private static final int PULL_DOWN_STATE = 1;
    // 刷新时状态
    private static final int PULL_TO_REFRESH = 2;
    private static final int RELEASE_TO_REFRESH = 3;
    private static final int REFRESHING = 4;

    private int mLastMotionY;
    // 顶部view控件
    private View mHeaderView;
    private int mHeaderViewHeight;
    private ImageView mHeaderImageView;
    private TextView mHeaderTextView;
    private TextView mHeaderUpdateTextView;
    //private ProgressBar mHeaderProgressBar;
    private View headViewAnimation;
    private AnimationDrawable headAnimationDrawable;

    // 底部view控件
    private View mFooterView;
    private int mFooterViewHeight;
    private TextView mFooterTextView;
    private LinearLayout loadingMore;
    //private ProgressBar mFooterProgressBar;
    private View footerViewAnimation;
    private AnimationDrawable footerAnimationDrawable;

    private LayoutInflater mInflater;
    private int mHeaderState;
    private int mFooterState;
    private int mPullState;

    // 变为向下的箭头,改变箭头方向
    private RotateAnimation mFlipAnimation;
    // 变为逆向的箭头,旋转
    private RotateAnimation mReverseFlipAnimation;
    private OnRefreshListener refreshListener;

    public PullBaseView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public PullBaseView(Context context) {
        super(context);
    }


    /**
     * 初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        // 在代码中加载我们需要的所有动画，而不是通过XML方式
        mFlipAnimation = new RotateAnimation(0, -180, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mFlipAnimation.setInterpolator(new LinearInterpolator());
        mFlipAnimation.setDuration(250);
        mFlipAnimation.setFillAfter(true);
        mReverseFlipAnimation = new RotateAnimation(-180, 0, RotateAnimation.RELATIVE_TO_SELF, 0.5f, RotateAnimation.RELATIVE_TO_SELF, 0.5f);
        mReverseFlipAnimation.setInterpolator(new LinearInterpolator());
        mReverseFlipAnimation.setDuration(250);
        mReverseFlipAnimation.setFillAfter(true);

        mInflater = LayoutInflater.from(getContext());
        // 顶部view在此添加,保证是第一个添加到linearlayout的最上端
        addHeaderView();
        mRecyclerView = createRecyclerView(context, attrs);
        mRecyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
        addView(mRecyclerView);
    }

    private void addHeaderView() {
        // 顶部view
        mHeaderView = mInflater.inflate(R.layout.refresh_header, this, false);
        mHeaderImageView = (ImageView) mHeaderView.findViewById(R.id.pull_to_refresh_image);
        mHeaderTextView = (TextView) mHeaderView.findViewById(R.id.pull_to_refresh_text);
        mHeaderUpdateTextView = (TextView) mHeaderView.findViewById(R.id.pull_to_refresh_updated_at);
        //mHeaderProgressBar = (ProgressBar) mHeaderView.findViewById(R.id.pull_to_refresh_progress);
        headViewAnimation = (View) mHeaderView.findViewById(R.id.header_animation);
        headAnimationDrawable = (AnimationDrawable) headViewAnimation.getBackground();
        mHeaderUpdateTextView.setText("最近刷新:" + getFormatDateString("MM-dd HH:mm"));
        // 顶部layout
        measureView(mHeaderView);
        mHeaderViewHeight = mHeaderView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mHeaderViewHeight);
        // 设置topMargin的值为负的header View高度,即将其隐藏在最上方
        params.topMargin = -(mHeaderViewHeight);
        // mHeaderView.setLayoutParams(params1);
        addView(mHeaderView, params);

    }

    public static final String getFormatDateString(String format) {
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date());
    }

    private void addFooterView() {
        // 底部view
        mFooterView = mInflater.inflate(R.layout.refresh_footer, this, false);
        mFooterTextView = (TextView) mFooterView.findViewById(R.id.pull_to_load_text);
        //mFooterProgressBar = (ProgressBar) mFooterView.findViewById(R.id.pull_to_load_progress);
        loadingMore = (LinearLayout) mFooterView.findViewById(R.id.ll_loading_more);
        footerViewAnimation = (View) mFooterView.findViewById(R.id.footer_animation);
        footerAnimationDrawable = (AnimationDrawable) footerViewAnimation.getBackground();
        measureView(mFooterView);
        mFooterViewHeight = mFooterView.getMeasuredHeight();
        LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT, mFooterViewHeight);
        addView(mFooterView, params);
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        addFooterView();
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) { // 刷新时禁止滑动
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                if (!isCanScrollAtRefreshing) {
                    if (mHeaderState == REFRESHING || mFooterState == REFRESHING) {
                        return true;
                    }
                }
                break;
        }
        return super.dispatchTouchEvent(ev);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent e) {
        int y = (int) e.getRawY();
        int x = (int) e.getRawX();
        switch (e.getAction()) {
            case MotionEvent.ACTION_DOWN:
                // 首先拦截down事件,记录y坐标
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                // deltaY > 0 是向下运动,< 0是向上运动
                int deltaY = y - mLastMotionY;
                if (isRefreshViewScroll(deltaY)) {
                    return true;
                }
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return false;
    }

    /*
     * 如果在onInterceptTouchEvent()方法中没有拦截(即onInterceptTouchEvent()方法中 return
     * false)PullBaseView 的子View来处理;否则由下面的方法来处理(即由PullToRefreshView自己来处理)
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int y = (int) event.getRawY();
        switch (event.getAction()) {
//            case MotionEvent.ACTION_DOWN: // 按下了屏幕，第一个执行也是必然执行的方法
//                break;
            case MotionEvent.ACTION_MOVE: // 移动手势，会不断的执行直到触摸停止
                int deltaY = y - mLastMotionY;
                if (isCanPullDown && mPullState == PULL_DOWN_STATE) {
                    headerPrepareToRefresh(deltaY);
                } else if (isCanPullUp && mPullState == PULL_UP_STATE) {
                    footerPrepareToRefresh(deltaY);
                }
                mLastMotionY = y;
                break;
            case MotionEvent.ACTION_UP: // 离开屏幕，触摸停止的时候执行
            case MotionEvent.ACTION_CANCEL: // 取消手势，不会由用户产生，而是由程序产生的
                int topMargin = getHeaderTopMargin();
                if (isCanPullDown && mPullState == PULL_DOWN_STATE) {
                    if (topMargin >= 0) {
                        // 开始执行顶部刷新
                        headerRefreshing();
                    } else {
                        // 还没有执行刷新，重新隐藏
                        setHeaderTopMargin(-mHeaderViewHeight);
                    }
                } else if (isCanPullUp && mPullState == PULL_UP_STATE) {
                    if (Math.abs(topMargin) >= mHeaderViewHeight + mFooterViewHeight) {
                        // 开始执行底部刷新
                        footerRefreshing();
                    } else {
                        // 还没有执行刷新，重新隐藏上拉加载更多文字
                        mFooterTextView.setVisibility(View.GONE);
                        // 还没有执行刷新，重新隐藏
                        setHeaderTopMargin(-mHeaderViewHeight);
                    }
                }
                break;
        }
        return super.onTouchEvent(event);
    }

    /**
     * 是否应该到了父View,即PullToRefreshView滑动
     *
     * @param deltaY , deltaY > 0 是向下运动,< 0是向上运动
     * @return
     */
    private boolean isRefreshViewScroll(int deltaY) {
        if (mHeaderState == REFRESHING || mFooterState == REFRESHING) {
            return false;
        }
        if (deltaY >= -20 && deltaY <= 20) {
            return false;
        }

        if (mRecyclerView != null) {
            // 子view(ListView or GridView)滑动到最顶端
            if (deltaY > 0) {
                View child = mRecyclerView.getChildAt(0);
                if (child == null) {
                    // 如果mRecyclerView中没有数据,不拦截
                    return false;
                }
                if (isScrollTop() && child.getTop() == 0) {
                    mPullState = PULL_DOWN_STATE;
                    return true;
                }
                int top = child.getTop();
                int padding = mRecyclerView.getPaddingTop();
                if (isScrollTop() && Math.abs(top - padding) <= 8) { // 这里之前用3可以判断,但现在不行
                    mPullState = PULL_DOWN_STATE;
                    return true;
                }

            } else if (deltaY < 0) {
                View lastChild = mRecyclerView.getChildAt(mRecyclerView.getChildCount() - 1);
                if (lastChild == null) {
                    // 如果mRecyclerView中没有数据,不拦截
                    return false;
                }
                // 最后一个子view的Bottom小于父View的高度说明mRecyclerView的数据没有填满父view,
                // 等于父View的高度说明mRecyclerView已经滑动到最后
                if (lastChild.getBottom() <= getHeight() && isScrollBottom()) {
                    mPullState = PULL_UP_STATE;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 判断mRecyclerView是否滑动到顶部
     *
     * @return
     */
    boolean isScrollTop() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        if (linearLayoutManager.findFirstVisibleItemPosition() == 0) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断mRecyclerView是否滑动到底部
     *
     * @return
     */
    boolean isScrollBottom() {
        LinearLayoutManager linearLayoutManager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        if (linearLayoutManager.findLastVisibleItemPosition() == (mRecyclerView.getAdapter().getItemCount() - 1)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 顶部准备刷新,手指移动过程,还没有释放
     *
     * @param deltaY ,手指滑动的距离
     */
    private void headerPrepareToRefresh(int deltaY) {
        int newTopMargin = changingHeaderViewTopMargin(deltaY);
        // 当顶部view的topMargin>=0时，说明已经完全显示出来了,修改顶部view的提示状态
        if (newTopMargin >= 0 && mHeaderState != RELEASE_TO_REFRESH) {
            mHeaderTextView.setText(R.string.pull_to_refresh_release_label);
            mHeaderUpdateTextView.setVisibility(View.VISIBLE);
            mHeaderImageView.clearAnimation();
            mHeaderImageView.startAnimation(mFlipAnimation);
            mHeaderState = RELEASE_TO_REFRESH;
        } else if (newTopMargin < 0 && newTopMargin > -mHeaderViewHeight) { // 拖动时没有释放
            mHeaderImageView.clearAnimation();
            mHeaderImageView.startAnimation(mFlipAnimation);
            // mHeaderImageView.
            mHeaderTextView.setText(R.string.pull_to_refresh_pull_label);
            mHeaderState = PULL_TO_REFRESH;
        }
    }

    /**
     * 底部准备刷新,手指移动过程,还没有释放 移动底部view高度同样和移动顶部view
     * 高度是一样，都是通过修改顶部 view的topmargin的值来达到
     *
     * @param deltaY ,手指滑动的距离
     */
    private void footerPrepareToRefresh(int deltaY) {
        int newTopMargin = changingHeaderViewTopMargin(deltaY);
        // 如果顶部view topMargin 的绝对值大于或等于 顶部 + 底部 的高度
        // 说明底部view完全显示出来了，修改底部view的提示状态
        if (Math.abs(newTopMargin) >= (mHeaderViewHeight + mFooterViewHeight) && mFooterState != RELEASE_TO_REFRESH) {
            //mFooterTextView.setVisibility(View.VISIBLE);
            mFooterTextView.setText(R.string.pull_to_refresh_footer_release_label);
            mFooterState = RELEASE_TO_REFRESH;
        } else if (Math.abs(newTopMargin) < (mHeaderViewHeight + mFooterViewHeight)) {
            mFooterTextView.setVisibility(View.VISIBLE); // 上拉显示提示
            mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
            mFooterState = PULL_TO_REFRESH;
        }
    }

    /**
     * 修改顶部view top margin的值
     *
     * @param deltaY
     * @description
     */
    private int changingHeaderViewTopMargin(int deltaY) {
        LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
        float newTopMargin = params.topMargin + deltaY * 0.3f;
        // 这里对上拉做一下限制,因为当前上拉后然后不释放手指直接下拉,会把下拉刷新给触发了
        // 表示如果是在上拉后一段距离,然后直接下拉
        if (deltaY > 0 && mPullState == PULL_UP_STATE && Math.abs(params.topMargin) <= mHeaderViewHeight) {
            return params.topMargin;
        }
        // 同样地,对下拉做一下限制,避免出现跟上拉操作时一样的问题
        if (deltaY < 0 && mPullState == PULL_DOWN_STATE && Math.abs(params.topMargin) >= mHeaderViewHeight) {
            return params.topMargin;
        }
        params.topMargin = (int) newTopMargin;
        mHeaderView.setLayoutParams(params);
        invalidate();
        return params.topMargin;
    }

    /**
     * 顶部刷新
     */
    public void headerRefreshing() {
        mHeaderState = REFRESHING;
        setHeaderTopMargin(0);
        mHeaderImageView.setVisibility(View.GONE);
        mHeaderImageView.clearAnimation();
        mHeaderImageView.setImageDrawable(null);
        //mHeaderProgressBar.setVisibility(View.VISIBLE);
        headViewAnimation.setVisibility(View.VISIBLE);
        headAnimationDrawable.start();
        mHeaderTextView.setText(R.string.pull_to_refresh_refreshing_label);
        if (refreshListener != null) {
            refreshListener.onHeaderRefresh(this);
        }
    }

    /**
     * 底部刷新
     */
    private void footerRefreshing() {
        mFooterState = REFRESHING;
        int top = mHeaderViewHeight + mFooterViewHeight;
        setHeaderTopMargin(-top);
        mFooterTextView.setVisibility(View.GONE);
        //mFooterProgressBar.setVisibility(View.VISIBLE);
        loadingMore.setVisibility(View.VISIBLE);
        //footerViewAnimation.setVisibility(View.VISIBLE);
        footerAnimationDrawable.start();
        if (refreshListener != null) {
            refreshListener.onFooterRefresh(this);
        }
    }

    /**
     * 设置顶部view的topMargin的值
     *
     * @param topMargin ，为0时，说明顶部view刚好完全显示出来； 为-mHeaderViewHeight时，说明完全隐藏了
     * @description
     */
    private void setHeaderTopMargin(int topMargin) {
        LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
        params.topMargin = topMargin;
        mHeaderView.setLayoutParams(params);
        invalidate();
    }

    /**
     * 顶部view完成更新后恢复初始状态
     */
    public void onHeaderRefreshComplete() {
        setHeaderTopMargin(-mHeaderViewHeight);
        mHeaderImageView.setVisibility(View.VISIBLE);
        mHeaderImageView.setImageResource(R.mipmap.ic_pulltorefresh_arrow);
        mHeaderTextView.setText(R.string.pull_to_refresh_pull_label);
        mHeaderUpdateTextView.setText("最近刷新：" + getFormatDateString("MM-dd HH:mm"));
        //mHeaderProgressBar.setVisibility(View.GONE);
        headViewAnimation.setVisibility(View.GONE);
        headAnimationDrawable.stop();
        mHeaderState = PULL_TO_REFRESH;
    }

    /**
     * 底部view完成更新后恢复初始状态
     */
    public void onFooterRefreshComplete() {
        setHeaderTopMargin(-mHeaderViewHeight);
        mFooterTextView.setText(R.string.pull_to_refresh_footer_pull_label);
        mFooterTextView.setVisibility(View.GONE);
        //mFooterProgressBar.setVisibility(View.GONE);
        footerAnimationDrawable.stop();
        loadingMore.setVisibility(View.GONE);
        //footerViewAnimation.setVisibility(View.GONE);
        //footerAnimationDrawable.stop();
        mFooterState = PULL_TO_REFRESH;
        if (mRecyclerView != null) {
            mRecyclerView.scrollToPosition(mRecyclerView.getAdapter().getItemCount() - 1);
        }

    }

    /**
     * 获取当前顶部view的topMargin
     *
     * @description
     */
    private int getHeaderTopMargin() {
        LayoutParams params = (LayoutParams) mHeaderView.getLayoutParams();
        return params.topMargin;
    }


    /**
     * 设置刷新监听
     *
     * @description
     */
    public void setOnRefreshListener(OnRefreshListener refreshListener) {
        this.refreshListener = refreshListener;
    }

    /**
     * 顶部底部刷新接口
     */
    public interface OnRefreshListener {
        void onHeaderRefresh(PullBaseView view);

        void onFooterRefresh(PullBaseView view);
    }

    /**
     * 设置是否可以在刷新时滑动
     *
     * @param canScrollAtRereshing
     */
    public void setCanScrollAtRereshing(boolean canScrollAtRereshing) {
        isCanScrollAtRefreshing = canScrollAtRereshing;
    }

    /**
     * 设置是否可上拉
     *
     * @param canPullUp
     */
    public void setCanPullUp(boolean canPullUp) {
        isCanPullUp = canPullUp;
    }

    /**
     * 设置是否可下拉
     *
     * @param canPullDown
     */
    public void setCanPullDown(boolean canPullDown) {
        isCanPullDown = canPullDown;
    }

    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        }

        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight, MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }

    protected abstract T createRecyclerView(Context context, AttributeSet attrs);

}
