package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.account.ScheduledResponse;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.model.server.response.Response;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00050\u0001H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "it", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledResponse;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: VisitsPresenter.kt */
final class VisitsPresenter$getVisits$1<T, R> implements Function<T, R> {
    public static final VisitsPresenter$getVisits$1 INSTANCE = new VisitsPresenter$getVisits$1();

    VisitsPresenter$getVisits$1() {
    }

    public final Response<List<ScheduledVisit>> apply(Response<ScheduledResponse> response) {
        Intrinsics.checkParameterIsNotNull(response, "it");
        boolean ok = response.getOk();
        ScheduledResponse body = response.getBody();
        if (body == null) {
            Intrinsics.throwNpe();
        }
        return new Response<>(ok, body.getScheduled(), response.getError());
    }
}
