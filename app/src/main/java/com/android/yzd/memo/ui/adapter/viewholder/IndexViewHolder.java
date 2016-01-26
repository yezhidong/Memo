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

    private final TextView mNoteLabelTextView;
    private final TextView mNoteContentTextView;
    private final TextView mNoteTimeTextView;
    private final MaterialRippleLayout materialRippleLayout;
    private OnRippleClick onRippleClick;

    public IndexViewHolder(View parent) {
        super(parent);
        materialRippleLayout = (MaterialRippleLayout) parent.findViewById(R.id.ripple);
        mNoteLabelTextView = (TextView) parent.findViewById(R.id.note_label_text);
        mNoteContentTextView = (TextView) parent.findViewById(R.id.note_content_text);
        mNoteTimeTextView = (TextView) parent.findViewById(R.id.note_last_edit_text);

        materialRippleLayout.setOnClickListener(this);
    }

    public void setLabelText(CharSequence text){
        setTextView(mNoteLabelTextView, text);
    }

    public void setLabelText(int text){
        setTextView(mNoteLabelTextView, text);
    }

    public void setContentText(CharSequence text){
        setTextView(mNoteContentTextView, text);
    }

    public void setContentText(int text){
        setTextView(mNoteContentTextView, text);
    }

    public void setTimeText(CharSequence text){
        setTextView(mNoteTimeTextView, text);
    }

    public void setTimeText(int text){
        setTextView(mNoteTimeTextView, text);
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
