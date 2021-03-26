package com.example.what2do.model;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.what2do.R;

import java.util.List;

public class MemberAdapter extends RecyclerView.Adapter<MemberAdapter.MemberViewHolder> {
    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the products in a list
    private List<Member> memberList;

    //getting the context and product list with constructor
    public MemberAdapter(Context mCtx, List<Member> memberList) {
        this.mCtx = mCtx;
        this.memberList = memberList;
        Log.d("AA", memberList.size() + " 323424");
    }

    @NonNull
    @Override
    public MemberViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.group_member, null);
        return new MemberViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MemberViewHolder holder, int position) {
        //getting the product of the specified position
        Member member = memberList.get(position);

        //binding the data with the viewholder views
        holder.memberName.setText(member.getName());
        holder.memberImage.setImageResource(member.getImage());
        holder.memberIcon.setImageResource(member.getIcon());
        Log.d("AA", position + "");
    }

    @Override
    public int getItemCount() {
        return memberList.size();
    }


    class MemberViewHolder extends RecyclerView.ViewHolder {
        TextView memberName;
        ImageView memberImage, memberIcon;

        public MemberViewHolder(@NonNull View itemView) {
            super(itemView);

            memberName = itemView.findViewById(R.id.member_name);
            memberImage = itemView.findViewById(R.id.member_image);
            memberIcon = itemView.findViewById(R.id.member_icon);
        }
    }
}
