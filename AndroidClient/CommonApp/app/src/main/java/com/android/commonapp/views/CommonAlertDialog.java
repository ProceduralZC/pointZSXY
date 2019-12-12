package com.android.commonapp.views;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.commonapp.R;


/**
 * @date: 2017/12/5.
 * @author: CHEN
 * @describe: 适用于一般的提示框
 */

public class CommonAlertDialog extends Dialog {
    // dialog高度
    private int height;
    // dialog宽度
    private int width;
    // 点击外部是否可以取消
    private boolean cancelTouchOutside;
    // 弹窗布局View
    private View dialogView;
    // 返回键是否可以取消
    private boolean cancelable;

    private CommonAlertDialog(Builder builder) {
        super(builder.context);
        height = builder.height;
        width = builder.width;
        cancelTouchOutside = builder.cancelTouchOutside;
        dialogView = builder.mDialogView;
    }


    private CommonAlertDialog(Builder builder, int resStyle) {
        super(builder.context, resStyle);
        height = builder.height;
        width = builder.width;
        cancelable = builder.cancelable;
        cancelTouchOutside = builder.cancelTouchOutside;
        dialogView = builder.mDialogView;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(dialogView);
        setCanceledOnTouchOutside(cancelTouchOutside);
        setCancelable(cancelable);
        Window win = getWindow();
        WindowManager.LayoutParams lp = win.getAttributes();
        lp.gravity = Gravity.CENTER;
        lp.height = height;
        lp.width = width;
        win.setAttributes(lp);
    }

    public static final class Builder {
        private Context context;
        private int height, width;
        private boolean cancelTouchOutside = false;
        private View mDialogView;
        private int resStyle = -1;
        private boolean cancelable = false;

        public Builder(Context context) {
            this.context = context;
            mDialogView = LayoutInflater.from(context).inflate(R.layout.common_alert_layout, null);
            // 计算dialog宽高
            int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
            mDialogView.measure(measureSpec, measureSpec);
            height = mDialogView.getMeasuredHeight();
            width = mDialogView.getMeasuredWidth();
        }

        /**
         * @param dialogView 关联dialog布局文件的View
         * @return
         */
        public Builder setDialogLayout(View dialogView) {
            this.mDialogView = dialogView;
            return this;
        }

        /**
         * 高
         * @param val
         * @return
         */
        public Builder setHeightPx(int val) {
            height = val;
            return this;
        }

        /**
         * 宽
         * @param val
         * @return
         */
        public Builder setWidthPx(int val) {
            width = val;
            return this;
        }

//        public Builder setHeightDp(int val) {
//            height = ScreenUtils.dp2px(context, val);
//            return this;
//        }
//
//        public Builder setWidthDp(int val) {
//            width = ScreenUtils.dp2px(context, val);
//            return this;
//        }

        /**
         * 设置主题
         *
         * @param resStyle
         * @return
         */
        public Builder setTheme(int resStyle) {
            this.resStyle = resStyle;
            return this;
        }

        /**
         * 设置点击dialog外部是否取消dialog
         *
         * @param val
         * @return
         */
        public Builder cancelTouchOutside(boolean val) {
            cancelTouchOutside = val;
            return this;
        }

        /**
         * 设置点击返回键是否取消dialog
         *
         * @param cancelable
         * @return
         */
        public Builder setCancelable(boolean cancelable) {
            this.cancelable = cancelable;
            return this;
        }

        /**
         * 给dialog中的view添加点击事件
         *
         * @param viewResId 被点击view的id
         * @param listener
         * @return
         */
        public Builder addViewOnclick(int viewResId, View.OnClickListener listener) {
            mDialogView.findViewById(viewResId).setOnClickListener(listener);
            return this;
        }

        /**
         * 确定键监听
         *
         * @param confirm
         * @param listener
         * @return
         */
        public Builder addOneConfirmClickListener(String confirm, View.OnClickListener listener) {
            TextView tvConfirm = (TextView) mDialogView.findViewById(R.id.tv_alert_one_confirm);
            tvConfirm.setText(confirm);
            tvConfirm.setOnClickListener(listener);
            return this;
        }

        /**
         * 确定键监听
         *
         * @param confirm
         * @param listener
         * @return
         */
        public Builder addConfirmClickListener(String confirm, View.OnClickListener listener) {
            TextView tvConfirm = (TextView) mDialogView.findViewById(R.id.tv_alert_confirm);
            tvConfirm.setText(confirm);
            tvConfirm.setOnClickListener(listener);
            return this;
        }

        /**
         * 取消键监听
         *
         * @param cancel
         * @param listener
         * @return
         */
        public Builder addCancelClickListener(String cancel, View.OnClickListener listener) {
            TextView tvCancel = (TextView) mDialogView.findViewById(R.id.tv_alert_cancel);
            tvCancel.setText(cancel);
            tvCancel.setOnClickListener(listener);
            return this;
        }

        /**
         * 设置标题
         *
         * @param title
         * @return
         */
        public Builder setTitle(String title) {
            TextView tvTitle = (TextView) mDialogView.findViewById(R.id.tv_alert_title);
            tvTitle.setText(title);
            return this;
        }

        /**
         * 设置标题颜色
         *
         * @param color 颜色
         * @return
         */
        public Builder setTitleColor(int color) {
            TextView tvCancel = (TextView) mDialogView.findViewById(R.id.tv_alert_title);
            tvCancel.setTextColor(color);
            return this;
        }

        /**
         * 设置内容
         *
         * @param content
         * @return
         */
        public Builder setContent(String content) {
            TextView tvTitle = (TextView) mDialogView.findViewById(R.id.tv_alert_content);
            tvTitle.setText(content);
            return this;
        }

        /**
         * 设置取消键颜色
         *
         * @param color 颜色
         * @return
         */
        public Builder setCancelColor(int color) {
            TextView tvCancel = (TextView) mDialogView.findViewById(R.id.tv_alert_cancel);
            tvCancel.setTextColor(color);
            return this;
        }

        /**
         * 设置确定键颜色
         *
         * @param color 颜色
         * @return
         */
        public Builder setConfirmColor(int color) {
            TextView tvCancel = (TextView) mDialogView.findViewById(R.id.tv_alert_confirm);
            tvCancel.setTextColor(color);
            return this;
        }

        /**
         * 显示一个按钮的弹窗
         *
         * @return
         */
        public Builder showOneButton() {
            mDialogView.findViewById(R.id.tv_alert_cancel).setVisibility(View.GONE);
            mDialogView.findViewById(R.id.tv_alert_confirm).setVisibility(View.GONE);
            mDialogView.findViewById(R.id.view_dialog).setVisibility(View.GONE);
            mDialogView.findViewById(R.id.tv_alert_one_confirm).setVisibility(View.VISIBLE);
            return this;
        }

        public CommonAlertDialog build() {
            if (resStyle != -1) {
                return new CommonAlertDialog(this, resStyle);
            } else {
                return new CommonAlertDialog(this);
            }
        }

    }

}
