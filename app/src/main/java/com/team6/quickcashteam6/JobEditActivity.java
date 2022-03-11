package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class JobEditActivity extends AppCompatActivity implements View.OnClickListener {

    EditText title;
    EditText payment;
    EditText startTime;
    EditText skills1;
    EditText skills2;
    EditText skills3;
    EditText desc;

    JobData job;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_job_edit);
        // Intent
        title = findViewById(R.id.insert_job_title);
        payment = findViewById(R.id.insert_job_payment);
        startTime = findViewById(R.id.insert_start_time);
        skills1 = findViewById(R.id.skill1);
        skills2 = findViewById(R.id.skill2);
        skills3 = findViewById(R.id.skill3);
        desc = findViewById(R.id.insert_job_description);

        Button button = findViewById(R.id.submitJobButton);



        job = getJob();
        populateFields(job);


        button.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        submit();
    }

    //TODO: Get job from firebase
    private JobData getJob(){

         return new JobData("mm","nn","Title", "Payment", "Start Time", "Skills, Other Skill, Third Skill", "Description",0.00,0.00);
    }

    private void populateFields(JobData job){
        setText(title, job.getJobTitle());
        setText(payment, job.getPayment());
        setText(startTime, job.getStartTime());
        setText(desc, job.getJobDescription());

        String[] skills = job.getSkills().split(",");
        setText(skills1, skills[0]);
        if(skills.length > 1){
            setText(skills2, skills[1]);
        }
        if(skills.length > 2){
            setText(skills3, skills[2]);
        }

    }

    private void setText(EditText textBox, String text){
        textBox.setText(text);
    }

    private void submit(){
        job.setJobDescription(desc.getText().toString());
        job.setJobTitle(title.getText().toString());
        job.setPayment(payment.getText().toString());
        job.setStartTime(startTime.getText().toString());

        String[] skills = job.getSkills().split(",");

        if(skills.length > 2){
            job.setSkills(skills[0] + "," + skills[1] + "," + skills[2]);
        }
        else if(skills.length > 1){
            job.setSkills(skills[0] + "," + skills[1]);
        }
        else{
            job.setSkills(skills[0]);
        }

        //TODO: Edit job in Firebase
        DatabaseReference jobToPost = FirebaseDatabase.getInstance().getReference().child("Job Postings");
        jobToPost.push().setValue(job);
        Toast.makeText(JobEditActivity.this, "Successful", Toast.LENGTH_LONG).show();

        startActivity(new Intent(JobEditActivity.this, EmployerPageActivity.class));
    }

}
