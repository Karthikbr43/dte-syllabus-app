package com.app.user.dtesyllabus.tutor;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.app.user.dtesyllabus.R;

/**
 * Created by User on 01-May-17.
 */
public class TutorPageOne extends Fragment {
    Typeface century;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tutor_page_one, container,
                false);

        century = Typeface.createFromAsset(getActivity().getAssets(),"fonts/century.ttf");

        TextView sc_des = (TextView) view.findViewById(R.id.fragment_sc1_tv);
        sc_des.setTypeface(century);
        return view;
    }
}
