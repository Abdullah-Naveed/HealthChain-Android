package com.abdullahnaveed.fyp_health_chain;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.abdullahnaveed.fyp_health_chain.Adapters.CardViewAdapter;
import com.abdullahnaveed.fyp_health_chain.AsyncTasks.GetEncryptedRecords;
import com.abdullahnaveed.fyp_health_chain.Retrofit.HealthChainApi;
import com.abdullahnaveed.fyp_health_chain.Retrofit.RetrofitService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PatientRecords extends AppCompatActivity{

    public static final String DATABASE_NAME = "cacheDB";
    private ObjectMapper mapper;
    SQLiteDatabase liteDatabase;
    private Button btn;
    private SwipeRefreshLayout pullToRefresh;
    private RecyclerView recyclerView;
    private CardViewAdapter cAdapter;
    private ArrayList<String> listOfDates;
    private ArrayList<MedicalRecord> decryptedRecords = new ArrayList<>();
    private static DecryptedRecordsCallback recordsCallback;
    private ProgressBar progressBar;
    private TextView textViewError;
    private ArrayList<MedicalRecord> recordsFromBC;

    public interface DecryptedRecordsCallback {
        void onSuccess(ArrayList<MedicalRecord> records);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_records);
        mapper = new ObjectMapper();
        //create db with db name
        liteDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);
        createTable();
        btn = findViewById(R.id.getRecordsBtn);
//        deleteTable();

        //pull to refresh
        pullToRefresh = findViewById(R.id.pullToRefresh);
        pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refreshData(); // your code
                pullToRefresh.setRefreshing(false);
            }
        });


        setTitle("Dates of Interaction");
        RetrofitService retrofitService = new RetrofitService();
        listOfDates = new ArrayList<>();
        progressBar = findViewById(R.id.progressbar);
        textViewError = findViewById(R.id.textViewError);

        //initialize recyclerview
        recyclerView = findViewById(R.id.recordsRecyclerView);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        String ppsNumber = intent.getStringExtra("ppsNumber");
        final HealthChainApi healthChainApi = retrofitService.getServiceClass(intent.getStringExtra("url"));

        getDecryptedRecords(healthChainApi, name, ppsNumber, new DecryptedRecordsCallback() {
            @Override
            public void onSuccess(ArrayList<MedicalRecord> records) {
                recordsFromBC = new ArrayList<>(records);
                prepareData(listOfDates, records, PatientRecords.this);
                progressBar.setVisibility(View.INVISIBLE);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), CacheDBRecords.class));
            }
        });

    }

    private void refreshData() {
//        //when pull to refresh..
//        //check if records in cacheDB are same as records from blockchain... if they are, then just display the records in cacheDB
//        //else... add the record into cacheDB and then display the records in cacheDB...
////        recordsFromBC //this has all results from blockchain
//        MedicalRecord medicalRecord, mRecord;
//        ArrayList<CacheRecord> cacheRecords = loadRecords();
//        ArrayList<MedicalRecord> newRecords = new ArrayList<>();
//        Gson gson = new Gson();
//
//        if(cacheRecords.size() == recordsFromBC.size()){
//            pullToRefresh.setRefreshing(false);
//            Toast.makeText(this, "No New Records!", Toast.LENGTH_LONG).show();
//        }else{
//            for(CacheRecord record : cacheRecords){
//                for(MedicalRecord recordBC : recordsFromBC) {
//                    //                        medicalRecord = mapper.readValue(record.getMedicalRecord(), MedicalRecord.class);
//                    medicalRecord = gson.fromJson(record.getMedicalRecord(), MedicalRecord.class);
//                    String jsonRecord = gson.toJson(medicalRecord);
//
//                    if (!medicalRecord.getDate().equals(recordBC.getDate())) {
//                        cacheRecords.add(new CacheRecord(cacheRecords.size() + 1, UserStatus.getInstance().getUserName(), jsonRecord));
//                    }
//                }
//            }
//
//            for(CacheRecord record : cacheRecords){
//                mRecord = gson.fromJson(record.getMedicalRecord(), MedicalRecord.class);
//                newRecords.add(mRecord);
//            }
//
//            prepareData(listOfDates, newRecords, this);
//        }

    }

    public void createTable(){

        String sql = "CREATE TABLE IF NOT EXISTS records (\n" +
                "    id INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,\n" +
                "    userName varchar(200) NOT NULL,\n" +
                "    medicalRecord varchar(200) NOT NULL)";

        liteDatabase.execSQL(sql);

    }

