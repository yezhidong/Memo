package com.android.yzd.memo.mvp.presenter.impl;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.preference.Preference;
import android.preference.PreferenceScreen;
import android.text.TextUtils;

import com.android.yzd.memo.mvp.model.Constans;
import com.android.yzd.memo.R;
import com.android.yzd.memo.mvp.model.evenbus.EventCenter;
import com.android.yzd.memo.mvp.presenter.FragmentPresenter;
import com.android.yzd.memo.mvp.ui.activity.CreateLockActivity;
import com.android.yzd.memo.mvp.ui.activity.FeedBackActivity;
import com.android.yzd.memo.utils.SPUtils;
import com.android.yzd.memo.mvp.ui.view.SettingAView;
import com.umeng.fb.FeedbackAgent;
import com.umeng.fb.fragment.FeedbackFragment;

import de.greenrobot.event.EventBus;

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
        isOpen = (Boolean) SPUtils.get(mContext, Constans.SETTING.OPEN_GESTURE, true);
        isOpenShow = (Boolean) SPUtils.get(mContext, Constans.SETTING.OPEN_PASS_WORD_SHOW, true);
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
            SPUtils.put(mContext, Constans.SETTING.OPEN_GESTURE, isOpen);
        } else if (TextUtils.equals(key, "首页密码可见")) {
            isOpenShow = !isOpenShow;
            SPUtils.put(mContext, Constans.SETTING.OPEN_PASS_WORD_SHOW, isOpenShow);
            EventBus.getDefault().post(new EventCenter(Constans.EVEN_BUS.CHANGE_PASS_WORD_SHOW));
        }else if (TextUtils.equals(key, "修改手势密码")) {
            Intent intent = new Intent(mContext, CreateLockActivity.class);
            intent.putExtra("CREATE_MODE", Constans.UPDATE_GESTURE);
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
        }
    }

    public void onThemeChoose(int position) {
        SPUtils.put(mContext, mContext.getResources().getString(R.string.change_theme_key), position);
        EventBus.getDefault().post(new EventCenter(Constans.EVEN_BUS.CHANGE_THEME));
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
