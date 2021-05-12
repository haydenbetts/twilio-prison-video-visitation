package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.SupportInfo;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.account.HelpView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/SupportInfo;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: HelpPresenter.kt */
final class HelpPresenter$onFirstViewAttach$3<T> implements Consumer<ApiResponse<? extends SupportInfo>> {
    final /* synthetic */ HelpPresenter this$0;

    HelpPresenter$onFirstViewAttach$3(HelpPresenter helpPresenter) {
        this.this$0 = helpPresenter;
    }

    public final void accept(ApiResponse<SupportInfo> apiResponse) {
        if (apiResponse.getOk()) {
            HelpView helpView = (HelpView) this.this$0.getViewState();
            SupportInfo body = apiResponse.getBody();
            if (body == null) {
                Intrinsics.throwNpe();
            }
            helpView.showQuestions(body.getQuestions());
            ((HelpView) this.this$0.getViewState()).showContacts(apiResponse.getBody().getContacts());
        }
    }
}
