package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.presentation.view.account.RefundsView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: RefundsPresenter.kt */
final class RefundsPresenter$onFirstViewAttach$3<T> implements Consumer<List<? extends String>> {
    final /* synthetic */ RefundsPresenter this$0;

    RefundsPresenter$onFirstViewAttach$3(RefundsPresenter refundsPresenter) {
        this.this$0 = refundsPresenter;
    }

    public final void accept(List<String> list) {
        Intrinsics.checkExpressionValueIsNotNull(list, "it");
        ((RefundsView) this.this$0.getViewState()).showInmates(list);
    }
}
