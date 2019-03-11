package com.abdullahnaveed.fyp_health_chain;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;


public class SetupServer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.server_setup);
        setTitle("Health Chain");

        EditText urlEditText = findViewById(R.id.editText);
        Button urlButton = findViewById(R.id.urlButton);
        setUpServer(urlEditText, urlButton);

    }

    public void setUpServer(final EditText editText, Button button) {

        button.setOnClickListener(view -> {

            String url = editText.getText().toString();
            //intent to login activity
            Intent intent = new Intent(SetupServer.this, Login.class);
            intent.putExtra("url", url);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);

        });

    }

}
