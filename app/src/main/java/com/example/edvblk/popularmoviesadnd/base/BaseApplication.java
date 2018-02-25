package com.example.edvblk.popularmoviesadnd.base;

import android.app.Application;
import android.content.Context;

import com.example.edvblk.popularmoviesadnd.utils.RetrofitFactory;
import com.example.edvblk.popularmoviesadnd.utils.image.GlideImageLoader;

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

    public static BaseImageLoader getImageLoader(Context context) {
        return ((BaseApplication) context.getApplicationContext()).getImageLoarder();
    }

    private static BaseApplication getBaseApplication(Context context) {
        return ((BaseApplication) context.getApplicationContext());
    }
}
