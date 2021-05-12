package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.view.home.InmateDetailView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateDetailPresenter.kt */
final class InmateDetailPresenter$onFirstViewAttach$3<T> implements Consumer<Inmate> {
    final /* synthetic */ InmateDetailPresenter this$0;

    InmateDetailPresenter$onFirstViewAttach$3(InmateDetailPresenter inmateDetailPresenter) {
        this.this$0 = inmateDetailPresenter;
    }

    public final void accept(Inmate inmate) {
        if (!Intrinsics.areEqual((Object) inmate, (Object) this.this$0.inmate)) {
            InmateDetailView inmateDetailView = (InmateDetailView) this.this$0.getViewState();
            String credit_balance = inmate.getCredit_balance();
            if (credit_balance == null) {
                credit_balance = "0";
            }
            inmateDetailView.setCredits(credit_balance);
            InmateDetailView inmateDetailView2 = (InmateDetailView) this.this$0.getViewState();
            String status = inmate.getStatus();
            if (status == null) {
                status = "red";
            }
            inmateDetailView2.setStatus(status);
            if (!Intrinsics.areEqual((Object) this.this$0.inmate.getInvisible(), (Object) inmate.getInvisible())) {
                this.this$0.inmate.setInvisible(inmate.getInvisible());
                ((InmateDetailView) this.this$0.getViewState()).setInvisible(this.this$0.isVisible());
            }
        }
    }
}
