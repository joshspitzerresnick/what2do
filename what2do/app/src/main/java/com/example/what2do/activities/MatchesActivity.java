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
import com.example.what2do.model.MatchAdapter;

import java.util.ArrayList;
import java.util.List;

public class MatchesActivity extends Activity implements View.OnClickListener {
    private Button done;

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_matches);
//
//        done = findViewById(R.id.matches_done);
//        done.setOnClickListener(this);
//    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.matches_done) {
            Intent intent = new Intent(this, ProfileActivity.class);
            startActivity(intent);
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


        //adding some items to our list
        matchList.add(
                new Match(
                        1,
                        "Applebees",
                        "American Food",
                        "Rating: 3.8/5.0",
                        "98% match",
                        R.drawable.applebees,
                        "123 Apple St.",
                        "This City, MM 55555",
                        "(XXX) XXX-XXXX",
                        "www.applebees.com"));

        matchList.add(
                new Match(
                        1,
                        "Olive Garden",
                        "Italian Food",
                        "Rating: 4.3/5.0",
                        "84% match",
                        R.drawable.olivegarden,
                        "123 Olive St.",
                        "This City, MM 55555",
                        "(XXX) XXX-XXXX",
                        "www.olivegarden.com"));

        matchList.add(
                new Match(
                        1,
                        "McDonalds",
                        "Fast Food",
                        "Rating: 2.7/5.0",
                        "62% match",
                        R.drawable.mcdonalds,
                        "123 Nugget St.",
                        "This City, MM 55555",
                        "(XXX) XXX-XXXX",
                        "www.mcdonalds.com"));

        //creating recyclerview adapter
        MatchAdapter adapter = new MatchAdapter(this, matchList);

        //setting adapter to recyclerview
        recyclerView.setAdapter(adapter);

        done = findViewById(R.id.matches_done);
        done.setOnClickListener(this);
    }

}
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_matches);
//
//        doneButton = findViewById(R.id.matches_done);
//        doneButton.setOnClickListener(this);
//    }
//
//    @Override
//    public void onClick(View view) {
//        if(view.getId() == R.id.matches_done) {
//            setResult(RESULT_OK);
//            finish();
//        }
//    }



