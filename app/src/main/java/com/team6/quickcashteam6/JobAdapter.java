/*
    * Citation:
    * URL: https://git.cs.dal.ca/dashah/csci3130firebasecrud
    * Lines: all code
    * Description: The code is inspired by the firebase tutorial and the firebasecrud project
*/
package com.team6.quickcashteam6;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class JobAdapter extends RecyclerView.Adapter<JobAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<JobData> data;
    String purpose;
    public JobAdapter (Context context, List<JobData> data,String purpose){
        this.inflater= LayoutInflater.from(context);
        this.data= data;
        this.purpose= purpose;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       final View view = inflater.inflate(R.layout.jobitem,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String smallDescription=data.get(position).jobDescription;
        String status = data.get(position).status;
        holder.description.setText(smallDescription);
        if (purpose.equals("show") && status.equals("Complete")){
            holder.jobEditBtn.setVisibility(View.GONE);
            holder.rateBtn.setVisibility(View.VISIBLE);
        }
        
        holder.rateBtn.setOnClickListener(view-> {
            final Intent rateIntent = new Intent(holder.context,RatingEmployeePage.class);
            rateIntent.putExtra("key",data.get(position).employerKey);
            rateIntent.putExtra("employeeKey",data.get(position).employeeKey);
            holder.context.startActivity(rateIntent);
        });

        holder.jobEditBtn.setOnClickListener(view-> {
          final Intent intent = new Intent(holder.context, JobEditActivity.class);
            intent.putExtra("key", data.get(position).getJobID());
            holder.context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView description;
        Button jobEditBtn;
        Button rateBtn;
        private final Context context;

        ViewHolder(View item) {
            super(item);
            description = item.findViewById(R.id.JName);
            jobEditBtn = item.findViewById(R.id.viewJobButton);
            rateBtn= item.findViewById(R.id.rateBtn);
            context = item.getContext();
        }
    }



}
