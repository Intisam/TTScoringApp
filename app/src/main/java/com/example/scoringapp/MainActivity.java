package com.example.scoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int player1score=0;
    int player2score=0;
    TextView player1s,play1;
    TextView player2s,play2;
    TextView winner;
    ImageView imageView2,imageView3;
    Button savebtn,resetbtn,p1btn,p2btn;
    SharedPreferences sharedPreferences;

    private static final String SHARED_PREF_NAME = "mypref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player1s=findViewById(R.id.score1);
        player2s=findViewById(R.id.score2);
        savebtn=findViewById(R.id.savetbtn);
        resetbtn=findViewById(R.id.resetbtn);
        p1btn=findViewById(R.id.p1btn);
        p2btn=findViewById(R.id.p2btn);
        play1=findViewById(R.id.play1);
        play2=findViewById(R.id.play2);
        winner=findViewById(R.id.winner);
        imageView2=findViewById(R.id.imageView2);
        imageView3=findViewById(R.id.imageView3);
        winner.setText("");

        Bundle ex=getIntent().getExtras();
        byte[] byteArray=ex.getByteArray("Image");
        byte[] byteArray1=ex.getByteArray("Image2");
        Bitmap bmp= BitmapFactory.decodeByteArray(byteArray,0,byteArray.length);
        Bitmap bmp1= BitmapFactory.decodeByteArray(byteArray1,0,byteArray1.length);
        imageView2.setImageBitmap(bmp);
        imageView3.setImageBitmap(bmp1);

        String name1 = getIntent().getStringExtra("name1");
        play1.setText("" +name1);
        String name2 = getIntent().getStringExtra("name2");
        play2.setText("" +name2);

        p1btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1score=player1score+1;
                player1s.setText("Score: "+player1score);
                if(player1score == 5) {
                    Toast.makeText(getApplicationContext(), "Player 1 wins", Toast.LENGTH_SHORT).show();
                    winner.setText("Winner: "+name1);
                }
            }
        });

        p2btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player2score=player2score+1;
                player2s.setText("Score: "+player2score);
                if(player2score == 5){
                    Toast.makeText(getApplicationContext(),"Player 1 wins",Toast.LENGTH_SHORT).show();
                    winner.setText("Winner: "+name2);
                }

            }
        });

        resetbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                player1score=0;
                player2score=0;
                player1s.setText("Score: "+player1score);
                player2s.setText("Score: "+player2score);
                winner.setText("");
            }
        });

        savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sharedPreferences = getSharedPreferences("Scores",MODE_PRIVATE);
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("p1",play1.getText().toString());
                editor.putString("s1",player1s.getText().toString());
                editor.putString("p2",play2.getText().toString());
                editor.putString("s2",player2s.getText().toString());
                editor.apply();
            }
        });





    }

}