package com.intellinet.hondatwowheeler.model;

/**
 * Created by Administrator on 8/8/2017.
 */

public class ContactUsModel {

    String conHeader;
    String conTitle;
    String conAddress;
    String conPhone;
    String conMail;

    public ContactUsModel(String conHeader, String conTitle, String conAddress, String conPhone, String conMail) {
        this.conHeader = conHeader;
        this.conTitle = conTitle;
        this.conAddress = conAddress;
        this.conPhone = conPhone;
        this.conMail = conMail;
    }


    public String getConHeader() {
        return conHeader;
    }

    public String getConTitle() {
        return conTitle;
    }

    public String getConAddress() {
        return conAddress;
    }

    public String getConPhone() {
        return conPhone;
    }

    public String getConMail() {
        return conMail;
    }
}
