package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class JobEditActivity extends AppCompatActivity implements View.OnClickListener {

    EditText title;
    EditText payment;
    EditText startTime;
    EditText skills1;
    EditText skills2;
    EditText skills3;
    EditText desc;

    JobData job;

    String key;

    Map<String, Object> jobMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_edit);
        // Intent
        title = findViewById(R.id.insert_job_title);
        payment = findViewById(R.id.insert_job_payment);
        startTime = findViewById(R.id.insert_start_time);
        skills1 = findViewById(R.id.skill1);
        skills2 = findViewById(R.id.skill2);
        skills3 = findViewById(R.id.skill3);
        desc = findViewById(R.id.insert_job_description);

        Button button = findViewById(R.id.submitJobButton);

        Intent intent = getIntent();
        key = intent.getStringExtra("key");

        getJob(key);



        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        submit();
    }

    //TODO: Get job from firebase
    private void getJob(String key){
        DatabaseReference jobToPost = FirebaseDatabase.getInstance().getReference().child("Job Postings").child(key);
        jobToPost.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                setJobData((Map<String, Object>) snapshot.getValue());
                populateFields(job);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }

    private void setJobData(Map<String, Object> map){

        long longLng= 0;
        long longLat= 0;
        job = new JobData((String) map.get("id"), (String) map.get("jobID"),(String) map.get("jobTitle"), (String) map.get("payment"), (String) map.get("startTime"),
                (String) map.get("skills"), (String) map.get("jobDescription"), (double) longLng , (double) longLat);
    }

    private void populateFields(JobData job){
        setText(title, job.getJobTitle());
        setText(payment, job.getPayment());
        setText(startTime, job.getStartTime());
        setText(desc, job.getJobDescription());

        String[] skills = job.getSkills().split(",");
        setText(skills1, skills[0]);
        if(skills.length > 1){
            setText(skills2, skills[1]);
        }
        if(skills.length > 2){
            setText(skills3, skills[2]);
        }

    }

    private void setText(EditText textBox, String text){
        textBox.setText(text);
    }

    private void submit(){
        DatabaseReference jobToPost = FirebaseDatabase.getInstance().getReference().child("Job Postings").child(key);

        jobToPost.child("jobDescription").setValue(desc.getText().toString());
        jobToPost.child("jobTitle").setValue(title.getText().toString());
        jobToPost.child("payment").setValue(payment.getText().toString());
        jobToPost.child("startTime").setValue(startTime.getText().toString());

        String[] skills = job.getSkills().split(",");

        if(skills.length > 2){
            jobToPost.child("skills").setValue(skills[0] + "," + skills[1] + "," + skills[2]);
        }
        else if(skills.length > 1){
            jobToPost.child("skills").setValue(skills[0] + "," + skills[1]);
        }
        else{
            jobToPost.child("skills").setValue(skills[0]);
        }

        Toast.makeText(JobEditActivity.this, "Successful", Toast.LENGTH_LONG).show();

        startActivity(new Intent(JobEditActivity.this, EmployerPageActivity.class));
    }

}
