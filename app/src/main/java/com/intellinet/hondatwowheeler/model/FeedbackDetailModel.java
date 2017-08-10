package com.intellinet.hondatwowheeler.model;

/**
 * Created by Chandan Dwivedi on 7/25/2017.
 */

public class FeedbackDetailModel {

    int catImage;
    String catTitle;
    String catStatus;

    public FeedbackDetailModel(int catImage, String catTitle, String catStatus) {
        this.catImage = catImage;
        this.catTitle = catTitle;
        this.catStatus = catStatus;
    }


    public int getCatImage() {
        return catImage;
    }

    public String getCatTitle() {
        return catTitle;
    }

    public String getCatStatus() {
        return catStatus;
    }

    public void setCatStatus(String catStatus) {
        this.catStatus = catStatus;
    }
}
