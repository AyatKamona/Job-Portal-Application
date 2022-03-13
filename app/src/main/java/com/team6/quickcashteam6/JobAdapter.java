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
    public JobAdapter (Context context, List<JobData> data){
        this.inflater= LayoutInflater.from(context);
        this.data= data;
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
        holder.description.setText(smallDescription);
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
        private final Context context;

        ViewHolder(View item) {
            super(item);
            description = item.findViewById(R.id.JName);
            jobEditBtn = item.findViewById(R.id.viewJobButton);
            context = item.getContext();
        }
    }



}
