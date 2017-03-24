package com.panachai.myservicedemo;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class IncomeSMS extends BroadcastReceiver {
    //ประกาศ object เป็นตัวจัดการ SMS
    SmsManager smsmng = SmsManager.getDefault();


    @Override
    public void onReceive(Context context, Intent intent) {
        //รับข้อมูล sms ที่ส่งมาด้วย getExtras
        Bundle bundle = intent.getExtras();
        try {
            //ตรวจสอบว่ามี sms ส่งมาหรือไม่
            if (bundle != null) {
                Object[] pduObj = (Object[]) bundle.get("pdus");
                /*PDU (Protocol Description Unit
                ที่จัดการรูปแบบของ sms ที่เป็น array
                โดย array ก็คือข้อความที่ถูกแบ่งออกเป็นชิ้นย่อยๆ*/
                for (int i = 0; i < pduObj.length; i++) {
                    //ใช้ SMS_Message ในการอ่านข้อความ
                    SmsMessage msg = SmsMessage.createFromPdu((byte[]) pduObj[i]);
                    //อ่านเบอร์โทรศัพท์ที่ส่ง sms มา
                    String phoneNumber = msg.getDisplayOriginatingAddress();
                    //get ข้อความที่อ่านผ่าน SmsMessage
                    String smsMsg = msg.getDisplayMessageBody();
                    String showMsg = "SMS From : " + phoneNumber + " :: Message is : " + smsMsg;
                    Log.d("SMS Receive:", showMsg);
                    Toast.makeText(context, showMsg, Toast.LENGTH_LONG).show();
                }

            }
        } catch (Exception e) {
            Log.e("Sms Error : ", e.toString());
        }

    }


}
