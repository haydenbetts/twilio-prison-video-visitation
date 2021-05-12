package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.presentation.view.payment.PayNearMeView;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/disposables/Disposable;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: PayNearMePresenter.kt */
final class PayNearMePresenter$requestPaynearmeOrder$1<T> implements Consumer<Disposable> {
    final /* synthetic */ PayNearMePresenter this$0;

    PayNearMePresenter$requestPaynearmeOrder$1(PayNearMePresenter payNearMePresenter) {
        this.this$0 = payNearMePresenter;
    }

    public final void accept(Disposable disposable) {
        ((PayNearMeView) this.this$0.getViewState()).showProgress(true);
    }
}
