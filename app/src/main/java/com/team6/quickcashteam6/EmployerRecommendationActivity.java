package com.team6.quickcashteam6;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class EmployerRecommendationActivity extends AppCompatActivity implements View.OnClickListener  {

    private ArrayList<Employee> recommendedEmployees;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendation_activity);
        Button recmndButton = findViewById(R.id.RecommendButton);
        recmndButton.setOnClickListener(this);

    }


    public ArrayList<Employee> recommendation(ArrayList<String> skills, ArrayList<Employee> employees) {
        recommendedEmployees= new ArrayList<>();
        for (Employee employee: employees){
            int count=0;
            for (String skill : skills){

               if (employee.getSkills().contains(skill)){
                   count++;
               }
            }
            if (count>=2){
                recommendedEmployees.add(employee);
            }

        }
        return recommendedEmployees;
    }

    @Override
    public void onClick(View view) {
        ArrayList<Employee> testEmployees= new ArrayList<>();
        ArrayList<String> skills= new ArrayList<>();
        for (int i=0; i<10; i++){
            testEmployees.add(new Employee(String.valueOf(i)));
        }
        skills.add("Cleaning");
        skills.add("Furniture assembly");
        skills.add("Pet sitting");
        skills.add("Yard Work");
        skills.add("Heavy lifting ");
        skills.add("Electrician");
        skills.add("Plumbing");
        Random rand= new Random();

        for (Employee employee: testEmployees){
           for (int i=0; i<4;i++){
               int index= rand.nextInt(7);
               if (!employee.getSkills().contains(skills.get(index))){
                   employee.addSingleSkill(skills.get((index)));
               }
               else {
                   i--;
               }
          }
        }
        ArrayList<String> jobSkills= new ArrayList<>();
        jobSkills.add("Plumbing");
        jobSkills.add("Electrician");


        ArrayList<Employee> testRecommend= recommendation(jobSkills,testEmployees);
        for (Employee employee: testRecommend){
            System.out.println(employee.getName());
        }
    }
}