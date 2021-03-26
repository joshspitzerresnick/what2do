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

public class StartSwipeGenreFragment extends GroupFragment implements View.OnClickListener {
    private FragmentState state;
    private Button swipeGenreButton;

    public StartSwipeGenreFragment(Group group) {
        super(group);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_swipe_genre, container, false);

        statusText = view.findViewById(R.id.status_text);
        refreshStatusText();
        swipeGenreButton = view.findViewById(R.id.swipe_genre);
        swipeGenreButton.setOnClickListener(this);

        state = new ViewModelProvider(requireActivity()).get(FragmentState.class);
        state.setState(GroupActivity.NONE);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.swipe_genre) {
            state.setState(GroupActivity.SWIPE_GENRE_PRESSED);
        }
    }
}
