package com.ane.framework.Base.network;

import android.os.AsyncTask;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AsyncRequestService extends AsyncTask<Object, Object, ResultMsg> {

    private AsyncRequestCallBack asyncRequestCallBack;
    private String postUrl;
    //默认的连接超时时间,可以通过set方法在特定的请求来修改超时时间
    private long connectionTimeout = 20000;
    private long socketTimeout = 20000;

    public AsyncRequestService(String postUrl) {
        this.postUrl = postUrl;
    }

    @Override
    protected void onPreExecute() {
        asyncRequestCallBack.AsyncRequestStart();
    }

    @Override
    protected ResultMsg doInBackground(Object... params) {
        String postParam;
        if (null != params && params.length > 0) {
            postParam = (String) params[0];
        } else {
            postParam = "";
        }

        //暂时屏蔽签名
//		String tmp = new String(OrderCodeMD5.GetMD5Code(postParam
//				+ Constants.APP_KEY_BANKING + Constants.APP_SECRET_BANKING));
        Map<String, Object> paramList = new HashMap<String, Object>();
//		paramList.put("digest",
//				(new String(Base64.encode(tmp.getBytes(), Base64.DEFAULT))
//						.trim()));
        paramList.put("params", postParam);
        paramList.put("timestamp", String.valueOf(new Date().getTime()));

        ResultMsg resultMsg = HttpConnUtils.connRequest(postUrl, paramList, connectionTimeout, socketTimeout);
        System.out.println("金融请求  请求的URL为：" + postUrl + "请求参数为：" + postParam);
        if (resultMsg.getResult()) {
            if (!TextUtils.isEmpty(resultMsg.getResultInfo().toString())
                    && resultMsg.getResultInfo() != null) {
                String strResultJson = resultMsg.getResultInfo().toString();
                strResultJson = strResultJson.replace("\r\nnull", "").replace("null{", "{");
                strResultJson = strResultJson.replace("}null", "}");
                JSONObject jsonObject = JSONObject.parseObject(strResultJson);
                resultMsg = JSON.toJavaObject(jsonObject, ResultMsg.class);
            } else {
                resultMsg.setResult(false);
                resultMsg.setReason("返回结果错误");
            }
        }
        return resultMsg;
    }

    @Override
    protected void onPostExecute(ResultMsg result) {
        asyncRequestCallBack.AsyncRequestEnd(result);
    }

    public interface AsyncRequestCallBack {
        void AsyncRequestStart();
        void AsyncRequestEnd(ResultMsg resultMsg);
    }

    public void setAsyncRequestCallBack(
            AsyncRequestCallBack asyncRequestCallBack) {
        this.asyncRequestCallBack = asyncRequestCallBack;

    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    public void setSocketTimeout(long socketTimeout) {
        this.socketTimeout = socketTimeout;
    }

}
