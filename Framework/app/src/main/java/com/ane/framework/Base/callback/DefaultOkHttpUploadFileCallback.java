package com.ane.framework.Base.callback;

import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;
import com.zhy.http.okhttp.callback.Callback;

import java.io.IOException;

/**
 * Created by Administrator on 2015/12/21.
 */
public abstract class DefaultOkHttpUploadFileCallback extends Callback<Response> {

    @Override
    public abstract void onBefore(Request request);

    @Override
    public abstract void onAfter();

    @Override
    public Response parseNetworkResponse(Response response) throws IOException {
        return response;
    }
    @Override
    public abstract void onResponse(Response response);

    @Override
    public abstract void inProgress(float progress);

    @Override
    public abstract void onError(Request request, Exception e);


}
