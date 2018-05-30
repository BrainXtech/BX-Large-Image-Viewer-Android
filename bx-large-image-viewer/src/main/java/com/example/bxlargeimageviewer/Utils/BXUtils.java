package com.example.bxlargeimageviewer.Utils;


import android.graphics.PorterDuff;
import android.net.Uri;
import android.widget.ProgressBar;

import com.facebook.common.util.UriUtil;

/**
 * Created by brainx on 5/30/18.
 */

public class BXUtils {
    public static void setProgressBarColor(ProgressBar progressBar, int color){
        progressBar.getIndeterminateDrawable().setColorFilter(
                progressBar.getContext().getResources().getColor(color),
                android.graphics.PorterDuff.Mode.SRC_IN);
    }
    public static Uri getUriOfResourceImage(int imageId){
       return new Uri.Builder()
                .scheme(UriUtil.LOCAL_RESOURCE_SCHEME)
                .path(String.valueOf(imageId))
                .build();
    }
}
