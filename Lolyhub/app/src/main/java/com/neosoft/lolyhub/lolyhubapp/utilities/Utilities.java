package com.neosoft.lolyhub.lolyhubapp.utilities;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.ProductScreenAdapter;

/**
 * Created by neosoft on 27/4/17.
 */

public class Utilities  {
    Context context;
    public Utilities(Context context){
        this.context=context;

    }
    public void setListMode(Activity activity, ProductScreenAdapter productScreenAdapter, RecyclerView mProductListview, int var, boolean flag){
        mProductListview.setLayoutManager(new GridLayoutManager(activity,var));
        mProductListview.setAdapter(productScreenAdapter);
    }

}
