package com.example.spamsms;

import android.os.Bundle;
import android.util.Log;

import com.example.spamsms.databinding.ActivityMainBinding;
import com.example.spamsms.databinding.IssueActivityBinding;
import com.google.firebase.crashlytics.FirebaseCrashlytics;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

public class IssuesActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        IssueActivityBinding issueActivityBinding = DataBindingUtil.setContentView(this, R.layout.issue_activity);
        issueActivityBinding.setClicks(new Click());

    }

    public class Click{



    }
}
