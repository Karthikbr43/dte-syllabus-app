package com.app.user.dtesyllabus.utilities;

import android.content.Context;
import android.database.SQLException;
import android.graphics.drawable.Drawable;
import android.text.Html;

import com.app.user.dtesyllabus.R;

import java.io.IOException;

/**
 * Created by User on 13-Feb-17.
 */
public class Utility {
    DatabaseHelper myDbHelper;
    Context context;
    int imageNumber;

    public Utility(Context context) {
        myDbHelper = new DatabaseHelper(context);
        this.context = context;
    }

    public DatabaseHelper initializeDB() {
        try {
            myDbHelper.createDataBase();
        } catch (IOException ioe) {
            throw new Error("Unable to create database");
        }

        try {
            myDbHelper.openDataBase();
        }catch(SQLException sqle){
            throw sqle;
        }
        return myDbHelper;
    }

    public Html.ImageGetter getImageGetter(String subject){
        Html.ImageGetter imgGetter;
        switch (subject) {
            case "15EC24P":
                imageNumber = 1;
                imgGetter = new Html.ImageGetter() {
                    public Drawable getDrawable(String source) {
                        Drawable drawable = null;
                        drawable = context.getResources().getDrawable(R.mipmap.msl1);
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    }
                };
                return imgGetter;

            case "15EC64P":
                imageNumber = 1;
                imgGetter = new Html.ImageGetter() {
                    public Drawable getDrawable(String source) {
                        Drawable drawable = null;
                        drawable = context.getResources().getDrawable(R.mipmap.ial);
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    }
                };
                return imgGetter;

            case "15MC55P":
                imageNumber = 1;
                imgGetter = new Html.ImageGetter() {
                    public Drawable getDrawable(String source) {
                        Drawable drawable = null;
                        drawable = context.getResources().getDrawable(R.mipmap.plcl);
                        drawable.setBounds(0, 0, drawable.getIntrinsicWidth(), drawable.getIntrinsicHeight());
                        return drawable;
                    }
                };
                return imgGetter;
        }
        return null;
    }
}