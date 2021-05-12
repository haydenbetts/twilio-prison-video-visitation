package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.register.InmateByVisitor;
import com.forasoft.homewavvisitor.navigation.Screens;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: NotificationsPresenter.kt */
final class NotificationsPresenter$onAddFundsClicked$1<T> implements Consumer<Inmate> {
    final /* synthetic */ NotificationsPresenter this$0;

    NotificationsPresenter$onAddFundsClicked$1(NotificationsPresenter notificationsPresenter) {
        this.this$0 = notificationsPresenter;
    }

    public final void accept(Inmate inmate) {
        String approved = inmate.getApproved();
        if (approved == null) {
            Intrinsics.throwNpe();
        }
        String credit_balance = inmate.getCredit_balance();
        if (credit_balance == null) {
            Intrinsics.throwNpe();
        }
        String first_name = inmate.getFirst_name();
        if (first_name == null) {
            Intrinsics.throwNpe();
        }
        String id = inmate.getId();
        String last_name = inmate.getLast_name();
        if (last_name == null) {
            Intrinsics.throwNpe();
        }
        String occupant_id = inmate.getOccupant_id();
        String prison_id = inmate.getPrison_id();
        if (prison_id == null) {
            Intrinsics.throwNpe();
        }
        String prison_payment_gateway = inmate.getPrison_payment_gateway();
        if (prison_payment_gateway == null) {
            Intrinsics.throwNpe();
        }
        this.this$0.paymentGateway.getSelectedConnectionSubject().onNext(new InmateByVisitor(approved, credit_balance, first_name, id, last_name, occupant_id, prison_id, prison_payment_gateway, inmate.isFraud(), "", (String) null, 1024, (DefaultConstructorMarker) null));
        this.this$0.router.navigateTo(Screens.ChooseFundsScreen.INSTANCE);
    }
}
