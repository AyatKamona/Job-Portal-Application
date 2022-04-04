package com.team6.quickcashteam6;

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

public class ViewMyJobs extends AppCompatActivity {
    JobAdapter jobAdapter;
    String employerKey;
    Employer employer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_myjobs);
        ArrayList<JobData> jobsToShow = new ArrayList<>();
        DatabaseReference jobsRef = FirebaseDatabase.getInstance().getReference().child("Job Postings");

        RecyclerView recyclerView = findViewById(R.id.jobsRecyclingView);
        recyclerView.setLayoutManager(new WrapLinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        employerKey= MainActivity.employeeKey;
        DatabaseReference employerRef = FirebaseDatabase.getInstance().getReference().child("Employer").child(employerKey);

        jobsRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                ArrayList<JobData> jobs= collectJobs((Map<String,Object>) snapshot.getValue());
                for (JobData job: jobs){
                    if (job.getID().equals(employerKey)){
                        jobsToShow.add(job);
                    }
                }
                jobAdapter= new JobAdapter(ViewMyJobs.this,jobsToShow,"show");
                recyclerView.setAdapter(jobAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    public ArrayList<JobData> collectJobs (Map<String,Object> data){
        ArrayList<JobData> jobs= new ArrayList<>();
        for (Map.Entry<String,Object> map:data.entrySet()) {
            Map jobMap = (Map) map.getValue();
            String longLng= jobMap.get("lng").toString();
            String longLat= jobMap.get("lat").toString();
            JobData job = new JobData((String) jobMap.get("id"), (String) jobMap.get("jobID"),(String) jobMap.get("jobTitle"), (String) jobMap.get("payment"), (String) jobMap.get("startTime"),

                    (String) jobMap.get("skills"), (String) jobMap.get("jobDescription"), Double.parseDouble(longLng), Double.parseDouble(longLat),(String) jobMap.get("status"));
            job.employeeKey= (String) jobMap.get("employeeKey");
            jobs.add(job);
        }
        return jobs;


    }
}
