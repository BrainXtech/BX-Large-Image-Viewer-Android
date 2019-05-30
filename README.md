
# BX-Large-Image-Viewer-Android :Large image viewer library for Android

#### This project aims to provide an full screen image viewer based on Fresco. Supports Gif as well. Made by [BrainxTech](https://brainxtech.com/)

# Usage 
1.Include the library

##### You can depend on the library through Gradle
Add bx-large-image-viewer to the dependencies block in your app level build.gradle:
```groovy
implementation 'com.brainx.android:bx-large-image-viewer:2.0'
```

##### Or through Maven
```gradle
    <dependency>
        <groupId>com.brainx.android</groupId>
        <artifactId>bx-large-image-viewer</artifactId>
        <version>2.0</version>
        <type>pom</type>
    </dependency>
```


 2.Add permissions to manifest

```
<uses-permission android:name="android.permission.INTERNET" />
```


3.The BXImageViewer configuration.

```
  BxImageViewer bxImageViewer = new BxImageViewer.Builder(this)
                      .setImageChangeListener(imageChangeListener)
                      .setDataSet(imageUrlList)
                      .setBackgroundColorRes(R.color.colorAccent)
                      .setProgressbarColorRes(R.color.colorWhite)
                      .setImageMarginPx(20)
                      .setStartPosition(0)
                      .setHeaderView(headerView)
                      .show();
```


5. Image Change Listener.

```
BxImageViewer.OnImageChangeListener imageChangeListener = new BxImageViewer.OnImageChangeListener() {
        @Override
        public void onImageChanged(int position) {

        }
    };

```

# Features
```
1 Built-in BxImageViewer.
2 Image pinch to zoom.
3 Set Start Position
4 Custom Acton bar
5 API Level 15.
   More..
```

# Development

Incrementing the build version

- Update the version number references in the above     **Installation** section
- Update the `libraryVersion` in the SDK's `build.gradle` file (/bx-large-image-viewer/build.gradle)
- Run the following command on the root of the project. `./gradlew install bintrayUpload`. Make sure
you have the correct `bintray.user` & `bintray.apikey` values in `local.properties` file.
- Commit & push the changes

### Let us know!

```
We’d be really happy if you sent us links to your 
projects where you use our component. Just send an
email to library@brainxtech.com And do let us know
if you have any questions or suggestion regarding
the library. 
```

### License

    Copyright 2018, BrainxTech

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.

