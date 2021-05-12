package com.forasoft.homewavvisitor.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0002\u0010\u0005R\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\u0007¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/Image;", "Lcom/forasoft/homewavvisitor/model/Media;", "uri", "", "mimeType", "(Ljava/lang/String;Ljava/lang/String;)V", "getMimeType", "()Ljava/lang/String;", "getUri", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Media.kt */
public final class Image implements Media {
    private final String mimeType;
    private final String uri;

    public Image(String str, String str2) {
        Intrinsics.checkParameterIsNotNull(str, "uri");
        Intrinsics.checkParameterIsNotNull(str2, "mimeType");
        this.uri = str;
        this.mimeType = str2;
    }

    /* JADX INFO: this call moved to the top of the method (can break code semantics) */
    public /* synthetic */ Image(String str, String str2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(str, (i & 2) != 0 ? "image/*" : str2);
    }

    public final String getMimeType() {
        return this.mimeType;
    }

    public final String getUri() {
        return this.uri;
    }
}
