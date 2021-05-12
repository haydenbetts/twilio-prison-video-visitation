package com.urbanairship.images;

import android.content.Context;
import android.graphics.drawable.AnimatedImageDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.TransitionDrawable;
import android.os.Build;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import androidx.core.content.ContextCompat;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.CancelableOperation;
import com.urbanairship.Logger;
import com.urbanairship.util.ImageUtils;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.util.concurrent.Executor;

abstract class ImageRequest {
    private static final int FADE_IN_TIME_MS = 200;
    private final Executor EXECUTOR = AirshipExecutors.THREAD_POOL_EXECUTOR;
    private final Context context;
    private int height;
    private final ImageCache imageCache;
    private final ImageRequestOptions imageRequestOptions;
    /* access modifiers changed from: private */
    public final WeakReference<ImageView> imageViewReference;
    /* access modifiers changed from: private */
    public final CancelableOperation pendingRequest = new CancelableOperation();
    private ViewTreeObserver.OnPreDrawListener preDrawListener;
    private int width;

    /* access modifiers changed from: package-private */
    public abstract void onFinish(ImageView imageView);

    ImageRequest(Context context2, ImageCache imageCache2, ImageView imageView, ImageRequestOptions imageRequestOptions2) {
        this.context = context2;
        this.imageCache = imageCache2;
        this.imageRequestOptions = imageRequestOptions2;
        this.imageViewReference = new WeakReference<>(imageView);
    }

    /* access modifiers changed from: package-private */
    public void cancel() {
        ImageView imageView = (ImageView) this.imageViewReference.get();
        if (!(imageView == null || this.preDrawListener == null)) {
            imageView.getViewTreeObserver().removeOnPreDrawListener(this.preDrawListener);
            this.imageViewReference.clear();
        }
        this.pendingRequest.cancel();
    }

    /* access modifiers changed from: package-private */
    public void execute() {
        if (!this.pendingRequest.isCancelled()) {
            ImageView imageView = (ImageView) this.imageViewReference.get();
            if (imageView == null) {
                onFinish((ImageView) null);
                return;
            }
            this.width = imageView.getWidth();
            int height2 = imageView.getHeight();
            this.height = height2;
            if (this.width == 0 && height2 == 0) {
                this.preDrawListener = new ViewTreeObserver.OnPreDrawListener() {
                    public boolean onPreDraw() {
                        ImageView imageView = (ImageView) ImageRequest.this.imageViewReference.get();
                        if (imageView == null) {
                            return true;
                        }
                        imageView.getViewTreeObserver().removeOnPreDrawListener(this);
                        if (!imageView.getViewTreeObserver().isAlive()) {
                            return true;
                        }
                        if (imageView.getHeight() == 0 && imageView.getWidth() == 0) {
                            ImageRequest.this.onFinish(imageView);
                            return true;
                        }
                        ImageRequest.this.execute();
                        return true;
                    }
                };
                imageView.getViewTreeObserver().addOnPreDrawListener(this.preDrawListener);
                return;
            }
            Drawable drawable = this.imageCache.getDrawable(getCacheKey());
            if (drawable != null) {
                imageView.setImageDrawable(drawable);
                onFinish(imageView);
                return;
            }
            if (this.imageRequestOptions.getPlaceHolder() != 0) {
                imageView.setImageResource(this.imageRequestOptions.getPlaceHolder());
            } else {
                imageView.setImageDrawable((Drawable) null);
            }
            this.EXECUTOR.execute(new Runnable() {
                public void run() {
                    if (!ImageRequest.this.pendingRequest.isCancelled()) {
                        try {
                            final Drawable access$200 = ImageRequest.this.fetchDrawableOnBackground();
                            if (access$200 != null) {
                                ImageRequest.this.pendingRequest.addOnRun(new Runnable() {
                                    public void run() {
                                        if (!ImageRequest.this.pendingRequest.isCancelled()) {
                                            ImageRequest.this.applyDrawable(access$200);
                                        }
                                    }
                                });
                                ImageRequest.this.pendingRequest.run();
                            }
                        } catch (IOException e) {
                            Logger.debug(e, "Unable to fetch bitmap", new Object[0]);
                        }
                    }
                }
            });
        }
    }

    private String getCacheKey() {
        if (this.imageRequestOptions.getUrl() == null) {
            return "";
        }
        return this.imageRequestOptions.getUrl() + ",size(" + this.width + "x" + this.height + ")";
    }

    /* access modifiers changed from: private */
    public Drawable fetchDrawableOnBackground() throws IOException {
        ImageUtils.DrawableResult fetchScaledDrawable;
        this.imageCache.installHttpCache();
        if (this.imageViewReference.get() == null || this.imageRequestOptions.getUrl() == null || (fetchScaledDrawable = ImageUtils.fetchScaledDrawable(this.context, new URL(this.imageRequestOptions.getUrl()), this.width, this.height)) == null) {
            return null;
        }
        this.imageCache.cacheDrawable(getCacheKey(), fetchScaledDrawable.drawable, fetchScaledDrawable.bytes);
        return fetchScaledDrawable.drawable;
    }

    /* access modifiers changed from: private */
    public void applyDrawable(Drawable drawable) {
        ImageView imageView = (ImageView) this.imageViewReference.get();
        if (drawable != null && imageView != null) {
            TransitionDrawable transitionDrawable = new TransitionDrawable(new Drawable[]{new ColorDrawable(ContextCompat.getColor(this.context, 17170445)), drawable});
            imageView.setImageDrawable(transitionDrawable);
            transitionDrawable.startTransition(200);
            if (Build.VERSION.SDK_INT >= 28 && (drawable instanceof AnimatedImageDrawable)) {
                ((AnimatedImageDrawable) drawable).start();
            }
        }
    }
}
