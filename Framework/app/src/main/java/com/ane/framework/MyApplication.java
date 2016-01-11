package com.ane.framework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.ane.framework.Base.util.TempUtil;
import com.ane.framework.constants.AppConfig;
import com.bugtags.library.Bugtags;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.LogLevel;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.LinkedList;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;


public class MyApplication extends Application {
    //保存Activity的队列
    private LinkedList<Activity> mSaveActivity;
    private RefWatcher refWatcher;
    @Override
    public void onCreate() {
        super.onCreate();
        mSaveActivity = new LinkedList<Activity>();
        if(AppConfig.APP_VERSION_TYPE.equals("debug"))
            debugStart();
        else
            releaseStart();

        //初始化util,暂时只有一个util需要实例化，因为里面需要有context的引用。如果又多个util需要实例化
        //那么，就要把他们实例化的方法集中到一个类里面
        TempUtil.initialize(this);

    }
    public MyApplication() {
        super();
    }
    /**
     * debug版本配置
     */
    public void debugStart() {
        //初始化log工具
        Logger.init(AppConfig.TAG);
        //初始化bugTags工具
        Bugtags.start(AppConfig.BUG_TAGS_KEY, this, Bugtags.BTGInvocationEventBubble);
        commonConfigure();
    }

    /**
     * release版本配置
     */
    public void releaseStart() {
//      隐藏log日志输出
        Logger.init(AppConfig.TAG).setLogLevel(LogLevel.NONE);
        //初始化bugTags工具
        Bugtags.start(AppConfig.BUG_TAGS_KEY, this, Bugtags.BTGInvocationEventNone);
        commonConfigure();
    }

    /**
     * 公共配置
     */
    public void commonConfigure(){
        //初始化内存检查工具
        refWatcher = LeakCanary.install(this);
        //初始化 捕捉错误或者异常出现自定义界面的工具
        CustomActivityOnCrash.install(this, null);
        //初始化fresco
        Fresco.initialize(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
//        屏蔽分包代码
//        MultiDex.install(this);
    }

    /**
     * 退出多个activity
     */
    public boolean removeAtys(LinkedList<Activity> removeAtys) {
        try {
            for (Activity activity : removeAtys) {
                if (activity != null) {
                    activity.finish();
                }
            }
            mSaveActivity.removeAll(removeAtys);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存Activity
     *
     * @param activity
     */
    public void addActivity(Activity activity) {
        mSaveActivity.add(activity);
    }

    /**
     * 退出应用
     *
     * @return
     */
    public boolean exit() {
        try {
            for (Activity activity : mSaveActivity) {
                if (activity != null)
                    activity.finish();
            }
            mSaveActivity.clear();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 销毁引用，防止内存泄露
     *
     * @return
     */
    public boolean removeActivity(Activity activity) {
        boolean result = mSaveActivity.remove(activity);
        return result;
    }


}
