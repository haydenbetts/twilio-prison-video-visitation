package com.forasoft.homewavvisitor.model;

import com.forasoft.homewavvisitor.model.data.Inmate;
import java.util.concurrent.Callable;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\n\u0012\u0004\u0012\u00020\u0002\u0018\u00010\u0001H\n¢\u0006\u0002\b\u0003"}, d2 = {"<anonymous>", "", "Lcom/forasoft/homewavvisitor/model/Video;", "call"}, k = 3, mv = {1, 1, 16})
/* compiled from: MediaStoreMediaLoader.kt */
final class MediaStoreMediaLoader$getVideos$1<V> implements Callable<T> {
    final /* synthetic */ Inmate $inmate;
    final /* synthetic */ MediaStoreMediaLoader this$0;

    MediaStoreMediaLoader$getVideos$1(MediaStoreMediaLoader mediaStoreMediaLoader, Inmate inmate) {
        this.this$0 = mediaStoreMediaLoader;
        this.$inmate = inmate;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:27:0x00a2, code lost:
        r2 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:28:0x00a3, code lost:
        kotlin.io.CloseableKt.closeFinally(r0, r1);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:29:0x00a6, code lost:
        throw r2;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.forasoft.homewavvisitor.model.Video> call() {
        /*
            r8 = this;
            com.forasoft.homewavvisitor.model.MediaStoreMediaLoader r0 = r8.this$0
            android.content.Context r0 = r0.context
            android.content.ContentResolver r1 = r0.getContentResolver()
            android.net.Uri r2 = android.provider.MediaStore.Video.Media.EXTERNAL_CONTENT_URI
            java.lang.String r0 = "_data"
            java.lang.String r3 = "duration"
            java.lang.String r4 = "mime_type"
            java.lang.String[] r3 = new java.lang.String[]{r0, r3, r4}
            r4 = 0
            r5 = 0
            r6 = 0
            android.database.Cursor r0 = r1.query(r2, r3, r4, r5, r6)
            java.io.Closeable r0 = (java.io.Closeable) r0
            r1 = 0
            r2 = r1
            java.lang.Throwable r2 = (java.lang.Throwable) r2
            r3 = r0
            android.database.Cursor r3 = (android.database.Cursor) r3     // Catch:{ all -> 0x00a0 }
            if (r3 == 0) goto L_0x0031
            com.forasoft.homewavvisitor.model.MediaStoreMediaLoader$getVideos$1$1$1 r4 = com.forasoft.homewavvisitor.model.MediaStoreMediaLoader$getVideos$1$1$1.INSTANCE     // Catch:{ all -> 0x00a0 }
            kotlin.jvm.functions.Function1 r4 = (kotlin.jvm.functions.Function1) r4     // Catch:{ all -> 0x00a0 }
            java.util.List r3 = com.forasoft.homewavvisitor.extension.CommonKt.map(r3, r4)     // Catch:{ all -> 0x00a0 }
            goto L_0x0032
        L_0x0031:
            r3 = r1
        L_0x0032:
            kotlin.io.CloseableKt.closeFinally(r0, r2)
            if (r3 == 0) goto L_0x009f
            java.lang.Iterable r3 = (java.lang.Iterable) r3
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r1 = r3.iterator()
        L_0x0044:
            boolean r2 = r1.hasNext()
            if (r2 == 0) goto L_0x0062
            java.lang.Object r2 = r1.next()
            r3 = r2
            com.forasoft.homewavvisitor.model.Video r3 = (com.forasoft.homewavvisitor.model.Video) r3
            java.lang.String r3 = r3.getMimeType()
            java.lang.String r4 = "video/mp4"
            boolean r3 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r3, (java.lang.Object) r4)
            if (r3 == 0) goto L_0x0044
            r0.add(r2)
            goto L_0x0044
        L_0x0062:
            java.util.List r0 = (java.util.List) r0
            java.lang.Iterable r0 = (java.lang.Iterable) r0
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.util.Collection r1 = (java.util.Collection) r1
            java.util.Iterator r0 = r0.iterator()
        L_0x0071:
            boolean r2 = r0.hasNext()
            if (r2 == 0) goto L_0x009d
            java.lang.Object r2 = r0.next()
            r3 = r2
            com.forasoft.homewavvisitor.model.Video r3 = (com.forasoft.homewavvisitor.model.Video) r3
            long r3 = r3.getDuration()
            r5 = 1000(0x3e8, float:1.401E-42)
            long r5 = (long) r5
            long r3 = r3 / r5
            com.forasoft.homewavvisitor.model.data.Inmate r5 = r8.$inmate
            java.lang.String r5 = r5.getPrison_max_video_message_length()
            long r5 = java.lang.Long.parseLong(r5)
            int r7 = (r3 > r5 ? 1 : (r3 == r5 ? 0 : -1))
            if (r7 >= 0) goto L_0x0096
            r3 = 1
            goto L_0x0097
        L_0x0096:
            r3 = 0
        L_0x0097:
            if (r3 == 0) goto L_0x0071
            r1.add(r2)
            goto L_0x0071
        L_0x009d:
            java.util.List r1 = (java.util.List) r1
        L_0x009f:
            return r1
        L_0x00a0:
            r1 = move-exception
            throw r1     // Catch:{ all -> 0x00a2 }
        L_0x00a2:
            r2 = move-exception
            kotlin.io.CloseableKt.closeFinally(r0, r1)
            throw r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.MediaStoreMediaLoader$getVideos$1.call():java.util.List");
    }
}
