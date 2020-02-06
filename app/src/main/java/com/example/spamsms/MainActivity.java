package com.example.spamsms;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.spamsms.sms.SMSModel;
import com.example.spamsms.sms.SpamAlgo;
import com.google.firebase.crashlytics.CrashlyticsRegistrar;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.core.CrashlyticsCore;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {


    private RecyclerView recyclerView = null;
    String[] strPermissions = {Manifest.permission.READ_CONTACTS, Manifest.permission.READ_SMS};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //checkPermission();


    }

    /*private void checkPermission(){
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


    }*/

    @Override
    protected void onResume() {
        super.onResume();


        logExceptions22();
    }



    private void issueOne(){

    }

    private void issueTwo(){

    }
    private void issueThree(){

    }
    private void issueFour(){

    }
    private void issueFive(){

    }
    private void issueSix(){

    }

    private void  logExceptions22(){

        FirebaseCrashlytics.getInstance().setCustomKey("USERID","ABCDC");
        FirebaseCrashlytics.getInstance().setCustomKey("GDGETSTATUS","1234");
        FirebaseCrashlytics.getInstance().setCustomKey("CURRENTSCREEN",getClass().getCanonicalName());

        FirebaseCrashlytics.getInstance().recordException(new Exception("This is Non Fatal Exception"));
        FirebaseCrashlytics.getInstance().sendUnsentReports();
    }

}
