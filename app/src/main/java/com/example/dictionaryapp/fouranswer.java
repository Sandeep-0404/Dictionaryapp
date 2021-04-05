package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class fouranswer extends AppCompatActivity {

    TextView scoreDisplay4, total4;
    int addition4;
    String gotScore4;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back at this stage", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fouranswer);
        scoreDisplay4 = (TextView) findViewById(R.id.scoreDisplay4);
        total4 = (TextView) findViewById(R.id.total4);


        gotScore4 = getIntent().getStringExtra("score");
        String totalScore = getIntent().getStringExtra("total");
        scoreDisplay4.setText(gotScore4);
        total4.setText(totalScore);

        int marks = Integer.parseInt(gotScore4);

        addition4 = addition4 + marks;
        String add = String.valueOf(marks);


    }

    public void nextQ4(View view) {
        Intent intent = new Intent(this, qfive.class);
        intent.putExtra("score", gotScore4);
        startActivity(intent);
        finish();
    }
}