package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView;
import io.reactivex.functions.Consumer;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/State;", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: TransferFundsPresenter.kt */
final class TransferFundsPresenter$onFirstViewAttach$3<T> implements Consumer<State> {
    final /* synthetic */ TransferFundsPresenter this$0;

    TransferFundsPresenter$onFirstViewAttach$3(TransferFundsPresenter transferFundsPresenter) {
        this.this$0 = transferFundsPresenter;
    }

    public final void accept(State state) {
        if (state != null) {
            if (this.this$0.inmateFrom != null) {
                TransferFundsPresenter transferFundsPresenter = this.this$0;
                Inmate access$getInmateFrom$p = transferFundsPresenter.inmateFrom;
                if (access$getInmateFrom$p == null) {
                    Intrinsics.throwNpe();
                }
                Map<String, String> status = state.getStatus();
                Inmate access$getInmateFrom$p2 = this.this$0.inmateFrom;
                if (access$getInmateFrom$p2 == null) {
                    Intrinsics.throwNpe();
                }
                transferFundsPresenter.inmateFrom = Inmate.copy$default(access$getInmateFrom$p, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, status.get(access$getInmateFrom$p2.getId()), (String) null, (String) null, false, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, false, (String) null, (String) null, -131073, -1, 63, (Object) null);
                TransferFundsView transferFundsView = (TransferFundsView) this.this$0.getViewState();
                Inmate access$getInmateFrom$p3 = this.this$0.inmateFrom;
                if (access$getInmateFrom$p3 == null) {
                    Intrinsics.throwNpe();
                }
                transferFundsView.showFromInmate(access$getInmateFrom$p3);
            }
            if (this.this$0.inmateTo != null) {
                TransferFundsPresenter transferFundsPresenter2 = this.this$0;
                Inmate access$getInmateTo$p = transferFundsPresenter2.inmateTo;
                if (access$getInmateTo$p == null) {
                    Intrinsics.throwNpe();
                }
                Map<String, String> status2 = state.getStatus();
                Inmate access$getInmateTo$p2 = this.this$0.inmateTo;
                if (access$getInmateTo$p2 == null) {
                    Intrinsics.throwNpe();
                }
                transferFundsPresenter2.inmateTo = Inmate.copy$default(access$getInmateTo$p, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, status2.get(access$getInmateTo$p2.getId()), (String) null, (String) null, false, false, false, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, (String) null, false, false, (String) null, (String) null, -131073, -1, 63, (Object) null);
                TransferFundsView transferFundsView2 = (TransferFundsView) this.this$0.getViewState();
                Inmate access$getInmateTo$p3 = this.this$0.inmateTo;
                if (access$getInmateTo$p3 == null) {
                    Intrinsics.throwNpe();
                }
                transferFundsView2.showToInmate(access$getInmateTo$p3);
            }
        }
    }
}
