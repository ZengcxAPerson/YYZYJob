package com.ane.framework.Base.util;

/**
 * Created by Administrator on 2015/12/16.
 */

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;

import java.util.List;

/**
 * 服务工具类
 *
 * @author liufengkai
 */
public class ServiceUtils {
    /**
     * 判断service是否正在运行
     *
     * @param context
     * @param className 服务类名
     * @return
     */
    public static boolean isServiceRunning(Context context, String className) {
        boolean isRunning = false;
        ActivityManager activityManager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningServiceInfo> serviceList = activityManager.getRunningServices(Integer.MAX_VALUE);

        if (serviceList.size() <= 0) {
            return false;
        }

        for (int i = 0; i < serviceList.size(); i++) {
            if (serviceList.get(i).service.getClassName().equals(className)) {
                isRunning = true;
                break;
            }
        }

        return isRunning;
    }

    /**
     * 启动服务，不会重复启动
     *
     * @param context
     * @param clazz
     * @param intent
     */
    public static void startService(Context context, Class clazz, Intent intent) {
        if (!isServiceRunning(context, clazz.getName())) {
            context.startService(intent);
        }
    }

    /**
     * 绑定服务,不会重复绑定
     * @param context
     * @param clazz
     * @param intent
     * @param coon
     * @param flag
     */
    public static void bindService(Context context, Class clazz, Intent intent, ServiceConnection coon, int flag) {
        if (!isServiceRunning(context, clazz.getName())) {
            context.bindService(intent, coon, flag);
        }
    }

    /**
     * 解绑服务，会判断服务是否启动着
     * @param context
     * @param clazz
     * @param coon
     */
    public static void unbindService(Context context, Class clazz, ServiceConnection coon) {
        if (!isServiceRunning(context, clazz.getName())) {
            context.unbindService(coon);
        }
    }


    /**
     * 关闭服务，会判断服务是否启动着
     *
     * @param context
     * @param clazz
     */
    public static void stopService(Context context, Class clazz) {
        if (isServiceRunning(context, clazz.getName())) {
            context.stopService(new Intent(context, clazz));
        }
    }

}