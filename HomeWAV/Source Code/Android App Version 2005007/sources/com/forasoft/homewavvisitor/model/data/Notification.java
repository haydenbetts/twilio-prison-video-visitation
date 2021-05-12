package com.forasoft.homewavvisitor.model.data;

import com.forasoft.homewavvisitor.model.data.ChatItem;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.threeten.bp.LocalDateTime;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\b\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0013\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0014\u001a\u00020\bHÆ\u0003J1\u0010\u0015\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\bHÆ\u0001J\u0013\u0010\u0016\u001a\u00020\u00172\b\u0010\u0018\u001a\u0004\u0018\u00010\u0019HÖ\u0003J\t\u0010\u001a\u001a\u00020\u0003HÖ\u0001J\t\u0010\u001b\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0016\u0010\u0002\u001a\u00020\u00038\u0006X\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000b¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/model/data/Notification;", "Lcom/forasoft/homewavvisitor/model/data/ChatItem;", "id", "", "type", "", "body", "created", "Lorg/threeten/bp/LocalDateTime;", "(ILjava/lang/String;Ljava/lang/String;Lorg/threeten/bp/LocalDateTime;)V", "getBody", "()Ljava/lang/String;", "getCreated", "()Lorg/threeten/bp/LocalDateTime;", "getId", "()I", "getType", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "", "hashCode", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: Notification.kt */
public final class Notification implements ChatItem {
    private final String body;
    private final LocalDateTime created;
    private final int id;
    private final String type;

    public static /* synthetic */ Notification copy$default(Notification notification, int i, String str, String str2, LocalDateTime localDateTime, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = notification.id;
        }
        if ((i2 & 2) != 0) {
            str = notification.type;
        }
        if ((i2 & 4) != 0) {
            str2 = notification.body;
        }
        if ((i2 & 8) != 0) {
            localDateTime = notification.getCreated();
        }
        return notification.copy(i, str, str2, localDateTime);
    }

    public final int component1() {
        return this.id;
    }

    public final String component2() {
        return this.type;
    }

    public final String component3() {
        return this.body;
    }

    public final LocalDateTime component4() {
        return getCreated();
    }

    public final Notification copy(int i, String str, String str2, LocalDateTime localDateTime) {
        Intrinsics.checkParameterIsNotNull(str, "type");
        Intrinsics.checkParameterIsNotNull(str2, "body");
        Intrinsics.checkParameterIsNotNull(localDateTime, "created");
        return new Notification(i, str, str2, localDateTime);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Notification)) {
            return false;
        }
        Notification notification = (Notification) obj;
        return this.id == notification.id && Intrinsics.areEqual((Object) this.type, (Object) notification.type) && Intrinsics.areEqual((Object) this.body, (Object) notification.body) && Intrinsics.areEqual((Object) getCreated(), (Object) notification.getCreated());
    }

    public int hashCode() {
        int i = this.id * 31;
        String str = this.type;
        int i2 = 0;
        int hashCode = (i + (str != null ? str.hashCode() : 0)) * 31;
        String str2 = this.body;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        LocalDateTime created2 = getCreated();
        if (created2 != null) {
            i2 = created2.hashCode();
        }
        return hashCode2 + i2;
    }

    public String toString() {
        return "Notification(id=" + this.id + ", type=" + this.type + ", body=" + this.body + ", created=" + getCreated() + ")";
    }

    public Notification(int i, String str, String str2, LocalDateTime localDateTime) {
        Intrinsics.checkParameterIsNotNull(str, "type");
        Intrinsics.checkParameterIsNotNull(str2, "body");
        Intrinsics.checkParameterIsNotNull(localDateTime, "created");
        this.id = i;
        this.type = str;
        this.body = str2;
        this.created = localDateTime;
    }

    public int compareTo(ChatItem chatItem) {
        Intrinsics.checkParameterIsNotNull(chatItem, "other");
        return ChatItem.DefaultImpls.compareTo(this, chatItem);
    }

    public final String getBody() {
        return this.body;
    }

    public LocalDateTime getCreated() {
        return this.created;
    }

    public final int getId() {
        return this.id;
    }

    public final String getType() {
        return this.type;
    }
}
