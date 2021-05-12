package com.forasoft.homewavvisitor.presentation.presenter.account;

import io.reactivex.functions.BiFunction;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000 \n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\b\n\u0002\b\t\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u0002\"\u0004\b\u0001\u0010\u0003\"\u0004\b\u0002\u0010\u00012\u0006\u0010\u0004\u001a\u0002H\u00022\u0006\u0010\u0005\u001a\u0002H\u0003H\n¢\u0006\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"<anonymous>", "R", "T1", "T2", "t1", "t2", "apply", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "io/reactivex/rxkotlin/Observables$combineLatest$1"}, k = 3, mv = {1, 1, 16})
/* compiled from: Observables.kt */
public final class NotificationsPresenter$onFirstViewAttach$$inlined$combineLatest$1<T1, T2, R> implements BiFunction<T1, T2, R> {
    final /* synthetic */ NotificationsPresenter this$0;

    public NotificationsPresenter$onFirstViewAttach$$inlined$combineLatest$1(NotificationsPresenter notificationsPresenter) {
        this.this$0 = notificationsPresenter;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:5:0x003e, code lost:
        r4 = r6.getStatus();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final R apply(T1 r6, T2 r7) {
        /*
            r5 = this;
            java.util.List r7 = (java.util.List) r7
            com.forasoft.homewavvisitor.model.data.State r6 = (com.forasoft.homewavvisitor.model.data.State) r6
            java.lang.String r0 = "notifications"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r7, r0)
            java.lang.Iterable r7 = (java.lang.Iterable) r7
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = kotlin.collections.CollectionsKt.collectionSizeOrDefault(r7, r1)
            r0.<init>(r1)
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r7 = r7.iterator()
        L_0x001c:
            boolean r1 = r7.hasNext()
            if (r1 == 0) goto L_0x0057
            java.lang.Object r1 = r7.next()
            com.forasoft.homewavvisitor.model.data.Notification r1 = (com.forasoft.homewavvisitor.model.data.Notification) r1
            com.forasoft.homewavvisitor.presentation.presenter.account.NotificationsPresenter r2 = r5.this$0
            com.google.gson.Gson r2 = r2.gson
            java.lang.String r3 = r1.getBody()
            java.lang.Class<com.forasoft.homewavvisitor.model.data.NotificationBody> r4 = com.forasoft.homewavvisitor.model.data.NotificationBody.class
            java.lang.Object r2 = r2.fromJson((java.lang.String) r3, r4)
            com.forasoft.homewavvisitor.model.data.NotificationBody r2 = (com.forasoft.homewavvisitor.model.data.NotificationBody) r2
            com.forasoft.homewavvisitor.presentation.presenter.account.NotificationWithInmateStatus r3 = new com.forasoft.homewavvisitor.presentation.presenter.account.NotificationWithInmateStatus
            if (r6 == 0) goto L_0x004f
            java.util.Map r4 = r6.getStatus()
            if (r4 == 0) goto L_0x004f
            java.lang.String r2 = r2.getInmateId()
            java.lang.Object r2 = r4.get(r2)
            java.lang.String r2 = (java.lang.String) r2
            goto L_0x0050
        L_0x004f:
            r2 = 0
        L_0x0050:
            r3.<init>(r1, r2)
            r0.add(r3)
            goto L_0x001c
        L_0x0057:
            java.util.List r0 = (java.util.List) r0
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.account.NotificationsPresenter$onFirstViewAttach$$inlined$combineLatest$1.apply(java.lang.Object, java.lang.Object):java.lang.Object");
    }
}
