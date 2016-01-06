package com.ane.framework.Base.interFace;

/**
 * Created by zengcanxiang on 2016/1/6.
 */
public interface ActivityWritCode {
    /**
     * 描述：处理业务逻辑
     */
     void disposeBusiness();
    /**
     * 描述：把所有View找出来
     */
    void findViews();
    /**
     * 描述：设置所有View的内容
     */
    void setViewsContent();
    /**
     * 描述：设置View的监控器
     */
    void setViewsListener();
}
