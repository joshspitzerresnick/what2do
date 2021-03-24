package com.example.what2do.model;


import android.content.Context;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.what2do.R;
import com.example.what2do.activities.MatchesActivity;
import com.example.what2do.activities.SingleMatchActivity;

import java.util.List;

public class MatchAdapter extends RecyclerView.Adapter<MatchAdapter.MatchViewHolder> {


    //this context we will use to inflate the layout
    protected Context mCtx;

    //we are storing all the matches in a list
    private List<Match> matchList;
   // protected List<Match> matchSingle;
    protected List<Match> matches;

    //getting the context and match list with constructor
    public MatchAdapter(Context mCtx, List<Match> matchList) {
        this.mCtx = mCtx;
        this.matchList = matchList;
    }

    @Override
    public MatchViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.layout_matches, null);
        return new MatchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MatchViewHolder holder, int position) {
        //getting the match of the specified position
        Match match = matchList.get(position);

        //binding the data with the viewholder views
        holder.textViewTitle.setText(match.getTitle());
        holder.textViewShortDesc.setText(match.getShortdesc());
        holder.textViewRating.setText(String.valueOf(match.getRating()));
        holder.textViewPercentage.setText(String.valueOf(match.getPercentage()));

        holder.imageView.setImageDrawable(mCtx.getResources().getDrawable(match.getImage()));

    }

    @Override
    public int getItemCount() {
        return matchList.size();
    }


    class MatchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView textViewTitle, textViewShortDesc, textViewRating, textViewPercentage;
        ImageView imageView;

        public MatchViewHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewShortDesc = itemView.findViewById(R.id.textViewShortDesc);
            textViewRating = itemView.findViewById(R.id.textViewRating);
            textViewPercentage = itemView.findViewById(R.id.textViewPercentage);
            imageView = itemView.findViewById(R.id.imageView);
        }


        @Override
        public void onClick(View view) {
            //Toast.makeText(view.getContext(), "position = " + getLayoutPosition(), Toast.LENGTH_SHORT).show();

            matches = FakeBackend.getMatches();

            //go through each item if you have few items within recycler view
            if (getLayoutPosition() == 0) {
                Match m = matches.get(0);
                FakeBackend.setMatch(m);
                Intent intent = new Intent(view.getContext(), SingleMatchActivity.class);
                mCtx.startActivity(intent);
            }
            else if (getLayoutPosition() == 1) {
                Match m1 = matches.get(1);
                FakeBackend.setMatch(m1);
                Intent intent = new Intent(view.getContext(), SingleMatchActivity.class);
                mCtx.startActivity(intent);
            }
            else if (getLayoutPosition() == 2) {
                Match m2 = matches.get(2);
                FakeBackend.setMatch(m2);
                Intent intent = new Intent(view.getContext(), SingleMatchActivity.class);
                mCtx.startActivity(intent);
            }
        }
    }
}
