package com.forasoft.homewavvisitor.dao;

import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.data.Notification;
import com.google.gson.Gson;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0014\u0010\u0002\u001a\u0010\u0012\f\u0012\n \u0005*\u0004\u0018\u00010\u00040\u00040\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "emitter", "Lio/reactivex/SingleEmitter;", "Lcom/forasoft/homewavvisitor/model/data/Notification;", "kotlin.jvm.PlatformType", "subscribe"}, k = 3, mv = {1, 1, 16})
/* compiled from: NotificationDao.kt */
final class NotificationDao$getCallNotification$1<T> implements SingleOnSubscribe<T> {
    final /* synthetic */ String $callId;
    final /* synthetic */ NotificationDao this$0;

    NotificationDao$getCallNotification$1(NotificationDao notificationDao, String str) {
        this.this$0 = notificationDao;
        this.$callId = str;
    }

    public final void subscribe(SingleEmitter<Notification> singleEmitter) {
        Object obj;
        Intrinsics.checkParameterIsNotNull(singleEmitter, "emitter");
        List blockingFirst = this.this$0.getByType("incoming_call").blockingFirst();
        Gson gson = new Gson();
        Intrinsics.checkExpressionValueIsNotNull(blockingFirst, "notifications");
        Iterable<Notification> iterable = blockingFirst;
        Collection arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(iterable, 10));
        for (Notification notification : iterable) {
            arrayList.add(TuplesKt.to(notification, gson.fromJson(notification.getBody(), Call.class)));
        }
        Iterator it = ((List) arrayList).iterator();
        while (true) {
            if (!it.hasNext()) {
                obj = null;
                break;
            }
            obj = it.next();
            if (Intrinsics.areEqual((Object) ((Call) ((Pair) obj).getSecond()).getId(), (Object) this.$callId)) {
                break;
            }
        }
        Pair pair = (Pair) obj;
        if (pair != null) {
            singleEmitter.onSuccess(pair.getFirst());
        } else {
            singleEmitter.onError(new Throwable(new NoSuchElementException("cant find this notification")));
        }
    }
}
