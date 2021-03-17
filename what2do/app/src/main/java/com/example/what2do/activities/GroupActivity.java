package com.example.what2do.activities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import com.example.what2do.R;
import com.example.what2do.fragments.FinishedFactorsFragment;
import com.example.what2do.fragments.FragmentState;
import com.example.what2do.fragments.StartFactorsFragment;
import com.example.what2do.fragments.StartActivityFragment;

enum GroupState {
    IDLE,
    FACTORS,
    FACTORS_STARTED,
    FACTORS_FINISHED,
    SWIPE_GENRE,
    SWIPE_ACTIVITY,
}

public class GroupActivity extends FragmentActivity implements View.OnClickListener {
    private final String[] GROUP_NAMES = {
            "Family Group",
            "Sibling Group",
            "Friend Group"
    };

    private GroupState userState;

    private FragmentManager fragmentManager;
    private Fragment startActivityFragment, startFactorsFragment, finishedFactorsFragment;

    private FragmentState fragmentState;
    public static final int NONE = 0;
    public static final int START_ACTIVITY_PRESSED = 1;
    public static final int START_FACTORS_PRESSED = 2;

    public static int CHOOSE_FACTORS_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        Intent intent = getIntent();
        TextView groupTitle = findViewById(R.id.group_title);
        groupTitle.setText(GROUP_NAMES[intent.getIntExtra(ProfileActivity.GROUP_ID, 0)]);


        startActivityFragment = new StartActivityFragment();
        startFactorsFragment = new StartFactorsFragment();
        finishedFactorsFragment = new FinishedFactorsFragment();

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, startActivityFragment, "")
                .commit();

        fragmentState = new ViewModelProvider(this).get(FragmentState.class);
        fragmentState.getState().observe(this, state -> {
            // Perform an action with the latest item data
            switch(state) {
                case START_ACTIVITY_PRESSED:
                    setUserState(GroupState.FACTORS_STARTED);
                    break;
                case START_FACTORS_PRESSED:
                    setUserState(GroupState.FACTORS_STARTED);
                    break;
                default:
                    break;
            }
        });

        setUserState(GroupState.IDLE);
        //if another group member started an activity, set state to FACTORS_STARTED
    }

    private void setFragment(Fragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("what2do", "activity result: " + resultCode);
        if (requestCode == CHOOSE_FACTORS_REQUEST) {
            if (resultCode == RESULT_OK) {
                setUserState(GroupState.FACTORS_FINISHED);
            } else {
                //if cancelled, show the fragment with "choose factors" button
                setUserState(GroupState.FACTORS);
            }
        }
    }

    private void setUserState(GroupState state) {
        //GroupState prevState = userState;
        userState = state;
        switch(userState) {
            case IDLE:
                setFragment(startActivityFragment);
                break;
            case FACTORS:
                setFragment(startFactorsFragment);
                break;
            case FACTORS_STARTED:
                //show factors activity
                Intent intent = new Intent(this, FactorsActivity.class);
                startActivityForResult(intent, CHOOSE_FACTORS_REQUEST);
                break;
            case FACTORS_FINISHED:
                setFragment(finishedFactorsFragment);
                //if all group members are finished, go to swipe state
                break;
        }
    }

    @Override
    public void onClick(View view) {

    }
}
