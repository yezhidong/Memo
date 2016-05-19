package com.android.yzd.memo.mvp.presenter.impl;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.text.TextUtils;

import com.android.yzd.memo.mvp.model.Constants;
import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.presenter.FragmentPresenter;
import com.android.yzd.memo.mvp.ui.activity.CreateLockActivity;
import com.android.yzd.memo.mvp.ui.activity.FeedBackActivity;
import com.android.yzd.memo.utils.SPUtils;
import com.android.yzd.memo.mvp.ui.view.SettingAView;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;
import com.umeng.update.UmengUpdateAgent;
import com.umeng.update.UmengUpdateListener;
import com.umeng.update.UpdateResponse;
import com.umeng.update.UpdateStatus;

import org.greenrobot.eventbus.EventBus;


/**
 * Created by yezhidong on 2016/1/27.
 */
public class SettingFImpl implements FragmentPresenter{

    private final Context mContext;
    private final SettingAView settingAView;
    private Boolean isOpen;
    private boolean isOpenShow;

    public SettingFImpl(Context context, SettingAView view) {
        mContext = context;
        settingAView = view;
    }

    @Override
    public void onFirstUserVisible() {
        settingAView.findView();
        isOpen = (Boolean) SPUtils.get(mContext, Constants.SETTING.OPEN_GESTURE, true);
        isOpenShow = (Boolean) SPUtils.get(mContext, Constants.SETTING.OPEN_PASS_WORD_SHOW, true);
        settingAView.initState(isOpen);
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
            FeedbackAgent mFeedbackAgent = new FeedbackAgent(mContext);
            mFeedbackAgent.sync();
            mFeedbackAgent.closeFeedbackPush();
            mFeedbackAgent.closeAudioFeedback();
            mFeedbackAgent.setWelcomeInfo("感谢反馈意见,我会尽快回复~~");
            Bundle bundle = new Bundle();
            bundle.putString(FeedbackFragment.BUNDLE_KEY_CONVERSATION_ID, mFeedbackAgent.getDefaultConversation().getId());
            settingAView.go2(FeedBackActivity.class, bundle);
        } else if (TextUtils.equals(key, "给应用点赞~")) {
            giveFavor();
        } else if (TextUtils.equals(key, "检测更新")) {
            UmengUpdateAgent.forceUpdate(mContext);
            UmengUpdateAgent.setUpdateListener(new UmengUpdateListener() {
                @Override
                public void onUpdateReturned(int updateStatus, UpdateResponse updateResponse) {
                    switch (updateStatus) {
                        case UpdateStatus.Yes: // has update
                            UmengUpdateAgent.showUpdateDialog(mContext, updateResponse);
                            break;
                        case UpdateStatus.No: // has no update
                            settingAView.showSnackBar("当前为最新版本~~");
                            break;
                        case UpdateStatus.NoneWifi: // none wifi
                            settingAView.showSnackBar("没有wifi连接， 只在wifi下更新");
                            break;
                        case UpdateStatus.Timeout: // time out
                            settingAView.showSnackBar("连接超时");
                            break;
                    }
                }
            });
        }
    }

    public void onThemeChoose(int position) {
        SPUtils.put(mContext, mContext.getResources().getString(R.string.change_theme_key), position);
        EventBus.getDefault().post(new EventCenter(Constants.EVEN_BUS.CHANGE_THEME));
        settingAView.reCreate();
    }

    private void giveFavor(){
        try{
            Uri uri = Uri.parse("market://details?id="+ mContext.getPackageName());
            Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            mContext.startActivity(intent);
        }catch(ActivityNotFoundException e){
            e.printStackTrace();
        }
    }
}
