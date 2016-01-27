package com.android.yzd.memo.ui.fragment;


import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.GridView;

import com.android.yzd.memo.R;
import com.android.yzd.memo.presenter.impl.SettingFImpl;
import com.android.yzd.memo.ui.adapter.ColorListAdapter;
import com.android.yzd.memo.view.SettingAView;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragment implements SettingAView{


    private static final String PREFERENCE_NAME = "Memo.setting";
    private SettingFImpl settingFImpl;

    public SettingFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference_xml);
        getPreferenceManager().setSharedPreferencesName(PREFERENCE_NAME);
        settingFImpl = new SettingFImpl(getActivity(), this);
    }

    @Override
    public boolean onPreferenceTreeClick(PreferenceScreen preferenceScreen, Preference preference) {
        settingFImpl.onPreferenceTreeClick(preferenceScreen, preference);
        return super.onPreferenceTreeClick(preferenceScreen, preference);
    }

    @Override
    public void showChangeThemeDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("更换主题");
        Integer[] res = new Integer[]{R.drawable.red_round, R.drawable.brown_round, R.drawable.blue_round,
                R.drawable.blue_grey_round, R.drawable.yellow_round, R.drawable.deep_purple_round,
                R.drawable.pink_round, R.drawable.green_round};
        List<Integer> list = Arrays.asList(res);
        ColorListAdapter adapter = new ColorListAdapter(getActivity(), list);
        adapter.setCheckItem(0);
        GridView gridView = (GridView) LayoutInflater.from(getActivity()).inflate(R.layout.colors_panel_layout, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.show();
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            dialog.dismiss();
//            settingPresenter.onThemeChoose(position);
        });
    }

}
