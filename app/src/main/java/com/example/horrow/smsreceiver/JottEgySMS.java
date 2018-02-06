package com.example.horrow.smsreceiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.widget.Toast;

/**
 * Created by Horrow on 2018.02.06..
 */

public class JottEgySMS extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle = intent.getExtras(); //Intent részeit kinyeri
        SmsMessage[] uzenetTomb = null;
        String kuldo = "";
        String uzenet = "";

        if (bundle!=null){
            Object[] segedTomb = (Object[]) bundle.get("pdus"); //bundle részeit beletesszük tömbbe
            uzenetTomb = new SmsMessage[segedTomb.length];

            for (int i = 0; i < segedTomb.length; i++) {
                uzenetTomb[i] = SmsMessage.createFromPdu((byte[]) segedTomb[i]);

                if (i==0){
                    kuldo = uzenetTomb[i].getOriginatingAddress();
                }
                uzenet = uzenet + uzenetTomb[i].getMessageBody();
            }
        }
        Toast.makeText(context,"Küldő: " + kuldo + " " + uzenet, Toast.LENGTH_LONG).show();
        abortBroadcast();
    }
}
