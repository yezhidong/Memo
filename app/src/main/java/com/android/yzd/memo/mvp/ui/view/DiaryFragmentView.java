// (c)2016 MiGuDongMan Co.,Ltd, All Rights Reserved.
package com.android.yzd.memo.mvp.ui.view;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Android Studio.
 * User: yezhidong
 * Date: 16/5/19
 * Time: 下午11:10
 */
public interface DiaryFragmentView {

    void initRecycler(LinearLayoutManager linearLayoutManager, RecyclerView.Adapter adapter);
    void readGo(Class clazz, int type, int position, int positionType);
    void hideException();
    void showException();
}
