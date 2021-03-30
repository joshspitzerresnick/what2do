package com.example.what2do.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.what2do.R;
import com.example.what2do.model.FakeBackend;
import com.example.what2do.model.Group;
import com.example.what2do.model.GroupAdapter;
import com.example.what2do.model.Member;
import com.example.what2do.model.MemberAdapter;
import com.example.what2do.util.ButtonListener;

import java.util.ArrayList;
import java.util.List;

public class ProfileActivity extends Activity implements View.OnClickListener, ButtonListener {
    public static final String GROUP_ID = "com.example.what2do.GROUP_ID";

    private RecyclerView groupRecyclerView;
    private GroupAdapter groupAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        groupRecyclerView = (RecyclerView)findViewById(R.id.group_list);
        //groupRecyclerView.setHasFixedSize(true);
        groupRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        FakeBackend.init();
        groupAdapter = new GroupAdapter(this, FakeBackend.getGroups(), this);
        groupRecyclerView.setAdapter(groupAdapter);
    }

    @Override
    protected void onResume() {
        groupAdapter.notifyDataSetChanged();
        super.onResume();
    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public void buttonClicked(int id) {
        Log.d("AA", "button pressed " + id);
        Intent intent = new Intent(this, GroupActivity.class);
        intent.putExtra(GROUP_ID, id);
        startActivity(intent);
    }
}
