package com.example.what2do.activities;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.what2do.R;

public class SwipeActivity extends Activity implements View.OnClickListener {
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swipe);

        doneButton = findViewById(R.id.swipe_done);
        doneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.swipe_done) {
            setResult(RESULT_OK);
            finish();
        }
    }
}
