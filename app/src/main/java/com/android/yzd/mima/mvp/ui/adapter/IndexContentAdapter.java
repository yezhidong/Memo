package com.android.yzd.mima.mvp.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.yzd.mima.mvp.ui.fragment.PassWordFragment;

import java.util.ArrayList;

/**
 * Created by yezhidong on 2016/1/15.
 */
public class IndexContentAdapter extends FragmentPagerAdapter{

    private final ArrayList<String> mData;

    public IndexContentAdapter(FragmentManager fm, ArrayList<String> data) {
        super(fm);
        mData = data;
    }

    @Override public Fragment getItem(int position) {
        Fragment fragment = new PassWordFragment();
        Bundle argus = new Bundle();
        argus.putInt("position", position);
        fragment.setArguments(argus);
        return fragment;
    }

    @Override public int getCount() {
        return mData.size() - 1;
    }
}
