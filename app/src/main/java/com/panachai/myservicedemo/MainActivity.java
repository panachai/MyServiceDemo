package com.panachai.myservicedemo;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity  //Activity
        implements View.OnClickListener {

    Button btnstart, btnstop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnstart = (Button) findViewById(R.id.btnStart);
        btnstop = (Button) findViewById(R.id.btnStop);
        btnstart.setOnClickListener(this);
        btnstop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.btnStart:
                intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.btnStop:
                intent = new Intent(this, MyService.class);

                //สั่งให้ Service หยุดทำงาน
                stopService(intent);
                break;
        }
    }

}


