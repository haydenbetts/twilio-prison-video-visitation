package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.model.server.response.Response;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "Lkotlin/ParameterName;", "name", "response", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: VisitsPresenter.kt */
final /* synthetic */ class VisitsPresenter$getVisits$2 extends FunctionReference implements Function1<Response<? extends List<? extends ScheduledVisit>>, Unit> {
    VisitsPresenter$getVisits$2(VisitsPresenter visitsPresenter) {
        super(1, visitsPresenter);
    }

    public final String getName() {
        return "handleSuccess";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(VisitsPresenter.class);
    }

    public final String getSignature() {
        return "handleSuccess(Lcom/forasoft/homewavvisitor/model/server/response/Response;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Response<? extends List<ScheduledVisit>>) (Response) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Response<? extends List<ScheduledVisit>> response) {
        Intrinsics.checkParameterIsNotNull(response, "p1");
        ((VisitsPresenter) this.receiver).handleSuccess(response);
    }
}
