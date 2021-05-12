package com.forasoft.homewavvisitor.model.data;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.chrono.ChronoLocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u000f\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\bf\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001J\u0011\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0000H\u0002R\u0012\u0010\u0002\u001a\u00020\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "", "created", "Lorg/threeten/bp/LocalDateTime;", "getCreated", "()Lorg/threeten/bp/LocalDateTime;", "compareTo", "", "other", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: ChatItem.kt */
public interface ChatItem extends Comparable<ChatItem> {
    int compareTo(ChatItem chatItem);

    LocalDateTime getCreated();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: ChatItem.kt */
    public static final class DefaultImpls {
        public static int compareTo(ChatItem chatItem, ChatItem chatItem2) {
            Intrinsics.checkParameterIsNotNull(chatItem2, "other");
            return chatItem.getCreated().compareTo((ChronoLocalDateTime<?>) chatItem2.getCreated());
        }
    }
}
