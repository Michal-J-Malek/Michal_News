package com.example.michalnews;

import static java.lang.String.valueOf;

import android.annotation.SuppressLint;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.michalnews.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;

import com.example.michalnews.MainActivity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String TAG = "MainActivity";
    private String selectedTopic;
    private ArrayList<SourceDetails> newSources = new ArrayList<SourceDetails>();
    ArrayList<NewsDetails> theNews = new ArrayList<NewsDetails>();
    private ArrayList<String> topics = new ArrayList<>();
    private ArrayList<String> colors = new ArrayList<>();
    private String apiKey = "81c5186b3bd8410d89153ef6155b06dc";
    private ActivityMainBinding binding;
    private AppBarConfiguration mAppBarConfiguration;
    private ActionBarDrawerToggle toggle;
    private boolean click = false;
    ScreenSlidePageFragment viewFrag = new ScreenSlidePageFragment();
    private MenuItem menuitem;
    DrawerLayout drawer;
    NavigationView navigationView;
    test ok = new test();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        click = false;
        selectedTopic = "all";
        super.onCreate(savedInstanceState);

        queryNewSource(apiKey);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        setSupportActionBar(binding.appBarMain.toolbar);

        drawer = binding.drawerLayout;
        /*

        final Menu menu = navigationView.getMenu();
        for (int i = 1; i <= 3; i++) {
            menu.add("Runtime item "+ i);
        }



        To add the Item programmatically, we can get a Menu object using getMenu() method of NavigationView and then we can add Items into the navigation drawer using that Menu object.

        final Menu menu = navigationView.getMenu();
        for (int i = 1; i <= 3; i++) {
           menu.add("Runtime item "+ i);
        }

        Using SubMenu, we can add a subsection and Items into it.

        // adding a section and items into it
        final SubMenu subMenu = menu.addSubMenu("SubMenu Title");
        for (int i = 1; i <= 2; i++) {
           subMenu.add("SubMenu Item " + i);
        }

        for more details Check TechnoTalkative.

        EDIT: If you want to interact with the menu, use
        menu.add(0, itemId, 0, title); and then

         public boolean onNavigationItemSelected(MenuItem item) {
                // Handle navigation view item clicks here.
                int id = item.getItemId();

        id will give you assigned itemId



        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow)
                .setOpenableLayout(drawer)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_content_main);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);*/
        //Toolbar toolbar = this;





        drawer = binding.drawerLayout;
        navigationView = findViewById(R.id.nav_view);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);


        toggle = new ActionBarDrawerToggle
                (this, drawer, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {

        };
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);
    }

    public void populateDrawer(Menu benu, ArrayList<SourceDetails> arrayList){
        benu.clear();
        for (int i = 0; i < arrayList.size(); i++) {
            String n = arrayList.get(i).getName();
            //String id = newSources.get(i).getNewsID();
            String t = arrayList.get(i).getTopic();
            benu.add(0, 100+i, 1 + i, n);
            MenuItem draw = benu.getItem(i);
            for (int j = 0; j < topics.size(); j++) {
                if (topics.get(j).equals(t)) {
                    String col = colors.get(j);
                    if (col.equals("green")) {
                        SpannableString s = new SpannableString(n);
                        s.setSpan(new ForegroundColorSpan(Color.rgb(8, 105, 8)), 0, s.length(), 0);
                        //s.setSpan(bss,0, s.length(),0);
                        draw.setTitle(s);
                    }
                    if (col.equals("scarlet")) {
                        SpannableString s = new SpannableString(n);
                        s.setSpan(new ForegroundColorSpan(Color.rgb(218, 75, 15)), 0, s.length(), 0);
                        //s.setSpan(bss,0, s.length(),0);
                        draw.setTitle(s);
                    }
                    if (col.equals("gold")) {
                        SpannableString s = new SpannableString(n);
                        s.setSpan(new ForegroundColorSpan(Color.rgb(189, 171, 6)), 0, s.length(), 0);
                        //s.setSpan(bss,0, s.length(),0);
                        draw.setTitle(s);
                    }
                    if (col.equals("teal")) {
                        SpannableString s = new SpannableString(n);
                        s.setSpan(new ForegroundColorSpan(Color.rgb(31, 165, 152)), 0, s.length(), 0);
                        //s.setSpan(bss,0, s.length(),0);
                        draw.setTitle(s);
                    }
                    if (col.equals("blue")) {
                        SpannableString s = new SpannableString(n);
                        s.setSpan(new ForegroundColorSpan(Color.rgb(21, 162, 191)), 0, s.length(), 0);
                        //s.setSpan(bss,0, s.length(),0);
                        draw.setTitle(s);
                    }
                    if (col.equals("gray")) {
                        SpannableString s = new SpannableString(n);
                        s.setSpan(new ForegroundColorSpan(Color.GRAY), 0, s.length(), 0);
                        //s.setSpan(bss,0, s.length(),0);
                        draw.setTitle(s);
                    }
                    if (col.equals("magenta")) {
                        SpannableString s = new SpannableString(n);
                        s.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, s.length(), 0);
                        //s.setSpan(bss,0, s.length(),0);
                        draw.setTitle(s);
                    }
                    if (col.equals("darkgray")) {
                        SpannableString s = new SpannableString(n);
                        s.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, s.length(), 0);
                        //s.setSpan(bss,0, s.length(),0);
                        draw.setTitle(s);
                    }
                }

            }
        }
    }

    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {

        menu.clear();

        Collections.sort(topics);
        final StyleSpan bss = new StyleSpan(android.graphics.Typeface.BOLD);

        for(int i = 0; i<topics.size();i++){
            menu.add( 0, i, 1+i,topics.get(i));
            MenuItem item = menu.getItem(i);
            if(i == 0) {
                SpannableString s = new SpannableString(String.valueOf(item.getTitle()));
                s.setSpan(new ForegroundColorSpan(Color.rgb(8, 105, 8)), 0, s.length(), 0);
                //s.setSpan(bss,0, s.length(),0);
                item.setTitle(s);
                colors.add("green");
            }
            if(i == 1) {
                SpannableString s = new SpannableString(String.valueOf(item.getTitle()));
                s.setSpan(new ForegroundColorSpan(Color.rgb(218, 75, 15)), 0, s.length(), 0);
                //s.setSpan(bss,0, s.length(),0);
                item.setTitle(s);
                colors.add("scarlet");
            }
            if(i == 2) {
                SpannableString s = new SpannableString(String.valueOf(item.getTitle()));
                s.setSpan(new ForegroundColorSpan(Color.rgb(189, 171, 6)), 0, s.length(), 0);
                //s.setSpan(bss,0, s.length(),0);
                item.setTitle(s);
                colors.add("gold");
            }
            if(i == 3) {
                SpannableString s = new SpannableString(String.valueOf(item.getTitle()));
                s.setSpan(new ForegroundColorSpan(Color.rgb(31, 165, 152)), 0, s.length(), 0);
                //s.setSpan(bss,0, s.length(),0);
                item.setTitle(s);
                colors.add("teal");
            }if(i == 4) {
                SpannableString s = new SpannableString(String.valueOf(item.getTitle()));
                s.setSpan(new ForegroundColorSpan(Color.rgb(21, 162, 191)), 0, s.length(), 0);
                //s.setSpan(bss,0, s.length(),0);
                item.setTitle(s);
                colors.add("blue");
            }if(i == 5) {
                SpannableString s = new SpannableString(String.valueOf(item.getTitle()));
                s.setSpan(new ForegroundColorSpan(Color.GRAY), 0, s.length(), 0);
                //s.setSpan(bss,0, s.length(),0);
                item.setTitle(s);
                colors.add("gray");
            }if(i == 6) {
                SpannableString s = new SpannableString(String.valueOf(item.getTitle()));
                s.setSpan(new ForegroundColorSpan(Color.MAGENTA), 0, s.length(), 0);
                //s.setSpan(bss,0, s.length(),0);
                item.setTitle(s);
                colors.add("magenta");
            }if(i == 7) {
                SpannableString s = new SpannableString(String.valueOf(item.getTitle()));
                s.setSpan(new ForegroundColorSpan(Color.DKGRAY), 0, s.length(), 0);
                //s.setSpan(bss,0, s.length(),0);
                item.setTitle(s);
                colors.add("darkgray");
            }
        }
        navigationView = findViewById(R.id.nav_view);
        final Menu benu = navigationView.getMenu();
        benu.clear();
        populateDrawer(benu, newSources);

        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        navigationView = findViewById(R.id.nav_view);
        final Menu benu = navigationView.getMenu();

        if (toggle.onOptionsItemSelected(item))
        {
            invalidateOptionsMenu();
            /*if(click == false) {
                invalidateOptionsMenu();
                click = true;
            }else{

            }*/
            return true;
        }
        switch (item.getItemId()) {
            case R.id.zero:
                selectedTopic = String.valueOf(item.getTitle());
                newSources.clear();
                queryNewSource(apiKey);
                Log.v("click menu", selectedTopic);
                return true;
            default:
                selectedTopic = String.valueOf(item.getTitle());
                Log.v("click menu", selectedTopic);
                queryNewSource(apiKey);
                populateDrawer(benu, newSources);
                return true;
        }
    }

    public void queryNewSource(String key){
        String url = "https://newsapi.org/v2/sources?apiKey="+key;
        RequestQueue requested = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {

                            @Override
                            public void onResponse(JSONObject response) {
                                newSources.clear();
                                try {

                                    for(int i = 0; i < response.getJSONArray("sources").length();i++){
                                        if(selectedTopic.equals(response.getJSONArray("sources").getJSONObject(i).getString("category"))) {
                                            String idName = response.getJSONArray("sources").getJSONObject(i).getString("id");
                                            String source = response.getJSONArray("sources").getJSONObject(i).getString("name");
                                            String topic = response.getJSONArray("sources").getJSONObject(i).getString("category");
                                            newSources.add(new SourceDetails(idName, source, topic));

                                            //String output = topic.substring(0, 1).toUpperCase() + topic.substring(1);
                                        }if(selectedTopic.equals("all")){
                                            String idName = response.getJSONArray("sources").getJSONObject(i).getString("id");
                                            String source = response.getJSONArray("sources").getJSONObject(i).getString("name");
                                            String topic = response.getJSONArray("sources").getJSONObject(i).getString("category");
                                            newSources.add(new SourceDetails(idName, source, topic));

                                            //String output = topic.substring(0, 1).toUpperCase() + topic.substring(1);
                                            if (!topics.contains(topic)) {

                                                topics.add(topic);
                                            }
                                            Log.v(TAG, topic);
                                        }

                                    }
                                    //topics.add("All");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        },
                        new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getBaseContext(), "Error getting News Sources", Toast.LENGTH_SHORT);
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("User-Agent", "Michal News");
                        return headers;
                    }
                };
        requested.add(jsonObjectRequest);
    }

    public void queryNewArticle(String channel, String key){
    String url = "https://newsapi.org/v2/top-headlines?sources="+ channel +"&apiKey=" + key;
        RequestQueue requested = Volley.newRequestQueue(MainActivity.this);
        JsonObjectRequest jsonObjectRequest =
                new JsonObjectRequest(Request.Method.GET, url, null,
                        new Response.Listener<JSONObject>() {
                            @Override
                            public void onResponse(JSONObject response) {
                                theNews.clear();
                                try {
                                    for(int i = 0; i < response.getJSONArray("articles").length();i++){
                                        String author = response.getJSONArray("articles").getJSONObject(i).getString("author");
                                        String title = response.getJSONArray("articles").getJSONObject(i).getString("title");
                                        String desc = response.getJSONArray("articles").getJSONObject(i).getString("description");
                                        String url = response.getJSONArray("articles").getJSONObject(i).getString("url");
                                        String urlPic = response.getJSONArray("articles").getJSONObject(i).getString("urlToImage");
                                        String time = response.getJSONArray("articles").getJSONObject(i).getString("publishedAt");
                                        theNews.add(new NewsDetails(urlPic, time, title, author, url, desc));
                                        Log.v("getting articles", title + (i+1));
                                    }
                                    //topics.add("All");
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        },
                        new Response.ErrorListener() {
                            @SuppressLint("ShowToast")
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                Toast.makeText(getBaseContext(), "Error getting News Articles", Toast.LENGTH_SHORT);
                            }
                        }){
                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String, String> headers = new HashMap<>();
                        headers.put("User-Agent", "Michal News");
                        return headers;
                    }
                };
        requested.add(jsonObjectRequest);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            default:
                Log.v("Nav Menu Item", String.valueOf(item.getTitle()));
                for (int i = 0; i < newSources.size(); i++) {

                    if(newSources.get(i).getName().contains(String.valueOf(item.getTitle()))){
                        Log.v("Nav newSource",  newSources.get(i).getName());
                        String id = String.valueOf(newSources.get(i).getNewsID());
                        queryNewArticle(newSources.get(i).getNewsID(), apiKey);
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, ok).commit();
                    }
                }


                //Log.v("Nav", theNews.get(0).getTitle());

        }
        drawer.closeDrawer(GravityCompat.START);

        //rv.setHasFixedSize(true);
        //rv.setLayoutManager(new LinearLayoutManager(getActivity()));
        return true;
    }


}