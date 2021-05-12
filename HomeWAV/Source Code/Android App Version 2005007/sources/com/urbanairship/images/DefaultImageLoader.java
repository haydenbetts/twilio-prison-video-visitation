package com.urbanairship.images;

import android.content.Context;
import android.widget.ImageView;
import java.util.Map;
import java.util.WeakHashMap;

public class DefaultImageLoader implements ImageLoader {
    private final ImageCache imageCache;
    /* access modifiers changed from: private */
    public final Map<ImageView, ImageRequest> requestMap = new WeakHashMap();

    public DefaultImageLoader(Context context) {
        this.imageCache = new ImageCache(context);
    }

    private void cancelRequest(ImageView imageView) {
        ImageRequest remove;
        if (imageView != null && (remove = this.requestMap.remove(imageView)) != null) {
            remove.cancel();
        }
    }

    public void load(Context context, ImageView imageView, ImageRequestOptions imageRequestOptions) {
        cancelRequest(imageView);
        AnonymousClass1 r0 = new ImageRequest(context, this.imageCache, imageView, imageRequestOptions) {
            /* access modifiers changed from: package-private */
            public void onFinish(ImageView imageView) {
                if (imageView != null) {
                    DefaultImageLoader.this.requestMap.remove(imageView);
                }
            }
        };
        this.requestMap.put(imageView, r0);
        r0.execute();
    }
}
