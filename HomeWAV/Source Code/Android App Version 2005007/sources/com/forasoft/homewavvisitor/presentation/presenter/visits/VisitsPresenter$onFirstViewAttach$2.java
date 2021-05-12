package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.presentation.view.visits.VisitsView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VisitsPresenter.kt */
final class VisitsPresenter$onFirstViewAttach$2<T> implements Consumer<List<? extends ScheduledVisit>> {
    final /* synthetic */ VisitsPresenter this$0;

    VisitsPresenter$onFirstViewAttach$2(VisitsPresenter visitsPresenter) {
        this.this$0 = visitsPresenter;
    }

    public final void accept(List<ScheduledVisit> list) {
        Intrinsics.checkExpressionValueIsNotNull(list, "it");
        ((VisitsView) this.this$0.getViewState()).displayPendingVisits(list);
    }
}
