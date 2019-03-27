package com.android.movie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.movie.R;
import com.android.movie.models.SimilarMovies.Result;
import com.bumptech.glide.Glide;

import java.util.List;

public class SimilarMoviesAdapter extends RecyclerView.Adapter<SimilarMoviesAdapter.SimilarMoviesViewHolder> {
    List<Result> similarList;
    Context context;

    public SimilarMoviesAdapter(List<Result> similarList, Context context) {
        this.similarList = similarList;
        this.context = context;
    }
    @Override
    public SimilarMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_similar_movie,parent,false);
        SimilarMoviesViewHolder smv = new SimilarMoviesViewHolder(v);
        return smv;
    }

    @Override
    public void onBindViewHolder(SimilarMoviesViewHolder holder, int position) {
        holder.tvMovieName.setText(similarList.get(position).getTitle());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+similarList.get(position).getPosterPath()).into(holder.ivMovie);
    }

    @Override
    public int getItemCount() {
        return similarList.size();
    }

    public class SimilarMoviesViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieName,tvMovieGenre;
        ImageView ivMovie;
        public SimilarMoviesViewHolder(View itemView) {
            super(itemView);
            tvMovieName = (TextView) itemView.findViewById(R.id.tvMovieName);
            tvMovieGenre = (TextView) itemView.findViewById(R.id.tvMovieGenre);
            ivMovie = (ImageView) itemView.findViewById(R.id.ivMovie);
        }
    }
}
