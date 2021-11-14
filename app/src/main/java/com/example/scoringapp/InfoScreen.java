package com.example.scoringapp;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;


public class InfoScreen extends AppCompatActivity {

    Button startbtn,recordbtn;
    EditText p1name,p2name;
    ImageView imageView2,imageView3;
    Bitmap bitmap,bitmap1;


    public static final int CAMERA_PERM_CODE = 101;
    public static final int CAMERA_REQUEST_CODE = 102;
    public static final int CAMERA_REQUEST_CODE1 = 103;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_screen);
        startbtn=findViewById(R.id.startbtn);
        recordbtn=findViewById(R.id.recordbtn);
        p1name=findViewById(R.id.play1name);
        p2name=findViewById(R.id.play2name);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             askCameraPermissions();
            }
        });

        imageView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askCameraPermissions();
            }
        });



        startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(bitmap != null) {
                    ByteArrayOutputStream bytes = new ByteArrayOutputStream();
                    ByteArrayOutputStream bytes1 = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 100, bytes);
                    bitmap1.compress(Bitmap.CompressFormat.JPEG, 100, bytes1);
                    byte[] byteArray = bytes.toByteArray();
                    byte[] byteArray1 = bytes1.toByteArray();
                    Intent intent = new Intent(InfoScreen.this, MainActivity.class);
                    intent.putExtra("Image",byteArray);
                    intent.putExtra("Image2",byteArray1);
                    intent.putExtra("name1", p1name.getText().toString());
                    intent.putExtra("name2", p2name.getText().toString());
                    startActivity(intent);
                }
            }
        });

        recordbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(InfoScreen.this,ScoreActivity.class);
                startActivity(intent);
            }
        });


    }

    private void askCameraPermissions() {
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this,new String[] {Manifest.permission.CAMERA}, CAMERA_PERM_CODE);
        }else {
            openCamera();
            openCamera2();
        }
    }


    private void openCamera() {
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE);

    }

    private void openCamera2(){
        Intent camera=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(camera, CAMERA_REQUEST_CODE1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == CAMERA_REQUEST_CODE) {
            bitmap=(Bitmap) data.getExtras().get("data");
            imageView2.setImageBitmap(bitmap);
        }
        if(requestCode == CAMERA_REQUEST_CODE1){
            bitmap1=(Bitmap) data.getExtras().get("data");
            imageView3.setImageBitmap(bitmap1);
        }
    }
}


