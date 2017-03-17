package com.panachai.myservicedemo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;

public class CallIn extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle bundle = intent.getExtras();
 //       try {
            //ตรวจสอบว่ามีสายเรียกเข้าหรือไม่
            if (bundle != null) {
                //อ่านค่า object ของสายเรียกเข้า
                Object[] pdusObj = (Object[]) bundle.get("pdusObj");

                //อ่านค่าแต่ละค่าใน object pdus
                for(int i=0;i<pdusObj.length;i++){
                    //SmsManager curMsg = SmsManager.getDefault();
                    //String callNumber = curMsg;
                }
            }
//        } catch () {

//        }

    }
}
