package com.example.scoringapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.content.SharedPreferences;


public class ScoreActivity extends AppCompatActivity {

    TextView pscore1,pscore2,pscore3,pscore4,pscore5;
    Button newbtn;
    SharedPreferences sharedPreferences;
    private static final String SHARED_PREF_NAME = "mypref";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);
        pscore1=findViewById(R.id.pscore1);
        pscore2=findViewById(R.id.pscore2);
        newbtn=findViewById(R.id.newbtn);

        sharedPreferences = getSharedPreferences("Scores",MODE_PRIVATE);

        String p1name=sharedPreferences.getString("p1","0");
        String p1score=sharedPreferences.getString("s1","0");
        String p2name=sharedPreferences.getString("p2","0");
        String p2score=sharedPreferences.getString("s2","0");
        pscore1.setText("Player : "+p1name+ " "+ p1score);
        pscore2.setText("Player : "+p2name+ " "+ p2score);

        newbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ScoreActivity.this,InfoScreen.class);
                startActivity(intent);
            }
        });




    }
}