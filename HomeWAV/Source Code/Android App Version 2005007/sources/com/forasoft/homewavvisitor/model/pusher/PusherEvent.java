package com.forasoft.homewavvisitor.model.pusher;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001:\u0001\u0014B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\u0002\u0010\u0005J\t\u0010\n\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\u000b\u001a\u0004\u0018\u00010\u0001HÆ\u0003J\u001f\u0010\f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0001HÆ\u0001J\u0013\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0010\u001a\u00020\u0011HÖ\u0001J\t\u0010\u0012\u001a\u00020\u0013HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0001¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent;", "", "type", "Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent$Type;", "value", "(Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent$Type;Ljava/lang/Object;)V", "getType", "()Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent$Type;", "getValue", "()Ljava/lang/Object;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "Type", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PusherEvent.kt */
public final class PusherEvent {
    private final Type type;
    private final Object value;

    @Metadata(bv = {1, 0, 3}, d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u000f\b\u0001\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002j\u0002\b\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000f¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent$Type;", "", "(Ljava/lang/String;I)V", "switch_back_to_heartbeat", "occupant_login", "status_changed", "visitor_login", "new_call", "new_message", "call_disconnected", "admin_warning", "admin_message", "chat_warning_on", "chat_warning_off", "changed_occupant_credits", "changed_credits", "app_release"}, k = 1, mv = {1, 1, 16})
    /* compiled from: PusherEvent.kt */
    public enum Type {
        switch_back_to_heartbeat,
        occupant_login,
        status_changed,
        visitor_login,
        new_call,
        new_message,
        call_disconnected,
        admin_warning,
        admin_message,
        chat_warning_on,
        chat_warning_off,
        changed_occupant_credits,
        changed_credits
    }

    public static /* synthetic */ PusherEvent copy$default(PusherEvent pusherEvent, Type type2, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            type2 = pusherEvent.type;
        }
        if ((i & 2) != 0) {
            obj = pusherEvent.value;
        }
        return pusherEvent.copy(type2, obj);
    }

    public final Type component1() {
        return this.type;
    }

    public final Object component2() {
        return this.value;
    }

    public final PusherEvent copy(Type type2, Object obj) {
        Intrinsics.checkParameterIsNotNull(type2, "type");
        return new PusherEvent(type2, obj);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof PusherEvent)) {
            return false;
        }
        PusherEvent pusherEvent = (PusherEvent) obj;
        return Intrinsics.areEqual((Object) this.type, (Object) pusherEvent.type) && Intrinsics.areEqual(this.value, pusherEvent.value);
    }

    public int hashCode() {
        Type type2 = this.type;
        int i = 0;
        int hashCode = (type2 != null ? type2.hashCode() : 0) * 31;
        Object obj = this.value;
        if (obj != null) {
            i = obj.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "PusherEvent(type=" + this.type + ", value=" + this.value + ")";
    }

    public PusherEvent(Type type2, Object obj) {
        Intrinsics.checkParameterIsNotNull(type2, "type");
        this.type = type2;
        this.value = obj;
    }

    public final Type getType() {
        return this.type;
    }

    public final Object getValue() {
        return this.value;
    }
}
