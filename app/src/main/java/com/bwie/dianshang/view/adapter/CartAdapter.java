package com.bwie.dianshang.view.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.CartGoodsBean;
import com.bwie.dianshang.utils.MessageEvent;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/24
 */
public class CartAdapter extends BaseAdapter{
    private Context mContext;
    private int mCount;
    private List<CartGoodsBean> mList;
    private CartGoodsBean mBean;
    private int sum;

    public void setData(List<CartGoodsBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public CartAdapter(Context context) {
        mContext = context;
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
    public View getView(final int position, View convertView, ViewGroup parent) {
        viewHolder holder = null;
        if (convertView == null) {
            convertView = View.inflate(mContext, R.layout.cart_fragment_lv_item, null);
            holder = new viewHolder();
            holder.name = (TextView) convertView.findViewById(R.id.cart_item_product_name);
            holder.price = (TextView) convertView.findViewById(R.id.cart_item_product_price);
            holder.count = (TextView) convertView.findViewById(R.id.cart_item_product_count);
            holder.add = (TextView) convertView.findViewById(R.id.cart_item_btn_add);
            holder.sub = (TextView) convertView.findViewById(R.id.cart_item_btn_sub);
            holder.mCheckBox = (CheckBox) convertView.findViewById(R.id.cart_item_check);
            convertView.setTag(holder);
        } else {
            holder = (viewHolder) convertView.getTag();
        }
        mBean = mList.get(position);
        holder.name.setText(mBean.getName());
        holder.price.setText(mBean.getPrice()+"");
        holder.count.setText(mBean.getCount()+"");

        final viewHolder finalHolder = holder;
        holder.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount = mList.get(position).getCount();
                Toast.makeText(mContext, ""+mCount, Toast.LENGTH_SHORT).show();
                mCount++;
                mList.get(position).setCount(mCount);
                finalHolder.count.setText(mList.get(position).getCount()+"");
            }
        });
        holder.sub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCount = mList.get(position).getCount();
                Toast.makeText(mContext, ""+mCount, Toast.LENGTH_SHORT).show();
                if (mCount>0){
                    mCount--;
                    mList.get(position).setCount(mCount);
                    finalHolder.count.setText(mList.get(position).getCount()+"");
                }
            }
        });
        holder.mCheckBox.setChecked(mList.get(position).isChecked());
        holder.mCheckBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                sum=0;
                if (isChecked) {
                    mList.get(position).setChecked(true);
                    for (CartGoodsBean cartGoodsBean : mList) {
                        if (cartGoodsBean.isChecked()) {
                            sum++;
                        }
                    }
                    if (sum == mList.size()) {
                        EventBus.getDefault().post(new MessageEvent(true));
                    }
                }
            }
        });
        return convertView;
    }



    class viewHolder {
        TextView name,price,count,add,sub;
        CheckBox mCheckBox;
    }
}
