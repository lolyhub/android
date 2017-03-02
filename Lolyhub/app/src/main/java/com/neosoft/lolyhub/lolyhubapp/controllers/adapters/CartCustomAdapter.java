package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.ClickListener;
import com.neosoft.lolyhub.lolyhubapp.rest.model.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by neosoft on 2/3/17.
 */

public class CartCustomAdapter extends RecyclerView.Adapter<CartCustomAdapter.ViewHolder> {
    List<ProductViewModel> mItems;
    Context mContext;
    private ClickListener mClickListener;

    public CartCustomAdapter(Context context) {
        super();
        mContext = context;
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
    public CartCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_row_cart, viewGroup, false);

        final CartCustomAdapter.ViewHolder viewHolder = new CartCustomAdapter.ViewHolder(v);

        // v.setOnClickListener(HomeActivity.myOnClickListener);
        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                viewHolder.cancelLayout.setVisibility(View.VISIBLE);
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(CartCustomAdapter.ViewHolder viewHolder, int i) {
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
            cancelLayout= (LinearLayout) itemView.findViewById(R.id.cart_cancel_layout);
            imageViewmageView = (ImageView) itemView.findViewById(R.id.imageId);
            txtView = (TextView) itemView.findViewById(R.id.titleId);

        }


    }
}
