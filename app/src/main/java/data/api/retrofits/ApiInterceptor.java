package data.api.retrofits;

import android.content.Context;

import java.io.IOException;

import application.SpotifyApplication;
import data.SpotifyPreferences;
import data.api.Constant;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;




class ApiInterceptor implements Interceptor {
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        String acessToken = "Bearer "+SpotifyApplication.getInstance().getAccessToken();
        Request request = original.newBuilder().header(Constant.API_KEY, acessToken)
            .method(original.method(), original.body()).build();
        return chain.proceed(request);
    }
}