package com.android.yzd.memo.mvp.ui.adapter.viewholder;

import android.content.Context;
import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.yzd.memo.R;
import com.balysv.materialripple.MaterialRippleLayout;
import com.jakewharton.rxbinding.view.RxView;

import java.util.concurrent.TimeUnit;


/**
 * Created by yezhidong on 2016/1/15.
 */
public class IndexViewHolder extends RecyclerView.ViewHolder {

    private final TextView mTitleTextView;
    private final TextView mNoteContentTextView;
    private final TextView mTimeTextView;
    private final TextView mMemoInfo;
    private final MaterialRippleLayout ripple;
    private final TextView mPassWordTextView;
    private final FrameLayout mMemoInfoContent;
    private final ImageView mImageType;
    private OnRippleClick onRippleClick;

    public IndexViewHolder(View parent) {
        super(parent);
        ripple = (MaterialRippleLayout) parent.findViewById(R.id.ripple);
        mTitleTextView = (TextView) parent.findViewById(R.id.main_item_title);
        mNoteContentTextView = (TextView) parent.findViewById(R.id.main_item_name);
        mTimeTextView = (TextView) parent.findViewById(R.id.main_item_date);
        mPassWordTextView = (TextView) parent.findViewById(R.id.main_item_password);
        mMemoInfo = (TextView) parent.findViewById(R.id.memoInfo);
        mMemoInfoContent = (FrameLayout) parent.findViewById(R.id.main_item_note_container);
        mImageType = (ImageView) parent.findViewById(R.id.imageType);
        RxView.clicks(ripple).throttleFirst(1000, TimeUnit.MILLISECONDS).subscribe(aVoid -> onRippleClick.onRippleClick(ripple));
    }

    public void setMemoInfoContentVisibility(boolean visibility) {
        if (visibility) {
            mMemoInfoContent.setVisibility(View.VISIBLE);
        } else {
            mMemoInfoContent.setVisibility(View.GONE);
        }
    }

    public void setMoRen(Context context) {
        mImageType.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.index_moren));
    }

    public void setYouXiang(Context context) {
        mImageType.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.index_youxiang));
    }

    public void setCard(Context context) {
        mImageType.setBackgroundDrawable(context.getResources().getDrawable(R.mipmap.ic_item_password));
    }

    public void setLabelText(CharSequence text){
        setTextView(mTitleTextView, text);
    }

    public void setLabelText(int text){
        setTextView(mTitleTextView, text);
    }

    public void setContentText(CharSequence text){
        setTextView(mNoteContentTextView, text);
    }

    public void setContentText(int text){
        setTextView(mNoteContentTextView, text);
    }

    public void setTimeText(CharSequence text){
        setTextView(mTimeTextView, text);
    }

    public void setTimeText(int text){
        setTextView(mTimeTextView, text);
    }

    public void setMemoInfo(CharSequence text) {
        setTextView(mMemoInfo, text);
    }

    public TextView getPassWordTextView() {
        return mPassWordTextView;
    }

    public void setPassWordTextView(String passWord) {
        setTextView(mPassWordTextView, passWord);
    }

    private void setTextView(TextView view, CharSequence text){
        if (view == null )
            return;
        if (TextUtils.isEmpty(text))
            view.setVisibility(View.GONE);
        view.setText(text);
    }

    private void setTextView(TextView view, @StringRes int text){
        if (view == null || text <= 0)
            return;
        view.setText(text);
    }

    public void setOnRippleClickListener(OnRippleClick onRippleClick){
        this.onRippleClick = onRippleClick;
    }

    public interface OnRippleClick{
        void onRippleClick(View view);
    }
}
