package com.ane.framework.Base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.ane.framework.MyApplication;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * <p>抽象一些公共操作,并集合了向右滑动返回Activity的类库的IBaseActivity</p>
 * Created by Administrator on 2015/11/27.
 */
public abstract class IBaseSwipeBackAty extends SwipeBackActivity {
    protected MyApplication application;
    protected Activity activity;
    protected static final String TAG = "Base-SwipeBackAty";

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
            Log.e(TAG, "IBaseSwipeBackAty onDestroy removeActivity is error！" +
                    "please you see leaks the Memory overflow!");
        }
    }


    /**
     * 实现base抽象类对子类初始化的要求
     */
    public abstract void onIBaseCrate();

}
