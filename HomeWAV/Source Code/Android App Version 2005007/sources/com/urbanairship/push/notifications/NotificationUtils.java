package com.urbanairship.push.notifications;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.WindowManager;
import com.urbanairship.AirshipExecutors;
import com.urbanairship.Logger;
import com.urbanairship.util.ImageUtils;
import java.net.URL;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class NotificationUtils {
    private static final int BIG_IMAGE_HEIGHT_DP = 240;
    private static final double BIG_IMAGE_SCREEN_WIDTH_PERCENT = 0.75d;
    private static final long BIG_PICTURE_TIMEOUT_SECONDS = 7;

    public static Bitmap fetchBigImage(final Context context, final URL url) {
        Logger.debug("Fetching notification image at URL: %s", url);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getMetrics(displayMetrics);
        final int max = (int) (((double) Math.max(displayMetrics.widthPixels, displayMetrics.heightPixels)) * BIG_IMAGE_SCREEN_WIDTH_PERCENT);
        final int applyDimension = (int) TypedValue.applyDimension(1, 240.0f, displayMetrics);
        Future submit = AirshipExecutors.THREAD_POOL_EXECUTOR.submit(new Callable<Bitmap>() {
            public Bitmap call() throws Exception {
                return ImageUtils.fetchScaledBitmap(context, url, max, applyDimension);
            }
        });
        try {
            return (Bitmap) submit.get(7, TimeUnit.SECONDS);
        } catch (InterruptedException | ExecutionException e) {
            Logger.error("Failed to create big picture style, unable to fetch image: %s", e);
            return null;
        } catch (TimeoutException unused) {
            submit.cancel(true);
            Logger.error("Big picture took longer than %s seconds to fetch.", 7L);
            return null;
        }
    }
}
