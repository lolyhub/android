package com.neosoft.lolyhub.lolyhubapp.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CardAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.HorizontalProductListingAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.ProductListingAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.SlidingImage_Adapter;
import com.neosoft.lolyhub.lolyhubapp.rest.model.HorizontalViewModel;

import java.util.ArrayList;

/**
 * Created by neosoft on 13/2/17.
 */

public class HomeFragment extends Fragment {
    private View view;
    private RecyclerView mRecyclerView_HoriZontal;

    private ProgressBar mProgressBar;
    private RecyclerView mRecyclerView_ProductListing;
    private HorizontalProductListingAdapter mHorizontalAdapter;
    private ProductListingAdapter mProductListingAdapter;
    private ViewPager mOffersIamgesViewpager;
    private TabLayout mOffersTablayout;
    private View mView;
    private HorizontalViewModel mHorizontalViewModel;
    private ArrayList<Integer> mImagesArray = new ArrayList<Integer>();
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mView=inflater.inflate(R.layout.fragment_home,container,false);
        initViews(mView);
        initAdapter();
        return mView;

    }
     private void initViews(View view){

         mOffersTablayout= (TabLayout) view.findViewById(R.id.tabDots);
         mOffersIamgesViewpager = (ViewPager)view.findViewById(R.id.offersViewpager_Id);

         for(int i=0;i< CommonConstant.IMAGES.length;i++)
             mImagesArray.add(CommonConstant.IMAGES[i]);
         mOffersIamgesViewpager.setAdapter(new SlidingImage_Adapter(getActivity(),mImagesArray));
         mOffersTablayout.setupWithViewPager(mOffersIamgesViewpager, true);

         mRecyclerView_HoriZontal= (RecyclerView)view.findViewById(R.id.horizontalRecyclerview);
         mRecyclerView_ProductListing= (RecyclerView) view.findViewById(R.id.productRecyclerview);
   }
   private void initAdapter(){
       LinearLayoutManager layoutManager
               = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);

       mRecyclerView_HoriZontal.setLayoutManager(layoutManager);
       mRecyclerView_ProductListing.setLayoutManager(new LinearLayoutManager(getActivity()));
       mProductListingAdapter=new ProductListingAdapter(getActivity());
       mHorizontalAdapter=new HorizontalProductListingAdapter(getActivity());
       mRecyclerView_HoriZontal.setAdapter(mHorizontalAdapter);
       mRecyclerView_ProductListing.setAdapter(mProductListingAdapter);

       for (int i=0;i<5;i++){
           HorizontalViewModel mHorizontalViewModel=new HorizontalViewModel();
           mHorizontalViewModel.setTitle("foods");
           mHorizontalViewModel.setImgIds(R.drawable.logo);
           mHorizontalAdapter.addData(mHorizontalViewModel);

       }
   }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }
}

