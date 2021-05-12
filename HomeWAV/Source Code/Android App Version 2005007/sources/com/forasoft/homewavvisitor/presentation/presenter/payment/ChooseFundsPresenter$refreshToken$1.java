package com.forasoft.homewavvisitor.presentation.presenter.payment;

import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.model.data.payment.BraintreeToken;
import com.forasoft.homewavvisitor.model.interactor.PaymentGatewayType;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.presentation.view.payment.ChooseFundsView;
import io.reactivex.functions.Consumer;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "paymentGatewayType", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: ChooseFundsPresenter.kt */
final class ChooseFundsPresenter$refreshToken$1<T> implements Consumer<PaymentGatewayType> {
    final /* synthetic */ ChooseFundsPresenter this$0;

    ChooseFundsPresenter$refreshToken$1(ChooseFundsPresenter chooseFundsPresenter) {
        this.this$0 = chooseFundsPresenter;
    }

    public final void accept(PaymentGatewayType paymentGatewayType) {
        if (paymentGatewayType == PaymentGatewayType.STRIPE) {
            this.this$0.paymentGateway.getCards().subscribe(new Consumer<ApiResponse<? extends List<? extends Card>>>(this) {
                final /* synthetic */ ChooseFundsPresenter$refreshToken$1 this$0;

                {
                    this.this$0 = r1;
                }

                public final void accept(ApiResponse<? extends List<Card>> apiResponse) {
                    this.this$0.this$0.cards.clear();
                    List list = (List) apiResponse.getBody();
                    if (list != null) {
                        this.this$0.this$0.cards.addAll(list);
                    }
                    CollectionsKt.sortedWith(this.this$0.this$0.cards, new ChooseFundsPresenter$refreshToken$1$1$$special$$inlined$sortedByDescending$1());
                    ((ChooseFundsView) this.this$0.this$0.getViewState()).displayCards(this.this$0.this$0.cards);
                }
            }, AnonymousClass2.INSTANCE);
        } else {
            this.this$0.paymentGateway.getClientToken().subscribe(new ChooseFundsPresenter$sam$io_reactivex_functions_Consumer$0(new Function1<ApiResponse<? extends BraintreeToken>, Unit>(this.this$0) {
                public final String getName() {
                    return "onTokenObtained";
                }

                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(ChooseFundsPresenter.class);
                }

                public final String getSignature() {
                    return "onTokenObtained(Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;)V";
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((ApiResponse<BraintreeToken>) (ApiResponse) obj);
                    return Unit.INSTANCE;
                }

                public final void invoke(ApiResponse<BraintreeToken> apiResponse) {
                    ((ChooseFundsPresenter) this.receiver).onTokenObtained(apiResponse);
                }
            }), new ChooseFundsPresenter$sam$io_reactivex_functions_Consumer$0(new Function1<Throwable, Unit>(this.this$0) {
                public final String getName() {
                    return "onObtainFailed";
                }

                public final KDeclarationContainer getOwner() {
                    return Reflection.getOrCreateKotlinClass(ChooseFundsPresenter.class);
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
                    ((ChooseFundsPresenter) this.receiver).onObtainFailed(th);
                }
            }));
        }
    }
}
