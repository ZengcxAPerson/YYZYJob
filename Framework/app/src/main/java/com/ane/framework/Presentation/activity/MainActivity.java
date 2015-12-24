package com.ane.framework.Presentation.activity;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SearchView;

import com.ane.framework.Base.activity.BaseActivity;
import com.ane.framework.Base.callback.DefaultAsyncCallback;
import com.ane.framework.Base.network.AsyncRequestService;
import com.ane.framework.Base.network.ResultMsg;
import com.ane.framework.R;

public class MainActivity extends BaseActivity {
    private Button tetBtn;

    @TargetApi(Build.VERSION_CODES.KITKAT)
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        this.tetBtn = (Button) findViewById(R.id.tetBtn);
        tetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Snackbar.make(v, "测试", Snackbar.LENGTH_LONG)
//                        .setAction("退出", null).show();
                startActivity(new Intent(MainActivity.this, Main2Activity.class));
            }
        });
        AsyncRequestService service = new AsyncRequestService("");
        service.setAsyncRequestCallBack(new DefaultAsyncCallback(this,null) {
            @Override
            public void onEnd(Context context, ResultMsg resultMsg) {

            }
        });
        SearchView searchView=new SearchView(this);

    }

}
