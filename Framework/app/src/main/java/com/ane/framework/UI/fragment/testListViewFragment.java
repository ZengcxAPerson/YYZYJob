package com.ane.framework.UI.fragment;

import android.content.Context;
import android.os.Bundle;
import android.widget.TextView;

import com.ane.framework.Base.fragment.ListViewFragment;
import com.ane.framework.R;
import com.orhanobut.logger.Logger;
import com.zengcanxiang.baseAdapter.absListView.HelperAdapter;
import com.zengcanxiang.baseAdapter.absListView.HelperHolder;

import java.util.ArrayList;
import java.util.List;

import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by zengcanxiang on 2016/1/17.
 */
public class testListViewFragment extends ListViewFragment {

    private List<String> mList;
    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mList=new ArrayList<>();
        for(int i=0;i<60;i++){
            mList.add(i+"");
        }
        installRefresh(true);
        installLoadMore(true);
        inItActivityWritCode();
    }

    @Override
    protected void refresh(PtrFrameLayout ptr) {
        Logger.d("下拉刷新");
        ptr.refreshComplete();
    }

    @Override
    protected void loadMore() {
        mListView.postDelayed(new Runnable() {
            @Override
            public void run() {
                Logger.d("加载更多");
                mAdapter.notifyDataSetChanged();
                loadMoreComplete();
            }
        }, 1500);
    }

    @Override
    public void install() {
        mView.setBackgroundResource(android.R.color.holo_red_dark);
        StoreHouseHeader header=defaultFreshHead("zengcanxiang");
        mPtrFrameLayout.setHeaderView(header);
        mPtrFrameLayout.addPtrUIHandler(header);
    }

    @Override
    protected HelperAdapter bindAdapter() {
        return new MyAdapter(mList,getActivity(),R.layout.test_list_item,R.layout.test_list_item2);
    }

    @Override
    public void disposeBusiness() {

    }

    @Override
    public void setViewsListener() {

    }


    class MyAdapter extends HelperAdapter<String> {
        public MyAdapter(List data, Context context, int... layoutIds) {
            super(data, context, layoutIds);
        }

        @Override
        public void convert(HelperHolder helperHolder, int i, String s) {
            TextView testText=helperHolder.getView(R.id.testText);
            testText.setText(i+"");
        }

        @Override
        public int checkLayout(int position, String item) {
            if(position%2==0){
                return 1;
            }
            return 0;
        }

    }
}
