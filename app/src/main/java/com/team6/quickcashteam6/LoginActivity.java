
package com.team6.quickcashteam6;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;
//Code inspiration from: https://github.com/ravizworldz/FirebasseAuth_Java

public class LoginActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDB;
    private DatabaseReference firebaseDBEmployee;
    private DatabaseReference firebaseDBEmployer;
    private  final String DB_URL= "https://quickcash-team6-default-rtdb.firebaseio.com/";
    ArrayList<Employee> employees = new ArrayList<>();
    ArrayList<Employer> employers = new ArrayList<>();
    String typeOfUser = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instance variables
        //Load state
        super.onCreate(savedInstanceState);
        //Load view
        setContentView(R.layout.activity_main);
        //Adds login button
        mAuth = FirebaseAuth.getInstance();Button login = findViewById(R.id.buttonLogin);
        final EditText lEmail = findViewById(R.id.lEmail);
        String Email = getEmail();
        final EditText lPassword =  findViewById(R.id.lPassword);
        String Password = getPassword();
        String errorMessage = "";
        if (isEmptyEmail(Email)){
            errorMessage = "Email is empty";
        }
        if (isEmptyPassword(Password)){
            errorMessage = "Password is empty";
        }
        //listener to check for user submission
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(lEmail.getText().toString(),lPassword.getText().toString());
                checkUserType();
                switch2UserPage();
            }
        });
        //Loads register button
        TextView registerL = findViewById(R.id.registrationLink);
        //listener to check for user registration
        registerL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });
    }
    protected String getEmail(){
        EditText lEmail = findViewById(R.id.lEmail);
        return lEmail.getText().toString().trim();
    }
    protected static boolean isEmptyEmail(String Email){
        return Email.isEmpty();
    }
    protected String getPassword(){
        EditText lPassword = findViewById(R.id.lPassword);
        return lPassword.getText().toString().trim();
    }
    protected static boolean isEmptyPassword(String Password){
        return Password.isEmpty();
    }
    private void login(String email, String password) {
        //Authnticates user credentials
        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    //Toast upon sign in success
                    Log.d("LoginActivity", "signInWithEmail:success");
                    FirebaseUser user = mAuth.getCurrentUser();
                    Toast.makeText(LoginActivity.this, "You are now logged in!" + user.getEmail(), Toast.LENGTH_SHORT).show();
                    //startActivity(new Intent(LoginActivity.this, ProfileActivity.class));
                    finish();
                } else {
                    //Error message on sign in failure
                    Log.w("LoginActivity", "signInWithEmail:failure", task.getException());
                    Toast.makeText(LoginActivity.this, "ERROR: Login details invalid", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void checkUserType() {

        /*
        Retrieve all employees and employers put them in an array and determine what instance of then use if statement to switch
         */

        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        DatabaseReference employeeRef = firebase.getReference("Employee");
        DatabaseReference employerRef = firebase.getReference("Employer");

        employeeRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                employees = collectEmployees(((Map<String, Object>) dataSnapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }


        });

        employerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                employers = collectEmployers(((Map<String, Object>) snapshot.getValue()));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }

    private ArrayList<Employee> collectEmployees(Map<String, Object> value) {
        String usertype = "";
        ArrayList<Employee> employeesList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            Map singleUser = (Map) entry.getValue();

            Employee employee = new Employee((String) singleUser.get("id"), (String) singleUser.get("name"));
            employeesList.add(employee);
        }


        return employeesList;

    }

    private ArrayList<Employer> collectEmployers(Map<String, Object> value) {
        String usertype = "";
        ArrayList<Employer> employersList = new ArrayList<>();
        for (Map.Entry<String, Object> entry : value.entrySet()) {
            Map singleUser = (Map) entry.getValue();

            Employer employer = new Employer((String) singleUser.get("id"), (String) singleUser.get("name"));
            employersList.add(employer);
        }


        return employersList;

    }

    public void switch2UserPage()  {
        System.out.println((mAuth.getCurrentUser().getUid()));
        String user = "";
        for (Employee emply: employees) {
            System.out.println(emply.getID() + " ------------ " + mAuth.getCurrentUser().getUid());
            if (emply.getID().equals(mAuth.getCurrentUser().getUid()))  {
                user = "Employee";
            }
        }

        for (Employer emplyer: employers) {

            if (emplyer.getID().equals(mAuth.getCurrentUser().getUid()))  {

                user = "Employer";
            }

        }
        if (user.equals("Employee"))  {
            startActivity(new Intent(LoginActivity.this, EmployeeRecommendationActivity.class));
        } else  {
            startActivity(new Intent(LoginActivity.this, EmployerPageActivity.class));
        }
    }



}

