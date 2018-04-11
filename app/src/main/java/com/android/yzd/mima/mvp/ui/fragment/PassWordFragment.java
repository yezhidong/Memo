package com.android.yzd.mima.mvp.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.android.yzd.mima.R;
import com.android.yzd.mima.mvp.model.Constants;
import com.android.yzd.mima.mvp.model.evenbus.EventCenter;
import com.android.yzd.mima.mvp.presenter.impl.PassWordFImpl;
import com.android.yzd.mima.mvp.ui.view.LoginTypeFView;
import com.android.yzd.mima.utils.SPUtils;
import com.qq.e.ads.banner.ADSize;
import com.qq.e.ads.banner.AbstractBannerADListener;
import com.qq.e.ads.banner.BannerView;
import com.qq.e.comm.util.AdError;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;

import org.greenrobot.eventbus.EventBus;

import butterknife.Bind;

/**
 * A simple {@link Fragment} subclass.
 *
 * @author yezhidong
 */
public class PassWordFragment extends BaseFragment implements LoginTypeFView, View.OnClickListener {

    private static final int INDEX_FRAGMENT_REQUEST_CODE = 2;
    private static final int EDIT_SAVE = 1;
    private static final int SUCCESS = 1;
    @Bind(R.id.recyclerView)
    RecyclerView mRecyclerView;
    @Bind(R.id.exception)
    LinearLayout mException;
    @Bind(R.id.refreshLayout)
    SmartRefreshLayout refreshLayout;
    @Bind(R.id.adcontent)
    RelativeLayout adcontent;
    @Bind(R.id.close)
    View close;
    private PassWordFImpl mIndexFImpl;
    private BannerView mBanner;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mIndexFImpl = new PassWordFImpl(mActivity, this);
        mIndexFImpl.getArgus(getArguments());
        loadAd();
    }


    @Override
    protected void onFirstUserVisible() {
        mIndexFImpl.onFirstUserVisible();
    }

    @Override
    protected void onUserVisible() {
        mIndexFImpl.onUserVisible();
    }

    @Override
    protected void onUserInvisible() {
        mIndexFImpl.onUserInvisible();
    }

    @Override
    protected int getContentViewLayoutID() {
        return R.layout.fragment_login_type;
    }

    @Override
    protected boolean isApplyButterKnife() {
        return true;
    }

    @Override
    protected boolean isApplyEventBus() {
        return true;
    }

    @Override
    protected void onEventComing(EventCenter eventCenter) {
        mIndexFImpl.onEventComing(eventCenter);
    }

    @Override
    public void initRecycler(LinearLayoutManager linearLayoutManager, RecyclerView.Adapter adapter) {
        refreshLayout.setVisibility(View.VISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void readGo(Class clazz, int type, int position, int positionType) {
        Intent intent = new Intent(mActivity, clazz);
        intent.putExtra("CREATE_MODE", type);
        intent.putExtra("position", position);
        intent.putExtra("positionType", positionType);
        startActivityForResult(intent, INDEX_FRAGMENT_REQUEST_CODE);
    }

    @Override
    public void hideException() {
        mException.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showException() {
        mException.setVisibility(View.VISIBLE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == INDEX_FRAGMENT_REQUEST_CODE) {
            if (resultCode == EDIT_SAVE && resultCode == SUCCESS) {
                EventCenter eventCenter = new EventCenter(EDIT_SAVE, true);
                EventBus.getDefault().post(eventCenter);
            }
        }
    }

    @Override
    public void onDestroy() {
        doCloseBanner();
        super.onDestroy();
    }

    private void loadAd() {
        int count = (int) SPUtils.get(mActivity, Constants.COUNT, 1);
        if (count > 10 && count % 3 == 0) {
            adcontent.setVisibility(View.VISIBLE);
            // 创建Banner广告AdView对象
            // appId : 在 http://e.qq.com/dev/ 能看到的app唯一字符串
            // posId : 在 http://e.qq.com/dev/ 生成的数字串，并非 appid 或者 appkey
            mBanner = new BannerView(mActivity, ADSize.BANNER, Constants.AD_AppId, Constants.BANNERID);
            //设置广告轮播时间，为0或30~120之间的数字，单位为s,0标识不自动轮播
            mBanner.setRefresh(30);
            mBanner.setShowClose(true);
            mBanner.setADListener(new AbstractBannerADListener() {


                @Override
                public void onNoAD(AdError adError) {
                    Log.i("AD_DEMO", "onNoAD");

                }

                @Override
                public void onADReceiv() {
                    Log.i("AD_DEMO", "ONBannerReceive");
                }
            });
            adcontent.addView(mBanner);
            /* 发起广告请求，收到广告数据后会展示数据   */
            mBanner.loadAD();
            close.setOnClickListener(this);
        } else {
            adcontent.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.close:
                doCloseBanner();
                break;
            default:
                break;
        }
    }

    private void doCloseBanner() {
        adcontent.removeAllViews();
        if (mBanner != null) {
            mBanner.destroy();
            mBanner = null;
        }
    }

}
