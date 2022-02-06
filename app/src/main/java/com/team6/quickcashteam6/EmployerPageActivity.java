package com.team6.quickcashteam6;

import android.app.Activity;
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

        Button registerButton = findViewById(R.id.postJobButton);
        registerButton.setOnClickListener(this);
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
