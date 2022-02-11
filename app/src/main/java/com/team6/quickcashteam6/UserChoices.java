package com.team6.quickcashteam6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Locale;

public class UserChoices extends AppCompatActivity implements View.OnClickListener {
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Button employeeButton = findViewById(R.id.employeeButton);
        EditText nameText = findViewById(R.id.nameTxtBox);
        //Listener to check for user submission
        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString().trim();
                Employee employee = new Employee(name);

            }
        });
        Button employerButton = findViewById(R.id.employerButton);
        employerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString().trim();
                Employer employer = new Employer(name);

            }
        });
    }
    @Override
    public void onClick(View view) {

    }
}
