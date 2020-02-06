package com.example.spamsms.Rules;

import com.example.spamsms.sms.SMSModel;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class BankRule implements IRule{

    public boolean isSpam(SMSModel model){


        Pattern patternCredit = Pattern.compile("^.*credit.*$", Pattern.CASE_INSENSITIVE);
        Matcher m = patternCredit.matcher(model.getMessage());

        Pattern patternDebit = Pattern.compile("^.*debit.*$", Pattern.CASE_INSENSITIVE);
        Matcher m2 = patternDebit.matcher(model.getMessage());


        if(m.matches() || m2.matches()){
            return false;
        }

/*        if(model.getMessage().contains("debit")
                || model.getMessage().contains("credit")){
            return false;
        }*/

        return true;
    }
}
