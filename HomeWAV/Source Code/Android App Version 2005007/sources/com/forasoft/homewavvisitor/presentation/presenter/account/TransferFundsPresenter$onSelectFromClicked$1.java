package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.navigation.ResultListener;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/presentation/presenter/account/TransferFundsPresenter$onSelectFromClicked$1", "Lcom/forasoft/homewavvisitor/navigation/ResultListener;", "onResult", "", "resultData", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TransferFundsPresenter.kt */
public final class TransferFundsPresenter$onSelectFromClicked$1 implements ResultListener {
    final /* synthetic */ TransferFundsPresenter this$0;

    TransferFundsPresenter$onSelectFromClicked$1(TransferFundsPresenter transferFundsPresenter) {
        this.this$0 = transferFundsPresenter;
    }

    public void onResult(Object obj) {
        this.this$0.facilitiesSubject.subscribe(new TransferFundsPresenter$onSelectFromClicked$1$onResult$1(this, obj));
    }
}
