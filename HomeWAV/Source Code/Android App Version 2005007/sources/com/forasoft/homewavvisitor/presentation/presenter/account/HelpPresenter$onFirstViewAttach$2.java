package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.SupportInfo;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.account.HelpView;
import io.reactivex.functions.BiConsumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0003\n\u0000\u0010\u0000\u001a\u00020\u00012\u001a\u0010\u0002\u001a\u0016\u0012\u0004\u0012\u00020\u0004 \u0005*\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00030\u00032\u000e\u0010\u0006\u001a\n \u0005*\u0004\u0018\u00010\u00070\u0007H\n¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "<anonymous parameter 0>", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/SupportInfo;", "kotlin.jvm.PlatformType", "<anonymous parameter 1>", "", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: HelpPresenter.kt */
final class HelpPresenter$onFirstViewAttach$2<T1, T2> implements BiConsumer<ApiResponse<? extends SupportInfo>, Throwable> {
    final /* synthetic */ HelpPresenter this$0;

    HelpPresenter$onFirstViewAttach$2(HelpPresenter helpPresenter) {
        this.this$0 = helpPresenter;
    }

    public final void accept(ApiResponse<SupportInfo> apiResponse, Throwable th) {
        ((HelpView) this.this$0.getViewState()).showProgress(false);
    }
}
