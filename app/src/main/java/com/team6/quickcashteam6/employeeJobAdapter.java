package com.team6.quickcashteam6;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

// Reference Used: https://www.geeksforgeeks.org/how-to-populate-recyclerview-with-firebase-data-using-firebaseui-in-android-studio/

public class employeeJobAdapter extends FirebaseRecyclerAdapter<JobData, employeeJobAdapter.jobsViewholder> {

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
        holder.lng.setText(Double.toString(model.getLng()));
        holder.lat.setText(Double.toString(model.getLat()));

    }

    @NonNull
    @Override
    public jobsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.job_post_card, parent, false);
        return new employeeJobAdapter.jobsViewholder(view);
    }

    class jobsViewholder extends RecyclerView.ViewHolder {
        TextView job_titles;
        TextView job_payments;
        TextView job_skills;
        TextView start_times;
        TextView job_descriptions;
        TextView lng;
        TextView lat;
        Button job_location;

        public jobsViewholder(@NonNull View itemView) {
            super(itemView);

            job_titles = itemView.findViewById(R.id.job_titles);
            job_payments = itemView.findViewById(R.id.job_payments);
            job_skills = itemView.findViewById(R.id.job_skills);
            start_times = itemView.findViewById(R.id.start_times);
            job_descriptions = itemView.findViewById(R.id.job_descriptions);
            lng = itemView.findViewById(R.id.lng);
            lat = itemView.findViewById(R.id.lat);
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
        }
    }
}
