package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.ClickListener;
import com.neosoft.lolyhub.lolyhubapp.rest.model.ProductViewModel;
import com.neosoft.lolyhub.lolyhubapp.view.activities.ProductDetailActivity;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rajendra on 7/5/17.
 */

public class OrderPageCustomAdapter extends RecyclerView.Adapter<OrderPageCustomAdapter.ViewHolder> {
    List<ProductViewModel> mItems;
    Context mContext;
    private ClickListener mClickListener;
    private boolean isVertical;

    public OrderPageCustomAdapter(Context context) {
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
    public OrderPageCustomAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_orderhistory_row, viewGroup, false);

        final OrderPageCustomAdapter.ViewHolder viewHolder = new OrderPageCustomAdapter.ViewHolder(v);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(OrderPageCustomAdapter.ViewHolder viewHolder, int i) {
      /*  ProductViewModel result = mItems.get(i);
        viewHolder.txtView.setText(result.getTitle());*/
        //    viewHolder.imageViewmageView.setImageResource(result.getAlpha2_code());
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(mContext, ProductDetailActivity.class);
                mContext.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return 4;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView number;
        public TextView redeemFor;
        public TextView earning;
        public TextView redeemDate;
        public TextView redeemTime;
        public Button voucherDetailButton;
        public ImageView orderImg;


        public CardView product_container;

        public ViewHolder(View itemView) {
            super(itemView);
          number= (TextView) itemView.findViewById(R.id.id_number);
            redeemFor= (TextView) itemView.findViewById(R.id.id_redeemValue);
            earning= (TextView) itemView.findViewById(R.id.id_earningValue);
            redeemDate= (TextView) itemView.findViewById(R.id.id_redeemDateValue);
            redeemTime= (TextView) itemView.findViewById(R.id.id_redeemTimeValue);
            voucherDetailButton= (Button) itemView.findViewById(R.id.id_viewVoucher);
            orderImg= (ImageView) itemView.findViewById(R.id.id_orderImg);

            product_container= (CardView) itemView.findViewById(R.id.product_container);
        }
    }
}