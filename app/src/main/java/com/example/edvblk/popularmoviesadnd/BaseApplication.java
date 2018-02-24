package com.example.edvblk.popularmoviesadnd;

import android.app.Application;
import android.content.Context;

import com.example.edvblk.popularmoviesadnd.utils.BaseImageLoader;
import com.example.edvblk.popularmoviesadnd.utils.GlideImageLoader;
import com.example.edvblk.popularmoviesadnd.utils.RetrofitFactory;

import retrofit2.Retrofit;

public class BaseApplication extends Application {
    private Retrofit retrofit;
    private BaseImageLoader glideImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        retrofit = new RetrofitFactory().create();
        glideImageLoader = new GlideImageLoader(this);
    }

    private Retrofit getRetrofit() {
        return retrofit;
    }

    public static Retrofit getRetrofit(Context context) {
        return getBaseApplication(context).getRetrofit();
    }

    private BaseImageLoader getImageLoarder() {
        return glideImageLoader;
    }

    private static BaseImageLoader getImageLoader(Context context) {
        return ((BaseApplication) context.getApplicationContext()).getImageLoarder();
    }

    private static BaseApplication getBaseApplication(Context context) {
        return ((BaseApplication) context.getApplicationContext());
    }
}
