package com.yang.demo.net;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface Api {
    @FormUrlEncoded
    @POST("/test/login")
    Call<ResponseBody> login(@Field("phoneNum") String phoneNum, @Field("pwd") String pwd);
}
