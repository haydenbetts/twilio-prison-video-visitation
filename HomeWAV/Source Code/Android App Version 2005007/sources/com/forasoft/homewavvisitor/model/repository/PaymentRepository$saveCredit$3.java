package com.forasoft.homewavvisitor.model.repository;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.payment.Credit;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0002H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "inmate", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentRepository.kt */
final class PaymentRepository$saveCredit$3<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ Credit $credit;
    final /* synthetic */ PaymentRepository this$0;

    PaymentRepository$saveCredit$3(PaymentRepository paymentRepository, Credit credit) {
        this.this$0 = paymentRepository;
        this.$credit = credit;
    }

    public final Single<Inmate> apply(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        String credit_balance = inmate.getCredit_balance();
        this.this$0.inmateDao.updateInmate(Inmate.copy$default(inmate, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, String.valueOf((credit_balance != null ? Float.parseFloat(credit_balance) : 0.0f) + Float.parseFloat(this.$credit.getValue())), (String) null, (String) null, (String) null, false, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, false, (String) null, (String) null, -65537, -1, 63, (Object) null));
        return Single.just(inmate);
    }
}
