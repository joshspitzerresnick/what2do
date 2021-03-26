package com.example.what2do.model;

public interface MemberStateListener {
    Group getCurrentGroup();
    void memberStateChanged(GroupState groupState, Member member, MemberState memberState);
}
