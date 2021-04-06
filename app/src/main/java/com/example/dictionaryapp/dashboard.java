package com.example.dictionaryapp;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class dashboard extends AppCompatActivity {

    NavigationView nav;
    private  long backPressedTime;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;
    Toolbar toolbar;

   


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_dashboard);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = (NavigationView) findViewById(R.id.navmenu);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer);


        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.menufinal);
        getSupportActionBar().setTitle(" ");

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item1) {
                switch (item1.getItemId()) {
                    case R.id.logout:
                        SharedPreferences preferences=getSharedPreferences("checkbox",MODE_PRIVATE);
                        SharedPreferences.Editor editor=preferences.edit();
                        editor.putString("remember","false");
                        editor.apply();
                        Intent intent=new Intent(getApplicationContext(),login_signup.class);
                        startActivity(intent);
                        finish();
                        break;

                    case R.id.menu_rate:
                        Toast.makeText(dashboard.this, "rate", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;

                    case R.id.menu_share:
                        Toast.makeText(dashboard.this, "share", Toast.LENGTH_SHORT).show();
                        drawerLayout.closeDrawer(GravityCompat.START);
                        break;
                }

                return true;
            }
        });

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
        Intent intent = new Intent(getApplicationContext(), confirmPlay.class);
        startActivity(intent);
    }

    public void goToSearch(View view) {
        Intent intent = new Intent(getApplicationContext(), search.class);
        startActivity(intent);
    }


}

