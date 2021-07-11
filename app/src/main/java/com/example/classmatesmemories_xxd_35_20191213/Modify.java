package com.example.classmatesmemories_xxd_35_20191213;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.classmatesmemories_xxd_35_20191213.bean.PersonInfo;
import com.example.classmatesmemories_xxd_35_20191213.dao.MyHelper;
import com.example.classmatesmemories_xxd_35_20191213.dao.PersonDao;

import java.util.ArrayList;
import java.util.List;


public class Modify extends AppCompatActivity {
    private int[] icons = {R.drawable.tx1};

    List<PersonInfo> list;

    private ListView listView;

    private MyHelper myHelper;

    private PersonDao dao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify);
        ImageView back = findViewById(R.id.img_back);
        dao = new PersonDao(Modify.this);



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(Modify.this,Main2Activity.class);
                startActivity(it);
            }
        });
        int j =test(Modify.this);

        if(j == 1){
            listView = (ListView) findViewById(R.id.lv_modify);

            MyBaseAdapter adapter = new MyBaseAdapter();

            listView.setAdapter(adapter);
        }
    }

    private int test(Context context) {
        list = new ArrayList<PersonInfo>();
        int i=0;
        int j=1;
        myHelper = new MyHelper(context);
        SQLiteDatabase db;
        db = myHelper.getReadableDatabase();
        Cursor cursor = db.query("T_pople_xxd",null,null,null,null,null,null);
        if (cursor.getCount()==0){
            Toast.makeText(this,"No data",Toast.LENGTH_SHORT).show();
            j = 0;
        }else{
            cursor.moveToFirst();
            while (cursor.moveToNext()){
                long id =cursor.getLong(cursor.getColumnIndex("id_xxd"));

                String name = cursor.getString(1);
                String gender = cursor.getString(2);
                String phone =cursor.getString(3);
                String address =cursor.getString(4);
                String qq =cursor.getString(5);
                String email=cursor.getString(6);
                list.add(new PersonInfo(id,name,gender,phone,address,qq,email));
            }
            cursor.close();
            db.close();
        }
        return j;
    }

    public class MyBaseAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            View view =convertView != null ? convertView : View.inflate(getApplicationContext(),R.layout.item_modify,null);
            final PersonInfo p = list.get(position);
            TextView id = view.findViewById(R.id.tv_numberid);
            id.setText(p.getId_xxd()+"");

            TextView name =view.findViewById(R.id.vet_name);
            name.setText(p.getName_xxd());

            final EditText gender = view.findViewById(R.id.vet_gender);
            gender.setText(p.getGender_xxd());

            final EditText phone = view.findViewById(R.id.vet_phone);
            phone.setText(p.getAddress_xxd());

            final EditText qq = view.findViewById(R.id.vet_qq);
            qq.setText(p.getQQ_xxd());

            final EditText email = view.findViewById(R.id.vet_email);
            email.setText(p.getEmail_xxd());

            final EditText address = view.findViewById(R.id.vet_address);
            address.setText(p.getPhone_xxd());

            Button delete = view.findViewById(R.id.btn_delete);
            delete.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //删除数据之前首先弹出一个对话框
                    DialogInterface.OnClickListener listener =
                            new DialogInterface.
                                    OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    list.remove(p);          // 从集合中删除
                                    dao.delete(p.getId_xxd()); // 从数据库中删除
                                    notifyDataSetChanged();// 刷新界面
                                }
                            };
                    AlertDialog.Builder builder = new AlertDialog.Builder(Modify.this); // 创建对话框
                    builder.setTitle("确定要删除吗?");                    // 设置标题
                    // 设置确定按钮的文本以及监听器
                    builder.setPositiveButton("确定", listener);
                    builder.setNegativeButton("取消", null);         // 设置取消按钮
                    builder.show(); // 显示对话框
                }
            });

            Button update = view.findViewById(R.id.btn_update);
            update.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    //删除数据之前首先弹出一个对话框
                    DialogInterface.OnClickListener listener =
                            new DialogInterface.
                                    OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    list.get(position).setGender_xxd(gender.getText().toString().trim());
                                    list.get(position).setAddress_xxd(address.getText().toString().trim());
                                    list.get(position).setEmail_xxd(email.getText().toString().trim());
                                    list.get(position).setPhone_xxd(phone.getText().toString().trim());
                                    list.get(position).setQQ_xxd(qq.getText().toString().trim());
                                    dao.update(list.get(position));


                                }
                            };
                    AlertDialog.Builder builder = new AlertDialog.Builder(Modify.this); // 创建对话框
                    builder.setTitle("确定要修改吗?");                    // 设置标题
                    // 设置确定按钮的文本以及监听器
                    builder.setPositiveButton("确定", listener);
                    builder.setNegativeButton("取消", null);         // 设置取消按钮
                    builder.show(); // 显示对话框
                }
            });
            return view;
        }
    }

}
