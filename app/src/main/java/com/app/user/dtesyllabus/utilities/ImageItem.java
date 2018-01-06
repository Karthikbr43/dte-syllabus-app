package com.app.user.dtesyllabus.utilities;

import java.io.Serializable;

/**
 * Created by User on 03-Mar-17.
 */
public class ImageItem implements Serializable{
    private int imgRes;
    private String str;

    public int getImgRes(){
        return imgRes;
    }

    public String getStr(){
        return str;
    }

    public void setImg(int img){
        this.imgRes = img;
    }

    public void setStr(String content){
        str = content;
    }
}
