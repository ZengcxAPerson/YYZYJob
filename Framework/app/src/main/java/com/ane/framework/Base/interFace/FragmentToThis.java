package com.ane.framework.Base.interFace;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zengcanxiang on 2015/12/30.
 */
public interface FragmentToThis {


    /**
     * 得到Activity传给fragment的bundle
     * @return
     */
    Bundle getBundle();

    /**
     * fragment发送数据给Activity
     * @param toBundle
     * @param parentActivity
     */
    void setBundle(Bundle toBundle,Class<?> parentActivity);

    View initView(int createViewId,ViewGroup container);

    interface fragmentToActivity{
        void getFragmentBundle(Bundle comeBundle);
    }

}
