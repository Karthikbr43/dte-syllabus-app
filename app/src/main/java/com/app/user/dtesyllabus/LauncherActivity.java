package com.app.user.dtesyllabus;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.app.user.dtesyllabus.utilities.SemSelector;

/**
 * LauncherActivity is a launcher screen of this app. It is displayed
 * every time the app is launched.
 */

public class LauncherActivity extends AppCompatActivity {

    SemSelector select;
    Intent intent;
    Typeface century;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);

        getSupportActionBar().hide();

        final SharedPreferences preferences = getSharedPreferences("MyAppData",0);
        select = new SemSelector(preferences);

        final SharedPreferences.Editor editor = preferences.edit();

        century = Typeface.createFromAsset(this.getAssets(),"fonts/century.ttf");

        TextView text1 = (TextView) findViewById(R.id.l_text1);
        TextView text2 = (TextView) findViewById(R.id.l_text2);
        text1.setTypeface(century);
        text2.setTypeface(century);

        /****** Create Thread that will sleep for 1 second *************/
        Thread background = new Thread() {
            @Override
            public void run() {
                try {
                    // Thread will sleep for 2 second
                    sleep(2 * 1000);

                    if(!preferences.getBoolean("isFirstRunCompleted",false))
                    {
                        editor.putBoolean("isFirstRunCompleted",true);
                        editor.commit();
                        intent = new Intent(LauncherActivity.this, TutorActivity.class);
                        startActivity(intent);
                    }
                    else {
                        if (!preferences.getBoolean("select", false)) {
                            intent = new Intent(LauncherActivity.this, BranchList_Activity.class);
                            startActivity(intent);
                        } else {
                            intent = new Intent(LauncherActivity.this, SubjectsActivity.class);
                            intent.putExtra("sem", select.getSem());
                            intent.putExtra("branch", select.getBranch());
                            startActivity(intent);
                        }
                    }

                    // Remove activity
                    finish();
                } catch (Exception e) {
                }
            }
        };
        // start thread
        background.start();
    }
}