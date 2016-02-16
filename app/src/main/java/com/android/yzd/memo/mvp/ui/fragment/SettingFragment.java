package com.android.yzd.memo.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceFragment;
import android.preference.PreferenceScreen;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.widget.GridView;

import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.presenter.impl.SettingFImpl;
import com.android.yzd.memo.mvp.ui.activity.SettingActivity;
import com.android.yzd.memo.mvp.ui.adapter.ColorListAdapter;
import com.android.yzd.memo.utils.SPUtils;
import com.android.yzd.memo.mvp.ui.view.SettingAView;
import com.jenzz.materialpreference.SwitchPreference;

import java.util.Arrays;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class SettingFragment extends PreferenceFragment implements SettingAView{


    private static final String PREFERENCE_NAME = "Memo.setting";
    private SettingFImpl settingFImpl;
    private SwitchPreference openGesture;
    private SwitchPreference openShow;

    public SettingFragment() {
        super();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.setting_preference_xml);
        getPreferenceManager().setSharedPreferencesName(PREFERENCE_NAME);
        settingFImpl = new SettingFImpl(getActivity(), this);
        settingFImpl.onFirstUserVisible();
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
        int value = (int) SPUtils.get(getActivity(), getActivity().getResources().getString(R.string.change_theme_key), 1);
        adapter.setCheckItem(value);
        GridView gridView = (GridView) LayoutInflater.from(getActivity()).inflate(R.layout.colors_panel_layout, null);
        gridView.setStretchMode(GridView.STRETCH_COLUMN_WIDTH);
        gridView.setCacheColorHint(0);
        gridView.setAdapter(adapter);
        builder.setView(gridView);
        final AlertDialog dialog = builder.show();
        gridView.setOnItemClickListener((parent, view, position, id) -> {
            dialog.dismiss();
            settingFImpl.onThemeChoose(position);
        });
    }

    @Override
    public void findView() {
        openGesture = (SwitchPreference) findPreference("开启手势密码");
        openShow = (SwitchPreference) findPreference("首页密码可见");
    }

    @Override
    public void initState(boolean isOpen) {
        openGesture.setChecked(isOpen);
    }

    @Override
    public void initOpenShow(boolean isOpen) {
        openShow.setChecked(isOpen);
    }

    @Override
    public void reCreate() {
        SettingActivity activity = (SettingActivity) getActivity();
        activity.reload(false);
    }

    @Override
    public void readyGo(Class clazz, Intent intent) {
        startActivityForResult(intent, 0);
    }

    @Override
    public void go2(Class clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(getView(), msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == 1) {
            showSnackBar("修改成功");
        } else if (resultCode == 0) {
            showSnackBar("放弃修改");
        }
    }
}
