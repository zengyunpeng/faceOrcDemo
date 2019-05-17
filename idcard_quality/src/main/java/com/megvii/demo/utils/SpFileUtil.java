package com.megvii.demo.utils;

import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;

import java.util.Map;


public class SpFileUtil {

    /**
     * 保存在手机里的文件名
     */
    public static final String FILE_NAME = "meg_idcard_quality";

    public static final String KEY_UUID = "key_uuid";

    /**
     * @WARNING Be careful, this maybe overwrite a exist value.<br>
     */
    public static void removeKey(Context context, String filename, String key) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.remove(key);
            editor.apply();
        }

    }

    /**
     * @WARNNING Be careful, this maybe overwrite a exist value.<br>
     */
    public static void saveString(Context context, String filename, String key, String value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putString(key, value);
            editor.apply();
        }

    }

    /**
     * get String from shared Preferences.<br>
     *
     * @PROMPT if the img_demo_target value is not exist, it will return the defaultValue.
     */
    public static String getString(Context context, String filename, String key, String defaultValue) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            return preferences.getString(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * @WARNNING Be careful, this maybe overwrite a exist value.<br>
     */
    public static void savelong(Context context, String filename, String key, long value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putLong(key, value);
            editor.apply();
        }

    }

    /**
     * get long value from shared Preferences.<br>
     *
     * @PROMPT if the img_demo_target value is not exist, it will return the defaultValue.
     */
    public static long getlong(Context context, String filename, String key, long defaultValue) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            return preferences.getLong(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * @WARNNING Be careful, this maybe overwrite a exist value.<br>
     */
    public static void saveInt(Context context, String filename, String key, int value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putInt(key, value);
            editor.apply();
        }

    }

    /**
     * get int value from shared Preferences.<br>
     *
     * @PROMPT if the img_demo_target value is not exist, it will return the defaultValue.
     */
    public static int getInt(Context context, String filename, String key, int defaultValue) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            return preferences.getInt(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * @WARNNING Be careful, this maybe overwrite a exist value.<br>
     */
    public static void saveFloat(Context context, String filename, String key, float value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putFloat(key, value);
            editor.apply();
        }

    }

    /**
     * get float value from shared Preferences.<br>
     *
     * @PROMPT if the img_demo_target value is not exist, it will return the defaultValue.
     */
    public static float getFloat(Context context, String filename, String key, float defaultValue) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            return preferences.getFloat(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * @WARNNING Be careful, this maybe overwrite a exist value.<br>
     */
    public static void saveBoolean(Context context, String filename, String key, boolean value) {
        if (context != null) {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean(key, value);
            editor.apply();
        }

    }

    /**
     * get boolean value from shared Preferences.<br>
     *
     * @PROMPT if the img_demo_target value is not exist, it will return the defaultValue.
     */
    public static boolean getBoolean(Context context, String filename, String key, boolean defaultValue) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            return preferences.getBoolean(key, defaultValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultValue;
    }

    /**
     * get key-value map of particular filename from shared Preferences.<br>
     */
    public static Map<String, ?> getFile(Context context, String filename) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            return preferences.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void clearFile(Context context, String filename) {
        try {
            SharedPreferences preferences = context.getSharedPreferences(filename, Context.MODE_PRIVATE);
            preferences.edit().clear().apply();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 保存ContentValues 的数据
     *
     * @param mContext
     * @param contentValues
     */
    public static void saveData(Context mContext, ContentValues contentValues) {
        try {
            int size = (contentValues != null && contentValues.size() > 0)
                    ? contentValues.size() : 0;
            if (size > 0) {
                for (String colName : contentValues.keySet()) {
                    SpFileUtil.saveString(mContext, SpFileUtil.FILE_NAME, colName, contentValues.get(colName).toString());
                }
            }
        } catch (Throwable e) {
        }
    }

}
