package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CartCustomAdapter;
import com.neosoft.lolyhub.lolyhubapp.utilities.GetterSetter;

import java.util.ArrayList;

/**
 * Created by neosoft on 2/3/17.
 */

public class CartActivity extends AppCompatActivity {

    TextView mAlphaTextView;
    private RecyclerView mWishlistReclerview;
    private CartCustomAdapter mWishlistCustomAdapter;


    private ArrayList<GetterSetter> feedItemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cartview);
        initViews();
        initAdapter();
    }
    private void initViews(){
        //AlphaTextView alphaTextView=new AlphaTextView(this);
        mAlphaTextView= (TextView)findViewById(R.id.rightText);
        mAlphaTextView.getBackground().setAlpha(255);

        feedItemList = new ArrayList<GetterSetter>();
        for (int i = 0; i < 20; i++) {
            GetterSetter getterSetter = new GetterSetter();
            feedItemList.add(getterSetter);
        }


    }
    private void initAdapter(){
        mWishlistCustomAdapter=new CartCustomAdapter(this);
        mWishlistReclerview= (RecyclerView) findViewById(R.id.wishlist_recyclerview);
        mWishlistReclerview.setLayoutManager(new LinearLayoutManager(this));
        mWishlistReclerview.setAdapter(mWishlistCustomAdapter);

    }
}