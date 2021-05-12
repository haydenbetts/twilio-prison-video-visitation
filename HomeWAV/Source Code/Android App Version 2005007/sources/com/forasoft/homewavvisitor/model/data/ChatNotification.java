package com.forasoft.homewavvisitor.model.data;

import com.forasoft.homewavvisitor.model.data.ChatItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004R\u0014\u0010\u0005\u001a\u00020\u00068VX\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/ChatNotification;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "message", "", "(Ljava/lang/String;)V", "created", "Lorg/threeten/bp/LocalDateTime;", "getCreated", "()Lorg/threeten/bp/LocalDateTime;", "getMessage", "()Ljava/lang/String;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ChatNotification.kt */
public final class ChatNotification implements ChatItem {
    private final String message;

    public ChatNotification(String str) {
        Intrinsics.checkParameterIsNotNull(str, "message");
        this.message = str;
    }

    public int compareTo(ChatItem chatItem) {
        Intrinsics.checkParameterIsNotNull(chatItem, "other");
        return ChatItem.DefaultImpls.compareTo(this, chatItem);
    }

    public final String getMessage() {
        return this.message;
    }

    public LocalDateTime getCreated() {
        LocalDateTime now = LocalDateTime.now();
        Intrinsics.checkExpressionValueIsNotNull(now, "LocalDateTime.now()");
        return now;
    }
}
