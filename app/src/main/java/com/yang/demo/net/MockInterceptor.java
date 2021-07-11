package com.yang.demo.net;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.Protocol;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okio.BufferedSink;

public class MockInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response;
        if (request.url().toString().contains("login")) {
            response = new Response.Builder()
                    .request(request)
                    .message("123")
                    .code(200)
                    .protocol(Protocol.HTTP_1_1)
                    .body(getResponseBody(request))
                    .build();
        } else {
            response = chain.proceed(request);
        }
        return response;
    }

    private ResponseBody getResponseBody(Request request) {
        RequestBody requestBody = request.body();
        ResponseBody responseBody = ResponseBody.create(MediaType.parse("application/json"), "success");
        return responseBody;
    }
}
