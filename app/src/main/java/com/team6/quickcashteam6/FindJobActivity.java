package com.team6.quickcashteam6;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class FindJobActivity extends AppCompatActivity implements View.OnClickListener {

    private DatabaseReference displayJobs;
    private List<JobData> jobsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_findjob);

        displayJobs = FirebaseDatabase.getInstance().getReference("Job Postings");
        jobsList = new ArrayList<>();

        displayJobs.addListenerForSingleValueEvent(valueEventListener);

        Button findJobButton = findViewById(R.id.findJobButton);
        findJobButton.setOnClickListener(this);

        Button addLocationButton = findViewById(R.id.mapButton);
        addLocationButton.setOnClickListener(this::onLocationBtnClick);
    }

    protected void setErrorMessage(String message){
        TextView errorMessage = findViewById(R.id.errorMessage);
        errorMessage.setText(message.trim());
    }

    // Display job postings button press
    @Override
    public void onClick(View view){

    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(DataSnapshot dataSnapshot) {
            jobsList.clear();
            if (dataSnapshot.exists()) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    JobData artist = snapshot.getValue(JobData.class);
                    jobsList.add(artist);
                }
            }
        }

        @Override
        public void onCancelled(DatabaseError databaseError) {

        }
    };

    // Add location button press
    public void onLocationBtnClick(View view){
        Intent map = new Intent(FindJobActivity.this, MapsActivity.class);
        map.putExtra("ID", getEmployerID());
        startActivity(map);
    }

    public void openUserPage() {
        Intent findJob = new Intent(FindJobActivity.this, User.class);
    }

    /*
    protected String getID(){
        EditText insert_ID = findViewById(R.id.insert_ID);
        return insert_ID.getText().toString().trim();
    }
    */

    protected String getStartTime(){
        EditText insert_start_time = findViewById(R.id.insert_start_time);
        return insert_start_time.getText().toString().trim();
    }

    protected double getLat(){
        return MainActivity.jobLatitude;
    }

    protected double getLng(){
        return MainActivity.jobLongtitute;
    }
    protected String getEmployerID(){
        Intent intent= getIntent();
        return intent.getStringExtra("ID");
    }

    protected static boolean isEmptyStartTime(String startTime){
        return startTime.isEmpty();
    }

}
