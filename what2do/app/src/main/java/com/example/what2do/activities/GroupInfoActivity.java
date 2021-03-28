package com.example.what2do.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.what2do.R;

public class GroupInfoActivity extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group_info);

        ImageButton backButton = (ImageButton) findViewById(R.id.imageButton2);
        backButton.setOnClickListener(this);
        TextView backText = (TextView) findViewById(R.id.textView12);
        backText.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.imageButton2 || v.getId() == R.id.textView12) {
            finish();
        }
    }
}
