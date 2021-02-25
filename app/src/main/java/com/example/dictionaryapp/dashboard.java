package com.example.dictionaryapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class dashboard extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

    }


    public void pronunciation(View view) {
        Intent intent = new Intent(getApplicationContext(), pronunciation.class);
        startActivity(intent);
    }

    public void wordoxftheday(View view) {
        Intent intent = new Intent(getApplicationContext(), wordoftheday.class);
        startActivity(intent);
    }

    public void wordgame(View view) {
        Intent intent = new Intent(getApplicationContext(), wordgame.class);
        startActivity(intent);
    }
}