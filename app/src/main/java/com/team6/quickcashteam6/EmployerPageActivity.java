package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class EmployerPageActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_employer_page);

        Button postJobButton = findViewById(R.id.postJobButton);
        postJobButton.setOnClickListener(this);

        Button moveToRecommend = findViewById(R.id.moveToRecommend);

        Button testButton = findViewById(R.id.testButton);
        testButton.setOnClickListener(this::onTestClick);

        moveToRecommend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(EmployerPageActivity.this, EmployerRecommendationActivity.class));
            }
        });

    }

    private void onTestClick(View view) {
        Intent i = new Intent(EmployerPageActivity.this, AllJobsActivity.class);
        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        openPostJobPage();
    }

    public void openPostJobPage() {
        Intent postJob = new Intent(EmployerPageActivity.this, PostJobActivity.class);
        startActivity(postJob);
    }
}