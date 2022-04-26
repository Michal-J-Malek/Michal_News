package com.example.michalnews;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

public class DemoCollectionAdapter extends FragmentStateAdapter {

    private ArrayList<NewsDetails> news = new ArrayList<>();

    public DemoCollectionAdapter(Fragment fragment, ArrayList<NewsDetails> news) {
        super(fragment);
        this.news = news;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        // Return a NEW fragment instance in createFragment(int)
        Fragment fragment = new DemoObjectFragment();
        Bundle args = new Bundle();
        // Our object is just an integer :-P
        /*String tit = news.get(position).getTitle();
        String t = news.get(position).getTime();
        String a = news.get(position).getAuthor();
        String d = news.get(position).getDesc();
        String url = news.get(position).getUrl();
        String pic = news.get(position).getPicurl();

        args.putString(tit, "title");
        args.putString(t, "time");
        args.putString(a, "author");
        args.putString(d, "desc");
        args.putString(url, "url");
        args.putString(pic, "urlpic");*/

        args.putInt(DemoObjectFragment.ARG_OBJECT, position + 1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getItemCount() {
        return 10;
    }
}
