package com.android.yzd.memo.ui.fragment;


import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.v4.app.Fragment;

import com.android.yzd.memo.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragment {


    private static final String PREFERENCE_NAME = "Memo.setting";

    public SettingFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference_xml);
        getPreferenceManager().setSharedPreferencesName(PREFERENCE_NAME);
    }

}
