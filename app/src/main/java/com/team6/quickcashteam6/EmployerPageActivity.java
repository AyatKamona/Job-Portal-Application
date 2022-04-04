package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class EmployerPageActivity extends AppCompatActivity implements View.OnClickListener {
    private FirebaseAuth mAuth;
    String employerID;
    public static String employerKey;
    private static final String Fb_URL = "https://quickcash-team6-default-rtdb.firebaseio.com/";
    ArrayList<IDPairs> allIDs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_page);
        Intent intent = getIntent();
        employerID = intent.getStringExtra("ID");
        if (employerID.charAt(0) == '-') {
            employerKey= employerID;
        }
        else {
            if (MainActivity.employeeKey==null){
                findEmployerKey();
            }

        }

       // MainActivity.employeeKey=employerKey;

        Button applicantPage = findViewById(R.id.showApplicants);
        applicantPage.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent applicants = new Intent(EmployerPageActivity.this, AllApplicantsActivity.class);
                startActivity(applicants);
            }
        });


        Button postJobButton = findViewById(R.id.postJobButton);
        postJobButton.setOnClickListener(this);

        Button moveToRecommend = findViewById(R.id.moveToRecommend);

        moveToRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getRecommendation = new Intent(EmployerPageActivity.this, EmployerRecommendationActivity.class);
                getRecommendation.putExtra("ID", employerKey);
                startActivity(getRecommendation);
            }
        });
        Button updateProfile = findViewById(R.id.updateProfile);
        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent updateProfileIntent= new Intent(EmployerPageActivity.this,EditEmployerProfile.class);
                updateProfileIntent.putExtra("ID",employerKey);
                startActivity(updateProfileIntent);
            }
        });

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser user = mAuth.getCurrentUser();

        Button logout = findViewById(R.id.logout);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                Intent reLogin = new Intent(EmployerPageActivity.this, MainActivity.class);
                startActivity(reLogin);
            }
        });

        Button moveToPayment = findViewById(R.id.moveToPayment);
        moveToPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent getPaypal = new Intent(EmployerPageActivity.this, PaypalActivity.class);
                startActivity(getPaypal);
            }
        });

        Button viewMyJobs= findViewById(R.id.viewPostedJobsButton);
        viewMyJobs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(EmployerPageActivity.this,ViewMyJobs.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View view) {
        openPostJobPage();
    }

    public void openPostJobPage() {
        Intent postJob = new Intent(EmployerPageActivity.this, PostJobActivity.class);
        postJob.putExtra("ID", employerKey);
        startActivity(postJob);
    }

    public void findEmployerKey() {
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();

        DatabaseReference ref = firebase.getReference().child("IDs");

        allIDs= new ArrayList<>();
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                allIDs= collectPairs((Map<String,Object>) snapshot.getValue());
                for (IDPairs pair:allIDs){
                    if (pair.getUserID().equals(employerID)){
                        employerKey=pair.getDatabaseKey();
                        MainActivity.employeeKey= pair.getDatabaseKey();
                        break;
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
    public ArrayList<IDPairs> collectPairs(Map<String,Object> data){
        ArrayList<IDPairs> IDs= new ArrayList<>();
        for (Map.Entry<String,Object> map:data.entrySet()){
            Map idMap=(Map) map.getValue();
            IDPairs pairs= new IDPairs((String) idMap.get("userID"),(String) idMap.get("databaseKey"));
            IDs.add(pairs);
        }
        return IDs;
    }
}

