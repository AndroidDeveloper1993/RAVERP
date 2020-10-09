package com.rav.raverp.data.local.prefs;

import android.content.Context;
import android.content.SharedPreferences;


public class PrefsHelper {
    public static final String MyPREFERENCES = "MyPrefs" ;

    public static void putInt(Context context, String key, int value) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putInt(key, value);
        editor.apply();
    }

    public static void putString(Context context, String key, String value) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public static void putBoolean(Context context, String key, Boolean value) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    public static int getInt(Context context, String key) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getInt(key, 0);
    }

    public static void putDouble(Context context, String key, Double value) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.putLong(key, Double.doubleToRawLongBits(value));
        editor.apply();
    }

    public static String getString(Context context, String key) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getString(key, null);
    }

    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return sharedpreferences.getBoolean(key, false);
    }

    public static Double getDouble(Context context, String key) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        return Double.longBitsToDouble(sharedpreferences.getLong(key,
                Double.doubleToRawLongBits(0.0)));
    }

    public static void remove(Context context, String key) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.remove(key);
        editor.apply();
    }

    public static void removeAll(Context context) {
        SharedPreferences sharedpreferences
                = context.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedpreferences.edit();
        editor.clear();
        editor.apply();
    }
}
