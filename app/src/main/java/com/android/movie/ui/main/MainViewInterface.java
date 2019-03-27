package com.android.movie.ui.main;

import com.android.movie.models.NowPlaying.MovieResponse;

/**
 * Created by bhavesh on 20/03/19.
 */

public interface MainViewInterface {

    void showToast(String s);
    void showProgressBar();
    void hideProgressBar();
    void displayMovies(MovieResponse movieResponse);
    void displayError(String s);
}
