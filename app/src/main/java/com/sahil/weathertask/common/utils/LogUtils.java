package com.sahil.weathertask.common.utils;

import android.util.Log;

import com.sahil.weathertask.BuildConfig;


public class LogUtils {

    private static boolean SHOW_LOG = BuildConfig.DEBUG;

    private LogUtils() {
    }

    public static void d(String tag, String msg) {
        if (SHOW_LOG) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg, Throwable tr) {
        if (SHOW_LOG) {
            Log.d(tag, msg, tr);
        }
    }

    public static void e(String tag, String msg) {
        if (SHOW_LOG) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg, Throwable tr) {
        if (SHOW_LOG) {
            Log.e(tag, msg, tr);
        }
    }

    public static void i(String tag, String msg) {
        if (SHOW_LOG) {
            Log.i(tag, msg);
        }
    }

    public static void i(String tag, String msg, Throwable tr) {
        if (SHOW_LOG) {
            Log.i(tag, msg, tr);
        }
    }

    public static void v(String tag, String msg) {
        if (SHOW_LOG) {
            Log.v(tag, msg);
        }
    }

    public static void v(String tag, String msg, Throwable tr) {
        if (SHOW_LOG) {
            Log.v(tag, msg, tr);
        }
    }

    public static void w(String tag, Throwable tr) {
        if (SHOW_LOG) {
            Log.w(tag, tr);
        }
    }

    public static void w(String tag, String msg, Throwable tr) {
        if (SHOW_LOG) {
            Log.w(tag, msg, tr);
        }
    }

    public static void w(String tag, String msg) {
        if (SHOW_LOG) {
            Log.w(tag, msg);
        }
    }
}
