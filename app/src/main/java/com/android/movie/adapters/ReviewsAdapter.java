package com.android.movie.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.movie.R;
import com.android.movie.models.Cast.Crew;
import com.android.movie.models.Reviews.Result;

import java.util.List;

public class ReviewsAdapter extends RecyclerView.Adapter<ReviewsAdapter.ReviewsViewHolder> {

    List<Result> reviewList;
    Context context;

    public ReviewsAdapter(List<Result> reviewList, Context context) {
        this.reviewList = reviewList;
        this.context = context;
    }

    @Override
    public ReviewsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.row_review,parent,false);
        ReviewsViewHolder rv = new ReviewsViewHolder(v);
        return rv;
    }

    @Override
    public void onBindViewHolder(ReviewsViewHolder holder, int position) {
        holder.tvAuthor.setText(reviewList.get(position).getAuthor());
        holder.tvContent.setText(reviewList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ReviewsViewHolder extends RecyclerView.ViewHolder {
        TextView tvAuthor,tvContent;

        public ReviewsViewHolder(View itemView) {
            super(itemView);
            tvAuthor = (TextView) itemView.findViewById(R.id.tvAuthor);
            tvContent = (TextView) itemView.findViewById(R.id.tvContent);
        }
    }
}
