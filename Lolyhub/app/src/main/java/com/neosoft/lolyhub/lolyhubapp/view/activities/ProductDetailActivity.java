package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.SlidingImageAdapter;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by neosoft on 26/4/17.
 */

public class ProductDetailActivity extends AppCompatActivity {
    private static ViewPager mPager;
    private static int currentPage = 0;
    private static int NUM_PAGES = 0;
    private static final Integer[] IMAGES= {R.drawable.watches,R.drawable.foods,R.drawable.watches,R.drawable.cart_img};
    private ArrayList<Integer> ImagesArray = new ArrayList<Integer>();
    private CirclePageIndicator indicator;
    @BindView(R.id.product_detail_toolbar)Toolbar mToolBar;
    @BindView(R.id.action_backbutton)ImageView mBackButton;
    @BindView(R.id.action_titletxtid)TextView mToolbarTitll;
    @BindView(R.id.action_shareid)ImageView mSharview;

    @BindView(R.id.productdetail_titleview_itemscontainer)RelativeLayout mProductDetail_TitleViewContainer;
    @BindView(R.id.productdetail_descriptiontxt_id)TextView mDescriptiontext;
    @BindView(R.id.productdetail_termstxt_id)TextView mTermsTxt;
    @BindView(R.id.productdetail_location_container)RelativeLayout mLocationContaner;
    @BindView(R.id.productdetail_locationImg_id)ImageView mLocationImage;
    @BindView(R.id.productdetail_titleview_arrowup)ImageView mTitleUpArrow;
    @BindView(R.id.productdetail_titleview_arrowdown)ImageView mTitleDownArrow;
    @BindView(R.id.productdetail_description_arrowup)ImageView mDescriptionUpArrow;
    @BindView(R.id.productdetail_description_arrowdown)ImageView mDescriptionDownArrow;
    @BindView(R.id.productdetail_terms_arrowup)ImageView TermsUpArrow;
    @BindView(R.id.productdetail_terms_arrowdown)ImageView TermsDownArrow;
    @BindView(R.id.productdetail_location_arrowup)ImageView LocationUpArrow;
    @BindView(R.id.productdetail_location_arrowdown)ImageView LocationDownArrow;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_productdetail);
        ButterKnife.bind(this);
        initViews();
        initValues();
        initListeners();
    }
    private void initViews(){
        mToolbarTitll.setText(getResources().getString(R.string.productdetail_producttitle));
        mPager = (ViewPager) findViewById(R.id.productdetail_viewpager_id);
        indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mSharview.setVisibility(View.VISIBLE);
        mTitleUpArrow.setVisibility(View.VISIBLE);
        mProductDetail_TitleViewContainer.setVisibility(View.VISIBLE);

        mDescriptionUpArrow.setVisibility(View.VISIBLE);
        mDescriptiontext.setVisibility(View.VISIBLE);

        TermsUpArrow.setVisibility(View.VISIBLE);
        mTermsTxt.setVisibility(View.VISIBLE);

        LocationUpArrow.setVisibility(View.VISIBLE);
        mLocationImage.setVisibility(View.VISIBLE);
        mLocationContaner.setVisibility(View.VISIBLE);

    }
    private void initValues(){
        for(int i=0;i<IMAGES.length;i++)
            ImagesArray.add(IMAGES[i]);
        mPager.setAdapter(new SlidingImageAdapter(ProductDetailActivity.this,ImagesArray));
        indicator.setViewPager(mPager);
        final float density = getResources().getDisplayMetrics().density;
       //Set circle indicator radius
        indicator.setRadius(5 * density);
        NUM_PAGES =IMAGES.length;
        // Auto start of viewpager
        final Handler handler = new Handler();
        final Runnable Update = new Runnable() {
            public void run() {
                if (currentPage == NUM_PAGES) {
                    currentPage = 0;
                }
                mPager.setCurrentItem(currentPage++, true);
            }
        };
        Timer swipeTimer = new Timer();
        swipeTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(Update);
            }
        }, 3000, 3000);
    }
    private void initListeners(){
        // Pager listener over indicator
        indicator.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;

            }
            @Override
            public void onPageScrolled(int pos, float arg1, int arg2) {

            }
            @Override
            public void onPageScrollStateChanged(int pos) {

            }
        });

    }
    @OnClick(R.id.action_backbutton)void showBackButton(){
        ProductDetailActivity.this.finish();
    }
    @OnClick(R.id.action_shareid)void shareData(){
        Toast.makeText(ProductDetailActivity.this, "want to share data?", Toast.LENGTH_SHORT).show();
    }
    @OnClick(R.id.productdetail_titleview_arrowup) public void hideTitleView(){
        mProductDetail_TitleViewContainer.setVisibility(View.GONE);
        mTitleDownArrow.setVisibility(View.VISIBLE);
        mTitleUpArrow.setVisibility(View.GONE);
    }
    @OnClick(R.id.productdetail_titleview_arrowdown) public void showTitleView(){
        mProductDetail_TitleViewContainer.setVisibility(View.VISIBLE);
        mTitleDownArrow.setVisibility(View.GONE);
        mTitleUpArrow.setVisibility(View.VISIBLE);

    }
    @OnClick(R.id.productdetail_description_arrowup) public void hideDescriptionView(){
        mDescriptiontext.setVisibility(View.GONE);
        mDescriptionUpArrow.setVisibility(View.GONE);
        mDescriptionDownArrow.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.productdetail_description_arrowdown) public void showDescriptionView(){
        mDescriptiontext.setVisibility(View.VISIBLE);
        mDescriptionUpArrow.setVisibility(View.VISIBLE);
        mDescriptionDownArrow.setVisibility(View.GONE);
    }
    @OnClick(R.id.productdetail_terms_arrowup) public void hideTermsView(){
        mTermsTxt.setVisibility(View.GONE);
        TermsUpArrow.setVisibility(View.GONE);
        TermsDownArrow.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.productdetail_terms_arrowdown) public void showTermsView(){
        mTermsTxt.setVisibility(View.VISIBLE);
        TermsUpArrow.setVisibility(View.VISIBLE);
        TermsDownArrow.setVisibility(View.GONE);
    }
    @OnClick(R.id.productdetail_location_arrowup) public void hideLocationView(){
        mLocationImage.setVisibility(View.GONE);
        LocationUpArrow.setVisibility(View.GONE);
        LocationDownArrow.setVisibility(View.VISIBLE);
    }
    @OnClick(R.id.productdetail_location_arrowdown) public void showLocationView(){
        mLocationImage.setVisibility(View.VISIBLE);
        LocationUpArrow.setVisibility(View.VISIBLE);
        LocationDownArrow.setVisibility(View.GONE);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu,menu);
        return true;
    }
}
