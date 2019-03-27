package com.android.movie.ui.detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.movie.R;
import com.android.movie.adapters.MovieCastAdapter;
import com.android.movie.adapters.MovieCrewAdapter;
import com.android.movie.adapters.MoviesAdapter;
import com.android.movie.adapters.ReviewsAdapter;
import com.android.movie.adapters.SimilarMoviesAdapter;
import com.android.movie.models.Cast.MovieCredits;
import com.android.movie.models.MovieDetail.MovieDetail;
import com.android.movie.models.NowPlaying.MovieResponse;
import com.android.movie.models.Reviews.MovieReviews;
import com.android.movie.models.SimilarMovies.SimilarMovies;
import com.android.movie.ui.main.MainActivity;
import com.android.movie.ui.search.SearchActivity;
import com.bumptech.glide.Glide;

import butterknife.BindView;
import butterknife.ButterKnife;

public class  DetailActivity extends AppCompatActivity implements DetailViewInterface {




    @BindView(R.id.collapsing_toolbar)
    CollapsingToolbarLayout collapsing_toolbar;

    @BindView(R.id.tvOverView)
    TextView tvOverView;

    @BindView(R.id.ivMovie)
    ImageView ivMovie;

    @BindView(R.id.rvCast)
    RecyclerView rvCast;

    @BindView(R.id.rvCrew)
    RecyclerView rvCrew;

    @BindView(R.id.rvReviews)
    RecyclerView rvReviews;

    @BindView(R.id.rvSimilarMovies)
    RecyclerView rvSimilarMovies;


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private String TAG = "DetailActivity";

    MovieCastAdapter movieCastAdapter;
    MovieCrewAdapter movieCrewAdapter;
    ReviewsAdapter reviewsAdapter;
    SimilarMoviesAdapter similarMoviesAdapter;

    String movie_id;

    DetailPresenter detailPresenter;
    //RecyclerView rvMovies;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        //rvMovies = (RecyclerView) findViewById(R.id.rvMovies);

        getMovieExtra();
        setupMVP();
        setupViews();
        getMovieDetails(movie_id);
    }

    private void getMovieExtra() {
        if (getIntent() != null)
        {
            movie_id = getIntent().getExtras().getString("movie_id");
        }
    }

    private void setupMVP() {
        detailPresenter = new DetailPresenter(this);
    }

    private void setupViews(){
        //Added in Part 2 of the series
        setSupportActionBar(toolbar);
        rvCast.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvCrew.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvReviews.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rvSimilarMovies.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    private void getMovieDetails(String movie_id) {
        detailPresenter.getMovieDetail(movie_id);
        detailPresenter.getMovieCredits(movie_id);
        detailPresenter.getMovieReviews(movie_id);
        detailPresenter.getSimilarMovies(movie_id);
    }

    @Override
    public void showToast(String str) {
        Toast.makeText(DetailActivity.this,str,Toast.LENGTH_LONG).show();
    }

    @Override
    public void displayMovieDetail(MovieDetail movieDetail) {

        if (movieDetail != null)
        {
            // Set collapsing tool bar title.
            collapsing_toolbar.setTitle(movieDetail.getTitle());
            collapsing_toolbar.setExpandedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);
            collapsing_toolbar.setCollapsedTitleTextAppearance(R.style.collapsingToolbarLayoutTitleColor);
            Glide.with(this).load("https://image.tmdb.org/t/p/w500/"+movieDetail.getPosterPath()).into(ivMovie);
            tvOverView.setText(movieDetail.getOverview());
        }else {
            Log.d(TAG,"Movies response null");
        }
    }

    @Override
    public void displayMovieCast(MovieCredits movieCredits) {
        if(movieCredits!=null) {
            if (movieCredits.getCast() != null && movieCredits.getCast().size()>0)
            {
                movieCastAdapter = new MovieCastAdapter(movieCredits.getCast(), DetailActivity.this);
                rvCast.setAdapter(movieCastAdapter);

            }

            if (movieCredits.getCrew() != null && movieCredits.getCrew().size()>0)
            {
                movieCrewAdapter = new MovieCrewAdapter(movieCredits.getCrew(), DetailActivity.this);
                rvCrew.setAdapter(movieCrewAdapter);
            }

        }else{
            Log.d(TAG,"Movies response null");
        }
    }

    @Override
    public void displayMovieReviews(MovieReviews movieReviews) {
        if(movieReviews!=null) {
            reviewsAdapter = new ReviewsAdapter(movieReviews.getResults(), DetailActivity.this);
            rvReviews.setAdapter(reviewsAdapter);
        }else{
            Log.d(TAG,"Response null");
        }
    }

    @Override
    public void displaySimilarMovies(SimilarMovies similarMovies) {
        if(similarMovies!=null) {
            similarMoviesAdapter = new SimilarMoviesAdapter(similarMovies.getResults(), DetailActivity.this);
            rvSimilarMovies.setAdapter(similarMoviesAdapter);
        }else{
            Log.d(TAG,"Response null");
        }
    }

    @Override
    public void displayError(String e) {
        showToast(e);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    //Added in Part 2 of the series
}
