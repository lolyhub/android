package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
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
    @BindView(R.id.toolbar)Toolbar mToolBar;
    @BindView(R.id.action_backbutton)ImageView mBackButton;
    @BindView(R.id.action_shareid)ImageView mSharview;
    @BindView(R.id.action_titletxtid)TextView mToolbarTitle;
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
        mPager = (ViewPager) findViewById(R.id.productdetail_viewpager_id);
        indicator = (CirclePageIndicator)findViewById(R.id.indicator);
        mSharview.setVisibility(View.VISIBLE);
    }
    private void initValues() {
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
    @OnClick(R.id.action_shareid)void shareData(){
        Toast.makeText(ProductDetailActivity.this, "want to share data?", Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.product_menu,menu);
        return true;
    }
}
