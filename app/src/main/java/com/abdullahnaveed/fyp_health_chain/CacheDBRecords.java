package com.abdullahnaveed.fyp_health_chain;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import static com.abdullahnaveed.fyp_health_chain.PatientRecords.DATABASE_NAME;

public class CacheDBRecords extends AppCompatActivity {

    SQLiteDatabase liteDatabase;
    List<CacheRecord> recordsList;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cache_dbrecords);
        listView = findViewById(R.id.listViewRecords);
        recordsList = new ArrayList<>();

        liteDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        loadRecordsFromDB();

    }

    private void loadRecordsFromDB(){
        //get all records from db
        String getRecordsSQL = "SELECT * FROM records WHERE userName" + "=" + "'" + UserStatus.getInstance().getUserName() + "'";

        //this cursor will have all the data
        Cursor cursor = liteDatabase.rawQuery(getRecordsSQL, null);

        if(cursor.moveToFirst()){ //if true then has data...
            do{
                recordsList.add(new CacheRecord(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                ));
            }while (cursor.moveToNext()); //keeps looping till move to next is false.. so no more records

            RecordsAdapter recordsAdapter = new RecordsAdapter(this, R.layout.list_layout_records, recordsList);
            listView.setAdapter(recordsAdapter);

        }

    }

}
