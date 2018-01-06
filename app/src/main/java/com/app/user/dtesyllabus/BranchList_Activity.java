package com.app.user.dtesyllabus;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.app.user.dtesyllabus.utilities.ListAdapters;

/**
 * BranchList_Activity display the list of branches in diploma. Then the user
 * can select any of the branch listed out and can access the content.
 */

public class BranchList_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_branch_list_);

        AssetManager assets = getAssets();
        ListAdapters adapt = new ListAdapters(this,getResources().getStringArray(R.array.branches),R.layout.list_row);

        TextView tv = (TextView) findViewById(android.R.id.text1);
        Typeface georgia = Typeface.createFromAsset(assets,"fonts/georgia.ttf");
        if(tv != null)
            tv.setTypeface(georgia);

        ListView listview = (ListView) findViewById(R.id.branch_list);
        listview.setAdapter(adapt);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(BranchList_Activity.this,SemsActivity.class);
                i.putExtra("pos",position);
                startActivity(i);
            }
        });

        getSupportActionBar().setLogo(R.mipmap.icon_76_55);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.action_bar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent intent = new Intent(BranchList_Activity.this, AboutActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        moveTaskToBack(true);
        android.os.Process.killProcess(android.os.Process.myPid());
        System.exit(1);
    }
}
