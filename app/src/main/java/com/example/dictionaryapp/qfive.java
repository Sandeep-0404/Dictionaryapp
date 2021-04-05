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

public class qfive extends AppCompatActivity {

    private int presCounter5 = 0;
    int seconds5, raw5;
    private String total5;
    private String score5;
    private TextView countDownText5;
    private CountDownTimer countDownTimer5;
    private int maxPresCounter5 = 4;
    private String[] keys5 = {"Y", "A", "L", "P", "E"};
    private String textAnswer5 = "PLAY";
    TextView textScreen5, textQuestion5, textTitle5;
    private boolean timerRunning5;
    private long timeLeftInMilliseconds5 = 31000;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back at this stage", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qfive);
        countDownText5 = (TextView) findViewById(R.id.countDownText5);
        total5 = "150";

        String raws = getIntent().getStringExtra("score");
        raw5 = Integer.parseInt(raws);

        keys5 = shuffleArray(keys5);
        for (String key : keys5) {
            addView((LinearLayout) findViewById(R.id.layoutParent5), key, ((EditText) findViewById(R.id.edittext5)));
        }

        maxPresCounter5 = 4;

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

        textQuestion5 = (TextView) findViewById(R.id.textquestion5);
        textScreen5 = (TextView) findViewById(R.id.textScreen5);
        textTitle5 = (TextView) findViewById(R.id.title5);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presCounter5 < maxPresCounter5) {
                    if (presCounter5 == 0)
                        edittext.setText("");
                    edittext.setText(edittext.getText().toString() + text);
                    textView.animate().alpha(0).setDuration(500);
                    presCounter5++;

                    if (presCounter5 == maxPresCounter5)
                        doValidate();

                }
            }
        });

        viewParent.addView(textView);


    }

    private void doValidate() {
        presCounter5 = 0;
        EditText editText5 = findViewById(R.id.edittext5);
        LinearLayout linearLayout5 = findViewById(R.id.layoutParent5);

        if (editText5.getText().toString().equals(textAnswer5)) {
        //    Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            seconds5 = seconds5 + raw5;
            String scores = String.valueOf(seconds5);
            score5 = scores + "/" + "30";
            Intent intent = new Intent(this, fiveanswer.class);
            intent.putExtra("score", scores);
            intent.putExtra("total", total5);
            startActivity(intent);
            finish();
            stopTimer();
            editText5.setText("");
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            editText5.setText("");
        }

        keys5 = shuffleArray(keys5);
        linearLayout5.removeAllViews();
        for (String key5 : keys5) {
            addView(linearLayout5, key5, editText5);
        }
    }

    public void startStop() {
        if (timerRunning5) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer5 = new CountDownTimer(timeLeftInMilliseconds5, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds5 = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                seconds5 = seconds5 + raw5;
                String scores = String.valueOf(seconds5);
                score5 = scores + "/" + "30";
                Intent intent = new Intent(getApplicationContext(), fiveanswer.class);
                intent.putExtra("score", scores);
                intent.putExtra("total", total5);
                startActivity(intent);
                finish();

            }
        }.start();


        timerRunning5 = true;
    }

    public void stopTimer() {
        countDownTimer5.cancel();
        timerRunning5 = false;
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds5 / 60000;
        seconds5 = (int) timeLeftInMilliseconds5 % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds5 < 10) timeLeftText += "0";
        timeLeftText += seconds5;

        countDownText5.setText(timeLeftText);
    }


}
