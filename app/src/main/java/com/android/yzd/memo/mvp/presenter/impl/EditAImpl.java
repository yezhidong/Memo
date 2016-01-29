package com.android.yzd.memo.mvp.presenter.impl;

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

import com.android.yzd.memo.R;
import com.android.yzd.memo.bean.God;
import com.android.yzd.memo.mvp.model.Realm.RealmHelper;
import com.android.yzd.memo.mvp.presenter.ActivityPresenter;
import com.android.yzd.memo.utils.TimeUtils;
import com.android.yzd.memo.mvp.ui.view.EditAView;

import java.util.ArrayList;

/**
 * Created by Clearlove on 16/1/17.
 */
public class EditAImpl implements ActivityPresenter,
        TextWatcher, AdapterView.OnItemSelectedListener,
        CompoundButton.OnCheckedChangeListener, View.OnFocusChangeListener, DialogInterface.OnClickListener {

    private final Context mContext;
    private final EditAView mEditAView;
    private int mPosition = 0;
    private int createMode;
    private boolean isEdit;

    public EditAImpl(Context context, EditAView view) {
        mContext = context;
        mEditAView = view;
    }
    @Override public void onCreate(Bundle savedInstanceState) {
        String[] stringArray = mContext.getResources().getStringArray(R.array.spinner_item);
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : stringArray) {
            arrayList.add(str);
        }
        mEditAView.initSpinner(arrayList);

    }

    @Override public void getIntent(Intent intent) {
        createMode = intent.getIntExtra("CREATE_MODE", 1);
        switch (createMode) {
            case 0:
                int position = intent.getIntExtra("position", 0);
                int positionType = intent.getIntExtra("positionType", 0);
                ArrayList<God> selector = selector(positionType);
                mEditAView.initViewModel(selector.get(position), positionType);
                mEditAView.setToolBarTitle(R.string.view_mode);
                mEditAView.setTime(TimeUtils.getTime(selector.get(position).getTime()));
                isEdit = false;
                break;
            case 1:
                isEdit = true;
                mEditAView.initCreateModel();
                break;
        }
    }

    private ArrayList<God> selector(int positionType) {
        return RealmHelper.getInstances(mContext).selector(mContext, positionType);
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
                if (isEdit) {
                    String titleName = mEditAView.getTitleName();
                    String userName = mEditAView.getUserName();
                    String passWord = mEditAView.getPassWord();
                    mEditAView.hideKeyBoard();
                    if (!TextUtils.isEmpty(titleName) && !TextUtils.isEmpty(userName) && !TextUtils.isEmpty(passWord)) {
                        mEditAView.showSaveDialog();
                    } else {
                        mEditAView.finishActivity();
                    }
                } else {
                    mEditAView.hideKeyBoard();
                    mEditAView.finishActivity();
                }
                return true;
            default: return false;
        }
    }

    private void saveData() {
        String titleName = mEditAView.getTitleName();
        String userName = mEditAView.getUserName();
        String passWord = mEditAView.getPassWord();
        God god = new God(mPosition, titleName, userName, passWord, TimeUtils.getCurrentTimeInLong());
        switch (createMode) {
            case 0:
                RealmHelper.update(mContext, god);
                break;
            case 1:
                RealmHelper.save(mContext, god);
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
            saveData();
        } else if (which == dialog.BUTTON_NEGATIVE) {
            mEditAView.hideKeyBoard();
            mEditAView.finishActivity();
        }

    }
}
