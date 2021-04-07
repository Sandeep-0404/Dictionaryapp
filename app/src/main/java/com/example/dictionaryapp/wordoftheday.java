package com.example.dictionaryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class wordoftheday extends AppCompatActivity {

    TextView outputText;
    TextView meaningwod;
    ImageView mButtonSpeak;
    private TextToSpeech mTTS;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_wordoftheday);

        mButtonSpeak = findViewById(R.id.micforwod);
        outputText=(TextView)findViewById(R.id.wod);
        meaningwod=(TextView)findViewById(R.id.wodmeaning);

        mTTS = new TextToSpeech(this, new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int status) {
                if(status==TextToSpeech.SUCCESS){
                    int result = mTTS.setLanguage(Locale.ENGLISH);

                    if(result==TextToSpeech.LANG_MISSING_DATA
                            || result==TextToSpeech.LANG_NOT_SUPPORTED){
                        Log.e("TTS","Language not supported");
                    }else{
                        mButtonSpeak.setEnabled(true);
                    }
                }else{
                    Log.e("TTS","Initialization Failed");
                }
            }
        });


        mButtonSpeak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                speak();
            }
        });


    }

    private void speak(){
        String text = outputText.getText().toString().trim() +"."+meaningwod.getText().toString().trim();

        mTTS.speak(text,TextToSpeech.QUEUE_FLUSH,null);
    }

}