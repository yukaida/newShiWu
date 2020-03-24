package com.hjq.shiwu.bean;

public class BannerBean {
    String imageUrl;
    String jumpUrl;

    public BannerBean(String imageUrl, String jumpUrl) {
        super();
        this.imageUrl = imageUrl;
        this.jumpUrl = jumpUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getJumpUrl() {
        return jumpUrl;
    }

    public void setJumpUrl(String jumpUrl) {
        this.jumpUrl = jumpUrl;
    }
}
