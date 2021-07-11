package com.example.classmatesmemories_xxd_35_20191213.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;



public class MyHelper extends SQLiteOpenHelper {
    public MyHelper( Context context) {
        super(context,"DB_memories_xxd.db",null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE T_pople_xxd(id_xxd INTEGER PRIMARY KEY AUTOINCREMENT,name_xxd VARCHAR(20),gender_xxd VARCHAR(6),phone_xxd VARCHAR(20),address_xxd VARCHAR(200),qq_xxd VARCHAR(20),email_xxd VARCHAR(100))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
