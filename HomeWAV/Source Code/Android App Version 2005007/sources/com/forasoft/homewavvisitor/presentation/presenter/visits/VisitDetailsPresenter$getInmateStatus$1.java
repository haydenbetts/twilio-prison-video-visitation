package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.State;
import com.forasoft.homewavvisitor.presentation.view.visits.VisitDetailsView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/State;", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VisitDetailsPresenter.kt */
final class VisitDetailsPresenter$getInmateStatus$1<T> implements Consumer<State> {
    final /* synthetic */ String $inmateId;
    final /* synthetic */ VisitDetailsPresenter this$0;

    VisitDetailsPresenter$getInmateStatus$1(VisitDetailsPresenter visitDetailsPresenter, String str) {
        this.this$0 = visitDetailsPresenter;
        this.$inmateId = str;
    }

    public final void accept(State state) {
        if (state != null) {
            ((VisitDetailsView) this.this$0.getViewState()).updateInmateStatus(state.getStatus().get(this.$inmateId));
        }
    }
}
