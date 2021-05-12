package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.model.data.Empty;
import com.forasoft.homewavvisitor.model.server.response.Response;
import com.forasoft.homewavvisitor.presentation.view.visits.VisitDetailsView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.concurrent.ThreadsKt;
import kotlin.jvm.functions.Function0;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "Lcom/forasoft/homewavvisitor/model/data/Empty;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VisitDetailsPresenter.kt */
final class VisitDetailsPresenter$onCancelConfirmed$1<T> implements Consumer<Response<? extends Empty>> {
    final /* synthetic */ VisitDetailsPresenter this$0;

    VisitDetailsPresenter$onCancelConfirmed$1(VisitDetailsPresenter visitDetailsPresenter) {
        this.this$0 = visitDetailsPresenter;
    }

    public final void accept(Response<Empty> response) {
        if (response.getOk()) {
            ThreadsKt.thread$default(false, false, (ClassLoader) null, (String) null, 0, new Function0<Unit>(this) {
                final /* synthetic */ VisitDetailsPresenter$onCancelConfirmed$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void invoke() {
                    this.this$0.this$0.visitDao.deleteVisitById(this.this$0.this$0.visitId);
                }
            }, 31, (Object) null);
            ((VisitDetailsView) this.this$0.getViewState()).showMessage("Visit cancelled");
            this.this$0.router.exit();
        }
    }
}
