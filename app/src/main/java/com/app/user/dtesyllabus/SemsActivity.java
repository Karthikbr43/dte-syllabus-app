package com.app.user.dtesyllabus;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.app.user.dtesyllabus.utilities.ListAdapters;

/**
 * SemsActivity display the list of semister. Then the user
 * can select any of the sem listed out.
 */

public class SemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sems);

        final String [] Branch = {"AT","CR","CH","CE","EN","CS","EE","EC","IS","ME","HP","MI","MY","WS","MC"};
        Intent i = getIntent();
        final int pos = i.getIntExtra("pos",0);

        TextView LL_Branch = (TextView) findViewById(R.id.SEM_ll_branch_tv);
        LL_Branch.setText(Branch[pos]);

        ImageView home = (ImageView) findViewById(R.id.SA_home);

        ListAdapters adapt = new ListAdapters(this,getResources().getStringArray(R.array.sems),R.layout.list_row);

        ListView listview = (ListView) findViewById(R.id.sem_list);
        listview.setAdapter(adapt);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SemsActivity.this, SubjectsActivity.class);
                i.putExtra("sem", position + 1);
                i.putExtra("branch", Branch[pos]);
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
        Intent intent = new Intent(SemsActivity.this, AboutActivity.class);
        startActivity(intent);
        return super.onOptionsItemSelected(item);
    }
}
