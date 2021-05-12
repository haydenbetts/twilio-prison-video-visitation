package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.presentation.view.home.TutorialsView;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: TutorialsPresenter.kt */
final class TutorialsPresenter$onFirstViewAttach$2 extends Lambda implements Function1<Throwable, Unit> {
    final /* synthetic */ TutorialsPresenter this$0;

    /* JADX INFO: super call moved to the top of the method (can break code semantics) */
    TutorialsPresenter$onFirstViewAttach$2(TutorialsPresenter tutorialsPresenter) {
        super(1);
        this.this$0 = tutorialsPresenter;
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        Intrinsics.checkParameterIsNotNull(th, "it");
        ((TutorialsView) this.this$0.getViewState()).showError();
    }
}
