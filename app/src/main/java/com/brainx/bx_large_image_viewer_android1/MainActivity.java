package com.brainx.bx_large_image_viewer_android1;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.bxlargeimageviewer.BxImageViewer;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    List<String> imageUrlList;
    BxImageViewer imageViewer;

    //region Lifecycle
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button button = findViewById(R.id.button_view);
        button.setOnClickListener(this);
        imageUrlList = getImagesList();


    }
    //endregion

    //region Private method
    private List<String> getImagesList() {
        List<String> stringList = new ArrayList<>();
        stringList.add("https://images.unsplash.com/photo-1550354256-48abc7b2318c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=562&q=80");
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

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_view) {
            View headerView = View.inflate(this, R.layout.custom_header_view, null);
            headerView.findViewById(R.id.bx_Close).setOnClickListener(this);

            imageViewer = new BxImageViewer.Builder(this)
                    .setImageChangeListener(imageChangeListener)
                    .setDataSet(imageUrlList)
                    .setBackgroundColorRes(R.color.colorBlack)
                    .setProgressbarColorRes(R.color.colorWhite)
                    .setImageMarginPx(20)
                    .setStartPosition(0)
                    .setHeaderView(headerView)
                    .show();

        } else if (view.getId() == R.id.bx_Close) {
            if (imageViewer != null)
                imageViewer.dismiss();
        }
    }

    //endregion
}
