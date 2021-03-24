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
import com.example.what2do.model.SingleMatchAdapter;

import java.util.ArrayList;
import java.util.List;

public class SingleMatchActivity extends Activity implements View.OnClickListener {
    private Button backButton;
    private List<Match> matchList;

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.backButton) {
            Intent intent = new Intent(this, MatchesActivity.class);
            startActivity(intent);
        }
    }

    //the recyclerview
    RecyclerView singleRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_single_match);
        backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(this);

        //getting the recyclerview from xml
        singleRecyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        //recyclerView.setHasFixedSize(true);
        singleRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        //initializing the matchlist
          matchList = new ArrayList<>();
          matchList.add(FakeBackend.getMatch());

        //creating recyclerview adapter
        SingleMatchAdapter adapter = new SingleMatchAdapter(this, matchList);

        //setting adapter to recyclerview
       singleRecyclerView.setAdapter(adapter);
    }
}


