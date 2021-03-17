package com.example.what2do;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class FactorsActivity extends Activity implements View.OnClickListener {
    private Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        doneButton = findViewById(R.id.factors_done);
        doneButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == doneButton.getId()) {
            finish();
        }
    }
}
