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

import static com.lolyhub.lolyhubapp.R.id.textView;

public class WalletExpandableListAdapter extends BaseExpandableListAdapter {

    private Context context;
    private List<String> expandableListTitle;
    private HashMap<String, List<String>> expandableListDetail;

    public static class GroupViewHolder{
       public LinearLayout wallet_cancelLayout;
        public  ImageView imageView;
        public TextView pointsTextView;
        public  TextView lpPointsTextView;
        public LinearLayout plusLayout;
    }
    public static class ChildViewHolder{

    }
    public WalletExpandableListAdapter(Context context){
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
        GroupViewHolder groupViewHolder;
        if (convertView == null) {
            LayoutInflater layoutInflater = (LayoutInflater) this.context.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.item_wallet_header_rows, null);
             groupViewHolder=new GroupViewHolder();
            groupViewHolder.imageView= (ImageView) convertView.findViewById(R.id.wallet_item_img_id);
            groupViewHolder.wallet_cancelLayout= (LinearLayout) convertView.findViewById(R.id.wallet_cancel_layout);
            groupViewHolder.plusLayout= (LinearLayout) convertView.findViewById(R.id.wallet_plus_container);
            groupViewHolder.pointsTextView= (TextView) convertView.findViewById(R.id.wallet_header_points_id);
            groupViewHolder.lpPointsTextView= (TextView) convertView.findViewById(R.id.wallet_header_lppoints_id);

            convertView.setTag(groupViewHolder);
        }else{
            groupViewHolder= (GroupViewHolder) convertView.getTag();
        }

        groupViewHolder.wallet_cancelLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Integer> stringObj=new ArrayList<Integer>();
                CommonUtils.deleteWalletRowDialog(context,stringObj,listPosition);
                notifyDataSetInvalidated();
            }
        });
        groupViewHolder.plusLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isExpanded) {
                    ((ExpandableListView) parent).collapseGroup(listPosition);
                }
                else
                {
                    ((ExpandableListView) parent).expandGroup(listPosition, true);
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