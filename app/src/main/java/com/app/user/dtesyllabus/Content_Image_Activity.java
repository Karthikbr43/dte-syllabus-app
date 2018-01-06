package com.app.user.dtesyllabus;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;

import com.app.user.dtesyllabus.utilities.ImageAdapter;
import com.app.user.dtesyllabus.utilities.ImageItem;

import java.util.ArrayList;

/**
 * This class display the subject specific figures only if there is
 * anything exist otherwise it simply display a smiley along with
 * message "There is no image to preview"
 */

public class Content_Image_Activity extends Activity {

    ArrayList <ImageItem> list = new ArrayList<ImageItem>();
    ImageAdapter adapt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content__image_);

        Intent intent = getIntent();
        String code = intent.getStringExtra("code");

        adapt = new ImageAdapter(this,list);

        startDisplay(code);

        ListView listview = (ListView) findViewById(R.id.ig_list);
        listview.setAdapter(adapt);
    }

    void startDisplay(String code){
        switch(code) {
            case "15SC01M":
                setItem(R.mipmap.m1u61, "Fig 1.1");
                setItem(R.mipmap.m1u62, "Fig 1.2");
                setItem(R.mipmap.m1u63, "Fig 1.3");
                break;

            case "15SC02M":
                setItem(R.mipmap.m2u11, "Fig 1.1");
                setItem(R.mipmap.m2u12, "Fig 1.2");
                setItem(R.mipmap.m2u13, "Fig 1.3");
                setItem(R.mipmap.m2u41, "Fig 1.4");
                setItem(R.mipmap.m2u42, "Fig 1.5");
                setItem(R.mipmap.m2u43, "Fig 1.6");
                break;

            case "15CS22P":
                setItem(R.mipmap.bwdl1, "Fig 1.1");
                setItem(R.mipmap.bwdl2, "Fig 1.2");
                setItem(R.mipmap.bwdl3, "Fig 1.3");
                break;

            case "dbmsl":         /*Instead of 15CS36P*/
                setItem(R.mipmap.dbmsl1, "Fig 1.1");
                setItem(R.mipmap.dbmsl2, "Fig 1.2");
                break;

            case "15IS22P":
                setItem(R.mipmap.bwdl1, "Fig 1.1");
                setItem(R.mipmap.bwdl2, "Fig 1.2");
                setItem(R.mipmap.bwdl3, "Fig 1.3");
                break;

            case "15IS32P":
                setItem(R.mipmap.dbmsl1, "Fig 1.1");
                setItem(R.mipmap.dbmsl2, "Fig 1.2");
                break;

            case "adal":     /*Instead of 15IS44P*/
                setItem(R.mipmap.adal1, "Fig 1.1");
                setItem(R.mipmap.adal2, "Fig 1.2");
                setItem(R.mipmap.adal3, "Fig 1.3");
                setItem(R.mipmap.adal4, "Fig 1.4");
                setItem(R.mipmap.adal5, "Fig 1.5");
                break;

            case "15CH35P":
                setItem(R.mipmap.cl21, "Fig 1.1");
                setItem(R.mipmap.cl22, "Fig 1.2");
                break;

            case "15EN22P":
                setItem(R.mipmap.mocl1, "Fig 1.1");
                setItem(R.mipmap.mocl2, "Fig 1.2");
                setItem(R.mipmap.mocl3, "Fig 1.3");
                setItem(R.mipmap.mocl4, "Fig 1.4");
                break;

            case "15CE14P":
                setItem(R.mipmap.mocl1, "Fig 1.1");
                setItem(R.mipmap.mocl2, "Fig 1.2");
                setItem(R.mipmap.mocl3, "Fig 1.3");
                setItem(R.mipmap.mocl4, "Fig 1.4");
                break;

            case "15EC23P":
                setItem(R.mipmap.del1, "Fig 1.1");
                setItem(R.mipmap.del2, "Fig 1.2");
                break;

            case "15EE37P":
                setItem(R.mipmap.csl1, "Fig 1.1");
                setItem(R.mipmap.csl2, "Fig 1.2");
                setItem(R.mipmap.csl3, "Fig 1.3");
                setItem(R.mipmap.csl4, "Fig 1.4");
                setItem(R.mipmap.csl5, "Fig 1.5");
                setItem(R.mipmap.csl6, "Fig 1.6");
                setItem(R.mipmap.csl7, "Fig 1.7");
                setItem(R.mipmap.csl8, "Fig 1.8");
                setItem(R.mipmap.csl9, "Fig 1.9");
                setItem(R.mipmap.csl10, "Fig 1.10");
                setItem(R.mipmap.csl11, "Fig 1.11");
                break;

            case "15IS53P":
                setItem(R.mipmap.plsql1, "Fig 1.1");
                setItem(R.mipmap.plsql2, "Fig 1.2");
                setItem(R.mipmap.plsql3, "Fig 1.3");
                break;

            case "15EE57P":
                setItem(R.mipmap.esiml, "Fig 1.1");
                break;

            default:
                setItem(R.mipmap.smiley, "No image to preview");
        }
    }

    void setItem(int imgId, String str){
        final ImageItem item = new ImageItem();
        item.setImg(imgId);
        item.setStr(str);
        list.add(item);
    }
}
