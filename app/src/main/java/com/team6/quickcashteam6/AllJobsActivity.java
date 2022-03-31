package com.team6.quickcashteam6;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.database.DatabaseReference;

// Reference Used: https://www.geeksforgeeks.org/how-to-populate-recyclerview-with-firebase-data-using-firebaseui-in-android-studio/

public class AllJobsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    employeeJobAdapter adapter;
    DatabaseReference publicBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_job_postings);

        /*
        Retrieving the posted jobs from public database instead of the regular one as it will not work
        otherwise. The regular "Job Postings" database is secure and will not allow retrieval.
         */
        publicBase = FirebaseDatabase.getInstance().getReference().child("Public Database");

        recyclerView = findViewById(R.id.recycler_for_jobs);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<JobData> options = new FirebaseRecyclerOptions.Builder<JobData>().setQuery(publicBase, JobData.class).build();

        adapter = new employeeJobAdapter(options);
        recyclerView.setAdapter(adapter);
    }

    @Override protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

}