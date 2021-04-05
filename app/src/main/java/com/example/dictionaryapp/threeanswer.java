package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class threeanswer extends AppCompatActivity {

    TextView scoreDisplay3, total3;
    int addition3;
    String gotScore3;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back at this stage", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threeanswer);
        scoreDisplay3 = (TextView) findViewById(R.id.scoreDisplay3);
        total3 = (TextView) findViewById(R.id.total3);


        gotScore3 = getIntent().getStringExtra("score");
        String totalScore = getIntent().getStringExtra("total");
        scoreDisplay3.setText(gotScore3);
        total3.setText(totalScore);

        int marks = Integer.parseInt(gotScore3);

        addition3 = addition3 + marks;
        String add = String.valueOf(marks);


    }

    public void nextQ3(View view) {
        Intent intent = new Intent(this, qfour.class);
        intent.putExtra("score", gotScore3);
        startActivity(intent);
        finish();
    }
}