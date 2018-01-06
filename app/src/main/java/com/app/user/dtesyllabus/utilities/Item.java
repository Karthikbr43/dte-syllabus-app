package com.app.user.dtesyllabus.utilities;

import java.io.Serializable;

/**
 * Created by User on 25-Sep-16.
 */
public class Item implements Serializable {
    private String chapter;
    private String content;

    public String getChapter(){
        return chapter;
    }

    public String getContent(){
        return content;
    }

    public void setChapter(String chapter){
        this.chapter = chapter;
    }

    public void setContent(String content){
        this.content = content;
    }
}
