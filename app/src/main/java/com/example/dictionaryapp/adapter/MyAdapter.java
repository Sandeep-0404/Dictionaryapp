package com.example.dictionaryapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dictionaryapp.R;
import com.example.dictionaryapp.model.model;
import com.example.dictionaryapp.recycler_click;

import java.util.ArrayList;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.myviewholder> {

    ArrayList<model> dataholder;


    public MyAdapter(ArrayList<model> dataholder) {
        this.dataholder = dataholder;
    }


    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);
        return new myviewholder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.ddatex.setText(dataholder.get(position).getDatex());

    }

    @Override
    public int getItemCount() {
        return dataholder.size() ;
    }

    class myviewholder extends RecyclerView.ViewHolder {
        public TextView ddatex;

        public myviewholder(@NonNull View itemView) {
            super(itemView);
            ddatex=(TextView)itemView.findViewById(R.id.textViewHead);


        }
    }

}
