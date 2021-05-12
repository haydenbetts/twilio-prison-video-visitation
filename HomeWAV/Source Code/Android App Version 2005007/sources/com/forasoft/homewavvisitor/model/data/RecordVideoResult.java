package com.forasoft.homewavvisitor.model.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0004\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0019\u001a\u00020\bHÖ\u0001J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/RecordVideoResult;", "", "pubId", "", "streamName", "protocol", "Lcom/forasoft/homewavvisitor/model/data/Protocol;", "videoLength", "", "(Ljava/lang/String;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/Protocol;I)V", "getProtocol", "()Lcom/forasoft/homewavvisitor/model/data/Protocol;", "getPubId", "()Ljava/lang/String;", "getStreamName", "getVideoLength", "()I", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RecordVideoResult.kt */
public final class RecordVideoResult {
    private final Protocol protocol;
    private final String pubId;
    private final String streamName;
    private final int videoLength;

    public static /* synthetic */ RecordVideoResult copy$default(RecordVideoResult recordVideoResult, String str, String str2, Protocol protocol2, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            str = recordVideoResult.pubId;
        }
        if ((i2 & 2) != 0) {
            str2 = recordVideoResult.streamName;
        }
        if ((i2 & 4) != 0) {
            protocol2 = recordVideoResult.protocol;
        }
        if ((i2 & 8) != 0) {
            i = recordVideoResult.videoLength;
        }
        return recordVideoResult.copy(str, str2, protocol2, i);
    }

    public final String component1() {
        return this.pubId;
    }

    public final String component2() {
        return this.streamName;
    }

    public final Protocol component3() {
        return this.protocol;
    }

    public final int component4() {
        return this.videoLength;
    }

    public final RecordVideoResult copy(String str, String str2, Protocol protocol2, int i) {
        Intrinsics.checkParameterIsNotNull(str, "pubId");
        Intrinsics.checkParameterIsNotNull(str2, "streamName");
        Intrinsics.checkParameterIsNotNull(protocol2, "protocol");
        return new RecordVideoResult(str, str2, protocol2, i);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof RecordVideoResult)) {
            return false;
        }
        RecordVideoResult recordVideoResult = (RecordVideoResult) obj;
        return Intrinsics.areEqual((Object) this.pubId, (Object) recordVideoResult.pubId) && Intrinsics.areEqual((Object) this.streamName, (Object) recordVideoResult.streamName) && Intrinsics.areEqual((Object) this.protocol, (Object) recordVideoResult.protocol) && this.videoLength == recordVideoResult.videoLength;
    }

    public int hashCode() {
        String str = this.pubId;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.streamName;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        Protocol protocol2 = this.protocol;
        if (protocol2 != null) {
            i = protocol2.hashCode();
        }
        return ((hashCode2 + i) * 31) + this.videoLength;
    }

    public String toString() {
        return "RecordVideoResult(pubId=" + this.pubId + ", streamName=" + this.streamName + ", protocol=" + this.protocol + ", videoLength=" + this.videoLength + ")";
    }

    public RecordVideoResult(String str, String str2, Protocol protocol2, int i) {
        Intrinsics.checkParameterIsNotNull(str, "pubId");
        Intrinsics.checkParameterIsNotNull(str2, "streamName");
        Intrinsics.checkParameterIsNotNull(protocol2, "protocol");
        this.pubId = str;
        this.streamName = str2;
        this.protocol = protocol2;
        this.videoLength = i;
    }

    public final String getPubId() {
        return this.pubId;
    }

    public final String getStreamName() {
        return this.streamName;
    }

    public final Protocol getProtocol() {
        return this.protocol;
    }

    public final int getVideoLength() {
        return this.videoLength;
    }
}
