package com.example.edvblk.popularmoviesadnd.utils;

import com.example.edvblk.popularmoviesadnd.BuildConfig;
import com.example.edvblk.popularmoviesadnd.base.BaseFactory;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitFactory implements BaseFactory<Retrofit> {
    @Override
    public Retrofit create() {
        RxJava2CallAdapterFactory scheduler
                = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io());
        return new Retrofit.Builder()
                .baseUrl(BuildConfig.MOVIES_SERVICE_BASE_URL)
                .addCallAdapterFactory(scheduler)
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
