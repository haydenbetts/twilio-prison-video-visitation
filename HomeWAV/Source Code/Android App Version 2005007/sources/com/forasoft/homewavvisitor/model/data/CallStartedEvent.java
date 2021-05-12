package com.forasoft.homewavvisitor.model.data;

import androidx.core.app.NotificationCompat;
import com.forasoft.homewavvisitor.model.data.ChatItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u0013\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u0003HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0005\u0010\u0006R\u0014\u0010\u0007\u001a\u00020\b8VX\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/CallStartedEvent;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "call", "Lcom/forasoft/homewavvisitor/model/data/CallEntity;", "(Lcom/forasoft/homewavvisitor/model/data/CallEntity;)V", "getCall", "()Lcom/forasoft/homewavvisitor/model/data/CallEntity;", "created", "Lorg/threeten/bp/LocalDateTime;", "getCreated", "()Lorg/threeten/bp/LocalDateTime;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CallStartedEvent.kt */
public final class CallStartedEvent implements ChatItem {
    private final CallEntity call;

    public static /* synthetic */ CallStartedEvent copy$default(CallStartedEvent callStartedEvent, CallEntity callEntity, int i, Object obj) {
        if ((i & 1) != 0) {
            callEntity = callStartedEvent.call;
        }
        return callStartedEvent.copy(callEntity);
    }

    public final CallEntity component1() {
        return this.call;
    }

    public final CallStartedEvent copy(CallEntity callEntity) {
        Intrinsics.checkParameterIsNotNull(callEntity, NotificationCompat.CATEGORY_CALL);
        return new CallStartedEvent(callEntity);
    }

    public boolean equals(Object obj) {
        if (this != obj) {
            return (obj instanceof CallStartedEvent) && Intrinsics.areEqual((Object) this.call, (Object) ((CallStartedEvent) obj).call);
        }
        return true;
    }

    public int hashCode() {
        CallEntity callEntity = this.call;
        if (callEntity != null) {
            return callEntity.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "CallStartedEvent(call=" + this.call + ")";
    }

    public CallStartedEvent(CallEntity callEntity) {
        Intrinsics.checkParameterIsNotNull(callEntity, NotificationCompat.CATEGORY_CALL);
        this.call = callEntity;
    }

    public int compareTo(ChatItem chatItem) {
        Intrinsics.checkParameterIsNotNull(chatItem, "other");
        return ChatItem.DefaultImpls.compareTo(this, chatItem);
    }

    public final CallEntity getCall() {
        return this.call;
    }

    public LocalDateTime getCreated() {
        LocalDateTime connected = this.call.getConnected();
        if (connected == null) {
            Intrinsics.throwNpe();
        }
        return connected;
    }
}
