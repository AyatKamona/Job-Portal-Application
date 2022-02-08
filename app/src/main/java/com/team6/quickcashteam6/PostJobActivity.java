package com.team6.quickcashteam6;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class PostJobActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postjob);

        Button submitJobButton = findViewById(R.id.submitJobButton);
        submitJobButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view){
        openEmployerPage();
    }

    public void openEmployerPage() {
        Intent submitJob = new Intent(PostJobActivity.this, EmployerPageActivity.class);
        startActivity(submitJob);
    }

}
