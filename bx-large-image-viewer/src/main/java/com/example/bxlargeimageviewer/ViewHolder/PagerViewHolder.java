package com.example.bxlargeimageviewer.ViewHolder;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.Animatable;
import android.view.View;
import android.widget.ProgressBar;

import com.example.bxlargeimageviewer.Drawee.ZoomableDraweeView;
import com.example.bxlargeimageviewer.R;
import com.example.bxlargeimageviewer.Utils.BXUtils;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.backends.pipeline.PipelineDraweeControllerBuilder;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.imagepipeline.image.ImageInfo;

import java.util.List;


public class PagerViewHolder extends ViewHolder {
    //region properties
    ZoomableDraweeView simpleDraweeView;
    ProgressBar progressBar;
    Context context;
    List<String> imageURIs;
    View errorView;
    ///endregion

    //region LieCycle
    public PagerViewHolder(View itemView, List<String> imageURIs) {
        super(itemView);
        context = itemView.getContext();
        simpleDraweeView = itemView.findViewById(R.id.image_view);
        progressBar = itemView.findViewById(R.id.progress_bar_view);
        errorView = itemView.findViewById(R.id.error_view);
        this.imageURIs = imageURIs;
    }

    //endregion

    //region public method
    public void onBind(int position, int progressBarColor) {

        BXUtils.setProgressBarColor(progressBar, progressBarColor);

        progressBar.setVisibility(View.VISIBLE);
        errorView.setVisibility(View.GONE);

        PipelineDraweeControllerBuilder builder = Fresco.newDraweeControllerBuilder();
        String path = imageURIs.get(position);
        builder.setUri(path);
        builder.setAutoPlayAnimations(true);

        builder.setControllerListener(getDraweeControllerListener(simpleDraweeView));
        simpleDraweeView.setController(builder.build());
    }

    //endregion

    //region private method
    private BaseControllerListener<ImageInfo>
    getDraweeControllerListener(final ZoomableDraweeView drawee) {
        return new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                super.onFinalImageSet(id, imageInfo, animatable);

                if (imageInfo == null) {
                    return;
                }
                drawee.update(imageInfo.getWidth(), imageInfo.getHeight());
                progressBar.setVisibility(View.GONE);
                errorView.setVisibility(View.GONE);
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                super.onFailure(id, throwable);
                errorView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
            }
        };
    }


    //endregion
}
