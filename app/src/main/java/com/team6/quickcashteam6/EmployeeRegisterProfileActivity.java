package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class EmployeeRegisterProfileActivity extends AppCompatActivity {

    private static final String Fb_URL = "https://quickcash-team6-default-rtdb.firebaseio.com/";
    private EditText age;
    private EditText gender;
    private EditText mobile;
    private Button submitProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_register_profile);

        age = findViewById(R.id.insert_age);
        gender = findViewById(R.id.insert_gender);
        mobile = findViewById(R.id.insert_mobile);
        submitProfile = findViewById(R.id.register_employee_profile);

        submitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployeeRegisterProfileActivity.this, LoginActivity.class));
            }
        });
    }

    protected void saveProfileToFirebase()  {

    }
}
