/*
 * The adapter creates view holders  for the card view. The adapter also then binds
 * the view holders to their individual data
 */


package com.abdullahnaveed.fyp_health_chain.Adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.abdullahnaveed.fyp_health_chain.PatientRecords;
import com.abdullahnaveed.fyp_health_chain.R;

import java.util.List;


public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.MyViewHolder> {

    //List
    private List<String> dates;
    private Context context;
    private OnItemClickListener mListener;

    public interface OnItemClickListener{
        void onItemClick(String date);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.mListener = onItemClickListener;
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView dateOfInteraction;

        MyViewHolder(View view, final OnItemClickListener onItemClickListener, List<String> dates) {
            super(view);
            //initialize cardview
            CardView cardView = view.findViewById(R.id.card_view);
            cardView.setCardElevation(20);//setcardview elevation
            cardView.setRadius(30);       //set radius of cardview
            //initialize text views
            dateOfInteraction = view.findViewById(R.id.dateOfInteraction);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(onItemClickListener != null){
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){ //makes sure position is valid..
                            onItemClickListener.onItemClick(dates.get(position)); //passes the position to the interface method created above
                        }
                    }

                }
            });
        }
    }

    public CardViewAdapter(List<String> dates, Context context) {
        this.dates = dates;
        this.context = context;
    }

    @NonNull
    @Override
    public CardViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //layout inflator-set view of each item of recyclerview
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_cardview, parent, false);
        return new CardViewAdapter.MyViewHolder(itemView, mListener, dates);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull CardViewAdapter.MyViewHolder holder, int position) {
        //get item at position
        String date = this.dates.get(position);
        holder.dateOfInteraction.setText(date);
    }

    @Override
    public int getItemCount() {
        return dates.size();
    }


}