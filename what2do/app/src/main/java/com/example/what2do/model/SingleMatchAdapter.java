package com.example.what2do.model;


import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.what2do.R;

import java.util.List;

public class SingleMatchAdapter extends RecyclerView.Adapter<SingleMatchAdapter.SingleMatchViewHolder> {


    //this context we will use to inflate the layout
    private Context mCtx;

    //we are storing all the matches in a list
    private List<Match> matchList;

    //getting the context and match list with constructor
    public SingleMatchAdapter(Context mCtx, List<Match> matchList) {
        this.mCtx = mCtx;
        this.matchList = matchList;
    }

    @Override
    public SingleMatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_single_match, null);
        //View view = inflater.inflate(R.layout.activity_matches, null);
        return new SingleMatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SingleMatchViewHolder holder, int position) {
        //getting the match of the specified position
        Match match = matchList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(match.getTitle());
//        holder.textViewShortDesc.setText(match.getShortdesc());
//        holder.textViewRating.setText(String.valueOf(match.getRating()));
//        holder.textViewPercentage.setText(String.valueOf(match.getPercentage()));
        holder.textViewPhone.setText(String.valueOf(match.getPhoneNum()));
        holder.textViewLoc1.setText(String.valueOf(match.getLocation1()));
        holder.textViewLoc2.setText(String.valueOf(match.getLocation2()));
        holder.textViewWeb.setText(String.valueOf(match.getWebsite()));

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(match.getImage()));


    }


    @Override
    public int getItemCount() {
        return matchList.size();
    }


    class SingleMatchViewHolder extends RecyclerView.ViewHolder {

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPercentage, textViewPhone, textViewLoc1, textViewLoc2, textViewWeb;
        ImageView imageView;

        public SingleMatchViewHolder(View itemView) {
            super(itemView);

            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPercentage = itemView.findViewById(R.id.textViewPercentage);
            imageView = itemView.findViewById(R.id.matchImage);
            textViewPhone = itemView.findViewById(R.id.textViewPhone);
            textViewLoc1 = itemView.findViewById(R.id.textViewLoc1);
            textViewLoc2 = itemView.findViewById(R.id.textViewLoc2);
            textViewWeb = itemView.findViewById(R.id.textViewWeb);

        }
    }
}
