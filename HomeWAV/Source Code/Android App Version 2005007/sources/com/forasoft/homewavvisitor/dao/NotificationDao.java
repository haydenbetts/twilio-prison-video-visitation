package com.forasoft.homewavvisitor.dao;

import com.forasoft.homewavvisitor.model.data.Notification;
import io.reactivex.Flowable;
import io.reactivex.Single;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b'\u0018\u00002\u00020\u0001B\u0005¢\u0006\u0002\u0010\u0002J\u000e\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H'J\b\u0010\u0006\u001a\u00020\u0007H'J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0005H'J\u0014\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u0004H'J\u001c\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u000b0\u00042\u0006\u0010\u000e\u001a\u00020\u000fH'J\u0016\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\f0\u00112\u0006\u0010\u0012\u001a\u00020\u000fH\u0016J\u0010\u0010\u0013\u001a\u00020\u00072\u0006\u0010\u0014\u001a\u00020\fH'¨\u0006\u0015"}, d2 = {"Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "", "()V", "countAll", "Lio/reactivex/Flowable;", "", "deleteAll", "", "deleteNotification", "messageId", "getAll", "", "Lcom/forasoft/homewavvisitor/model/data/Notification;", "getByType", "type", "", "getCallNotification", "Lio/reactivex/Single;", "callId", "saveNotification", "notification", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: NotificationDao.kt */
public abstract class NotificationDao {
    public abstract Flowable<Integer> countAll();

    public abstract void deleteAll();

    public abstract void deleteNotification(int i);

    public abstract Flowable<List<Notification>> getAll();

    public abstract Flowable<List<Notification>> getByType(String str);

    public abstract void saveNotification(Notification notification);

    public Single<Notification> getCallNotification(String str) {
        Intrinsics.checkParameterIsNotNull(str, "callId");
        Single<Notification> create = Single.create(new NotificationDao$getCallNotification$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(create, "Single.create<Notificati…otification\")))\n        }");
        return create;
    }
}
