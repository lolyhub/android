package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;

import java.util.HashMap;
import java.util.List;

public class CustomExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public CustomExpandableListAdapter(Context context){
        this.context=context;
    }

  /*  public CustomExpandableListAdapter(Context context, List<String> expandableListTitle,
                                       HashMap<String, List<String>> expandableListDetail) {
        this.context = context;
        this.expandableListTitle = expandableListTitle;
        this.expandableListDetail = expandableListDetail;
    }
*/
    @Override
    public Object getChild(int listPosition, int expandedListPosition) {
       /* return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .get(expandedListPosition);*/
       return "nothing";
    }

    @Override
    public long getChildId(int listPosition, int expandedListPosition) {
        return expandedListPosition;
    }

    @Override
    public View getChildView(int listPosition, final int expandedListPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {
        final String expandedListText = (String) getChild(listPosition, expandedListPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_wallet_items_row, null);
        }
       /* TextView expandedListTextView = (TextView) convertView
                .findViewById(R.id.expandedListItem);
        expandedListTextView.setText(expandedListText);*/
        return convertView;
    }

    @Override
    public int getChildrenCount(int listPosition) {
       /* return this.expandableListDetail.get(this.expandableListTitle.get(listPosition))
                .size();*/
       return 1;
    }

    @Override
    public Object getGroup(int listPosition) {

       /* return this.expandableListTitle.get(listPosition);*/
       return  "nothing";
    }

    @Override
    public int getGroupCount() {
       /* return this.expandableListTitle.size();*/
       return 5;
    }

    @Override
    public long getGroupId(int listPosition) {
        return listPosition;
    }

    @Override
    public View getGroupView(final int listPosition, final boolean isExpanded,
                             View convertView, final ViewGroup parent) {
        String listTitle = (String) getGroup(listPosition);
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_wallet_header_row, null);
        }
        final LinearLayout cancelLayout= (LinearLayout) convertView.findViewById(R.id.cancel_layout);
        LinearLayout textView= (LinearLayout) convertView.findViewById(R.id.wallet_plus_container);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(context, "clicked on plus", Toast.LENGTH_SHORT).show();
                if(isExpanded) ((ExpandableListView) parent).collapseGroup(listPosition);
                else ((ExpandableListView) parent).expandGroup(listPosition, true);
                cancelLayout.setVisibility(View.VISIBLE);

            }
        });

        /*TextView listTitleTextView = (TextView) convertView
                .findViewById(R.id.listTitle);
        listTitleTextView.setTypeface(null, Typeface.BOLD);
        listTitleTextView.setText(listTitle);*/
        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int listPosition, int expandedListPosition) {
        return true;
    }
}