package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.Map;

public class EmployeeRecommendationActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String Fb_URL = "https://quickcash-team6-default-rtdb.firebaseio.com/";
    ArrayList<JobData> jobsToBeSorted = new ArrayList<>();
    ArrayList<JobData> sortedJobs = new ArrayList<>();
    ArrayList<Employee> users = new ArrayList<>();
    Map <String, Object> map;
    Employee employee;
    String employeeID;
    //Button notifyBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_recommendation);
        Intent intent = getIntent();
        employeeID= intent.getStringExtra("ID");
        Button recmndButton = findViewById(R.id.jobNotify);
        recmndButton.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {
        retrieveJobsFromDB();
        //checkUserType();
    }

    public void retrieveJobsFromDB() {
        FirebaseDatabase firebase = FirebaseDatabase.getInstance(Fb_URL);
        DatabaseReference ref = firebase.getReference("Job Postings");
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobsToBeSorted = collectJobData(((Map<String, Object>) dataSnapshot.getValue()));
                sortedJobs = RecommendationService.employeeRecommendation(jobsToBeSorted, employee.getSkills());
                for (JobData job : sortedJobs) {
                    System.out.println(job.getJobTitle());
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });
    }

    /*
    This methods is referenced "Retrieving Java object data from Firebase in Android Studio"
    URL: https://www.youtube.com/watch?v=aPLh31MWewc&ab_channel=BrandanJones
    Author: Brendan Jones
    Accessed: 12/02/2022
     */
    private ArrayList<Employee> collectEmployees(Map<String, Object> value) {
        ArrayList<Employee> employee = new ArrayList<>();
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            Map singleUser = (Map) entry.getValue();
            Employee employee1 = new Employee((String) singleUser.get("id"), (String) singleUser.get("name"));
            employee1.addSkills((ArrayList<String>) singleUser.get("skills"));
            employee.add(employee1);
        }
        return employee;
    }
    private ArrayList<JobData> collectJobData(Map<String, Object> map) {
        ArrayList<JobData> jobs = new ArrayList<>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Map singleUser = (Map) entry.getValue();
            jobs.add(new JobData("mm","kk",((String) singleUser.get("jobTitle")), ((String) singleUser.get("payment")), ((String) singleUser.get("startTime")), ((String) singleUser.get("skills")), ((String) singleUser.get("jobDescription")), ((double) singleUser.get("lng")), ((double) singleUser.get("lat"))
            , ((String) singleUser.get("status"))));
        }

        return jobs;
    }

}
