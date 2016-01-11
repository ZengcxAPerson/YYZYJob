package com.ane.framework.Base.activity;

import android.os.Bundle;
import android.util.Log;

import com.ane.framework.Base.interFace.UIWritCode;
import com.ane.framework.MyApplication;

import me.imid.swipebacklayout.lib.app.SwipeBackActivity;

/**
 * <p>抽象一些公共操作,并集合了向右滑动返回Activity的类库的IBaseActivity</p>
 * <p>建立公共的代码规范</p>
 * Created by Administrator on 2015/11/27.
 */
public abstract class IBaseSwipeBackAty extends SwipeBackActivity implements UIWritCode {
    protected MyApplication application;
    protected static final String TAG = "Base-SwipeBackAty";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        application.addActivity(this);
    }
    @Override
    public void inItActivityWritCode() {
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
}
