package com.ane.framework.UI.activity;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ane.framework.Base.activity.IBaseActivity;
import com.ane.framework.R;

public class MainActivity extends IBaseActivity {
    private Button tetBtn;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        findViews();
        setViewsContent();
        setViewsListener();
        disposeBusiness();
    }

    @Override
    protected void disposeBusiness() {

    }
    @Override
    protected void findViews() {
        this.tetBtn = (Button) findViewById(R.id.tetBtn);

    }

    @Override
    protected void setViewsContent() {
        tetBtn.setText("示例");
    }

    @Override
    protected void setViewsListener() {
        tetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "测试", Snackbar.LENGTH_LONG)
//                        .setAction("退出", null).show();
                startActivity(new Intent(MainActivity.this, TestActivity.class));
            }
        });
    }


}