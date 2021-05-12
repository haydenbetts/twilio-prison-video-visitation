package com.forasoft.homewavvisitor.presentation.presenter.auth;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.auth.VerifyCodeView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012&\u0010\u0002\u001a\"\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004 \u0006*\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyCodePresenter.kt */
final class VerifyCodePresenter$onSendNewCodeClicked$2<T> implements Consumer<ApiResponse<? extends List<? extends Object>>> {
    final /* synthetic */ VerifyCodePresenter this$0;

    VerifyCodePresenter$onSendNewCodeClicked$2(VerifyCodePresenter verifyCodePresenter) {
        this.this$0 = verifyCodePresenter;
    }

    public final void accept(ApiResponse<? extends List<? extends Object>> apiResponse) {
        if (apiResponse.getOk()) {
            ((VerifyCodeView) this.this$0.getViewState()).showSuccessMessage(R.string.code_sent);
        } else {
            ((VerifyCodeView) this.this$0.getViewState()).showErrorMessage(R.string.code_not_sent);
        }
    }
}
