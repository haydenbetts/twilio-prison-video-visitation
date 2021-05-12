package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0000\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00020\u00012\u0018\u0010\u0005\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\b0\u00060\u0002H\n¢\u0006\u0002\b\t"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "it", "", "", "", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: ManageCardsPresenter.kt */
final class ManageCardsPresenter$onUpdateCardStatus$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ ManageCardsPresenter this$0;

    ManageCardsPresenter$onUpdateCardStatus$1(ManageCardsPresenter manageCardsPresenter) {
        this.this$0 = manageCardsPresenter;
    }

    public final Single<ApiResponse<List<Card>>> apply(ApiResponse<? extends Map<String, Boolean>> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "it");
        return this.this$0.paymentGateway.getCards();
    }
}
