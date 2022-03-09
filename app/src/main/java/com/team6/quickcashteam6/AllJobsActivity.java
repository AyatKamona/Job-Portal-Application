package com.team6.quickcashteam6;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import java.util.Objects;

public class AllJobsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_job_postings);

        Toolbar title = findViewById(R.id.job_postings_toolbar);
        setSupportActionBar(title);
        Objects.requireNonNull(getSupportActionBar()).setTitle("All Available Jobs");
    }
}
