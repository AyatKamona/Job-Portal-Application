package com.team6.quickcashteam6;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.BreakIterator;

public class PostJobActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference publicDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postjob);
        publicDatabase = FirebaseDatabase.getInstance().getReference().child("Public Database");
        Button submitJobButton = findViewById(R.id.submitJobButton);
        submitJobButton.setOnClickListener(this);

        Button addLocationButton = findViewById(R.id.mapButton);
        addLocationButton.setOnClickListener(this::onLocationBtnClick);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)  {

            NotificationChannel channel = new NotificationChannel("New Job", "New Job", NotificationManager.IMPORTANCE_DEFAULT);
            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }
    }

    protected void setErrorMessage(String message){
        TextView errorMessage = findViewById(R.id.errorMessage);
        errorMessage.setText(message.trim());
    }

    // Submit job button press
    @Override
    public void onClick(View view){
        String jobTitle = getJobTitle();
        String skills = getSkills();
        String jobDescription = getDescription();
        String jobPayment = getPayment();
        String startTime = getStartTime();
        double jobLat = getLat();
        double jobLng = getLng();
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
            JobData post = new JobData(jobTitle, jobPayment, startTime, skills, jobDescription, jobLng, jobLat);
            publicDatabase.child(jobTitle).setValue(post);
            DatabaseReference jobToPost = FirebaseDatabase.getInstance().getReference().child("Job Postings");
            jobToPost.push().setValue(post);
            openEmployerPage();
            Toast.makeText(PostJobActivity.this, "Successful", Toast.LENGTH_LONG).show();
        }

        setErrorMessage(errorMessage);
        System.out.println(errorMessage);
        postJobNotify();
    }

    // Add location button press
    public void onLocationBtnClick(View view){
        Intent map = new Intent(PostJobActivity.this, MapsActivity.class);
        startActivity(map);
        setAddedTag();
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

    protected double getLat(){
        return MainActivity.jobLatitude;
    }

    protected double getLng(){
        return MainActivity.jobLongtitute;
    }

    public void setAddedTag(){
        TextView addedTag = findViewById(R.id.added);
        addedTag.setText("Added");
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

    public void postJobNotify() {

        /*
        This method is referenced how to create a a notification in Android
        URL: https://www.youtube.com/watch?v=4BuRMScaaI4&ab_channel=EasyTuto
        Author: Easy Tuto
        Accessed: 11/02/12
         */

       NotificationCompat.Builder builder = new NotificationCompat.Builder(PostJobActivity.this, "New Job");
       builder.setContentTitle("New Job");
       builder.setContentText(getDescription());
       builder.setSmallIcon(R.drawable.ic_launcher_background);
       builder.setAutoCancel(true);

        NotificationManagerCompat manager = NotificationManagerCompat.from(PostJobActivity.this);
        manager.notify(1, builder.build());


    }
}
