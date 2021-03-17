package com.example.what2do.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.what2do.R;

public class ProfileActivity extends Activity implements View.OnClickListener {
    public static final String GROUP_ID = "com.example.what2do.GROUP_ID";
    private final int[] BUTTON_IDS = new int[] {
            R.id.group_button1,
            R.id.group_button2,
            R.id.group_button3};
    private Button[] groupButtons;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        groupButtons = new Button[BUTTON_IDS.length];
        for(int i = 0; i < groupButtons.length; i++) {
            groupButtons[i] = findViewById(BUTTON_IDS[i]);
            groupButtons[i].setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View view) {
        for(int i = 0; i < groupButtons.length; i++) {
            if(view.getId() == BUTTON_IDS[i]) {
                Intent intent = new Intent(this, GroupActivity.class);
                intent.putExtra(GROUP_ID, i);
                startActivity(intent);
            }
        }
    }
}
