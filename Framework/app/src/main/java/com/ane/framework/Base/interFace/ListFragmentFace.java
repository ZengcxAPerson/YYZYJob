package com.ane.framework.Base.interFace;

import com.ane.framework.Base.adapter.BaseAdapter;

/**
 * Created by zengcanxiang on 2016/1/10.
 */
public interface ListFragmentFace {

    BaseAdapter bindAdapter();

    void bindRefresh();
}
