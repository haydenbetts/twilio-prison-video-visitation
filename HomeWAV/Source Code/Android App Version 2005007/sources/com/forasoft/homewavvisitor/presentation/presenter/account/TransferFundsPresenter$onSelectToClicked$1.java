package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.navigation.ResultListener;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/presentation/presenter/account/TransferFundsPresenter$onSelectToClicked$1", "Lcom/forasoft/homewavvisitor/navigation/ResultListener;", "onResult", "", "resultData", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TransferFundsPresenter.kt */
public final class TransferFundsPresenter$onSelectToClicked$1 implements ResultListener {
    final /* synthetic */ TransferFundsPresenter this$0;

    TransferFundsPresenter$onSelectToClicked$1(TransferFundsPresenter transferFundsPresenter) {
        this.this$0 = transferFundsPresenter;
    }

    public void onResult(Object obj) {
        CompositeDisposable access$getDisposables$p = this.this$0.getDisposables();
        Disposable subscribe = this.this$0.facilitiesSubject.subscribe(new TransferFundsPresenter$onSelectToClicked$1$onResult$1(this, obj));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "facilitiesSubject.subscr…tener()\n                }");
        DisposableKt.plusAssign(access$getDisposables$p, subscribe);
    }
}
