package com.team6.quickcashteam6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PostJobActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText insert_job_title = findViewById(R.id.insert_job_title);
    private EditText insert_skills = findViewById(R.id.insert_skills);
    private EditText insert_job_description = findViewById(R.id.insert_job_description);
    private EditText insert_job_payment = findViewById(R.id.insert_job_payment);
    private EditText insert_start_time = findViewById(R.id.insert_start_time);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postjob);

        Button submitJobButton = findViewById(R.id.submitJobButton);
        submitJobButton.setOnClickListener(this);

    }

    protected void setErrorMessage(String message){
        TextView errorMessage = findViewById(R.id.errorMessage);
        errorMessage.setText(message.trim());
    }

    @Override
    public void onClick(View view){

        String job_title = insert_job_title.getText().toString().trim();
        String skills = insert_skills.getText().toString().trim();
        String job_description = insert_job_description.getText().toString().trim();
        String job_payment = insert_job_payment.getText().toString().trim();
        String start_time = insert_start_time.getText().toString().trim();



        openEmployerPage();
    }

    public void openEmployerPage() {
        Intent submitJob = new Intent(PostJobActivity.this, EmployerPageActivity.class);
        startActivity(submitJob);
    }

}
