package com.das.jospablo.littewebservice.webservice;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by JoséPablo on 24/06/16.
 * Retrofit Service, initiate interface.
 */
public class RetroFitService {

    public static final String BASE_URL = "https://api.github.com/";
    private static Retrofit retrofit = null;
    private static HttpLoggingInterceptor logging;

    private static OkHttpClient okHttpClient;

    public static Retrofit getClient() {
        if (retrofit == null) {

            logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);

            okHttpClient = new OkHttpClient.Builder()
                    .readTimeout(30, TimeUnit.SECONDS)
                    .connectTimeout(30, TimeUnit.SECONDS)
                    .addInterceptor(logging)
                    .build();

            retrofit = new Retrofit.Builder()
                    .client(okHttpClient)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        
        return retrofit;
    }

    public static GitHubApi getInstance() {
        return getClient().create(GitHubApi.class);
    }

}
