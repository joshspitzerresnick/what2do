package com.example.what2do.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.what2do.R;

public class FactorsActivity extends Activity implements View.OnClickListener {
    private Button readyButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factors);

        readyButton = findViewById(R.id.factors_done);
        readyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Log.d("what2do", "uhh HELLO?");
        if(view.getId() == R.id.factors_done) {
            setResult(RESULT_OK);
            finish();
            //finishActivity(RESULT_OK);
        }
    }
}
