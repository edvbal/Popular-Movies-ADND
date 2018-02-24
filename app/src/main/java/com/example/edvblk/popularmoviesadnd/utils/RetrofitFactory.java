package com.example.edvblk.popularmoviesadnd.utils;

import com.example.edvblk.popularmoviesadnd.BuildConfig;
import com.example.edvblk.popularmoviesadnd.base.BaseFactory;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory implements BaseFactory<Retrofit> {
    @Override
    public Retrofit create() {
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MOVIES_SERVICE_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideHttpClient())
                .build();
    }

    private OkHttpClient provideHttpClient() {
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();
    }
}
