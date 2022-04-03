package com.team6.quickcashteam6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

// Reference Used: https://www.geeksforgeeks.org/how-to-populate-recyclerview-with-firebase-data-using-firebaseui-in-android-studio/

public class ApplicantAdapter extends FirebaseRecyclerAdapter<ApplicantData, ApplicantAdapter.applicantViewHolder> {

    public ApplicantAdapter(@NonNull FirebaseRecyclerOptions<ApplicantData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull applicantViewHolder holder, int position, @NonNull ApplicantData model) {
        holder.applicantName.setText(model.getEmployeeName());
        holder.jobTitle.setText(model.getJobTitle());
        holder.phoneNumber.setText(model.getEmployeePhone());
        holder.key.setText(model.getKey());
        holder.jobID.setText(model.getJobID());
        holder.employeeID.setText(model.getEmployeeID());

    }

    @NonNull
    @Override
    public applicantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.applicants_item_view, parent, false);
        return new ApplicantAdapter.applicantViewHolder(view);

    }

    class applicantViewHolder extends RecyclerView.ViewHolder {
        TextView jobTitle;
        TextView applicantName;
        TextView phoneNumber;
        TextView key;
        TextView jobID;
        TextView employeeID;
        Button accept;
        Button deny;

        /*
        Each job is shown as a card view and all the details of one are taken from FireBase
        and put into each of these variables to be displayed to the employee in the
        AllJobsActivity class.
         */

        public applicantViewHolder(@NonNull View itemView) {
            super(itemView);

            jobTitle = itemView.findViewById(R.id.jobTitle);
            applicantName = itemView.findViewById(R.id.applicantName);
            phoneNumber = itemView.findViewById(R.id.applicantPhone);
            key = itemView.findViewById(R.id.applicantKey);
            jobID = itemView.findViewById(R.id.jobKey);
            employeeID = itemView.findViewById(R.id.employeeID);
            accept = itemView.findViewById(R.id.accept);
            accept.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Toast.makeText(deny.getContext(), "Applicant Accepted", Toast.LENGTH_LONG).show();

                    MainActivity.jobID = jobID.getText().toString();
                    DatabaseReference jobRef = FirebaseDatabase.getInstance().getReference().child("Job Postings").child(MainActivity.jobID);
                    jobRef.child("status").setValue("In-Progress");

                    DatabaseReference employeeRef = FirebaseDatabase.getInstance().getReference().child("Employee").child(employeeID.getText().toString());
                    employeeRef.child("currentJobID").setValue(jobID.getText().toString());

                    DatabaseReference deletePublicRef = FirebaseDatabase.getInstance().getReference().child("Public Database").child(jobTitle.getText().toString());
                    deletePublicRef.removeValue();;

                    MainActivity.applicantKey = key.getText().toString();
                    DatabaseReference removeRef = FirebaseDatabase.getInstance().getReference().child("Applicants").child(MainActivity.applicantKey);
                    removeRef.removeValue();

                    FirebaseDatabase.getInstance().getReference("Job Postings/"+ MainActivity.jobID+"/employeeKey").setValue(employeeID.getText().toString());
                }
            });

            deny = itemView.findViewById(R.id.deny);
            deny.setOnClickListener(this::DenyOnClick);

        }

        public void DenyOnClick(View view) {
            MainActivity.applicantKey = key.getText().toString();
            Toast.makeText(deny.getContext(), "Applicant Denied", Toast.LENGTH_LONG).show();
            if(MainActivity.applicantKey !=null) {
                DatabaseReference ref = FirebaseDatabase.getInstance().getReference().child("Applicants").child(MainActivity.applicantKey);
                ref.removeValue();
            }
        }

    }


}


