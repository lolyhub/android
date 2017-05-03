package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.ProductScreenAdapter;
import com.neosoft.lolyhub.lolyhubapp.utilities.GridSpacingItemDecoration;
import com.neosoft.lolyhub.lolyhubapp.utilities.Utilities;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.http.Query;

/**
 * Created by neosoft on 24/4/17.
 */

public class ProductActivity extends AppCompatActivity {
    private ProductScreenAdapter productScreenAdapter;
    private ProductScreenAdapter productScreenAdapterVertical;
    private RecyclerView mProductListview;
    private SearchView searchViewMenu;
    private MenuItem searchMenuItem;
    private  SearchView.OnQueryTextListener listener;
    private boolean isSwitchMode=false;
    @BindView(R.id.toolbar)Toolbar mToolBar;
    @BindView(R.id.action_backbutton)ImageView mBackButton;
    @BindView(R.id.action_searchid)ImageView mSearchView;
    @BindView(R.id.action_titletxtid)TextView mToolbarTitle;
    @BindView(R.id.switchbutton_id)ImageView mListSwitchTab;
    @BindView(R.id.filter_id)ImageView mFilterTab;
    private Utilities utilities;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);
        ButterKnife.bind(this);
        initViews();
        initProductAdapter();
    }
    private void initViews(){
        mProductListview= (RecyclerView) findViewById(R.id.productdetail_listview);
        //mSearchView.setVisibility(View.VISIBLE);
        setSupportActionBar(mToolBar);
        utilities=new Utilities(ProductActivity.this);

    }
    private void initProductAdapter(){
        productScreenAdapter=new ProductScreenAdapter(this,false);
        mProductListview.setLayoutManager(new GridLayoutManager(this,2));
        mProductListview.setAdapter(productScreenAdapter);
        mProductListview.addItemDecoration(new GridSpacingItemDecoration(1,1,true));

       listener= new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        };
    }
@OnClick(R.id.action_backbutton)void backArrowPress(){
    this.finish();
}
@OnClick(R.id.switchbutton_id)void switchList(){
    if (!isSwitchMode){
        isSwitchMode=true;
        productScreenAdapterVertical=new ProductScreenAdapter(ProductActivity.this,true);
        utilities.setListMode(ProductActivity.this,productScreenAdapterVertical,mProductListview,1,isSwitchMode);
    }else if (isSwitchMode){
        isSwitchMode=false;
        productScreenAdapter=new ProductScreenAdapter(ProductActivity.this,false);
      utilities.setListMode(ProductActivity.this,productScreenAdapter,mProductListview,2,isSwitchMode);
    }
   }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.search_menu, menu);
        searchMenuItem = menu.findItem(R.id.action_search);
        searchViewMenu = (SearchView) searchMenuItem.getActionView();
        searchViewMenu.setOnQueryTextListener(listener);
        ImageView searchClose = (ImageView) searchViewMenu.findViewById(android.support.v7.appcompat.R.id.search_close_btn);
        searchClose.setImageResource(R.drawable.close);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id=item.getItemId();
        return super.onOptionsItemSelected(item);
    }
}


