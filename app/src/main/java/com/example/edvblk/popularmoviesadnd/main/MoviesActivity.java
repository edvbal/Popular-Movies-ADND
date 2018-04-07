package com.example.edvblk.popularmoviesadnd.main;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.example.edvblk.popularmoviesadnd.MainContract;
import com.example.edvblk.popularmoviesadnd.Movie;
import com.example.edvblk.popularmoviesadnd.R;
import com.example.edvblk.popularmoviesadnd.base.BaseActivity;
import com.example.edvblk.popularmoviesadnd.main.MoviesAdapter;
import com.example.edvblk.popularmoviesadnd.main.MoviesPresenterFactory;
import com.example.edvblk.popularmoviesadnd.utils.ErrorProvider;
import com.example.edvblk.popularmoviesadnd.utils.ErrorProviderImpl;
import com.example.edvblk.popularmoviesadnd.utils.image.DefaultImageUrlProvider;
import com.example.edvblk.popularmoviesadnd.utils.image.GlideImageLoader;

import java.util.List;

import butterknife.BindView;

import static com.example.edvblk.popularmoviesadnd.MainContract.Presenter;

public class MoviesActivity extends BaseActivity implements MainContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private MoviesAdapter adapter;
    private Presenter presenter;
    private ErrorProvider errorProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFields();
        presenter.onCreate();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    private void initFields() {
        errorProvider = new ErrorProviderImpl(recyclerView);
        initPresenter();
        initAdapter();
        initRecycler();
    }

    private void initPresenter() {
        presenter = new MoviesPresenterFactory().create(this);
        presenter.takeView(this);
    }

    private void initAdapter() {
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        DefaultImageUrlProvider urlProvider = new DefaultImageUrlProvider(widthPixels);
        adapter = new MoviesAdapter(new GlideImageLoader(this), urlProvider);
    }

    private void initRecycler() {
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void populateView(List<Movie> movies) {
        adapter.setItems(movies);
    }

    @Override
    public void showError(String errorMessage) {
        errorProvider.showError(errorMessage);
    }


    @Override
    protected void onDestroy() {
        presenter.dropView();
        super.onDestroy();
    }
}