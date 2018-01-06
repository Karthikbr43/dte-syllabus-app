package com.app.user.dtesyllabus.utilities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.app.user.dtesyllabus.R;

import java.util.ArrayList;

/**
 * Created by User on 03-Mar-17.
 */
public class ImageAdapter extends ArrayAdapter <ImageItem>{
    private Context context;
    private ArrayList<ImageItem> imgList;

    public ImageAdapter(Context context, ArrayList<ImageItem> imgList){
        super(context, R.layout.list_content,imgList);
        this.context = context;
        this.imgList = imgList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = convertView;
        ImageItemHolder holder = null;
        if(v == null){
            // Inflate row layout
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = inflater.inflate(R.layout.list_image, parent, false);
            holder = new ImageItemHolder();
            holder.image_content = (ImageView) v.findViewById(R.id.content_img);
            holder.str_content = (TextView) v.findViewById(R.id.fig_id);

            v.setTag(holder);
        }
        else
            holder = (ImageItemHolder) v.getTag();
        holder.image_content.setImageResource(imgList.get(position).getImgRes());
        holder.str_content.setText(imgList.get(position).getStr());
        return v;
    }
}
