package com.ane.framework.Base.activity;

import android.content.Context;
import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * <p>拥有滑动返回Activity的功能</p>
 * <p>实现抽象类对子类的一些要求,让具体实现业务逻辑的Activity不必去关心其余操作,降低类之间的耦合度</p>
 * <p>此类使用注意事项:
 * <ul>
 * <li>该类主题要进行设置背景透明化,windowIsTranslucent=true</li>
 * <li>可以通过setSwipeBackEnable()来关闭功能</li>
 * <li>可以实现mSwipeListener变量，自定义滑动的事件处理</li>
 * <li>更多注意事项请参考<a href="https://github.com/ikew0ng/SwipeBackLayout">GitHub SwipeBackActivity</a></a></li>
 * </ul>
 * </p>
 * Created by zcx on 2015/11/27.
 */
public class BaseSwipeBackAty extends IBaseSwipeBackAty {
    private SwipeBackLayout mSwipeBackLayout;
    private static final int VIBRATE_DURATION = 20;
    protected SwipeBackLayout.SwipeListener mSwipeListener;
    //默认滑动方向
    //按理说应该保存到SharedPreferences文件中，但是为了此类的公用性，不对此操作进行封装
    //让一个项目下面的所有滑动退出的Activity可以实现不同的退出方案
    private int mEdgeFlag = SwipeBackLayout.EDGE_LEFT;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSwipeBackLayout = getSwipeBackLayout();
        mSwipeBackLayout.setEdgeTrackingEnabled(mEdgeFlag);
    }


    @Override
    protected void onStart() {
        super.onStart();
        //如果mSwipeBackLayout不为空，并且mSwipeListener子类对他进行了实例化，就设置子类重写的处理
        //否则，使用默认处理
        //mSwipeBackLayout为空,代表出现了错误，打印提示(一般应该不可能)
        if (mSwipeBackLayout != null) {
            if (mSwipeListener == null) {
                mSwipeBackLayout.addSwipeListener(new SwipeBackLayout.SwipeListener() {
                    @Override
                    public void onScrollStateChange(int state, float scrollPercent) {

                    }

                    @Override
                    public void onEdgeTouch(int edgeFlag) {

//                        vibrate(VIBRATE_DURATION);
                    }

                    @Override
                    public void onScrollOverThreshold() {
//                        vibrate(VIBRATE_DURATION);
                    }
                });
            } else {
                mSwipeBackLayout.addSwipeListener(mSwipeListener);
            }
        } else {
            Log.e(TAG, "BaseSwipeBackAty addSwipeListener appear error!mSwipeBackLayout is null!");
        }
    }

    @Override
    public void onIBaseCrate() {
        super.activity = this;
    }

    /**
     * 默认的滑动处理特效(会产生轻微振动。。。)
     *
     * @param duration
     */
    private void vibrate(long duration) {
        Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
        long[] pattern = {
                0, duration
        };
        vibrator.vibrate(pattern, -1);
    }

    protected int getEdgeFlag() {
        return mEdgeFlag;
    }
    /**
     * 修改滑动方向属性
     */
    protected void updateEdgeFlag(int mEdgeFlag) {
        this.mEdgeFlag = mEdgeFlag;
        mSwipeBackLayout.setEdgeTrackingEnabled(mEdgeFlag);
    }

}
