package com.brainx.bx_large_image_viewer_android1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.bxlargeimageviewer.BxImageViewer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        View headerView = View.inflate(this, R.layout.custom_header_view, null);
        List<String> imageUrlList = getImagesList();

        BxImageViewer bxImageViewer = BxImageViewer.getInstance(this);
        bxImageViewer.initialization()
                .setImageChangeListener(imageChangeListener)
                .addDataSet(imageUrlList)
                .setStartPosition(0).setOverlayView(headerView).show();
    }
    //endregion

    //region Private method
    private List<String> getImagesList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("http://www.kinyu-z.net/data/wallpapers/35/823942.jpg");
        stringList.add("https://media.giphy.com/media/Rxb9AZOq1U1Vu/giphy.gif");
        stringList.add("https://media.istockphoto.com/photos/plant-growing-picture-id510222832");
        return stringList;
    }
    //endregion

    //region CallBack
    BxImageViewer.OnImageChangeListener imageChangeListener = new BxImageViewer.OnImageChangeListener() {
        @Override
        public void onImageChanged(int position) {

        }
    };
    //endregion
}
