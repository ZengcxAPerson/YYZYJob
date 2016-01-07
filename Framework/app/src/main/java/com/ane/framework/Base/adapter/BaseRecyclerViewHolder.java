package com.ane.framework.Base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zengcanxiang on 2016/1/6.
 */
public class BaseRecyclerViewHolder extends RecyclerView.ViewHolder {


    private SparseArray<View> mViews = new SparseArray<View>();
    private View mConvertView;

    public BaseRecyclerViewHolder(View itemView) {
        super(itemView);
        mConvertView = itemView;
        mConvertView.setTag(this);
    }


    public static BaseRecyclerViewHolder get(Context context,
                                             View convertView, ViewGroup parent, int layoutId) {
        if (convertView == null) {
            View mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                    false);
            return new BaseRecyclerViewHolder(mConvertView);
        } else {
            BaseRecyclerViewHolder bHolder = (BaseRecyclerViewHolder) convertView.getTag();
            return bHolder;
        }
    }

    @SuppressWarnings("unchecked")
    public <R extends View> R getView(int viewId) {
        View view = mViews.get(viewId);
        if (view == null) {
            view = mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (R) view;
    }

    public View getConvertView() {
        return mConvertView;
    }
}
