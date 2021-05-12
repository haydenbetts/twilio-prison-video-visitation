package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.model.server.response.Terms;
import com.forasoft.homewavvisitor.presentation.view.account.TermConditionsView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/server/response/Terms;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: TermConditionsPresenter.kt */
final class TermConditionsPresenter$getTerms$1<T> implements Consumer<ApiResponse<? extends Terms>> {
    final /* synthetic */ TermConditionsPresenter this$0;

    TermConditionsPresenter$getTerms$1(TermConditionsPresenter termConditionsPresenter) {
        this.this$0 = termConditionsPresenter;
    }

    public final void accept(ApiResponse<Terms> apiResponse) {
        if (apiResponse.getOk()) {
            Terms body = apiResponse.getBody();
            String terms_text = body != null ? body.getTerms_text() : null;
            if (terms_text != null) {
                ((TermConditionsView) this.this$0.getViewState()).showTerms(terms_text);
            }
        }
    }
}
