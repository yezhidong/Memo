package com.android.yzd.mima.mvp.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.android.yzd.mima.R;
import com.android.yzd.mima.databinding.ActivityIndexBinding;
import com.android.yzd.mima.mvp.model.Constants;
import com.android.yzd.mima.mvp.model.evenbus.EventCenter;
import com.android.yzd.mima.mvp.presenter.impl.IndexPreImpl;
import com.android.yzd.mima.mvp.ui.activity.base.BaseActivity;
import com.android.yzd.mima.mvp.ui.adapter.IndexContentAdapter;
import com.android.yzd.mima.mvp.ui.view.IndexAView;
import com.android.yzd.mima.utils.SPUtils;
import com.android.yzd.mima.widget.XViewPager;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;

import butterknife.Bind;

public class IndexActivity extends BaseActivity implements IndexAView{

    private static final int INDEX_REQUEST_CODE = 1;
    private static final int SETTING_REQUEST_CODE = 2;
    private static final int EDIT_SAVE = 1;
    private int SUCCESS = 1;
    @Bind(R.id.common_toolbar) Toolbar mToolBar;
    @Bind(R.id.navigationView) NavigationView mNavigationView;
    @Bind(R.id.typeRecyclerView) RecyclerView mRecyclerView;
    private IndexPreImpl mIndexPre;
    private ActivityIndexBinding mDataBinding;
    private ActionBarDrawerToggle mActionBarDrawerToggle;
    private int INDEX_EVENT_SUCCESS = 1;
    private int currentIndex = 0;
    private ArrayList<String> mData;
    private XViewPager mXViewPager;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mDataBinding = (ActivityIndexBinding) super.mDataBinding;
        initRecycler();
        WindowManager wm = this.getWindowManager();//获取屏幕宽高
        int width1 = wm.getDefaultDisplay().getWidth();
        int height1 = wm.getDefaultDisplay().getHeight();
        ViewGroup.LayoutParams para = mDataBinding.drawerLayout.getLayoutParams();//获取drawerlayout的布局
        para.width=width1/3*2;//修改宽度
        para.height=height1;//修改高度
        mDataBinding.drawerLayout.setLayoutParams(para); //设置修改后的布局。
        mIndexPre = new IndexPreImpl(this, this, mDataBinding);
        mIndexPre.getIntent(getIntent());
        mIndexPre.onCreate(savedInstanceState);
    }

    private void initRecycler() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        String typeString = (String) SPUtils.get(this, Constants.TYPE, "默认");
        String[] split = typeString.split(";");
        mData = new ArrayList<>();
        for (int i = 0; i < split.length; i++) {
            mData.add(split[i]);
        }
        mData.add("+新增分组");
        mRecyclerView.setAdapter(new RecyclerView.Adapter() {
            @Override
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(IndexActivity.this).inflate(R.layout.item_typ, parent, false);
                return new Holder(view);
            }

            @Override
            public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
                Holder viewHolder = (Holder) holder;
                viewHolder.mTypeName.setText(mData.get(position));
                viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mData.size() - 1 == position) {
                            showCreateTypeDialog();
                        } else {
                            mXViewPager.setCurrentItem(position, false);
                            mDataBinding.drawerLayout.closeDrawer(GravityCompat.START);
                        }
                    }
                });
            }

            @Override
            public int getItemCount() {
                return mData.size();
            }
        });
    }

    private void showCreateTypeDialog() {
        final EditText editText = new EditText(this);
        final AlertDialog.Builder builder = new AlertDialog.Builder(this,3);
        builder.setTitle("添加新分组");
        builder.setView(editText);
        builder.setPositiveButton("添加", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String text = editText.getText().toString().trim();
                if (TextUtils.isEmpty(text)) {
                    return;
                }
                String typeString = (String) SPUtils.get(IndexActivity.this, Constants.TYPE, "默认");
                text = typeString + ";" + text;
                SPUtils.put(IndexActivity.this, Constants.TYPE, text);
            }
        });
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        builder.create().show();
    }

    class Holder extends RecyclerView.ViewHolder {

        private final TextView mTypeName;

        public Holder(View itemView) {
            super(itemView);
            mTypeName = (TextView) itemView.findViewById(R.id.typeName);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mIndexPre.onResume();
    }

    @Override
    protected TransitionMode getOverridePendingTransitionMode() {
        return TransitionMode.RIGHT;
    }

    @Override
    protected boolean toggleOverridePendingTransition() {
        return false;
    }

    @Override protected int getContentView() {
        return R.layout.activity_index;
    }

    @Override protected void initToolbar() {
        super.initToolBar(mToolBar);
        mToolBar.setTitle("私人密码本");
    }

    @Override protected boolean isApplyTranslucency() {
        return true;
    }

    @Override protected boolean isApplyButterKnife() {
        return true;
    }

    @Override
    protected boolean isApplyEventBus() {
        return true;
    }

    @Override public void initDrawerToggle() {
        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDataBinding.drawerLayout, mDataBinding.commonToolbar, 0, 0){
            @Override public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
            @Override public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
        };
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mDataBinding.drawerLayout.setDrawerListener(mActionBarDrawerToggle);
        mNavigationView.setItemIconTintList(null);
    }

    @Override public void initXViewPager() {
        mDataBinding.content.setOffscreenPageLimit(mData.size() - 1);
        IndexContentAdapter indexContentAdapter = new IndexContentAdapter(getSupportFragmentManager(), mData);
        mDataBinding.content.setAdapter(indexContentAdapter);
        mXViewPager = mDataBinding.content;
        mXViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                currentIndex = position;
//                if (position == 0) {
//                    mToolBar.setTitle("社交");
//                } else if (position == 1) {
//                    mToolBar.setTitle("游戏");
//                } else {
//                    mToolBar.setTitle("购物");
//                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override public void readyGoForResult(Class clazz) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra("CREATE_MODE", Constants.CREATE_MODE);
        intent.putExtra(EditActivity.CURRENT_TYPE, currentIndex);
        startActivityForResult(intent, INDEX_REQUEST_CODE);
    }

    @Override protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.syncState();
        }
    }

    @Override public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        if (mActionBarDrawerToggle != null) {
            mActionBarDrawerToggle.onConfigurationChanged(newConfig);
        }
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.setting:
                go2Setting();
                return true;
            case R.id.about:
                Intent intent = new Intent(this, AboutActivity.class);
                startActivity(intent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void go2Setting() {
        Intent intent = new Intent(this, SettingActivity.class);
        startActivityForResult(intent, SETTING_REQUEST_CODE);
    }

    @Override
    public void showSnackBar(String msg) {
        Snackbar.make(mToolBar, msg, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void kill() {
        finish();
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        if (eventCenter.getEventCode() == Constants.EVEN_BUS.CHANGE_THEME) {
            reload(false);
        }
    }

    @Override protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INDEX_REQUEST_CODE) {
            if (resultCode == EDIT_SAVE && resultCode == SUCCESS) {
                EventCenter eventCenter = new EventCenter(INDEX_EVENT_SUCCESS, true);
                EventBus.getDefault().post(eventCenter);
            }
        } else if (requestCode == SETTING_REQUEST_CODE) {

        }
    }

    @Override
    public void onBackPressed() {
        if (mIndexPre.onBackPress()) {
            super.onBackPressed();
        }
    }
}
