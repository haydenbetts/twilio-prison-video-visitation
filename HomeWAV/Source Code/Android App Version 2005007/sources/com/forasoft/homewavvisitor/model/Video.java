package com.forasoft.homewavvisitor.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/model/Video;", "Lcom/forasoft/homewavvisitor/model/Media;", "uri", "", "mimeType", "duration", "", "(Ljava/lang/String;Ljava/lang/String;J)V", "getDuration", "()J", "getMimeType", "()Ljava/lang/String;", "getUri", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Media.kt */
public final class Video implements Media {
    private final long duration;
    private final String mimeType;
    private final String uri;

    public Video(String str, String str2, long j) {
        Intrinsics.checkParameterIsNotNull(str, "uri");
        Intrinsics.checkParameterIsNotNull(str2, "mimeType");
        this.uri = str;
        this.mimeType = str2;
        this.duration = j;
    }

    public final long getDuration() {
        return this.duration;
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final String getUri() {
        return this.uri;
    }
}
