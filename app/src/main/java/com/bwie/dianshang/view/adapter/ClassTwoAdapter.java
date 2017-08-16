package com.bwie.dianshang.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.TextView;

import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.GoodsClassBean;
import com.bwie.dianshang.view.activity.GoodsContentActivity;

import java.util.List;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/18
 */
public class ClassTwoAdapter extends BaseAdapter {
    private Context context;
    private List<GoodsClassBean> mList;
    private List<GoodsClassBean> gridlist;
    private int positions;

    public ClassTwoAdapter(Context context, List<GoodsClassBean> list) {
        this.context = context;
        mList = list;
    }

    public void setData(List<GoodsClassBean> list, int position) {
        gridlist = list;
        positions = position;
        notifyDataSetChanged();
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
        if (convertView == null) {
            convertView = View.inflate(context, R.layout.item_class_two, null);
            holder = new viewHolder();
            holder.mTextView = (TextView) convertView.findViewById(R.id.class2_item_text);
            holder.mGridView = (GridView) convertView.findViewById(R.id.class2_item_gridview);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        holder.mTextView.setText(mList.get(position).getGc_name());

        if (gridlist != null && positions == position) {
            holder.mGridView.setVisibility(View.VISIBLE);
            holder.mGridView.setAdapter(new ClassThreeAdapter(context, gridlist));
            holder.mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, GoodsContentActivity.class);
                    String gc_id = gridlist.get(position).getGc_id();
                    if(gc_id.equals("587")){
                        intent.putExtra("gc_id",gc_id);
                    }
                    context.startActivity(intent);
                }
            });
        }else{
            holder.mGridView.setVisibility(View.GONE);
        }

        return convertView;
    }

    class viewHolder {
        TextView mTextView;
        GridView mGridView;
    }
}
