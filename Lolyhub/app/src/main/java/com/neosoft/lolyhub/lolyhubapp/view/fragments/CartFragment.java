package com.neosoft.lolyhub.lolyhubapp.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CartCustomAdapter;
import com.neosoft.lolyhub.lolyhubapp.utilities.GetterSetter;

import java.util.ArrayList;


/**
 * Created by neosoft on 11/1/17.
 */

public class CartFragment extends Fragment {
    private View view;
    private TextView mAlphaTextView;
    private RecyclerView mWishlistReclerview;
    private CartCustomAdapter mWishlistCustomAdapter;
    private View mView;


    private ArrayList<GetterSetter> feedItemList;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_cart,container,false);
        initViews(mView);
        initAdapter(mView);

        return mView;

    } private void initViews(View view){
        //AlphaTextView alphaTextView=new AlphaTextView(this);


        feedItemList = new ArrayList<GetterSetter>();
        for (int i = 0; i < 20; i++) {
            GetterSetter getterSetter = new GetterSetter();
            feedItemList.add(getterSetter);
        }


    }
    private void initAdapter(View view){
        mWishlistCustomAdapter=new CartCustomAdapter(getActivity());
        mWishlistReclerview= (RecyclerView)view.findViewById(R.id.wishlist_recyclerview);
        mWishlistReclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
        mWishlistReclerview.setAdapter(mWishlistCustomAdapter);

    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }
}

