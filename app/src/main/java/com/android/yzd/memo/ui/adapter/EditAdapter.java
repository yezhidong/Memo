package com.android.yzd.memo.ui.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;

import com.android.yzd.memo.R;
import com.android.yzd.memo.model.bean.God;
import com.android.yzd.memo.ui.adapter.viewholder.EditViewHolder;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Clearlove on 16/1/17.
 */
public class EditAdapter extends RecyclerView.Adapter {

    private Context mContext;
    private List<God> mGodList;

    public EditAdapter(Context context) {
        this(context, null);
    }

    public EditAdapter(Context context, List<God> godList) {
        mContext = context;
        mGodList = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_edit, parent, false);
        return new EditViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return mGodList == null ? 0 : mGodList.size();
    }

    public void setGodList(List<God> godList) {
        this.mGodList.clear();
        this.mGodList.addAll(godList);
    }

    private class EditFilter extends Filter {

        private EditAdapter adapter;
        private List<God> godList;
        private List<God> filterGodList;
        private EditFilter(EditAdapter adapter, List<God> godList) {
            super();
            this.adapter = adapter;
            this.godList = new LinkedList<>(godList);
            filterGodList = new ArrayList<>();
        }

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            filterGodList.clear();
            if (constraint.length() == 0) {
                filterGodList.addAll(godList);
            } else {
                for (God god : godList) {
                    if (god.getGodType().contains(constraint)) {
                        filterGodList.add(god);
                    }
                }
            }
            new FilterResults().values = filterGodList;
            return null;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            adapter.setGodList((ArrayList<God>) results.values);
            adapter.notifyDataSetChanged();
        }
    }
}
