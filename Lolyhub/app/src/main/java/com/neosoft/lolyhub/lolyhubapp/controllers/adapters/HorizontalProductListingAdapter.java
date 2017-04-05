package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.ClickListener;
import com.neosoft.lolyhub.lolyhubapp.rest.model.HorizontalViewModel;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Result;
import com.neosoft.lolyhub.lolyhubapp.view.activities.HomeActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neosoft on 14/2/17.
 */

public class HorizontalProductListingAdapter extends RecyclerView.Adapter<HorizontalProductListingAdapter.ViewHolder> {
    List<HorizontalViewModel> mItems;
    Context mContext;
    private ClickListener mClickListener;

    public HorizontalProductListingAdapter(Context context) {
        super();
        mContext = context;
        mItems = new ArrayList<HorizontalViewModel>();
    }
    public void addData(HorizontalViewModel horizontalViewModel) {
        mItems.add(horizontalViewModel);
        //  mItems=result;
        notifyDataSetChanged();
    }
    public void removeData(int position) {
        mItems.remove(position);
        notifyDataSetChanged();
    }
    public void clear() {
        mItems.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.layout_shopebycategory, viewGroup, false);
        ViewHolder viewHolder = new ViewHolder(v);

      //  v.setOnClickListener(HomeActivity.myOnClickListener);

       /* v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });*/
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        HorizontalViewModel result = mItems.get(i);
        viewHolder.txtView.setText(result.getTitle());
     //    viewHolder.imageViewmageView.setImageResource(result.getAlpha2_code());


    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewmageView;
        public TextView txtView;


        public ViewHolder(View itemView) {
            super(itemView);

            imageViewmageView = (ImageView)itemView.findViewById(R.id.imageId);
            txtView = (TextView) itemView.findViewById(R.id.titleId );

        }


    }
}