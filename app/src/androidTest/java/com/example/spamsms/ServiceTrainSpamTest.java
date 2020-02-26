package com.example.spamsms;

import android.content.Context;

import com.example.spamsms.trainspam.TrainSpam;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;
import java.io.IOException;
import java.util.List;

import androidx.test.InstrumentationRegistry;
import androidx.test.runner.AndroidJUnit4;

@RunWith(AndroidJUnit4.class)
public class ServiceTrainSpamTest {


    TrainSpam trainSpam = null;
    Context appContext = null;
    @Before
    public void initiaseTest(){

        appContext = InstrumentationRegistry.getTargetContext();
        trainSpam = TrainSpam.getInstance(appContext);
    }
    @Test
    public void checlingMessagesFileCount(){

        try {
            List<File> fileList = trainSpam.getFilesFromAssetDirectory(appContext, "lingmessages");
            Assert.assertEquals(400, fileList.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void checSpamMessagesFileCount(){

        try {
            List<File> fileList = trainSpam.getFilesFromAssetDirectory(appContext, "spammessages");
            Assert.assertEquals(101, fileList.size());

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //This test depending upon initiaseTest()
    @Test
    public void checkSpamMessage(){

        boolean isSpam = trainSpam.isSpam("Get Upto Rs300 Cashback on prepaid recharge using Amazon Pay on Airtel Thanks App. Recharge now : bit.ly/circlerc");

        Assert.assertEquals(true, isSpam);
    }

    //This test depending upon initiaseTest()
    @Test
    public void checkHamMessage(){
        boolean isSpam = trainSpam.isSpam("Your NEFT Txn. with Ref. AXMB200368986604 for Rs. 10000 has been credited to Beneficiary : Ramdas Gangaram Zanz on February 05,2020 at 13:09:45  Hrs.");

        Assert.assertEquals(false, isSpam);
    }
}
