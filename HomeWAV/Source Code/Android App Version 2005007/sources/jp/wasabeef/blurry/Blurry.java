package jp.wasabeef.blurry;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import jp.wasabeef.blurry.internal.Blur;
import jp.wasabeef.blurry.internal.BlurFactor;
import jp.wasabeef.blurry.internal.BlurTask;
import jp.wasabeef.blurry.internal.Helper;

public class Blurry {
    /* access modifiers changed from: private */
    public static final String TAG = "Blurry";

    public static Composer with(Context context) {
        return new Composer(context);
    }

    public static void delete(ViewGroup viewGroup) {
        View findViewWithTag = viewGroup.findViewWithTag(TAG);
        if (findViewWithTag != null) {
            viewGroup.removeView(findViewWithTag);
        }
    }

    public static class Composer {
        private boolean animate;
        private boolean async;
        private View blurredView;
        private Context context;
        private int duration = 300;
        private BlurFactor factor;
        /* access modifiers changed from: private */
        public ImageComposer.ImageComposerListener listener;

        public Composer(Context context2) {
            this.context = context2;
            View view = new View(context2);
            this.blurredView = view;
            view.setTag(Blurry.TAG);
            this.factor = new BlurFactor();
        }

        public Composer radius(int i) {
            this.factor.radius = i;
            return this;
        }

        public Composer sampling(int i) {
            this.factor.sampling = i;
            return this;
        }

        public Composer color(int i) {
            this.factor.color = i;
            return this;
        }

        public Composer async() {
            this.async = true;
            return this;
        }

        public Composer async(ImageComposer.ImageComposerListener imageComposerListener) {
            this.async = true;
            this.listener = imageComposerListener;
            return this;
        }

        public Composer animate() {
            this.animate = true;
            return this;
        }

        public Composer animate(int i) {
            this.animate = true;
            this.duration = i;
            return this;
        }

        public ImageComposer capture(View view) {
            return new ImageComposer(this.context, view, this.factor, this.async, this.listener);
        }

        public BitmapComposer from(Bitmap bitmap) {
            return new BitmapComposer(this.context, bitmap, this.factor, this.async, this.listener);
        }

        public void onto(final ViewGroup viewGroup) {
            this.factor.width = viewGroup.getMeasuredWidth();
            this.factor.height = viewGroup.getMeasuredHeight();
            if (this.async) {
                new BlurTask(viewGroup, this.factor, new BlurTask.Callback() {
                    public void done(BitmapDrawable bitmapDrawable) {
                        Composer.this.addView(viewGroup, bitmapDrawable);
                        if (Composer.this.listener != null) {
                            Composer.this.listener.onImageReady(bitmapDrawable);
                        }
                    }
                }).execute();
            } else {
                addView(viewGroup, new BitmapDrawable(this.context.getResources(), Blur.of(viewGroup, this.factor)));
            }
        }

        /* access modifiers changed from: private */
        public void addView(ViewGroup viewGroup, Drawable drawable) {
            Helper.setBackground(this.blurredView, drawable);
            viewGroup.addView(this.blurredView);
            if (this.animate) {
                Helper.animate(this.blurredView, this.duration);
            }
        }
    }

    public static class BitmapComposer {
        private boolean async;
        private Bitmap bitmap;
        private Context context;
        private BlurFactor factor;
        /* access modifiers changed from: private */
        public ImageComposer.ImageComposerListener listener;

        public BitmapComposer(Context context2, Bitmap bitmap2, BlurFactor blurFactor, boolean z, ImageComposer.ImageComposerListener imageComposerListener) {
            this.context = context2;
            this.bitmap = bitmap2;
            this.factor = blurFactor;
            this.async = z;
            this.listener = imageComposerListener;
        }

        public void into(final ImageView imageView) {
            this.factor.width = this.bitmap.getWidth();
            this.factor.height = this.bitmap.getHeight();
            if (this.async) {
                new BlurTask(imageView.getContext(), this.bitmap, this.factor, new BlurTask.Callback() {
                    public void done(BitmapDrawable bitmapDrawable) {
                        if (BitmapComposer.this.listener == null) {
                            imageView.setImageDrawable(bitmapDrawable);
                        } else {
                            BitmapComposer.this.listener.onImageReady(bitmapDrawable);
                        }
                    }
                }).execute();
            } else {
                imageView.setImageDrawable(new BitmapDrawable(this.context.getResources(), Blur.of(imageView.getContext(), this.bitmap, this.factor)));
            }
        }
    }

    public static class ImageComposer {
        private boolean async;
        private View capture;
        private Context context;
        private BlurFactor factor;
        /* access modifiers changed from: private */
        public ImageComposerListener listener;

        public interface ImageComposerListener {
            void onImageReady(BitmapDrawable bitmapDrawable);
        }

        public ImageComposer(Context context2, View view, BlurFactor blurFactor, boolean z, ImageComposerListener imageComposerListener) {
            this.context = context2;
            this.capture = view;
            this.factor = blurFactor;
            this.async = z;
            this.listener = imageComposerListener;
        }

        public void into(final ImageView imageView) {
            this.factor.width = this.capture.getMeasuredWidth();
            this.factor.height = this.capture.getMeasuredHeight();
            if (this.async) {
                new BlurTask(this.capture, this.factor, new BlurTask.Callback() {
                    public void done(BitmapDrawable bitmapDrawable) {
                        if (ImageComposer.this.listener == null) {
                            imageView.setImageDrawable(bitmapDrawable);
                        } else {
                            ImageComposer.this.listener.onImageReady(bitmapDrawable);
                        }
                    }
                }).execute();
            } else {
                imageView.setImageDrawable(new BitmapDrawable(this.context.getResources(), Blur.of(this.capture, this.factor)));
            }
        }
    }
}
