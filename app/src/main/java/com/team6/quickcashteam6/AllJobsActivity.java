package com.team6.quickcashteam6;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllJobsActivity extends AppCompatActivity {

    private RecyclerView rView;
    private DatabaseReference jobPostReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_job_postings);

        rView = findViewById(R.id.recycler_for_jobs);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);
        rView.setHasFixedSize(true);
        rView.setLayoutManager(layoutManager);

        jobPostReference = FirebaseDatabase.getInstance().getReference().child("Job Postings");
        jobPostReference.keepSynced(true);
    }


    // Reference used: https://www.youtube.com/watch?v=xP1Ui8_nYWs
    @Override
    protected void onStart() {

        super.onStart();

        FirebaseRecyclerOptions<JobData> firebaseRecyclerOptions = new FirebaseRecyclerOptions.Builder<JobData>().setQuery(jobPostReference, JobData.class).build();

        FirebaseRecyclerAdapter<JobData, ViewJobPosts> adapter = new FirebaseRecyclerAdapter<JobData, ViewJobPosts>(firebaseRecyclerOptions){

            @NonNull
            @Override
            public ViewJobPosts onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_job_postings,parent,false);
                return new ViewJobPosts(view);
            }

            @Override
            protected void onBindViewHolder(@NonNull ViewJobPosts holder, int position, @NonNull JobData modelJob) {
                holder.setJobTitle(modelJob.getJobTitle());
                holder.setJobPayment(modelJob.getPayment());
                holder.setJobStartTime(modelJob.getStartTime());
                holder.setSkills(modelJob.getSkills());
                holder.setJobDescription(modelJob.getJobDescription());
            }

        };

        rView.setAdapter(adapter);

    }
    public static class ViewJobPosts extends RecyclerView.ViewHolder {

        View jobView;

        public ViewJobPosts(@NonNull View itemView) {
            super(itemView);
            jobView = itemView;
        }

        public void setJobTitle(String title) {
            TextView jobTitle = jobView.findViewById(R.id.job_title);
            jobTitle.setText(title);
        }

        public void setJobPayment(String payment) {
            TextView jobPayment = jobView.findViewById(R.id.job_payments);
            jobPayment.setText(payment);
        }

        public void setSkills(String skills) {
            TextView jobSkills = jobView.findViewById(R.id.job_skills);
            jobSkills.setText(skills);
        }

        public void setJobStartTime(String startTime) {
            TextView jobStartTime = jobView.findViewById(R.id.start_times);
            jobStartTime.setText(startTime);
        }

        public void setJobDescription(String description) {
            TextView jobDescription = jobView.findViewById(R.id.job_descriptions);
            jobDescription.setText(description);
        }

    }
}