package com.example.what2do.fragments;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.what2do.R;
import com.example.what2do.activities.GroupActivity;
import com.example.what2do.model.FakeBackend;
import com.example.what2do.model.Group;

public class FinishedSwipeGenreFragment extends GroupFragment implements View.OnClickListener {
    private FragmentState state;
    private Button proposeCustomActivityButton, cancelSwipingButton, readyUpButton;

    private String m_text;

    public FinishedSwipeGenreFragment(Group group) {
        super(group);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_finished_swipe_genre, container, false);

        statusText = view.findViewById(R.id.status_text);
        refreshStatusText();
        proposeCustomActivityButton = view.findViewById(R.id.propose_custom_activity);
        proposeCustomActivityButton.setOnClickListener(this);
        cancelSwipingButton = view.findViewById(R.id.cancel_swiping);
        cancelSwipingButton.setOnClickListener(this);
        readyUpButton = view.findViewById(R.id.ready_up);
        readyUpButton.setOnClickListener(this);

        FakeBackend.setGenreChosen(0);

        state = new ViewModelProvider(requireActivity()).get(FragmentState.class);
        state.setState(GroupActivity.NONE);


//        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//        builder.setTitle("Title");
//        // I'm using fragment here so I'm using getView() to provide ViewGroup
//        // but you can provide here any other instance of ViewGroup from your Fragment / Activity
//        View viewInflated = LayoutInflater.from(getContext()).inflate(R.layout.text_inpu_password, (ViewGroup) getView(), false);
//        // Set up the input
//        final EditText input = (EditText) viewInflated.findViewById(R.id.input);
//        // Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//        builder.setView(viewInflated);
//
//        // Set up the buttons
//        builder.setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.dismiss();
//                m_text = input.getText().toString();
//            }
//        });
//        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
//            @Override
//            public void onClick(DialogInterface dialog, int which) {
//                dialog.cancel();
//            }
//        });
//
//        builder.show();




        return view;
    }

    @Override
    public void onClick(View view) {
        if(view.getId() == R.id.propose_custom_activity) {
            state.setState(GroupActivity.PROPOSE_CUSTOM_ACTIVITY_PRESSED);
            showInputDialog(getContext());
            //FakeBackend.addActivity("Custom Activity");
        } else if(view.getId() == R.id.cancel_swiping) {
            state.setState(GroupActivity.CANCEL_SWIPING_PRESSED);
        } else if(view.getId() == R.id.ready_up) {
            state.setState(GroupActivity.READY_UP_PRESSED);
        }
    }

    private void showInputDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Propose Custom Activity")
                .setMessage("Enter your activity suggestion")
                .setView(taskEditText)
                .setPositiveButton("Suggest", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String activity = String.valueOf(taskEditText.getText());
                        FakeBackend.addActivity(activity);
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }
}
