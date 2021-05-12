package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.functions.Consumer;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u000122\u0010\u0002\u001a.\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004 \u0007*\u0016\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ManageCardsPresenter.kt */
final class ManageCardsPresenter$onDeleteCardClicked$2<T> implements Consumer<ApiResponse<? extends Map<String, ? extends Boolean>>> {
    final /* synthetic */ Card $card;
    final /* synthetic */ ManageCardsPresenter this$0;

    ManageCardsPresenter$onDeleteCardClicked$2(ManageCardsPresenter manageCardsPresenter, Card card) {
        this.this$0 = manageCardsPresenter;
        this.$card = card;
    }

    public final void accept(ApiResponse<? extends Map<String, Boolean>> apiResponse) {
        Map map = (Map) apiResponse.getBody();
        if (Intrinsics.areEqual((Object) map != null ? (Boolean) map.get("delete_result") : null, (Object) true)) {
            Object value = this.this$0.cardsSubject.getValue();
            Intrinsics.checkExpressionValueIsNotNull(value, "cardsSubject.value");
            Collection arrayList = new ArrayList();
            for (Object next : (Iterable) value) {
                if (!Intrinsics.areEqual((Object) ((Card) next).getToken(), (Object) this.$card.getToken())) {
                    arrayList.add(next);
                }
            }
            this.this$0.cardsSubject.onNext((List) arrayList);
        }
    }
}
