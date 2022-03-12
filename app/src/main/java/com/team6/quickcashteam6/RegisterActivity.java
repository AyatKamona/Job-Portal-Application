package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
    public static String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //Loads state
        super.onCreate(savedInstanceState);
        //Loads view
        setContentView(R.layout.activity_register);
        //Loads firebase
        mAuth = FirebaseAuth.getInstance();
        //Instance variables
        final EditText rEmail = findViewById(R.id.rEmail);
        final EditText rPassword = findViewById(R.id.rPassword);
        //Loads register button

        Button registerButton = findViewById(R.id.buttonRegister);
        //Listener to check for user submission
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(rEmail.getText().toString(), rPassword.getText().toString());
                switch2UserChoices();
            }
        });


    }

     private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            //Message upon registration success
                            Log.d("RegistrationActivity", "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();

                            Toast.makeText(RegisterActivity.this, "Authentication Success." + user.getEmail(),
                                    Toast.LENGTH_SHORT).show();

                            finish();

                        } else {
                            //Message upon registration failure
                            Log.w("RegistrationActivity", "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    private void switch2UserChoices()  {
        userID = mAuth.getCurrentUser().getUid();
        Intent switch2UserChoices = new Intent(this, UserChoices.class);
        switch2UserChoices.putExtra("ID",userID);
        startActivity(switch2UserChoices);
    }
}