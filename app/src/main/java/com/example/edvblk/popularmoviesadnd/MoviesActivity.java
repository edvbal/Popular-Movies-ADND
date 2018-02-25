package com.example.edvblk.popularmoviesadnd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.edvblk.popularmoviesadnd.base.BaseApplication;
import com.example.edvblk.popularmoviesadnd.base.BaseImageLoader;
import com.example.edvblk.popularmoviesadnd.utils.MoviesService;
import com.example.edvblk.popularmoviesadnd.utils.image.DefaultImageUrlProvider;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

import static com.example.edvblk.popularmoviesadnd.MainContract.Presenter;
import static com.example.edvblk.popularmoviesadnd.MainContract.View;

public class MoviesActivity extends AppCompatActivity implements View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private MoviesAdapter adapter;
    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFields();
        presenter.onCreate();
    }

    private void initFields() {
        unbinder = ButterKnife.bind(this);
        initPresenter();
        initAdapter();
        initRecycler();
    }

    private void initPresenter() {
        MoviesService service = BaseApplication.getRetrofit(this).create(MoviesService.class);
        MoviesModel model = new MoviesModel(service);
        presenter = new MoviesPresenter(model);
        presenter.takeView(this);
    }

    private void initAdapter() {
        BaseImageLoader imageLoader = BaseApplication.getImageLoader(this);
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        DefaultImageUrlProvider urlProvider = new DefaultImageUrlProvider(widthPixels);
        adapter = new MoviesAdapter(imageLoader, urlProvider);
    }

    private void initRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        presenter.dropView();
        unbinder.unbind();
        super.onDestroy();
    }
}