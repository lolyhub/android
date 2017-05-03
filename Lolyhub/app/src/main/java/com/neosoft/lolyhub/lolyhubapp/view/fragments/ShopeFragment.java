package com.neosoft.lolyhub.lolyhubapp.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.constants.CommonConstant;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.SearchViewExpandableAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.WalletExpandableListAdapter;
import com.neosoft.lolyhub.lolyhubapp.rest.model.responsepojo.CartResponse;
import com.neosoft.lolyhub.lolyhubapp.view.activities.ProductActivity;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by neosoft on 11/1/17.
 */

public class ShopeFragment extends Fragment {
    private View mView;
    private SearchViewExpandableAdapter mSearchViewAdapter;
    @BindView(R.id.searchExpandable)ExpandableListView expandleList;

    @BindView(R.id.searchIcon)ImageView searchViewIcon;
    @BindView(R.id.searchTxt)TextView searchTxt;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_shope, container, false);
        ButterKnife.bind(this,mView);
        initViews(mView);
        initListners();
        return mView;
    }
    private void initViews(View view){

        mSearchViewAdapter=new SearchViewExpandableAdapter(getActivity());
        expandleList.setAdapter(mSearchViewAdapter);

    }
    private void initListners(){
        expandleList.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView parent, View v, int groupPosition, int childPosition, long id) {
                Intent intent=new Intent(getActivity(), ProductActivity.class);
                startActivity(intent);
                return false;
            }
        });
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_search).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

}
