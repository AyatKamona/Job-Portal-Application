package com.team6.quickcashteam6;

import android.app.Notification;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
import java.util.Random;

public class EmployeeRecommendationActivity extends AppCompatActivity implements View.OnClickListener {

    ArrayList<JobData> jobsToBeSorted = new ArrayList<>();
    ArrayList<JobData> sortedJobs = new ArrayList<>();
    Employee employee;
    Button notifyBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation_activity);
        Button recmndButton = findViewById(R.id.RecommendButton);
        recmndButton.setOnClickListener(this);

        notifyBtn = findViewById(R.id.jobNotify);
        notifyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NotificationCompat.Builder builder = new NotificationCompat.Builder(EmployeeRecommendationActivity.this, "My notify");
                builder.setContentTitle("New Job Matches your skills");
              //  builder.setContentText()
            }
        });

        employee = new Employee("Eli");
        employee.addSingleSkill("Responsible");


    }

    @Override
    public void onClick(View view) {


        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebase.getReference("Job Posting");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    jobsToBeSorted = collectJobData(((Map<String, Object>) dataSnapshot.getValue()));
                    sortedJobs = RecommendationService.employeeRecommendation(jobsToBeSorted, employee.getSkills());

                    for (JobData job : sortedJobs){
                        System.out.println(job.getJobTitle());
                    }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private ArrayList<JobData> collectJobData(Map<String, Object> value){
        ArrayList<JobData> jobs = new ArrayList<>();

        for(Map.Entry<String, Object> entry : value.entrySet()){
            Map singleUser = (Map) entry.getValue();

            jobs.add(new JobData(((String) singleUser.get("jobTitle")), ((String) singleUser.get("payment")), ((String) singleUser.get("startTime")), ((String) singleUser.get("skills")), ((String) singleUser.get("jobDescription"))));
        }

        return jobs;
    }
}
