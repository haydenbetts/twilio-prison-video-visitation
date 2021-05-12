package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.account.ManageCardsView;
import io.reactivex.functions.BiConsumer;
import java.util.Map;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u000122\u0010\u0002\u001a.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004 \u0007*\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0018\u00010\u00030\u00032\u000e\u0010\b\u001a\n \u0007*\u0004\u0018\u00010\t0\tH\n¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ManageCardsPresenter.kt */
final class ManageCardsPresenter$onDeleteCardClicked$1<T1, T2> implements BiConsumer<ApiResponse<? extends Map<String, ? extends Boolean>>, Throwable> {
    final /* synthetic */ ManageCardsPresenter this$0;

    ManageCardsPresenter$onDeleteCardClicked$1(ManageCardsPresenter manageCardsPresenter) {
        this.this$0 = manageCardsPresenter;
    }

    public final void accept(ApiResponse<? extends Map<String, Boolean>> apiResponse, Throwable th) {
        ((ManageCardsView) this.this$0.getViewState()).hideProgress();
    }
}
