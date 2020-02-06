package com.example.spamsms.sms;

import android.content.ContentResolver;
import android.util.Log;

import com.example.spamsms.Rules.BankRule;
import com.example.spamsms.Rules.ContactRule;
import com.example.spamsms.Rules.KeywordsRule;
import com.example.spamsms.Rules.OTPRule;

import org.reactivestreams.Subscription;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class SpamAlgo {

    /*
    *
        Not a Spam
        1. Origin of SMS is in Contact
        2. Message of SMS contains Credit or Debit information
        3. Message of SMS contains password change information

        Spam
        1. If SMS origined from Bulk SMS and non of the 'Not Spam' items matches with SMS
    * */

    public interface ISmsList{
        void smsList(List<SMSModel> list);
    }

    public void readSms(ContentResolver contentResolver, long fromDate, long toDate, ISmsList smsList){


        Observable<List<SMSModel>> observable = Observable.create(new ObservableOnSubscribe<List<SMSModel>>() {

            @Override
            public void subscribe(ObservableEmitter<List<SMSModel>> emitter) throws Exception {

                ReadSMS readSMS = new ReadSMS();
                List<SMSModel> list =  readSMS.readInRange(contentResolver,
                        fromDate,
                        toDate);

                list = smsCategorisation(contentResolver, list);

                Log.d("WASTE", " TotalSms: " + list.size());

                emitter.onNext(list);
                emitter.onComplete();
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread());

        Disposable disposable =  observable.subscribe(list -> {
            smsList.smsList(list);

        });
    }

    private List<SMSModel> smsCategorisation(ContentResolver contentResolver, List<SMSModel> list){

        BankRule bankRule = new BankRule();
        OTPRule otpRule = new OTPRule();
        KeywordsRule keywordsRule = new KeywordsRule();
        ContactRule contactRule = new ContactRule(contentResolver);
        contactRule.fetchContacts(()->{

            for(SMSModel model: list){

                Log.d("MESSAGE","Number: "+ model.getMobile() +"\n"
                        +" message: "+model.getMessage() +"\n"
                        +" contactRule: "+ contactRule.isSpam(model)
                        +" bankRule:" +bankRule.isSpam(model)
                        +" otpRule: "+otpRule.isSpam(model) + "\n");

                if(!contactRule.isSpam(model)
                        || !bankRule.isSpam(model)
                        || !otpRule.isSpam(model)
                || !keywordsRule.isSpam(model)){
                    model.setSMSCATEGORY(SMSModel.SMSCATEGORY.HAM);
                }else{
                    model.setSMSCATEGORY(SMSModel.SMSCATEGORY.SPAM);
                }

            }

        });



        return list;
    }


}
