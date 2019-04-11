package com.pyrusoft.pybot.pysecurity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import java.net.URL;

public class CctvActivity extends AppCompatActivity {


    Button back_btn;
    VideoView Vv_Cam1, Vv_Cam2, Vv_Cam3, Vv_Cam4, Vv_Cam5, Vv_Cam6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cctv);

        getSupportActionBar().setTitle("CCTV footages");

        back_btn = (Button) findViewById(R.id.back);

        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent landIntent = new Intent(CctvActivity.this, MainActivity.class);
                startActivity(landIntent);
                finish();
            }
        });

        Vv_Cam1 = (VideoView) findViewById(R.id.cctv1);
        Vv_Cam2 = (VideoView) findViewById(R.id.cctv2);
        Vv_Cam3 = (VideoView) findViewById(R.id.cctv3);
        Vv_Cam4 = (VideoView) findViewById(R.id.cctv4);
        Vv_Cam5 = (VideoView) findViewById(R.id.cctv5);
        Vv_Cam6 = (VideoView) findViewById(R.id.cctv6);



        //1. Here is the first video
        //setting the footage controller
        MediaController mediaController1 = new MediaController(this);
        mediaController1.setAnchorView(Vv_Cam1);
        //setting the url for the cloud footage
        //for an offline footage; Environment.getExternalStorageDirectory().getPath+"media/name.mp4
        Uri uri_one = Uri.parse("http://192.168.0.164/pySecurity/footages/footage_one.mp4");
        //setting the footage
        Vv_Cam1.setMediaController(mediaController1);
        Vv_Cam1.setVideoURI(uri_one);
        Vv_Cam1.requestFocus();
        Vv_Cam1.start();

        //2. Here is the second video
        //setting the footage controller
        MediaController mediaController2 = new MediaController(this);
        mediaController2.setAnchorView(Vv_Cam2);
        //setting the url for the cloud footage
        Uri uri_two = Uri.parse("http://192.168.0.164/pySecurity/footages/footage_two.mp4");
        //setting the footage
        Vv_Cam2.setMediaController(mediaController2);
        Vv_Cam2.setVideoURI(uri_two);
        Vv_Cam2.requestFocus();
        Vv_Cam2.start();

        //3. Here is the third video
        //setting the footage controller
        MediaController mediaController3 = new MediaController(this);
        mediaController3.setAnchorView(Vv_Cam3);
        //setting the url for the cloud footage
        Uri uri_three = Uri.parse("http://192.168.0.164/pySecurity/footages/footage_three.mp4");
        //setting the footage
        Vv_Cam3.setMediaController(mediaController3);
        Vv_Cam3.setVideoURI(uri_three);
        Vv_Cam3.requestFocus();
        Vv_Cam3.start();

        //4. Here is the fourth video
        //setting the footage controller
        MediaController mediaController4 = new MediaController(this);
        mediaController4.setAnchorView(Vv_Cam4);
        //setting the url for the cloud footage
        Uri uri_four = Uri.parse("http://192.168.0.164/pySecurity/footages/footage_four.mp4");
        //setting the footage
        Vv_Cam4.setMediaController(mediaController4);
        Vv_Cam4.setVideoURI(uri_four);
        Vv_Cam4.requestFocus();
        Vv_Cam4.start();

        //5. Here is the fifth video
        //setting the footage controller
        MediaController mediaController5 = new MediaController(this);
        mediaController5.setAnchorView(Vv_Cam5);
        //setting the url for the cloud footage
        Uri uri_five = Uri.parse("http://192.168.0.164/pySecurity/footages/footage_five.mp4");
        //setting the footage
        Vv_Cam5.setMediaController(mediaController5);
        Vv_Cam5.setVideoURI(uri_five);
        Vv_Cam5.requestFocus();
        Vv_Cam5.start();

        //6. Here is the sixth video
        //setting the footage controller
        MediaController mediaController6 = new MediaController(this);
        mediaController6.setAnchorView(Vv_Cam6);
        //setting the url for the cloud footage
        Uri uri_six = Uri.parse("https://youtu.be/UAoNH0kGG0g");
        //setting the footage
        Vv_Cam6.setMediaController(mediaController6);
        Vv_Cam6.setVideoURI(uri_six);
        Vv_Cam6.requestFocus();
        Vv_Cam6.start();


    }

}
