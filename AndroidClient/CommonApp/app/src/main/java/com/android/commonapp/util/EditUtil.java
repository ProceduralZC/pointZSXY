package com.android.commonapp.util;

import android.text.Editable;
import android.widget.EditText;
import android.widget.TextView;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public class EditUtil {

    public static String getContent(EditText editText) {
        if (null == editText) {
            return null;
        }
        Editable editable = editText.getText();
        return null == editable ? null : editable.toString().trim();
    }

    public static String getContent(TextView textView) {
        if (null == textView) {
            return null;
        }
        CharSequence charSequence = textView.getText();
        return null == charSequence ? null : charSequence.toString();
    }

}
