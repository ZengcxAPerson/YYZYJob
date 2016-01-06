package com.ane.framework.UI.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ane.framework.Base.activity.IBaseActivity;
import com.ane.framework.R;

public class MainActivity extends IBaseActivity {
    private Button tetBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        activityWritInit();
    }

    @Override
    public void disposeBusiness() {

    }
    @Override
    public void findViews() {
        this.tetBtn = (Button) findViewById(R.id.tetBtn);
    }

    @Override
    public void setViewsContent() {
        tetBtn.setText("示例");
    }

    @Override
    public void setViewsListener() {
        tetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "测试", Snackbar.LENGTH_LONG)
//                        .setAction("退出", null).show();
//                startActivity(new Intent(MainActivity.this, TestActivity.class));
//                throw new IllegalArgumentException("错误");
            }
        });

    }


}
