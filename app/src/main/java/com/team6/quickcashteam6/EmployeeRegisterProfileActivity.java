package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class EmployeeRegisterProfileActivity extends AppCompatActivity {

    private FirebaseDatabase firebaseDB;
    private DatabaseReference firebaseDBEmployee;
    private DatabaseReference firebaseDBEmployer;
    private DatabaseReference firebaseDBIDs;
    private final String DB_URL = "https://quickcash-team6-default-rtdb.firebaseio.com/";
    private EditText age;
    private CheckBox gender_male;
    private CheckBox gender_female;
    private CheckBox gender_other;
    private EditText mobile;
    private Button submitProfile;
    private int checkedBoxes = 0;
    private Employee employee;
    private FirebaseAuth mAuth;
    String key;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employee_register_profile);

        mAuth = FirebaseAuth.getInstance();
        age = findViewById(R.id.insert_age);
        gender_male = findViewById(R.id.male);
        gender_female = findViewById(R.id.female);
        gender_other = findViewById(R.id.other);
        mobile = findViewById(R.id.insert_mobile);
        submitProfile = findViewById(R.id.register_employee_profile);

        submitProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkFieldsAreNotEmpty();
                if (UserChoices.employer_profile != null && UserChoices.employer_profile.isEmployer() && !UserChoices.employer_profile.isEmployee())  {
                    setEmployerInfo();
                    saveEmployer_ProfileToFirebase();
                }

                if ( UserChoices.employee_profile != null && UserChoices.employee_profile.isEmployee() && !UserChoices.employee_profile.isEmployer())  {
                    setEmployeeInfo();
                    addEmployeeToFireBase();
                }

                Intent loginPage= new Intent(EmployeeRegisterProfileActivity.this, LoginActivity.class);
                loginPage.putExtra("ID",key);
                startActivity(loginPage);

            }
        });
    }

    /*
    Adds the employer registration information to firebase
     */

    protected void saveEmployer_ProfileToFirebase()  {

        firebaseDB  = FirebaseDatabase.getInstance(DB_URL);
        firebaseDBEmployer= firebaseDB.getReference().child("Employer");
        key =firebaseDBEmployer.push().getKey();
        IDPairs pair= new IDPairs(mAuth.getUid(),key);
        firebaseDBIDs= firebaseDB.getReference().child("IDs");
        firebaseDBIDs.push().setValue(pair);
        firebaseDB.getReference("Employer/"+key).setValue(UserChoices.employer_profile);
    }

    /*
    Adds the employee registration information to firebase
     */

    public void addEmployeeToFireBase(){

        firebaseDB  = FirebaseDatabase.getInstance(DB_URL);
        firebaseDBEmployee= firebaseDB.getReference().child("Employee");
        key = firebaseDBEmployee.push().getKey();
        IDPairs pair = new IDPairs(mAuth.getUid(), key);
        firebaseDBIDs = firebaseDB.getReference().child("IDs");
        firebaseDBIDs.push().setValue(pair);
        firebaseDB.getReference("Employee/"+key).setValue(UserChoices.employee_profile);
    }

     /*
    setEmployerInfo() adds all the information retrieved from the employer interface
    to the employer object
     */

    public void setEmployerInfo()  {

        if (gender_male.isChecked())  {
            UserChoices.employer_profile.setGender("Male");
        }
        if (gender_female.isChecked())  {
            UserChoices.employer_profile.setGender("Female");
        }
        if (gender_other.isChecked())  {
            UserChoices.employer_profile.setGender("Other");
        }

        UserChoices.employer_profile.setAge(Integer.parseInt(age.getText().toString()));
        UserChoices.employer_profile.setPhone(mobile.getText().toString());
    }

    /*
    setEmployeeInfo() adds all the information retrieved from the employee interface
    to the employee object
     */

    public void setEmployeeInfo()  {

        if (gender_male.isChecked())  {
            UserChoices.employee_profile.setGender("Male");
        }
        if (gender_female.isChecked())  {
            UserChoices.employee_profile.setGender("Female");
        }
        if (gender_female.isChecked())  {
            UserChoices.employee_profile.setGender("Other");
        }

        UserChoices.employee_profile.setAge(Integer.parseInt(age.getText().toString()));
        UserChoices.employee_profile.setPhone(mobile.getText().toString());
    }

    /*
    This method uses Toast class to make sure all field are entered correctly
     */

    public void checkFieldsAreNotEmpty()  {
        System.out.println("start of check");

        String mobileDigits = mobile.getText().toString().trim();
        int user_Age = Integer.parseInt(age.getText().toString());

        if (gender_male.isChecked() && checkedBoxes == 0)  {
            checkedBoxes++;
        }
        if (gender_female.isChecked() && checkedBoxes == 0)  {
            checkedBoxes++;
        }
        if (gender_female.isChecked() && checkedBoxes == 0)  {
            checkedBoxes++;
        }

        if (age.getText().toString().trim().equals(""))  {
            Toast.makeText(EmployeeRegisterProfileActivity.this, "Please enter your  age", Toast.LENGTH_LONG).show();
        }

        if (user_Age < 16 || user_Age > 100)  {
            Toast.makeText(EmployeeRegisterProfileActivity.this, "Please enter a valid age", Toast.LENGTH_LONG).show();
        }

        if (checkedBoxes > 1)  {
            Toast.makeText(getApplicationContext(), "Please select 1 gender", Toast.LENGTH_LONG).show();
        }

        if (checkedBoxes < 1)  {
            Toast.makeText(getApplicationContext(), "Please choose your gender", Toast.LENGTH_LONG).show();
        }

        if (mobile.getText().toString().trim().equals("") || mobileDigits.length() != 10)  {
            Toast.makeText(EmployeeRegisterProfileActivity.this, "Please enter a valid phone number", Toast.LENGTH_LONG).show();
        }
    }
}
