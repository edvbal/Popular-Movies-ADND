package com.example.edvblk.popularmoviesadnd;

import com.example.edvblk.popularmoviesadnd.base.BasePresenter;

public interface MainContract {
    interface View {
    }

    interface Presenter extends BasePresenter<View> {
        void onCreate();
    }

    interface Model {
    }
}
