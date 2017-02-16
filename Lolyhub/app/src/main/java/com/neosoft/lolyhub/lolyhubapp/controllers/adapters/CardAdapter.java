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
import com.neosoft.lolyhub.lolyhubapp.view.activities.HomeActivity;

import java.util.ArrayList;
import java.util.List;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> {
    List<Result> mItems;
    Context mContext;
    private ClickListener mClickListener;

    public CardAdapter(Context context) {
        super();
        mContext=context;
        mItems = new ArrayList<Result>();
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
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);
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
        Result result = mItems.get(i);
        viewHolder.name.setText(result.getName());
        viewHolder.alpha2.setText(result.getAlpha2_code());
        viewHolder.alpha3.setText(result.getAlpha3_code());

    }

    @Override
    public int getItemCount() {
        return mItems.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView alpha2;
        public TextView alpha3;
        public CardView cardView;
        public LinearLayout mLyaout;

        public ViewHolder(View itemView) {
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