package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.content.Context;
import android.graphics.PorterDuff;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.swipe.SwipeLayout;
import com.daimajia.swipe.util.Attributes;
import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.ClickListener;
import com.neosoft.lolyhub.lolyhubapp.rest.model.ProductViewModel;
import com.neosoft.lolyhub.lolyhubapp.view.activities.WishlistActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neosoft on 2/3/17.
 */

public class WishlistCustomAdapter extends RecyclerView.Adapter<WishlistCustomAdapter.ViewHolder> {
    List<ProductViewModel> mItems;
    Context mContext;
    private SwipeLayout mLayout;
    private ClickListener mClickListener;

    public WishlistCustomAdapter(Context context) {
        super();
        mContext = context;
        mItems = new ArrayList<ProductViewModel>();
    }
    public void addData(ProductViewModel wishlistCustomAdapter) {
        mItems.add(wishlistCustomAdapter);
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
    public WishlistCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row_wishlist, viewGroup, false);

        final WishlistCustomAdapter.ViewHolder viewHolder = new WishlistCustomAdapter.ViewHolder(v);
        mLayout= (SwipeLayout)v.findViewById(R.id.container_wishlist);


       v.setOnClickListener(WishlistActivity.myOnClickListener);

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WishlistCustomAdapter.ViewHolder viewHolder, int i) {
      /*  ProductViewModel result = mItems.get(i);
        viewHolder.txtView.setText(result.getTitle());*/
        //    viewHolder.imageViewmageView.setImageResource(result.getAlpha2_code());


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imageViewmageView;
        public TextView txtView;
        public LinearLayout cancelLayout;

        public ViewHolder(View itemView) {
            super(itemView);
             cancelLayout= (LinearLayout) itemView.findViewById(R.id.cancel_layout);
            imageViewmageView = (ImageView)itemView.findViewById(R.id.imageId);
            txtView = (TextView) itemView.findViewById(R.id.titleId );

        }


    }
}
