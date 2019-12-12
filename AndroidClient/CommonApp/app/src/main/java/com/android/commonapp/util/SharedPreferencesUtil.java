package com.android.commonapp.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * @date: 2017/11/17.
 * @author: CHEN
 * @describe:
 */

public class SharedPreferencesUtil {
    private String TAG = getClass().getSimpleName();
    // 存放信息的文件名
    protected SharedPreferences mSp;

    public SharedPreferencesUtil(Context context, String name) {
        mSp = context.getSharedPreferences(name, Context.MODE_PRIVATE);
    }

    interface Executable {
        void execute(Editor editor);
    }

    private boolean executeWithEditor(Executable ex) {
        Editor editor = mSp.edit();
        ex.execute(editor);
        return editor.commit();
    }

    public boolean put(final String key, final boolean value) {
        return executeWithEditor(new Executable() {
            @Override
            public void execute(Editor editor) {
                editor.putBoolean(key, value);
            }
        });
    }

    public boolean put(final String key, final int value) {
        return executeWithEditor(new Executable() {
            @Override
            public void execute(Editor editor) {
                editor.putInt(key, value);
            }
        });
    }

    public boolean put(final String key, final long value) {
        return executeWithEditor(new Executable() {
            @Override
            public void execute(Editor editor) {
                editor.putLong(key, value);
            }
        });
    }

    public boolean put(final String key, final float value) {
        return executeWithEditor(new Executable() {
            @Override
            public void execute(Editor editor) {
                editor.putFloat(key, value);
            }
        });
    }

    public boolean put(final String key, final String value) {
        return executeWithEditor(new Executable() {
            @Override
            public void execute(Editor editor) {
                editor.putString(key, value);
            }
        });
    }

    public void remove(final String key) {
        executeWithEditor(new Executable() {
            @Override
            public void execute(Editor editor) {
                editor.remove(key);
            }
        });
    }

    public void clear() {
        executeWithEditor(new Executable() {
            @Override
            public void execute(Editor editor) {
                editor.clear();
            }
        });
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        return mSp.getBoolean(key, defaultValue);
    }

    public int getInt(String key, int defaultValue) {
        return mSp.getInt(key, defaultValue);
    }

    public long getLong(String key, long defaultValue) {
        return mSp.getLong(key, defaultValue);
    }

    public float getFloat(String key, float defaultValue) {
        return mSp.getFloat(key, defaultValue);
    }

    public String getString(String key, String defaultValue) {
        return mSp.getString(key, defaultValue);
    }

    public SharedPreferences getSharedPreferences() {
        return mSp;
    }

    public Editor getEditor() {
        return mSp.edit();
    }
}
