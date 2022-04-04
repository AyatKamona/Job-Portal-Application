package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
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

public class EmployerRecommendationActivity extends AppCompatActivity  {

    private ArrayList<Employee> recommendedEmployees;
    private RecyclerView recyclerView;


    ArrayList<Employee> employees;
    EmployeeAdapter employeeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation_activity);
        recommendedEmployees= new ArrayList<>();
        getRecommendEmployees();
        init();

    }

    private void init() {
        recyclerView = findViewById(R.id.EmployeeList);
        recyclerView.setLayoutManager(new WrapLinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

    }



    private ArrayList<Employee> collectEmployees(Map<String, Object> data) {
        ArrayList<Employee> allEmployees = new ArrayList<>();
        for (Map.Entry<String, Object> map : data.entrySet()) {
            Map employeeMap = (Map) map.getValue();
            Employee employee = new Employee((String) employeeMap.get("id"), ((String) employeeMap.get("name")));
            employee.addSkills((ArrayList<String>) employeeMap.get("skills"));
            Long longAge = (Long) employeeMap.get("age");
            employee.setAge(longAge.intValue());
            if (( employeeMap.get("Ratings")) != null){
                employee.rating=  Float.parseFloat(employeeMap.get("Ratings").toString());
            }

            allEmployees.add(employee);

        }
        return allEmployees;
    }

    private void getRecommendEmployees() {
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference employeeRef = database.getReference("Employee");
        DatabaseReference jobRef = database.getReference("Job Postings");
        employeeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                employees = collectEmployees((Map<String, Object>) snapshot.getValue());
                jobRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Map<String, Object> jobsMap = ((Map<String, Object>) snapshot.getValue());
                        for (Map.Entry<String, Object> map : jobsMap.entrySet()) {
                            Map job = (Map) map.getValue();
                                if (job.get("id").equals(MainActivity.employeeKey)){
                                    String skills = (String) job.get("skills");
                                    ArrayList <Employee> tempEmployees =RecommendationService.employerRecommendation(skills,employees);
                                    for (int i=0; i<tempEmployees.size();i++){
                                        if (!recommendedEmployees.contains(tempEmployees.get(i))){
                                            recommendedEmployees.add(tempEmployees.get(i));
                                        }
                                    }


                                }

                        }
                        employeeAdapter= new EmployeeAdapter(EmployerRecommendationActivity.this,recommendedEmployees);
                        recyclerView.setAdapter(employeeAdapter);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



}