package com.example.what2do.fragments;

import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.what2do.model.Group;
import com.example.what2do.model.Member;
import com.example.what2do.model.MemberState;

public abstract class GroupFragment extends Fragment {
    protected Group group;
    protected TextView statusText;
    private String initialStatus;

    public GroupFragment(Group group) {
        this.group = group;
    }

    public void refreshStatusText() {
        if(statusText != null) {
            if(initialStatus == null) {
                initialStatus = (String)statusText.getText();
            }

            String statusInsert = "";

            int numToComplete = 0;
            for(Member m: group.getGroupMembers()) {
                if(!m.isUser() && m.getState() != MemberState.COMPLETED) {
                    numToComplete++;
                }
            }

            boolean userToComplete = false;
            for(Member m: group.getGroupMembers()) {
                if(m.isUser() && m.getState() != MemberState.COMPLETED) {
                    userToComplete = true;
                    if(numToComplete == 1) {
                        statusInsert = statusInsert + "you and ";
                    } else if(numToComplete > 1) {
                        statusInsert = statusInsert + "you, ";
                    } else {
                        statusInsert = statusInsert + "you";
                    }
                }
            }

            int i = 0;
            for(Member m: group.getGroupMembers()) {
                if(!m.isUser() && m.getState() != MemberState.COMPLETED) {
                    statusInsert = statusInsert + m.getName();
                    if(i == numToComplete-2) {
                        if(numToComplete == 2) {
                            statusInsert = statusInsert + " and ";
                        } else {
                            statusInsert = statusInsert + ", and ";
                        }
                    } else if(i < numToComplete-2) {
                        statusInsert = statusInsert + ", ";
                    }
                    i++;
                }
            }


            if(numToComplete == 0 && !userToComplete) {
                statusText.setText("All done!");
            } else {
                statusText.setText(String.format(initialStatus, statusInsert));
            }
        }
    }
}
