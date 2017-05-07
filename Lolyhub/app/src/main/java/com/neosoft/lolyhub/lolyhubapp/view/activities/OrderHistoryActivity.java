package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.OrderPageCustomAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.ProductScreenAdapter;
import com.neosoft.lolyhub.lolyhubapp.utilities.GridSpacingItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by rajendra on 7/5/17.
 */

public class OrderHistoryActivity extends AppCompatActivity {
    private OrderPageCustomAdapter orderPageCustomAdapter;

    @BindView(R.id.order_listView)RecyclerView mOrderPageList;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);
        ButterKnife.bind(this);
        initOrderAdapter();
    }
    public void initOrderAdapter(){
        orderPageCustomAdapter=new OrderPageCustomAdapter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mOrderPageList.setLayoutManager(linearLayoutManager);
        mOrderPageList.setAdapter(orderPageCustomAdapter);
        mOrderPageList.addItemDecoration(new GridSpacingItemDecoration(2,4,true));
    }
}
