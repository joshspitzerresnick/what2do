package com.example.what2do.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.what2do.R;
import com.example.what2do.model.Match;
import com.example.what2do.model.SingleMatchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SingleMatchActivity extends Activity implements View.OnClickListener {
    private Button backButton;

    //    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_matches);
//
//        done = findViewById(R.id.matches_done);
//        done.setOnClickListener(this);
//    }
//
    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            Intent intent = new Intent(this, MatchesActivity.class);
            startActivity(intent);
        }
    }


    //a list to store all the matches
    List<Match> matchList;

//    //the recyclerview
//    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_single_match);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

//        //getting the recyclerview from xml
//        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
//        recyclerView.setHasFixedSize(true);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the matchlist
        matchList = new ArrayList<>();

        matchList.add(
                new Match(
                        1,
                        "Applebees",
                        "American Food",
                        "Rating: 3.8/5.0",
                        "98% match",
                        R.drawable.applebees,
                        "123 Baker St.",
                        "This City, MM 55555",
                        "(XXX) XXX-XXXX",
                        "www.thiswebsite.com"));



        //creating recyclerview adapter
        SingleMatchAdapter adapter = new SingleMatchAdapter(this, matchList);
//
//        //setting adapter to recyclerview
//        recyclerView.setAdapter(adapter);
    }
}


