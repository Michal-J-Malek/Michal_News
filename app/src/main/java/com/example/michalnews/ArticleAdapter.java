package com.example.michalnews;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.MyViewHolder>{
    private ArrayList<NewsDetails> news = new ArrayList<>();

    public ArticleAdapter(ArrayList<NewsDetails> array) {
        this.news = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.articles, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        //holder.tvName.setText(arrayList.get(position));
        holder.title.setText(news.get(position).getTitle());
        holder.date.setText(news.get(position).getTime());
        holder.author.setText(news.get(position).getAuthor());
        holder.desc.setText(news.get(position).getDesc());
    }

    @Override
    public int getItemCount() { return news.size();}

    public class MyViewHolder extends RecyclerView.ViewHolder {
        //TextView tvName;
         TextView title, date, author, desc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            //tvName = itemView.findViewById(R.id.tvName);
            title = itemView.findViewById(R.id.art_title);
            date = itemView.findViewById(R.id.art_date);
            author = itemView.findViewById(R.id.art_author);
            desc = itemView.findViewById(R.id.art_desc);
        }
    }
}
