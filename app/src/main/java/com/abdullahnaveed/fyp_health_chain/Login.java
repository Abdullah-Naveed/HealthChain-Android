package com.abdullahnaveed.fyp_health_chain;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.abdullahnaveed.fyp_health_chain.AsyncTasks.Web3jTask;
import com.abdullahnaveed.fyp_health_chain.Retrofit.HealthChainApi;
import com.abdullahnaveed.fyp_health_chain.Retrofit.RetrofitService;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText nameEditText = null;
    EditText passwordEditText = null;
    Button loginBtn = null;
    String serverURL = null;
    ImageView logo = null;
    int numOfRecords = 0;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setTitle("Login");
        RetrofitService retrofitService = new RetrofitService();
        nameEditText = findViewById(R.id.nameEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        loginBtn = findViewById(R.id.login_button);
        progressBar = findViewById(R.id.progressbarLogin);
        logo = findViewById(R.id.logo);

//        getIntent();
        
        Intent intent = getIntent();
        serverURL = intent.getStringExtra("url");
        Log.d("Server URL: ", serverURL);

        final HealthChainApi healthChainApi = retrofitService.getServiceClass(serverURL);

        loginBtn.setOnClickListener(view -> {
            hideKeyboard(this);
            progressBar.setVisibility(View.VISIBLE);
            getRegisteredUsers(healthChainApi);
        });

    }

    public void getRegisteredUsers(final HealthChainApi healthChainApi) {
        Web3jTask web3jTask = new Web3jTask();

        try {
            numOfRecords = web3jTask.execute().get();
            Log.d("RECORDS", String.valueOf(numOfRecords));
        } catch (ExecutionException | InterruptedException e) {
            e.printStackTrace();
        }

        final List<User> users = new ArrayList<>();
        Call<List<User>> call = healthChainApi.getUsers();

        //executing on background thread
        call.enqueue(new Callback<List<User>>() {
            @Override
            public void onResponse(@NonNull Call<List<User>> call, @NonNull Response<List<User>> response) {

                if (!response.isSuccessful()) {
                    Log.d("response: ", "Code: " + response.code());
                    return; //will exit the method
                }

                users.addAll(response.body());
                for (User user : users) {
                    if (nameEditText.getText().toString().equals(user.getName())) {
                        getDecryptedPassword(healthChainApi, user.getName());
                        Log.d("FOUND USER", user.getName());
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<User>> call, @NonNull Throwable t) {
                Log.d("responseError: ", "Code: " + t.getMessage());
            }
        });

    }

    public void getDecryptedPassword(HealthChainApi healthChainApi, final String name) {
        Call<ArrayList<String>> call = healthChainApi.getSecretAndPps(name);

        call.enqueue(new Callback<ArrayList<String>>() {
            @Override
            public void onResponse(@NonNull Call<ArrayList<String>> call, @NonNull Response<ArrayList<String>> response) {
                String decryptedPassword = response.body().get(0);
                String ppsNumber = response.body().get(1);

                System.out.println("password: " +decryptedPassword + ", pps:" + ppsNumber);

                if (passwordEditText.getText().toString().equals(decryptedPassword)) {
                    UserStatus.getInstance().setUserName(name);
                    Log.d("Login", "worked");

                    //intent to patient records activity
                    Intent intent = new Intent(Login.this, PatientRecords.class);
                    intent.putExtra("url", serverURL);
                    intent.putExtra("name", name);
                    intent.putExtra("ppsNumber", ppsNumber);
                    intent.putExtra("numOfRecords", numOfRecords);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);

                } else {
                    Log.d("Login", "failed");
                    new SweetAlertDialog(Login.this, SweetAlertDialog.ERROR_TYPE)
                            .setTitleText("Oops...")
                            .setContentText("Name or Password Incorrect. Please try again.")
                            .show();
                }

            }

            @Override
            public void onFailure(@NonNull Call<ArrayList<String>> call, @NonNull Throwable t) {
                Log.d("fail", t.getMessage());
            }
        });

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
