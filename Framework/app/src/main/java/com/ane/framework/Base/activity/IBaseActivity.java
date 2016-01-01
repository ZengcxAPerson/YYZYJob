package com.ane.framework.Base.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;

import com.ane.framework.Base.interFace.ActivityToFragment;
import com.ane.framework.MyApplication;
import com.bugtags.library.Bugtags;


/**
 * <p>抽象一些公共操作</p>
 * <p>建立公共的代码规范</p>
 * Created by zcx on 2015/11/14.
 */
public abstract class IBaseActivity extends Activity implements ActivityToFragment{
    protected MyApplication application;
//    protected Activity activity;
    private static final String TAG = "Base-Activity";
    protected LayoutInflater mLayoutInflater;
    protected FragmentManager mFragmentManager;
//    protected AsyncRequestService mAsyncRequestService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //base的公共变量的实现
        application = (MyApplication) getApplication();
        application.addActivity(this);
        mLayoutInflater=LayoutInflater.from(this);
        mFragmentManager= getFragmentManager();

//        //规范子类代码书写
//        findViews();
//        setViewsContent();
//        setViewsListener();
//        disposeBusiness();
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

    @Override
    protected void onResume() {
        super.onResume();
        Bugtags.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Bugtags.onPause(this);
    }

    @Override
    public boolean dispatchGenericMotionEvent(MotionEvent ev) {
        Bugtags.onDispatchTouchEvent(this, ev);
        return super.dispatchGenericMotionEvent(ev);
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
