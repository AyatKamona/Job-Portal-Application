package com.team6.quickcashteam6;

import android.os.Bundle;
import android.widget.Button;
import android.widget.RatingBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RatingEmployeePage extends AppCompatActivity {

    private Button submitBtn;
    private RatingBar ratingBarStars;
    private float ratingNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_employee);

        submitBtn = findViewById(R.id.submitEmployeeRating);
        ratingBarStars = findViewById(R.id.EmployeeRatingBar);

        ratingBarStars.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {

                int rating = (int) v;
                ratingNumber = ratingBar.getRating();

                String message = "";

                if (rating >0 && rating <= 2)  {
                    message = "Sorry for your experience";
                }

                if (rating >= 3 && rating < 4)  {
                    message = "Good enough!";
                }

                if (rating >= 4) {
                    message = "Awesome! Thank you!";
                }

                Toast.makeText(RatingEmployeePage.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEmployeePage = new Intent(RatingEmployeePage.this, EmployerPageActivity.class);
                startActivity(toEmployeePage);
            }
        });
    }
}
