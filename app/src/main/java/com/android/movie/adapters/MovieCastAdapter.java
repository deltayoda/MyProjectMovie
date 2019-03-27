package com.android.movie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.movie.R;
import com.android.movie.models.Cast.Cast;
import com.android.movie.models.NowPlaying.Result;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieCastAdapter extends RecyclerView.Adapter<MovieCastAdapter.MovieCastViewHolder> {

    List<Cast> castList;
    Context context;

    public MovieCastAdapter(List<Cast> castList, Context context) {
        this.castList = castList;
        this.context = context;
    }

    @Override
    public MovieCastViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_cast,parent,false);
        MovieCastViewHolder mcv = new MovieCastViewHolder(v);
        return mcv;
    }

    @Override
    public void onBindViewHolder(MovieCastViewHolder holder, int position) {
        holder.tvCastName.setText(castList.get(position).getName());
        holder.tvCastAs.setText(castList.get(position).getCharacter());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+castList.get(position).getProfilePath()).into(holder.ivCast);
    }

    @Override
    public int getItemCount() {
        return castList.size();
    }

    public class MovieCastViewHolder extends RecyclerView.ViewHolder {

        TextView tvCastName,tvCastAs;
        ImageView ivCast;

        public MovieCastViewHolder(View itemView) {
            super(itemView);
            tvCastName = (TextView) itemView.findViewById(R.id.tvCastName);
            tvCastAs = (TextView) itemView.findViewById(R.id.tvCastAs);
            ivCast = (ImageView) itemView.findViewById(R.id.ivCast);
        }
    }
}
