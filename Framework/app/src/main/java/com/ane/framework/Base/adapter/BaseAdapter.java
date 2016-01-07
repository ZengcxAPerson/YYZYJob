package com.ane.framework.Base.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * <p>万能适配Adapter,减少赘于代码和加快开发流程</p>
 * @author zcx
 *
 */
public abstract class BaseAdapter<T> extends android.widget.BaseAdapter
 {

	protected List<T> mList;
	protected Context mContext;
	protected LayoutInflater mLInflater;
	private int[] layoutId;
	private BaseViewHolder holder = null;
	/**
	 *
	 * @param data 数据源
	 * @param context 上下文
	 * @param layoutId 布局Id
	 */
	public BaseAdapter(List<T> data,Context context,int... layoutId){
		this.mList=data;
		this.layoutId=layoutId;
		this.mContext=context;
		this.mLInflater=LayoutInflater.from(mContext);
	}
	/**
     * 在初始化的时候不能确定layoutId,才可以不提供,但是必须重写checkLayoutId()方法
	 * @param data 数据源
	 * @param context 上下文
	 */
	public BaseAdapter(List<T> data,Context context){
		this.mList=data;
		this.mContext=context;
		this.mLInflater=LayoutInflater.from(mContext);
	}
	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if(layoutId==null){
			holder= BaseViewHolder.get(mContext, position, convertView, parent, checkLayoutId());
		}else{
			holder= BaseViewHolder.get(mContext, position, convertView, parent, layoutId[checkLayout(position)]);
		}
		convert(holder,position, mList.get(position));
		return holder.getConvertView();
	}
	/**
	 * 实现具体控件的获取和赋值等业务
	 * @param viewHolder viewHolder
	 * @param position position
	 * @param t 数据源中,当前对应的bean
	 */
	public abstract void convert(BaseViewHolder viewHolder,int position,T t);

	/**
	 * 根据业务逻辑确定layoutId位置,使用在listview中有几种样式
	 * @param position
	 * @return 默认使用第一个,返回下标,从0开始
	 */
	public int checkLayout(int position){
		return 0;
	}
	/**
	 * 根据业务逻辑确定layoutId,只会在构造方法没有传入layoutId时生效
	 * @return 默认为0
	 */
	public int checkLayoutId(){
		return 0;
	}
}
