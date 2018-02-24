package com.example.edvblk.popularmoviesadnd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.example.edvblk.popularmoviesadnd.base.BaseApplication;

import java.util.Arrays;
import java.util.List;

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
        unbinder = ButterKnife.bind(this);
        presenter = new MoviesPresenter();
        presenter.takeView(this);
        initRecycler();
        setFakeItems();
    }

    private void setFakeItems() {
        List<Movie> movies = Arrays.asList(
                new Movie("https://media3.giphy.com/media/3oKIPvR5gGtrCXMzYY/200w.webp"),
                new Movie("https://media3.giphy.com/media/xT1R9ChQiaw6XfDa48/200w.webp"));
        adapter.setItems(movies);
    }

    private void initRecycler() {
        adapter = new MoviesAdapter(BaseApplication.getImageLoader(this));
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