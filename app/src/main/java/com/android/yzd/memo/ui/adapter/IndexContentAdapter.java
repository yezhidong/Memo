package com.android.yzd.memo.ui.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.android.yzd.memo.ui.fragment.LoginTypeFragment;

/**
 * Created by yezhidong on 2016/1/15.
 */
public class IndexContentAdapter extends FragmentPagerAdapter{

    public IndexContentAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override public Fragment getItem(int position) {
        Fragment fragment = new LoginTypeFragment();
        return fragment;
    }

    @Override public int getCount() {
        return 3;
    }
}
