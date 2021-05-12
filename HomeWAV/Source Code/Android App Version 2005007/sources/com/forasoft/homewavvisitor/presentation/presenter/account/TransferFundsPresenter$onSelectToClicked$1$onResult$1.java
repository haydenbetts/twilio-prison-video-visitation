package com.forasoft.homewavvisitor.presentation.presenter.account;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView;
import io.reactivex.functions.Consumer;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "facilities", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: TransferFundsPresenter.kt */
final class TransferFundsPresenter$onSelectToClicked$1$onResult$1<T> implements Consumer<List<? extends Facility>> {
    final /* synthetic */ Object $resultData;
    final /* synthetic */ TransferFundsPresenter$onSelectToClicked$1 this$0;

    TransferFundsPresenter$onSelectToClicked$1$onResult$1(TransferFundsPresenter$onSelectToClicked$1 transferFundsPresenter$onSelectToClicked$1, Object obj) {
        this.this$0 = transferFundsPresenter$onSelectToClicked$1;
        this.$resultData = obj;
    }

    public final void accept(List<Facility> list) {
        Facility facility;
        Object obj;
        Object obj2 = this.$resultData;
        if (obj2 != null) {
            Inmate inmate = (Inmate) obj2;
            String str = null;
            if (list != null) {
                Iterator it = list.iterator();
                while (true) {
                    if (!it.hasNext()) {
                        obj = null;
                        break;
                    }
                    obj = it.next();
                    if (Intrinsics.areEqual((Object) ((Facility) obj).getId(), (Object) inmate.getPrison_id())) {
                        break;
                    }
                }
                facility = (Facility) obj;
            } else {
                facility = null;
            }
            if (facility != null) {
                str = facility.getTalk_to_me_credits_disabled();
            }
            if (Intrinsics.areEqual((Object) str, (Object) "0")) {
                this.this$0.this$0.inmateTo = inmate;
                ((TransferFundsView) this.this$0.this$0.getViewState()).showToInmate(inmate);
                ((TransferFundsView) this.this$0.this$0.getViewState()).showTransferFee(facility.getTransfer_fee());
            } else {
                ((TransferFundsView) this.this$0.this$0.getViewState()).showMessage(R.string.option_is_on_available);
            }
            this.this$0.this$0.clearResultListener();
            return;
        }
        throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.model.data.Inmate");
    }
}
