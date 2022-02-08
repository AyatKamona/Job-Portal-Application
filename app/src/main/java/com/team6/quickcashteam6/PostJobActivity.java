package com.team6.quickcashteam6;

import android.content.Intent;
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

    private static final String FIREBASE_DATABASE_URL = "https://quickcash-team6-default-rtdb.firebaseio.com/";
    private FirebaseDatabase firebaseDB;
    private DatabaseReference jobToPost;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postjob);

        Button submitJobButton = findViewById(R.id.submitJobButton);
        submitJobButton.setOnClickListener(this);

    }

   // protected void initializeDatabase(){
     //   firebaseDB = FirebaseDatabase.getInstance(FIREBASE_DATABASE_URL);
      //  jobToPost = firebaseDB.getReference("Job Post");
   // }

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
        String errorMessage = new String();

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
            jobToPost = FirebaseDatabase.getInstance().getReference().child("Job Post");
            jobToPost.push().setValue(post);
            openEmployerPage();
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
        EditText insert_skills = findViewById(R.id.insert_skills);
        return insert_skills.getText().toString().trim();
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
