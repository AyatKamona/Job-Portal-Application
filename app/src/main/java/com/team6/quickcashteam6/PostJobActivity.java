package com.team6.quickcashteam6;

import android.app.Notification;
import android.app.NotificationChannel;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class PostJobActivity extends AppCompatActivity implements View.OnClickListener {

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
        String jobTitle = getJobTitle();
        String skills = getSkills();
        String jobDescription = getDescription();
        String jobPayment = getPayment();
        String startTime = getStartTime();
        String errorMessage = "";

        if (isEmptyJobTitle(jobTitle)){
            errorMessage = getResources().getString(R.string.EMPTY_JOB_TITLE).trim();
        }

        else if (isEmptyPayment(jobPayment)){
            errorMessage = getResources().getString(R.string.EMPTY_PAYMENT).trim();
        }

        else if (isEmptyStartTime(startTime)){
            errorMessage = getResources().getString(R.string.EMPTY_START_TIME).trim();
        }

        else if (isEmptySkills(skills)){
            errorMessage = getResources().getString(R.string.EMPTY_SKILLS).trim();
        }

        else if (isEmptyDescription(jobDescription)){
            errorMessage = getResources().getString(R.string.EMPTY_JOB_DESCRIPTION).trim();
        }

        else {
            JobData post = new JobData(jobTitle, jobPayment, startTime, skills, jobDescription);
            DatabaseReference jobToPost = FirebaseDatabase.getInstance().getReference().child("Job Postings");
            jobToPost.push().setValue(post);
            openEmployerPage();
            Toast.makeText(PostJobActivity.this, "Successful", Toast.LENGTH_LONG).show();
        }


        setErrorMessage(errorMessage);
        System.out.println(errorMessage);
    }

    public void openEmployerPage() {
        Intent submitJob = new Intent(PostJobActivity.this, EmployerPageActivity.class);
        startActivity(submitJob);
    }


    protected String getJobTitle(){
        EditText insert_job_title = findViewById(R.id.insert_job_title);
        return insert_job_title.getText().toString().trim();
    }

    protected String getSkills(){
        String skills = "";
        EditText skill1 = findViewById(R.id.skill1);
        EditText skill2 = findViewById(R.id.skill2);
        EditText skill3 = findViewById(R.id.skill3);

        if(!(skill1.getText().toString()).isEmpty()){
            skills += skill1.getText().toString() + ",";
        }

        if(!(skill2.getText().toString()).isEmpty()){
            skills += skill2.getText().toString() + ",";
        }

        if(!(skill3.getText().toString()).isEmpty()){
            skills += skill3.getText().toString();
        }

        return skills;
    }

    protected String getDescription(){
        EditText insert_job_description = findViewById(R.id.insert_job_description);
        return insert_job_description.getText().toString().trim();
    }

    protected String getPayment(){
        EditText insert_job_payment = findViewById(R.id.insert_job_payment);
        return insert_job_payment.getText().toString().trim();
    }

    protected String getStartTime(){
        EditText insert_start_time = findViewById(R.id.insert_start_time);
        return insert_start_time.getText().toString().trim();
    }

    protected static boolean isEmptyJobTitle(String jobTitle){
        return jobTitle.isEmpty();
    }

    protected static boolean isEmptyPayment(String payment){
        return payment.isEmpty();
    }

    protected static boolean isEmptyStartTime(String startTime){
        return startTime.isEmpty();
    }

    protected static boolean isEmptySkills(String skills){
        return skills.isEmpty();
    }

    protected static boolean isEmptyDescription(String description){
        return description.isEmpty();
    }

}
