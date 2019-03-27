package com.android.movie.network;


import com.android.movie.models.Cast.MovieCredits;
import com.android.movie.models.MovieDetail.MovieDetail;
import com.android.movie.models.NowPlaying.MovieResponse;
import com.android.movie.models.Reviews.MovieReviews;
import com.android.movie.models.SimilarMovies.SimilarMovies;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by bhavesh on 20/03/19.
 */

public interface NetworkInterface {

    @GET("movie/now_playing")
    Observable<MovieResponse> getMovies(@Query("api_key") String api_key);

    @GET("search/movie")
    Observable<MovieResponse> getMoviesBasedOnQuery(@Query("api_key") String api_key, @Query("query") String q);

    @GET("movie/{movie_id}")
    Observable<MovieDetail> getMoviesBasedOnId(@Path("movie_id") String movieId,@Query("api_key") String api_key);

    @GET("movie/{movie_id}/reviews")
    Observable<MovieReviews> getMoviesReviewsBasedOnId(@Path("movie_id") String movieId,@Query("api_key") String api_key);

    @GET("movie/{movie_id}/credits")
    Observable<MovieCredits> getMoviesCreditsBasedOnId(@Path("movie_id") String movieId,@Query("api_key") String api_key);

    @GET("movie/{movie_id}/similar")
    Observable<SimilarMovies> getSimilarMoviesBasedOnId(@Path("movie_id") String movieId,@Query("api_key") String api_key);
}
