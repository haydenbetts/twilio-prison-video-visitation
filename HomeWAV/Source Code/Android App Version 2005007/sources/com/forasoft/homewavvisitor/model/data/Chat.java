package com.forasoft.homewavvisitor.model.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0014HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/Chat;", "", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "lastMessage", "Lcom/forasoft/homewavvisitor/model/data/Message;", "(Lcom/forasoft/homewavvisitor/model/data/Inmate;Lcom/forasoft/homewavvisitor/model/data/Message;)V", "getInmate", "()Lcom/forasoft/homewavvisitor/model/data/Inmate;", "getLastMessage", "()Lcom/forasoft/homewavvisitor/model/data/Message;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Chat.kt */
public final class Chat {
    private final Inmate inmate;
    private final Message lastMessage;

    public static /* synthetic */ Chat copy$default(Chat chat, Inmate inmate2, Message message, int i, Object obj) {
        if ((i & 1) != 0) {
            inmate2 = chat.inmate;
        }
        if ((i & 2) != 0) {
            message = chat.lastMessage;
        }
        return chat.copy(inmate2, message);
    }

    public final Inmate component1() {
        return this.inmate;
    }

    public final Message component2() {
        return this.lastMessage;
    }

    public final Chat copy(Inmate inmate2, Message message) {
        Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
        return new Chat(inmate2, message);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Chat)) {
            return false;
        }
        Chat chat = (Chat) obj;
        return Intrinsics.areEqual((Object) this.inmate, (Object) chat.inmate) && Intrinsics.areEqual((Object) this.lastMessage, (Object) chat.lastMessage);
    }

    public int hashCode() {
        Inmate inmate2 = this.inmate;
        int i = 0;
        int hashCode = (inmate2 != null ? inmate2.hashCode() : 0) * 31;
        Message message = this.lastMessage;
        if (message != null) {
            i = message.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "Chat(inmate=" + this.inmate + ", lastMessage=" + this.lastMessage + ")";
    }

    public Chat(Inmate inmate2, Message message) {
        Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
        this.inmate = inmate2;
        this.lastMessage = message;
    }

    public final Inmate getInmate() {
        return this.inmate;
    }

    public final Message getLastMessage() {
        return this.lastMessage;
    }
}
