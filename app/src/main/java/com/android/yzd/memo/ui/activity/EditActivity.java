package com.android.yzd.memo.ui.activity;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;

import com.android.yzd.memo.R;
import com.android.yzd.memo.presenter.impl.EditAImpl;
import com.android.yzd.memo.ui.widget.spinner.NiceSpinner;
import com.android.yzd.memo.view.EditAView;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.List;

import butterknife.Bind;

public class EditActivity extends BaseActivity implements EditAView, AdapterView.OnItemSelectedListener {

    @Bind(R.id.common_toolbar) Toolbar mToolBar;
    @Bind(R.id.spinner) NiceSpinner mSpinner;
    @Bind(R.id.title_edit_text) MaterialEditText mTitleEdt;
    @Bind(R.id.userName) MaterialEditText mUserNameEdt;
    @Bind(R.id.passWord) MaterialEditText mPassWordEdt;
    @Bind(R.id.eye) CheckBox mEyeChB;
    private EditAImpl mEditImpl;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEditImpl = new EditAImpl(this, this);
        mEditImpl.onCreate();
    }

    @Override protected int getContentView() {
        return R.layout.activity_edit;
    }

    @Override protected void initToolbar() {
        initToolBar(mToolBar);
        mToolBar.setTitle("添加");
    }

    @Override protected boolean isApplyTranslucency() {
        return true;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }

    @Override
    public void initSpinner(List<String> data) {
        mSpinner.attachDataSource(data);
        mSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
