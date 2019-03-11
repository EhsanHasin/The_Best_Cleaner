package com.csofcs.ehsanhasin.thebestcleaner.Model;

import android.graphics.drawable.Drawable;

/**
 * Created by intag pc on 2/16/2017.
 */

public class Apps {

    String catgory;
    String name;
    int size;
    Drawable image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public Drawable getImage() {
        return image;
    }

    public void setImage(Drawable image) {
        this.image = image;
    }

    public String getCatgory() {
        return catgory;
    }

    public void setCatgory(String catgory) {
        this.catgory = catgory;
    }
}
