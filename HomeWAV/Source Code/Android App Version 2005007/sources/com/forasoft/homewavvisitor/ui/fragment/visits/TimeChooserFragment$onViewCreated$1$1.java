package com.forasoft.homewavvisitor.ui.fragment.visits;

import com.forasoft.homewavvisitor.presentation.presenter.visits.TimeChooserPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "", "Lkotlin/ParameterName;", "name", "start", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: TimeChooserFragment.kt */
final /* synthetic */ class TimeChooserFragment$onViewCreated$1$1 extends FunctionReference implements Function1<Long, Unit> {
    TimeChooserFragment$onViewCreated$1$1(TimeChooserPresenter timeChooserPresenter) {
        super(1, timeChooserPresenter);
    }

    public final String getName() {
        return "onTimeSelected";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(TimeChooserPresenter.class);
    }

    public final String getSignature() {
        return "onTimeSelected(J)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke(((Number) obj).longValue());
        return Unit.INSTANCE;
    }

    public final void invoke(long j) {
        ((TimeChooserPresenter) this.receiver).onTimeSelected(j);
    }
}
