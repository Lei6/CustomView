package com.demo.customview.adapter;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

/**
 * created by 姚明亮
 * Time：2019/8/5 09:45
 */
public class ViewHolder extends RecyclerView.ViewHolder implements ItemTouchHelperViewHolder{
    //用于 缓存已找的界面
    private SparseArray<View> mView = null;
    private ItemTouchHelperViewHolder itemTouchListener;

    public ViewHolder(View itemView) {
        super(itemView);
        mView = new SparseArray<>();
    }

    public View getView(int viewId){
        //对已有的view做缓存
        View view = mView.get(viewId);
        //使用缓存的方式减少findViewByID的次数
        if (view==null){
            view=itemView.findViewById(viewId);
            mView.put(viewId,view);
        }
        return view;
    }

    public View getViewGroup(int viewId){
        //对已有的view做缓存
        View view = mView.get(viewId);
        //使用缓存的方式减少findViewByID的次数
        if (view==null){
            view=itemView.findViewById(viewId);
            mView.put(viewId,view);
        }
        return view;
    }

    /**
     * 设置文本控件Text值
     */
    public ViewHolder setText(int viewId, String text) {
        TextView textView = (TextView) getView(viewId);
        textView.setText(text);
        return this;
    }

    /**
     * 设置Button是否可用
     */
    public ViewHolder setEnable(int viewId, boolean enable) {
        Button btn = (Button) getView(viewId);
        btn.setEnabled(enable);
        return this;
    }

    /**
     * 设置Checkbox是否选中
     */
    public ViewHolder setChecked(int viewId, boolean checked) {
        CheckBox checkBox = (CheckBox) getView(viewId);
        checkBox.setChecked(checked);
        return this;
    }

    /**
     * 设置控件显示隐藏
     */
    public ViewHolder setViewVisibility(int viewId, int visibility) {
        View view = getView(viewId);
        view.setVisibility(visibility);
        return this;
    }

    public ViewHolder setViewOnClickListener(int viewId, View.OnClickListener listener) {
        View view = getView(viewId);
        view.setOnClickListener(listener);
        return this;
    }

    public ViewHolder setViewTag(int viewId, Object tag) {
        View view = getView(viewId);
        view.setTag(tag);
        return this;
    }

    public Object getViewTag(int viewId) {
        return getView(viewId).getTag();
    }

    /**
     * 设置ImageView res图片
     */
    public ViewHolder setImageResource(int viewId, int resId) {
        ImageView imageView = (ImageView) getView(viewId);
        imageView.setImageResource(resId);
        return this;
    }

    /**
     * 设置ImageView bitmap
     */
    public ViewHolder setImageBitmap(int viewId, Bitmap bitmap) {
        ImageView imageView = (ImageView) getView(viewId);
        imageView.setImageBitmap(bitmap);
        return this;
    }

    /**
     * 设置View Background
     */
    public ViewHolder setBackground(int viewId, int res) {
        View view = getView(viewId);
        view.setBackgroundResource(res);
        return this;
    }

    /**
     * 设置TextView的字体颜色
     */
    public ViewHolder setTextColor(int viewId, int res) {
        TextView textView = (TextView) getView(viewId);
        textView.setTextColor(res);
        return this;
    }

    /**
     * 3-1:Just for ItemTouch (optional)
     */
    public void setOnItemTouchListener(ItemTouchHelperViewHolder listener) {
        this.itemTouchListener = listener;
    }

    /**
     * 3-2:Just for ItemTouch
     */
    @Override
    public void onItemSelected() {
        if (itemTouchListener != null) {
            itemTouchListener.onItemSelected();
        }
    }

    /**
     * 3-3:Just for ItemTouch
     */
    @Override
    public void onItemClear() {
        if (itemTouchListener != null) {
            itemTouchListener.onItemClear();
        }
    }


}
