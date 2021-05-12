package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.model.data.payment.BraintreeToken;
import com.forasoft.homewavvisitor.model.interactor.PaymentGatewayType;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.presenter.AddCardPresenter;
import com.forasoft.homewavvisitor.presentation.view.AddCardView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddCardPresenter.kt */
final class AddCardPresenter$initializePayments$1<T> implements Consumer<PaymentGatewayType> {
    final /* synthetic */ AddCardPresenter this$0;

    AddCardPresenter$initializePayments$1(AddCardPresenter addCardPresenter) {
        this.this$0 = addCardPresenter;
    }

    public final void accept(PaymentGatewayType paymentGatewayType) {
        if (paymentGatewayType != null) {
            int i = AddCardPresenter.WhenMappings.$EnumSwitchMapping$0[paymentGatewayType.ordinal()];
            if (i == 1) {
                this.this$0.paymentGateway.getClientToken().subscribe(new AddCardPresenter$sam$io_reactivex_functions_Consumer$0(new Function1<ApiResponse<? extends BraintreeToken>, Unit>(this.this$0) {
                    public final String getName() {
                        return "onTokenObtained";
                    }

                    public final KDeclarationContainer getOwner() {
                        return Reflection.getOrCreateKotlinClass(AddCardPresenter.class);
                    }

                    public final String getSignature() {
                        return "onTokenObtained(Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;)V";
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((ApiResponse<BraintreeToken>) (ApiResponse) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(ApiResponse<BraintreeToken> apiResponse) {
                        ((AddCardPresenter) this.receiver).onTokenObtained(apiResponse);
                    }
                }), new AddCardPresenter$sam$io_reactivex_functions_Consumer$0(new Function1<Throwable, Unit>(this.this$0) {
                    public final String getName() {
                        return "onObtainFailed";
                    }

                    public final KDeclarationContainer getOwner() {
                        return Reflection.getOrCreateKotlinClass(AddCardPresenter.class);
                    }

                    public final String getSignature() {
                        return "onObtainFailed(Ljava/lang/Throwable;)V";
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((Throwable) obj);
                        return Unit.INSTANCE;
                    }

                    public final void invoke(Throwable th) {
                        Intrinsics.checkParameterIsNotNull(th, "p1");
                        ((AddCardPresenter) this.receiver).onObtainFailed(th);
                    }
                }));
            } else if (i == 2) {
                ((AddCardView) this.this$0.getViewState()).showCardTypes();
            }
        }
    }
}
