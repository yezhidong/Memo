package com.android.yzd.mima.mvp.presenter.impl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;

import com.android.yzd.mima.R;
import com.android.yzd.mima.mvp.model.Constants;
import com.android.yzd.mima.mvp.model.evenbus.EventCenter;
import com.android.yzd.mima.mvp.presenter.FragmentPresenter;
import com.android.yzd.mima.mvp.ui.activity.CreateLockActivity;
import com.android.yzd.mima.mvp.ui.view.SettingAView;
import com.android.yzd.mima.utils.GetUri;
import com.android.yzd.mima.utils.SPUtils;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by yezhidong on 2016/1/27.
 */
public class SettingFImpl implements FragmentPresenter{

    private final Context mContext;
    private final SettingAView settingAView;
    private Boolean isOpen;
    private boolean isOpenShow;
    private Boolean isOpenAD;
    private AlertDialog mAlertDialog;

    public SettingFImpl(Context context, SettingAView view) {
        mContext = context;
        settingAView = view;
    }

    @Override
    public void onFirstUserVisible() {
        settingAView.findView();
        isOpen = (Boolean) SPUtils.get(mContext, Constants.SETTING.OPEN_GESTURE, true);
        isOpenShow = (Boolean) SPUtils.get(mContext, Constants.SETTING.OPEN_PASS_WORD_SHOW, false);
        isOpenAD = (Boolean) SPUtils.get(mContext, Constants.SETTING.OPEN_AD, true);
        settingAView.initState(isOpen, isOpenAD);
        settingAView.initOpenShow(isOpenShow);
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
            isOpen = !isOpen;
            SPUtils.put(mContext, Constants.SETTING.OPEN_GESTURE, isOpen);
        } else if (TextUtils.equals(key, "首页密码可见")) {
            isOpenShow = !isOpenShow;
            SPUtils.put(mContext, Constants.SETTING.OPEN_PASS_WORD_SHOW, isOpenShow);
            EventBus.getDefault().post(new EventCenter(Constants.EVEN_BUS.CHANGE_PASS_WORD_SHOW));
        }else if (TextUtils.equals(key, "修改手势密码")) {
            Intent intent = new Intent(mContext, CreateLockActivity.class);
            intent.putExtra("CREATE_MODE", Constants.UPDATE_GESTURE);
            settingAView.readyGo(CreateLockActivity.class,intent);
        } else if (TextUtils.equals(key, "更换主题")) {
            settingAView.showChangeThemeDialog();
        } else if (TextUtils.equals(key, "意见反馈")) {
        } else if (TextUtils.equals(key, "给应用点赞")) {
            giveFavor();
        } else if (TextUtils.equals(key, "检测更新")) {
//            UmengUpdateAgent.forceUpdate(mContext);
//            UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
//                @Override
//                public void onUpdateReturned(int updateStatus, UpdateResponse updateResponse) {
//                    switch (updateStatus) {
//                        case UpdateStatus.Yes: // has update
//                            UmengUpdateAgent.showUpdateDialog(mContext, updateResponse);
//                            break;
//                        case UpdateStatus.No: // has no update
//                            settingAView.showSnackBar("当前为最新版本~~");
//                            break;
//                        case UpdateStatus.NoneWifi: // none wifi
//                            settingAView.showSnackBar("没有wifi连接， 只在wifi下更新");
//                            break;
//                        case UpdateStatus.Timeout: // time out
//                            settingAView.showSnackBar("连接超时");
//                            break;
//                    }
//                }
//            });
        } else if (TextUtils.equals(key, "ad_switch")) {
            isOpenAD = !isOpenAD;
            SPUtils.put(mContext, Constants.SETTING.OPEN_AD, isOpenAD);
            if (!isOpenAD) {
                showDialog();
            }
        }
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
        builder.setTitle("主人");
        builder.setMessage("关闭广告前，麻烦主人给我个五星好评～");//
        builder.setPositiveButton(mContext.getString(R.string.dialog_positive_string), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                giveFavor();
                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                }
            }
        });
        builder.setNegativeButton(mContext.getString(R.string.dialog_negative_string), new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (mAlertDialog != null) {
                    mAlertDialog.dismiss();
                }
            }
        });
        mAlertDialog = builder.show();
    }

    public void onThemeChoose(int position) {
        SPUtils.put(mContext, mContext.getResources().getString(R.string.change_theme_key), position);
        EventBus.getDefault().post(new EventCenter(Constants.EVEN_BUS.CHANGE_THEME));
        settingAView.reCreate();
    }

    private void giveFavor(){
        Intent intent = GetUri.getIntent(mContext, mContext.getPackageName());
        boolean b = GetUri.judge(mContext, intent);
        if (b == false) {
            mContext.startActivity(intent);
        } else {
            settingAView.showSnackBar("你还未安装市场软件。");
        }
    }
}
