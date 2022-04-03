package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
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
    FirebaseAuth mAuth;
    EditText name;
    EditText age;
    EditText phone;
    Employer employer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.updateprofile);
        Intent intent = getIntent();
        employerKey= intent.getStringExtra("ID");
        name= findViewById(R.id.insert_name);
        age=findViewById(R.id.insert_Age);
        phone= findViewById(R.id.insert_Phone);
        ArrayList<JobData> jobsToShow= new ArrayList<>();
        DatabaseReference jobsRef = FirebaseDatabase.getInstance().getReference().child("Job Postings");

        RecyclerView recyclerView = findViewById(R.id.jobsRecyclingView);
        recyclerView.setLayoutManager(new WrapLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        DatabaseReference employerRef= FirebaseDatabase.getInstance().getReference().child("Employer").child(employerKey);
        employerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Map map = (Map<String,Object>) snapshot.getValue();
                employer = new Employer((String) map.get("id"),(String) map.get("name") );
                long longAge= (long) map.get("age");
                employer.setAge((int)longAge);
                employer.setPhone((String) map.get("phone"));
                name.setText(employer.getName());
                age.setText((String.valueOf(employer.getAge())));
                phone.setText(employer.getPhone());

                Button updateBtn= findViewById(R.id.sumbitUpdate);
                updateBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int ageConvert= Integer.parseInt(age.getText().toString());
                        employerRef.child("age").setValue(ageConvert);
                        employerRef.child("name").setValue(name.getText().toString());
                        employerRef.child("phone").setValue(phone.getText().toString());

                        Intent intent= new Intent(EditEmployerProfile.this,EmployerPageActivity.class);
                        intent.putExtra("ID",employerKey);
                        startActivity(intent);
                    }
                });



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
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
            String longLng= jobMap.get("lng").toString();
            String longLat= jobMap.get("lat").toString();
            JobData job = new JobData((String) jobMap.get("id"), (String) jobMap.get("jobID"),(String) jobMap.get("jobTitle"), (String) jobMap.get("payment"), (String) jobMap.get("startTime"),

                    (String) jobMap.get("skills"), (String) jobMap.get("jobDescription"), Double.parseDouble(longLng), Double.parseDouble(longLat), (String) jobMap.get("status"));
            jobs.add(job);
        }
        return jobs;


    }

}
