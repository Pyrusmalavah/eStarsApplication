package com.pyrusoft.pybot.pysecurity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;


import pl.droidsonroids.gif.GifImageView;

public class SplashActivity extends AppCompatActivity {

    private GifImageView gifLogo;
    private static int splashTimeOut = 4000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        gifLogo = (GifImageView) findViewById(R.id.gif_logo);

        Animation myanim = AnimationUtils.loadAnimation(this, R.anim.splash);
        gifLogo.startAnimation(myanim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent authIntent = new Intent(SplashActivity.this, AuthenticationActivity.class);
                startActivity(authIntent);
                finish();
            }
        }, splashTimeOut);
    }
}
