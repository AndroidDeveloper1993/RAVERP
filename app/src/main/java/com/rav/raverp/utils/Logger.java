package com.rav.raverp.utils;

import android.util.Log;

import androidx.databinding.library.BuildConfig;


public class Logger {
    public static void d(String tag, String data) {
        if(BuildConfig.DEBUG) {
            Log.d(tag, data);
        }
    }

    public static void i(String tag, String data) {
        if(BuildConfig.DEBUG) {
            Log.i(tag, data);
        }
    }

    public static void e(String tag, String data) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, data);
        }
    }

    public static void e(String tag, String data, Throwable t) {
        if(BuildConfig.DEBUG) {
            Log.e(tag, data, t);
        }
    }

    public static void w(String tag, String data) {
        if(BuildConfig.DEBUG) {
            Log.w(tag, data);
        }
    }

    public static void wtf(String tag, String data) {
        if(BuildConfig.DEBUG) {
            Log.wtf(tag, data);
        }
    }
}
