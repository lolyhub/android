package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.OrderPageCustomAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.ProductScreenAdapter;
import com.neosoft.lolyhub.lolyhubapp.utilities.GridSpacingItemDecoration;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by rajendra on 7/5/17.
 */

public class OrderHistoryActivity extends AppCompatActivity {
    private OrderPageCustomAdapter orderPageCustomAdapter;
    @BindView(R.id.id_programHistory)RelativeLayout mPrgramHistoryTab;
    @BindView(R.id.id_orderHistory)RelativeLayout mOrderHistoryTab;
    @BindView(R.id.order_listView)RecyclerView mOrderPageList;
    //toolbar
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderhistory);
        ButterKnife.bind(this);
        initValues();
        initOrderAdapter();

    }
    private void initValues(){
        Toolbar toolbar= (Toolbar) findViewById(R.id.order_toolbar);
        setSupportActionBar(toolbar);
        toolbar.setTitle("Title");
        toolbar.setTitleTextColor(ContextCompat.getColor(this,android.R.color.black));


        // add back arrow to toolbar
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
    }
    public void initOrderAdapter(){
        mPrgramHistoryTab.setBackgroundColor(ContextCompat.getColor(this,R.color.darkGrey));
        mOrderHistoryTab.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
        orderPageCustomAdapter=new OrderPageCustomAdapter(this);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mOrderPageList.setLayoutManager(linearLayoutManager);
        mOrderPageList.setAdapter(orderPageCustomAdapter);
        mOrderPageList.addItemDecoration(new GridSpacingItemDecoration(2,4,true));
    }


    @OnClick(R.id.id_programHistory)void showProgramHistory(){
        mPrgramHistoryTab.setBackgroundColor(ContextCompat.getColor(this,R.color.darkGrey));
        mOrderHistoryTab.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
    }
    @OnClick(R.id.id_orderHistory)void howOrderHistory(){
        mPrgramHistoryTab.setBackgroundColor(ContextCompat.getColor(this,R.color.grey));
        mOrderHistoryTab.setBackgroundColor(ContextCompat.getColor(this,R.color.darkGrey));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        if (item.getItemId()==android.R.id.home){
            OrderHistoryActivity.this.finish();
        }
        return super.onOptionsItemSelected(item);
    }

}
