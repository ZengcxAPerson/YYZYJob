package com.ane.framework.Base.fragment;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.ane.framework.R;
import com.zengcanxiang.baseAdapter.absListView.HelperAdapter;

import in.srain.cube.views.ptr.PtrDefaultHandler;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import in.srain.cube.views.ptr.header.StoreHouseHeader;

/**
 * Created by zengcanxiang on 2016/1/10.
 */
public abstract class ListViewFragment extends BaseFragment implements AbsListView.OnScrollListener {

    protected ListView mListView;
    protected HelperAdapter mAdapter;
    private final int listView_layoutId = R.layout.base_fragment_listview;
    private final int listView_viewId = R.id.base_listView;
    private final int ptrLayoutId = R.id.base_ptrLayout;
    private boolean isRefresh = false;
    private boolean isLoadMore = false;
    private boolean isBeingLoadMore = false;
    protected PtrFrameLayout mPtrFrameLayout;
    protected View mView;
    protected View layout_loadMore;
    protected LinearLayout loadMoreView;
    //How long can not be repeated loading
    //多久之内不能重复加载
    private int repeated_loading=1500;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = initView(inflater, listView_layoutId, container);
        layout_loadMore = inflater.inflate(R.layout.load_more_view, null);
        return mView;

    }

    @Override
    public void findViews() {
        mListView = (ListView) getView(listView_viewId);
        mPtrFrameLayout = (PtrFrameLayout) getView(ptrLayoutId);
    }

    @Override
    public void setViewsContent() {
        mAdapter = bindAdapter();
        mListView.setAdapter(mAdapter);
        install();
        if (isRefresh) {
            mPtrFrameLayout.setDurationToCloseHeader(repeated_loading);
            mPtrFrameLayout.setPtrHandler(new PtrHandler() {
                @Override
                public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {
                    return PtrDefaultHandler.checkContentCanBePulledDown(frame, content, header);
                }

                @Override
                public void onRefreshBegin(PtrFrameLayout frame) {
                    mPtrFrameLayout.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            refresh(mPtrFrameLayout);
                        }
                    }, 500);
                }
            });
        }

        if (isLoadMore) {
            mListView.setOnScrollListener(this);
        }
    }


    /**
     * 刷新
     */
    protected void refresh(PtrFrameLayout ptr) {

    }

    /**
     * 下拉加载
     */
    protected void loadMore() {

    }

    /***
     * 下拉加载完成
     */
    public void loadMoreComplete() {
        isBeingLoadMore = false;
        mListView.removeFooterView(loadMoreView);
    }

    /**
     * 是否启动下拉刷新
     * @param refresh
     */
    public void installRefresh(boolean refresh) {
        this.isRefresh = refresh;
    }

    /**
     * 是否启动上拉加载
     * @param loadMore
     */
    public void installLoadMore(boolean loadMore) {
        this.isLoadMore = loadMore;
    }

    /**
     * <p>设置控件的自定义属性</p>
     * <p>父类的控件，子类全部可以拿到</p>
     */
    public void install() {
    }



    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (scrollState == AbsListView.OnScrollListener.SCROLL_STATE_IDLE) {
            // 判断是否滚动到底部
            if (view.getLastVisiblePosition() == view.getCount() - 1) {
                //加载更多功能的代码
                //要加2层判断，防止加载更多重复调用
                if (!isBeingLoadMore) {
                    addFooterView();
                    loadMore();
                    isBeingLoadMore = true;
                }
            }
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {

    }

    /**
     * 绑定适配器
     *
     * @return
     */
    protected abstract HelperAdapter bindAdapter();

    /**
     * 默认设置的刷新头部
     *
     * @param headText 自动转大写，支持字母，数字
     * @return
     */
    public StoreHouseHeader defaultFreshHead(String headText) {
        StoreHouseHeader header = new StoreHouseHeader(getActivity());
        header.setPadding(0, dp2px(20), 0, dp2px(20));
        header.initWithString(headText);
        return header;
    }
    /**
     * listView加上加载视图
     */
    private void addFooterView() {
        if (loadMoreView == null) {
            loadMoreView = (LinearLayout) layout_loadMore.findViewById(R.id.loadMoreView);
        }
        mListView.addFooterView(loadMoreView);
    }
    /***
     * dp转px
     */
    private int dp2px(int dp) {
        DisplayMetrics dm = new DisplayMetrics();
        WindowManager wm;
        wm = (WindowManager) getActivity().getApplicationContext().getSystemService("window");
        wm.getDefaultDisplay().getMetrics(dm);
        float scale = dm.density;
        return (int) (dp * scale + 0.5F);
    }


    public boolean getIsLoadMore(){
        return isBeingLoadMore;
    }
    public ListView getListView() {
        return mListView;
    }

    public HelperAdapter getAdapter() {
        return mAdapter;
    }

    public void setRepeated_loading(int repeated_loading){
        this.repeated_loading=repeated_loading;
    }
}
