package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.presentation.view.home.InmateDetailView;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\n\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0003*\u0004\u0018\u00010\u00010\u0001H\n¢\u0006\u0004\b\u0004\u0010\u0005"}, d2 = {"<anonymous>", "", "it", "kotlin.jvm.PlatformType", "accept", "(Lkotlin/Unit;)V"}, k = 3, mv = {1, 1, 16})
/* compiled from: InmateDetailPresenter.kt */
final class InmateDetailPresenter$onDeleteSuccess$2<T> implements Consumer<Unit> {
    final /* synthetic */ InmateDetailPresenter this$0;

    InmateDetailPresenter$onDeleteSuccess$2(InmateDetailPresenter inmateDetailPresenter) {
        this.this$0 = inmateDetailPresenter;
    }

    public final void accept(Unit unit) {
        Analytics access$getAnalytics$p = this.this$0.analytics;
        String prison_name = this.this$0.inmate.getPrison_name();
        if (prison_name == null) {
            Intrinsics.throwNpe();
        }
        String prison_state = this.this$0.inmate.getPrison_state();
        if (prison_state == null) {
            Intrinsics.throwNpe();
        }
        access$getAnalytics$p.onDeleteInmate(prison_name, prison_state);
        Object value = this.this$0.subject.getValue();
        Intrinsics.checkExpressionValueIsNotNull(value, "subject.value");
        Collection arrayList = new ArrayList();
        for (Object next : (Iterable) value) {
            if (!Intrinsics.areEqual((Object) ((Inmate) next).getId(), (Object) this.this$0.inmate.getId())) {
                arrayList.add(next);
            }
        }
        this.this$0.subject.onNext((List) arrayList);
        ((InmateDetailView) this.this$0.getViewState()).showMessage("Deleted successfully");
        this.this$0.router.exit();
    }
}
