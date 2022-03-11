package com.example.changetheworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.changetheworld.databinding.ActivityMainBinding;
import com.example.changetheworld.model.FireStoreDB;

public class BusinessLogin extends AppCompatActivity {

    private Button createBusinessAccount;
    private Button login;
    private Button gotoClient;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business_login);
        createBusinessAccount = (Button) findViewById(R.id.createBusinessAccount);
        createBusinessAccount.setOnClickListener(view -> {openCreateBusinessAccount(); });
        gotoClient = (Button) findViewById(R.id.gotoLoginBusiness);
        gotoClient.setOnClickListener(view->{gotoLoginClient();});
        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(view->{
            String userName = ((EditText) findViewById(R.id.editTextTextPersonName)).getText().toString();
            String password = ((EditText) findViewById(R.id.editTextTextPassword)).getText().toString();
            if (userName.isEmpty()){
                Toast toast = Toast.makeText(this, "username invalid, please try Again",Toast.LENGTH_LONG);
                toast.show();
            }
            if (password.isEmpty()){
                Toast toast = Toast.makeText(this, "password invalid, please try Again",Toast.LENGTH_LONG);
                toast.show();
            }
            else{
                Intent intent = new Intent(this,client_home_page.class);
                FireStoreDB.getInstance().VerifyAndBusinessLogin(this, userName, password, intent);
            }
        });
    }

    public void openCreateBusinessAccount(){
        Intent intent = new Intent(this,CreateBusinessAccount.class);
        startActivity(intent);
    }

    public void gotoLoginClient(){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
    }
}