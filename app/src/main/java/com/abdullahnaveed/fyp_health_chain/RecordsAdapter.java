package com.abdullahnaveed.fyp_health_chain;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class RecordsAdapter extends ArrayAdapter<CacheRecord> {

    Context mCtx;
    int listLayoutRes;
    List<CacheRecord> recordsList;

    public RecordsAdapter(Context mCtx, int listLayoutRes, List<CacheRecord> recordsList) {
        super(mCtx, listLayoutRes, recordsList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.recordsList = recordsList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        //getting record of the specified position
        CacheRecord record = recordsList.get(position);


        //getting views
        TextView textViewID = view.findViewById(R.id.textViewID);
        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewRecord = view.findViewById(R.id.textViewRecord);


        //adding data to views
        textViewID.setText(String.valueOf(record.getID()));
        textViewName.setText(record.getUserName());
        textViewRecord.setText(record.getMedicalRecord());


//        //we will use these buttons later for update and delete operation
//        Button buttonDelete = view.findViewById(R.id.buttonDeleteEmployee);
//        Button buttonEdit = view.findViewById(R.id.buttonEditEmployee);

        return view;
    }
}