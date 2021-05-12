package com.forasoft.homewavvisitor.model;

import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/forasoft/homewavvisitor/model/Image;", "call"}, k = 3, mv = {1, 1, 16})
/* compiled from: MediaStoreMediaLoader.kt */
final class MediaStoreMediaLoader$getImages$1<V> implements Callable<T> {
    final /* synthetic */ MediaStoreMediaLoader this$0;

    MediaStoreMediaLoader$getImages$1(MediaStoreMediaLoader mediaStoreMediaLoader) {
        this.this$0 = mediaStoreMediaLoader;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0074, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0075, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0078, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.forasoft.homewavvisitor.model.Image> call() {
        /*
            r7 = this;
            com.forasoft.homewavvisitor.model.MediaStoreMediaLoader r0 = r7.this$0
            android.content.Context r0 = r0.context
            android.content.ContentResolver r1 = r0.getContentResolver()
            android.net.Uri r2 = android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI
            java.lang.String r0 = "_data"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            r2 = r1
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = r0
            android.database.Cursor r3 = (android.database.Cursor) r3     // Catch:{ all -> 0x0072 }
            if (r3 == 0) goto L_0x002d
            com.forasoft.homewavvisitor.model.MediaStoreMediaLoader$getImages$1$1$1 r4 = com.forasoft.homewavvisitor.model.MediaStoreMediaLoader$getImages$1$1$1.INSTANCE     // Catch:{ all -> 0x0072 }
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4     // Catch:{ all -> 0x0072 }
            java.util.List r3 = com.forasoft.homewavvisitor.extension.CommonKt.map(r3, r4)     // Catch:{ all -> 0x0072 }
            goto L_0x002e
        L_0x002d:
            r3 = r1
        L_0x002e:
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            if (r3 == 0) goto L_0x0071
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r0 = new java.util.ArrayList
            r2 = 10
            int r2 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r3, r2)
            r0.<init>(r2)
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r2 = r3.iterator()
        L_0x0046:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x006e
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            com.forasoft.homewavvisitor.model.Image r4 = new com.forasoft.homewavvisitor.model.Image
            java.io.File r5 = new java.io.File
            r5.<init>(r3)
            android.net.Uri r3 = android.net.Uri.fromFile(r5)
            java.lang.String r3 = r3.toString()
            java.lang.String r5 = "Uri.fromFile(File(it)).toString()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r3, r5)
            r5 = 2
            r4.<init>(r3, r1, r5, r1)
            r0.add(r4)
            goto L_0x0046
        L_0x006e:
            r1 = r0
            java.util.List r1 = (java.util.List) r1
        L_0x0071:
            return r1
        L_0x0072:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x0074 }
        L_0x0074:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.MediaStoreMediaLoader$getImages$1.call():java.util.List");
    }
}
