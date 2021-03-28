package com.example.what2do.activities;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.what2do.R;
import com.example.what2do.fragments.FinishedFactorsFragment;
import com.example.what2do.fragments.FinishedSwipeActivityFragment;
import com.example.what2do.fragments.FinishedSwipeGenreFragment;
import com.example.what2do.fragments.FragmentState;
import com.example.what2do.fragments.GroupFragment;
import com.example.what2do.fragments.StartFactorsFragment;
import com.example.what2do.fragments.StartActivityFragment;
import com.example.what2do.fragments.StartSwipeActivityFragment;
import com.example.what2do.fragments.StartSwipeGenreFragment;
import com.example.what2do.model.FakeBackend;
import com.example.what2do.model.Group;
import com.example.what2do.model.GroupState;
import com.example.what2do.model.Member;
import com.example.what2do.model.MemberAdapter;
import com.example.what2do.model.MemberState;
import com.example.what2do.model.MemberStateListener;

import java.util.HashMap;
import java.util.Map;

public class GroupActivity extends FragmentActivity implements View.OnClickListener, MemberStateListener {
    private GroupState groupState;
    private MemberAdapter memberAdapter;
    private Group group;
    private Member userMember;
    private CountDownTimer delayTimer;

    private FragmentManager fragmentManager;
    private FragmentState fragmentState;
    private GroupFragment startActivityFragment,
            startFactorsFragment, finishedFactorsFragment,
            startSwipeGenreFragment, finishedSwipeGenreFragment,
            startSwipeActivityFragment, finishedSwipeActivityFragment,
            currentFragment;


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

