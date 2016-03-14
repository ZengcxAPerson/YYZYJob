package com.ane.framework.UI.activity;

import android.view.MotionEvent;

import com.ane.framework.Base.activity.IBaseActivity;
import com.bugtags.library.Bugtags;

/**
 * Created by zengcanxiang on 2016/1/28.
 */
public abstract class BaseActivity extends IBaseActivity {
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
}
