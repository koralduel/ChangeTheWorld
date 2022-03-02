package com.example.changetheworld;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.changetheworld.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    Button createAccount;
    Button createBusinessAccount;
    private ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main);
        createAccount = (Button) findViewById(R.id.createClientAccount);
        createBusinessAccount = (Button) findViewById(R.id.createBusinessAccount);
        createAccount.setOnClickListener(view ->  { openCreateNewAccount(); });
        createBusinessAccount.setOnClickListener(view -> {openCreateBusinessAccount(); });

        binding.login.setOnClickListener(view->{
            String userName = binding.editTextTextPersonName.getText().toString();
            String password = binding.editTextTextPassword.getText().toString();
            Log.d("Main","button pressed" + userName + " " + password);
        });
    }

    public void openCreateNewAccount(){
        Intent intent = new Intent(this,CreateAccountPrivate.class);
        startActivity(intent);
    }

    public void openCreateBusinessAccount(){
        Intent intent = new Intent(this,CreateBusinessAccount.class);
        startActivity(intent);
    }


}