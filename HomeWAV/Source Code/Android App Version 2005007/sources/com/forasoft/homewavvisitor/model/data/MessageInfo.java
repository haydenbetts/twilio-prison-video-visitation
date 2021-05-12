package com.forasoft.homewavvisitor.model.data;

import com.google.gson.annotations.SerializedName;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J'\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001J\u0013\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0014\u001a\u00020\u0015HÖ\u0001J\t\u0010\u0016\u001a\u00020\u0003HÖ\u0001R\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\u0004\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000b¨\u0006\u0017"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/MessageInfo;", "", "pubId", "", "streamName", "protocol", "Lcom/forasoft/homewavvisitor/model/data/Protocol;", "(Ljava/lang/String;Ljava/lang/String;Lcom/forasoft/homewavvisitor/model/data/Protocol;)V", "getProtocol", "()Lcom/forasoft/homewavvisitor/model/data/Protocol;", "getPubId", "()Ljava/lang/String;", "getStreamName", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MessageInfo.kt */
public final class MessageInfo {
    private final Protocol protocol;
    @SerializedName("pubid")
    private final String pubId;
    @SerializedName("stream_name")
    private final String streamName;

    public static /* synthetic */ MessageInfo copy$default(MessageInfo messageInfo, String str, String str2, Protocol protocol2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = messageInfo.pubId;
        }
        if ((i & 2) != 0) {
            str2 = messageInfo.streamName;
        }
        if ((i & 4) != 0) {
            protocol2 = messageInfo.protocol;
        }
        return messageInfo.copy(str, str2, protocol2);
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

    public final MessageInfo copy(String str, String str2, Protocol protocol2) {
        Intrinsics.checkParameterIsNotNull(str, "pubId");
        Intrinsics.checkParameterIsNotNull(str2, "streamName");
        Intrinsics.checkParameterIsNotNull(protocol2, "protocol");
        return new MessageInfo(str, str2, protocol2);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof MessageInfo)) {
            return false;
        }
        MessageInfo messageInfo = (MessageInfo) obj;
        return Intrinsics.areEqual((Object) this.pubId, (Object) messageInfo.pubId) && Intrinsics.areEqual((Object) this.streamName, (Object) messageInfo.streamName) && Intrinsics.areEqual((Object) this.protocol, (Object) messageInfo.protocol);
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
        return hashCode2 + i;
    }

    public String toString() {
        return "MessageInfo(pubId=" + this.pubId + ", streamName=" + this.streamName + ", protocol=" + this.protocol + ")";
    }

    public MessageInfo(String str, String str2, Protocol protocol2) {
        Intrinsics.checkParameterIsNotNull(str, "pubId");
        Intrinsics.checkParameterIsNotNull(str2, "streamName");
        Intrinsics.checkParameterIsNotNull(protocol2, "protocol");
        this.pubId = str;
        this.streamName = str2;
        this.protocol = protocol2;
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
}
