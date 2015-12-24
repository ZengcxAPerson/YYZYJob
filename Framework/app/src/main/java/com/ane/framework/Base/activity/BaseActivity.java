package com.ane.framework.Base.activity;

/**
 * <p>继承自IBaseActivity</p>
 * <p>实现象类对子类的一些要求,让具体实现业务逻辑的Activity不必去关心其余操作,降低类之间的耦合度</p>
 * <p>Created by zcx on 2015/11/27.</p>
 */
public class BaseActivity extends IBaseActivity {
    @Override
    public void onIBaseCrate() {
        super.activity = this;
    }
}
