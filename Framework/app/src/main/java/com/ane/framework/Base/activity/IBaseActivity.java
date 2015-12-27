package com.ane.framework.Base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ane.framework.MyApplication;


/**
 * <p>抽象一些公共操作</p>
 * <p>建立公共的代码规范</p>
 * Created by zcx on 2015/11/14.
 */
public abstract class IBaseActivity extends Activity {
    protected MyApplication application;
//    protected Activity activity;
    private static final String TAG = "Base-Activity";
//    protected AsyncRequestService mAsyncRequestService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        application.addActivity(this);
        findViews();
        setViewsContent();
        setViewsListener();
        disposeBusiness();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        boolean result = application.removeActivity(this);
        if (!result) {
            Log.e(TAG, "IBaseActivity onDestroy removeActivity is error！" +
                    "please you see leaks the Memory overflow!");
        }
    }

    /**
     * 描述：处理业务逻辑
     */
    protected abstract void disposeBusiness();
    /**
     * 描述：把所有View找出来
     */
    protected abstract void findViews();
    /**
     * 描述：设置所有View的内容
     */
    protected abstract void setViewsContent();
    /**
     * 描述：设置View的监控器
     */
    protected abstract void setViewsListener();

}
