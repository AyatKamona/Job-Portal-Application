package com.team6.quickcashteam6;



import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class EmployeeAdapter extends RecyclerView.Adapter<EmployeeAdapter.ViewHolder> {
    LayoutInflater inflater;
    List<Employee> data;

    public EmployeeAdapter (Context context, List<Employee> data){
        this.inflater= LayoutInflater.from(context);
        this.data= data;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View view = inflater.inflate(R.layout.jobitem_recommendation,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String name = data.get(position).getName();
        int age= data.get(position).getAge();
        float rate= data.get(position).rating;

        holder.name.setText(name);
        holder.age.setText(String.valueOf(age) );
        holder.rate.setRating(rate);



    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        TextView age;
        Button viewProfile;
        RatingBar rate;
        private final Context context;

        ViewHolder(View item) {
            super(item);
            name= item.findViewById(R.id.EName);
            age= item.findViewById(R.id.EDescription);
            viewProfile= item.findViewById(R.id.viewEmployeeButton);
            rate= item.findViewById(R.id.RecommedationEmployeeRate);
            context = item.getContext();
        }
    }

}
