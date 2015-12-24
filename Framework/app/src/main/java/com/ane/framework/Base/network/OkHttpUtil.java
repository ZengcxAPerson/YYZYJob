package com.ane.framework.Base.network;

import android.support.v4.util.ArrayMap;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.builder.GetBuilder;
import com.zhy.http.okhttp.builder.PostFormBuilder;
import com.zhy.http.okhttp.callback.Callback;
import com.zhy.http.okhttp.callback.FileCallBack;

import java.io.File;
import java.util.ArrayList;

/**
 * <p>okHttp使用util</p>
 * Created by Administrator on 2015/12/18.
 */
public class OkHttpUtil {

    /**
     * <p>如果没有为请求自定义tag，即使用默认tag</p>
     * <p>不推荐,因为可能会存在取消某一个请求的时候,会取消掉其他为默认tag但是不想取消的请求</p>
     * @deprecated
     */
    public static final String DEFAULT_TAG = "Framework";

    //相关的默认时间值
    public static final long TIME_OUT_CONN_DEFAULT = 20000;
    public static final long TIME_OUT_READ_DEFAULT = 20000;
    public static final long TIME_OUT_WRITE_DEFAULT = 20000;

    /**
     * post方式请求
     * @param paramsMap 参数键值对
     * @param url  请求url
     * @param tag  请求tag
     * @param callback 请求回调
     */
    public static void post(ArrayMap<String, String> paramsMap, String url,
                            Object tag, Callback callback) throws IllegalArgumentException{
        catchUrl(url);
        PostFormBuilder post = OkHttpUtils.post();
        post.params(paramsMap);
        post.url(url).tag(tag).build().execute(callback);

    }

    /**
     * post方式请求,使用默认的tag
     * @param paramsMap 请求url
     * @param url 请求tag
     * @param callback 请求回调
     */
    public static void post(ArrayMap<String, String> paramsMap, String url, Callback callback){
        post(paramsMap, url, DEFAULT_TAG, callback);
    }

    /**
     * get方式请求
     * @param paramsMap 参数键值对
     * @param url 请求url
     * @param tag 请求tag
     * @param callback 请求回调
     */
    public static void get(ArrayMap<String, String> paramsMap,
                           String url, Object tag, Callback callback) {
        catchUrl(url);
        GetBuilder get = OkHttpUtils.get();
        get.params(paramsMap);
        get.url(url).tag(tag).build().execute(callback);
    }

    /**
     * get方式请求,使用默认的tag
     * @param paramsMap 参数键值对
     * @param url 请求url
     * @param callback 请求回调
     */
    public static void get(ArrayMap<String, String> paramsMap, String url, Callback callback) {
        get(paramsMap, url, DEFAULT_TAG, callback);
    }

    /**
     * 上传文件,没有测试大文件上传是否可行
     * @param uploadUrl 上传url
     * @param uploadFileTag 上传文件请求tag
     * @param paramsMap 键值对
     * @param file 文件
     * @param callback 上传回调
     */
    public static void uploadFile(String uploadUrl, Object uploadFileTag,ArrayMap<String, String> paramsMap,
                                  File file, Callback callback) {
        catchUrl(uploadUrl);
        OkHttpUtils.postFile().tag(uploadFileTag).url(uploadUrl).params(paramsMap)
                .file(file).build().execute(callback);
    }

    /**
     * 上传文件,不需要键值对,没有测试大文件上传是否可行,
     * @param uploadUrl 上传url
     * @param uploadFileTag 上传文件请求tag
     * @param file 文件
     * @param callback 上传回调
     */
    public static void uploadFile(String uploadUrl, Object uploadFileTag,
                                  File file, Callback callback) {
            uploadFile(uploadUrl, uploadFileTag, null, file, callback);
    }

    /**
     * 表单形式上传单个文件
     * @param uploadUrl 上传url
     * @param uploadFileTag 上传文件请求tag
     * @param paramsMap 键值对
     * @param fileKey 文件对应的key
     * @param fileName 文件的名称
     * @param file 文件
     * @param callback 请求回调
     */
    public static void uploadFile(String uploadUrl, Object uploadFileTag,
                                  ArrayMap<String, String> paramsMap,
                                  String fileKey, String fileName, File file, Callback callback) {
        catchUrl(uploadUrl);
        OkHttpUtils.post().tag(uploadFileTag).url(uploadUrl).params(paramsMap).
                addFile(fileKey, fileName, file).build().execute(callback);
    }

