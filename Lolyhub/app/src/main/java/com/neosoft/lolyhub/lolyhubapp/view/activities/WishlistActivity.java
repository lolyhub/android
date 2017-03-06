package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CardAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.WishlistCustomAdapter;
import com.neosoft.lolyhub.lolyhubapp.utilities.AlphaTextView;
import com.neosoft.lolyhub.lolyhubapp.utilities.GetterSetter;

import java.util.ArrayList;

/**
 * Created by rajendra on 1/3/17.
 */

public class WishlistActivity extends AppCompatActivity {
    TextView mAlphaTextView;
    private RecyclerView mWishlistReclerview;
    private WishlistCustomAdapter mWishlistCustomAdapter;
    public static View.OnClickListener myOnClickListener;
    private CardAdapter cardAdapter;


    private ArrayList<GetterSetter> feedItemList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        myOnClickListener=new MyOnClickListener(this);
        initViews();
        initAdapter();
    }
    private void initViews(){
        AlphaTextView alphaTextView=new AlphaTextView(this);
        mAlphaTextView= (TextView)findViewById(R.id.rightText);
        mAlphaTextView.getBackground().setAlpha(255);

        feedItemList = new ArrayList<GetterSetter>();
        for (int i = 0; i < 20; i++) {
            GetterSetter getterSetter = new GetterSetter();
            feedItemList.add(getterSetter);
        }


    }
    private void initAdapter(){
        mWishlistCustomAdapter=new WishlistCustomAdapter(this);
        cardAdapter=new CardAdapter(this,1);
        mWishlistReclerview= (RecyclerView) findViewById(R.id.wishlist_recyclerview);
        mWishlistReclerview.setLayoutManager(new LinearLayoutManager(this));
        mWishlistReclerview.setAdapter(mWishlistCustomAdapter);

    }
    public class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }
        @Override
        public void onClick(View v) {
            //removeItem(v);
            Toast.makeText(context, "clicked"+mWishlistCustomAdapter.getItemCount(), Toast.LENGTH_SHORT).show();
        }
       /* private void removeItem(View v) {
            int selectedItemPosition = mRecyclerView.getChildPosition(v);
            mCardAdapter.removeData(selectedItemPosition);
        }*/
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }
}
