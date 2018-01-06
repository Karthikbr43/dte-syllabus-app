package com.app.user.dtesyllabus;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.app.user.dtesyllabus.tutor.CirclePageIndicator;
import com.app.user.dtesyllabus.tutor.TutorPageOne;
import com.app.user.dtesyllabus.tutor.TutorPageThree;
import com.app.user.dtesyllabus.tutor.TutorPageTwo;

/**
 * Tutorial Activity launches first time the app is installed.
 * It guilds the user the various features of application.
*/

public class TutorActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        overridePendingTransition(R.anim.push_left_in, R.anim.slide_out_left);
        setContentView(R.layout.activity_tutor);

        ViewPager mPager = (ViewPager) findViewById(R.id.viewPager);
        mPager.setAdapter(new ScreenSlidePagerAdapter(
                getSupportFragmentManager()));

        CirclePageIndicator circlePageIndicator = (CirclePageIndicator) findViewById(R.id.pi);
        circlePageIndicator.setViewPager(mPager);
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        finish();
    }

    private class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
        public static final int NUM_PAGES = 3;

        public ScreenSlidePagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position) {
                case 0:
                    return new TutorPageOne();

                case 1:
                    return new TutorPageTwo();

                case 2:
                    return new TutorPageThree();

                default:
                    return new TutorPageThree();
            }
        }

        @Override
        public int getCount() {
            return NUM_PAGES;
        }
    }
}
