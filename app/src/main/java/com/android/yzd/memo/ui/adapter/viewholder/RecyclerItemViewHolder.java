package com.android.yzd.memo.ui.adapter.viewholder;

import android.support.annotation.StringRes;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.android.yzd.memo.R;


/**
 * Created by yezhidong on 2016/1/15.
 */
public class RecyclerItemViewHolder extends RecyclerView.ViewHolder{

    private final TextView mNoteLabelTextView;
    private final TextView mNoteContentTextView;
    private final TextView mNoteTimeTextView;
    public RecyclerItemViewHolder(View parent) {
        super(parent);
        mNoteLabelTextView = (TextView) parent.findViewById(R.id.note_label_text);
        mNoteContentTextView = (TextView) parent.findViewById(R.id.note_content_text);
        mNoteTimeTextView = (TextView) parent.findViewById(R.id.note_last_edit_text);
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
}
