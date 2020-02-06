package com.example.spamsms.Rules;

import android.util.Log;

import com.example.spamsms.sms.SMSModel;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class KeywordsRule implements IRule {

    //These keywords should be stored in database

    private String strPattern = null;

    public KeywordsRule(){

        List<String> listSpamKeywords = null;
        listSpamKeywords = new ArrayList<>();
        listSpamKeywords.add("off");
        listSpamKeywords.add("sale");
        listSpamKeywords.add("buy");
        listSpamKeywords.add("loan");
        listSpamKeywords.add("free");
        listSpamKeywords.add("download");
        listSpamKeywords.add("offer");
        listSpamKeywords.add("scheme");
        listSpamKeywords.add("launch");
        listSpamKeywords.add("insight");
        listSpamKeywords.add("cashback");

        StringBuilder builder = new StringBuilder();
        builder.append("(?i)");

        for(String key: listSpamKeywords){
            builder.append(key);
            builder.append("|");
        }

        if(builder.charAt(builder.length() - 1) == '|'){
            builder.delete(builder.length() - 1, builder.length());
        }

        strPattern = builder.toString();
        Log.d("KEYWORDSRULE","Pattern: "+strPattern);
    }
    @Override
    public boolean isSpam(SMSModel model) {

        Pattern pattern = Pattern.compile(strPattern, Pattern.CASE_INSENSITIVE);
        Matcher m = pattern.matcher(model.getMessage());

        if(m.find()){

            Log.d("KEYWORDSRULE","SPAM "+"\n"+model.getMessage());
            return true;
        }

        Log.d("KEYWORDSRULE","HAM "+"\n"+model.getMessage());

        return false;
    }

}
