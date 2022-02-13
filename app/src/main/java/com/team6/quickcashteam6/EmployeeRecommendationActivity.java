package com.team6.quickcashteam6;

import android.app.Notification;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.firebase.FirebaseError;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;

public class EmployeeRecommendationActivity extends AppCompatActivity implements View.OnClickListener {


    private static final String Fb_URL = "https://quickcash-team6-default-rtdb.firebaseio.com/";
    //ArrayList<JobData> jobsToBeSorted = new ArrayList<>();
    //ArrayList<JobData> sortedJobs = new ArrayList<>();
    ArrayList<Employee> users = new ArrayList<>();
    Map <String, Object> map;
    Employee employee;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_recommendation);
        Intent intent = getIntent();
       // employee = new Employee(RegisterActivity.userID, "Eli");
        employee.addSingleSkill("Responsible,");
        Button recmndButton = findViewById(R.id.jobNotify);
        recmndButton.setOnClickListener(this);




    }

    @Override
    public void onClick(View view) {

      //  retrieveJobsFromDB();
        //checkUserType();


    }


/*
    public void retrieveJobsFromDB() {
        FirebaseDatabase firebase = FirebaseDatabase.getInstance(Fb_URL);
        DatabaseReference ref = firebase.getReference("Job Postings");


        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                jobsToBeSorted = collectJobData(((Map<String, Object>) dataSnapshot.getValue()));
                sortedJobs = RecommendationService.employeeRecommendation(jobsToBeSorted, employee.getSkills());
                showJobs(sortedJobs);
                for (JobData job : sortedJobs) {
                    System.out.println(job.getJobTitle());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }

 */


    public void checkUserType() {

        /*
        Retrieve all employees and employers put them in an array and determine what instance of then use if statement to switch
         */

        FirebaseDatabase firebase = FirebaseDatabase.getInstance(Fb_URL);
        DatabaseReference ref = firebase.getReference("Employee");
        ArrayList<String> skills = new ArrayList<>();
        int i = 0;
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              //  users = collectEmployees(((Map<String, Object>) dataSnapshot.getValue()));
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
/*
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

 */

/*
    private ArrayList<JobData> collectJobData(Map<String, Object> map) {
        ArrayList<JobData> jobs = new ArrayList<>();

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            Map singleUser = (Map) entry.getValue();

            jobs.add(new JobData(((String) singleUser.get("jobTitle")), ((String) singleUser.get("payment")), ((String) singleUser.get("startTime")), ((String) singleUser.get("skills")), ((String) singleUser.get("jobDescription"))));
        }

        return jobs;
    }

 */

  /*  private void showJobs(ArrayList<JobData> jobs){
        TextView job1 = findViewById(R.id.textView1);
        TextView job2 = findViewById(R.id.textView2);
        TextView job3 = findViewById(R.id.textView3);
        if(jobs != null){
            job1.setText(jobs.get(0).getJobTitle());
            if(jobs.size() > 1){
                job2.setText(jobs.get(1).getJobTitle());
            }
            if(jobs.size() > 2){
                job3.setText(jobs.get(2).getJobTitle());
            }
        }
    }

   */

}

