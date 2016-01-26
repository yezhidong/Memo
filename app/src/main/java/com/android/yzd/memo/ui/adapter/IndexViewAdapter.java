package com.android.yzd.memo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import com.android.yzd.memo.R;
import com.android.yzd.memo.bean.God;
import com.android.yzd.memo.ui.adapter.viewholder.IndexViewHolder;
import com.android.yzd.memo.utils.TimeUtils;
import com.android.yzd.memo.utils.Utils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yezhidong on 2016/1/15.
 */
public class IndexViewAdapter extends RecyclerView.Adapter<IndexViewHolder> implements View.OnClickListener {

    private final Context mContext;
    private List<God> mGodList = new ArrayList<>();
    private OnRecyclerItemClickListener listener;

    public IndexViewAdapter(Context context, ArrayList<God> godArrayList) {
        mContext = context;
        if (godArrayList != null) {
            mGodList.addAll(godArrayList);
        }
    }
    @Override
    public IndexViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_item_layout, parent, false);
        view.findViewById(R.id.ripple).setOnClickListener(this);
        return new IndexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IndexViewHolder holder, int position) {
        holder.setContentText(mGodList.get(position).getCompany());
        holder.setLabelText(mGodList.get(position).getUserName());
        holder.setTimeText(TimeUtils.getConciseTime((mGodList.get(position).getTime()), mContext));
    }

    @Override
    public int getItemCount() {
        return mGodList.size();
    }

    public void addOneTop(God god) {
        mGodList.add(0, god);
    }

    public void addAll(ArrayList<God> godArrayList) {
        mGodList.clear();
        mGodList.addAll(godArrayList);
    }

    @Override
    public void onClick(View v) {
        if (listener != null) {
            listener.onRecyclerItemClick(v);
        }
    }

    public void setOnRecyclerItemClick(OnRecyclerItemClickListener onItemClickListener){
        listener = onItemClickListener;
    }
    public  interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(View view);
    }
}
