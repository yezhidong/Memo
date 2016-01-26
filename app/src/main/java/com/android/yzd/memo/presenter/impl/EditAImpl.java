package com.android.yzd.memo.presenter.impl;

import android.content.Context;
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
import com.android.yzd.memo.presenter.ActivityPresenter;
import com.android.yzd.memo.utils.TimeUtils;
import com.android.yzd.memo.view.EditAView;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by Clearlove on 16/1/17.
 */
public class EditAImpl implements ActivityPresenter, TextWatcher, AdapterView.OnItemSelectedListener, CompoundButton.OnCheckedChangeListener {

    private final Context mContext;
    private final EditAView mEditAView;
    private int mPosition = 0;

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
        int mode = intent.getIntExtra("CREATE_MODE", 1);
        switch (mode) {
            case 0:

                break;
            case 1:
                mEditAView.initCreateModel();
                break;
        }
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
                mEditAView.hideKeyBoard();
                mEditAView.finishActivity();
                return true;
            default: return false;
        }
    }

    private void saveData() {
        String titleName = mEditAView.getTitleName();
        String userName = mEditAView.getUserName();
        String passWord = mEditAView.getPassWord();
        God god = new God(mPosition, titleName, userName, passWord, TimeUtils.getCurrentTimeInLong());
        Realm realm = Realm.getInstance(mContext);
        realm.beginTransaction();
        realm.copyToRealm(god);
        realm.commitTransaction();
        mEditAView.hideKeyBoard();
        mEditAView.finishActivity();
    }

    @Override public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        mPosition = position;
    }

    @Override public void onNothingSelected(AdapterView<?> parent) {

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        mEditAView.setPassWordVisible(isChecked);
    }

}
