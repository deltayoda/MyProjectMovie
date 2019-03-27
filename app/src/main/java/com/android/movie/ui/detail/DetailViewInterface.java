package com.android.movie.ui.detail;

import com.android.movie.models.Cast.MovieCredits;
import com.android.movie.models.MovieDetail.MovieDetail;
import com.android.movie.models.NowPlaying.MovieResponse;
import com.android.movie.models.Reviews.MovieReviews;
import com.android.movie.models.SimilarMovies.SimilarMovies;

public interface DetailViewInterface {
    void showToast(String s);
   /* void showProgressBar();
    void hideProgressBar();*/
    void displayMovieDetail(MovieDetail movieDetail);
    void displayMovieCast(MovieCredits movieCredits);
    void displayMovieReviews(MovieReviews movieReviews);
    void displaySimilarMovies(SimilarMovies similarMovies);
    void displayError(String s);
}
