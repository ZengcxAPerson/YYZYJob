package com.ane.framework.Base.adapter;

import android.content.Context;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * 万能适配Holder,减少赘于代码和加快开发流程
 * @author zcx
 *
 */
public class BaseViewHolder {

    private SparseArray<View> mViews = new SparseArray<View>();
    private SparseArray<View> mConvertViews = new SparseArray<View>();
    private View mConvertView;
    private int mPosition;

    public BaseViewHolder(Context context, int position, ViewGroup parent,
                          int layoutId) {
        mConvertView = LayoutInflater.from(context).inflate(layoutId, parent,
                false);
        mPosition = position;
        mConvertView.setTag(this);
    }

    public static BaseViewHolder get(Context context, int position,
                                     View convertView,ViewGroup parent, int layoutId) {
        if (convertView == null) {
            return new BaseViewHolder(context, position, parent, layoutId);
        } else {
            BaseViewHolder bHolder = (BaseViewHolder) convertView.getTag();
            bHolder.setPosition(position);
            return bHolder;
        }
    }

    @SuppressWarnings("unchecked")
    public <R extends View> R getView(int viewId){
        View view=mViews.get(viewId);
        if(view==null){
            view=mConvertView.findViewById(viewId);
            mViews.put(viewId, view);
        }
        return (R)view;
    }

    public void setPosition(int mPosition) {
        this.mPosition = mPosition;
    }
    public View getConvertView() {
        return mConvertView;
    }
}
