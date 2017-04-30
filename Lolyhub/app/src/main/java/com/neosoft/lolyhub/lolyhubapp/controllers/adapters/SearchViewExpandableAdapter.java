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

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.utilities.CommonUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rajendra on 30/4/17.
 */

public class SearchViewExpandableAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public static class GroupViewHolder{
        public TextView headerTitle;
        public ImageView headerUpArrow;
        public ImageView headerDownAroow;

    }
    public static class ChildViewHolder{
        public TextView subTitle;


    }
    public SearchViewExpandableAdapter(Context context){
        this.context=context;
    }
    /*  public WalletExpandableListAdapter(Context context, List<String> expandableListTitle,
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
        ChildViewHolder childViewHolder=null;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_searchview_list_row, null);
            childViewHolder=new ChildViewHolder();

            childViewHolder.subTitle= (TextView) convertView.findViewById(R.id.searchHeader_title);

            convertView.setTag(childViewHolder);
                   }else{
             childViewHolder= (ChildViewHolder) convertView.getTag();
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
        return 3;
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
        final SearchViewExpandableAdapter.GroupViewHolder groupViewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_searchview_header_row, null);
            groupViewHolder=new SearchViewExpandableAdapter.GroupViewHolder();
            groupViewHolder.headerTitle= (TextView)convertView.findViewById(R.id.searchHeader_title);
            groupViewHolder.headerUpArrow= (ImageView) convertView.findViewById(R.id.header_upImg);
            groupViewHolder.headerDownAroow= (ImageView)convertView.findViewById(R.id.header_downImg);

            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder= (SearchViewExpandableAdapter.GroupViewHolder) convertView.getTag();
        }
                if(isExpanded) {
                   groupViewHolder.headerUpArrow.setImageResource(R.drawable.up);
                  //  ((ExpandableListView) parent).collapseGroup(listPosition);
                    groupViewHolder.headerDownAroow.setVisibility(View.GONE);
                    groupViewHolder.headerUpArrow.setVisibility(View.VISIBLE);
                }
                else
                {
                    groupViewHolder.headerDownAroow.setImageResource(R.drawable.down);
                    groupViewHolder.headerDownAroow.setVisibility(View.VISIBLE);
                    groupViewHolder.headerUpArrow.setVisibility(View.GONE);
                    //((ExpandableListView) parent).expandGroup(listPosition, true);
                }

                groupViewHolder.headerUpArrow.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(isExpanded) {
                            groupViewHolder.headerUpArrow.setImageResource(R.drawable.up);
                            ((ExpandableListView) parent).collapseGroup(listPosition);
                            groupViewHolder.headerDownAroow.setVisibility(View.GONE);
                            groupViewHolder.headerUpArrow.setVisibility(View.VISIBLE);
                        }
                        else
                        {
                            groupViewHolder.headerDownAroow.setImageResource(R.drawable.down);
                            groupViewHolder.headerDownAroow.setVisibility(View.VISIBLE);
                            groupViewHolder.headerUpArrow.setVisibility(View.GONE);
                            ((ExpandableListView) parent).expandGroup(listPosition, true);
                        }
                    }
                });


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