package com.app.user.dtesyllabus.utilities;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.app.user.dtesyllabus.R;

/**
 * Created by User on 01-Feb-17.
 */
public class ListAdapters extends ArrayAdapter {

    private Context context;
    private int resId;
    Typeface tf;
    String [] data = null;

    public ListAdapters(Context context,String [] data,  int layoutResId ){
        super(context, layoutResId, data);
        this.context = context;
        resId = layoutResId;
        tf = Typeface.createFromAsset(context.getAssets(),"fonts/century.ttf");
        this.data = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View mView = convertView;
        if(mView == null){
            LayoutInflater vi = (LayoutInflater)context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            mView = vi.inflate(resId,null);
        }
        TextView tv = (TextView) mView.findViewById(R.id.list_text);
        tv.setTypeface(tf);
        tv.setText(data[position]);
        return mView;
    }
}
