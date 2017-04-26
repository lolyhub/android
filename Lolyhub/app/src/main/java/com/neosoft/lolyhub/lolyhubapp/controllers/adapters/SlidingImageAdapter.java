package com.neosoft.lolyhub.lolyhubapp.controllers.adapters;

import android.content.Context;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lolyhub.lolyhubapp.R;

import java.util.ArrayList;

/**
 * Created by neosoft on 26/4/17.
 */

public class SlidingImageAdapter extends PagerAdapter {


    private ArrayList<Integer> IMAGES;
    private LayoutInflater inflater;
    private Context context;


    public SlidingImageAdapter(Context context, ArrayList<Integer> IMAGES) {
        this.context = context;
        this.IMAGES=IMAGES;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public int getCount() {
        return IMAGES.size();
    }

    @Override
    public Object instantiateItem(ViewGroup view, int position) {
        View imageLayout = inflater.inflate(R.layout.layout_product_iamgesliding, view, false);

        assert imageLayout != null;
        final ImageView imageView = (ImageView) imageLayout
                .findViewById(R.id.product_swipe_image_id);


        imageView.setImageResource(IMAGES.get(position));

        view.addView(imageLayout, 0);

        return imageLayout;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view.equals(object);
    }

    @Override
    public void restoreState(Parcelable state, ClassLoader loader) {
    }

    @Override
    public Parcelable saveState() {
        return null;
    }


}