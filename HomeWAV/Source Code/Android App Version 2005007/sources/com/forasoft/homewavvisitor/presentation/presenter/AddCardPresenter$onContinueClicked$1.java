package com.forasoft.homewavvisitor.presentation.presenter;

import com.braintreepayments.api.models.CardBuilder;
import com.forasoft.homewavvisitor.model.interactor.PaymentGatewayType;
import com.forasoft.homewavvisitor.presentation.view.AddCardView;
import com.stripe.android.model.Card;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/interactor/PaymentGatewayType;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddCardPresenter.kt */
final class AddCardPresenter$onContinueClicked$1<T> implements Consumer<PaymentGatewayType> {
    final /* synthetic */ String $cardNumber;
    final /* synthetic */ String $cvv;
    final /* synthetic */ String $expires;
    final /* synthetic */ String $zip;
    final /* synthetic */ AddCardPresenter this$0;

    AddCardPresenter$onContinueClicked$1(AddCardPresenter addCardPresenter, String str, String str2, String str3, String str4) {
        this.this$0 = addCardPresenter;
        this.$cardNumber = str;
        this.$expires = str2;
        this.$cvv = str3;
        this.$zip = str4;
    }

    public final void accept(PaymentGatewayType paymentGatewayType) {
        if (paymentGatewayType == PaymentGatewayType.STRIPE) {
            String str = this.$cardNumber;
            String str2 = this.$expires;
            if (str2 != null) {
                String substring = str2.substring(0, 2);
                Intrinsics.checkExpressionValueIsNotNull(substring, "(this as java.lang.Strin…ing(startIndex, endIndex)");
                Integer valueOf = Integer.valueOf(Integer.parseInt(substring));
                String str3 = this.$expires;
                if (str3 != null) {
                    String substring2 = str3.substring(3);
                    Intrinsics.checkExpressionValueIsNotNull(substring2, "(this as java.lang.String).substring(startIndex)");
                    Card card = new Card(str, valueOf, Integer.valueOf(Integer.parseInt(substring2)), this.$cvv);
                    String str4 = this.$zip;
                    if (str4 != null) {
                        card.setAddressZip(str4);
                    }
                    ((AddCardView) this.this$0.getViewState()).tokenizeStripe(card);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
            }
            throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
        }
        CardBuilder cardBuilder = (CardBuilder) ((CardBuilder) ((CardBuilder) ((CardBuilder) new CardBuilder().cardNumber(this.$cardNumber)).cvv(this.$cvv)).expirationDate(this.$expires)).validate(false);
        Intrinsics.checkExpressionValueIsNotNull(cardBuilder, "cardBuilder");
        ((AddCardView) this.this$0.getViewState()).tokenize(cardBuilder);
    }
}
