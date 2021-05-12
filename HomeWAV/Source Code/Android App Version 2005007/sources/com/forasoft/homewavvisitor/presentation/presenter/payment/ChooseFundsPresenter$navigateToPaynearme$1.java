package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.model.data.PaymentRequestData;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.navigation.Screens;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/register/InmateByVisitor;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsPresenter.kt */
final class ChooseFundsPresenter$navigateToPaynearme$1<T> implements Consumer<InmateByVisitor> {
    final /* synthetic */ Float $realAmount;
    final /* synthetic */ ChooseFundsPresenter this$0;

    ChooseFundsPresenter$navigateToPaynearme$1(ChooseFundsPresenter chooseFundsPresenter, Float f) {
        this.this$0 = chooseFundsPresenter;
        this.$realAmount = f;
    }

    public final void accept(InmateByVisitor inmateByVisitor) {
        Float f = this.$realAmount;
        String id = inmateByVisitor.getId();
        String occupant_id = inmateByVisitor.getOccupant_id();
        String access$getCurrentPaymentScope$p = this.this$0.currentPaymentScope;
        if (access$getCurrentPaymentScope$p == null) {
            Intrinsics.throwNpe();
        }
        this.this$0.router.navigateTo(new Screens.PayNearMeScreen(new PaymentRequestData(f, id, occupant_id, access$getCurrentPaymentScope$p, inmateByVisitor.getVisitorEmail())));
    }
}
