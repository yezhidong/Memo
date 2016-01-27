package com.android.yzd.memo.presenter.impl;

import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceScreen;

import com.android.yzd.memo.presenter.FragmentPresenter;
import com.android.yzd.memo.view.SettingAView;

/**
 * Created by yezhidong on 2016/1/27.
 */
public class SettingFImpl implements FragmentPresenter{

    private final Context mContext;
    private final SettingAView settingAView;

    public SettingFImpl(Context context, SettingAView view) {
        mContext = context;
        settingAView = view;
    }

    @Override
    public void onFirstUserVisible() {

    }

    @Override
    public void onUserVisible() {

    }

    @Override
    public void onUserInvisible() {

    }

    public void onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        settingAView.showChangeThemeDialog();
    }
}
