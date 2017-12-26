package com.app.user.dtesyllabus;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
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

import com.app.user.dtesyllabus.utilities.DatabaseHelper;
import com.app.user.dtesyllabus.utilities.ListAdapters;
import com.app.user.dtesyllabus.utilities.SemSelector;
import com.app.user.dtesyllabus.utilities.Utility;

/**
 * SubjectsActivity class display the subjects of appropriate sem
 * that the user being selected
 */

public class SubjectsActivity extends AppCompatActivity {

    Cursor c = null;
    String [] stringArray;
    DatabaseHelper myDbHelper;
    SharedPreferences preferences;
    Utility usable;
    SemSelector select;
    int sem;
    String branch;
    Intent intent;
    ListAdapters adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subjects);

        preferences = getSharedPreferences("MyAppData",0);
        select = new SemSelector(preferences);

        intent = getIntent();
        sem = intent.getIntExtra("sem",0);
        branch = intent.getStringExtra("branch");

        TextView LL_Branch = (TextView) findViewById(R.id.SA_ll_branch_tv);
        LL_Branch.setText(branch);
        TextView LL_Sem = (TextView) findViewById(R.id.SA_ll_sem_tv);
        LL_Sem.setText(getResources().getStringArray(R.array.sems)[sem-1]);

        usable = new Utility(SubjectsActivity.this);
        myDbHelper = usable.initializeDB();

        int i=0;
        c = myDbHelper.getSubjectList(sem,branch);
        final int SIZE = c.getCount();
        if(c.moveToFirst()){
            stringArray = new String[SIZE];
            do{
                stringArray[i] = c.getString(0).toUpperCase();
                i++;
            }while (c.moveToNext());
        }

        if (stringArray != null)
            adapt = new ListAdapters(this, stringArray, R.layout.list_row);
        else
            Toast.makeText(this,"null",Toast.LENGTH_LONG).show();
        ListView listview = (ListView) findViewById(R.id.sub_list);
        listview.setAdapter(adapt);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i = new Intent(SubjectsActivity.this,ContentsActivity.class);
                i.putExtra("Branch",branch);
                i.putExtra("Position",position);
                i.putExtra("Sem",sem);
                startActivity(i);
            }
        });

        getSupportActionBar().setLogo(R.mipmap.icon_76_55);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    /*@TargetApi(23)
    void checkRunTimePermission(){
        checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE);

        if (checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    WRITE_REQUEST_CODE);

            // MY_PERMISSIONS_REQUEST_READ_CONTACTS is an
            // app-defined int constant

            return;
        }
    }*/

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(select.whether_sem_selected() && (sem == preferences.getInt("sem",0)) && (preferences.getString("branch",null).equals(branch)))
            getMenuInflater().inflate(R.menu.action_bar_extend,menu);
        else
            getMenuInflater().inflate(R.menu.action_bar_item,menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.sem_select:
                if(!select.whether_sem_selected() || preferences.getInt("sem",0) != sem){
                    item.setIcon(R.drawable.ic_star_gold_24dp);
                    select.setSem(sem,branch);
                    Toast.makeText(SubjectsActivity.this,
                            "You are studying " + select.getSemString(sem) + " in\n" + select.getBranchString(branch),
                            Toast.LENGTH_LONG).show();
                }
                else {
                    item.setIcon(R.drawable.ic_star_white_24dp);
                    select.set_Sem_select();
                }
                break;

            case R.id.home_btn:
                intent = new Intent(SubjectsActivity.this, BranchList_Activity.class);
                startActivity(intent);
                break;

            case R.id.about_btn:
                intent = new Intent(SubjectsActivity.this, AboutActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        if(select.whether_sem_selected()) {
            super.onBackPressed();
            moveTaskToBack(true);
            android.os.Process.killProcess(android.os.Process.myPid());
            System.exit(1);
        }
        else
            super.onBackPressed();
    }
}
