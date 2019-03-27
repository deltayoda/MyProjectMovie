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
import com.android.movie.models.Cast.Crew;
import com.bumptech.glide.Glide;

import java.util.List;

public class MovieCrewAdapter extends RecyclerView.Adapter<MovieCrewAdapter.MovieCrewViewHolder> {

    List<Crew> crewList;
    Context context;

    public MovieCrewAdapter(List<Crew> crewList, Context context) {
        this.crewList = crewList;
        this.context = context;
    }

    @Override
    public MovieCrewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_crew,parent,false);
        MovieCrewViewHolder mcv = new MovieCrewViewHolder(v);
        return mcv;
    }

    @Override
    public void onBindViewHolder(MovieCrewViewHolder holder, int position) {
        holder.tvCrewName.setText(crewList.get(position).getName());
        holder.tvCrewProfile.setText(crewList.get(position).getDepartment());
        Glide.with(context).load("https://image.tmdb.org/t/p/w500/"+crewList.get(position).getProfilePath()).into(holder.ivCrew);
    }

    @Override
    public int getItemCount(){return crewList.size();
    }

    public class MovieCrewViewHolder extends RecyclerView.ViewHolder {
        TextView tvCrewName,tvCrewProfile;
        ImageView ivCrew;

        public MovieCrewViewHolder(View itemView) {
            super(itemView);
            tvCrewName = (TextView) itemView.findViewById(R.id.tvCrewName);
            tvCrewProfile = (TextView) itemView.findViewById(R.id.tvCrewProfile);
            ivCrew = (ImageView) itemView.findViewById(R.id.ivCrew);
        }
    }
}
