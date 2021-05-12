package com.bumptech.glide.load.resource.bitmap;

import android.graphics.Bitmap;
import com.bumptech.glide.load.engine.Resource;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.util.Util;
import java.util.Objects;

public class BitmapResource implements Resource<Bitmap> {
    private final Bitmap bitmap;
    private final BitmapPool bitmapPool;

    public static BitmapResource obtain(Bitmap bitmap2, BitmapPool bitmapPool2) {
        if (bitmap2 == null) {
            return null;
        }
        return new BitmapResource(bitmap2, bitmapPool2);
    }

    public BitmapResource(Bitmap bitmap2, BitmapPool bitmapPool2) {
        Objects.requireNonNull(bitmap2, "Bitmap must not be null");
        Objects.requireNonNull(bitmapPool2, "BitmapPool must not be null");
        this.bitmap = bitmap2;
        this.bitmapPool = bitmapPool2;
    }

    public Bitmap get() {
        return this.bitmap;
    }

    public int getSize() {
        return Util.getBitmapByteSize(this.bitmap);
    }

    public void recycle() {
        if (!this.bitmapPool.put(this.bitmap)) {
            this.bitmap.recycle();
        }
    }
}
