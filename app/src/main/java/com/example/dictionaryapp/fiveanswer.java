package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class fiveanswer extends AppCompatActivity {

    TextView scoreDisplay5, total5;
    int addition5;
    String gotScore5;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back at this stage", Toast.LENGTH_SHORT).show();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fiveanswer);
        scoreDisplay5 = (TextView) findViewById(R.id.scoreDisplay5);
        total5 = (TextView) findViewById(R.id.total5);


        gotScore5 = getIntent().getStringExtra("score");
        String totalScore = getIntent().getStringExtra("total");
        scoreDisplay5.setText(gotScore5);
        total5.setText(totalScore);

        int marks = Integer.parseInt(gotScore5);

        addition5 = addition5 + marks;
        String add5 = String.valueOf(marks);


    }

    public void nextQ5(View view) {
        Intent intent = new Intent(this, dashboard.class);
        intent.putExtra("score", gotScore5);
        startActivity(intent);
        finish();
    }
}