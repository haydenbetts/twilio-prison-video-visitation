package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u001b\u0010\u0002\u001a\u0017\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "p1", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "Lkotlin/ParameterName;", "name", "it", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: EditAccountPresenter.kt */
final /* synthetic */ class EditAccountPresenter$sendStepData$1 extends FunctionReference implements Function1<ApiResponse<? extends User>, Unit> {
    EditAccountPresenter$sendStepData$1(EditAccountPresenter editAccountPresenter) {
        super(1, editAccountPresenter);
    }

    public final String getName() {
        return "onRegisterSuccess";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(EditAccountPresenter.class);
    }

    public final String getSignature() {
        return "onRegisterSuccess(Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApiResponse<User>) (ApiResponse) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ApiResponse<User> apiResponse) {
        Intrinsics.checkParameterIsNotNull(apiResponse, "p1");
        ((EditAccountPresenter) this.receiver).onRegisterSuccess(apiResponse);
    }
}
