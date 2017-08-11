package com.intellinet.hondatwowheeler.model;

import java.io.Serializable;

/**
 * Created by Administrator on 7/31/2017.
 */

public class NotificationModel implements Serializable{

    String title;
    String time;
    String desc;
    int img;

    public NotificationModel(String title, String time, String desc, int img) {
        this.title = title;
        this.time = time;
        this.desc = desc;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public String getTime() {
        return time;
    }

    public String getDesc() {
        return desc;
    }

    public int getImg() {
        return img;
    }
}
