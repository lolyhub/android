package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.ProductScreenAdapter;

/**
 * Created by neosoft on 24/4/17.
 */

public class ProductActivity extends AppCompatActivity {
    private ProductScreenAdapter productScreenAdapter;
    private RecyclerView mProductListview;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        initViews();
        initProductAdapter();

    }
    private void initViews(){
        mProductListview= (RecyclerView) findViewById(R.id.productdetail_listview);
    }
    private void initProductAdapter(){

        productScreenAdapter=new ProductScreenAdapter(this,false);
        mProductListview.setLayoutManager(new GridLayoutManager(this,2));
        mProductListview.setAdapter(productScreenAdapter);

    }
}
