package com.example.what2do.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
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
import com.example.what2do.fragments.FinishedSwipeActivityFragment;
import com.example.what2do.fragments.FinishedSwipeGenreFragment;
import com.example.what2do.fragments.FragmentState;
import com.example.what2do.fragments.StartFactorsFragment;
import com.example.what2do.fragments.StartActivityFragment;
import com.example.what2do.fragments.StartSwipeActivityFragment;
import com.example.what2do.fragments.StartSwipeGenreFragment;

enum GroupState {
    IDLE,
    FACTORS,
    FACTORS_STARTED,
    FACTORS_FINISHED,
    SWIPE_GENRE,
    SWIPE_GENRE_STARTED,
    SWIPE_GENRE_FINISHED,
    SWIPE_ACTIVITY,
    SWIPE_ACTIVITY_STARTED,
    SWIPE_ACTIVITY_FINISHED,
    MATCHES,
}

public class GroupActivity extends FragmentActivity implements View.OnClickListener {
    private final String[] GROUP_NAMES = {
            "Family Group",
            "Sibling Group",
            "Friend Group"
    };

    private GroupState userState;

    private FragmentManager fragmentManager;
    private FragmentState fragmentState;
    private Fragment startActivityFragment,
            startFactorsFragment, finishedFactorsFragment,
            startSwipeGenreFragment, finishedSwipeGenreFragment,
            startSwipeActivityFragment, finishedSwipeActivityFragment;

    public static final int NONE = 0;
    public static final int START_ACTIVITY_PRESSED = 1;
    public static final int START_FACTORS_PRESSED = 2;
    public static final int SWIPE_GENRE_PRESSED = 3;
    public static final int SWIPE_ACTIVITY_PRESSED = 4;
    public static final int PROPOSE_CUSTOM_ACTIVITY_PRESSED = 5;
    public static final int SWIPE_AGAIN_PRESSED = 6;
    public static final int CANCEL_SWIPING_PRESSED = 7;
    public static final int READY_UP_PRESSED = 8;

    public static final int CHOOSE_FACTORS_REQUEST = 0;
    public static final int SWIPE_GENRE_REQUEST = 1;
    public static final int SWIPE_ACTIVITY_REQUEST = 2;
    public static final int MATCHES_REQUEST = 3;

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
        startSwipeGenreFragment = new StartSwipeGenreFragment();
        finishedSwipeGenreFragment = new FinishedSwipeGenreFragment();
        startSwipeActivityFragment = new StartSwipeActivityFragment();
        finishedSwipeActivityFragment = new FinishedSwipeActivityFragment();

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
                case SWIPE_GENRE_PRESSED:
                    setUserState(GroupState.SWIPE_GENRE_STARTED);
                    break;
                case SWIPE_ACTIVITY_PRESSED:
                    setUserState(GroupState.SWIPE_ACTIVITY_STARTED);
                    break;
                case PROPOSE_CUSTOM_ACTIVITY_PRESSED:
                    break;
                case SWIPE_AGAIN_PRESSED:
                    break;
                case CANCEL_SWIPING_PRESSED:
                    break;
                case READY_UP_PRESSED:
                    //after pressing ready up, simulate waiting for group members by waiting 2 seconds
                    if(userState == GroupState.SWIPE_GENRE_FINISHED) {
                        setUserStateDelayed(GroupState.SWIPE_ACTIVITY_STARTED, 2000);
                    } else if(userState == GroupState.SWIPE_ACTIVITY_FINISHED) {
                        setUserStateDelayed(GroupState.MATCHES, 2000);
                    }
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

        Log.d("what2do", "activity result: " + requestCode + ", " + resultCode);
        if (requestCode == CHOOSE_FACTORS_REQUEST) {
            if (resultCode == RESULT_OK) {
                setUserState(GroupState.FACTORS_FINISHED);
            } else {
                //if cancelled, show the fragment with "choose factors" button
                setUserState(GroupState.FACTORS);
            }
        } else if(requestCode == SWIPE_GENRE_REQUEST) {
            if (resultCode == RESULT_OK) {
                setUserState(GroupState.SWIPE_GENRE_FINISHED);
            } else {
                setUserState(GroupState.SWIPE_GENRE);
            }
        } else if(requestCode == SWIPE_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                setUserState(GroupState.SWIPE_ACTIVITY_FINISHED);
            } else {
                setUserState(GroupState.SWIPE_ACTIVITY);
            }
        } else if(requestCode == MATCHES_REQUEST) {
            setUserState(GroupState.IDLE);
        }
    }

    private void setUserStateDelayed(GroupState state, long delayMillis) {
        new CountDownTimer(delayMillis, delayMillis) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                setUserState(state);
            }
        }.start();
    }

    private void setUserState(GroupState state) {
        //GroupState prevState = userState;
        userState = state;
        Intent intent;

        switch(userState) {
            case IDLE:
                setFragment(startActivityFragment);
                break;
            case FACTORS:
                setFragment(startFactorsFragment);
                break;
            case FACTORS_STARTED:
                //show factors activity
                intent = new Intent(this, FactorsActivity.class);
                startActivityForResult(intent, CHOOSE_FACTORS_REQUEST);
                break;
            case FACTORS_FINISHED:
                setFragment(finishedFactorsFragment);
                //wait until all group members are finished, go to swipe state
                setUserStateDelayed(GroupState.SWIPE_GENRE_STARTED, 2000);
                break;
            case SWIPE_GENRE:
                setFragment(startSwipeGenreFragment);
                break;
            case SWIPE_GENRE_STARTED:
                //show swipe activity
                intent = new Intent(this, SwipeActivity.class);
                startActivityForResult(intent, SWIPE_GENRE_REQUEST);
                break;
            case SWIPE_GENRE_FINISHED:
                setFragment(finishedSwipeGenreFragment);
                //wait until all group members are finished, go to next swipe state
                //setUserStateDelayed(GroupState.SWIPE_ACTIVITY_STARTED, 2000);
                break;
            case SWIPE_ACTIVITY:
                setFragment(startSwipeActivityFragment);
                break;
            case SWIPE_ACTIVITY_STARTED:
                //show swipe activity
                intent = new Intent(this, SwipeActivity.class);
                startActivityForResult(intent, SWIPE_ACTIVITY_REQUEST);
                break;
            case SWIPE_ACTIVITY_FINISHED:
                setFragment(finishedSwipeActivityFragment);
                //wait until all group members are finished, go to next swipe state
                //setUserStateDelayed(GroupState.MATCHES, 2000);
                break;
            case MATCHES:
                intent = new Intent(this, MatchesActivity.class);
                startActivityForResult(intent, MATCHES_REQUEST);
                break;
        }
    }

    @Override
    public void onClick(View view) {

    }
}
