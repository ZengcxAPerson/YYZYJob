package com.ane.framework.Base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ane.framework.MyApplication;

/**
 * Created by Administrator on 2015/12/3.
 */
public abstract  class IBaseAppCompatAty extends AppCompatActivity{

    protected MyApplication application;
    protected Activity activity;
    private static final String TAG = "Base-CompatActivity";
//    protected AsyncRequestService mAsyncRequestService;
    //    protected Http http=new Http();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        onIBaseCrate();
        application = (MyApplication) getApplication();
        application.addActivity(activity);
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean result = application.removeActivity(this);
        if (!result) {
            Log.e(TAG, "IBaseAppCompatAty onDestroy removeActivity is error！" +
                    "please you see leaks the Memory overflow!");
        }
    }
    /**
     * 实现base抽象类对子类初始化的要求
     */
    public abstract void onIBaseCrate();
}
