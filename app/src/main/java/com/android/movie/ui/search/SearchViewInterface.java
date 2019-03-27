package com.android.movie.ui.search;

import com.android.movie.models.NowPlaying.MovieResponse;

/**
 * Created by bhavesh on 27/12/17.
 */

public interface SearchViewInterface {

    void showToast(String str);
    void displayResult(MovieResponse movieResponse);
    void displayError(String s);
    void showProgressBar();
    void hideProgressBar();
}
