package com.hjq.shiwu.bean;

import java.util.jar.JarEntry;

public class KindItem {
    private int Image;
    private String name;

    public KindItem(int Image,String name) {
        super();
        this.Image = Image;
        this.name = name;
    }

    public int getImage() {
        return Image;
    }

    public void setImage(int image) {
        Image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
