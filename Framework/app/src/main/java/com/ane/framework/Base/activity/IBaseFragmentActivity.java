package com.ane.framework.Base.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.os.PersistableBundle;

import com.ane.framework.Base.interFace.ActivityToFragment;

/**
 * Created by zengcanxiang on 2016/1/6.
 */
public abstract class IBaseFragmentActivity extends IBaseActivity implements ActivityToFragment {
    protected FragmentManager mFragmentManager;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        mFragmentManager = getFragmentManager();
    }

    @Override
    public void initBindFragment(int fragmentViewId, Fragment initFragment) {
        mFragmentManager.beginTransaction().add(fragmentViewId, initFragment).commit();
    }

    @Override
    public void setBundle(Bundle bundle, Fragment fragment) {
        if (fragment != null)
            fragment.setArguments(bundle);
        else
            throw new IllegalArgumentException("fragment不能为空");
    }

    @Override
    public void changeFragment(Fragment[] hideFragment, Fragment showFragment) {
        FragmentTransaction mfTransaction = mFragmentManager.beginTransaction();
        for (Fragment f : hideFragment) {
            mfTransaction.hide(f);
        }
        mfTransaction.show(showFragment);
        mfTransaction.commit();
    }
    @Override
    public void hideFragment(Fragment hideFragment) {
        mFragmentManager.beginTransaction().hide(hideFragment).commit();
    }


}
