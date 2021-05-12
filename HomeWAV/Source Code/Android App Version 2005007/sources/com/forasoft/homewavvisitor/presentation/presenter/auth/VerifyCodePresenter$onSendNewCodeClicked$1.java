package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import io.reactivex.Single;
import io.reactivex.SingleSource;
import io.reactivex.functions.Function;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u0014\u0012\u0010\u0012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00040\u00030\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u0006H\n¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "Lio/reactivex/Single;", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "", "", "it", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "apply"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyCodePresenter.kt */
final class VerifyCodePresenter$onSendNewCodeClicked$1<T, R> implements Function<T, SingleSource<? extends R>> {
    final /* synthetic */ VerifyCodePresenter this$0;

    VerifyCodePresenter$onSendNewCodeClicked$1(VerifyCodePresenter verifyCodePresenter) {
        this.this$0 = verifyCodePresenter;
    }

    public final Single<ApiResponse<List<Object>>> apply(User user) {
        Intrinsics.checkParameterIsNotNull(user, "it");
        return this.this$0.api.getCode(user.getId(), this.this$0.channel);
    }
}
