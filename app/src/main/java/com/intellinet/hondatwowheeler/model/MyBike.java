package com.intellinet.hondatwowheeler.model;

import java.io.Serializable;

/**
 * Created by Chandan Dwivedi on 7/17/2017.
 */

public class MyBike implements Serializable{

    int bikeImageUrl;
    String bikeModelName;
    String bikeRegNumber;

    public MyBike(int bikeImageUrl, String bikeModelName, String bikeRegNumber) {
        this.bikeImageUrl = bikeImageUrl;
        this.bikeModelName = bikeModelName;
        this.bikeRegNumber = bikeRegNumber;
    }

    public int getBikeImageUrl() {
        return bikeImageUrl;
    }

    public String getBikeModelName() {
        return bikeModelName;
    }

    public String getBikeRegNumber() {
        return bikeRegNumber;
    }
}
