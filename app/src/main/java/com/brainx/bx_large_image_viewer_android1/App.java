package com.brainx.bx_large_image_viewer_android1;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;


public class App extends Application {

       @Override
    public void onCreate() {
        super.onCreate();

           Fresco.initialize(this);
    }

}
