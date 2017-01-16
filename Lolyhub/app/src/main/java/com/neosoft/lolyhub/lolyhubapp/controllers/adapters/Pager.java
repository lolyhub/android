package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.neosoft.lolyhub.lolyhubapp.view.fragments.CartFragment;
import com.neosoft.lolyhub.lolyhubapp.view.fragments.SearchFragment;
import com.neosoft.lolyhub.lolyhubapp.view.fragments.WalletFragment;
import com.neosoft.lolyhub.lolyhubapp.view.fragments.WishListFragment;

public class Pager extends FragmentStatePagerAdapter {
    
    //integer to count number of tabs
    int tabCount;
 
    //Constructor to the class 
    public Pager(FragmentManager fm, int tabCount) {
        super(fm);
        //Initializing tab count
        this.tabCount= tabCount;
    }
 
    //Overriding method getItem
    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs 
        switch (position) {
            case 0:
                CartFragment tab1 = new CartFragment();
                return tab1;
            case 1:
                WalletFragment tab2 = new WalletFragment();
                return tab2;
            case 2:
                SearchFragment tab3 = new SearchFragment();
                return tab3;
            case 3:
                WishListFragment tab4 = new WishListFragment();
                return tab4;

            default:
                return null;
        }
    }
 
    //Overriden method getCount to get the number of tabs 
    @Override
    public int getCount() {
        return tabCount;
    }
}