package com.example.classmatesmemories_xxd_35_20191213;

import android.Manifest;
import android.content.ContentValues;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.classmatesmemories_xxd_35_20191213.dao.MyHelper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class add extends AppCompatActivity implements View.OnClickListener{
    EditText t_name,t_address,t_phone,t_qq,t_email,t_gender;
    Button submit;
    ImageView photo_tx,exit;
    Bitmap image;



    //打开相册的请求码
    private static final int MY_ADD_CASE_CALL_PHONE2 = 7;
    private AlertDialog.Builder builder;
    private AlertDialog dialog;
    private LayoutInflater inflater;

    private View layout;
    private TextView takePhotoTV;
    private TextView choosePhontoTV;
    private TextView cancelTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        photo_tx = findViewById(R.id.img_photo);
        t_name = findViewById(R.id.et_name);
        t_address = findViewById(R.id.et_address);
        t_phone = findViewById(R.id.et_call);
        t_qq = findViewById(R.id.et_qq);
        t_gender = findViewById(R.id.et_gender);
        t_email = findViewById(R.id.et_email);
        submit = findViewById(R.id.btn_submit);
        exit = findViewById(R.id.img_back);

        final MyHelper myHelper = new MyHelper(this);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent it = new Intent(add.this,Main2Activity.class);
                startActivity(it);
            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SQLiteDatabase db;
                ContentValues values;
                db = myHelper.getWritableDatabase();
                values = new ContentValues();
                values.put("name_xxd",t_name.getText().toString().trim());
                values.put("gender_xxd",t_gender.getText().toString().trim());
                values.put("phone_xxd",t_phone.getText().toString().trim());
                values.put("address_xxd",t_address.getText().toString().trim());
                values.put("qq_xxd",t_qq.getText().toString().trim());
                values.put("email_xxd",t_email.getText().toString().trim());
                db.insert("T_pople_xxd",null,values);
                Toast.makeText(add.this,"填写完成",Toast.LENGTH_LONG).show();
                db.close();
            }
        });
    }

    public void updatephoto(View view) {
        viewInit();
    }

    private void viewInit() {
        builder = new AlertDialog.Builder(this);//创建对话框
        inflater =getLayoutInflater();
        layout = inflater.inflate(R.layout.choicephoto,null);//获取自定义布局
        builder.setView(layout);//设置对话框的布局
        dialog = builder.create();//生成最终的对话框
        dialog.show();//显示对话框

        takePhotoTV = layout.findViewById(R.id.photograph);
        choosePhontoTV = layout.findViewById(R.id.photo);
        cancelTV = layout.findViewById(R.id.cancel);

        //设置监听
        takePhotoTV.setOnClickListener(this);
        choosePhontoTV.setOnClickListener(this);
        cancelTV.setOnClickListener(this);

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.photograph:

                        //动态权限：点击相机获取相机权限
                        DongTaiShare();

                        getPicFromCamera();

                break;
        case R.id.photo:
            //点击了相册
            //6.0之后动态申请权限 SD卡写入权限
        if (ContextCompat.checkSelfPermission(add.this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(add.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_ADD_CASE_CALL_PHONE2);

        } else {
            //打开相册
            getPicFromAlbm();
        }
        dialog.dismiss();
        break;
    case R.id.cancel:
        dialog.dismiss();//关闭对话框
        break;
    default:break;
    }
}

    private void getPicFromAlbm() {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, 2);

    }

    private void getPicFromCamera() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        startActivityForResult(intent, 1);
    }

    private void DongTaiShare() {
        if (Build.VERSION.SDK_INT >= 23) {
            String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.CALL_PHONE, Manifest.permission.READ_LOGS, Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.SET_DEBUG_APP, Manifest.permission.SYSTEM_ALERT_WINDOW, Manifest.permission.GET_ACCOUNTS, Manifest.permission.WRITE_APN_SETTINGS, Manifest.permission.CAMERA};
            ActivityCompat.requestPermissions(this, mPermissionList, 123);
        }
    }

    protected void onActivityResult(int requestCode,int resultCode,Intent intent){
        switch (requestCode){
            //调用相机后返回
            case 1:
                if (resultCode == RESULT_OK){
                    final Bitmap photo = intent.getParcelableExtra("data");
                    photo_tx.setImageBitmap(photo);
                }
                break;
            case 2:
                if(resultCode ==RESULT_OK){
                    Uri uri = intent.getData();
                    cropPhone(uri);//裁剪图片
                }
                break;
            case 3:
                Bundle bundle = intent.getExtras();
                if(bundle != null){
                    //在这里获得了剪裁后的Bitmap对象，可以用于上传
                    Bitmap image = bundle.getParcelable("data");
                    //设置到ImageView上
                    photo_tx.setImageBitmap(image);
                    //也可以进行一些保存、压缩等操作后上传
                    String path = saveImage("userHeader",image);
                    File file = new File(path);

//                    这里可以做上传文件的操作
                }
                break;
        }
    }
//裁剪图片
    private void cropPhone(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri,"image/*");
        intent.putExtra("crop","true");
        intent.putExtra("aspectX",1);
        intent.putExtra("aspectY",1);
        intent.putExtra("outputX",300);
        intent.putExtra("outputY",300);
        intent.putExtra("return-data",true);
        startActivityForResult(intent,3);
    }

    //保存图片到本地
    public String saveImage(String name, Bitmap bmp){
        File appDir = new File(Environment.getExternalStorageDirectory().getPath());
        if(!appDir.exists()){
            appDir.mkdir();
        }
        String fileName = name + ".jpg";
        File file= new File(appDir,fileName);
        try {
            FileOutputStream fos = new FileOutputStream(file);
            bmp.compress(Bitmap.CompressFormat.JPEG,100,fos);
            fos.flush();
            fos.close();
            return  file.getAbsolutePath();
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

}
