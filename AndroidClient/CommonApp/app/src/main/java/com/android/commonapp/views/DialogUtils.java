package com.android.commonapp.views;

import android.app.Activity;
import android.app.ProgressDialog;
import android.support.annotation.ColorInt;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;

import com.android.commonapp.R;


/**
 * @date: 2017/12/5.
 * @author: CHEN
 * @describe:
 */

public class DialogUtils {

    private ProgressDialog mProgressDialog;
    private CommonProgressDialog commonProgressDialog;
    private CommonAlertDialog commonAlertDialog;
    private Activity activity;

    public DialogUtils(Activity activity) {
        this.activity = activity;
    }

    /**
     * 一个按钮
     *
     * @param content         内容
     * @param confirm         按钮文字
     * @param confirmListener 按钮监听
     */
    public void showOneButtonDialog(String content, String confirm, View.OnClickListener confirmListener) {
        isFinish();
        if (commonAlertDialog == null) {
            commonAlertDialog = new CommonAlertDialog.Builder(activity)
                    .setTheme(R.style.alertDialog)
                    .setContent(content)
                    .addOneConfirmClickListener(confirm, confirmListener)
                    .showOneButton()
                    .setCancelable(false)
                    .build();
            commonAlertDialog.show();
        }
    }

    /**
     * 两个按钮
     *
     * @param content         内容
     * @param confirmListener 确定键监听
     * @param cancelListener  取消键监听
     */
    public void showTwoButtonDialog(String content,
                                    View.OnClickListener confirmListener,
                                    View.OnClickListener cancelListener) {
        isFinish();
        if (commonAlertDialog == null) {
            commonAlertDialog = new CommonAlertDialog.Builder(activity)
                    .setTheme(R.style.alertDialog)
                    .setContent(content)
                    .addConfirmClickListener("确定", confirmListener)
                    .addCancelClickListener("取消", cancelListener)
                    .build();
            commonAlertDialog.show();
        }
    }

    /**
     * 两个按钮改变字颜色
     *
     * @param content         内容
     * @param confirm         确定键文字
     * @param cancel          取消键文字
     * @param confirmColor    确定键颜色
     * @param cancelColor     取消键颜色
     * @param confirmListener 确定键监听
     * @param cancelListener  取消键监听
     */
    public void showTwoButtonDialog(String content, String confirm, String cancel,
                                    @ColorInt int confirmColor, @ColorInt int cancelColor,
                                    View.OnClickListener confirmListener,
                                    View.OnClickListener cancelListener) {
        isFinish();
        if (commonAlertDialog == null) {
            commonAlertDialog = new CommonAlertDialog.Builder(activity)
                    .setTheme(R.style.alertDialog)
                    .setContent(content)
                    .setConfirmColor(confirmColor)
                    .setCancelColor(cancelColor)
                    .addConfirmClickListener(confirm, confirmListener)
                    .addCancelClickListener(cancel, cancelListener)
                    .build();
            commonAlertDialog.show();
        }
    }

    /**
     * 自定义布局
     *
     * @param dialogLayoutRes    dialog布局资源文件
     * @param cancelTouchOutside 点击外部是否可以取消
     * @return 自定义的dialog对应的View
     */
    public View createDialog(@LayoutRes Integer dialogLayoutRes, boolean cancelTouchOutside) {
        if (activity == null || activity.isFinishing()) {
            return null;
        }
        View dialogView = LayoutInflater.from(activity).inflate(dialogLayoutRes, null);
        //  计算dialog宽高
        int measureSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        dialogView.measure(measureSpec, measureSpec);
        int height = dialogView.getMeasuredHeight();
        int width = dialogView.getMeasuredWidth();
        if (commonAlertDialog == null) {
            commonAlertDialog = new CommonAlertDialog.Builder(activity)
                    .setTheme(R.style.alertDialog)
                    .setHeightPx(height)
                    .setWidthPx(width)
                    .cancelTouchOutside(cancelTouchOutside)
                    .setDialogLayout(dialogView)
                    .build();
            commonAlertDialog.show();
        }
        return dialogView;
    }

    /**
     * 取消dialog
     */
    public void dismissDialog() {
        if (commonAlertDialog != null) {
            if (commonAlertDialog.isShowing()) {
                commonAlertDialog.dismiss();
                commonAlertDialog = null;
            } else {
                commonAlertDialog = null;
            }
        }
    }

    /**
     * 显示CommonProgressDialog
     */
    public void showProgress(String msg,boolean ifcancel,boolean showloading) {
        isFinish();
        if (commonProgressDialog == null) {
            commonProgressDialog = new CommonProgressDialog.Builder(activity)
                    .setTheme(R.style.commonProgressDialog)
                    .setCancelable(false)
                    .cancelTouchOutside(ifcancel)
                    .setMessage(msg)
                    .build();
            if(showloading) {
                commonProgressDialog.show();
            }
        }
    }

    /**
     * 取消CommonProgressDialog
     */
    public void dismissProgress() {
        if (commonProgressDialog != null) {
            if (commonProgressDialog.isShowing()) {
                commonProgressDialog.dismiss();
                commonProgressDialog = null;
            } else {
                commonProgressDialog = null;
            }
        }
    }

    public void isFinish() {
        if (commonAlertDialog != null){
            commonAlertDialog = null;
        }
        if (activity == null || activity.isFinishing()) {
            return;
        }
    }

}
