package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;


public class EditEmployerProfile extends AppCompatActivity  {
    JobAdapter jobAdapter;
    String employerKey;
    ArrayList<JobData> jobsToShow;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateprofile);
        Intent intent = getIntent();
        employerKey= intent.getStringExtra("ID");
        ArrayList<JobData> jobsToShow= new ArrayList<>();
        DatabaseReference jobsRef = FirebaseDatabase.getInstance().getReference().child("Job Postings");

        RecyclerView recyclerView = findViewById(R.id.jobsRecyclingView);
        recyclerView.setLayoutManager(new WrapLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        jobsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
               ArrayList<JobData> jobs= collectJobs((Map<String,Object>) snapshot.getValue());
               for (JobData job: jobs){
                   if (job.getID().equals(employerKey)){
                       jobsToShow.add(job);
                   }
               }
                jobAdapter= new JobAdapter(EditEmployerProfile.this,jobsToShow);
                recyclerView.setAdapter(jobAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

      //  jobs.add(new JobData("Plumbing","$20","7pm","Hard Labour","I need someone to fix my bathroom"));
      //  jobs.add(new JobData("Moving","$30","3pm","Hard Labour, lifting","I need someone to help me with moving my furniture"));
     //   jobs.add(new JobData("Pet Sitting","$50","1pm","Gentle, Respectful","Need someone to look after my dog while im gone"));

    }
    public ArrayList<JobData> collectJobs (Map<String,Object> data){
        ArrayList<JobData> jobs= new ArrayList<>();
        for (Map.Entry<String,Object> map:data.entrySet()) {
            Map jobMap = (Map) map.getValue();
           long longLng= (long)jobMap.get("lng");
           long longLat= (long) jobMap.get("lat");
            JobData job = new JobData((String) jobMap.get("id"), (String) jobMap.get("jobID"),(String) jobMap.get("jobTitle"), (String) jobMap.get("payment"), (String) jobMap.get("startTime"),
                    (String) jobMap.get("skills"), (String) jobMap.get("jobDescription"), (double) longLng , (double) longLat);
            jobs.add(job);
        }
        return jobs;


    }

}
