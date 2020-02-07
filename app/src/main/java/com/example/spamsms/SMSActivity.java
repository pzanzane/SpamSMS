package com.example.spamsms;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;

import com.example.spamsms.databinding.ActivityMainBinding;
import com.example.spamsms.sms.SMSModel;
import com.example.spamsms.sms.SpamAlgo;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

public class SMSActivity extends AppCompatActivity {

    String[] strPermissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main);
        checkPermission();
    }

    private void checkPermission(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {


            if(hasAllPermissions(strPermissions)){
                readSMS();
            }else{
                requestPermission();
            }

        }else{

            readSMS();
        }
    }
    private boolean hasAllPermissions(String[] permissions){

        for(String permission: permissions){

            if(!hasPermission(permission)){
                return false;
            }
        }

        return true;
    }
    private boolean hasPermission(String permission){
        return ContextCompat.checkSelfPermission(this, permission) == PackageManager.PERMISSION_GRANTED;

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void requestPermission(){

        requestPermissions(strPermissions, 101);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        if(grantResults.length > 0 ){

            boolean hasAllPermissions = true;
            for(Integer grants: grantResults){

                if(grants != PackageManager.PERMISSION_GRANTED){
                    checkPermission();
                    hasAllPermissions = false;
                    break;
                }
            }

            if(hasAllPermissions){

                readSMS();

            }


        }

    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void readSMS(){

        Calendar fromCalendar = Calendar.getInstance();
        fromCalendar.add(Calendar.DATE, -30);
        SpamAlgo spamAlgo = new SpamAlgo();
        spamAlgo.readSms(getContentResolver(), fromCalendar.getTimeInMillis(), Calendar.getInstance().getTimeInMillis(),
                (listSms) -> {

                    Log.d("WASTE", "Received SMS Count: "+ listSms.size());
                    for(SMSModel model: listSms){
                        Log.d("WASTE", "SMS: "+ model.getMessage());
                        Log.d("WASTE", "Category: "+ model.getSMSCATEGORY().name() + "\n"+" ");
                    }

                });


    }
}
