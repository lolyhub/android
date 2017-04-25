package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CardAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CartSwipeToDeleteAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.Pager;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.SwipeToDeleteAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.NetworkReceiver;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Countries;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Result;
import com.neosoft.lolyhub.lolyhubapp.utilities.CommonUtils;
import java.util.ArrayList;
import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by neosoft on 29/12/16.
 */
public class HomeActivity extends AppCompatActivity implements NetworkReceiver{
    public static String TAG="HomeActivity";
    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;
    private ProgressBar mProgressBar;
    public static View.OnClickListener myOnClickListener;
    @BindView(R.id.tabLayout)TabLayout mTabLayout;
    @BindView(R.id.pager)ViewPager viewPager;
    @BindView(R.id.fab)FloatingActionButton floatingActionButton;
    private View searchView,walletView,wishlistView,cartView,homeView;
    private ArrayList<Object> items = new ArrayList<>();
    private SwipeToDeleteAdapter mSwipeAdapter;
    private CartSwipeToDeleteAdapter mCartSwipeToDeleteAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        ButterKnife.bind(this);
        myOnClickListener=new MyOnClickListener(this);
        initViews();
        setUpTabLayout();
        addListners();
        Pager adapter = new Pager(getSupportFragmentManager(), mTabLayout.getTabCount());
        viewPager.setAdapter(adapter);

    }
 public void addListners(){
     floatingActionButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             setDialog();
         }
     });
 }
    private void setUpTabLayout(){
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(homeView));
        //Adding the tabs using addTab() method
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(searchView));
      //  mTabLayout.addTab(mTabLayout.newTab().setIcon(R.drawable.search));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(walletView));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(wishlistView));
        mTabLayout.addTab(mTabLayout.newTab().setCustomView(cartView));
        mTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }
            @Override
            public void onPageSelected(int position) {
               mTabLayout.getTabAt(position).select();
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
               viewPager.setCurrentItem(tab.getPosition());
                ArrayList<String> list=new ArrayList<String>();
                for (int i=0;i<2;i++){
                    list.add("objects");
                }
                if (tab.getPosition()==1){
                    startActivity(new Intent(HomeActivity.this, ProductActivity.class));
                }
                /*if (tab.getPosition()==3){
                    mSwipeAdapter=new SwipeToDeleteAdapter(HomeActivity.this,list);
                    mNavigationRecyclerView.setAdapter(mSwipeAdapter);
                    drawer.openDrawer(GravityCompat.START);
                    mBottomView.setText(R.string.move_to_cart);
                    setInVisibility();
                }else if(tab.getPosition()==4){
                    mCartSwipeToDeleteAdapter=new CartSwipeToDeleteAdapter(HomeActivity.this,list);
                    mNavigationRecyclerView.setAdapter(mCartSwipeToDeleteAdapter);
                    drawer.openDrawer(GravityCompat.START);
                    mBottomView.setText(R.string.checkout);
                    setVisibility();
                }*/
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    public void setDialog(){
        LayoutInflater layoutInflater= (LayoutInflater) getBaseContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View view=layoutInflater.inflate(R.layout.layout_custom_floatinbuttonview,null);

        CommonUtils.CustomDialog(view,HomeActivity.this);
    }
    private void initViews(){
        homeView= getLayoutInflater().inflate(R.layout.tab_home,null);
        searchView= getLayoutInflater().inflate(R.layout.tab_customeview,null);
        walletView = getLayoutInflater().inflate(R.layout.tab_wallet,null);
        wishlistView = getLayoutInflater().inflate(R.layout.tab_wishlist,null);
        cartView = getLayoutInflater().inflate(R.layout.tab_cart,null);
    }
    /*private void setVisibility(){
        mTotalLPContainer.setVisibility(View.VISIBLE);
        mEarningContainer.setVisibility(View.VISIBLE);
        mFirstView.setVisibility(View.VISIBLE);
        mSecondView.setVisibility(View.VISIBLE);

    }
    private void setInVisibility(){
        mTotalLPContainer.setVisibility(View.GONE);
        mEarningContainer.setVisibility(View.GONE);
        mFirstView.setVisibility(View.GONE);
        mSecondView.setVisibility(View.GONE);

    }*/
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public <T> void onResponse(T obj, int tag) {
        Countries countries;
        countries= (Countries) obj;
        switch (tag){
            case CommonConstant.TAG_LOGIN:

                for(final Result resultObj : countries.getRestResponse().getResult()) {
                    mCardAdapter.addData(resultObj);
                    mProgressBar.setVisibility(View.GONE);
                }
                break;
            default:
                break;
        }
    }
    @Override
    public void onError( String msg) {

        Toast.makeText(this, "Exception"+msg, Toast.LENGTH_SHORT).show();

    }

    public class MyOnClickListener implements View.OnClickListener {

        private final Context context;

        private MyOnClickListener(Context context) {
            this.context = context;
        }
        @Override
        public void onClick(View v) {
            //removeItem(v);
            Toast.makeText(context, "clicked"+mRecyclerView.getChildPosition(v), Toast.LENGTH_SHORT).show();
        }
        private void removeItem(View v) {
            int selectedItemPosition = mRecyclerView.getChildPosition(v);
            mCardAdapter.removeData(selectedItemPosition);
        }
    }

    @Override
    public void onBackPressed() {
            super.onBackPressed();

    }
}
