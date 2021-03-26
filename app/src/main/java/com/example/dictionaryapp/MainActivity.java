package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView logo;
    private ImageView name_logo;
    Animation topanim, bottomanim;
    private static int sandeep = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        Intent intent = new Intent(this, login_signup.class);

        logo = (ImageView) findViewById(R.id.logo);
        name_logo = (ImageView) findViewById(R.id.logo_name);
        topanim = AnimationUtils.loadAnimation(this, R.anim.top_anim);
        bottomanim = AnimationUtils.loadAnimation(this, R.anim.bottom_anim);

        logo.setAnimation(topanim);
        name_logo.setAnimation(bottomanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(intent);
                finish();
            }
        }, sandeep);
    }
}