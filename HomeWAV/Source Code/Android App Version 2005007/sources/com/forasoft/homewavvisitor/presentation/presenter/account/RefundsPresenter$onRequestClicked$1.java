package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.server.response.Cause;
import com.forasoft.homewavvisitor.model.server.response.Errors;
import com.forasoft.homewavvisitor.model.server.response.RefundError;
import com.forasoft.homewavvisitor.model.server.response.RefundResponse;
import com.forasoft.homewavvisitor.presentation.view.account.RefundsView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/RefundResponse;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: RefundsPresenter.kt */
final class RefundsPresenter$onRequestClicked$1<T> implements Consumer<RefundResponse> {
    final /* synthetic */ RefundsPresenter this$0;

    RefundsPresenter$onRequestClicked$1(RefundsPresenter refundsPresenter) {
        this.this$0 = refundsPresenter;
    }

    public final void accept(RefundResponse refundResponse) {
        Errors errors;
        RefundError refund;
        List<String> amount;
        String str;
        if (refundResponse.getOk()) {
            ((RefundsView) this.this$0.getViewState()).showMessage("Refund request sent");
            this.this$0.router.exit();
            return;
        }
        Cause cause = refundResponse.getCause();
        if (cause != null && (errors = cause.getErrors()) != null && (refund = errors.getRefund()) != null && (amount = refund.getAmount()) != null && (str = amount.get(0)) != null) {
            ((RefundsView) this.this$0.getViewState()).showMessage(str);
        }
    }
}
