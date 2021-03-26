package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Paint;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class recycler_click extends AppCompatActivity {

    private TextView wikipediaLink;
    private TextView heading;
    private TextView description;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_click);

        wikipediaLink = (TextView) findViewById(R.id.wikipedia_link);
        heading = (TextView) findViewById(R.id.heading);
        description = (TextView) findViewById(R.id.description);
        wikipediaLink.setPaintFlags(wikipediaLink.getPaintFlags() | Paint.UNDERLINE_TEXT_FLAG);

        String headingText = getIntent().getStringExtra("heading");

        heading.setText(headingText);
    }
}