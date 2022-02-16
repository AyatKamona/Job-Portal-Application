package com.team6.quickcashteam6;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

//Code inspiration from: https://github.com/ravizworldz/FirebasseAuth_Java
public class RegisterActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;

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
        String Email = getrEmail();
        final EditText rPassword = findViewById(R.id.rPassword);
        String Password = getrPassword();
        String errorMessage = "";
        if (isEmptyrEmail(Email)){
            errorMessage = "Email is empty";
        }
        if (isEmptyrPassword(Password)){
            errorMessage = "Password is empty";
        }
        //Loads register button
        Button registerButton = findViewById(R.id.buttonRegister);
        //Listener to check for user submission
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                signUp(rEmail.getText().toString(), rPassword.getText().toString());
            }
        });
    }

    protected String getrEmail(){
        EditText rEmail = findViewById(R.id.rEmail);
        return rEmail.getText().toString().trim();
    }
    protected static boolean isEmptyrEmail(String Email){
        return Email.isEmpty();
    }
    protected String getrPassword(){
        EditText rPassword = findViewById(R.id.rPassword);
        return rPassword.getText().toString().trim();
    }
    protected static boolean isEmptyrPassword(String Password){
        return Password.isEmpty();
    }

    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
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
}
