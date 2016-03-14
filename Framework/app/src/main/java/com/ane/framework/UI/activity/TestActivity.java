package com.ane.framework.UI.activity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.ane.framework.Base.activity.IBaseActivity;
import com.ane.framework.R;
import com.zengcanxiang.baseAdapter.absListView.BaseAdapter;
import com.zengcanxiang.baseAdapter.absListView.HelperHolder;

import java.util.ArrayList;
import java.util.List;

public class TestActivity extends IBaseActivity {
    ListView choiceListView;
    private List<String> mList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test);
        mList=new ArrayList<>();
        for(int i=0;i<60;i++){
            mList.add(i+"");
        }
        inItActivityWritCode();
    }

    @Override
    public void disposeBusiness() {

    }

    @Override
    public void findViews() {
        choiceListView= (ListView) findViewById(R.id.choiceListView);
        choiceListView.setAdapter(new MyAdapter(mList,this,R.layout.test_list_item));
    }

    @Override
    public void setViewsContent() {

    }

    @Override
    public void setViewsListener() {

    }

    class MyAdapter extends BaseAdapter<String> {
        public MyAdapter(List data, Context context, int... layoutId) {
            super(data, context, layoutId);
        }


        @Override
        public void convert(HelperHolder helperHolder, int i, String s) {
            TextView testText=helperHolder.getView(R.id.testText);
            testText.setText(i+"");
        }
    }
}
