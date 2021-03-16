package com.example.what2do;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GroupActivity extends Activity implements View.OnClickListener {
    private final String[] GROUP_NAMES = {
            "Family Group",
            "Sibling Group",
            "Friend Group"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Intent intent = getIntent();
        TextView groupTitle = findViewById(R.id.group_title);
        groupTitle.setText(GROUP_NAMES[intent.getIntExtra(ProfileActivity.GROUP_ID, 0)]);
    }

    @Override
    public void onClick(View view) {

    }
}
