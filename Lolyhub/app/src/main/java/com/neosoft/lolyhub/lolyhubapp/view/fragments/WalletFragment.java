package com.neosoft.lolyhub.lolyhubapp.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.adapters.CustomExpandableListAdapter;


/**
 * Created by neosoft on 12/1/17.
 */

public class WalletFragment extends Fragment {
    private View mView;
    private ExpandableListView expandableListView;
    private ExpandableListAdapter expandableListAdapter;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView=inflater.inflate(R.layout.fragment_wallet,container,false);
        initViews(mView);
        return mView;

    }
    private void initViews(View view){
        expandableListView= (ExpandableListView) view.findViewById(R.id.expandableListView);
        expandableListAdapter=new CustomExpandableListAdapter(getActivity());
        expandableListView.setAdapter(expandableListAdapter);
        expandableListView.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {

                if (v.getId()==R.id.wallet_plus_container){
                    return false;
                }else{
                    return true;
                }
            }
        });
    }
    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        menu.findItem(R.id.menu_search).setVisible(false);
        super.onPrepareOptionsMenu(menu);
    }
}