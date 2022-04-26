package com.example.michalnews;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.example.michalnews.databinding.ArticlesBinding;
import com.example.michalnews.databinding.ContentMainBinding;

import java.util.ArrayList;
import java.util.List;

public class ScreenSlidePageFragment extends Fragment {

    ArticleAdapter adapter;
    ViewPager2 rv;
    private ContentMainBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        binding = ContentMainBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //return (ViewGroup) inflater.inflate(R.layout.content_main, container, false);
        rv = root.findViewById(R.id.viewpage);
        //rv.setHasFixedSize(true);

        Log.v("ScreenSlide", "here");
        MainActivity main = new MainActivity();
        adapter = new ArticleAdapter(main.theNews);
        rv.setAdapter(adapter);
        return root;
    }
}