    /**
     * 表单形式上传单个文件,不需要键值对
     * @param uploadUrl 上传url
     * @param uploadFileTag 上传文件请求tag
     * @param fileKey 文件对应的key
     * @param fileName 文件的名称
     * @param file 文件
     * @param callback 请求回调
     */
    public static void uploadFile(String uploadUrl, Object uploadFileTag,
                                  String fileKey, String fileName, File file, Callback callback) {
        uploadFile(uploadUrl, uploadFileTag, null, fileKey, fileName, file, callback);
    }

    /**
     * 表单形式上传多个文件
     * @param uploadUrl 上传url
     * @param uploadFileTag 上传文件请求tag
     * @param fileKeys 文件分别对应的key
     * @param fileNames 文件分别对应的name
     * @param files 文件
     * @param paramsMap 键值对
     * @param callback 请求回调
     */
    public static void uploadFiles(String uploadUrl, Object uploadFileTag,
           ArrayList<String> fileKeys,ArrayList<String> fileNames, ArrayList<File> files,
           ArrayMap<String, String> paramsMap,Callback callback) {
        catchUrl(uploadUrl);
        PostFormBuilder post = OkHttpUtils.post();
        post.params(paramsMap);
        post.url(uploadUrl);
        post.tag(uploadFileTag);
        int size=fileNames.size();
        if(size!=files.size() || size!=fileKeys.size() || files.size()!=fileKeys.size()){
//            throw new  IllegalAccessError("files的长度和fileKeys的长度还有fileNames的长度要保持一致");
            throw new IllegalAccessError("files size not equal fileKeys size or fileNames size");
        }
        for (int i=0;i<size;i++) {
            post.addFile(fileKeys.get(i),fileNames.get(i),files.get(i));
        }
        post.build().execute(callback);
    }

    /**
     * 表单形式上传多个文件,不需要键值对
     * @param uploadUrl 上传url
     * @param uploadFileTag 上传文件请求tag
     * @param fileKeys 文件分别对应的key
     * @param fileNames 文件分别对应的name
     * @param files 文件
     * @param callback 请求回调
     */
    public static void uploadFiles(String uploadUrl, Object uploadFileTag,
                                   ArrayList<String> fileKeys,ArrayList<String> fileNames, ArrayList<File> files,
                                   Callback callback) {
        uploadFiles(uploadUrl, uploadFileTag, fileKeys, fileNames, files, null, callback);
    }

    /**
     * 下载文件
     * @param downUrl 下载url
     * @param downFileTag 下载请求tag
     * @param callBack 请求回调
     */
    public static void downFile(String downUrl, Object downFileTag, FileCallBack callBack) {
        catchUrl(downUrl);
        downFile(downUrl, downFileTag,
                TIME_OUT_CONN_DEFAULT, TIME_OUT_READ_DEFAULT, TIME_OUT_WRITE_DEFAULT,
                callBack);
    }

    /**
     * 下载文件,有特殊定制要求
     * @param downUrl 下载url
     * @param downFileTag 下载请求tag
     * @param connTimeOut 连接超时限制
     * @param readTimeOut 读取超时限制
     * @param writeTimeOut 写操作超时限制
     * @param callBack 请求回调
     */
    public static void downFile(String downUrl, Object downFileTag,
                                long connTimeOut, long readTimeOut, long writeTimeOut, FileCallBack callBack) {
        catchUrl(downUrl);
        OkHttpUtils.get().url(downUrl).tag(downFileTag).build()
                .connTimeOut(connTimeOut)
                .readTimeOut(readTimeOut)
                .writeTimeOut(writeTimeOut)
                .execute(callBack);
    }

    /**
     * 取消某个tag对应的请求
     * @param tag 请求对应的tag
     */
    public static void cancelTag(Object tag) {
        OkHttpUtils.getInstance().cancelTag(tag);
    }

    /**
     * 包装请求键值对
     * @param jsonParams 请求的json参数
     * @return 包装好的ArrayMap
     */
    public static ArrayMap<String, String> builderMap(String jsonParams) {
        ArrayMap<String, String> arrayMap = new ArrayMap<>();
        arrayMap.put("", "");
        arrayMap.put("", jsonParams);
        arrayMap.put("", "");
        return arrayMap;
    }

    private static void catchUrl(String url){
        if(url.indexOf("http")<0){
            throw new IllegalArgumentException("url不符合规则 没有http或者https前缀");
        }
    }
}
