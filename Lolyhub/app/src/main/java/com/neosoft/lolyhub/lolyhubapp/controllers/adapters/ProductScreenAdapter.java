package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.ClickListener;
import com.neosoft.lolyhub.lolyhubapp.rest.model.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neosoft on 24/4/17.
 */

public class ProductScreenAdapter extends RecyclerView.Adapter<ProductScreenAdapter.ViewHolder> {
    List<ProductViewModel> mItems;
    Context mContext;
    private ClickListener mClickListener;
    private boolean isVertical;

    public ProductScreenAdapter(Context context,boolean isVertical) {
        super();
        mContext = context;
        this.isVertical=isVertical;
        mItems = new ArrayList<ProductViewModel>();
    }

    public void addData(ProductViewModel horizontalViewModel) {
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
    public ProductScreenAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_productdetail_horizontalrow, viewGroup, false);

        final ProductScreenAdapter.ViewHolder viewHolder = new ProductScreenAdapter.ViewHolder(v);

        if (isVertical){
            viewHolder.product_radioview.setVisibility(View.VISIBLE);
            viewHolder.product_radioViewHorizontal.setVisibility(View.GONE);
        }else if(!isVertical){
            viewHolder.product_radioViewHorizontal.setVisibility(View.VISIBLE);
            viewHolder.product_radioview.setVisibility(View.GONE);
        }


        // v.setOnClickListener(HomeActivity.myOnClickListener);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ProductScreenAdapter.ViewHolder viewHolder, int i) {
      /*  ProductViewModel result = mItems.get(i);
        viewHolder.txtView.setText(result.getTitle());*/
        //    viewHolder.imageViewmageView.setImageResource(result.getAlpha2_code());


    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView product_img;
        public TextView product_title;
        public TextView product_redeemlp;
        public TextView product_earninglp;
        public RadioButton product_cart;
        public TextView product_wishlist;
        public LinearLayout product_radioview;
        public LinearLayout product_radioViewHorizontal;
        public CardView product_container;

        public ViewHolder(View itemView) {
            super(itemView);
            product_img = (ImageView) itemView.findViewById(R.id.productDetail_item_imageid);
            product_title = (TextView) itemView.findViewById(R.id.productDetail_item_titleid);
            product_redeemlp = (TextView) itemView.findViewById(R.id.list_redeemlpid);
            product_earninglp = (TextView) itemView.findViewById(R.id.list_earninglpid);
            product_cart= (RadioButton) itemView.findViewById(R.id.product_item_cartradioid);
            product_wishlist= (TextView) itemView.findViewById(R.id.product_item_wishlistradioid);
            product_radioview= (LinearLayout) itemView.findViewById(R.id.radioButtonConatiner);
            product_radioViewHorizontal= (LinearLayout) itemView.findViewById(R.id.radioButtonContainerHori);
            product_container= (CardView) itemView.findViewById(R.id.product_container);
        }
    }
}
