package com.neosoft.lolyhub.lolyhubapp.utilities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.lolyhub.lolyhubapp.R;

/**
 * Created by neosoft on 2/3/17.
 */

public class ViewHolderTwo extends RecyclerView.ViewHolder {

    private TextView headerLabel;

    public ViewHolderTwo(View view) {
        super(view);

        headerLabel = (TextView) view.findViewById(R.id.textView);

    }

    public void setHeaderText(String text) {
        headerLabel.setText(text);
    }
}