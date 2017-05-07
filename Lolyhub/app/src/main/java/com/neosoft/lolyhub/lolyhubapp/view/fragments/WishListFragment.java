package com.neosoft.lolyhub.lolyhubapp.view.fragments;

import android.content.Context;
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
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CardAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.WishlistCustomAdapter;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.CartResponse;
import com.neosoft.lolyhub.lolyhubapp.utilities.GetterSetter;
import com.neosoft.lolyhub.lolyhubapp.view.activities.WishlistActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;


/**
 * Created by neosoft on 16/1/17.
 */

public class WishListFragment extends Fragment{
    private View mView;
    TextView mAlphaTextView;
    private RecyclerView mWishlistReclerview;
    private WishlistCustomAdapter mWishlistCustomAdapter;
    public static View.OnClickListener myOnClickListener;
    private CardAdapter cardAdapter;
    private ArrayList<GetterSetter> feedItemList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_wishlist,container,false);
        myOnClickListener=new MyOnClickListener(getActivity());
        initViews(mView);
        initAdapter(mView);
        return mView;

    }
    private void initViews(View view){
        feedItemList = new ArrayList<GetterSetter>();
        for (int i = 0; i < 20; i++) {
            GetterSetter getterSetter = new GetterSetter();
            feedItemList.add(getterSetter);
        }

    }
    private void initAdapter(View view){
        mWishlistCustomAdapter=new WishlistCustomAdapter(getActivity());
        cardAdapter=new CardAdapter(getActivity(),1);
        mWishlistReclerview= (RecyclerView)view.findViewById(R.id.wishlist_recyclerview);
        mWishlistReclerview.setLayoutManager(new LinearLayoutManager(getActivity()));
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
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void onGetWishlistReposne(CartResponse response){
        if (response.getRequestType().equalsIgnoreCase(CommonConstant.ADDTOWISHLIST)){
            //add to wishlist function
        }else if (response.getRequestType().equalsIgnoreCase(CommonConstant.DELETEFROMWISHLIST)){
            //delete from wishlist function
        }else if(response.getRequestType().equalsIgnoreCase(CommonConstant.GETITEMWISHLIST)){
            //get item from wishlist function
        }
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
