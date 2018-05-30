package com.example.bxlargeimageviewer.Adapters;

import android.content.Context;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.bxlargeimageviewer.R;
import com.example.bxlargeimageviewer.ViewHolder.PagerViewHolder;

import java.util.List;


public class ImageViewAdapter extends RecyclingPagerAdapter<PagerViewHolder> {

    private List<String> imageURIs;
    Context context;
    private int progressBarColor = -1;

    public ImageViewAdapter(Context context) {
        this.context = context;

    }

    @Override
    public int getItemCount() {
        if (imageURIs != null)
            return imageURIs.size();
        else {
            return 0;
        }
    }

    @Override
    public void onBindViewHolder(PagerViewHolder holder, int position) {
        if (progressBarColor == -1) {
            progressBarColor = R.color.bxColorWhite;
        }
        holder.onBind(position, progressBarColor);

    }

    @Override
    public PagerViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        PagerViewHolder holder = new PagerViewHolder(view, imageURIs);
        return holder;
    }

    public void addDataSet(List<String> imageURIs) {
        this.imageURIs = imageURIs;

    }

    public void setProgressBarColor(int progressBarColor) {
        this.progressBarColor = progressBarColor;
    }
}
