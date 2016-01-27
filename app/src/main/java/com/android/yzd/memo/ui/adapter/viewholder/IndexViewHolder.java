package com.android.yzd.memo.ui.adapter.viewholder;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.android.yzd.memo.R;
import com.balysv.materialripple.MaterialRippleLayout;


/**
 * Created by yezhidong on 2016/1/15.
 */
public class IndexViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final TextView mTitleTextView;
    private final TextView mNoteContentTextView;
    private final TextView mTimeTextView;
    private final MaterialRippleLayout ripple;
    private final TextView mPassWordTextView;
    private OnRippleClick onRippleClick;

    public IndexViewHolder(View parent) {
        super(parent);
        ripple = (MaterialRippleLayout) parent.findViewById(R.id.ripple);
        mTitleTextView = (TextView) parent.findViewById(R.id.main_item_title);
        mNoteContentTextView = (TextView) parent.findViewById(R.id.main_item_name);
        mTimeTextView = (TextView) parent.findViewById(R.id.main_item_date);
        mPassWordTextView = (TextView) parent.findViewById(R.id.main_item_password);

        ripple.setOnClickListener(this);
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

    @Override
    public void onClick(View v) {
        onRippleClick.onRippleClick(v);
    }

    public void setOnRippleClickListener(OnRippleClick onRippleClick){
        this.onRippleClick = onRippleClick;
    }

    public interface OnRippleClick{
        void onRippleClick(View view);
    }
}
