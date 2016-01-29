package com.android.yzd.memo.mvp.ui.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.yzd.memo.mvp.ui.fragment.IndexFragment;

/**
 * Created by yezhidong on 2016/1/15.
 */
public class IndexContentAdapter extends FragmentPagerAdapter{

    public IndexContentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        Fragment fragment = new IndexFragment();
        Bundle argus = new Bundle();
        argus.putInt("position", position);
        fragment.setArguments(argus);
        return fragment;
    }

    @Override public int getCount() {
        return 3;
    }
}
