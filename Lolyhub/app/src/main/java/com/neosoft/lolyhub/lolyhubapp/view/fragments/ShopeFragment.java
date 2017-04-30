package com.neosoft.lolyhub.lolyhubapp.view.fragments;

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
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.SearchViewExpandableAdapter;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.WalletExpandableListAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * Created by neosoft on 11/1/17.
 */

public class ShopeFragment extends Fragment {
    private View mView;
    private SearchViewExpandableAdapter mSearchViewAdapter;
    @BindView(R.id.searchExpandable)ExpandableListView expandleList;
    private ExpandableListView expandleListView;
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

        return mView;
    }
    private void initViews(View view){
        expandleListView= (ExpandableListView) view.findViewById(R.id.searchExpandable);
        mSearchViewAdapter=new SearchViewExpandableAdapter(getActivity());
        expandleList.setAdapter(mSearchViewAdapter);

    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_search).setVisible(true);
        super.onPrepareOptionsMenu(menu);
    }

}
