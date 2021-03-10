package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class MyProfile extends AppCompatActivity {

    private TextView usernameid;
    private RatingBar ratingBar;
    int MyRAting =0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        usernameid = (TextView) findViewById(R.id.usernameid);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);

        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {

                int rat = (int) rating;
                String message = null;

                switch (rat) {

                    case 1:
                        ;
                        message = "Sorry to hear that :(";
                        break;
                    case 2:
                        ;
                        message = "You always accept suggestions";
                        break;
                    case 3:
                        ;
                        message = "Good enough!";
                        break;
                    case 4:
                        ;
                        message = "Great! Thank You! ";
                        break;
                    case 5:
                        ;
                        message = "Awesome! You are the best!";
                        break;

                    default:
                        message = "Please give some rating";
                        break;

                }

                Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
            }
        });


    }
}