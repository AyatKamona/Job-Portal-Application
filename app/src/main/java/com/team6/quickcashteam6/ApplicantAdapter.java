package com.team6.quickcashteam6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
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

public class ApplicantAdapter extends FirebaseRecyclerAdapter<ApplicantData, ApplicantAdapter.applicantViewHolder> {

    public ApplicantAdapter(@NonNull FirebaseRecyclerOptions<ApplicantData> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull applicantViewHolder holder, int position, @NonNull ApplicantData model) {
        holder.applicantName.setText(model.getEmployeeName());
        holder.employeeID.setText(model.getEmployeeID());
        holder.jobTitle.setText(model.getJobTitle());

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
            employeeID = itemView.findViewById(R.id.applicantPhone);
            accept = itemView.findViewById(R.id.accept);
            accept.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View view) {
                    Toast.makeText(deny.getContext(), "Applicant Accepted", Toast.LENGTH_LONG).show();
                }
            });

            deny = itemView.findViewById(R.id.deny);
            deny.setOnClickListener(this::DenyOnClick);

        }

        public void DenyOnClick(View view) {
            Toast.makeText(deny.getContext(), "Applicant Denied", Toast.LENGTH_LONG).show();

        }

    }
}

