package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class twoanswer extends AppCompatActivity {

    TextView scoreDisplay2, total2;
    int addition2;
    String gotScore2;


    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back at this stage", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_twoanswer);
        scoreDisplay2 = (TextView) findViewById(R.id.scoreDisplay2);
        total2 = (TextView) findViewById(R.id.total2);


        gotScore2 = getIntent().getStringExtra("score");
        String totalScore = getIntent().getStringExtra("total");
        scoreDisplay2.setText(gotScore2);
        total2.setText(totalScore);

        int marks2 = Integer.parseInt(gotScore2);

        addition2 = addition2 + marks2;
        String add2 = String.valueOf(marks2);


    }

    public void nextQ2(View view) {
        Intent intent = new Intent(this, qthree.class);
        intent.putExtra("score", gotScore2);
        startActivity(intent);
        finish();
    }
}