package com.android.movie.ui.detail;

import android.util.Log;

import com.android.movie.KeyUtil;
import com.android.movie.models.Cast.MovieCredits;
import com.android.movie.models.MovieDetail.MovieDetail;
import com.android.movie.models.Reviews.MovieReviews;
import com.android.movie.models.SimilarMovies.SimilarMovies;
import com.android.movie.network.NetworkClient;
import com.android.movie.network.NetworkInterface;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class DetailPresenter implements DetailPresenterInterInface{
    DetailViewInterface dvi;
    private String TAG = "DetailPresenter";

    public DetailPresenter(DetailViewInterface dvi) {
        this.dvi = dvi;
    }

    //--------------------------- Movies Details----------------------------------------------

    public Observable<MovieDetail> getMovieDetailObservable(String movie_id){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMoviesBasedOnId(movie_id,KeyUtil.KEY_MOVIEDB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieDetail> getMovieDetailObserver(){
        return new DisposableObserver<MovieDetail>() {

            @Override
            public void onNext(@NonNull MovieDetail movieDetail) {
                Log.d(TAG,"OnNext"+movieDetail.getBackdropPath());
                dvi.displayMovieDetail(movieDetail);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                dvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                //dvi.hideProgressBar();
            }
        };
    }

    //---------------------------------------------------------------------------------------


    //--------------------------- Movies Credits----------------------------------------------

    public Observable<MovieCredits> getMovieCreditsObservable(String movie_id){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMoviesCreditsBasedOnId(movie_id,KeyUtil.KEY_MOVIEDB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieCredits> getMovieCreditsObserver(){
        return new DisposableObserver<MovieCredits>() {

            @Override
            public void onNext(@NonNull MovieCredits movieCredits) {
                Log.d(TAG,"OnNext"+movieCredits.getCast());
                dvi.displayMovieCast(movieCredits);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                dvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                //dvi.hideProgressBar();
            }
        };
    }
    //---------------------------------------------------------------------------------------


    //--------------------------- Movies Reviews----------------------------------------------

    public Observable<MovieReviews> getMovieReviewsObservable(String movie_id){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getMoviesReviewsBasedOnId(movie_id,KeyUtil.KEY_MOVIEDB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<MovieReviews> getMovieReviewObserver(){
        return new DisposableObserver<MovieReviews>() {

            @Override
            public void onNext(@NonNull MovieReviews movieReviews) {
                Log.d(TAG,"OnNext"+movieReviews.getId());
                dvi.displayMovieReviews(movieReviews);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                dvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                //dvi.hideProgressBar();
            }
        };
    }

    //---------------------------------------------------------------------------------------


    //---------------------------Similar Movies----------------------------------------------
    public Observable<SimilarMovies> getSimilarMoviesObservable(String movie_id){
        return NetworkClient.getRetrofit().create(NetworkInterface.class)
                .getSimilarMoviesBasedOnId(movie_id,KeyUtil.KEY_MOVIEDB)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

    public DisposableObserver<SimilarMovies> gettSimilarMoviesObserver(){
        return new DisposableObserver<SimilarMovies>() {

            @Override
            public void onNext(@NonNull SimilarMovies similarMovies) {
                Log.d(TAG,"OnNext"+similarMovies.getPage());
                dvi.displaySimilarMovies(similarMovies);
            }

            @Override
            public void onError(@NonNull Throwable e) {
                Log.d(TAG,"Error"+e);
                e.printStackTrace();
                dvi.displayError("Error fetching Movie Data");
            }

            @Override
            public void onComplete() {
                Log.d(TAG,"Completed");
                //dvi.hideProgressBar();
            }
        };
    }
    //--------------------------------------------------------------------------------------


    @Override
    public void getMovieDetail(String movie_id) {
        getMovieDetailObservable(movie_id).subscribeWith(getMovieDetailObserver());
    }

    @Override
    public void getMovieCredits(String movie_id) {
        getMovieCreditsObservable(movie_id).subscribeWith(getMovieCreditsObserver());
    }

    @Override
    public void getMovieReviews(String movie_id) {
        getMovieReviewsObservable(movie_id).subscribeWith(getMovieReviewObserver());
    }

    @Override
    public void getSimilarMovies(String movie_id) {
        getSimilarMoviesObservable(movie_id).subscribeWith(gettSimilarMoviesObserver());
    }

}
