package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class welldone extends AppCompatActivity {
    TextView scoreDisplay,total;
    int addition;
    String gotScore;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back at this stage", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welldone);

        scoreDisplay = (TextView) findViewById(R.id.scoreDisplay);
        total = (TextView) findViewById(R.id.total);


        gotScore = getIntent().getStringExtra("score");
       String  totalScore = getIntent().getStringExtra("total");
        scoreDisplay.setText(gotScore);
        total.setText(totalScore);

        int marks = Integer.parseInt(gotScore);

        addition = addition + marks;
        String add = String.valueOf(marks);


    }

    public void nextQ(View view) {
        Intent intent = new Intent(this, qtwo.class);
        intent.putExtra("score", gotScore);
        startActivity(intent);
        finish();
    }
}