//    public void deleteTable(){
//        String sql = "DROP TABLE records;";
//        liteDatabase.execSQL(sql);
//        Log.d("TABLE", "dropped");
//    }

    public void addRecordToCacheDB(ArrayList<MedicalRecord> records){
        //before adding the records... check if the records are already in there.. if so dont add...
        //add anonther first to table called username.. and then return records with that username...

        ArrayList<CacheRecord> cacheRecords = loadRecords();
        Gson gson = new Gson();

        //JSON from String to Object
        MedicalRecord obj;
        if(cacheRecords.size()==0){
            for(MedicalRecord medicalRecord : records){
                String addRecordSQL = "INSERT INTO records(userName, medicalRecord)" +
                        "VALUES (?, ?)";

                String jsonRecord = gson.toJson(medicalRecord);

                liteDatabase.execSQL(addRecordSQL, new String[]{UserStatus.getInstance().getUserName(), jsonRecord});
                Log.d("USERNAME", UserStatus.getInstance().getUserName());
                Toast.makeText(this, "Records Added!", Toast.LENGTH_LONG).show();

            }
        }else{
            for(CacheRecord record : cacheRecords){
                for(MedicalRecord medicalRecord : records){
                    obj = gson.fromJson(record.getMedicalRecord(), MedicalRecord.class);

                    if(!obj.getDate().equals(medicalRecord.getDate())){

                        String addRecordSQL = "INSERT INTO records(userName, medicalRecord)" +
                                "VALUES (?, ?)";

                        String jsonRecord = gson.toJson(medicalRecord);

                        liteDatabase.execSQL(addRecordSQL, new String[]{UserStatus.getInstance().getUserName(), jsonRecord});

                        Toast.makeText(this, "Records Added!", Toast.LENGTH_LONG).show();

                    }

                }
            }
        }

    }

    private ArrayList<CacheRecord> loadRecords(){
        //get all records from db
        String getRecordsSQL = "SELECT * FROM records WHERE userName" + "='" + UserStatus.getInstance().getUserName()+"'";

        ArrayList<CacheRecord> recordsList = new ArrayList<>();

        //this cursor will have all the data
        Cursor cursor = liteDatabase.rawQuery(getRecordsSQL, null);

        if(cursor.moveToFirst()){ //if true then has data...
            do{
                recordsList.add(new CacheRecord(
                        cursor.getInt(0),
                        cursor.getString(1),
                        cursor.getString(2)
                ));
            }while (cursor.moveToNext()); //keeps looping till no more records
        }

        return recordsList;
    }

    public void prepareData(ArrayList<String> dates, ArrayList<MedicalRecord> records, Context context) {
        int counter = 0;
        for (String date : dates){
            counter++;
            System.out.println(counter + " : " + date);
        }

        addRecordToCacheDB(records);

        //initialize Adapterclass with List
        cAdapter = new CardViewAdapter(listOfDates,PatientRecords.this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(cAdapter); //add adapter to recycler view

        Gson gson = new Gson();

        cAdapter.setOnItemClickListener(new CardViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(String date) {
                Log.d("CLICKED", date);

                ArrayList<CacheRecord> cacheRecords = loadRecords();
                MedicalRecord medicalRecord;
                for(CacheRecord cacheRecord : cacheRecords){
                    medicalRecord = gson.fromJson(cacheRecord.getMedicalRecord(), MedicalRecord.class);

                    //TODO: if records have same date.. shows both records in a pop up
                    if(date.equals(medicalRecord.getDate())) {
                        String interaction = "Interaction Type: " + medicalRecord.getInteractionType();
                        String gpNumber = "GP Number: " + medicalRecord.getGpNumber();
                        String notes = "Notes: " + medicalRecord.getNotes();
                        String prescription = "Prescription: " + medicalRecord.getPrescription();
                        String outcome = "Outcome: " + medicalRecord.getOutcome();

                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
                        builder.setMessage(interaction + "\n\n" + gpNumber + "\n\n" + notes + "\n\n" + prescription + "\n\n" + outcome)
                                .setTitle("Medical Record")
                                .show();
                    }
                }


//                for (MedicalRecord medicalRecord : records){
//                    if(date.equals(medicalRecord.getDate())){
//                        Log.d("Interaction Type: ", medicalRecord.getInteractionType());
//                        Log.d("Outcome: ", medicalRecord.getOutcome());
//
//                        String interaction = "Interaction Type: " + medicalRecord.getInteractionType();
//                        String gpNumber = "GP Number: " + medicalRecord.getGpNumber();
//                        String notes = "Notes: " + medicalRecord.getNotes();
//                        String prescription = "Prescription: " + medicalRecord.getPrescription();
//                        String outcome = "Outcome: " + medicalRecord.getOutcome();
//
//                        AlertDialog.Builder builder = new AlertDialog.Builder(context);
//                        builder.setMessage(interaction+"\n\n"+gpNumber+"\n\n"+notes+"\n\n"+prescription+"\n\n"+outcome)
//                                .setTitle("Medical Record")
//                                .show();
//                    }
//                }

            }
        });

    }

    private void getDecryptedRecords(HealthChainApi healthChainApi, final String name, String ppsNumber, DecryptedRecordsCallback callback) {
        progressBar.setVisibility(View.VISIBLE);
        recordsCallback = callback;
        GetEncryptedRecords getEncryptedRecords = new GetEncryptedRecords();
        ArrayList<Record> records = new ArrayList<>();

        try {
            records = getEncryptedRecords.execute().get();
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        ArrayList<Record> finalRecords = new ArrayList<>();
        int recordCounter = 0;

        for(Record record : records){
            if(ppsNumber.equals(record.getPpsNumber())){
                finalRecords.add(record);
                MedicalRecord.MedicalRecordBuilder medicalRecordBuilder = new MedicalRecord.MedicalRecordBuilder();

                Call<String> call = healthChainApi.getRecord(name, record.getEncryptedRecord());
                call.enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {

                        if(response.body() != null){
                            JsonParser parser = new JsonParser();
                            JsonObject json = parser.parse(response.body()).getAsJsonObject();
                            medicalRecordBuilder.interactionType(json.get("interactionType").getAsString())
                            .date(json.get("date").getAsString())
                            .gpNumber(json.get("gpNumber").getAsString())
                            .notes(json.get("notes").getAsString())
                            .prescription(json.get("prescription").getAsString())
                            .outcome(json.get("outcome").getAsString());

                            decryptedRecords.add(medicalRecordBuilder.build());

                            if(callback != null && (decryptedRecords.size() == finalRecords.size())){

                                for(MedicalRecord medicalRecord : decryptedRecords){
                                    listOfDates.add(medicalRecord.getDate());
                                }

                                callback.onSuccess(decryptedRecords);
                            }

                        }
                    }

                    @Override
                    public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                        Log.d("fail", t.getMessage());
                    }
                });

            }else{
                recordCounter++;
            }

        }

        if(recordCounter == records.size()){
            progressBar.setVisibility(View.INVISIBLE);
            textViewError.setText("You have no medical records.");
            AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
            builder.setTitle("You have no medical records!")
                    .setMessage("To add a medical record, please visit your local GP.")
                    .setPositiveButton("OK", null)
                    .show();
        }

    }

    @Override
    public void onBackPressed()
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.AlertDialogTheme);
        builder.setMessage("Are you sure you want to log out?")
                .setPositiveButton("Logout", dialogClickListener)
                .setNegativeButton("Cancel", null)
                .show();
    }

    DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
        switch (which){
            case DialogInterface.BUTTON_POSITIVE:
                //Yes button clicked
                UserStatus.getInstance().setUserName("");
                Intent intent = new Intent(PatientRecords.this, SetupServer.class);
//                Intent intent = new Intent(PatientRecords.this, Login.class);
                startActivity(intent);
                break;

            case DialogInterface.BUTTON_NEGATIVE:
                //No button clicked
                break;
        }
    };

}
