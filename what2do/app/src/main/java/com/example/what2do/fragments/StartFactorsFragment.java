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

public class StartFactorsFragment extends Fragment implements View.OnClickListener {
    private FragmentState state;

    private Button startFactorsButton;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_start_factors, container, false);

        startFactorsButton = view.findViewById(R.id.start_factors);
        startFactorsButton.setOnClickListener(this);

        state = new ViewModelProvider(requireActivity()).get(FragmentState.class);
        state.setState(GroupActivity.NONE);
        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.start_factors) {
            state.setState(GroupActivity.START_FACTORS_PRESSED);
        }
    }
}
