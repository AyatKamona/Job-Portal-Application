package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class UserChoices extends AppCompatActivity implements View.OnClickListener {
    private CheckBox skill1,skill2,skill3,skill4,skill5,skill6,skill7,skill8,skill9,skill10;
    private FirebaseDatabase firebaseDB;
    private DatabaseReference firebaseDBEmployee;
    private DatabaseReference firebaseDBIDs;
    private DatabaseReference firebaseDBEmployer;
    private String UID;
    private  final String DB_URL= "https://quickcash-team6-default-rtdb.firebaseio.com/";
    public static ArrayList<String> skills = new ArrayList<>();
    public static String name;
    public static Employee employee_profile;
    public static Employer employer_profile;
    private FirebaseAuth mAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userchoice);

        Intent intent = getIntent();
        UID=intent.getStringExtra("ID");
        Button employeeButton = findViewById(R.id.employeeButton);
        EditText nameText = findViewById(R.id.nameTxtBox);
        mAuth = FirebaseAuth.getInstance();

        //Listener to check for user submission
        employeeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = nameText.getText().toString().trim();
                employee_profile = new Employee(mAuth.getUid(),name);
                employee_profile.setEmployee();
                LinearLayout layout= findViewById(R.id.linear);
                layout.setVisibility(View.VISIBLE);

                Button skillsButton = findViewById(R.id.skillsregister);
                skillsButton.setVisibility(View.VISIBLE);
                skillsButton.setOnClickListener(new View.OnClickListener() {

                    /*
                        Citation: https://abhiandroid.com/ui/checkbox
                     */
                    @Override
                    public void onClick(View view) {
                        int count=0;
                        skill1= findViewById(R.id.skill1);
                        skill2= findViewById(R.id.skill2);
                        skill3= findViewById(R.id.skill3);
                        skill4= findViewById(R.id.skill4);
                        skill5= findViewById(R.id.skill5);
                        skill6= findViewById(R.id.skill6);
                        skill7= findViewById(R.id.skill7);
                        skill8= findViewById(R.id.skill8);
                        skill9= findViewById(R.id.skill9);
                        skill10= findViewById(R.id.skill10);
                        if (skill1.isChecked()) {
                            skills.add("Cleaning");
                            count++;
                        }
                        if (skill2.isChecked()){
                            skills.add("Furniture assembly");
                            count++;
                        }
                        if (skill3.isChecked()){
                            skills.add("Pet sitting");
                            count++;
                        }
                        if (skill4.isChecked()){
                            skills.add("Yard Work");
                            count++;
                        }
                        if (skill5.isChecked()){
                            skills.add("Heavy lifting");
                            count++;
                        }
                        if (skill6.isChecked()){
                            skills.add("Electrician");
                            count++;
                        }
                        if (skill7.isChecked()){
                            skills.add("Delivery");
                            count++;
                        }
                        if (skill8.isChecked()){
                            skills.add("IT Help");
                            count++;
                        }
                        if (skill9.isChecked()){
                            skills.add("Baby sitting");
                            count++;
                        }
                        if (skill10.isChecked()){
                            skills.add("Tutor");
                            count++;
                        }
                        if (count>5){
                            Toast.makeText(getApplicationContext(),"Please choose only 5 skills",Toast.LENGTH_LONG).show();
                        }
                        else if (count==0){
                            Toast.makeText(getApplicationContext(),"Please choose at least one skill",Toast.LENGTH_LONG).show();
                        }
                        else if (name.equals("")){
                            Toast.makeText(UserChoices.this, "Please enter a name", Toast.LENGTH_LONG).show();
                            layout.setVisibility(View.GONE);
                            skillsButton.setVisibility(View.GONE);
                        }
                        else {
                            employee_profile.addSkills(skills);
                            layout.setVisibility(View.GONE);
                            skillsButton.setVisibility(View.GONE);
                            startActivity(new Intent(UserChoices.this, EmployeeRegisterProfileActivity.class));
                        }

                    }

                });

            }
        });
        Button employerButton = findViewById(R.id.employerButton);
        employerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = nameText.getText().toString().trim();

                if (name.equals("")){
                    Toast.makeText(UserChoices.this, "Please enter a name", Toast.LENGTH_LONG).show();
                } else  {
                    employer_profile = new Employer(mAuth.getUid(), name);
                    employer_profile.setEmployer();
                    startActivity(new Intent(UserChoices.this, EmployeeRegisterProfileActivity.class));

                }

            }
        });
    }
    @Override
    public void onClick(View view) {

    }

}
