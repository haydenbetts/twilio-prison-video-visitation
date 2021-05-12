package com.urbanairship.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.ImageDecoder;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import com.urbanairship.Logger;
import java.io.File;
import java.io.IOException;
import java.net.URL;

public class ImageUtils {

    private interface ImageProcessor<T> {
        T onProcessFile(File file) throws IOException;
    }

    public static class DrawableResult {
        public final long bytes;
        public final Drawable drawable;

        private DrawableResult(Drawable drawable2, long j) {
            this.drawable = drawable2;
            this.bytes = j;
        }
    }

    public static DrawableResult fetchScaledDrawable(Context context, URL url, final int i, final int i2) throws IOException {
        if (Build.VERSION.SDK_INT >= 28) {
            return (DrawableResult) fetchImage(context, url, new ImageProcessor<DrawableResult>() {
                public DrawableResult onProcessFile(File file) throws IOException {
                    long j;
                    Drawable decodeDrawable = ImageDecoder.decodeDrawable(ImageDecoder.createSource(file), new ImageDecoder.OnHeaderDecodedListener() {
                        public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                            imageDecoder.setTargetSize(i, i2);
                            imageDecoder.setTargetSampleSize(ImageUtils.calculateInSampleSize(imageInfo.getSize().getWidth(), imageInfo.getSize().getHeight(), i, i2));
                        }
                    });
                    if (decodeDrawable instanceof BitmapDrawable) {
                        j = (long) ((BitmapDrawable) decodeDrawable).getBitmap().getByteCount();
                    } else {
                        j = file.length();
                    }
                    return new DrawableResult(decodeDrawable, j);
                }
            });
        }
        Bitmap fetchScaledBitmap = fetchScaledBitmap(context, url, i, i2);
        if (fetchScaledBitmap == null) {
            return null;
        }
        return new DrawableResult(new BitmapDrawable(context.getResources(), fetchScaledBitmap), (long) fetchScaledBitmap.getByteCount());
    }

    public static Bitmap fetchScaledBitmap(Context context, URL url, final int i, final int i2) throws IOException {
        Bitmap bitmap = (Bitmap) fetchImage(context, url, new ImageProcessor<Bitmap>() {
            public Bitmap onProcessFile(File file) throws IOException {
                if (Build.VERSION.SDK_INT >= 28) {
                    return ImageDecoder.decodeBitmap(ImageDecoder.createSource(file), new ImageDecoder.OnHeaderDecodedListener() {
                        public void onHeaderDecoded(ImageDecoder imageDecoder, ImageDecoder.ImageInfo imageInfo, ImageDecoder.Source source) {
                            imageDecoder.setTargetSize(i, i2);
                            imageDecoder.setTargetSampleSize(ImageUtils.calculateInSampleSize(imageInfo.getSize().getWidth(), imageInfo.getSize().getHeight(), i, i2));
                        }
                    });
                }
                BitmapFactory.Options options = new BitmapFactory.Options();
                options.inJustDecodeBounds = true;
                BitmapFactory.decodeFile(file.getAbsolutePath(), options);
                options.inSampleSize = ImageUtils.calculateInSampleSize(options.outWidth, options.outHeight, i, i2);
                options.inJustDecodeBounds = false;
                return BitmapFactory.decodeFile(file.getAbsolutePath(), options);
            }
        });
        if (bitmap != null) {
            Logger.debug("ImageUtils - Fetched image from: %s. Original image size: %dx%d. Requested image size: %dx%d. Bitmap size: %dx%d.", url, Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(i), Integer.valueOf(i2), Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()));
        }
        return bitmap;
    }

    public static int calculateInSampleSize(int i, int i2, int i3, int i4) {
        int i5 = 1;
        if (i2 > i4 || i > i3) {
            int i6 = i2 / 2;
            int i7 = i / 2;
            while (i6 / i5 > i4 && i7 / i5 > i3) {
                i5 *= 2;
            }
        }
        return i5;
    }

    /* JADX WARNING: Removed duplicated region for block: B:42:0x0098  */
    /* JADX WARNING: Removed duplicated region for block: B:43:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x00b5  */
    /* JADX WARNING: Removed duplicated region for block: B:52:0x00bd  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static <T> T fetchImage(android.content.Context r7, java.net.URL r8, com.urbanairship.util.ImageUtils.ImageProcessor<T> r9) throws java.io.IOException {
        /*
            java.lang.String r0 = "ImageUtils - Deleted temp file: %s"
            java.lang.String r1 = "ImageUtils - Failed to delete temp file: %s"
            r2 = 1
            java.lang.Object[] r3 = new java.lang.Object[r2]
            r4 = 0
            r3[r4] = r8
            java.lang.String r5 = "ImageUtils - Fetching image from: %s"
            com.urbanairship.Logger.verbose(r5, r3)
            r3 = 0
            java.lang.String r5 = r8.toString()     // Catch:{ URISyntaxException -> 0x0083, all -> 0x0080 }
            boolean r5 = android.webkit.URLUtil.isFileUrl(r5)     // Catch:{ URISyntaxException -> 0x0083, all -> 0x0080 }
            if (r5 == 0) goto L_0x0025
            java.io.File r7 = new java.io.File     // Catch:{ URISyntaxException -> 0x0083, all -> 0x0080 }
            java.net.URI r5 = r8.toURI()     // Catch:{ URISyntaxException -> 0x0083, all -> 0x0080 }
            r7.<init>(r5)     // Catch:{ URISyntaxException -> 0x0083, all -> 0x0080 }
            r5 = 0
            goto L_0x005b
        L_0x0025:
            java.lang.String r5 = "ua_"
            java.lang.String r6 = ".temp"
            java.io.File r7 = r7.getCacheDir()     // Catch:{ URISyntaxException -> 0x0083, all -> 0x0080 }
            java.io.File r7 = java.io.File.createTempFile(r5, r6, r7)     // Catch:{ URISyntaxException -> 0x0083, all -> 0x0080 }
            com.urbanairship.util.FileUtils$DownloadResult r5 = com.urbanairship.util.FileUtils.downloadFile(r8, r7)     // Catch:{ URISyntaxException -> 0x007e, all -> 0x0079 }
            boolean r5 = r5.isSuccess     // Catch:{ URISyntaxException -> 0x007e, all -> 0x0079 }
            if (r5 != 0) goto L_0x005a
            java.lang.String r9 = "ImageUtils - Failed to fetch image from: %s"
            java.lang.Object[] r5 = new java.lang.Object[r2]     // Catch:{ URISyntaxException -> 0x007e, all -> 0x0079 }
            r5[r4] = r8     // Catch:{ URISyntaxException -> 0x007e, all -> 0x0079 }
            com.urbanairship.Logger.verbose(r9, r5)     // Catch:{ URISyntaxException -> 0x007e, all -> 0x0079 }
            if (r7 == 0) goto L_0x0059
            boolean r8 = r7.delete()
            if (r8 == 0) goto L_0x0052
            java.lang.Object[] r8 = new java.lang.Object[r2]
            r8[r4] = r7
            com.urbanairship.Logger.verbose(r0, r8)
            goto L_0x0059
        L_0x0052:
            java.lang.Object[] r8 = new java.lang.Object[r2]
            r8[r4] = r7
            com.urbanairship.Logger.verbose(r1, r8)
        L_0x0059:
            return r3
        L_0x005a:
            r5 = 1
        L_0x005b:
            java.lang.Object r8 = r9.onProcessFile(r7)     // Catch:{ URISyntaxException -> 0x0085 }
            if (r5 == 0) goto L_0x0078
            if (r7 == 0) goto L_0x0078
            boolean r9 = r7.delete()
            if (r9 == 0) goto L_0x0071
            java.lang.Object[] r9 = new java.lang.Object[r2]
            r9[r4] = r7
            com.urbanairship.Logger.verbose(r0, r9)
            goto L_0x0078
        L_0x0071:
            java.lang.Object[] r9 = new java.lang.Object[r2]
            r9[r4] = r7
            com.urbanairship.Logger.verbose(r1, r9)
        L_0x0078:
            return r8
        L_0x0079:
            r8 = move-exception
            r3 = r7
            r7 = r8
            r5 = 1
            goto L_0x00ab
        L_0x007e:
            r5 = 1
            goto L_0x0085
        L_0x0080:
            r7 = move-exception
            r5 = 0
            goto L_0x00ab
        L_0x0083:
            r7 = r3
            r5 = 0
        L_0x0085:
            java.lang.String r9 = "ImageUtils - Invalid URL: %s "
            java.lang.Object[] r6 = new java.lang.Object[r2]     // Catch:{ all -> 0x00a8 }
            r6[r4] = r8     // Catch:{ all -> 0x00a8 }
            com.urbanairship.Logger.error(r9, r6)     // Catch:{ all -> 0x00a8 }
            if (r5 == 0) goto L_0x00a7
            if (r7 == 0) goto L_0x00a7
            boolean r8 = r7.delete()
            if (r8 == 0) goto L_0x00a0
            java.lang.Object[] r8 = new java.lang.Object[r2]
            r8[r4] = r7
            com.urbanairship.Logger.verbose(r0, r8)
            goto L_0x00a7
        L_0x00a0:
            java.lang.Object[] r8 = new java.lang.Object[r2]
            r8[r4] = r7
            com.urbanairship.Logger.verbose(r1, r8)
        L_0x00a7:
            return r3
        L_0x00a8:
            r8 = move-exception
            r3 = r7
            r7 = r8
        L_0x00ab:
            if (r5 == 0) goto L_0x00c4
            if (r3 == 0) goto L_0x00c4
            boolean r8 = r3.delete()
            if (r8 == 0) goto L_0x00bd
            java.lang.Object[] r8 = new java.lang.Object[r2]
            r8[r4] = r3
            com.urbanairship.Logger.verbose(r0, r8)
            goto L_0x00c4
        L_0x00bd:
            java.lang.Object[] r8 = new java.lang.Object[r2]
            r8[r4] = r3
            com.urbanairship.Logger.verbose(r1, r8)
        L_0x00c4:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.util.ImageUtils.fetchImage(android.content.Context, java.net.URL, com.urbanairship.util.ImageUtils$ImageProcessor):java.lang.Object");
    }
}
