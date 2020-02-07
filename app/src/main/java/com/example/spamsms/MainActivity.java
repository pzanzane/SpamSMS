package com.example.spamsms;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import com.example.spamsms.databinding.ActivityMainBinding;
import com.example.spamsms.sms.SMSModel;
import com.example.spamsms.sms.SpamAlgo;
import com.google.firebase.crashlytics.CrashlyticsRegistrar;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.core.CrashlyticsCore;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding activityMainBinding =  DataBindingUtil.setContentView(this, R.layout.activity_main);
        activityMainBinding.setClicks(new Clicks());
    }


    public static void  addKeyValues(Context context){

        FirebaseCrashlytics.getInstance().setCustomKey("USERID","ABCDC");
        FirebaseCrashlytics.getInstance().setCustomKey("GDGETSTATUS","1234");
        FirebaseCrashlytics.getInstance().setCustomKey("CURRENTSCREEN",context.getClass().getCanonicalName());

    }

    public class Clicks{

        public void gadgetIssue(){

            Log.d("WASTE","gadgetIssue()");
            addKeyValues(MainActivity.this);
            FirebaseCrashlytics.getInstance().recordException(new Exception("This is caused by Problem in Gadget"));
            FirebaseCrashlytics.getInstance().sendUnsentReports();

        }

        public void dataAnomalyissue(){

            Log.d("WASTE","dataAnomalyissue()");
            addKeyValues(MainActivity.this);
            FirebaseCrashlytics.getInstance().recordException(new Exception("This is caused by Problem in Data"));
            FirebaseCrashlytics.getInstance().sendUnsentReports();

        }

        public void nextScreen(){

            Log.d("WASTE","nextScreen()");
            Intent intent = new Intent(MainActivity.this, IssuesActivity.class);
            startActivity(intent);

        }

        public void bluetoothConnectivityIssue(){

            Log.d("WASTE","bluetoothConnectivityIssue()");
            MainActivity.addKeyValues(MainActivity.this);
            FirebaseCrashlytics.getInstance().recordException(new Exception("This is caused by broken connection with bluetooth"));
            FirebaseCrashlytics.getInstance().sendUnsentReports();

        }
        public void networkConnectivityIssue(){

            Log.d("WASTE","networkConnectivityIssue()");
            MainActivity.addKeyValues(MainActivity.this);
            FirebaseCrashlytics.getInstance().recordException(new Exception("This is caused by broken netowork connection while processing"));
            FirebaseCrashlytics.getInstance().sendUnsentReports();

        }

    }

}
