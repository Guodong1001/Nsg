package com.bwie.dianshang.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bwie.dianshang.R;
import com.bwie.dianshang.model.bean.DetailsBean;
import com.bwie.dianshang.view.IView.OnItemClickListener;

import java.util.List;

/**
 * 类描述：
 * 创建人：guodongdong
 * 创建时间：2017/7/21
 */
public class GoodsCommendAdapter extends RecyclerView.Adapter{
    private List<DetailsBean.DatasBean.GoodsCommendListBean> mList;
    private Context context;
    private boolean isChecked = false;
    private MyViewHolder mMyViewHolder;
    private OnItemClickListener mClickListener;

    public GoodsCommendAdapter(List<DetailsBean.DatasBean.GoodsCommendListBean> list, Context context) {
        mList = list;
        this.context = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (isChecked) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_connect_grid, parent, false);
            return new MyViewHolder(view,mClickListener);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_goods_connect_list, parent, false);
            return new MyViewHolder(view,mClickListener);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        mMyViewHolder = (MyViewHolder) holder;
        Glide.with(context).load(mList.get(position).getGoods_image_url()).into(mMyViewHolder.goodsImg);
        mMyViewHolder.goodsName.setText(mList.get(position).getGoods_name());
        mMyViewHolder.goodsPrice.setText("￥" + mList.get(position).getGoods_promotion_price());
        mMyViewHolder.goodsSalenum.setVisibility(View.GONE);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mClickListener = listener;
    }

    public void setData(boolean isChecked) {
        mMyViewHolder = null;
        this.isChecked = isChecked;
    }

    class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView goodsImg;
        TextView goodsName;
        TextView goodsPrice;
        TextView goodsSalenum;
        private OnItemClickListener mListener;

        public MyViewHolder(View itemView,OnItemClickListener listener) {
            super(itemView);
            mListener = listener;
            itemView.setOnClickListener(this);
            goodsImg = (ImageView) itemView.findViewById(R.id.goods_img);
            goodsName = (TextView) itemView.findViewById(R.id.goods_name);
            goodsPrice = (TextView) itemView.findViewById(R.id.goods_price);
            goodsSalenum = (TextView) itemView.findViewById(R.id.goods_salenum);
        }

        @Override
        public void onClick(View v) {
            mListener.onItemClick(v,getLayoutPosition());
        }
    }
}
