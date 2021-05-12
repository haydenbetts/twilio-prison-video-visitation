package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.account.ManageCardsView;
import io.reactivex.functions.BiConsumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0007\u001a\n \u0006*\u0004\u0018\u00010\b0\bH\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ManageCardsPresenter.kt */
final class ManageCardsPresenter$onUpdateCardStatus$2<T1, T2> implements BiConsumer<ApiResponse<? extends List<? extends Card>>, Throwable> {
    final /* synthetic */ ManageCardsPresenter this$0;

    ManageCardsPresenter$onUpdateCardStatus$2(ManageCardsPresenter manageCardsPresenter) {
        this.this$0 = manageCardsPresenter;
    }

    public final void accept(ApiResponse<? extends List<Card>> apiResponse, Throwable th) {
        ((ManageCardsView) this.this$0.getViewState()).hideProgress();
    }
}
