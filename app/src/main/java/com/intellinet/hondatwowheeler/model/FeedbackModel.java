package com.intellinet.hondatwowheeler.model;

/**
 * Created by Administrator on 7/24/2017.
 */

public class FeedbackModel {

    String fTypeTitle;
    int fTypeImage;

    public FeedbackModel(String fTypeTitle, int fTypeImage) {
        this.fTypeTitle = fTypeTitle;
        this.fTypeImage = fTypeImage;
    }

    public String getfTypeTitle() {
        return fTypeTitle;
    }

    public int getfTypeImage() {
        return fTypeImage;
    }
}
