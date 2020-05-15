package com.example.coffeeshopinventorytracking;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class HomeScreen extends AppCompatActivity {

    private Button mLoginButton;
    private EditText username;
    private EditText password;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.screen_home);

        DataModel dataModel = DataModel.get(this);
        username = findViewById(R.id.homeScreenUserEditText);
        password = findViewById(R.id.homeScreenPasswordEditText);

        mLoginButton = findViewById(R.id.homeScreenButton);
        mLoginButton.setOnClickListener(v -> {
            boolean success = DataModel.get(this).getUsername(username.getText().toString(), password.getText().toString());
            if (success){
                Intent intent = new Intent(HomeScreen.this, MainScreenActivity.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Login attempt failed, try again", Toast.LENGTH_LONG).show();
            }
        });
    }
}
