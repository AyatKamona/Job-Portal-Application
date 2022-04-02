package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class currentJobActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_job);

        Toast.makeText(currentJobActivity.this, MainActivity.currentJobID, Toast.LENGTH_LONG).show();

        if (MainActivity.currentJobID != null) {
            fillCurrentJobDetails();
        }

        Button completeJobButton= findViewById(R.id.completeButton);
        completeJobButton.setOnClickListener(this::onCompleteClick);

    }

    public void fillCurrentJobDetails(){

        DatabaseReference currentJobRef = FirebaseDatabase.getInstance().getReference().child("Job Postings").child(MainActivity.currentJobID);
        currentJobRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                JobData currentJob = snapshot.getValue(JobData.class);
                assert currentJob != null;

                TextView currentJobTitle = findViewById(R.id.currentJobTitle);
                TextView currentJobDescription = findViewById(R.id.currentJobDescription);
                TextView currentJobSkills = findViewById(R.id.currentJobSkills);
                TextView currentJobPayment = findViewById(R.id.currentJobPayment);
                TextView currentJobStartTime = findViewById(R.id.currentJobStartTime);
                TextView currentJobStatus = findViewById(R.id.currentJobStatus);

                currentJobTitle.setText(currentJob.getJobTitle());
                currentJobDescription.setText(currentJob.getJobDescription());
                currentJobSkills.setText(currentJob.getSkills());
                currentJobPayment.setText(currentJob.getPayment());
                currentJobStartTime.setText(currentJob.getStartTime());
                currentJobStatus.setText(currentJob.getStatus());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });
    }

    public void onCompleteClick(View view){
        DatabaseReference jobRef = FirebaseDatabase.getInstance().getReference().child("Job Postings").child(MainActivity.currentJobID);
        jobRef.child("status").setValue("Complete");

        DatabaseReference deleteCurrentJob = FirebaseDatabase.getInstance().getReference().child("Employee").child(MainActivity.employeeKey);
        deleteCurrentJob.child("currentJobID").setValue(null);
        MainActivity.currentJobID = null;

    }

}
