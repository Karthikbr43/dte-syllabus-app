package com.app.user.dtesyllabus.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.user.dtesyllabus.R;

import java.util.ArrayList;

/**
 * Created by User on 25-Sep-16.
 */
public class ContentAdapter extends ArrayAdapter <Item> {

    private Context context;
    private ArrayList<Item> itemList;
    volatile public Html.ImageGetter img = null;

    public ContentAdapter(Context context, ArrayList<Item> itemList){
        super(context,R.layout.list_content,itemList);
        this.context = context;
        this.itemList = itemList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ItemHolder holder = null;
        if(v == null){
            // Inflate row layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_content, parent, false);
            holder = new ItemHolder();
            holder.chapter_name = (TextView) v.findViewById(R.id.unit_name);
            holder.chapter_content = (TextView) v.findViewById(R.id.contents);
            holder.chapter_name.setVisibility(View.VISIBLE);

            Typeface georgia = Typeface.createFromAsset(context.getAssets(), "fonts/georgia.ttf");
            holder.chapter_name.setTypeface(georgia);
            Typeface century = Typeface.createFromAsset(context.getAssets(), "fonts/Times_New_Roman_Normal.ttf");
            holder.chapter_content.setTypeface(century);
            v.setTag(holder);
        }
        else
            holder = (ItemHolder) v.getTag();
        holder.chapter_name.setText(Html.fromHtml(itemList.get(position).getChapter()));
        if(img == null)
            holder.chapter_content.setText(Html.fromHtml(itemList.get(position).getContent()));
        else
            holder.chapter_content.setText(Html.fromHtml(itemList.get(position).getContent(),img,null));
        return v;
    }
}