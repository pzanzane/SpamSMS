package com.example.spamsms.Rules;

import android.content.ContentResolver;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.util.Log;

import com.example.spamsms.sms.SMSModel;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class ContactRule implements IRule{

    public static class ContactModel{

        private String name;
        private String mobile;
    }

    public interface IContactsList{
        void contacts();
    }

    private ContentResolver contentResolver;
    private List<ContactModel> listModels = null;

    public ContactRule(ContentResolver contentResolver){
        this.contentResolver = contentResolver;
    }

    public void fetchContacts(IContactsList contactsList){


        Cursor cursor = contentResolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                null,null,null,null);

        if(cursor == null || cursor.isAfterLast()){
            contactsList.contacts();
            return;
        }

        listModels = new ArrayList<>();
        while (cursor.moveToNext()){

            ContactModel contactModel = new ContactModel();
            contactModel.name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
            contactModel.mobile = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));

            contactModel.mobile = contactModel.mobile.replaceAll("[^0-9a-zA-Z]+", "");
            listModels.add(contactModel);
        }

        contactsList.contacts();



    }
    public boolean isSpam(SMSModel model){

        if(listModels == null){
            return false;
        }

        for(ContactModel contactModel: listModels){

            Log.d("CONTACT","Mobile: "+contactModel.mobile);
            if(model.getMessage().contains(contactModel.name)
            || model.getMessage().contains(String.valueOf(contactModel.mobile))){
                return false;
            }

            if(model.getMobile().contains(String.valueOf(contactModel.mobile))){
                return false;
            }
        }

        return true;
    }
}
