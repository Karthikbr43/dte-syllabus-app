package com.app.user.dtesyllabus;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.widget.TextView;

/**
 * AboutActivity display the version of app
 */

public class AboutActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        getSupportActionBar().setTitle("About");

        TextView supportText = (TextView) findViewById(R.id.supportText);
        supportText.setMovementMethod(LinkMovementMethod.getInstance());
        supportText.setText(Html.fromHtml("Send your feedback to following e-mail address:<br>sachin.s27717@gmail.com"));
    }
}
