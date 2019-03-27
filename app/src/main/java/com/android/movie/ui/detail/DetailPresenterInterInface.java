package com.android.movie.ui.detail;

public interface DetailPresenterInterInface {
    void getMovieDetail(String movie_id);
    void getMovieCredits(String movie_id);
    void getMovieReviews(String movie_id);
    void getSimilarMovies(String movie_id);
}
