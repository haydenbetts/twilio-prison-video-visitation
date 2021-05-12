package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.presentation.view.account.TransferFundsView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import okhttp3.ResponseBody;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lokhttp3/ResponseBody;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: TransferFundsPresenter.kt */
final class TransferFundsPresenter$onTransferClicked$1<T> implements Consumer<ResponseBody> {
    final /* synthetic */ TransferFundsPresenter this$0;

    TransferFundsPresenter$onTransferClicked$1(TransferFundsPresenter transferFundsPresenter) {
        this.this$0 = transferFundsPresenter;
    }

    public final void accept(ResponseBody responseBody) {
        ((TransferFundsView) this.this$0.getViewState()).hideProgressDialog();
        this.this$0.router.exit();
    }
}
