package com.example.android.mvptest.Adapter;

import android.annotation.SuppressLint;
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
import com.example.android.mvptest.Model.ResultSecond;
import com.example.android.mvptest.R;

import java.util.List;

public class AdapterAllMain extends RecyclerView.Adapter<AdapterAllMain.ViewHolder> {

    List<ResultSecond> dataList;
    Context context;

    public void setDataList(List<ResultSecond> dataList) {
        this.dataList = dataList;
        notifyDataSetChanged();
    }



    public AdapterAllMain(Context context, List<ResultSecond> dataList) {
        this.context = context;
        this.dataList = dataList;

    }


    @NonNull
    @Override
    public AdapterAllMain.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        View view;
        LayoutInflater inflater = LayoutInflater.from(context);
        view = inflater.inflate(R.layout.layout_movies, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull final AdapterAllMain.ViewHolder holder, final int position) {
        final ResultSecond user = dataList.get(position);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, DetailsActivity.class);
                intent.putExtra("example", user);
                intent.putExtra("test", 1);
                context.startActivity(intent);
            }
        });


        holder.user_id.setText(user.getTitle());
        holder.user_name.setText(user.getOriginalLanguage());
        holder.name.setText(user.getOriginalTitle());
        holder.user_email.setText(user.getTitle());
        holder.website.setText(user.getTitle());

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        ConstraintLayout constraintLayout;

        TextView user_id;

        TextView user_name;

        TextView name;

        TextView user_email;

        TextView website;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            //initializing
            constraintLayout = itemView.findViewById(R.id.constraintMain);
            user_id = itemView.findViewById(R.id.movie_id);
            user_name = itemView.findViewById(R.id.description_movie);
            name = itemView.findViewById(R.id.genre);
            user_email = itemView.findViewById(R.id.user_email);
            website = itemView.findViewById(R.id.website);
        }


    }
}
