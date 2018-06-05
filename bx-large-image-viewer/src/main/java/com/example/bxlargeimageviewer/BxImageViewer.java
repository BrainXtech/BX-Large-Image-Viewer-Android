package com.example.bxlargeimageviewer;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.DimenRes;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.ViewGroup;

import com.example.bxlargeimageviewer.Adapters.ImageViewAdapter;

import java.util.List;

/**
 * Created by brainx on 5/17/18.
 */

public class BxImageViewer {

    //region Properties
    private int totalImages = 0;
    private int selectedPosition = 0;

    private ImageViewAdapter imageViewAdapter;
    private View header;
    private View imageViewer;
    private ViewPager viewPager;
    private ViewGroup headerContainer;

    private OnImageChangeListener imageChangeListener;
    private List<String> imageURIs;
    private static BxImageViewer bxImageViewer;
    private static Context context;
    private static AlertDialog alertDialog;
    private int backgroundColor;
    private int imageMarginPixels;
    //endregion

    //region Public Static Method
    public static BxImageViewer getInstance(Context con) {
        context = con;
        if (bxImageViewer == null) {
            bxImageViewer = new BxImageViewer();
        }
        return bxImageViewer;
    }
    //endregion

    //region initialization
    private void initView() {
        imageViewer = View.inflate(context, R.layout.image_view_layout, null);
        viewPager = imageViewer.findViewById(R.id.my_pager);
        headerContainer = imageViewer.findViewById(R.id.header_container);
    }

    public BxImageViewer initialization() {

        initView();
        initAdapter();
        onDismiss();
        imageViewer.setBackgroundResource(R.color.bxColorBlack);
        return bxImageViewer;
    }

    private void initAdapter() {
        imageViewAdapter = new ImageViewAdapter(context);
    }

    //endregion

    //region setListener
    private void setViewPagerListener() {
        viewPager.setOnPageChangeListener(onPageChangeListener);
    }

    //endregion

    //region private method

    private void setViewPagerAdapter() {
        if (viewPager != null && imageViewAdapter != null)
            viewPager.setAdapter(imageViewAdapter);
    }

    private void setupAlertDialog() {
        alertDialog = new AlertDialog.Builder(context, android.R.style.Theme_Translucent_NoTitleBar_Fullscreen)
                .setView(imageViewer)
                .create();
    }
    //endregion

    //region public method


    public BxImageViewer addDataSet(List<String> imageURIs) {
        this.imageURIs = imageURIs;
        if (imageURIs != null) {
            totalImages = imageURIs.size();
        }
        if (viewPager != null && imageViewAdapter != null)
            imageViewAdapter.addDataSet(imageURIs);
        return bxImageViewer;
    }

    public BxImageViewer setStartPosition(int selectedPosition) {
        if (selectedPosition < 0) {
            this.selectedPosition = 0;
        } else {
            this.selectedPosition = selectedPosition;
        }
        return bxImageViewer;
    }

    public BxImageViewer setOverlayView(View header) {
        this.header = header;
        if (headerContainer != null) {
            headerContainer.removeAllViews();
            headerContainer.addView(header);
        }
        return bxImageViewer;
    }

    public BxImageViewer setBackgroundColor(int color) {
        try {
            imageViewer.setBackgroundColor(color);
        } catch (Exception e) {
        }
        return bxImageViewer;
    }

    public BxImageViewer setBackgroundColorRes(int color) {
        try {
            imageViewer.setBackgroundResource(color);
        } catch (Exception e) {
        }
        return bxImageViewer;
    }

    public BxImageViewer setImageMarginPx(int marginPixels) {
        try {
            this.imageMarginPixels = marginPixels;
            viewPager.setPageMargin(imageMarginPixels);
        } catch (Exception e) {
        }
        return this;
    }

    public BxImageViewer setImageMargin(Context context, @DimenRes int dimen) {
        try {
            this.imageMarginPixels = Math.round(context.getResources().getDimension(dimen));
            viewPager.setPageMargin(imageMarginPixels);
        } catch (Exception e) {
        }
        return this;
    }

    public BxImageViewer setProgressBarColorRes(int progressBarColor) {
        imageViewAdapter.setProgressBarColor(progressBarColor);
        return bxImageViewer;
    }

    public BxImageViewer setImageChangeListener(OnImageChangeListener imageChangeListener) {

        this.imageChangeListener = imageChangeListener;
        return bxImageViewer;
    }

    public void show() {
        setupAlertDialog();
        if (alertDialog != null && !alertDialog.isShowing()) {
            setViewPagerAdapter();
            setViewPagerListener();
            if (selectedPosition >= totalImages) {
                selectedPosition = totalImages - 1;
            }
            if (selectedPosition > 0) {
                viewPager.setCurrentItem(selectedPosition);
            }
            if (imageChangeListener != null)
                imageChangeListener.onImageChanged(selectedPosition);
            if (totalImages > 0)
                alertDialog.show();
        }
    }

    public void onDismiss() {
        if (alertDialog != null && alertDialog.isShowing()) {
            alertDialog.dismiss();
        }
    }

    //endregion

    //region CallBack
    ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
       }

        @Override
        public void onPageSelected(int position) {
            if (imageChangeListener != null)
                imageChangeListener.onImageChanged(position);
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };

    //endregion

    //region Listener
    public interface OnImageChangeListener {
        void onImageChanged(int position);
    }
    //endregion

}