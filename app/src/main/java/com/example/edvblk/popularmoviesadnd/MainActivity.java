package com.example.edvblk.popularmoviesadnd;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    private Unbinder unbinder;
    private MoviesAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        unbinder = ButterKnife.bind(this);
        initRecycler();
        List<Movie> movies = Arrays.asList(
                new Movie("https://media3.giphy.com/media/3oKIPvR5gGtrCXMzYY/200w.webp"),
                new Movie("https://media3.giphy.com/media/xT1R9ChQiaw6XfDa48/200w.webp"));
        adapter.setItems(movies);
    }

    private void initRecycler() {
        adapter = new MoviesAdapter(new GlideImageLoader(this));
        recyclerView.setHasFixedSize(true);
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        unbinder.unbind();
        super.onDestroy();
    }
}