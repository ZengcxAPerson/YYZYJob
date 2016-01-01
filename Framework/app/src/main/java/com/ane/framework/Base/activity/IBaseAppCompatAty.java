package com.ane.framework.Base.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.ane.framework.Base.interFace.ActivityToFragment;
import com.ane.framework.MyApplication;

/**
 * <p>抽象一些公共操作</p>
 * <p>如果需要使用谷歌的XXX设计规范的一些方法，请继承这个base类</p>
 * <p>建立公共的代码规范</p>
 * Created by Administrator on 2015/12/3.
 */
public abstract class IBaseAppCompatAty extends AppCompatActivity implements ActivityToFragment {

    protected MyApplication application;
    private static final String TAG = "Base-CompatActivity";
    protected FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        application = (MyApplication) getApplication();
        application.addActivity(this);
        mFragmentManager= getFragmentManager();

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
            Log.e(TAG, "IBaseAppCompatAty onDestroy removeActivity is error！" +
                    "please you see leaks the Memory overflow!");
        }
    }
    @Override
    public void initBindFragment(int fragmentViewId,Fragment initFragment) {
        mFragmentManager.beginTransaction().add(fragmentViewId, initFragment).commit();
    }

    @Override
    public void setBundle(Bundle bundle, Fragment fragment) {
        if(fragment!=null)
            fragment.setArguments(bundle);
        else
            throw new IllegalArgumentException("fragment不能为空");
    }

    @Override
    public void changeFragment(Fragment[] hideFragment, Fragment showFragment) {
        FragmentTransaction mfTransaction=mFragmentManager.beginTransaction();
        for (Fragment f:hideFragment) {
            mfTransaction.hide(f);
        }
        mfTransaction.show(showFragment);
        mfTransaction.commit();
    }

    @Override
    public void hideFragment(Fragment hideFragment) {
        mFragmentManager.beginTransaction().hide(hideFragment).commit();
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
