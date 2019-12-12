package com.android.commonapp.util;

import android.text.TextUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 验证
 */
public class CheckTextFormatUtils {

    // 验证手机号格式
    public static boolean checkPhoneNumberFormat(String text) {
        if (TextUtils.isEmpty(text))
            return false;
        String regx = "((\\d{11})|^((\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})|(\\d{4}|\\d{3})-(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1})|(\\d{7,8})-(\\d{4}|\\d{3}|\\d{2}|\\d{1}))$)";
        Pattern pattern = Pattern.compile(regx);
        Matcher matcher = pattern.matcher(text);
        return matcher.matches();
    }

    // 验证密码格式
    public static boolean checkPasswordFormat(String text) {
        if (TextUtils.isEmpty(text))
            return false;
        text = text.trim();
        if (6 <= text.length() && text.length() <= 18) {
            return true;
        }
        return false;
    }

    // 验证 验证码格式
    public static boolean checkVerificationCodeFormat(String text) {
        if (TextUtils.isEmpty(text))
            return false;
        text = text.trim();
        return text.length() == 6 ? true : false;
    }

}
