package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.model.data.payment.Handling;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u001d\u0010\u0002\u001a\u0019\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u0003¢\u0006\f\b\u0005\u0012\b\b\u0006\u0012\u0004\b\b(\u0007¢\u0006\u0002\b\b"}, d2 = {"<anonymous>", "", "p1", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "Lcom/forasoft/homewavvisitor/model/data/payment/Handling;", "Lkotlin/ParameterName;", "name", "response", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsPresenter.kt */
final /* synthetic */ class ChooseFundsPresenter$fetchHandlingFee$1$1 extends FunctionReference implements Function1<ApiResponse<? extends Handling>, Unit> {
    ChooseFundsPresenter$fetchHandlingFee$1$1(ChooseFundsPresenter chooseFundsPresenter) {
        super(1, chooseFundsPresenter);
    }

    public final String getName() {
        return "onHandlingObtained";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(ChooseFundsPresenter.class);
    }

    public final String getSignature() {
        return "onHandlingObtained(Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((ApiResponse<Handling>) (ApiResponse) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(ApiResponse<Handling> apiResponse) {
        ((ChooseFundsPresenter) this.receiver).onHandlingObtained(apiResponse);
    }
}
