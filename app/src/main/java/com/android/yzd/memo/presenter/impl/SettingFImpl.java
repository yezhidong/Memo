package com.android.yzd.memo.presenter.impl;

import android.content.Context;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.text.TextUtils;

import com.android.yzd.memo.presenter.FragmentPresenter;
import com.android.yzd.memo.utils.SPUtils;
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
        String key = preference.getKey();

        if (TextUtils.equals(key, "开启手势密码")) {
            SPUtils.put(mContext, "开启手势密码", false);
        } else if (TextUtils.equals(key, "修改手势密码")) {

        } else if (TextUtils.equals(key, "更换主题")) {
            settingAView.showChangeThemeDialog();
        } else if (TextUtils.equals(key, "意见反馈")) {

        } else if (TextUtils.equals(key, "给应用点赞")) {

        }
    }
}
