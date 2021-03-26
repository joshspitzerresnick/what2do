package com.example.what2do.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.what2do.R;
import com.example.what2do.activities.GroupActivity;
import com.example.what2do.model.Group;

public class FinishedSwipeActivityFragment extends GroupFragment implements View.OnClickListener {
    private FragmentState state;
    private Button redoSwipeButton, cancelSwipingButton, readyUpButton;

    public FinishedSwipeActivityFragment(Group group) {
        super(group);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finished_swipe_activity, container, false);

        statusText = view.findViewById(R.id.status_text);
        refreshStatusText();
        redoSwipeButton = view.findViewById(R.id.swipe_again);
        redoSwipeButton.setOnClickListener(this);
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
        if(view.getId() == R.id.swipe_again) {
            state.setState(GroupActivity.SWIPE_AGAIN_PRESSED);
        } else if(view.getId() == R.id.cancel_swiping) {
            state.setState(GroupActivity.CANCEL_SWIPING_PRESSED);
        } else if(view.getId() == R.id.ready_up) {
            state.setState(GroupActivity.READY_UP_PRESSED);
        }
    }
}
