package com.vndevpro.android52_day6;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity implements IUpdateAdapterListener {

    private static final String TAG = "MainActivity";
    private ListView lvDemo;
    private ArrayList<String> mListData;
    private ArrayList<ProductModel> mListDataProduct;
    private ProductAdapter mProductAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initData();
        initView();
    }

    private void initView() {
        lvDemo = findViewById(R.id.lvDemo);

//        ArrayAdapter arrayAdapter = new ArrayAdapter(this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, mListData);
//        lvDemo.setAdapter(arrayAdapter);

        mProductAdapter = new ProductAdapter(mListDataProduct);
        mProductAdapter.setCallback(this);
        lvDemo.setAdapter(mProductAdapter);

//        lvDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                Log.d(TAG, "onItemClick: "+mListDataProduct.get(position).toString());
//                TextView tvProductName = view.findViewById(R.id.tvProductName);
//                Log.d(TAG, "onItemClick: "+tvProductName.getText().toString());
//            }
//        });

        lvDemo.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                return false;
            }
        });

        findViewById(R.id.btnAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewProduct();
            }
        });
    }

    private void addNewProduct() {
        ProductModel productModel = new ProductModel();
        productModel.setProductName("Product " + (mListDataProduct.size()));
        productModel.setProductImage("https://play-lh.googleusercontent.com/j9zl-GpzBaNY_nAE4XJ5LquJihqK3FqrhwEKNwwdFsp7RcIz0b-CNFGL5OEk_hiSPKnr");
        productModel.setProductPrices("$" + (((mListDataProduct.size() - 1) + 1) + 1 * 1000));
        productModel.setRate(new Random().nextInt(5) + "");
        productModel.setWish(false);
        mListDataProduct.add(productModel);
        mProductAdapter.notifyDataSetChanged();
    }

    private void initData() {
        mListData = new ArrayList<>();
        mListDataProduct = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductModel productModel = new ProductModel();
            productModel.setProductName("Product " + i);
            productModel.setProductImage("https://play-lh.googleusercontent.com/j9zl-GpzBaNY_nAE4XJ5LquJihqK3FqrhwEKNwwdFsp7RcIz0b-CNFGL5OEk_hiSPKnr");
            productModel.setProductPrices("$" + (i + 1 * 1000));
            productModel.setRate(new Random().nextInt(5) + "");
            productModel.setWish(false);
            mListData.add(productModel.getProductName());
            mListDataProduct.add(productModel);
        }
    }

    @Override
    public void onChangeWishList(int position) {
        Log.d(TAG, "onChangeWishList: " + position);
        ProductModel productModel = mListDataProduct.get(position);
        productModel.setWish(!productModel.isWish());

        mListDataProduct.set(position, productModel);
        mProductAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDelete(int position) {
        mListDataProduct.remove(position);
        mProductAdapter.notifyDataSetChanged();
    }
}