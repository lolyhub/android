package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.content.Context;
import android.os.Build;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;
import com.neosoft.lolyhub.lolyhubapp.controllers.interfaces.ClickListener;
import com.neosoft.lolyhub.lolyhubapp.rest.model.Result;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<Result> mItems;
    Context mContext;
    private ClickListener mClickListener;
    private int viewType;
    private final int USER = 0, IMAGE = 1;

    public CardAdapter(Context context, int viewType) {
        super();
        mContext=context;
        mItems = new ArrayList<Result>();
        this.viewType=viewType;
    }

    public void addData(Result  result) {
        mItems.add(result);
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
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder=null;
        if (viewType==1)
        {
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_row_cart, viewGroup, false);
             viewHolder = new CardViewHolder(v);

            return viewHolder;

        }else if(viewType==2){
            View v = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_row_wishlist, viewGroup, false);
             viewHolder = new WishlistViewHolder(v);

            return viewHolder;

        }

return null;

      //  v.setOnClickListener(HomeActivity.myOnClickListener);

       /* v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "Clicked", Toast.LENGTH_SHORT).show();
            }
        });*/

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
        switch (viewType) {
            case 1:
                CardViewHolder vh1 = (CardViewHolder) viewHolder;
                /*Result result = mItems.get(position);
                vh1.name.setText(result.getName());
                vh1.alpha2.setText(result.getAlpha2_code());
                vh1.alpha3.setText(result.getAlpha3_code());*/

                break;
            case 2:
                WishlistViewHolder vh2=(WishlistViewHolder)viewHolder;

                break;

        }
    }


    @Override
    public int getItemCount() {
        return 5;
    }

    class CardViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView alpha2;
        public TextView alpha3;
        public CardView cardView;
        public LinearLayout mLyaout;

        public CardViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.id_name);
            alpha2 = (TextView) itemView.findViewById(R.id.id_alpha2);
            alpha3 = (TextView) itemView.findViewById(R.id.id_alpha3);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            mLyaout= (LinearLayout) itemView.findViewById(R.id.home_layout);
            setRippleEffect(cardView,mLyaout);
        }


    }
    class WishlistViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView alpha2;
        public TextView alpha3;
        public CardView cardView;
        public LinearLayout mLyaout;

        public WishlistViewHolder(View itemView) {
            super(itemView);

            name = (TextView) itemView.findViewById(R.id.id_name);
            alpha2 = (TextView) itemView.findViewById(R.id.id_alpha2);
            alpha3 = (TextView) itemView.findViewById(R.id.id_alpha3);
            cardView= (CardView) itemView.findViewById(R.id.card_view);
            mLyaout= (LinearLayout) itemView.findViewById(R.id.home_layout);
            setRippleEffect(cardView,mLyaout);
        }


    }
    private void setRippleEffect(CardView cardView , LinearLayout linearLayout){
        // cardView.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.riple_effect));
        if (Build.VERSION.SDK_INT< Build.VERSION_CODES.LOLLIPOP) {
          /* linearLayout.removeView(cardView);
            mMaterialRippleLayout =new MaterialRippleLayout(mContext);
            mMaterialRippleLayout.setLayoutParams(new MaterialRippleLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            mMaterialRippleLayout.addView(cardView);*/

           // linearLayout.setBackgroundDrawable(mContext.getResources().getDrawable(R.drawable.riple_effect));
        }
    }

}