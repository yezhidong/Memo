package com.android.yzd.memo.mvp.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.android.yzd.memo.R;

import java.util.List;

/**
 * Created by yezhidong on 2016/1/27.
 */
public class ColorListAdapter extends BaseAdapter {

    private final Context mContext;
    private final List<Integer> list;

    public ColorListAdapter(Context context, List<Integer> list){
        mContext = context;
        this.list = list;
    }
    private int checkItem;
    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Holder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.colors_image_layout, null);
            holder = new Holder();
            holder.imageView1 = (ImageView)convertView.findViewById(R.id.img_1);
            holder.imageView2 = (ImageView)convertView.findViewById(R.id.img_2);
            convertView.setTag(holder);
        }else{
            holder = (Holder)convertView.getTag();
        }
        holder.imageView1.setImageResource(list.get(position));
        if (checkItem == position){
            holder.imageView2.setImageResource(R.mipmap.ic_done_white);
        }
        return convertView;
    }

    public void setCheckItem(int checkItem) {
        this.checkItem = checkItem;
    }
    static class Holder {
        ImageView imageView1;
        ImageView imageView2;
    }
}
