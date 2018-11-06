package com.duanlian.daimeng.utils;

import android.util.Log;

/**
 * Created by 段炼 on 2017/9/13.
 * Description : 日志工具类
 */

public class LogU {
    //日志开关
    private static boolean openLog = false;
    private static String tag = "duanyimeng";

    public static void init(boolean isOpenLog) {
        LogU.openLog = isOpenLog;
    }

    public static void d(String msg) {
        if (openLog) {
            Log.d(tag, msg);
        }
    }

    public static void d(String tag, String msg) {
        if (openLog) {
            Log.d(tag, msg);
        }
    }

    public static void e(String msg) {
        if (openLog) {
            Log.e(tag, msg);
        }
    }

    public static void e(String tag, String msg) {
        if (openLog) {
            Log.e(tag, msg);
        }
    }

}
