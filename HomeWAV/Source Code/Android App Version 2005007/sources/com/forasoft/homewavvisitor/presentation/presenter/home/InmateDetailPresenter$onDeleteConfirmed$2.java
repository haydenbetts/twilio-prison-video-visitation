package com.forasoft.homewavvisitor.presentation.presenter.home;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "", "Lkotlin/ParameterName;", "name", "it", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateDetailPresenter.kt */
final /* synthetic */ class InmateDetailPresenter$onDeleteConfirmed$2 extends FunctionReference implements Function1<Throwable, Unit> {
    InmateDetailPresenter$onDeleteConfirmed$2(InmateDetailPresenter inmateDetailPresenter) {
        super(1, inmateDetailPresenter);
    }

    public final String getName() {
        return "onDeleteFailed";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(InmateDetailPresenter.class);
    }

    public final String getSignature() {
        return "onDeleteFailed(Ljava/lang/Throwable;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Throwable) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Throwable th) {
        ((InmateDetailPresenter) this.receiver).onDeleteFailed(th);
    }
}
