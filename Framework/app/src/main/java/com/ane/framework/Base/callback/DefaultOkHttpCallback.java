package com.ane.framework.Base.callback;

import android.app.Dialog;
import android.content.Context;

import com.alibaba.fastjson.JSONObject;
import com.ane.framework.Base.network.ResultMsg;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

/**
 * <p>实现访问网络接口的回调,实现一个默认的请求dialog弹框.</p>
 * <p>如果不用弹框(null)或者更换单独的弹框(new dialog...),可以通过构造方法来改变默认dialog</p>
 * <p>如果需要特殊操作,可以重写onBefore()方法或者重新实现Callback.</p>
 * <p>可以自己决定是不是继续使用DefaultOkHttpCallback onBefore()和onAfter()的实现</p>
 * Created by Administrator on 2015/12/18.
 */
public abstract class DefaultOkHttpCallback extends Callback<ResultMsg> {
    protected Dialog mDialog;
    private Context mContext;
    /**
     * 查看类说明
     */
    public DefaultOkHttpCallback(Context context) {
        this.mContext = context;
        //实现默认dialog
//        mDialog=new ...
    }
    /**
     * 查看类说明
     */
    public DefaultOkHttpCallback(Context context, Dialog dialog) {
        this.mDialog = dialog;
        this.mContext = context;
    }

    @Override
    public ResultMsg parseNetworkResponse(Response response) throws IOException {
        String string = response.body().string();
        ResultMsg msg = JSONObject.parseObject(string, ResultMsg.class);
        return msg;
    }

    @Override
    public void onResponse(ResultMsg response) {
        RequestEnd(mContext,response);
    }

    @Override
    public void onError(Request request, Exception e) {
        onError(mContext,request,e);
    }

    @Override
    public void onAfter() {
        if (mDialog != null)
            mDialog.dismiss();
    }

    @Override
    public void onBefore(Request request) {
        if (mDialog != null)
            mDialog.show();
    }

    /**
     * 访问结束后的结果回调
     * @param context 初始化callback的上下文,便于操作
     * @param resultMsg 访问结果实体类
     */
    public abstract void RequestEnd(Context context,ResultMsg resultMsg);

    /**
     * 出现访问错误时的回调
     * @param context 初始化callback的上下文,便于操作
     * @param request 包含访问请求的参数、路径等信息
     * @param e 访问异常信息类
     */
    public abstract void onError(Context context,Request request, Exception e);
}
