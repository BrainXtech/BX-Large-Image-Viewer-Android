package com.example.bxlargeimageviewer;

import android.content.Context;
import android.support.annotation.ColorInt;
import android.support.annotation.ColorRes;
import android.support.annotation.DimenRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;

import com.example.bxlargeimageviewer.Adapters.ImageViewAdapter;

import java.util.List;

/**
 * Created by brainx on 5/17/18.
 */

public class BxImageViewerV2 {

    //region Properties
    @NonNull
    private Builder builder;
    private int totalImages = 0;

    @Nullable
    private ImageViewAdapter imageViewAdapter;
    @Nullable
    private View imageViewer;
    @Nullable
    private ViewPager viewPager;
    @Nullable
    private ViewGroup headerContainer;

    @Nullable
    private AlertDialog alertDialog;
    //endregion

    //region Public Static Method

    private BxImageViewerV2(@NonNull Builder builder) {
        this.builder = builder;
        initView();
        initAdapter();
        setDataSet();
        setOverlayView();
        setBackgroundColor();
        setImageMarginPx();
        setProgressBarColor();
    }

    //endregion

    //region Private method
    private void initView() {
        imageViewer = View.inflate(builder.mContext, R.layout.image_view_layout, null);
        viewPager = imageViewer.findViewById(R.id.my_pager);
        headerContainer = imageViewer.findViewById(R.id.header_container);
    }

    private void initAdapter() {
        imageViewAdapter = new ImageViewAdapter(builder.mContext);
    }

    private void setViewPagerListener() {
        if (viewPager != null) {
            viewPager.setOnPageChangeListener(onPageChangeListener);
        }
    }

    private void setViewPagerAdapter() {
        if (viewPager != null && imageViewAdapter != null)
            viewPager.setAdapter(imageViewAdapter);
    }

    private void setupAlertDialog() {
        alertDialog = new AlertDialog.Builder(builder.mContext,
                android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
                .setView(imageViewer)
                .create();
    }

    private void setDataSet() {
        if (builder.imageURIs != null) {
            totalImages = builder.imageURIs.size();
        }
        if (viewPager != null && imageViewAdapter != null)
            imageViewAdapter.addDataSet(builder.imageURIs);
    }

    private void setOverlayView() {
        if (headerContainer == null || builder.headerView == null)
            return;

        headerContainer.removeAllViews();
        headerContainer.addView(builder.headerView);
    }

    private void setBackgroundColor() {
        try {
            if (imageViewer != null)
                imageViewer.setBackgroundColor(builder.backgroundColor);
        } catch (Exception ignore) {
        }
    }

    private void setImageMarginPx() {
        try {
            if (viewPager != null)
                viewPager.setPageMargin(builder.imageMarginPixels);
        } catch (Exception ignore) {
        }
    }

    private void setProgressBarColor() {
        if (imageViewAdapter != null)
            imageViewAdapter.setProgressBarColor(builder.progressbarColor);
    }

    //endregion

    //region Pubic methods

    public void show() {
        setupAlertDialog();

        if (alertDialog == null || alertDialog.isShowing())
            return;

        setViewPagerAdapter();
        setViewPagerListener();

        if (builder.startingPos >= totalImages)
            builder.startingPos = totalImages - 1;

        if (viewPager != null && builder.startingPos > 0)
            viewPager.setCurrentItem(builder.startingPos);

        if (builder.imageChangeListener != null)
            builder.imageChangeListener.onImageChanged(builder.startingPos);
        if (totalImages > 0)
            alertDialog.show();
    }

    public void dismiss() {
        if (alertDialog != null && alertDialog.isShowing())
            alertDialog.dismiss();
    }

    //endregion

    //region CallBack
    private ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            if (builder.imageChangeListener != null)
                builder.imageChangeListener.onImageChanged(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //endregion

    //region Builder

    public static class Builder {
        @NonNull
        private final Context mContext;
        @Nullable
        private OnImageChangeListener imageChangeListener;
        @Nullable
        private List<String> imageURIs;

        private int startingPos = 0;

        @ColorInt
        private int backgroundColor;
        @ColorInt
        private int progressbarColor;

        private int imageMarginPixels;

        @Nullable
        private View headerView;

        public Builder(@NonNull Context mContext) {
            this.mContext = mContext;
            this.backgroundColor = mContext.getResources().getColor(R.color.bxColorBlack);
            this.progressbarColor = mContext.getResources().getColor(R.color.bxColorWhite);
            this.imageMarginPixels = 0;
        }

        public Builder setImageChangeListener(@Nullable OnImageChangeListener imageChangeListener) {
            this.imageChangeListener = imageChangeListener;
            return this;
        }

        public Builder setDataSet(@Nullable List<String> imageURIs) {
            this.imageURIs = imageURIs;
            return this;
        }

        public Builder setStartPosition(int pos) {
            if (pos < 0) {
                this.startingPos = 0;
            } else {
                this.startingPos = pos;
            }
            return this;
        }

        public Builder setBackgroundColorRes(@ColorRes int color) {
            this.backgroundColor = mContext.getResources().getColor(color);
            return this;
        }

        public Builder setBackgroundColor(@ColorInt int color) {
            this.backgroundColor = color;
            return this;
        }

        public Builder setProgressbarColorRes(@ColorRes int color) {
            this.progressbarColor = mContext.getResources().getColor(color);
            return this;
        }

        public Builder setProgressbarColor(@ColorInt int color) {
            this.progressbarColor = color;
            return this;
        }

        public Builder setImageMarginRes(@DimenRes int dimen) {
            try {
                this.imageMarginPixels = Math.round(mContext.getResources().getDimension(dimen));
            } catch (Exception ignored) {
            }
            return this;
        }

        public Builder setImageMarginPx(int dimen) {
            this.imageMarginPixels = dimen;
            return this;
        }

        public Builder setHeaderView(@Nullable View headerView) {
            this.headerView = headerView;
            return this;
        }

        public BxImageViewerV2 show() {
            BxImageViewerV2 viewer = this.create();
            viewer.show();
            return viewer;
        }

        public BxImageViewerV2 create() {
            return new BxImageViewerV2(this);
        }
    }

    //endregion

    //region Listener
    public interface OnImageChangeListener {
        void onImageChanged(int position);
    }
    //endregion

}