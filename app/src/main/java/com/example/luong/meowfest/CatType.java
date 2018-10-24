package com.example.luong.meowfest;

import android.graphics.Bitmap;
import android.media.Image;
import android.widget.ImageView;

/**
 * Created by Luong on 10/23/2018.
 */

public class CatType {
    private String title;
    private String timeStamp;
    private String description;
    private Bitmap image;

    public CatType(String title, String timestamp, String description, Bitmap image) {
        this.title = title;
        this.timeStamp = timestamp;
        this.description = description;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getTimeStamp(){
        return timeStamp;
    }

    public String getDescription(){
        return description;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }



}
