package com.example.what2do.activities;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.what2do.R;

public class FactorsActivity extends Activity implements View.OnClickListener {
    private Button done, cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factors);

        done = findViewById(R.id.factors_done);
        done.setOnClickListener(this);
        cancel = findViewById(R.id.factors_cancel);
        cancel.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        //Log.d("what2do", "uhh HELLO?");
        if(view.getId() == R.id.factors_done) {
            setResult(RESULT_OK);
            finish();
            //finishActivity(RESULT_OK);
        } else if(view.getId() == R.id.factors_cancel) {
            setResult(RESULT_CANCELED);
            finish();
            //finishActivity(RESULT_CANCELED);
        }
    }
}
