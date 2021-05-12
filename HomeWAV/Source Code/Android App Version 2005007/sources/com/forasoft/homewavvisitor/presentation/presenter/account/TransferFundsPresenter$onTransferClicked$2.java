package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: TransferFundsPresenter.kt */
final class TransferFundsPresenter$onTransferClicked$2<T> implements Consumer<Throwable> {
    final /* synthetic */ TransferFundsPresenter this$0;

    TransferFundsPresenter$onTransferClicked$2(TransferFundsPresenter transferFundsPresenter) {
        this.this$0 = transferFundsPresenter;
    }

    public final void accept(Throwable th) {
        ((TransferFundsView) this.this$0.getViewState()).hideProgressDialog();
    }
}
