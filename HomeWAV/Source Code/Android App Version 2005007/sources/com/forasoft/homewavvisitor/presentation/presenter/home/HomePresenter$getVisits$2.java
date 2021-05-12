package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.model.server.response.Response;
import io.reactivex.Observable;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012!\u0010\u0002\u001a\u001d\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u00040\u0003¢\u0006\f\b\u0006\u0012\b\b\u0007\u0012\u0004\b\b(\b¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "p1", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "Lkotlin/ParameterName;", "name", "response", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: HomePresenter.kt */
final /* synthetic */ class HomePresenter$getVisits$2 extends FunctionReference implements Function1<Response<? extends List<? extends ScheduledVisit>>, Unit> {
    HomePresenter$getVisits$2(HomePresenter homePresenter) {
        super(1, homePresenter);
    }

    public final String getName() {
        return "handleSuccess";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(HomePresenter.class);
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
        HomePresenter homePresenter = (HomePresenter) this.receiver;
        if (response.getOk()) {
            Class<ScheduledVisit> cls = ScheduledVisit.class;
            if (Intrinsics.areEqual((Object) cls, (Object) Inmate.class)) {
                Object body = response.getBody();
                if (body != null) {
                    List list = (List) body;
                    Observable fromCallable = Observable.fromCallable(new HomePresenter$handleSuccess$1(homePresenter, list));
                    Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Observable.fromCallable …es)\n                    }");
                    CommonKt.applyAsync(fromCallable).subscribe(HomePresenter$handleSuccess$2.INSTANCE);
                    homePresenter.setAudioVideoTags(list);
                    homePresenter.inmatesSubject.onNext(list);
                    homePresenter.updateTagsAndShowInmatesList(homePresenter.heartbeatRepository.getHeartbeatState().getValue());
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.forasoft.homewavvisitor.model.data.Inmate>");
            } else if (Intrinsics.areEqual((Object) cls, (Object) ScheduledVisit.class)) {
                Object body2 = response.getBody();
                if (body2 != null) {
                    Observable fromCallable2 = Observable.fromCallable(new HomePresenter$handleSuccess$3(homePresenter, (List) body2));
                    Intrinsics.checkExpressionValueIsNotNull(fromCallable2, "Observable.fromCallable …ts)\n                    }");
                    CommonKt.applyAsync(fromCallable2).subscribe(HomePresenter$handleSuccess$4.INSTANCE);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.forasoft.homewavvisitor.model.data.account.ScheduledVisit>");
            }
        } else {
            homePresenter.handleError(new Throwable(response.getError()));
        }
    }
}
