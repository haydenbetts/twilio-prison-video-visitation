package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.model.data.Facility;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.home.PendingInmatesView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Facility;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: PendingInmatesPresenter.kt */
final class PendingInmatesPresenter$onAddFundsClicked$2<T> implements Consumer<ApiResponse<? extends List<? extends Facility>>> {
    final /* synthetic */ PendingInmatesPresenter this$0;

    PendingInmatesPresenter$onAddFundsClicked$2(PendingInmatesPresenter pendingInmatesPresenter) {
        this.this$0 = pendingInmatesPresenter;
    }

    public final void accept(ApiResponse<? extends List<Facility>> apiResponse) {
        ((PendingInmatesView) this.this$0.getViewState()).showProgress(false);
    }
}
