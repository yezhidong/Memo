package com.android.yzd.memo.mvp.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.yzd.memo.mvp.model.evenbus.EventCenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.lang.reflect.Field;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/1/15.
 * lazyLoadFragment
 */
public abstract class BaseFragment extends Fragment {

    private boolean isFirstResume = true;
    private boolean isFirstVisible = true;
    private boolean isFirstInvisible = true;
    private boolean isPrepared;
    protected FragmentActivity mActivity;


    @Nullable @Override public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(getContentViewLayoutID(), container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mActivity = getActivity();
        if (isApplyButterKnife()) ButterKnife.bind(this, view);
        if (isApplyEventBus()) EventBus.getDefault().register(this);
    }

    @Override public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initPrepare();
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        if (isApplyEventBus()) EventBus.getDefault().unregister(this);
        if (isApplyButterKnife()) ButterKnife.unbind(this);
        super.onDestroyView();

    }

    @Override public void onDetach() {
        super.onDetach();
        // for bug ---> java.lang.IllegalStateException: Activity has been destroyed
        try {
            Field childFragmentManager = Fragment.class.getDeclaredField("mChildFragmentManager");
            childFragmentManager.setAccessible(true);
            childFragmentManager.set(this, null);

        } catch (NoSuchFieldException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @Override public void onResume() {
        super.onResume();
        if (isFirstResume) {
            isFirstResume = false;
            return;
        }
        if (getUserVisibleHint()) {
            onUserVisible();
        }
    }

    @Override public void onPause() {
        super.onPause();
        if (getUserVisibleHint()) {
            onUserInvisible();
        }
    }

    @Override public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        if (isVisibleToUser) {
            if (isFirstVisible) {
                isFirstVisible = false;
                initPrepare();
            } else {
                onUserVisible();
            }
        } else {
            if (isFirstInvisible) {
                isFirstInvisible = false;
                onFirstUserInvisible();
            } else {
                onUserInvisible();
            }
        }
    }

    private synchronized void initPrepare() {
        if (isPrepared) {
            onFirstUserVisible();
        } else {
            isPrepared = true;
        }
    }


    @Subscribe
    public void onMessageEvent(EventCenter event) {
        if (event != null) {
            onEventComing(event);
        }
    }

    /**
     * when fragment is visible for the first time, here we can do some initialized work or refresh data only once
     */
    protected abstract void onFirstUserVisible();

    /**
     * this method like the fragment's lifecycle method onResume()
     */
    protected abstract void onUserVisible();

    /**
     * when fragment is invisible for the first time
     */
    private void onFirstUserInvisible() {
        // here we do not recommend do something
    }

    /**
     * this method like the fragment's lifecycle method onPause()
     */
    protected abstract void onUserInvisible();

    protected abstract int getContentViewLayoutID();

    protected abstract boolean isApplyButterKnife();

    protected abstract boolean isApplyEventBus();

    protected abstract void onEventComing(EventCenter eventCenter);
}
