package com.forasoft.homewavvisitor.model.repository;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.payment.Credit;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004H\n¢\u0006\u0004\b\u0005\u0010\u0006"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "it", "", "apply", "(Lkotlin/Unit;)Lio/reactivex/Single;"}, k = 3, mv = {1, 1, 16})
/* compiled from: PaymentRepository.kt */
final class PaymentRepository$saveCredit$2<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ Credit $credit;
    final /* synthetic */ PaymentRepository this$0;

    PaymentRepository$saveCredit$2(PaymentRepository paymentRepository, Credit credit) {
        this.this$0 = paymentRepository;
        this.$credit = credit;
    }

    public final Single<Inmate> apply(Unit unit) {
        Intrinsics.checkParameterIsNotNull(unit, "it");
        InmateDao access$getInmateDao$p = this.this$0.inmateDao;
        String inmate_id = this.$credit.getInmate_id();
        if (inmate_id == null) {
            Intrinsics.throwNpe();
        }
        return access$getInmateDao$p.getInmate(inmate_id);
    }
}
