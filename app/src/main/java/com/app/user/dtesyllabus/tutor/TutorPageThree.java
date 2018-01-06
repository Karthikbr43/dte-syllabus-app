package com.app.user.dtesyllabus.tutor;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.app.user.dtesyllabus.BranchList_Activity;
import com.app.user.dtesyllabus.R;

/**
 * Created by User on 01-May-17.
 */
public class TutorPageThree extends Fragment {

    Typeface century;
    Intent intent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_tutor_page_three, container,
                false);

        century = Typeface.createFromAsset(getActivity().getAssets(),"fonts/century.ttf");

        Button Btn = (Button) view.findViewById(R.id.get_start_btn);
        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), BranchList_Activity.class);
                startActivity(intent);
            }
        });
        return view;
    }
}
