package com.android.yzd.memo.presenter.impl;

import android.content.Context;

import com.android.yzd.memo.R;
import com.android.yzd.memo.presenter.ActivityPresenter;
import com.android.yzd.memo.view.EditAView;

import java.util.ArrayList;

/**
 * Created by Clearlove on 16/1/17.
 */
public class EditAImpl implements ActivityPresenter {

    private final Context mContext;
    private final EditAView mEditAView;

    public EditAImpl(Context context, EditAView view) {
        mContext = context;
        mEditAView = view;
    }
    @Override
    public void onCreate() {
        String[] stringArray = mContext.getResources().getStringArray(R.array.spinner_item);
        ArrayList<String> arrayList = new ArrayList<>();
        for (String str : stringArray) {
            arrayList.add(str);
        }
        mEditAView.initSpinner(arrayList);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onStart() {

    }

    @Override
    public void onPause() {

    }

    @Override
    public void onStop() {

    }

    @Override
    public void onDestroy() {

    }
}
