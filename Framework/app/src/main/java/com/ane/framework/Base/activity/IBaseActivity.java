package com.ane.framework.Base.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;

import com.ane.framework.Base.interFace.UIWritCode;
import com.ane.framework.MyApplication;


/**
 * <p>抽象一些公共操作</p>
 * <p>建立公共的代码规范</p>
 * Created by zcx on 2015/11/14.
 */
public abstract class IBaseActivity extends Activity implements UIWritCode {
    protected MyApplication application;
    private static final String TAG = "Base-Activity";
    protected LayoutInflater mLayoutInflater;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //base的公共变量的实现
        application = (MyApplication) getApplication();
        application.addActivity(this);
        mLayoutInflater = LayoutInflater.from(this);

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
            Log.e(TAG, "IBaseActivity onDestroy removeActivity is error！" +
                    "please you see leaks the Memory overflow!");
        }
    }
}
