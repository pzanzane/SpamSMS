package com.example.spamsms.sms;

import android.content.ContentResolver;
import android.database.Cursor;
import android.os.Build;
import android.provider.Telephony;
import android.util.Log;

import com.example.spamsms.sms.SMSModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;

class ReadSMS {

    StringBuilder builder = new StringBuilder();
    public static final String INBOX = "content://sms/inbox";

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public List<SMSModel> readInRange(ContentResolver contentResolver, long fromDate, long toDate){


        //new String[]{String.valueOf(fromDate), String.valueOf(toDate)}
        // Telephony.Sms.DATE + " >= "+ fromDate +" AND "+Telephony.Sms.DATE + " <= "+ toDate

        Cursor cursor = contentResolver.query(Telephony.Sms.Inbox.CONTENT_URI,
                null,
                Telephony.Sms.Inbox.DATE + " >= ?   AND "+Telephony.Sms.Inbox.DATE + " <= ?",
                new String[]{String.valueOf(fromDate), String.valueOf(toDate)},
                null);

        if(cursor == null || cursor.isAfterLast()){
            return null;
        }

        List<SMSModel> list = new ArrayList<>();
        while (cursor.moveToNext()){

            SMSModel model = new SMSModel();
            model.setMessage(cursor.getString(cursor.getColumnIndex(Telephony.Sms.BODY)));
            model.setMobile(cursor.getString(cursor.getColumnIndex(Telephony.Sms.ADDRESS)));

            list.add(model);
            String date = cursor.getString(cursor.getColumnIndex(Telephony.Sms.DATE));

            builder.append("\""+model.getMessage()+ "\"" +","+"\n");
        }

        Log.d("MESSAGE","Messages: "+builder.toString());
        cursor.close();

        return list;
    }


    private void printMessage(){
    }
}
