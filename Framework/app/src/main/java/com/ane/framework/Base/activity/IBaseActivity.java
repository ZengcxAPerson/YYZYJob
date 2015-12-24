package com.ane.framework.Base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ane.framework.MyApplication;


/**
 * <p>抽象一些公共操作</p>
 * Created by zcx on 2015/11/14.
 */
public abstract class IBaseActivity extends Activity {
    protected MyApplication application;
    protected Activity activity;
    private static final String TAG = "Base-Activity";
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
            Log.e(TAG, "IBaseActivity onDestroy removeActivity is error！" +
                    "please you see leaks the Memory overflow!");
        }
        //取消异步线程
//        http.stopAsyn(mAsyncRequestService);
    }

    /**
     * post异步请求
     * @param networkUrl 网络路径
     * @param params     参数
     * @param callBack   回调callback
     */
//    protected void post(String networkUrl,String params,AsyncRequestService.AsyncRequestCallBack callBack){
//        mAsyncRequestService= http.post(networkUrl,params,callBack);
//    }
    /**
     * 实现base抽象类对子类初始化的要求
     */
    public abstract void onIBaseCrate();
}
