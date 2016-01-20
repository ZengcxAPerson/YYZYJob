package com.ane.framework.UI.activity;

import android.os.Bundle;

import com.ane.framework.Base.activity.IBaseActivity;
import com.ane.framework.R;
import com.ane.framework.UI.fragment.testListViewFragment;

public class MainActivity extends IBaseActivity {
    private testListViewFragment fragment;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        inItActivityWritCode();
    }

    @Override
    public void disposeBusiness() {

    }
    @Override
    public void findViews() {
        fragment=new testListViewFragment();
    }

    @Override
    public void setViewsContent() {
        getFragmentManager().beginTransaction().add(R.id.testFrg, fragment).commit();
    }

    @Override
    public void setViewsListener() {

    }


}
