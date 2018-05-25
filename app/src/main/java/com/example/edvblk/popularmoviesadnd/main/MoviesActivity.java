package com.example.edvblk.popularmoviesadnd.main;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.edvblk.popularmoviesadnd.R;
import com.example.edvblk.popularmoviesadnd.base.BaseActivity;
import com.example.edvblk.popularmoviesadnd.details.MovieDetailsActivity;
import com.example.edvblk.popularmoviesadnd.utils.ErrorProvider;
import com.example.edvblk.popularmoviesadnd.utils.ErrorProviderImpl;
import com.example.edvblk.popularmoviesadnd.utils.image.DefaultImageUrlProvider;
import com.example.edvblk.popularmoviesadnd.utils.image.GlideImageLoader;

import java.util.List;

import butterknife.BindView;

import static com.example.edvblk.popularmoviesadnd.main.MainContract.Presenter;

public class MoviesActivity extends BaseActivity implements MainContract.View {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    private MoviesAdapter adapter;
    private Presenter presenter;
    private ErrorProvider errorProvider;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFields();
        setSupportActionBar(toolbar);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menu_item_highest_rated) {
            presenter.onHighestRatedClicked();
        } else if (item.getItemId() == R.id.menu_item_most_popular) {
            presenter.onPopularClicked();
        }
        return super.onOptionsItemSelected(item);
    }

    private void initAdapter() {
        int widthPixels = getResources().getDisplayMetrics().widthPixels;
        DefaultImageUrlProvider urlProvider = new DefaultImageUrlProvider(widthPixels);
        adapter = new MoviesAdapter(
                new GlideImageLoader(this),
                urlProvider,
                item -> presenter.onItemSelected(item));
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
    public void openDetailsActivity(Movie item) {
        MovieDetailsActivity.start(this, item);
    }

    @Override
    protected void onDestroy() {
        presenter.dropView();
        super.onDestroy();
    }
}