package com.app.user.dtesyllabus;

import android.content.Intent;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.TextView;

import com.app.user.dtesyllabus.utilities.ContentAdapter;
import com.app.user.dtesyllabus.utilities.DatabaseHelper;
import com.app.user.dtesyllabus.utilities.Item;
import com.app.user.dtesyllabus.utilities.Utility;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * ContentsActivity display the main content of the syllabus
 * along with semister, subject name, code etc.
 */

public class ContentsActivity extends AppCompatActivity {

    AssetManager asset;
    InputStream Ifile;
    String contentText;
    ContentAdapter adapt;
    ArrayList<Item> list = new ArrayList<Item>();
    DatabaseHelper myDbHelper;
    Utility usable;
    Cursor c;
    String branch;
    int pos;
    int sem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contents);

        Intent intent = getIntent();
        branch = intent.getStringExtra("Branch");
        pos = intent.getIntExtra("Position",0);
        sem = intent.getIntExtra("Sem",0);

        usable = new Utility(ContentsActivity.this);
        myDbHelper = usable.initializeDB();
        c = myDbHelper.getSubjectDetails(branch,pos+1,sem);
        c.moveToFirst();
        asset = getAssets();

        try {
            Ifile = asset.open("subjects/" + c.getString(5).trim() + ".txt");
            int size = Ifile.available();
            byte[] buffer = new byte[size];
            Ifile.read(buffer);
            contentText = new String(buffer);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        adapt = new ContentAdapter(this,list);

        TextView NameTV = (TextView) findViewById(R.id.sub_name);
        TextView CodeTV = (TextView) findViewById(R.id.sub_code);
        TextView SemTV = (TextView) findViewById(R.id.sem);
        TextView CreditsTV = (TextView) findViewById(R.id.credit);
        TextView SeeTV = (TextView) findViewById(R.id.see);
        String SubjectName = c.getString(2).toUpperCase().trim();

        //Removing "E :" from subject name
        if(sem == 5 || sem == 6) {
            if(SubjectName.substring(0,3).equals("E :")) {
                SubjectName = SubjectName.substring(4);
            }
        }
        NameTV.setText(Html.fromHtml("<b>Course Title :</b> " + SubjectName));
        CodeTV.setText(Html.fromHtml("<b>Course Code :</b> " + c.getString(3)));
        SemTV.setText(Html.fromHtml("<b>Sem :</b> " + c.getString(1)));
        CreditsTV.setText(Html.fromHtml("<b>Credits :</b> " + c.getString(7)));

        adapt.img = usable.getImageGetter(c.getString(3));

        if(c.getString(6).equals("T") || c.getString(6).equals("D")) {
            SeeTV.setText(Html.fromHtml("<b>SEE :</b> 100 Marks"));
            setContentForTheory();
        }
        else if(c.getString(6).equals("PL")) {
            SeeTV.setText(Html.fromHtml("<b>SEE :</b> 00 Marks"));
            setOpenContent();
        }
        else if(c.getString(6).equals("P")) {
            if(sem == 5) {
                CreditsTV.setText(Html.fromHtml("<b>Credits :</b> --"));
                SeeTV.setText(Html.fromHtml("<b>SEE :</b> 00 Marks"));
            }
            else if(sem == 6)
                SeeTV.setText(Html.fromHtml("<b>SEE :</b> 50 Marks"));
            setOpenContent();
        }
        else {
            SeeTV.setText(Html.fromHtml("<b>SEE :</b> 50 Marks"));
            setContentForPracticle();
        }

        ListView listview = (ListView) findViewById(R.id.unit_list);
        listview.setAdapter(adapt);

        getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        getSupportActionBar().setHomeButtonEnabled(false);

        getSupportActionBar().setLogo(R.mipmap.icon_76_55);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }

    private void setContentForTheory(){
        int pos = contentText.indexOf("UNIT", 0);
        while(pos >= 0)
        {
            int space = contentText.indexOf("\n", pos+4);
            String chapter = contentText.substring(pos,space);

            pos = contentText.indexOf("UNIT", space);
            if(pos < 0 ) {
                try{
                    pos = contentText.indexOf("Text books");
                    setItem(chapter, "<p>" + contentText.substring(space + 1, pos - 4) + "</p>");
                }
                catch (StringIndexOutOfBoundsException e){
                    pos = contentText.indexOf("Reference Books");
                    setItem(chapter, "<p>" + contentText.substring(space + 1, pos - 4) + "</p>");
                }
                break;
            }
            setItem(chapter,"<p>" + contentText.substring(space + 1,pos - 1)+"</p>");
        }
        setItem(" ", contentText.substring(pos - 4));
    }

    private void setContentForPracticle(){
        int pos = contentText.indexOf("Unit", 0);
        if (pos >= 0) {
            while(pos >= 0)
            {
                int space = contentText.indexOf("\n", pos+4);
                String chapter = contentText.substring(pos,space);

                pos = contentText.indexOf("Unit", space);
                if(pos < 0) {
                    pos = contentText.indexOf("Reference Books");
                    setItem(chapter, "<p>" + contentText.substring(space + 1, pos - 4) + "</p>");
                    break;
                }
                setItem(chapter, "<p>" + contentText.substring(space + 1,pos - 1) + "</p>");
            }
            int temp = contentText.indexOf("Scheme of Valuation", pos);
            setItem(" ", contentText.substring(pos - 4 , temp - 1));
            setItem(" ", contentText.substring(temp - 4));
        }
        else {
            pos = contentText.indexOf("Reference Books");
            setItem(" ", contentText.substring(0 , pos - 4));
            int temp = contentText.indexOf("Scheme of Valuation", pos);
            setItem(" ", contentText.substring(pos - 4 , temp - 1));
            setItem(" ", contentText.substring(temp - 4));
        }
    }

    private void setOpenContent() {
        setItem(" ", contentText);
    }

    private void setItem(String Chapter, String Content){
        final Item item = new Item();
        item.setChapter(Chapter);
        item.setContent(Content);
        list.add(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.content_action_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        Intent i = new Intent(ContentsActivity.this, Content_Image_Activity.class);
        String sub_code;
        if(branch.equals("CS") && sem == 3 && pos == 5)
            sub_code = "dbmsl";
        else if(branch.equals("IS") && sem == 4 && pos == 6)
            sub_code = "adal";
        else
            sub_code = c.getString(3);
        i.putExtra("code", sub_code);
        startActivity(i);
        return super.onOptionsItemSelected(item);
    }
}