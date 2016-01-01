package com.ane.framework.Base.interFace;

import android.app.Fragment;
import android.os.Bundle;

/**
 * Created by zengcanxiang on 2015/12/30.
 */
public interface ActivityToFragment {

    /**
     * 初始化绑定fragment
     */
    void initBindFragment(int fragmentViewId,Fragment initFragment);

    /**
     * Activity发信息给fragment
     * @param bundle
     */
    void setBundle(Bundle bundle,Fragment fragment);

    /**
     * 切换fragment
     */
    void changeFragment(Fragment[] hideFragment,Fragment showFragment);

    /**
     * 隐藏fragment
     * @param hideFragment
     */
    void hideFragment(Fragment hideFragment);


}
