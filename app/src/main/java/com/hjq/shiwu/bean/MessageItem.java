package com.hjq.shiwu.bean;

import cn.bmob.v3.BmobObject;

public class MessageItem extends BmobObject {
    String content;

    public MessageItem(String content) {
        super();
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
