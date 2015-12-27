package com.ane.framework.Base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ane.framework.MyApplication;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * <p>抽象一些公共操作,并集合了向右滑动返回Activity的类库的IBaseActivity</p>
 * <p>建立公共的代码规范</p>
 * Created by Administrator on 2015/11/27.
 */
public abstract class IBaseSwipeBackAty extends SwipeBackActivity {
    protected MyApplication application;
    protected static final String TAG = "Base-SwipeBackAty";

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
            Log.e(TAG, "IBaseSwipeBackAty onDestroy removeActivity is error！" +
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
