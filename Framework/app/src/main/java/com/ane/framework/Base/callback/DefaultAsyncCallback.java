package com.ane.framework.Base.callback;

import android.app.Dialog;
import android.content.Context;

import com.ane.framework.Base.network.AsyncRequestService;
import com.ane.framework.Base.network.ResultMsg;

/**
 * <p>实现访问网络接口的回调,实现一个默认的请求dialog弹框.</p>
 * <p>如果不用弹框(null)或者更换单独的弹框(new dialog...),可以通过构造方法来改变默认dialog</p>
 * <p>如果需要特殊操作,可以重写AsyncRequestStart()方法或者重新实现AsyncRequestCallBack.</p>
 * <p>可以自己决定是不是继续使用DefaultAsyncCallback AsyncRequestStart实现()</p>
 * Created by Administrator on 2015/12/8.
 */
public abstract class DefaultAsyncCallback implements AsyncRequestService.AsyncRequestCallBack {


    protected Dialog mDialog;
    private Context mContext;

    /**
     * 查看类说明
     */
    public DefaultAsyncCallback(Context context) {
        this.mContext = context;
        //编写默认的dialog
        //Dialog=new ...
    }

    /**
     * 查看类说明
     */
    public DefaultAsyncCallback(Context context,Dialog dialog) {
        this.mDialog = dialog;
        this.mContext = context;
    }

    @Override
    public void AsyncRequestStart() {
        if (mDialog != null) {
            mDialog.show();
        }
    }

    @Override
    public void AsyncRequestEnd(ResultMsg resultMsg) {
//        隐藏 if(mDialog!=null)mDialog.dismiss();
        onEnd(mContext,resultMsg);
    }

    public abstract void onEnd(Context context,ResultMsg resultMsg);
}
