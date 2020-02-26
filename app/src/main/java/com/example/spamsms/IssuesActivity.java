package com.example.spamsms;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.example.spamsms.databinding.ActivityMainBinding;
import com.example.spamsms.databinding.IssueActivityBinding;
import com.google.firebase.analytics.FirebaseAnalytics;
import com.google.firebase.crashlytics.FirebaseCrashlytics;
import com.google.firebase.crashlytics.core.CrashlyticsCore;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class IssuesActivity extends AppCompatActivity {

    private Item item = new Item();
    private Clicks clikcks = new Clicks();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IssueActivityBinding issueActivityBinding = DataBindingUtil.setContentView(this, R.layout.issue_activity);
        issueActivityBinding.setItem(item);
        issueActivityBinding.setClicks(clikcks);
    }

    public class Item{

        public String strError = null;
        public String strUserId = null;

        public TextWatcher getUserTextWatcher(){

            return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    strUserId = charSequence.toString().trim();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };

        }
        public TextWatcher getTextWatcher(){

            return new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    strError = charSequence.toString().trim();
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            };
        }


    }
    public class Clicks{



        public void sendReport(){

            Log.d("WASTE","In sendReport");
            Log.d("WASTE","User: "+item.strUserId+" - "+" Error: "+item.strError);

            if(TextUtils.isEmpty(item.strUserId)){
                Toast.makeText(IssuesActivity.this, "User should not be Empty", Toast.LENGTH_SHORT).show();
                return;
            }

            if(TextUtils.isEmpty(item.strError)){
                Toast.makeText(IssuesActivity.this, "Exception Message should not be Empty", Toast.LENGTH_SHORT).show();
                return;
            }



            FirebaseCrashlytics.getInstance().setCustomKey("USERID",String.valueOf(item.strUserId));
            FirebaseCrashlytics.getInstance().setCustomKey("CURRENTSCREEN",getClass().getCanonicalName());
            FirebaseCrashlytics.getInstance().setCustomKey("DATETIME",getDateTime());
            FirebaseCrashlytics.getInstance().recordException(new Throwable(item.strError));
            FirebaseCrashlytics.getInstance().sendUnsentReports();

            Toast.makeText(IssuesActivity.this, "Error logged, should take some time", Toast.LENGTH_SHORT).show();

            item.strUserId = "";
            item.strError = "";
        }
    }

    private String getDateTime(){

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm");
        return  dateFormat.format(Calendar.getInstance().getTime());

    }
}
