package com.team6.quickcashteam6;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.Map;

// Reference Used: https://www.geeksforgeeks.org/how-to-populate-recyclerview-with-firebase-data-using-firebaseui-in-android-studio/

public class employeeJobAdapter extends FirebaseRecyclerAdapter<JobData, employeeJobAdapter.jobsViewholder> {

    private String id = new String();
    private float rating;
    private float rateNum;

    public employeeJobAdapter(@NonNull FirebaseRecyclerOptions<JobData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull jobsViewholder holder, int position, @NonNull JobData model) {
        holder.job_titles.setText(model.getJobTitle());
        holder.job_payments.setText(model.getPayment());
        holder.job_skills.setText(model.getSkills());
        holder.start_times.setText(model.getStartTime());
        holder.job_descriptions.setText(model.getJobDescription());
        holder.job_id.setText(model.getJobID());
        holder.lng.setText(Double.toString(model.getLng()));
        holder.lat.setText(Double.toString(model.getLat()));
        getStarsRating(model.jobID, holder);
        holder.ratingBarStars.setIsIndicator(true);
        holder.ratingBarStars.setClickable(false);

    }

    @NonNull
    @Override
    public jobsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_post_card, parent, false);
        getEmployeeName();
        return new employeeJobAdapter.jobsViewholder(view);
    }

    class jobsViewholder extends RecyclerView.ViewHolder {
        TextView job_titles;
        TextView job_payments;
        TextView job_skills;
        TextView start_times;
        TextView job_descriptions;
        TextView job_id;
        TextView lng;
        TextView lat;
        RatingBar ratingBarStars;
        Button job_location;
        Button apply;

        /*
        Each job is shown as a card view and all the details of one are taken from FireBase
        and put into each of these variables to be displayed to the employee in the
        AllJobsActivity class.
         */

        public jobsViewholder(@NonNull View itemView) {
            super(itemView);

            job_titles = itemView.findViewById(R.id.job_titles);
            job_payments = itemView.findViewById(R.id.job_payments);
            job_skills = itemView.findViewById(R.id.job_skills);
            start_times = itemView.findViewById(R.id.start_times);
            job_descriptions = itemView.findViewById(R.id.job_descriptions);
            job_id = itemView.findViewById(R.id.job_id);
            lng = itemView.findViewById(R.id.lng);
            lat = itemView.findViewById(R.id.lat);
            ratingBarStars = itemView.findViewById(R.id.JobPostCardRBtn);
            job_location = itemView.findViewById(R.id.job_location);
            job_location.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), ViewLocationMap.class);
                    String mapLng = lng.getText().toString();
                    String mapLat = lat.getText().toString();
                    intent.putExtra("lng", mapLng);
                    intent.putExtra("lat", mapLat);
                    view.getContext().startActivity(intent);
                }
            });

            apply = itemView.findViewById(R.id.apply_to_job);
            apply.setOnClickListener(this::ApplyOnClick);
        }

        public void ApplyOnClick(View view) {
            Toast.makeText(apply.getContext(), "Applied to Job Successfully!", Toast.LENGTH_SHORT).show();
            String jobTitle = job_titles.getText().toString();
            String thisID = MainActivity.employeeKey;
            String employeeName = MainActivity.employeeName;
            String employeePhone = MainActivity.employeePhone;
            MainActivity.jobID = job_id.getText().toString();

            DatabaseReference applicantToPost = FirebaseDatabase.getInstance().getReference().child("Applicants");
            MainActivity.applicantKey = applicantToPost.push().getKey();
            ApplicantData applicant = new ApplicantData(employeeName, thisID, jobTitle, employeePhone,  MainActivity.applicantKey, MainActivity.jobID);
            FirebaseDatabase.getInstance().getReference("Applicants/"+ MainActivity.applicantKey).setValue(applicant);
        }

    }

    public void getEmployeeName(){
        DatabaseReference employeeRef= FirebaseDatabase.getInstance().getReference().child("Employee").child(MainActivity.employeeKey);
        employeeRef.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Employee employee = snapshot.getValue(Employee.class);
                MainActivity.employeeName = employee.getName();
                MainActivity.employeePhone = employee.getPhone();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

    }

    public float getStarsRating(String jobID, jobsViewholder holder)  {
        DatabaseReference jobRef = FirebaseDatabase.getInstance().getReference().child("Job Postings").child(jobID).child("id");

        jobRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                String ID = (String) snapshot.getValue(String.class);
                id = ID;
                rating = getRating(id, holder);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rating;
    }

    public float getRating(String employerID, jobsViewholder holder)  {
        System.out.println("Employer ID: " + employerID);
        DatabaseReference employerRef = FirebaseDatabase.getInstance().getReference().child("Employer").child(employerID);
        System.out.println("In getRating");

        employerRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Map<String, Object> value = (Map<String, Object>) (snapshot.getValue());

                if ((value.get("Ratings").toString()) != null) {
                    System.out.println("Rating1: " + value.get("Ratings").toString());
                    rating = Float.parseFloat(value.get("Ratings").toString());
                    System.out.println("Rating2: " + rating);
                    rateNum = rating;
                    holder.ratingBarStars.setRating(rating);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        return rating;
    }
}
