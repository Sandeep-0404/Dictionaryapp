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

public class qtwo extends AppCompatActivity {

    private int presCounter2 = 0;
    int seconds2, raw2;
    private String total2;
    private String score2;
    private TextView countDownText2;
    private CountDownTimer countDownTimer2;
    private int maxPresCounter2 = 4;
    private String[] keys2 = {"S", "H", "I", "F", "K"};
    private String textAnswer2 = "FISH";
     TextView textScreen2, textQuestion2, textTitle2;
    private boolean timerRunning2;
    private long timeLeftInMilliseconds2 = 31000;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back at this stage", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qtwo);
        countDownText2 = (TextView) findViewById(R.id.countDownText2);
        total2 = "60";

        String raws2 = getIntent().getStringExtra("score");
        raw2 = Integer.parseInt(raws2);

        keys2 = shuffleArray(keys2);
        for (String key : keys2) {
            addView((LinearLayout) findViewById(R.id.layoutParent2), key, ((EditText) findViewById(R.id.edittext2)));
        }

        maxPresCounter2 = 4;

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

        textQuestion2 = (TextView) findViewById(R.id.textquestion2);
        textScreen2 = (TextView) findViewById(R.id.textScreen2);
        textTitle2 = (TextView) findViewById(R.id.title2);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presCounter2 < maxPresCounter2) {
                    if (presCounter2 == 0)
                        edittext.setText("");
                    edittext.setText(edittext.getText().toString() + text);
                    textView.animate().alpha(0).setDuration(500);
                    presCounter2++;

                    if (presCounter2 == maxPresCounter2)
                        doValidate();

                }
            }
        });

        viewParent.addView(textView);


    }

    private void doValidate() {
        presCounter2 = 0;
        EditText editText2 = findViewById(R.id.edittext2);
        LinearLayout linearLayout2 = findViewById(R.id.layoutParent2);

        if (editText2.getText().toString().equals(textAnswer2)) {
         //   Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            seconds2 = seconds2 + raw2;
            String scores = String.valueOf(seconds2);
            score2 = scores + "/" + "30";
            Intent intent = new Intent(this, twoanswer.class);
            intent.putExtra("score", scores);
            intent.putExtra("total", total2);
            startActivity(intent);
            finish();
            stopTimer();
            editText2.setText("");
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            editText2.setText("");
        }

        keys2 = shuffleArray(keys2);
        linearLayout2.removeAllViews();
        for (String key2 : keys2) {
            addView(linearLayout2, key2, editText2);
        }
    }

    public void startStop() {
        if (timerRunning2) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer2 = new CountDownTimer(timeLeftInMilliseconds2, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds2 = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                seconds2 = seconds2 + raw2;
                String scores = String.valueOf(seconds2);
                score2 = scores + "/" + "30";
                Intent intent = new Intent(getApplicationContext(), twoanswer.class);
                intent.putExtra("score", scores);
                intent.putExtra("total", total2);
                startActivity(intent);
                finish();

            }
        }.start();


        timerRunning2 = true;
    }

    public void stopTimer() {
        countDownTimer2.cancel();
        timerRunning2 = false;
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds2 / 60000;
        seconds2 = (int) timeLeftInMilliseconds2 % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds2 < 10) timeLeftText += "0";
        timeLeftText += seconds2;

        countDownText2.setText(timeLeftText);
    }


}
