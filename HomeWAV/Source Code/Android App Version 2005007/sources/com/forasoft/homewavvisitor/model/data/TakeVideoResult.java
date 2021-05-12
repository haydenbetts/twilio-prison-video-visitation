package com.forasoft.homewavvisitor.model.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\t\u0010\f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/TakeVideoResult;", "", "path", "", "length", "", "(Ljava/lang/String;I)V", "getLength", "()I", "getPath", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TakeVideoResult.kt */
public final class TakeVideoResult {
    private final int length;
    private final String path;

    public static /* synthetic */ TakeVideoResult copy$default(TakeVideoResult takeVideoResult, String str, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = takeVideoResult.path;
        }
        if ((i2 & 2) != 0) {
            i = takeVideoResult.length;
        }
        return takeVideoResult.copy(str, i);
    }

    public final String component1() {
        return this.path;
    }

    public final int component2() {
        return this.length;
    }

    public final TakeVideoResult copy(String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "path");
        return new TakeVideoResult(str, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof TakeVideoResult)) {
            return false;
        }
        TakeVideoResult takeVideoResult = (TakeVideoResult) obj;
        return Intrinsics.areEqual((Object) this.path, (Object) takeVideoResult.path) && this.length == takeVideoResult.length;
    }

    public int hashCode() {
        String str = this.path;
        return ((str != null ? str.hashCode() : 0) * 31) + this.length;
    }

    public String toString() {
        return "TakeVideoResult(path=" + this.path + ", length=" + this.length + ")";
    }

    public TakeVideoResult(String str, int i) {
        Intrinsics.checkParameterIsNotNull(str, "path");
        this.path = str;
        this.length = i;
    }

    public final int getLength() {
        return this.length;
    }

    public final String getPath() {
        return this.path;
    }
}
