package com.forasoft.homewavvisitor.model.repository;

import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.Notification;
import com.forasoft.homewavvisitor.model.data.payment.Credit;
import com.forasoft.homewavvisitor.model.notifications.MessageListenerService;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.concurrent.Callable;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "", "kotlin.jvm.PlatformType", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentRepository.kt */
final class PaymentRepository$saveCredit$4<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ Credit $credit;
    final /* synthetic */ PaymentRepository this$0;

    PaymentRepository$saveCredit$4(PaymentRepository paymentRepository, Credit credit) {
        this.this$0 = paymentRepository;
        this.$credit = credit;
    }

    public final Single<Unit> apply(final Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        return Single.fromCallable(new Callable<T>(this) {
            final /* synthetic */ PaymentRepository$saveCredit$4 this$0;

            {
                this.this$0 = r1;
            }

            public final void call() {
                NotificationDao access$getNotificationDao$p = this.this$0.this$0.notificationDao;
                int parseInt = Integer.parseInt(this.this$0.$credit.getId());
                access$getNotificationDao$p.saveNotification(new Notification(parseInt, MessageListenerService.TYPE_ADDED_FUNDS, StringsKt.trimIndent("\n                                        {\n                                        \"type\": \"added_funds\",\n                                        \"inmate_id\": \"" + inmate.getId() + "\",\n                                        \"inmate_name\": \"" + inmate.getFull_name() + "\",\n                                        \"value\": \"" + this.this$0.$credit.getValue() + "\"\n                                        }\n                                    "), this.this$0.$credit.getCreated()));
            }
        });
    }
}
