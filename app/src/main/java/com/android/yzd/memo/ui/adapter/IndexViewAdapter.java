package com.android.yzd.memo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.yzd.memo.R;
import com.android.yzd.memo.bean.God;
import com.android.yzd.memo.ui.adapter.viewholder.IndexViewHolder;
import com.android.yzd.memo.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yezhidong on 2016/1/15.
 */
public class IndexViewAdapter extends RecyclerView.Adapter<IndexViewHolder> {

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
        View view = LayoutInflater.from(mContext).inflate(R.layout.main_password_item, parent, false);
        return new IndexViewHolder(view);
    }

    @Override
    public void onBindViewHolder(IndexViewHolder holder, int position) {
        holder.setLabelText(mGodList.get(position).getCompany());
        holder.setContentText(mGodList.get(position).getUserName());
        holder.setPassWordTextView(mGodList.get(position).getPassWord());
        holder.setTimeText(TimeUtils.getConciseTime((mGodList.get(position).getTime()), mContext));
        holder.setOnRippleClickListener(new IndexViewHolder.OnRippleClick() {
            @Override
            public void onRippleClick(View view) {
                if (listener != null) {
                    listener.onRecyclerItemClick(view, position);
                }
            }
        });
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


    public void setOnRecyclerItemClick(OnRecyclerItemClickListener onItemClickListener){
        listener = onItemClickListener;
    }
    public  interface OnRecyclerItemClickListener {
        void onRecyclerItemClick(View view, int position);
    }
}
