package com.example.uyg_1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtYazi;
    private TextView txtYazi2;
    private static int gecissuresi=2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtYazi=(TextView)findViewById(R.id.txtYazi);
        txtYazi2=(TextView)findViewById(R.id.txtYazi2);

        Animation splash= AnimationUtils.loadAnimation( this,R.anim.splash);
        txtYazi.startAnimation(splash);
        txtYazi2.startAnimation(splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent gecis = new Intent(MainActivity.this,RehberList.class);
                startActivity(gecis);
                finish();
            }
        },gecissuresi);
    }

}