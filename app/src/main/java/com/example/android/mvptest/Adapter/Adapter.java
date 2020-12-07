package com.example.android.mvptest.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.example.android.mvptest.MVP.DetailsActivity;
import com.example.android.mvptest.Model.Result;
import com.example.android.mvptest.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    Context context;

    public void setList(ArrayList<Result> list) {
        this.list = list;
    }

    ArrayList<Result> list;

    public Adapter(Context context, ArrayList<Result> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_movies, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Result user = list.get(position);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("example", user);
                intent.putExtra("test", 0);
                context.startActivity(intent);
            }
        });


        holder.user_id.setText(user.getTitle());
        holder.user_name.setText(user.getMediaType());
        holder.name.setText(user.getOriginalTitle());
        holder.user_email.setText(user.getTitle());
        holder.website.setText(user.getTitle());

    }

    @Override
    public int getItemCount() {
        return (list != null ? list.size() : 0);
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.constraintMain)
        ConstraintLayout constraintLayout;
        @BindView(R.id.movie_id)
        TextView user_id;
        @BindView(R.id.description_movie)
        TextView user_name;
        @BindView(R.id.genre)
        TextView name;
        @BindView(R.id.user_email)
        TextView user_email;
        @BindView(R.id.website)
        TextView website;
        public ViewHolder(@NonNull View itemView) {

            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
