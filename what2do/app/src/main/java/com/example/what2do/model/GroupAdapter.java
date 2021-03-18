package com.example.what2do.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.what2do.R;
import com.example.what2do.activities.GroupActivity;
import com.example.what2do.util.ButtonListener;

import java.util.List;

public class GroupAdapter extends RecyclerView.Adapter<GroupAdapter.GroupViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Group> groupList;

    private ButtonListener listener;

    //getting the context and product list with constructor
    public GroupAdapter(Context mCtx, List<Group> groupList, ButtonListener listener) {
        this.mCtx = mCtx;
        this.groupList = groupList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public GroupAdapter.GroupViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.group_layout, null);
        return new GroupAdapter.GroupViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull GroupAdapter.GroupViewHolder holder, int position) {
        //getting the product of the specified position
        Group group = groupList.get(position);

        //binding the data with the viewholder views
        holder.groupName.setText(group.getName());
        holder.groupIcon.setImageResource(group.getIcon());

        holder.index = position;
    }

    @Override
    public int getItemCount() {
        return groupList.size();
    }


    class GroupViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView groupName;
        ImageView groupIcon;
        Button groupButton;
        int index;

        public GroupViewHolder(@NonNull View itemView) {
            super(itemView);

            groupName = itemView.findViewById(R.id.group_name);
            groupIcon = itemView.findViewById(R.id.group_icon);
            groupButton = itemView.findViewById(R.id.group_button);

            groupButton.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.group_button) {
                listener.buttonClicked(index);
            }
        }
    }
}
