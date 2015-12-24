package com.ane.framework.Presentation.activity;

import android.os.Bundle;

import com.ane.framework.Base.activity.BaseSwipeBackAty;
import com.ane.framework.R;

public class TestActivity extends BaseSwipeBackAty {
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
    }

}
