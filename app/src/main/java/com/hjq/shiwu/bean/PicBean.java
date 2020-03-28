package com.hjq.shiwu.bean;

import cn.bmob.v3.BmobObject;
import cn.bmob.v3.datatype.BmobFile;

public class PicBean extends BmobObject {
    BmobFile bmobFile;

    public BmobFile getBmobFile() {
        return bmobFile;
    }

    public void setBmobFile(BmobFile bmobFile) {
        this.bmobFile = bmobFile;
    }
}
