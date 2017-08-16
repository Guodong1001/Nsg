package com.bwie.dianshang.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.GoodsClassBean;

import java.util.List;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/18
 */
public class ClassThreeAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsClassBean> mList;

    public ClassThreeAdapter(Context context, List<GoodsClassBean> list) {
        this.context = context;
        mList = list;
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
        viewHolder holder;
        if(convertView==null){
            convertView = View.inflate(context, R.layout.item_class_three,null);
            holder = new viewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.class3_item_text);
            convertView.setTag(holder);
        }else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.mTextView.setText(mList.get(position).getGc_name());
        return convertView;
    }
    class viewHolder{
        TextView mTextView;
    }
}
