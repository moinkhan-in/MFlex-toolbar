package com.msquare.flex_toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;

public class MainActivity extends AppCompatActivity implements ViewPager.OnPageChangeListener {

    private static final String TAG = "MainActivity";
    Toolbar mToolbar;
    ViewPager mViewPager;

    int heightForPage0 = 150;
    int heightForPage1 = 400;
    int heightForPage2 = 300;
    int heightForPage3 = 600;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mToolbar = (Toolbar) findViewById(R.id.toolBar);
        mViewPager = (ViewPager) findViewById(R.id.pager);

        mViewPager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        setSupportActionBar(mToolbar);

        mViewPager.addOnPageChangeListener(this);
        mToolbar.setMinimumHeight(heightForPage0);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        Log.d(TAG,positionOffset+"");

        int currentHeight;
        int nextHeight;

        switch (position) {
            case 0:
                currentHeight = heightForPage0;
                nextHeight = heightForPage1;
                calculateHeightAndApply(currentHeight, nextHeight, positionOffset);
                break;
            case 1:
                currentHeight = heightForPage1;
                nextHeight = heightForPage2;
                calculateHeightAndApply(currentHeight, nextHeight, positionOffset);
                break;
            case 2:
                currentHeight = heightForPage2;
                nextHeight = heightForPage3;
                calculateHeightAndApply(currentHeight, nextHeight, positionOffset);
                break;
            case 3:
                // for last page we don't have to worry about it.
                // bcoz there is no next page available;
                break;
        }
    }

    private void calculateHeightAndApply(int currentHeight, int nextHeight, float positionOffset) {
        if (positionOffset==0) {
            return;
        }
        int diff = nextHeight - currentHeight;
        int newHeight = (int) ((positionOffset*diff));
        mToolbar.setMinimumHeight(currentHeight+newHeight);
    }

    @Override
    public void onPageSelected(int position) {}

    @Override
    public void onPageScrollStateChanged(int state) {}

    class MyPagerAdapter extends FragmentStatePagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return BlankFragment.newInstance("My toolbar height is :" + heightForPage0 + " px") ;
                case 1:
                    return BlankFragment.newInstance("My toolbar height is :" + heightForPage1 + " px");
                case 2:
                    return BlankFragment.newInstance("My toolbar height is :" + heightForPage2 + " px");
                case 3:
                    return BlankFragment.newInstance("My toolbar height is :" + heightForPage3 + " px");
                default:
                    return null;
            }
        }

        @Override
        public int getCount() {
            return 4;
        }
    }
}
