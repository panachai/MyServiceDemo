package com.panachai.myservicedemo;


import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
    private MyThread myThread;
    private MediaPlayer player;


    //@Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    //สร้าง onCreate() เมื่อมีการสั่ง start service จะมาเรียกใช้ method นี้
    public void onCreate() {

        myThread = new MyThread();
    }

    //เมื่อ Service ถูกสร้างแล้ว
    public int onStartCommand(Intent intent, int flags, int startID) {
        Toast.makeText(this, "Service Started ....", Toast.LENGTH_LONG).show();
        //ถ้า Thread not alive สั่งให้ thread ทำงาน
        if(!myThread.isAlive()){
            myThread.start();
        }

        //กรณี service ถูกทำลาย ก็จะทำการสร้างและเรียก method (แรมไม่พอ ถูกปิด อื่นๆ) จะทำให้เรียกใช้ใหม่อัตรโนมัติ
        return START_STICKY;
    }

    //ถ้าสั่งให้ stop service จะเรียก method onDestroy ของ Service
    @Override
    public void onDestroy(){
        Toast.makeText(this,"Service Stopped....",Toast.LENGTH_LONG).show();
        myThread.finish=true;
    }


    public class MyThread extends Thread {
        //ตั้งชื่อ thread
        private static final String TAG = "MyServiceDemo";
        //กำหนดเวลา sleep
        private static final int DELAY = 3000; //นับเป็น intrual (เป็นรอบๆ)
        //running number ที่แสดงบน log
        private int i = 0;
        //ตัวแปรที่ใช้ในกาตรวจสอบการทำงานของ Thread ว่าเสร็จหรือยัง
        private boolean finish = false;

        //method เมื่อ thread ถูกเรียกใช้งาน
        public void run() {

            MediaPlayer play = MediaPlayer.create(getBaseContext(),
                    R.raw.something_just_like_this_jfla);
            play.start();

            //สั่งให้พิมพ์ค่า i บน Log ทุก 3 วินาที
            while (true) {//ทำให้ขณะ Thread ยังมีชีวิต
                //แสดงค่า i บน log
                Log.d(TAG, Integer.toString(i++));

                //สั่งให้ Thread Sleep
                try {
                    sleep(DELAY);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //เมื่อสั่งให้ Stop Service ให้ออกจากลูปยุติการทำงานของ Thread
                if (finish == true) {
                    play.stop();
                    return; //return false
                }

            }
        }
    }
}
