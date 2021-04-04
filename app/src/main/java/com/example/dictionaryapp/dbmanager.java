package com.example.dictionaryapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dbmanager extends SQLiteOpenHelper {

    private static final String dbname="dictionary";
    public dbmanager(@Nullable Context context) {
        super(context, dbname,null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String qry="create table tbl_contact (id integer primary key autoincrement, datex text)";  //datey
        sqLiteDatabase.execSQL(qry);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        String qry="DROP TABLE IF EXISTS tbl_contact";
        sqLiteDatabase.execSQL(qry);
        onCreate(sqLiteDatabase);
    }
    public String addrecord(String datex){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("datex",datex);
        float res=db.insert("tbl_contact",null,cv);
        if (res==-1)
            return "failed";
        else
            return "successfully inserted";
    }
    public Cursor readalldata(){
        SQLiteDatabase db=this.getWritableDatabase();
        String qry="select*from tbl_contact order by id desc";
        Cursor cursor=db.rawQuery(qry,null);
        return cursor;
    }
}