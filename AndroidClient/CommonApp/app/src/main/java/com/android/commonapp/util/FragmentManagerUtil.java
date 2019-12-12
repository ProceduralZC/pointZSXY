package com.android.commonapp.util;

import android.app.Activity;
import android.content.Context;
import android.os.IBinder;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import com.android.commonapp.R;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public class FragmentManagerUtil {

    private static final String tag = FragmentManagerUtil.class.getSimpleName();

    /**
     * @param fm        FragmentManager
     * @param resId     Fragment要显示的view空间id
     * @param fragment1 当前的Fragment，如果没有那么传入null
     * @param fragment2 要跳转的下一个Fragment
     */
    public static void add(FragmentManager fm, int resId, Fragment fragment1, Fragment fragment2) {
        add(fm, resId, fragment1, fragment2, true);
    }

    public static void add(FragmentManager fm, int resId, Fragment fragment1, Fragment fragment2, boolean hidenSoftInput) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out);
        transaction.add(resId, fragment2, fragment2.getClass().getSimpleName());
        if (null != fragment1) {
            transaction.hide(fragment1);
            transaction.addToBackStack(null);

            if (hidenSoftInput) {
                hidenSoftInput(fragment1.getActivity());
            }
        }

        transaction.commit();

    }

    public static void hidenSoftInput(Activity activity) {
        IBinder iBinder = getWindowTocken(activity);
        if (null != iBinder) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(iBinder, 0);
            //Print.i(tag, "hideSoftInputFromWindow.");
        }
    }

    public static void showSoftInput(Activity activity) {
        View view = activity.getCurrentFocus();
        if (null != view) {
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.showSoftInput(view, 0);
            //Print.i(tag, "showSoftInput.");
        }
    }

    private static IBinder getWindowTocken(Activity activity) {
        if (null == activity)
            return null;
        View view = activity.getCurrentFocus();
        if (null == view)
            return null;
        return view.getWindowToken();
    }

    public static void replace(FragmentManager fm, int resId, Fragment fragment) {
        replace(fm, resId, fragment, false);
    }

    public static void replace(FragmentManager fm, int resId, Fragment fragment, boolean isBack) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out);
        transaction.replace(resId, fragment, fragment.getClass().getSimpleName());
        if (isBack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void addNoAnimation(FragmentManager fm, int resId, Fragment fragment1, Fragment fragment2, boolean hidenSoftInput) {
        FragmentTransaction transaction = fm.beginTransaction();
        if (null != fragment1) {
            transaction.hide(fragment1);
            transaction.addToBackStack(null);
            if (hidenSoftInput) {
                hidenSoftInput(fragment1.getActivity());
            }
        }
        transaction.add(resId, fragment2, fragment2.getClass().getSimpleName());
        transaction.commit();

    }

    public static void addNoAnimation(FragmentManager fm, int resId, Fragment fragment2) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(resId, fragment2, fragment2.getClass().getSimpleName());
        transaction.commit();

    }

    public static void removeAdd(FragmentManager fm, int resId, Fragment fragment1, Fragment fragment2) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.setCustomAnimations(R.anim.push_left_in, R.anim.push_left_out, R.anim.push_right_in, R.anim.push_right_out);
        transaction.add(resId, fragment2, fragment2.getClass().getSimpleName());
        if (null != fragment1) {
            transaction.remove(fragment1);
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    public static void replaceNoAnimation(FragmentManager fm, int resId, Fragment fragment) {
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(resId, fragment, fragment.getClass().getSimpleName());
        transaction.commit();
    }
}
