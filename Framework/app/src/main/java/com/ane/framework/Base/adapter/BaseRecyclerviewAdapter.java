package com.ane.framework.Base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by zengcanxiang on 2016/1/6.
 */
public abstract class BaseRecyclerviewAdapter<T> extends RecyclerView.Adapter<BaseRecyclerViewHolder> {
    protected List<T> mList;
    protected Context mContext;
    protected LayoutInflater mLInflater;
    private int[] layoutId;
    private BaseRecyclerViewHolder holder = null;

    /**
     * @param data     数据源
     * @param context  上下文
     * @param layoutId 布局Id
     */
    public BaseRecyclerviewAdapter(List<T> data, Context context, int... layoutId) {
        this.mList = data;
        this.layoutId = layoutId;
        this.mContext = context;
        this.mLInflater = LayoutInflater.from(mContext);
    }

    @Override
    public BaseRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //TODO 要测试这个方法会调用几次,如果只会调用一次，else的代码可以删除
        if (holder == null) {
            holder = BaseRecyclerViewHolder.get(mContext, null, parent, layoutId[checkLayout()]);
        } else {
            holder = BaseRecyclerViewHolder.get(mContext,
                                            holder.getConvertView(), parent, layoutId[checkLayout()]);
        }
        return holder;
    }

    @Override
    public void onBindViewHolder(BaseRecyclerViewHolder holder, int position) {
        final T item = getItem(position);
        // 绑定数据
        onBindData(holder, position, item);
        //赋值相关事件,例如点击长按等
        setListener(holder, position, item);
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 追加单个数据
     *
     * @param item
     */
    public void addItem(T item) {
        mList.add(item);
        notifyDataSetChanged();
    }

    /**
     * 追加数据集
     *
     * @param items
     */
    public void addItems(List<T> items) {
        mList.addAll(items);
        notifyDataSetChanged();
    }

    /**
     * 添加单个数据到列表头部
     *
     * @param item
     */
    public void addItemToHead(T item) {
        mList.add(0, item);
        notifyDataSetChanged();
    }

    /**
     * 添加数据集到列表头部
     *
     * @param items
     */
    public void addItemsToHead(List<T> items) {
        mList.addAll(0, items);
        notifyDataSetChanged();
    }

    /**
     * 移除第position条数据
     *
     * @param position
     */
    public void remove(int position) {
        mList.remove(position);
        notifyDataSetChanged();
    }

    /**
     * 移除某个数据项
     *
     * @param item
     */
    public void remove(T item) {
        mList.remove(item);
        notifyDataSetChanged();
    }

    /**
     * 移除所有数据
     */
    public void removeAll() {
        mList.clear();
        notifyDataSetChanged();
    }

    /**
     * 获取指定位置的数据项
     *
     * @param position
     * @return
     */
    public T getItem(int position) {
        return mList.get(position);
    }

    /**
     * 根据业务逻辑确定layoutId位置,使用在listview中有几种样式
     *
     * @return 默认使用第一个, 返回下标, 从0开始
     */
    public int checkLayout() {
        return 0;
    }

    /**
     * 绑定数据到Item View上
     *
     * @param viewHolder
     * @param position   数据的位置
     * @param item       数据项
     */
    protected abstract void onBindData(BaseRecyclerViewHolder viewHolder, int position, T item);

    /**
     * 设置事件,默认空实现
     *
     * @param viewHolder
     * @param position   数据的位置
     * @param item       数据项
     */
    protected void setListener(BaseRecyclerViewHolder viewHolder, int position, T item) {

    }

}