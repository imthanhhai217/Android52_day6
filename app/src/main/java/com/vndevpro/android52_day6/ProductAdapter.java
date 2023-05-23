package com.vndevpro.android52_day6;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class ProductAdapter extends BaseAdapter {

    private ArrayList<ProductModel> mListData;
    private IUpdateAdapterListener callback;

    public ProductAdapter(ArrayList<ProductModel> listData) {
        this.mListData = listData;
    }

    public void setCallback(IUpdateAdapterListener callback) {
        this.callback = callback;
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public Object getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return mListData.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_product_item, null);
        }
        ProductModel productModel = (ProductModel) getItem(position);
        TextView tvProductName = convertView.findViewById(R.id.tvProductName);
        TextView tvPrices = convertView.findViewById(R.id.tvPrices);
        TextView tvRating = convertView.findViewById(R.id.tvRating);
        ImageView imgProduct = convertView.findViewById(R.id.imgProduct);
        ImageView imgWishList = convertView.findViewById(R.id.imgWishlist);
        ImageView imgRemove = convertView.findViewById(R.id.imgRemove);

        tvProductName.setText(productModel.getProductName());
        tvPrices.setText(productModel.getProductPrices());
        tvRating.setText(productModel.getRate());
        Glide.with(parent.getContext())
                .load(productModel.getProductImage())
                .placeholder(R.drawable.ic_launcher_background)
                .into(imgProduct);

        if (productModel.isWish()) {
            Glide.with(parent.getContext()).load(R.drawable.in_wishlist).into(imgWishList);
        } else {
            Glide.with(parent.getContext()).load(R.drawable.wish).into(imgWishList);
        }

        imgWishList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onChangeWishList(position);
            }
        });
        imgRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                callback.onDelete(position);
            }
        });

        return convertView;
    }
}
