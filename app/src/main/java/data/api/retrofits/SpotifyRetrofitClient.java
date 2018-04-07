package data.api.retrofits;

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import data.api.Constant;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;


import retrofit2.converter.gson.GsonConverterFactory;

public abstract class SpotifyRetrofitClient {

  private SpotifyRetrofitService spotifyRetrofitService;

  public SpotifyRetrofitClient() {
    initRetrofit();
  }

  private void initRetrofit() {
    Retrofit retrofit = retrofitBuilder();
    spotifyRetrofitService = retrofit.create(getSpotifyServiceClass());
  }

  private Retrofit retrofitBuilder() {
    return new Retrofit.Builder().baseUrl(Constant.SPOTIFY_API)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .client(getOkHttpClient())
        .build();
  }

  private OkHttpClient getOkHttpClient() {
    OkHttpClient.Builder client = new OkHttpClient.Builder();
    ApiInterceptor apiInterceptor = new ApiInterceptor();
    client.addInterceptor(apiInterceptor);
    return client.build();
  }


  private Class<SpotifyRetrofitService> getSpotifyServiceClass() {
    return SpotifyRetrofitService.class;
  }


  protected SpotifyRetrofitService getSpotifyService() {
    return spotifyRetrofitService;
  }
}