    // Leave group popup stuff
    private Button leaveButton;
    private AlertDialog.Builder dialogBuilder;
    private AlertDialog dialog;
    private Button yesButton, noButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);

        // Leave group
        leaveButton = findViewById(R.id.LeaveGroup);
        leaveButton.setOnClickListener(this);

        // Info Button
        ImageView infoButton = (ImageView) findViewById(R.id.imageView4);
        infoButton.setOnClickListener(this);

        Intent intent = getIntent();
        int groupIndex = intent.getIntExtra(ProfileActivity.GROUP_ID, 0);
        group = FakeBackend.getGroups().get(groupIndex);

        FakeBackend.setMemberStateListener(this);
        setAllMemberStates(MemberState.NONE);

        for(Member m: group.getGroupMembers()) {
            if(m.isUser()) {
                userMember = m;
            }
        }
        if(userMember == null) {
            userMember = group.getGroupMembers().get(0);
        }

        TextView groupTitle = findViewById(R.id.group_title);
        groupTitle.setText(group.getName());

        RecyclerView memberRecyclerView = (RecyclerView) findViewById(R.id.member_list);
        memberRecyclerView.setHasFixedSize(true);
        memberRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        memberAdapter = new MemberAdapter(this, group.getGroupMembers());
        memberRecyclerView.setAdapter(memberAdapter);


        startActivityFragment = new StartActivityFragment(group);
        startFactorsFragment = new StartFactorsFragment(group);
        finishedFactorsFragment = new FinishedFactorsFragment(group);
        startSwipeGenreFragment = new StartSwipeGenreFragment(group);
        finishedSwipeGenreFragment = new FinishedSwipeGenreFragment(group);
        startSwipeActivityFragment = new StartSwipeActivityFragment(group);
        finishedSwipeActivityFragment = new FinishedSwipeActivityFragment(group);

        fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction()
                .add(R.id.fragment_container, startActivityFragment, "")
                .commit();

        //fragmentState allows fragments to send messages to this activity
        fragmentState = new ViewModelProvider(this).get(FragmentState.class);
        fragmentState.getState().observe(this, state -> {
            switch(state) {
                case START_ACTIVITY_PRESSED:
                    setGroupState(GroupState.FACTORS_STARTED);
                    break;
                case START_FACTORS_PRESSED:
                    setGroupState(GroupState.FACTORS_STARTED);
                    break;
                case SWIPE_GENRE_PRESSED:
                    setGroupState(GroupState.SWIPE_GENRE_STARTED);
                    break;
                case SWIPE_ACTIVITY_PRESSED:
                    setGroupState(GroupState.SWIPE_ACTIVITY_STARTED);
                    break;
                case PROPOSE_CUSTOM_ACTIVITY_PRESSED:
                    break;
                case SWIPE_AGAIN_PRESSED:
                    FakeBackend.changeMemberState(groupState, userMember, MemberState.REDO_SWIPE);
                    //setGroupState(GroupState.SWIPE_ACTIVITY_STARTED);
                    break;
                case CANCEL_SWIPING_PRESSED:
                    //finish();
                    break;
                case READY_UP_PRESSED:
                    FakeBackend.changeMemberState(groupState, userMember, MemberState.COMPLETED);
                    break;
                default:
                    break;
            }
        });

        setGroupState(GroupState.IDLE);
        //if another group member started an activity, set state to FACTORS_STARTED

    }

    private void setFragment(GroupFragment fragment) {
        fragmentManager.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .commit();
        currentFragment = fragment;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        Log.d("what2do", "activity result: " + requestCode + ", " + resultCode);
        if (requestCode == CHOOSE_FACTORS_REQUEST) {
            if (resultCode == RESULT_OK) {
                setGroupState(GroupState.FACTORS_FINISHED);
            } else {
                //if cancelled, show the fragment with "choose factors" button
                setGroupState(GroupState.FACTORS);
            }
        } else if(requestCode == SWIPE_GENRE_REQUEST) {
            if (resultCode == RESULT_OK) {
                setGroupState(GroupState.SWIPE_GENRE_FINISHED);
            } else {
                //if back is pressed, show the fragment with "swipe" button
                setGroupState(GroupState.SWIPE_GENRE);
            }
        } else if(requestCode == SWIPE_ACTIVITY_REQUEST) {
            if (resultCode == RESULT_OK) {
                setGroupState(GroupState.SWIPE_ACTIVITY_FINISHED);
            } else {
                //if back is pressed, show the fragment with "swipe" button
                setGroupState(GroupState.SWIPE_ACTIVITY);
            }
        } else if(requestCode == MATCHES_REQUEST) {
            setGroupState(GroupState.IDLE);
        }
    }

    private void setGroupStateDelayed(GroupState state, long delayMillis) {
        if(delayTimer != null) {
            delayTimer.cancel();
        }
        delayTimer = new CountDownTimer(delayMillis, delayMillis) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                setGroupState(state);
            }
        }.start();
    }

    private void setGroupState(GroupState state) {
        //GroupState prevState = userState;
        groupState = state;
        Intent intent;

        switch(groupState) {
            case IDLE:
                setAllMemberStates(MemberState.NONE);
                setFragment(startActivityFragment);
                break;
            case FACTORS:
                setFragment(startFactorsFragment);
                setAllMemberStates(MemberState.IN_PROGRESS);
                break;
            case FACTORS_STARTED:
                //show factors activity
                intent = new Intent(this, FactorsActivity.class);
                startActivityForResult(intent, CHOOSE_FACTORS_REQUEST);
                break;
            case FACTORS_FINISHED:
                setAllMemberStates(MemberState.IN_PROGRESS);
                setFragment(finishedFactorsFragment);
                //wait until all group members are finished, go to swipe state
                FakeBackend.changeMemberState(groupState, userMember, MemberState.COMPLETED);
                break;
            case SWIPE_GENRE:
                setFragment(startSwipeGenreFragment);
                setAllMemberStates(MemberState.IN_PROGRESS);
                break;
            case SWIPE_GENRE_STARTED:
                //show swipe activity
                intent = new Intent(this, SwipeGenreActivity.class);
                startActivityForResult(intent, SWIPE_GENRE_REQUEST);
                break;
            case SWIPE_GENRE_FINISHED:
                setAllMemberStates(MemberState.IN_PROGRESS);
                setFragment(finishedSwipeGenreFragment);
                //wait until all group members are finished, go to next swipe state
                //setUserStateDelayed(GroupState.SWIPE_ACTIVITY_STARTED, 2000);
                //this is instead called after pressing "ready up"
                break;
            case SWIPE_ACTIVITY:
                setFragment(startSwipeActivityFragment);
                setAllMemberStates(MemberState.IN_PROGRESS);
                break;
            case SWIPE_ACTIVITY_STARTED:
                //show swipe activity
                intent = new Intent(this, SwipeActivity.class);
                startActivityForResult(intent, SWIPE_ACTIVITY_REQUEST);
                break;
            case SWIPE_ACTIVITY_FINISHED:
                setAllMemberStates(MemberState.IN_PROGRESS);
                setFragment(finishedSwipeActivityFragment);
                //wait until all group members are finished, go to next swipe state
                //setUserStateDelayed(GroupState.MATCHES, 2000);
                //this is instead called after pressing "ready up"
                break;
            case MATCHES:
                intent = new Intent(this, MatchesActivity.class);
                startActivityForResult(intent, MATCHES_REQUEST);
                break;
        }
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.LeaveGroup) {
            createNewPopUp();
        }
        else if (view.getId() == R.id.imageView4) {
            Intent intent = new Intent(this, GroupInfoActivity.class);
            startActivity(intent);
        }
    }

    public void createNewPopUp(){
        dialogBuilder = new AlertDialog.Builder(this);
        final View popUp = getLayoutInflater().inflate(R.layout.leavegrouppopup, null);

        yesButton = (Button) popUp.findViewById(R.id.yesButton);
        noButton = (Button) popUp.findViewById(R.id.noButton);

        dialogBuilder.setView(popUp);
        dialog = dialogBuilder.create();
        dialog.show();

        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dialog.dismiss();
                setResult(RESULT_OK);
                finish();
            }
        });

        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                dialog.dismiss();
            }
        });


    }

    @Override
    public Group getCurrentGroup() {
        return group;
    }

    @Override
    public void memberStateChanged(GroupState groupState, Member member, MemberState memberState) {
        if(groupState != this.groupState || !group.getGroupMembers().contains(member)) {
            return;
        }

        setMemberState(member, memberState);
    }

    private void setAllMemberStates(MemberState state) {
        for(Member m: group.getGroupMembers()) {
            FakeBackend.changeMemberState(groupState, m, state);
        }
    }

    private void setMemberState(Member member, MemberState state) {
        member.setState(state);
        if(memberAdapter != null) {
            memberAdapter.notifyItemChanged(group.getGroupMembers().indexOf(member));
        }
        if(currentFragment != null) {
            currentFragment.refreshStatusText();
        }

        int numCompleted = 0;
        int numRedoSwipe = 0;
        for(Member m: group.getGroupMembers()) {
            if(m.getState() == MemberState.COMPLETED) {
                numCompleted++;
            } else if(m.getState() == MemberState.REDO_SWIPE) {
                numRedoSwipe++;
            }
        }
        if(groupState == GroupState.FACTORS_FINISHED && numCompleted == group.getGroupMembers().size()) {
            setGroupStateDelayed(GroupState.SWIPE_GENRE_STARTED, 1000);
        } else if(groupState == GroupState.SWIPE_GENRE_FINISHED && numCompleted == group.getGroupMembers().size()) {
            setGroupStateDelayed(GroupState.SWIPE_ACTIVITY_STARTED, 1000);
        } else if(groupState == GroupState.SWIPE_ACTIVITY_FINISHED && numCompleted == group.getGroupMembers().size()) {
            setGroupStateDelayed(GroupState.MATCHES, 1000);
        } else if(groupState == GroupState.SWIPE_ACTIVITY_FINISHED && numRedoSwipe == group.getGroupMembers().size()) {
            //setGroupState(GroupState.MATCHES);
            //redo swiping here
        }
    }
}
