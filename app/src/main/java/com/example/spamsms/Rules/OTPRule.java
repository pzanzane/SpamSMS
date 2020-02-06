package com.example.spamsms.Rules;

import com.example.spamsms.sms.SMSModel;

public class OTPRule implements IRule{

    @Override
    public boolean isSpam(SMSModel model) {

        if(model.getMessage().contains("OTP")){
            return false;
        }

        return true;

    }
}
