package com.hjq.shiwu.bean;

import cn.bmob.v3.BmobObject;

public class ThingsBean extends BmobObject {
    String type;
    String image;
    String kind;
    String des;
    String tips;
    String time;
    String name;
    String qqNubmber;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTips() {
        return tips;
    }

    public void setTips(String tips) {
        this.tips = tips;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQqNubmber() {
        return qqNubmber;
    }

    public void setQqNubmber(String qqNubmber) {
        this.qqNubmber = qqNubmber;
    }
}
