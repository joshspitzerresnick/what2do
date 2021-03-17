package com.example.what2do.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.what2do.R;

public class MatchesActivity extends Activity implements View.OnClickListener {
    private Button done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matches);

        done = findViewById(R.id.matches_done);
        done.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.matches_done) {
            finish();
        }
    }
}
