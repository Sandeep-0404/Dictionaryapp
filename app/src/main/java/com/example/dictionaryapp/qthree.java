package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class qthree extends AppCompatActivity {

    private int presCounter3 = 0;
    int seconds3, raw3;
    private String total3;
    private String score;
    private TextView countDownText3;
    private CountDownTimer countDownTimer3;
    private int maxPresCounter3 = 4;
    private String[] keys3 = {"G", "A", "N", "I", "R"};
    private String textAnswer3= "RAIN";
   TextView textScreen3, textQuestion3, textTitle3;
    private boolean timerRunning3;
    private long timeLeftInMilliseconds3 = 31000;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back at this stage", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qthree);
        countDownText3 = (TextView) findViewById(R.id.countDownText3);
        total3 = "90";

        String raws = getIntent().getStringExtra("score");
        raw3 = Integer.parseInt(raws);

        keys3 = shuffleArray(keys3);
        for (String key : keys3) {
            addView((LinearLayout) findViewById(R.id.layoutParent3), key, ((EditText) findViewById(R.id.edittext3)));
        }

        maxPresCounter3 = 4;

        startStop();


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

        textQuestion3 = (TextView) findViewById(R.id.textquestion3);
        textScreen3 = (TextView) findViewById(R.id.textScreen3);
        textTitle3 = (TextView) findViewById(R.id.title3);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presCounter3 < maxPresCounter3) {
                    if (presCounter3 == 0)
                        edittext.setText("");
                    edittext.setText(edittext.getText().toString() + text);
                    textView.animate().alpha(0).setDuration(500);
                    presCounter3++;

                    if (presCounter3 == maxPresCounter3)
                        doValidate();

                }
            }
        });

        viewParent.addView(textView);


    }

    private void doValidate() {
        presCounter3 = 0;
        EditText editText3 = findViewById(R.id.edittext3);
        LinearLayout linearLayout3 = findViewById(R.id.layoutParent3);

        if (editText3.getText().toString().equals(textAnswer3)) {
          //  Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            seconds3 = seconds3 + raw3;
            String scores = String.valueOf(seconds3);
            score = scores + "/" + "30";
            Intent intent = new Intent(this, threeanswer.class);
            intent.putExtra("score", scores);
            intent.putExtra("total", total3);
            startActivity(intent);
            finish();
            stopTimer();
            editText3.setText("");
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            editText3.setText("");
        }

        keys3 = shuffleArray(keys3);
        linearLayout3.removeAllViews();
        for (String key3 : keys3) {
            addView(linearLayout3, key3, editText3);
        }
    }

    public void startStop() {
        if (timerRunning3) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer3 = new CountDownTimer(timeLeftInMilliseconds3, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds3 = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                seconds3 = seconds3 + raw3;
                String scores = String.valueOf(seconds3);
                score = scores + "/" + "30";
                Intent intent = new Intent(getApplicationContext(), threeanswer.class);
                intent.putExtra("score", scores);
                intent.putExtra("total", total3);
                startActivity(intent);
                finish();

            }
        }.start();


        timerRunning3 = true;
    }

    public void stopTimer() {
        countDownTimer3.cancel();
        timerRunning3 = false;
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds3 / 60000;
        seconds3 = (int) timeLeftInMilliseconds3 % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds3 < 10) timeLeftText += "0";
        timeLeftText += seconds3;

        countDownText3.setText(timeLeftText);
    }


}
