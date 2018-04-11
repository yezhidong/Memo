package com.android.yzd.mima.mvp.presenter.impl;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CompoundButton;

import com.android.yzd.mima.R;
import com.android.yzd.mima.mvp.model.Constants;
import com.android.yzd.mima.mvp.model.Realm.RealmHelper;
import com.android.yzd.mima.mvp.model.bean.God;
import com.android.yzd.mima.mvp.presenter.ActivityPresenter;
import com.android.yzd.mima.mvp.ui.activity.EditActivity;
import com.android.yzd.mima.mvp.ui.view.EditAView;
import com.android.yzd.mima.utils.SPUtils;
import com.android.yzd.mima.utils.TimeUtils;

import java.util.ArrayList;
import java.util.Collections;

import me.imid.swipebacklayout.lib.SwipeBackLayout;

/**
 * Created by Clearlove on 16/1/17.
 */
public class EditAImpl implements ActivityPresenter,
        TextWatcher, AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener, DialogInterface.OnClickListener, SwipeBackLayout.SwipeListener, View.OnClickListener {

    private final Context mContext;
    private final EditAView mEditAView;
    private int mPosition = 0;
    private int createMode;
    private boolean isEdit;
    private boolean isCreate;
    private God mGodInfo;
    private int positionType;
    private String mPositiveButtonMsg;
    private int p;
    private String[] mSplit;

    public EditAImpl(Context context, EditAView view) {
        mContext = context;
        mEditAView = view;
    }
    @Override public void onCreate(Bundle savedInstanceState) {
        ArrayList<String> arrayList = new ArrayList<>();
        String typeString = (String) SPUtils.get(mContext, Constants.TYPE, "默认");
        mSplit = typeString.split(";");
        for (int i = 0; i < mSplit.length; i++) {
            arrayList.add(mSplit[i]);

        }
        mEditAView.initSpinner(arrayList);
        mEditAView.getSwipeBack().addSwipeListener(this);

    }

    @Override public void getIntent(Intent intent) {

        createMode = intent.getIntExtra("CREATE_MODE", 1);
        switch (createMode) {
            case 0:// 查看
                p = intent.getIntExtra("position", 0);
                // 密码类型
                mPosition = positionType = intent.getIntExtra("positionType", 0);
                ArrayList<God> selector = selector(positionType);
                mGodInfo = selector.get(p);
                mEditAView.initViewModel(mGodInfo, positionType);
                mEditAView.setToolBarTitle(R.string.view_mode);
                mEditAView.setTime(TimeUtils.getTime(mGodInfo.getTime()));
                isEdit = false;
                break;
            case 1:// 添加
                int intExtra = intent.getIntExtra(EditActivity.CURRENT_TYPE, 0);
                mPosition = intExtra;
                isCreate = true;
                mEditAView.initCreateModel(intExtra);
                break;
        }
    }

    private ArrayList<God> selector(int positionType) {
        ArrayList<God> selector = RealmHelper.getInstances(mContext).selector(mContext, positionType);
        if (selector != null && selector.size() > 0) {
            Collections.reverse(selector);
        }
        return selector;
    }

    @Override public void onResume() {

    }

    @Override public void onStart() {

    }

    @Override public void onPause() {

    }

    @Override public void onStop() {

    }

    @Override public void onDestroy() {

    }

    @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override public void onTextChanged(CharSequence s, int start, int before, int count) {
        String titleName = mEditAView.getTitleName();
        String userName = mEditAView.getUserName();
        String passWord = mEditAView.getPassWord();
        if (!TextUtils.isEmpty(passWord)) {
            if (!TextUtils.isEmpty(titleName) && !TextUtils.isEmpty(userName)) {
                mEditAView.setItemMenuVisible(true);
            } else {
                mEditAView.setItemMenuVisible(false);
            }
        } else {
            mEditAView.setItemMenuVisible(false);
        }
    }

    @Override public void afterTextChanged(Editable s) {

    }

    public boolean onOptionItemSelected(MenuItem item){
        switch (item.getItemId()) {
            case R.id.done:
                saveData();
                return true;

            case android.R.id.home:
                return comeBack();
            default: return false;
        }
    }

    public boolean comeBack() {
        if (isEdit) {
            String userName = mEditAView.getUserName();
            String passWord = mEditAView.getPassWord();
            mEditAView.hideKeyBoard();
            if (positionType != mPosition || !TextUtils.equals(userName, mGodInfo.getUserName()) || !TextUtils.equals(passWord, mGodInfo.getPassWord())) {
                mPositiveButtonMsg = "保存";
                mEditAView.showDialog("密码还未保存，是否先保存在退出", mPositiveButtonMsg);
            } else {
                mEditAView.finishActivity();
            }
        } else {
            mEditAView.hideKeyBoard();
            mEditAView.finishActivity();
        }
        return true;
    }

    private void saveData() {
        String titleName = mEditAView.getTitleName();
        String userName = mEditAView.getUserName();
        String passWord = mEditAView.getPassWord();
        String memoInfo = mEditAView.getMemoInfo();
        God god = new God(mPosition, titleName, userName, passWord, TimeUtils.getCurrentTimeInLong(), memoInfo);
        switch (createMode) {
            case 0:
                if (!RealmHelper.update(mContext, god)) {
                    mEditAView.showSnackToast("修改失败");
                    mEditAView.hideKeyBoard();
                    return;
                }
                break;
            case 1:
                if (RealmHelper.save(mContext, god)) {
                    mEditAView.showSnackToast("保存失败，已经存在-"+god.getTitle()+"-的标题");
                    mEditAView.hideKeyBoard();
                    return;
                }
                break;
        }

        mEditAView.hideKeyBoard();
        mEditAView.finishActivity();
    }
    @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        isEdit = true;
        mPosition = position;
    }

    @Override public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mEditAView.setPassWordVisible(isChecked);
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        if (hasFocus) {
            isEdit = true;
            mEditAView.setToolBarTitle(R.string.edit_mode);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == dialog.BUTTON_POSITIVE) {
            if (TextUtils.equals(mPositiveButtonMsg, "确定")) {
                RealmHelper.delete(mContext, mGodInfo, p);
                mEditAView.finishActivity();
            } else {
                saveData();
            }
        } else if (which == dialog.BUTTON_NEGATIVE) {
            if (!TextUtils.equals(mPositiveButtonMsg, "确定")) {
                mEditAView.hideKeyBoard();
                mEditAView.finishActivity();
            }
        }

    }

    @Override
    public void onScrollStateChange(int state, float scrollPercent) {
        mEditAView.hideKeyBoard();
    }

    @Override
    public void onEdgeTouch(int edgeFlag) {

    }

    @Override
    public void onScrollOverThreshold() {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.deleteButton) {
            mPositiveButtonMsg = "确定";
            mEditAView.showDialog("将永久删除该密码备忘记录~~", mPositiveButtonMsg);
        }
    }
}
