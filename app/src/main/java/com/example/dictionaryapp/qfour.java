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

public class qfour extends AppCompatActivity {

    private int presCounter4 = 0;
    int seconds4, raw4;
    private String total4;
    private String score4;
    private TextView countDownText4;
    private CountDownTimer countDownTimer4;
    private int maxPresCounter4 = 4;
    private String[] keys4 = {"W", "C", "O", "R", "G"};
    private String textAnswer4 = "GROW";
   TextView textScreen4, textQuestion4, textTitle4;
    private boolean timerRunning4;
    private long timeLeftInMilliseconds4 = 31000;

    @Override
    public void onBackPressed() {
        Toast.makeText(getApplicationContext(), "Can't go back at this stage", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qfour);
        countDownText4 = (TextView) findViewById(R.id.countDownText4);
        total4 = "120";

        String raws = getIntent().getStringExtra("score");
        raw4 = Integer.parseInt(raws);

        keys4 = shuffleArray(keys4);
        for (String key : keys4) {
            addView((LinearLayout) findViewById(R.id.layoutParent4), key, ((EditText) findViewById(R.id.edittext4)));
        }

        maxPresCounter4 = 4;

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

        textQuestion4 = (TextView) findViewById(R.id.textquestion);
        textScreen4 = (TextView) findViewById(R.id.textScreen);
        textTitle4 = (TextView) findViewById(R.id.title);

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (presCounter4 < maxPresCounter4) {
                    if (presCounter4 == 0)
                        edittext.setText("");
                    edittext.setText(edittext.getText().toString() + text);
                    textView.animate().alpha(0).setDuration(500);
                    presCounter4++;

                    if (presCounter4 == maxPresCounter4)
                        doValidate();

                }
            }
        });

        viewParent.addView(textView);


    }

    private void doValidate() {
        presCounter4 = 0;
        EditText editText4 = findViewById(R.id.edittext4);
        LinearLayout linearLayout4 = findViewById(R.id.layoutParent4);

        if (editText4.getText().toString().equals(textAnswer4)) {
          //  Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            seconds4 = seconds4 + raw4;
            String scores = String.valueOf(seconds4);
            score4 = scores + "/" + "30";
            Intent intent = new Intent(this, fouranswer.class);
            intent.putExtra("score", scores);
            intent.putExtra("total", total4);
            startActivity(intent);
            finish();
            stopTimer();
            editText4.setText("");
        } else {
            Toast.makeText(this, "Wrong", Toast.LENGTH_SHORT).show();
            editText4.setText("");
        }

        keys4 = shuffleArray(keys4);
        linearLayout4.removeAllViews();
        for (String key4 : keys4) {
            addView(linearLayout4, key4, editText4);
        }
    }

    public void startStop() {
        if (timerRunning4) {
            stopTimer();
        } else {
            startTimer();
        }
    }

    public void startTimer() {
        countDownTimer4 = new CountDownTimer(timeLeftInMilliseconds4, 1000) {
            @Override
            public void onTick(long l) {
                timeLeftInMilliseconds4 = l;
                updateTimer();
            }

            @Override
            public void onFinish() {
                seconds4 = seconds4 + raw4;
                String scores = String.valueOf(seconds4);
                score4 = scores + "/" + "30";
                Intent intent = new Intent(getApplicationContext(), fouranswer.class);
                intent.putExtra("score", scores);
                intent.putExtra("total", total4);
                startActivity(intent);
                finish();

            }
        }.start();


        timerRunning4 = true;
    }

    public void stopTimer() {
        countDownTimer4.cancel();
        timerRunning4 = false;
    }

    public void updateTimer() {
        int minutes = (int) timeLeftInMilliseconds4 / 60000;
        seconds4 = (int) timeLeftInMilliseconds4 % 60000 / 1000;

        String timeLeftText;
        timeLeftText = "" + minutes;
        timeLeftText += ":";
        if (seconds4 < 10) timeLeftText += "0";
        timeLeftText += seconds4;

        countDownText4.setText(timeLeftText);
    }


}
