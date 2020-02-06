package com.example.spamsms.Rules;

import com.example.spamsms.sms.SMSModel;

public interface IRule {

    public boolean isSpam(SMSModel model);
}
