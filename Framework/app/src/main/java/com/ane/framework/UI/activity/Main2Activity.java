package com.ane.framework.UI.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.ane.framework.Base.activity.IBaseAppCompatAty;
import com.ane.framework.R;
import com.orhanobut.logger.Logger;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Main2Activity extends IBaseAppCompatAty {
    Snackbar snackbar;
    FloatingActionButton fab;
    long oneTime = 0, twoTime = 0;
    private static final int DEFAULT_OUT_TIME = 1500;//判断连续点击的条件,800这个数字是不对的

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                if (oneTime == 0) {
                    oneTime = System.currentTimeMillis();
                    twoTime = System.currentTimeMillis();
                } else {
                    oneTime = twoTime;
                    twoTime = System.currentTimeMillis();
                    Logger.i(twoTime - oneTime + "");
                    //2750 1500
                    if (twoTime - oneTime < DEFAULT_OUT_TIME) {
                        Toast.makeText(Main2Activity.this, "请不要连续多次点击", Toast.LENGTH_SHORT).show();
                        return;
                    }
                }
                snackbar = Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG);
                snackbar.setAction("Action", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        onAnimationHideSnack(snackbar);
                    }
                }).show();
            }
        });


    }

    @Override
    public void disposeBusiness() {

    }

    @Override
    public void findViews() {

    }

    @Override
    public void setViewsContent() {

    }

    @Override
    public void setViewsListener() {

    }

    /**
     * <p>代码解决当Snackbar直接调用dismiss时候FloatingActionButton不下来</p>
     * <p>可能这个效果可以通过xml某个属性控制,但是现在没有找到,是使用发射方法解决的</p>
     * <p>会抛出一个异常，但是在测试中，暂时没有影响</p>
     *
     * @param s 要消失的snackbar对象
     */
    public void onAnimationHideSnack(Snackbar s) {
        Class<? extends Object> clazz = s.getClass();
        try {
            Method hideView = clazz.getMethod("hideView");
            hideView.invoke(s);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

}
