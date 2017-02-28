package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CardAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.ComplexRecyclerViewAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.NavigationListingAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.Pager;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.NetworkReceiver;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Countries;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Result;
import com.neosoft.lolyhub.lolyhubapp.rest.model.User;
import com.neosoft.lolyhub.lolyhubapp.utilities.CommonUtils;

import java.util.ArrayList;
import java.util.zip.Inflater;

/**
 * Created by neosoft on 29/12/16.
 */

public class HomeActivity extends AppCompatActivity implements NetworkReceiver, NavigationView.OnNavigationItemSelectedListener{
    public static String TAG="HomeActivity";
    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;
    private ProgressBar mProgressBar;
    public static View.OnClickListener myOnClickListener;
    private TabLayout mTabLayout;
    private ViewPager viewPager;
    private FloatingActionButton floatingActionButton;
    private View searchView,walletView,wishlistView,cartView,homeView;
    private RecyclerView mNavigationRecyclerView;
    private NavigationListingAdapter mNavigationAdapterListing;
    private  DrawerLayout drawer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
*/
        myOnClickListener=new MyOnClickListener(this);

        viewPager = (ViewPager) findViewById(R.id.pager);

        initViews();
        setUpTabLayout();
        addListners();
        //Creating our pager adapter
        Pager adapter = new Pager(getSupportFragmentManager(), mTabLayout.getTabCount());

        viewPager.setAdapter(adapter);

        // NetworkCall networkCall=new NetworkCall(this,this);
        //  networkCall.fetchWSCall();

        CommonUtils.initDrawer(this);
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer= (DrawerLayout) findViewById(R.id.drawer_layout);


    }

 public void addListners(){
     floatingActionButton.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {
             setDialog();
         }
     });
     mNavigationRecyclerView.setLayoutManager(new LinearLayoutManager(this));
     mNavigationAdapterListing=new NavigationListingAdapter(this);
    // mNavigationRecyclerView.setAdapter(mNavigationAdapterListing);
     ArrayList<Object> items = new ArrayList<>();
     items.add(new User("Dany Targaryen", "Valyria"));
     items.add(new User("Rob Stark", "Winterfell"));
     items.add("image");
     items.add(new User("Jon Snow", "Castle Black"));
     items.add("image");
     items.add(new User("Tyrion Lanister", "King's Landing"));

     mNavigationRecyclerView.setAdapter(new ComplexRecyclerViewAdapter(items));


 }
    private void setUpTabLayout(){

        mTabLayout = (TabLayout) findViewById(R.id.tabLayout);

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

                drawer.openDrawer(GravityCompat.START);
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
        mNavigationRecyclerView= (RecyclerView) findViewById(R.id.navigation_item_list);
        floatingActionButton= (FloatingActionButton) findViewById(R.id.fab);
        homeView= getLayoutInflater().inflate(R.layout.tab_home,null);
        searchView= getLayoutInflater().inflate(R.layout.tab_customeview,null);
        walletView = getLayoutInflater().inflate(R.layout.tab_wallet,null);
        wishlistView = getLayoutInflater().inflate(R.layout.tab_wishlist,null);
        cartView = getLayoutInflater().inflate(R.layout.tab_cart,null);
    }

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

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage){
        }


        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public <T> void onResponse(T obj, int tag) {
        Countries countries;
        countries= (Countries) obj;
        switch (tag){
            case CommonConstant.TAG_COUNTRY:

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
}
