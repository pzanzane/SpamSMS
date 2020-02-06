package com.example.spamsms.sms;

public class SMSModel {

    public enum SMSCATEGORY{
        SPAM,HAM
    }

    private String mobile;
    private String message;
    private Enum<SMSCATEGORY>  enumSmsCategory = SMSCATEGORY.HAM;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Enum getSMSCATEGORY() {
        return enumSmsCategory;
    }

    public void setSMSCATEGORY(Enum SMSCATEGORY) {
        this.enumSmsCategory = SMSCATEGORY;
    }
}
