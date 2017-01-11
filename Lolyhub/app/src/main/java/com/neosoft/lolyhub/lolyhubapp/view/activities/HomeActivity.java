package com.neosoft.lolyhub.lolyhubapp.view.activities;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.neosoft.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CardAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.NetworkReceiver;
import com.neosoft.lolyhub.lolyhubapp.rest.NetworkCall;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Countries;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Result;

/**
 * Created by neosoft on 29/12/16.
 */

public class HomeActivity extends AppCompatActivity implements NetworkReceiver{
    public static String TAG="HomeActivity";
    private RecyclerView mRecyclerView;
    private CardAdapter mCardAdapter;
    private ProgressBar mProgressBar;
    public static View.OnClickListener myOnClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myOnClickListener=new MyOnClickListener(this);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        initViews();
        initAdapter();
        NetworkCall networkCall=new NetworkCall(this,this);
        networkCall.fetchWSCall();
    }
    private void initViews(){
        mRecyclerView= (RecyclerView) findViewById(R.id.recycler_view);
        mProgressBar= (ProgressBar) findViewById(R.id.progress_bar);
        mProgressBar.setVisibility(View.VISIBLE);

    }
    private void initAdapter(){
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mCardAdapter=new CardAdapter(this);
        mRecyclerView.setAdapter(mCardAdapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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
