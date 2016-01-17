package com.android.yzd.memo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.android.yzd.memo.R;
import com.android.yzd.memo.ui.adapter.viewholder.IndexViewHolder;
import com.android.yzd.memo.utils.Utils;

/**
 * Created by Administrator on 2016/1/15.
 */
public class IndexViewAdapter extends RecyclerView.Adapter {

    private final Context mContext;
    private boolean animateItems = true;
    private int ANIMATED_ITEMS_COUNT = 2;
    private int lastAnimatedPosition = -1;

    public IndexViewAdapter(Context context) {
        mContext = context;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_layout, parent, false);
        return new IndexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        runEnterAnimation(holder.itemView, position);
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    private void runEnterAnimation(View view, int position) {

        if (!animateItems || position >= ANIMATED_ITEMS_COUNT - 1) {
            return;
        }

        if (position > lastAnimatedPosition) {
            lastAnimatedPosition = position;
            view.setTranslationY(Utils.getScreenHeight(mContext));
            view.animate()
                    .translationY(0)
                    .setInterpolator(new DecelerateInterpolator(3.f))
                    .setDuration(700)
                    .start();
        }
    }
}
