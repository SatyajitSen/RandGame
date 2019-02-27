package com.satyajitsentutul.randgame;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView txtHighScore,txtScore;
    Button btnPlay, btnReset ;

    //Declare shared preferences object
    SharedPreferences sp ;
    SharedPreferences.Editor editor ;
    private final String SP_GAME="com.satyajitsentutul.randgame.game";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPlay=findViewById(R.id.btn_play);
        btnReset=findViewById(R.id.btn_reset);

        txtHighScore=findViewById(R.id.txt_high_score);
        txtScore=findViewById(R.id.txt_score);

        sp=getSharedPreferences(SP_GAME,MODE_PRIVATE);
        editor=sp.edit();

        // get value from shared preference file
        int high_score=sp.getInt("high_score",0);
        txtHighScore.setText("High score: "+high_score);

        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Random random=new Random();
                int score=random.nextInt(2000);
                txtScore.setText(String.valueOf(score));
                int getSavedScore =sp.getInt("high_score",0);
                if((score>getSavedScore)){
                    txtHighScore.setText("High Score:"+score);
                    editor.putInt("high_score",score);
                    editor.apply();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                txtHighScore.setText("High Score: 0");
                txtScore.setText(""+0);
                editor.putInt("high_score",0);
                editor.apply();
            }
        });
    }
}
