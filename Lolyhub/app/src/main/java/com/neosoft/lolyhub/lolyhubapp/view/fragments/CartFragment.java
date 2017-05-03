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
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CartCustomAdapter;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.CartResponse;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.UpdateCartResponse;
import com.neosoft.lolyhub.lolyhubapp.utilities.GetterSetter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
//ajsdjsadjsdjsj

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
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onGetCartReposne(CartResponse response){
        if (response.getRequestType().equalsIgnoreCase(CommonConstant.ADDTOCART)){
            //add to cart function
        }else if (response.getRequestType().equalsIgnoreCase(CommonConstant.DELETEFROMCART)){
            //delete from cart function
        }else if(response.getRequestType().equalsIgnoreCase(CommonConstant.GETITEMCART)){
            //get item from cart function
        }
    }

    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onUpdateData(UpdateCartResponse response){

    }
    @Override
    public void onPause() {
        super.onPause();
        EventBus.getDefault().register(this);
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}

