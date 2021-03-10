package com.example.dictionaryapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.example.dictionaryapp.adapter.MyAdapter;
import com.example.dictionaryapp.model.Listitem;

import java.util.ArrayList;
import java.util.List;

public class search extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;

    private List<Listitem> listitems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        listitems = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            Listitem listitem = new Listitem(
                    "Heading " + (i + 1),
                    "Lorem ipsum dummy text"
            );

            listitems.add(listitem);
        }

        adapter = new MyAdapter(listitems, this);

        recyclerView.setAdapter(adapter);

    }

}