package com.panachai.myservicedemo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.telephony.PhoneStateListener;
import android.telephony.TelephonyManager;
import android.util.Log;
import android.widget.Toast;

public class CallIn extends BroadcastReceiver {
    Context context;

    @Override
    public void onReceive(Context rcontext, Intent intent) {

        try {
            //เรียกใช้ class TelephonyManager เพื่อจัดการระบบโทรศัพท์ของเครื่องและ event ต่างๆ
            TelephonyManager telemng = (TelephonyManager) context.getSystemService(Context.TELECOM_SERVICE);

            //เพิ่ม event listenner ให้กับ Service
            PhoneState_Listener phoneListener = new PhoneState_Listener();

            //register listenner to TelephonyManager
            //เมื่อทำงาน จะวิ่งไป onCallStateChanged เอง
            telemng.listen(phoneListener, phoneListener.LISTEN_CALL_STATE);
            context = rcontext;

/*
                Bundle bundle = intent.getExtras();
            //ตรวจสอบว่ามีสายเรียกเข้าหรือไม่
            if (bundle != null) {
                //อ่านค่า object ของสายเรียกเข้า
                Object[] pdusObj = (Object[]) bundle.get("pdusObj");

                //อ่านค่าแต่ละค่าใน object pdus
                for (int i = 0; i < pdusObj.length; i++) {
                    //SmsManager curMsg = SmsManager.getDefault();
                    //String callNumber = curMsg;
                }
            }
        } catch () {

        }
        */

        } catch (Exception ex) {
            Log.e("Phone State Error: ", ex.toString());
        }


    }

    //ระบบ รับโทรศัพท์ โทรออก ดักการทำงาน จะมีอยู่แล้ว อันนี้แสดงถึงการทำงานเฉยๆ
    private class PhoneState_Listener extends PhoneStateListener {
        //method ที่คอยตรวจจับสถานะของระบบโทรศัพท์
        public void onCallStateChanged(int state, String callinNumber) {
            //ตรวจสอบว่าสถานะของโทรศัพท์เป็น ringing หรือไม่
            if (state == 1) {
                String msg = "You have call in from Number " + callinNumber;
                Log.d("Callin:", msg);
                Toast.makeText(context, msg, Toast.LENGTH_LONG).show();

            }
        }
    }
}
