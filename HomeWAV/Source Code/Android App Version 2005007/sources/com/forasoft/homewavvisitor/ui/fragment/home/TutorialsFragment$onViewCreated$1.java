package com.forasoft.homewavvisitor.ui.fragment.home;

import com.forasoft.homewavvisitor.presentation.presenter.home.TutorialsPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "", "Lkotlin/ParameterName;", "name", "url", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: TutorialsFragment.kt */
final /* synthetic */ class TutorialsFragment$onViewCreated$1 extends FunctionReference implements Function1<String, Unit> {
    TutorialsFragment$onViewCreated$1(TutorialsPresenter tutorialsPresenter) {
        super(1, tutorialsPresenter);
    }

    public final String getName() {
        return "onTutorialClicked";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(TutorialsPresenter.class);
    }

    public final String getSignature() {
        return "onTutorialClicked(Ljava/lang/String;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((String) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(String str) {
        Intrinsics.checkParameterIsNotNull(str, "p1");
        ((TutorialsPresenter) this.receiver).onTutorialClicked(str);
    }
}
