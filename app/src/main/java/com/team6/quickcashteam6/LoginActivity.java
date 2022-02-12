package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {
    private FirebaseDatabase firebaseDB;
    private DatabaseReference firebaseDBEmployee;
    private DatabaseReference firebaseDBEmployer;
    private  final String DB_URL= "https://quickcash-team6-default-rtdb.firebaseio.com/";
    private FirebaseAuth mAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Instance variables
        final EditText lEmail = findViewById(R.id.lEmail);
        final EditText lPassword =  findViewById(R.id.lPassword);
        //Load state
        super.onCreate(savedInstanceState);
        //Load view
        setContentView(R.layout.activity_main);
        //Adds login button
        mAuth = FirebaseAuth.getInstance();Button login = findViewById(R.id.buttonLogin);
        //listener to check for user submission
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login(lEmail.getText().toString(),lPassword.getText().toString());

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

    private void initializeDB()  {

    }
}