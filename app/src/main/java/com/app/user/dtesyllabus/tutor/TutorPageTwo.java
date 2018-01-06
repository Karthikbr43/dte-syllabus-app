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
public class TutorPageTwo extends Fragment {

    Typeface century;
    //private Animation animation;
    //ImageView sc1;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tutor_page_two, container,
                false);

        century = Typeface.createFromAsset(getActivity().getAssets(),"fonts/century.ttf");

        /*animation = AnimationUtils.loadAnimation(getActivity(),
                android.R.anim.fade_out);
        animation.setDuration(500);

        sc1 = (ImageView) view.findViewById(R.id.sc2_img2);*/

        TextView sc_des = (TextView) view.findViewById(R.id.fragment_sc2_tv);
        sc_des.setTypeface(century);
        return view;
    }

    /*@Override
    public void onResume() {
        super.onResume();
        sc1.startAnimation(animation);
    }*/
}
