package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.utilities.CommonUtils;

import java.util.ArrayList;
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
        final LinearLayout cancelLayout= (LinearLayout) convertView.findViewById(R.id.item_cancel_layout);
        final LinearLayout parentLayout= (LinearLayout) convertView.findViewById(R.id.listContainer_id);
        final ImageView headerImage= (ImageView) convertView.findViewById(R.id.header_img_id);
        LinearLayout textView= (LinearLayout) convertView.findViewById(R.id.wallet_plus_container);
        cancelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> stringObj=new ArrayList<Integer>();
                CommonUtils.deleteWalletRowDialog(context,stringObj,listPosition);
                notifyDataSetInvalidated();
            }
        });
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             //   Toast.makeText(context, "clicked on plus", Toast.LENGTH_SHORT).show();
                if(isExpanded) {
                    ((ExpandableListView) parent).collapseGroup(listPosition);
                    cancelLayout.setVisibility(View.GONE);
                    ViewGroup.MarginLayoutParams params =
                            (ViewGroup.MarginLayoutParams)headerImage.getLayoutParams();
                    params.setMargins(0,0,0,0);
                    headerImage.setLayoutParams(params);
                    headerImage.requestLayout();
                }
                else
                {
                    ((ExpandableListView) parent).expandGroup(listPosition, true);
                    cancelLayout.setVisibility(View.VISIBLE);
                    ViewGroup.MarginLayoutParams params =
                            (ViewGroup.MarginLayoutParams)headerImage.getLayoutParams();
                    params.setMargins(-50,0,0,0);
                    headerImage.setLayoutParams(params);
                    headerImage.requestLayout();
                }
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