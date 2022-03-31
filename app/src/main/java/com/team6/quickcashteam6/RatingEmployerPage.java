package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RatingEmployerPage extends AppCompatActivity {

    private Button submitBtn;
    private RatingBar ratingBarStars;
    private float ratingNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating_employer);

        submitBtn = findViewById(R.id.submitEmployerRating);
        ratingBarStars = findViewById(R.id.EmployerRatingBar);

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

                Toast.makeText(RatingEmployerPage.this, message, Toast.LENGTH_SHORT).show();
            }
        });

        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent toEmployerPage = new Intent(RatingEmployerPage.this, EmployerPageActivity.class);
                startActivity(toEmployerPage);
            }
        });
    }
}
