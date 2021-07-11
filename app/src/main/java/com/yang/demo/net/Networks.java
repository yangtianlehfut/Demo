package com.yang.demo.net;

import com.google.gson.Gson;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Networks {
    private static volatile Networks instance;
    private Api api;
    private Networks() {
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new MockInterceptor())
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl("http://fundgz.1234567.com.cn/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        api = retrofit.create(Api.class);
    }

    public static Networks getInstance() {
        if (instance == null) {
            synchronized (Networks.class) {
                if (instance == null) {
                    instance = new Networks();
                }
            }
        }

        return instance;
    }

    public void login(String phoneNum, String pwd, Callback<ResponseBody> callback) {
        Call call = api.login(phoneNum, pwd);
        call.enqueue(callback);
    }
}
