package com.example.what2do.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.what2do.R;
import com.example.what2do.model.FakeBackend;
import com.example.what2do.model.Match;
import com.example.what2do.model.MatchAdapter;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends Activity implements View.OnClickListener {
    private Button done;


    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.matches_done) {
            finish();
        }
    }

    //a list to store all the matches
    List<Match> matchList;

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the matchlist
        matchList = new ArrayList<>();
        matchList = FakeBackend.getMatches();

        //creating recyclerview adapter
        MatchAdapter adapter = new MatchAdapter(this, matchList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        done = findViewById(R.id.matches_done);
        done.setOnClickListener(this);
    }
}



