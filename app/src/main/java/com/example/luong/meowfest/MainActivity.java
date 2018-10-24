package com.example.luong.meowfest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    RVAdapter rvAdapter;
    List<CatType> cat;
    QueryCats qc;
    String url = "https://chex-triplebyte.herokuapp.com/api/cats?page=0";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = (RecyclerView) findViewById(R.id.recyclerView);
        setUpCats();
    }

    private void setUpCats() {
        cat = new ArrayList<>();
        qc = new QueryCats(this);
        qc.execute(url);
    }

    public void setList(List<CatType> ct){
        cat = ct;
        rvAdapter = new RVAdapter(this,cat);
        rv.setAdapter(rvAdapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        qc.cancel(true);
        for(int i = 0; i < cat.size(); i++){
            Log.d("MAINUI",cat.toString());
        }
        //rv.notifyAll();
    }
}
