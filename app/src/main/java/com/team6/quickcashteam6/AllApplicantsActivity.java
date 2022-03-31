package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AllApplicantsActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    ApplicantAdapter adapter;
    DatabaseReference applicantsDBase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.applicants_page);

        applicantsDBase = FirebaseDatabase.getInstance().getReference().child("Applicants");

        recyclerView = (RecyclerView)findViewById(R.id.applicantsRecyclingView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<ApplicantData> options = new FirebaseRecyclerOptions.Builder<ApplicantData>().setQuery(applicantsDBase, ApplicantData.class).build();

        adapter = new ApplicantAdapter(options);
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
