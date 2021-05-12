package com.urbanairship.images;

import android.content.Context;
import android.widget.ImageView;

public interface ImageLoader {
    void load(Context context, ImageView imageView, ImageRequestOptions imageRequestOptions);
}
