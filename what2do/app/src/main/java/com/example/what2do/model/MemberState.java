package com.example.what2do.model;

import com.example.what2do.R;

public enum MemberState {
    NONE        (0),
    IN_PROGRESS (R.drawable.group_wait),
    COMPLETED   (R.drawable.ready_up),
    REDO_SWIPE  (R.drawable.vote_to_swipe);

    public final int icon;
    private MemberState(int icon) {
        this.icon = icon;
    }
}
