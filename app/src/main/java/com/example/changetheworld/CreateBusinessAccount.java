package com.example.changetheworld;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.changetheworld.model.BusinessClient;
import com.example.changetheworld.model.FireStoreDB;

public class CreateBusinessAccount extends AppCompatActivity {
    Spinner states;
    String[] state = {"Choose State","Israel","United States","Brazil","Italy"};
    int flag = 0;
    int SELECT_PICTURE = 200;
    String business_chosen_approvel = "";
    String business_chosen_owner_id = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_business_account);

        states = (Spinner) findViewById(R.id.enterBusinessState);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1);
        adapter.addAll(state);
        states.setAdapter(adapter);




        //Business Approval Document load
        Button business_approval = ((Button) findViewById(R.id.businessApprovalDoc));
        business_approval.setOnClickListener(new View.OnClickListener()
                                          {
                                              @Override
                                              public void onClick(View v) {
                                                  flag = 1;
                                                  imageChooser();
                                              }
                                          }
        );

        //Business Owner id load
        Button business_owner_id = ((Button) findViewById(R.id.businessOwnerId));
        business_owner_id.setOnClickListener(new View.OnClickListener()
                                          {
                                              @Override
                                              public void onClick(View v) {
                                                  flag = 2;
                                                  imageChooser();
                                              }
                                          }
        );





        Button CreateBusinessAccount = findViewById(R.id.createBusinessAccountButton);
        CreateBusinessAccount.setOnClickListener(view -> {
            String business_name = ((EditText) findViewById(R.id.enterBusinessName)).getText().toString();
            String mail = ((EditText) findViewById(R.id.enterMailAddressBusiness)).getText().toString();
            String state = ((Spinner) findViewById(R.id.enterBusinessState)).getSelectedItem().toString();
            String phone = ((EditText) findViewById(R.id.enterBusinessPhone)).getText().toString();
            String user_name = ((EditText) findViewById(R.id.enterBusinessUsername)).getText().toString();
            String password = ((EditText) findViewById(R.id.enterBusinessPassword)).getText().toString();
            String business_owner_name = ((EditText) findViewById(R.id.enterBusinessOwnerName)).getText().toString();


            if (business_name.isEmpty()){
                Toast toast = Toast.makeText(this, "Invalid business name", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if (mail.isEmpty() || !mail.matches("^(.+)@(\\S+)$")){
                Toast toast = Toast.makeText(this, "Invalid mail", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if (state.isEmpty() || state.equals("Choose State")){
                Toast toast = Toast.makeText(this, "Invalid state", Toast.LENGTH_SHORT);
                toast.show();
            }

            else if (phone.isEmpty() || !phone.matches("^[0-9]*$")){
                Toast toast = Toast.makeText(this, "Invalid phone number", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if (user_name.isEmpty() || user_name.contains(" ")){
                Toast toast = Toast.makeText(this, "Invalid username", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if (password.isEmpty() || !password.matches("^[A-Za-z0-9]*$")){
                Toast toast = Toast.makeText(this, "Invalid password", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if (business_owner_name.isEmpty() || !business_owner_name.matches("[a-zA-z\\s]*$")){
                Toast toast = Toast.makeText(this, "Invalid business name", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if (business_chosen_approvel.isEmpty()){
                Toast toast = Toast.makeText(this, "Invalid business approval document", Toast.LENGTH_SHORT);
                toast.show();
            }
            else if (business_chosen_owner_id.isEmpty()){
                Toast toast = Toast.makeText(this, "Invalid business owner id", Toast.LENGTH_SHORT);
                toast.show();
            }else { //TODO: ADD DATABASE HERE
                BusinessClient business_client = new BusinessClient(business_name,mail,state,phone,user_name,password,business_owner_name,business_chosen_approvel,business_chosen_owner_id);
                FireStoreDB.getInstance().SaveBusinessClient(this, business_client);
            }
        });

    }


    public void imageChooser() {

        // create an instance of the
        // intent of the type image
        Intent i = new Intent();

        i.setType("image/*");
        i.setAction(Intent.ACTION_GET_CONTENT);

        // pass the constant to compare it
        // with the returned requestCode
        startActivityForResult(Intent.createChooser(i, "Select Picture"), SELECT_PICTURE);
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {

            // compare the resultCode with the
            // SELECT_PICTURE constant
            if (requestCode == SELECT_PICTURE) {
                // Get the url of the image from data
                Uri selectedImageUri = data.getData();
                if (null != selectedImageUri) {
                    if (flag == 1){
                        business_chosen_approvel = selectedImageUri.toString();
                        Toast.makeText(CreateBusinessAccount.this, "Business Approval Document Upload Success", Toast.LENGTH_SHORT).show();
                    }
                }
                if (flag == 2) {
                    business_chosen_owner_id = selectedImageUri.toString();
                    Toast.makeText(CreateBusinessAccount.this, "Business Owner ID Upload Success", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }


}