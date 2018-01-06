package com.app.user.dtesyllabus.utilities;

import android.content.SharedPreferences;

/**
 * Created by User on 16-Nov-16.
 */
public class SemSelector {
    int sem;
    String branch;
    SharedPreferences preferences;
    SharedPreferences.Editor editor;

    public SemSelector(SharedPreferences preferences){
        this.preferences = preferences;
        editor = preferences.edit();
    }

    public void setSem(int sem,String branch){
        this.sem = sem;
        this.branch = branch;
        editor.remove("sem");
        editor.remove("branch");
        editor.putInt("sem",sem);
        editor.putString("branch",branch);
        editor.putBoolean("select",true);
        editor.commit();
    }

    public int getSem(){
        return preferences.getInt("sem",0);
    }

    public String getBranch() {
        return preferences.getString("branch",null);
    }

    public void set_Sem_select(){
        editor.clear();
        editor.commit();
    }

    public String getSemString(int sem){
        switch (sem) {
            case 1:
                return "1st SEM";
            case 2:
                return "2nd SEM";
            case 3:
                return "3rd SEM";
            case 4:
                return "4th SEM";
            case 5:
                return "5th SEM";
            case 6:
                return "6th SEM";
        }
        return null;
    }

    public String getBranchString(String branch){
        switch (branch) {
            case "AN":
                return "Aeronautical Engg";
            case "AG":
                return "Agriculture Engg";
            case "FT":
                return "Apparel Design and Fashion Technology";
            case "AR":
                return "Architecture";
            case "AT":
                return "Automobile Engg";
            case "CR":
                return "Ceramics Technology";
            case"CH":
                return "Chemical Engg.";
            case"CN":
                return "";
            case"CD":
                return "";
            case"EN":
                return "Civil Engg.(Environmental)";
            case"CE":
                return "Civil Engg.";
            case"PH":
                return "";
            case"CS":
                return " Computer Science and Engg";
            case"EE":
                return "Electrical and Electronics Engg.";
            case"EC":
                return "Electronics and communication Engg.";
            case"EI":
                return "";
            case"IS":
                return " Information Science and Engg";
            case"ID":
                return "";
            case"LT":
                return "";
            case"LB":
                return "";
            case"MI":
                return "Mechanical Engg.(Instrumentation)";
            case"ME":
                return "Mechanical Engg.";
            case"HP":
                return "Mechanical Engg.(HPT)";
            case"MY":
                return "Mechanical Engg.(MTT)";
            case"WS":
                return "Mechanical Engg.(WSM)";
            case"MC":
                return "Mechatronics";
            case"MT":
                return "";
            case"MN":
                return "";
            case"MM":
                return "";
            case"PS":
                return "";
            case"PT":
                return "";
            case"SR":
                return "";
            case"TX":
                return "";
            case"WT":
                return "";
        }
        return null;
    }

    public boolean whether_sem_selected(){
        return preferences.getBoolean("select",false);
    }
}
