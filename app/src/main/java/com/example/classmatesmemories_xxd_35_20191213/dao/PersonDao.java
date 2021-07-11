package com.example.classmatesmemories_xxd_35_20191213.dao;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.example.classmatesmemories_xxd_35_20191213.bean.PersonInfo;

public class PersonDao {
    private MyHelper helper;
    public PersonDao(Context context){
        helper = new MyHelper(context);
    }

    public int delete(long id) {
        SQLiteDatabase db = helper.getWritableDatabase();
        // 按条件删除指定表中的数据, 返回受影响的行数
        int count = db.delete("T_pople_xxd", "id_xxd=?", new String[] { id + "" });
        db.close();
        return count;
    }
    public int update(PersonInfo p){
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name_xxd",p.getName_xxd());
        values.put("gender_xxd",p.getGender_xxd());
        values.put("phone_xxd",p.getPhone_xxd());
        values.put("address_xxd",p.getAddress_xxd());
        values.put("qq_xxd",p.getQQ_xxd());
        values.put("email_xxd",p.getEmail_xxd());
        int count = db.update("T_pople_xxd",values,"id_xxd=?",new String[]{p.getId_xxd()+""});
        db.close();
        return count;
    }


}
