package com.l99.chinafootball.utils;

import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.Callback;

import okhttp3.Call;
import okhttp3.Response;

/**
 * Created by 78101 on 2016/7/1.
 */
public class HttpUtils {

    public static void get(String url,final  HttpListener listener) {
        //创建一个Request
        OkHttpUtils.get().url(url).build().execute(new Callback<String>() {
            @Override
            public String parseNetworkResponse(Response response, int i) throws Exception {
                        if(response.isSuccessful()) {
                                return response.body().string();
                        }else {
                            if (listener !=null) {
                                listener.onError();

                            }
                        }
                            return null;
            }

            @Override
            public void onError(Call call, Exception e, int i) {
                if (listener !=null) {
                    listener.onError();
                }
            }

            @Override
            public void onResponse(String json, int i) {
                if(listener!=null&&json!=null) {
                    listener.onSuccess(json);
                }
            }
        });




    }

    public interface HttpListener{
        void onSuccess(String json);

        void onError();


    }


}
