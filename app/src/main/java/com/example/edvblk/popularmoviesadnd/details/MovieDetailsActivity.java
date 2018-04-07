package com.example.edvblk.popularmoviesadnd.details;

import android.content.Context;
import android.content.Intent;

import com.example.edvblk.popularmoviesadnd.R;
import com.example.edvblk.popularmoviesadnd.base.BaseActivity;

public class MovieDetailsActivity extends BaseActivity {
    public static void start(Context context) {
        Intent starter = new Intent(context, MovieDetailsActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_details;
    }
}
