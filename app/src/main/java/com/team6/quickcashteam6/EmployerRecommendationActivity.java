package com.team6.quickcashteam6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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

public class EmployerRecommendationActivity extends AppCompatActivity implements View.OnClickListener  {

    //private ArrayList<Employee> recommendedEmployees = new ArrayList<>();
    private RecyclerView recyclerView;
    //private EmployerRecommendationAdapter recommendationAdapter;
    private ArrayList<Employee> recommendedEmployees ;
    ArrayList<Employee> employees;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.employeeitem);
      //  init();
        recommendedEmployees= new ArrayList<>();


        Button recmndButton = findViewById(R.id.RecommendButton1);
        recmndButton.setOnClickListener(this);

    }
    private void init(){
        recyclerView = findViewById(R.id.EmployeeList);
        //recyclerView.setLayoutManager(new WrapLinearLayoutManger(this, LinearLayoutManager.VERTICAL,false));
        LinearLayoutManager linearLayoutManager= new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(linearLayoutManager);
    }
    private void connectToFirebaseDB(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
     //   DatabaseReference jobRef= database.getReference("job Postings");
    //    DatabaseReference employeeRef= database.getReference("Employee");
       // getRecommenedEmployees();
      //  recommendationAdapter= new EmployerRecommendationAdapter(EmployerRecommendationActivity.this,recommendedEmployees);
      //  recyclerView.setAdapter(recommendationAdapter);

    }

    private ArrayList<Employee> collectEmployees(Map<String,Object> data){
        ArrayList<Employee> allEmployees= new ArrayList<>();
        for (Map.Entry<String,Object> map: data.entrySet()){
            Map employeeMap = (Map) map.getValue();
            Employee employee= new Employee((String) employeeMap.get("id"), ((String)employeeMap.get("name")));
            employee.addSkills((ArrayList<String>) employeeMap.get("skills"));
            allEmployees.add(employee);

        }
        return allEmployees;
    }
    private void getRecommendEmployees(){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference employeeRef= database.getReference("Employee");
        DatabaseReference jobRef= database.getReference("Job Postings");
               employeeRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        employees= collectEmployees((Map<String,Object>) snapshot.getValue());
                        jobRef.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Map<String,Object> jobsMap= ((Map<String,Object>) snapshot.getValue());
                                for (Map.Entry<String,Object> map: jobsMap.entrySet()){
                                    Map job = (Map) map.getValue();

                                    if (((String) map.getKey()).equals("-Mvj4JmACXsCddWw91gJ")){
                                        String skills = (String) job.get("skills");
                                        recommendedEmployees= RecommendationService.employerRecommendation(skills,employees);
                                        break;
                                    }
                                }
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
    @Override
    public void onClick(View view) {
        /*
        EditText name1,  name2;
        EditText skills1,skill2 ;
        EditText email1,email2 ;
        name1= findViewById(R.id.employeeName);
        name2= findViewById(R.id.employeeName1);
        skills1= findViewById(R.id.employeeSkills);
        skill2 = findViewById(R.id.employeeSkills1);
        email1= findViewById(R.id.employeeEmail);
        email2= findViewById(R.id.employeeEmail1);
        //connectToFirebaseDB();
        getRecommendEmployees();
        name1.setText("Name: "+ recommendedEmployees.get(0).getName());
        name2.setText(recommendedEmployees.get(1).getName());
        String skillse1="";
        for (int i=0; i<recommendedEmployees.get(0).getSkills().size();i++){
            skillse1+=recommendedEmployees.get(0).getSkills().get(i);
        }
        skillse1="";
        for (int i=0; i<recommendedEmployees.get(1).getSkills().size();i++){
            skillse1+=recommendedEmployees.get(1).getSkills().get(i);
        }
        skills1.setText(skillse1);
        email1.setText(recommendedEmployees.get(0).getEmail());
        email2.setText(recommendedEmployees.get(1).getEmail());
        LinearLayout linear1= findViewById(R.id.linearLayout1);
        LinearLayout linear2= findViewById(R.id.linearLayout2);
        linear1.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.VISIBLE);
        //  recommendationAdapter.startListening();
*/
        LinearLayout linear1= findViewById(R.id.linearLayout1);
        LinearLayout linear2= findViewById(R.id.linearLayout2);
        linear1.setVisibility(View.VISIBLE);
        linear2.setVisibility(View.VISIBLE);
        Button button= findViewById(R.id.RecommendButton1);
        button.setVisibility(View.GONE);

    }
}