package com.forasoft.homewavvisitor.model;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import com.bumptech.glide.Priority;
import com.bumptech.glide.load.data.DataFetcher;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import wseemann.media.FFmpegMediaMetadataRetriever;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\r\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\b\u0010\u0006\u001a\u00020\u0007H\u0016J\b\u0010\b\u001a\u00020\u0007H\u0016J\b\u0010\t\u001a\u00020\u0004H\u0016J\u0012\u0010\n\u001a\u00020\u00022\b\u0010\u000b\u001a\u0004\u0018\u00010\fH\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/model/Mp4DataFetcher;", "Lcom/bumptech/glide/load/data/DataFetcher;", "Ljava/io/InputStream;", "url", "", "(Ljava/lang/String;)V", "cancel", "", "cleanup", "getId", "loadData", "priority", "Lcom/bumptech/glide/Priority;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Mvp4DataFetcher.kt */
public final class Mp4DataFetcher implements DataFetcher<InputStream> {
    private final String url;

    public void cancel() {
    }

    public void cleanup() {
    }

    public Mp4DataFetcher(String str) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        this.url = str;
    }

    public String getId() {
        return StringsKt.substringBeforeLast$default(StringsKt.substringAfterLast$default(this.url, "/", (String) null, 2, (Object) null), ".", (String) null, 2, (Object) null);
    }

    public InputStream loadData(Priority priority) {
        FFmpegMediaMetadataRetriever fFmpegMediaMetadataRetriever = new FFmpegMediaMetadataRetriever();
        fFmpegMediaMetadataRetriever.setDataSource(this.url);
        String extractMetadata = fFmpegMediaMetadataRetriever.extractMetadata(FFmpegMediaMetadataRetriever.METADATA_KEY_VIDEO_ROTATION);
        float parseFloat = extractMetadata != null ? Float.parseFloat(extractMetadata) : 0.0f;
        Bitmap frameAtTime = fFmpegMediaMetadataRetriever.getFrameAtTime();
        Matrix matrix = new Matrix();
        matrix.postRotate(parseFloat);
        Intrinsics.checkExpressionValueIsNotNull(frameAtTime, "src");
        Bitmap createBitmap = Bitmap.createBitmap(frameAtTime, 0, 0, frameAtTime.getWidth(), frameAtTime.getHeight(), matrix, false);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        createBitmap.compress(Bitmap.CompressFormat.PNG, 0, byteArrayOutputStream);
        return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
    }
}
