package com.example.dictionaryapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;


import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

import java.io.File;
import java.util.UUID;

public class MyProfile extends AppCompatActivity {

    private TextView usernameid;
    private RatingBar ratingBar;
    int MyRAting = 0;
    private ImageView profilePic;
    private Uri imageUri;
    FirebaseAuth mAuth;
TextView displayemailuser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        displayemailuser=(TextView) findViewById(R.id.displayemail);
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        profilePic = (ImageView) findViewById(R.id.profilePic);
        mAuth=FirebaseAuth.getInstance();

        loadUserINformation();


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

    private void loadUserINformation() {
        FirebaseUser user=mAuth.getCurrentUser();
        String displayEmail=user.getEmail();
        displayemailuser.setText(displayEmail);

    }
}