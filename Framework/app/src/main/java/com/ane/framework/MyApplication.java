package com.ane.framework;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;

import com.ane.framework.Base.util.TempUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.orhanobut.logger.Logger;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import java.util.LinkedList;

import cat.ereza.customactivityoncrash.CustomActivityOnCrash;


public class MyApplication extends Application {
    //程序log日志的标签,可以修改
    private final static String TAG="TAG";
    private LinkedList<Activity> mSaveActivity;
    private RefWatcher refWatcher;

    public MyApplication(){
        super();
    }
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化log工具
        Logger.init(TAG);
//      隐藏log日志输出
//      Logger.init(TAG).setLogLevel(LogLevel.NONE);
        //初始化内存检查工具
        refWatcher=LeakCanary.install(this);
        //初始化 捕捉错误或者异常出现自定义界面的工具
        CustomActivityOnCrash.install(this, null);
        //初始化fresco
        Fresco.initialize(this);
        mSaveActivity=new LinkedList<Activity>();
        //初始化util,暂时只有一个util需要实例化，因为里面需要有context的引用。如果又多个util需要实例化
        //那么，就要把他们实例化的方法集中到一个类里面
        TempUtil.initialize(this);
    }
    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    /**
     *退出多个activity
     */
    public boolean removeAtys(LinkedList<Activity> removeAtys){
        try{
            for(Activity activity : removeAtys){
                if(activity!=null){
                    activity.finish();
                }
            }
            mSaveActivity.removeAll(removeAtys);
            return  true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 保存Activity
     * @param activity
     */
    public void addActivity(Activity activity) {
        mSaveActivity.add(activity);
    }
    /**
     * 退出应用
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
     * @return
     */
    public boolean removeActivity(Activity activity){
        boolean result= mSaveActivity.remove(activity);
        return result;
    }


}
