package com.example.what2do.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.what2do.R;
import com.example.what2do.activities.GroupActivity;

public class FinishedSwipeGenreFragment extends Fragment implements View.OnClickListener {
    private FragmentState state;

    private Button proposeCustomActivityButton, cancelSwipingButton, readyUpButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finished_swipe_genre, container, false);

        proposeCustomActivityButton = view.findViewById(R.id.propose_custom_activity);
        proposeCustomActivityButton.setOnClickListener(this);
        cancelSwipingButton = view.findViewById(R.id.cancel_swiping);
        cancelSwipingButton.setOnClickListener(this);
        readyUpButton = view.findViewById(R.id.ready_up);
        readyUpButton.setOnClickListener(this);

        state = new ViewModelProvider(requireActivity()).get(FragmentState.class);
        state.setState(GroupActivity.NONE);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.propose_custom_activity) {
            state.setState(GroupActivity.PROPOSE_CUSTOM_ACTIVITY_PRESSED);
        } else if(view.getId() == R.id.cancel_swiping) {
            state.setState(GroupActivity.CANCEL_SWIPING_PRESSED);
        } else if(view.getId() == R.id.ready_up) {
            state.setState(GroupActivity.READY_UP_PRESSED);
        }
    }
}
