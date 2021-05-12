package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Notification;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0002\u0010\u0006J\t\u0010\u000b\u001a\u00020\u0003HÆ\u0003J\u000b\u0010\f\u001a\u0004\u0018\u00010\u0005HÆ\u0003J\u001f\u0010\r\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005HÆ\u0001J\u0013\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u0011\u001a\u00020\u0012HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0005HÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/NotificationWithInmateStatus;", "", "notification", "Lcom/forasoft/homewavvisitor/model/data/Notification;", "status", "", "(Lcom/forasoft/homewavvisitor/model/data/Notification;Ljava/lang/String;)V", "getNotification", "()Lcom/forasoft/homewavvisitor/model/data/Notification;", "getStatus", "()Ljava/lang/String;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationsPresenter.kt */
public final class NotificationWithInmateStatus {
    private final Notification notification;
    private final String status;

    public static /* synthetic */ NotificationWithInmateStatus copy$default(NotificationWithInmateStatus notificationWithInmateStatus, Notification notification2, String str, int i, Object obj) {
        if ((i & 1) != 0) {
            notification2 = notificationWithInmateStatus.notification;
        }
        if ((i & 2) != 0) {
            str = notificationWithInmateStatus.status;
        }
        return notificationWithInmateStatus.copy(notification2, str);
    }

    public final Notification component1() {
        return this.notification;
    }

    public final String component2() {
        return this.status;
    }

    public final NotificationWithInmateStatus copy(Notification notification2, String str) {
        Intrinsics.checkParameterIsNotNull(notification2, "notification");
        return new NotificationWithInmateStatus(notification2, str);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof NotificationWithInmateStatus)) {
            return false;
        }
        NotificationWithInmateStatus notificationWithInmateStatus = (NotificationWithInmateStatus) obj;
        return Intrinsics.areEqual((Object) this.notification, (Object) notificationWithInmateStatus.notification) && Intrinsics.areEqual((Object) this.status, (Object) notificationWithInmateStatus.status);
    }

    public int hashCode() {
        Notification notification2 = this.notification;
        int i = 0;
        int hashCode = (notification2 != null ? notification2.hashCode() : 0) * 31;
        String str = this.status;
        if (str != null) {
            i = str.hashCode();
        }
        return hashCode + i;
    }

    public String toString() {
        return "NotificationWithInmateStatus(notification=" + this.notification + ", status=" + this.status + ")";
    }

    public NotificationWithInmateStatus(Notification notification2, String str) {
        Intrinsics.checkParameterIsNotNull(notification2, "notification");
        this.notification = notification2;
        this.status = str;
    }

    public final Notification getNotification() {
        return this.notification;
    }

    public final String getStatus() {
        return this.status;
    }
}
