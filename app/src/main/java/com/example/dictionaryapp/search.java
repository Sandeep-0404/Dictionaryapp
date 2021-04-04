package com.example.dictionaryapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.dictionaryapp.adapter.MyAdapter;
import com.example.dictionaryapp.model.model;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class search extends AppCompatActivity {
    FloatingActionButton floating;

    RecyclerView recyclerView1;
    ArrayList<model> dataholder;
    RecyclerView.Adapter adapter1;
    private TextView searchOutput;
    Button button;
    String url;
    private TextView showDef;
    EditText datex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);



        datex = (EditText)findViewById(R.id.date1);
        button =(Button)findViewById(R.id.bt_add);
        showDef=(TextView)findViewById(R.id.showDef);

        searchOutput=(TextView)findViewById(R.id.date1);
        floating =(FloatingActionButton)findViewById(R.id.floating);
        recyclerView1=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerView1.setLayoutManager(new LinearLayoutManager(this));
        dataholder = new ArrayList<>();
        Cursor cursor=new dbmanager(this).readalldata();
        while (cursor.moveToNext()){
            model obj=new model(cursor.getString(1));
            dataholder.add(obj);
        }
        adapter1= new MyAdapter(dataholder);
        recyclerView1.setAdapter(adapter1);


    }

    public void voice_search(View view) {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Hi Speak Something");
        try {
            startActivityForResult(intent, 1);
        } catch (ActivityNotFoundException e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK && null != data) {
                    ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

                    searchOutput.setText(result.get(0));

                }

                break;
        }
    }

    public void floating_button(View view) {
        MyDictionaryRequest myDictionaryRequest=new MyDictionaryRequest(this,showDef);
        url=dictionaryEntries();
        myDictionaryRequest.execute(url);

        processinsert(datex.getText().toString().trim());
    }



    private void processinsert(String d) {
        if (d.isEmpty()) {
            Toast.makeText(getApplicationContext(), "Please type something", Toast.LENGTH_SHORT).show();
        } else {
            String res = new dbmanager(this).addrecord(d);
            datex.setText("");
        }
    }


    private String dictionaryEntries() {
        final String language = "en-gb";
        final String word = datex.getText().toString().trim();
        final String fields = "definitions";
        final String strictMatch = "false";
        final String word_id = word.toLowerCase();
        return "https://od-api.oxforddictionaries.com:443/api/v2/entries/" + language + "/" + word_id + "?" + "fields=" + fields + "&strictMatch=" + strictMatch;
    }
}