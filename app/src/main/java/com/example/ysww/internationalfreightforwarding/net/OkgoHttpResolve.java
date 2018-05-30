package com.example.ysww.internationalfreightforwarding.net;

import android.content.Context;
import android.util.Log;

import com.example.ysww.internationalfreightforwarding.utils.GsonUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;

import java.io.File;

import static android.content.ContentValues.TAG;


/**
 * Created by me-jie on 2017/3/31.
 */

public class OkgoHttpResolve {
    private static Context context;

    public OkgoHttpResolve(Context context) {
        this.context = context;
    }

    /**
     * get 提交
     *
     * @param url
     * @param clazz
     * @param callBack
     * @param <T>
     * @return
     */
    public static <T> T getResult(String url, String tokenId, final Class<T> clazz, final HttpCallBack callBack) {
        OkGo.<String>get(url)
                .tag(context)
                .headers("token", tokenId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        //结果
                        T t = GsonUtils.getGson(response.body(), clazz);
                        if (callBack != null) {
                            callBack.finish(t);
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e(TAG, "onError: " + response.body());
                    }
                });
        return null;
    }

    /**
     * post json提交
     *
     * @param url
     * @param clazz
     * @param callBack
     * @param <T>
     * @return
     */
    public static <T> T postJsonResult(String url, String tokenId, String str, final Class<T> clazz, final HttpCallBack callBack) {
        OkGo.<String>post(url)
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .upString(str, HttpParams.MEDIA_TYPE_JSON)
                .headers("token", tokenId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        //结果
                        T t = GsonUtils.getGson(response.body(), clazz);
                        if (callBack != null) {
                            callBack.finish(t);
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e(TAG, "onError: " + response.body());
                    }
                });
        return null;
    }
    /**
     * post 字符串提交
     *
     * @param url
     * @param clazz
     * @param callBack
     * @param <T>
     * @return
     */
    public static <T> T postStringResult(String url, String tokenId, final Class<T> clazz, final HttpCallBack callBack) {
        OkGo.<String>post(url)     // 请求方式和请求url
                .tag(context)
                .headers("token", tokenId)
                // 请求的 tag, 主要用于取消对应的请求
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        //结果
                        T t = GsonUtils.getGson(response.body(), clazz);
                        if (callBack != null) {
                            callBack.finish(t);
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e(TAG, "onError: " + response.body());
                    }
                });
        return null;
    }
    /**
     * 上传文件
     *
     * @param url
     * @param category
     * @param srcPath
     * @param clazz
     * @param callBack
     * @param <T>
     * @return
     */
    public static <T> T postJsonUpLoadResult(String url, String tokenId, String category, String srcPath, final Class<T> clazz, final HttpCallBack callBack) {
        OkGo.<String>post(url)     // 请求方式和请求url
                .tag(context)                       // 请求的 tag, 主要用于取消对应的请求
                .params("category", category)        // 这里可以上传参数
                .params("file", new File(srcPath))   // 可以添加文件上传
                .cacheKey("file_img" + category)
                .headers("token", tokenId)
                .execute(new StringCallback() {
                    @Override
                    public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                        //结果
                        T t = GsonUtils.getGson(response.body(), clazz);
                        if (callBack != null) {
                            callBack.finish(t);
                        }
                    }

                    @Override
                    public void onError(com.lzy.okgo.model.Response<String> response) {
                        super.onError(response);
                        Log.e(TAG, "onError: " + response.body());
                    }
                });
        return null;
    }


    public interface HttpCallBack<T> {
        <T> void finish(T result);
    }
}
