package com.forasoft.homewavvisitor.model;

import android.content.Context;
import androidx.work.Worker;
import androidx.work.WorkerParameters;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \t2\u00020\u0001:\u0001\tB\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/model/UploadWorker;", "Landroidx/work/Worker;", "context", "Landroid/content/Context;", "params", "Landroidx/work/WorkerParameters;", "(Landroid/content/Context;Landroidx/work/WorkerParameters;)V", "doWork", "Landroidx/work/ListenableWorker$Result;", "Companion", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: UploadWorker.kt */
public final class UploadWorker extends Worker {
    public static final Companion Companion = new Companion((DefaultConstructorMarker) null);
    public static final String KEY_BODY = "body";
    public static final String KEY_ELAPSED_TIME = "elapsed_time";
    public static final String KEY_ERROR_MESSAGE = "error_message";
    public static final String KEY_GALLERY_URL = "gallery_url";
    public static final String KEY_INMATE_ID = "inmate_id";
    public static final String KEY_PUB_ID = "pubid";
    private static final String KEY_SENDER = "sender";
    public static final String KEY_TYPE = "type";
    public static final String KEY_VISITOR_ID = "visitor_id";
    public static final String TAG_IMAGE = "image";
    public static final String TAG_RESIZE = "resize";
    public static final String TAG_TEXT = "text";
    public static final String TAG_VIDEO = "video";

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    public UploadWorker(Context context, WorkerParameters workerParameters) {
        super(context, workerParameters);
        Intrinsics.checkParameterIsNotNull(context, "context");
        Intrinsics.checkParameterIsNotNull(workerParameters, "params");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0055, code lost:
        r10 = (com.forasoft.homewavvisitor.model.data.MessageInfo) r10.getBody();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:53:0x03eb, code lost:
        if (r0.getOk() == false) goto L_0x03f7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:54:0x03ed, code lost:
        r0 = androidx.work.ListenableWorker.Result.success();
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, "Result.success()");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:55:0x03f7, code lost:
        r0 = androidx.work.ListenableWorker.Result.failure(new androidx.work.Data.Builder().putString(KEY_ERROR_MESSAGE, r0.getError()).build());
        kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, "Result.failure(Data.Buil… response.error).build())");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:59:0x0427, code lost:
        throw new java.lang.UnsupportedOperationException();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:68:?, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:69:?, code lost:
        return r0;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public androidx.work.ListenableWorker.Result doWork() {
        /*
            r28 = this;
            r0 = 1
            java.lang.Object[] r1 = new java.lang.Object[r0]
            java.lang.String r2 = "server scope"
            r3 = 0
            r1[r3] = r2
            toothpick.Scope r1 = toothpick.Toothpick.openScopes(r1)
            java.lang.Class<com.forasoft.homewavvisitor.model.server.apis.HomewavApi> r2 = com.forasoft.homewavvisitor.model.server.apis.HomewavApi.class
            java.lang.Object r1 = r1.getInstance(r2)
            r4 = r1
            com.forasoft.homewavvisitor.model.server.apis.HomewavApi r4 = (com.forasoft.homewavvisitor.model.server.apis.HomewavApi) r4
            androidx.work.Data r1 = r28.getInputData()
            java.lang.String r2 = "visitor_id"
            java.lang.String r1 = r1.getString(r2)
            java.lang.String r5 = "Required value was null."
            if (r1 == 0) goto L_0x0452
            androidx.work.Data r6 = r28.getInputData()
            java.lang.String r7 = "inmate_id"
            java.lang.String r6 = r6.getString(r7)
            if (r6 == 0) goto L_0x0444
            androidx.work.Data r8 = r28.getInputData()
            java.lang.String r9 = "type"
            java.lang.String r8 = r8.getString(r9)
            if (r8 == 0) goto L_0x0436
            androidx.work.Data r10 = r28.getInputData()
            java.lang.String r11 = "pubid"
            java.lang.String r10 = r10.getString(r11)
            if (r10 != 0) goto L_0x0064
            io.reactivex.Single r10 = r4.initMessage(r1, r6)
            java.lang.Object r10 = r10.blockingGet()
            com.forasoft.homewavvisitor.model.server.response.ApiResponse r10 = (com.forasoft.homewavvisitor.model.server.response.ApiResponse) r10
            if (r10 == 0) goto L_0x0062
            java.lang.Object r10 = r10.getBody()
            com.forasoft.homewavvisitor.model.data.MessageInfo r10 = (com.forasoft.homewavvisitor.model.data.MessageInfo) r10
            if (r10 == 0) goto L_0x0062
            java.lang.String r10 = r10.getPubId()
            goto L_0x006c
        L_0x0062:
            r10 = 0
            goto L_0x006c
        L_0x0064:
            androidx.work.Data r10 = r28.getInputData()
            java.lang.String r10 = r10.getString(r11)
        L_0x006c:
            if (r10 == 0) goto L_0x0428
            int r12 = r8.hashCode()
            java.lang.String r14 = "0"
            java.lang.String r15 = ""
            java.lang.String r13 = "gallery_url"
            java.lang.String r3 = "visitor"
            java.lang.String r0 = "sender"
            r16 = 6
            r17 = 5
            r18 = 4
            r19 = 3
            r20 = 2
            r21 = r5
            java.lang.String r5 = "elapsed_time"
            r22 = r14
            java.lang.String r14 = "body"
            switch(r12) {
                case 102340: goto L_0x0378;
                case 3556653: goto L_0x030a;
                case 100313435: goto L_0x01f1;
                case 112202875: goto L_0x0094;
                default: goto L_0x0092;
            }
        L_0x0092:
            goto L_0x0420
        L_0x0094:
            java.lang.String r12 = "video"
            boolean r12 = r8.equals(r12)
            if (r12 == 0) goto L_0x0420
            androidx.work.Data r12 = r28.getInputData()
            java.lang.String r12 = r12.getString(r13)
            r23 = r13
            androidx.work.Data r13 = r28.getInputData()
            java.lang.String r13 = r13.getString(r5)
            if (r13 == 0) goto L_0x01e5
            if (r12 != 0) goto L_0x00fa
            r12 = 7
            kotlin.Pair[] r12 = new kotlin.Pair[r12]
            kotlin.Pair r10 = kotlin.TuplesKt.to(r11, r10)
            r11 = 0
            r12[r11] = r10
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 1
            r12[r2] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r7, r6)
            r12[r20] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r5, r13)
            r12[r19] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r15)
            r12[r18] = r1
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r3)
            r12[r17] = r0
            kotlin.Pair r0 = kotlin.TuplesKt.to(r9, r8)
            r12[r16] = r0
            java.util.Map r0 = kotlin.collections.MapsKt.mapOf(r12)
            java.util.Map r5 = com.forasoft.homewavvisitor.extension.CommonKt.asPartMap(r0)
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            io.reactivex.Single r0 = com.forasoft.homewavvisitor.model.server.apis.HomewavApi.DefaultImpls.sendMessage$default(r4, r5, r6, r7, r8, r9)
            java.lang.Object r0 = r0.blockingGet()
            com.forasoft.homewavvisitor.model.server.response.Response r0 = (com.forasoft.homewavvisitor.model.server.response.Response) r0
            goto L_0x03e7
        L_0x00fa:
            r24 = r12
            java.lang.String r12 = "http"
            r25 = r8
            java.lang.String r8 = "https"
            java.lang.String[] r8 = new java.lang.String[]{r12, r8}
            java.util.Set r8 = kotlin.collections.SetsKt.setOf(r8)
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            android.net.Uri r12 = android.net.Uri.parse(r24)
            r26 = r13
            java.lang.String r13 = "Uri.parse(galleryUrl)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r12, r13)
            java.lang.String r12 = r12.getScheme()
            boolean r8 = kotlin.collections.CollectionsKt.contains(r8, r12)
            if (r8 == 0) goto L_0x0178
            r8 = 8
            kotlin.Pair[] r8 = new kotlin.Pair[r8]
            kotlin.Pair r10 = kotlin.TuplesKt.to(r11, r10)
            r11 = 0
            r8[r11] = r10
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 1
            r8[r2] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r7, r6)
            r8[r20] = r1
            r13 = r23
            r12 = r24
            kotlin.Pair r1 = kotlin.TuplesKt.to(r13, r12)
            r8[r19] = r1
            r12 = r22
            kotlin.Pair r1 = kotlin.TuplesKt.to(r5, r12)
            r8[r18] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r15)
            r8[r17] = r1
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r3)
            r8[r16] = r0
            java.lang.String r0 = "s3_video"
            kotlin.Pair r0 = kotlin.TuplesKt.to(r9, r0)
            r1 = 7
            r8[r1] = r0
            java.util.Map r0 = kotlin.collections.MapsKt.mapOf(r8)
            java.util.Map r5 = com.forasoft.homewavvisitor.extension.CommonKt.asPartMap(r0)
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            io.reactivex.Single r0 = com.forasoft.homewavvisitor.model.server.apis.HomewavApi.DefaultImpls.sendMessage$default(r4, r5, r6, r7, r8, r9)
            java.lang.Object r0 = r0.blockingGet()
            com.forasoft.homewavvisitor.model.server.response.Response r0 = (com.forasoft.homewavvisitor.model.server.response.Response) r0
            goto L_0x03e7
        L_0x0178:
            r12 = r24
            r8 = 7
            kotlin.Pair[] r8 = new kotlin.Pair[r8]
            kotlin.Pair r10 = kotlin.TuplesKt.to(r11, r10)
            r11 = 0
            r8[r11] = r10
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 1
            r8[r2] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r7, r6)
            r8[r20] = r1
            r1 = r26
            kotlin.Pair r1 = kotlin.TuplesKt.to(r5, r1)
            r8[r19] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r15)
            r8[r18] = r1
            kotlin.Pair r0 = kotlin.TuplesKt.to(r0, r3)
            r8[r17] = r0
            r0 = r25
            kotlin.Pair r0 = kotlin.TuplesKt.to(r9, r0)
            r8[r16] = r0
            java.util.Map r0 = kotlin.collections.MapsKt.mapOf(r8)
            java.util.Map r5 = com.forasoft.homewavvisitor.extension.CommonKt.asPartMap(r0)
            java.io.File r0 = new java.io.File
            r0.<init>(r12)
            okhttp3.RequestBody$Companion r1 = okhttp3.RequestBody.Companion
            okhttp3.MediaType$Companion r2 = okhttp3.MediaType.Companion
            java.lang.String r3 = "video/mp4"
            okhttp3.MediaType r2 = r2.parse(r3)
            okhttp3.RequestBody r1 = r1.create((java.io.File) r0, (okhttp3.MediaType) r2)
            okhttp3.MultipartBody$Part$Companion r2 = okhttp3.MultipartBody.Part.Companion
            java.lang.String r0 = r0.getAbsolutePath()
            java.lang.String r3 = "video"
            okhttp3.MultipartBody$Part r6 = r2.createFormData(r3, r0, r1)
            r7 = 0
            r8 = 4
            r9 = 0
            io.reactivex.Single r0 = com.forasoft.homewavvisitor.model.server.apis.HomewavApi.DefaultImpls.sendMessage$default(r4, r5, r6, r7, r8, r9)
            java.lang.Object r0 = r0.blockingGet()
            com.forasoft.homewavvisitor.model.server.response.Response r0 = (com.forasoft.homewavvisitor.model.server.response.Response) r0
            goto L_0x03e7
        L_0x01e5:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r21.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x01f1:
            r12 = r22
            r22 = r4
            java.lang.String r4 = "image"
            boolean r23 = r8.equals(r4)
            if (r23 == 0) goto L_0x0420
            r23 = r4
            androidx.work.Data r4 = r28.getInputData()
            java.lang.String r4 = r4.getString(r13)
            if (r4 == 0) goto L_0x02fe
            r25 = r8
            java.lang.String r8 = "http"
            r24 = r9
            java.lang.String r9 = "https"
            java.lang.String[] r8 = new java.lang.String[]{r8, r9}
            java.util.Set r8 = kotlin.collections.SetsKt.setOf(r8)
            java.lang.Iterable r8 = (java.lang.Iterable) r8
            android.net.Uri r9 = android.net.Uri.parse(r4)
            r26 = r0
            java.lang.String r0 = "Uri.parse(galleryUrl)"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r9, r0)
            java.lang.String r0 = r9.getScheme()
            boolean r0 = kotlin.collections.CollectionsKt.contains(r8, r0)
            if (r0 == 0) goto L_0x0287
            r0 = 8
            kotlin.Pair[] r0 = new kotlin.Pair[r0]
            kotlin.Pair r8 = kotlin.TuplesKt.to(r11, r10)
            r9 = 0
            r0[r9] = r8
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 1
            r0[r2] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r7, r6)
            r0[r20] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r13, r4)
            r0[r19] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r5, r12)
            r0[r18] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r15)
            r0[r17] = r1
            r8 = r26
            kotlin.Pair r1 = kotlin.TuplesKt.to(r8, r3)
            r0[r16] = r1
            r13 = r24
            r9 = r25
            kotlin.Pair r1 = kotlin.TuplesKt.to(r13, r9)
            r2 = 7
            r0[r2] = r1
            java.util.Map r0 = kotlin.collections.MapsKt.mapOf(r0)
            java.util.Map r5 = com.forasoft.homewavvisitor.extension.CommonKt.asPartMap(r0)
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            r4 = r22
            io.reactivex.Single r0 = com.forasoft.homewavvisitor.model.server.apis.HomewavApi.DefaultImpls.sendMessage$default(r4, r5, r6, r7, r8, r9)
            java.lang.Object r0 = r0.blockingGet()
            com.forasoft.homewavvisitor.model.server.response.Response r0 = (com.forasoft.homewavvisitor.model.server.response.Response) r0
            goto L_0x03e7
        L_0x0287:
            r13 = r24
            r9 = r25
            r8 = r26
            java.io.File r0 = new java.io.File
            java.net.URI r9 = new java.net.URI
            r9.<init>(r4)
            r0.<init>(r9)
            okhttp3.RequestBody$Companion r4 = okhttp3.RequestBody.Companion
            okhttp3.MediaType$Companion r9 = okhttp3.MediaType.Companion
            java.lang.String r13 = "image/png"
            okhttp3.MediaType r9 = r9.parse(r13)
            okhttp3.RequestBody r4 = r4.create((java.io.File) r0, (okhttp3.MediaType) r9)
            okhttp3.MultipartBody$Part$Companion r9 = okhttp3.MultipartBody.Part.Companion
            java.lang.String r0 = r0.getAbsolutePath()
            r13 = r23
            okhttp3.MultipartBody$Part r0 = r9.createFormData(r13, r0, r4)
            r4 = 7
            kotlin.Pair[] r4 = new kotlin.Pair[r4]
            kotlin.Pair r9 = kotlin.TuplesKt.to(r11, r10)
            r10 = 0
            r4[r10] = r9
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 1
            r4[r2] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r7, r6)
            r4[r20] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r5, r12)
            r4[r19] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r15)
            r4[r18] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r8, r3)
            r4[r17] = r1
            r13 = r24
            r9 = r25
            kotlin.Pair r1 = kotlin.TuplesKt.to(r13, r9)
            r4[r16] = r1
            java.util.Map r1 = kotlin.collections.MapsKt.mapOf(r4)
            java.util.Map r5 = com.forasoft.homewavvisitor.extension.CommonKt.asPartMap(r1)
            r6 = 0
            r8 = 2
            r9 = 0
            r4 = r22
            r7 = r0
            io.reactivex.Single r0 = com.forasoft.homewavvisitor.model.server.apis.HomewavApi.DefaultImpls.sendMessage$default(r4, r5, r6, r7, r8, r9)
            java.lang.Object r0 = r0.blockingGet()
            com.forasoft.homewavvisitor.model.server.response.Response r0 = (com.forasoft.homewavvisitor.model.server.response.Response) r0
            goto L_0x03e7
        L_0x02fe:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r21.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x030a:
            r13 = r9
            r12 = r22
            r22 = r4
            r9 = r8
            r8 = r0
            java.lang.String r0 = "text"
            boolean r0 = r9.equals(r0)
            if (r0 == 0) goto L_0x0420
            androidx.work.Data r0 = r28.getInputData()
            java.lang.String r0 = r0.getString(r14)
            if (r0 == 0) goto L_0x036c
            r4 = 7
            kotlin.Pair[] r4 = new kotlin.Pair[r4]
            kotlin.Pair r10 = kotlin.TuplesKt.to(r11, r10)
            r11 = 0
            r4[r11] = r10
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 1
            r4[r2] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r7, r6)
            r4[r20] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r5, r12)
            r4[r19] = r1
            kotlin.Pair r0 = kotlin.TuplesKt.to(r14, r0)
            r4[r18] = r0
            kotlin.Pair r0 = kotlin.TuplesKt.to(r8, r3)
            r4[r17] = r0
            kotlin.Pair r0 = kotlin.TuplesKt.to(r13, r9)
            r4[r16] = r0
            java.util.Map r0 = kotlin.collections.MapsKt.mapOf(r4)
            java.util.Map r5 = com.forasoft.homewavvisitor.extension.CommonKt.asPartMap(r0)
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            r4 = r22
            io.reactivex.Single r0 = com.forasoft.homewavvisitor.model.server.apis.HomewavApi.DefaultImpls.sendMessage$default(r4, r5, r6, r7, r8, r9)
            java.lang.Object r0 = r0.blockingGet()
            com.forasoft.homewavvisitor.model.server.response.Response r0 = (com.forasoft.homewavvisitor.model.server.response.Response) r0
            goto L_0x03e7
        L_0x036c:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r21.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0378:
            r12 = r22
            r22 = r4
            r27 = r8
            r8 = r0
            r0 = r9
            r9 = r27
            java.lang.String r4 = "gif"
            boolean r4 = r9.equals(r4)
            if (r4 == 0) goto L_0x0420
            androidx.work.Data r4 = r28.getInputData()
            java.lang.String r4 = r4.getString(r13)
            if (r4 == 0) goto L_0x0414
            r24 = r0
            r0 = 8
            kotlin.Pair[] r0 = new kotlin.Pair[r0]
            kotlin.Pair r10 = kotlin.TuplesKt.to(r11, r10)
            r11 = 0
            r0[r11] = r10
            kotlin.Pair r1 = kotlin.TuplesKt.to(r2, r1)
            r2 = 1
            r0[r2] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r7, r6)
            r0[r20] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r13, r4)
            r0[r19] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r5, r12)
            r0[r18] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r14, r15)
            r0[r17] = r1
            kotlin.Pair r1 = kotlin.TuplesKt.to(r8, r3)
            r0[r16] = r1
            r1 = r24
            kotlin.Pair r1 = kotlin.TuplesKt.to(r1, r9)
            r2 = 7
            r0[r2] = r1
            java.util.Map r0 = kotlin.collections.MapsKt.mapOf(r0)
            java.util.Map r5 = com.forasoft.homewavvisitor.extension.CommonKt.asPartMap(r0)
            r6 = 0
            r7 = 0
            r8 = 6
            r9 = 0
            r4 = r22
            io.reactivex.Single r0 = com.forasoft.homewavvisitor.model.server.apis.HomewavApi.DefaultImpls.sendMessage$default(r4, r5, r6, r7, r8, r9)
            java.lang.Object r0 = r0.blockingGet()
            com.forasoft.homewavvisitor.model.server.response.Response r0 = (com.forasoft.homewavvisitor.model.server.response.Response) r0
        L_0x03e7:
            boolean r1 = r0.getOk()
            if (r1 == 0) goto L_0x03f7
            androidx.work.ListenableWorker$Result r0 = androidx.work.ListenableWorker.Result.success()
            java.lang.String r1 = "Result.success()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            goto L_0x0413
        L_0x03f7:
            androidx.work.Data$Builder r1 = new androidx.work.Data$Builder
            r1.<init>()
            java.lang.String r0 = r0.getError()
            java.lang.String r2 = "error_message"
            androidx.work.Data$Builder r0 = r1.putString(r2, r0)
            androidx.work.Data r0 = r0.build()
            androidx.work.ListenableWorker$Result r0 = androidx.work.ListenableWorker.Result.failure(r0)
            java.lang.String r1 = "Result.failure(Data.Buil… response.error).build())"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
        L_0x0413:
            return r0
        L_0x0414:
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r21.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0420:
            java.lang.UnsupportedOperationException r0 = new java.lang.UnsupportedOperationException
            r0.<init>()
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0428:
            r21 = r5
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r21.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0436:
            r21 = r5
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r21.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0444:
            r21 = r5
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r21.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        L_0x0452:
            r21 = r5
            java.lang.IllegalArgumentException r0 = new java.lang.IllegalArgumentException
            java.lang.String r1 = r21.toString()
            r0.<init>(r1)
            java.lang.Throwable r0 = (java.lang.Throwable) r0
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.model.UploadWorker.doWork():androidx.work.ListenableWorker$Result");
    }

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\r\b\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004XT¢\u0006\u0002\n\u0000¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/model/UploadWorker$Companion;", "", "()V", "KEY_BODY", "", "KEY_ELAPSED_TIME", "KEY_ERROR_MESSAGE", "KEY_GALLERY_URL", "KEY_INMATE_ID", "KEY_PUB_ID", "KEY_SENDER", "KEY_TYPE", "KEY_VISITOR_ID", "TAG_IMAGE", "TAG_RESIZE", "TAG_TEXT", "TAG_VIDEO", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: UploadWorker.kt */
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }
    }
}
