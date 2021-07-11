package com.example.classmatesmemories_xxd_35_20191213;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.classmatesmemories_xxd_35_20191213.bean.PersonInfo;
import com.example.classmatesmemories_xxd_35_20191213.dao.MyHelper;

import java.util.ArrayList;
import java.util.List;

public class queryAll extends AppCompatActivity {
    private int[] icons = {R.drawable.tx1,R.drawable.message,R.drawable.call};

    List<PersonInfo> list;

    private ListView listView;

    private MyHelper myHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query_all);
        ImageView back = findViewById(R.id.img_back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(queryAll.this,Main2Activity.class);
                startActivity(it);
            }
        });
       int j =test(queryAll.this);

        if(j == 1){
            listView = (ListView) findViewById(R.id.lv);

          MyBaseAdapter adapter = new MyBaseAdapter();

          listView.setAdapter(adapter);
        }
    }

   private int test(Context context){
        list = new ArrayList<PersonInfo>();
        int i=0;
        int j=1;
       myHelper = new MyHelper(context);
       SQLiteDatabase db;
       db = myHelper.getReadableDatabase();
       Cursor cursor = db.query("T_pople_xxd",null,null,null,null,null,null);
       if (cursor.getCount() == 0){
        Toast.makeText(this,"no data",Toast.LENGTH_LONG).show();
        j=0;
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
       return  j;
   }
    public class MyBaseAdapter extends BaseAdapter{

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

           View view =convertView != null ? convertView : View.inflate(getApplicationContext(),R.layout.item_temper,null);

            TextView id = view.findViewById(R.id.tv_numberid);
            id.setText(list.get(position).getId_xxd()+"");

            TextView name = view.findViewById(R.id.vet_name);
            name.setText(list.get(position).getName_xxd());

            TextView gender =view.findViewById(R.id.vet_gender);
            gender.setText(list.get(position).getGender_xxd());

             TextView number =view.findViewById(R.id.vet_phone);
            number.setText(list.get(position).getPhone_xxd());

            TextView qq = view.findViewById(R.id.vet_qq);
            qq.setText(list.get(position).getQQ_xxd());

            TextView email = view.findViewById(R.id.vet_email);
            email.setText(list.get(position).getEmail_xxd());

            TextView address = view.findViewById(R.id.vet_address);
            address.setText(list.get(position).getAddress_xxd());

            ImageView call = view.findViewById(R.id.img_call);
            call.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                            String tel = list.get(position).getAddress_xxd(); //获取电话号码
                            Intent intent = new Intent(Intent.ACTION_DIAL);//设置动作为打电话
                            intent.setData(Uri.parse("tel:" + tel));//设置打电话的号码
                            startActivity(intent);
                }
            });
            ImageView message = view.findViewById(R.id.img_message);
            message.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String tels = list.get(position).getAddress_xxd();             //获取电话
                    Intent intent = new Intent();                        //创建 Intent 实例
                    intent.setAction(Intent.ACTION_SENDTO);             //设置动作为发送短信
                    intent.setData(Uri.parse("smsto:"+tels));           //设置发送的号码
                    startActivity(intent);                               //启动 Activity
                }
            });

            return view;
        }
    }

}
