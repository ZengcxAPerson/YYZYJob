package com.ane.framework.Base.callback;

import com.zhy.http.okhttp.callback.FileCallBack;

/**
 * Created by Administrator on 2015/12/21.
 */
public abstract class DefaultOkHttpDownFileCallback extends FileCallBack {
    /**
     * 下载文件回调构造方法
     * @param destFileDir  所保存的文件夹
     * @param destFileName 文件名
     */
    public DefaultOkHttpDownFileCallback(String destFileDir, String destFileName) {
        super(destFileDir, destFileName);
    }
}
