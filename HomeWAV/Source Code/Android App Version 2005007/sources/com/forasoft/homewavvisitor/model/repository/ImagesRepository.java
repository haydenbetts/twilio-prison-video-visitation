package com.forasoft.homewavvisitor.model.repository;

import android.content.Context;
import android.net.Uri;
import androidx.exifinterface.media.ExifInterface;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0002J\"\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\rR\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/model/repository/ImagesRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getImageOrientation", "", "path", "", "getScaledImage", "Landroid/net/Uri;", "uri", "width", "", "height", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ImagesRepository.kt */
public final class ImagesRepository {
    private final Context context;

    @Inject
    public ImagesRepository(Context context2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        this.context = context2;
    }

    public static /* synthetic */ Uri getScaledImage$default(ImagesRepository imagesRepository, Uri uri, int i, int i2, int i3, Object obj) {
        if ((i3 & 2) != 0) {
            i = 1000;
        }
        if ((i3 & 4) != 0) {
            i2 = 1000;
        }
        return imagesRepository.getScaledImage(uri, i, i2);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:21:0x00ca, code lost:
        r13 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x00cb, code lost:
        kotlin.io.CloseableKt.closeFinally(r11, r12);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x00ce, code lost:
        throw r13;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final android.net.Uri getScaledImage(android.net.Uri r11, int r12, int r13) {
        /*
            r10 = this;
            java.lang.String r0 = "uri"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r11, r0)
            java.lang.String r0 = "http"
            java.lang.String r1 = "https"
            java.lang.String[] r0 = new java.lang.String[]{r0, r1}
            java.util.Set r0 = kotlin.collections.SetsKt.setOf(r0)
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.lang.String r1 = r11.getScheme()
            boolean r0 = kotlin.collections.CollectionsKt.contains(r0, r1)
            if (r0 == 0) goto L_0x001f
            return r11
        L_0x001f:
            android.content.Context r0 = r10.context
            java.lang.String r0 = com.forasoft.homewavvisitor.extension.PathUtil.pathForUri(r0, r11)
            java.lang.String r1 = "path"
            if (r0 == 0) goto L_0x002a
            goto L_0x0058
        L_0x002a:
            r0 = r10
            com.forasoft.homewavvisitor.model.repository.ImagesRepository r0 = (com.forasoft.homewavvisitor.model.repository.ImagesRepository) r0
            android.content.Context r0 = r0.context
            android.content.ContentResolver r2 = r0.getContentResolver()
            r4 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r3 = r11
            android.database.Cursor r11 = r2.query(r3, r4, r5, r6, r7)
            java.io.Closeable r11 = (java.io.Closeable) r11
            r0 = 0
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            r2 = r11
            android.database.Cursor r2 = (android.database.Cursor) r2     // Catch:{ all -> 0x00c8 }
            if (r2 != 0) goto L_0x0049
            kotlin.jvm.internal.Intrinsics.throwNpe()     // Catch:{ all -> 0x00c8 }
        L_0x0049:
            r2.moveToFirst()     // Catch:{ all -> 0x00c8 }
            int r3 = r2.getColumnIndex(r1)     // Catch:{ all -> 0x00c8 }
            java.lang.String r2 = r2.getString(r3)     // Catch:{ all -> 0x00c8 }
            kotlin.io.CloseableKt.closeFinally(r11, r0)
            r0 = r2
        L_0x0058:
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            float r11 = r10.getImageOrientation(r0)
            android.graphics.BitmapFactory$Options r1 = new android.graphics.BitmapFactory$Options
            r1.<init>()
            r2 = 1
            r1.inJustDecodeBounds = r2
            android.graphics.BitmapFactory.decodeFile(r0, r1)
            int r3 = r1.outWidth
            int r4 = r1.outHeight
            if (r3 <= r12) goto L_0x0078
            if (r4 <= r13) goto L_0x0078
            int r3 = r3 / r12
            int r4 = r4 / r13
            int r2 = java.lang.Math.min(r3, r4)
        L_0x0078:
            r12 = 0
            r1.inJustDecodeBounds = r12
            r1.inSampleSize = r2
            android.graphics.Bitmap r3 = android.graphics.BitmapFactory.decodeFile(r0, r1)
            android.graphics.Matrix r8 = new android.graphics.Matrix
            r8.<init>()
            r8.postRotate(r11)
            r4 = 0
            r5 = 0
            java.lang.String r11 = "bitmap"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r11)
            int r6 = r3.getWidth()
            int r7 = r3.getHeight()
            r9 = 1
            android.graphics.Bitmap r11 = android.graphics.Bitmap.createBitmap(r3, r4, r5, r6, r7, r8, r9)
            java.io.File r12 = new java.io.File
            android.content.Context r13 = r10.context
            java.io.File r13 = r13.getFilesDir()
            java.util.UUID r0 = java.util.UUID.randomUUID()
            java.lang.String r0 = r0.toString()
            r12.<init>(r13, r0)
            java.io.FileOutputStream r13 = new java.io.FileOutputStream
            r13.<init>(r12)
            android.graphics.Bitmap$CompressFormat r0 = android.graphics.Bitmap.CompressFormat.PNG
            r1 = 100
            java.io.OutputStream r13 = (java.io.OutputStream) r13
            r11.compress(r0, r1, r13)
            android.net.Uri r11 = android.net.Uri.fromFile(r12)
            java.lang.String r12 = "Uri.fromFile(file)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r11, r12)
            return r11
        L_0x00c8:
            r12 = move-exception
            throw r12     // Catch:{ all -> 0x00ca }
        L_0x00ca:
            r13 = move-exception
            kotlin.io.CloseableKt.closeFinally(r11, r12)
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.repository.ImagesRepository.getScaledImage(android.net.Uri, int, int):android.net.Uri");
    }

    private final float getImageOrientation(String str) {
        int attributeInt = new ExifInterface(str).getAttributeInt(ExifInterface.TAG_ORIENTATION, 1);
        if (attributeInt == 3) {
            return 180.0f;
        }
        if (attributeInt != 6) {
            return attributeInt != 8 ? 0.0f : 270.0f;
        }
        return 90.0f;
    }
}
