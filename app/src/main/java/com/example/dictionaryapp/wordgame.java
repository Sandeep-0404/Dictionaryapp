package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class wordgame extends AppCompatActivity {

    private int presCounter = 0;
    private int maxPresCounter = 4;
    private String[] keys = {"R", "I", "B", "D", "X"};
    private String textAnswer = "BIRD";
    TextView textScreen, textQuestion, textTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wordgame);

        keys = shuffleArray(keys);
        for (String key : keys) {
            addView((LinearLayout) findViewById(R.id.layoutParent), key, ((EditText) findViewById(R.id.edittext)));
        }

        maxPresCounter = 4;


    }

    private String[] shuffleArray(String[] ar) {
        Random rnd = new Random();
        for (int i = ar.length - 1; i > 0; i--) {
            int index = rnd.nextInt(i + 1);
            String a = ar[index];
            ar[index] = ar[i];
            ar[i] = a;
        }
        return ar;
    }

    private void addView(LinearLayout viewParent, final String text, final EditText edittext) {
        LinearLayout.LayoutParams linearLayoutParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        linearLayoutParams.rightMargin = 20;
        linearLayoutParams.topMargin = 50;

        final TextView textView = new TextView(this);
        textView.setLayoutParams(linearLayoutParams);
        textView.setBackground(this.getResources().getDrawable(R.drawable.bgpink));
        textView.setTextColor(this.getResources().getColor(R.color.colorPurple));
        textView.setGravity(Gravity.CENTER);
        textView.setText(text);
        textView.setClickable(true);
        textView.setFocusable(true);
        textView.setTextSize(32);

        textQuestion = (TextView) findViewById(R.id.textquestion);
        textScreen = (TextView) findViewById(R.id.textScreen);
        textTitle = (TextView) findViewById(R.id.title);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presCounter < maxPresCounter) {
                    if (presCounter == 0)
                        edittext.setText("");
                    edittext.setText(edittext.getText().toString() + text);
                    textView.animate().alpha(0).setDuration(500);
                    presCounter++;

                    if (presCounter == maxPresCounter)
                        doValidate();

                }
            }
        });

        viewParent.addView(textView);


    }

    private void doValidate() {
        presCounter = 0;
        EditText editText = findViewById(R.id.edittext);
        LinearLayout linearLayout = findViewById(R.id.layoutParent);

        if (editText.getText().toString().equals(textAnswer)) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(this, welldone.class);
            startActivity(intent);
            editText.setText("");
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            editText.setText("");
        }

        keys = shuffleArray(keys);
        linearLayout.removeAllViews();
        for (String key : keys) {
            addView(linearLayout, key, editText);
        }
    }
}