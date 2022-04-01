package com.team6.quickcashteam6;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RatingEmployerPage extends AppCompatActivity {

    private Button submitBtn;
    private RatingBar ratingBarStars;
    private float ratingNumber;
    int weight;

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
                postRating(ratingNumber);
                Intent toEmployerPage = new Intent(RatingEmployerPage.this, EmployeePageActivity.class);
                startActivity(toEmployerPage);

            }
        });
    }

    private boolean postRating(float rating){
        FirebaseDatabase firebase = FirebaseDatabase.getInstance();
        DatabaseReference ref = firebase.getReference("Employer").child("-Mxv1BSNyBQZN33f-7wW").child("Ratings");
        DatabaseReference ref2 = firebase.getReference("Employer").child("-Mxv1BSNyBQZN33f-7wW").child("Weight");



        //change to single event
        ref2.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int tempWeight = dataSnapshot.getValue(Integer.class);
                setWeight(tempWeight);

                ref2.setValue(weight);

                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        float ratings = dataSnapshot.getValue(Float.class);
                        ratings = ((ratings*(weight-1))+rating)/weight;
                        ref.setValue(ratings);

                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });


            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        return true;
    }

    private void setWeight(int newWeight){
        weight = newWeight + 1;
    }
}
