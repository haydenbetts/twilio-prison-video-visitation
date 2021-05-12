package com.forasoft.homewavvisitor.presentation.view.payment;

import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import java.util.List;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\b\f\n\u0002\u0010\u0007\n\u0002\b\u0002\bg\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&J\b\u0010\u0007\u001a\u00020\u0003H'J\u0010\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\nH'J%\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\n2\u000e\u0010\r\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\n0\u000eH&¢\u0006\u0002\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00032\u0006\u0010\u0011\u001a\u00020\nH&J\b\u0010\u0012\u001a\u00020\u0003H&J\u0010\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\nH&J\u0010\u0010\u0015\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\nH'J\b\u0010\u0016\u001a\u00020\u0003H'J\b\u0010\u0017\u001a\u00020\u0003H'J\b\u0010\u0018\u001a\u00020\u0003H'J\u0010\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u001a\u001a\u00020\u001bH&J\b\u0010\u001c\u001a\u00020\u0003H'¨\u0006\u001d"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/payment/ChooseFundsView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "displayCards", "", "cards", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "executePayment", "initPayment", "token", "", "initPaymentScope", "scope", "allowedScopes", "", "(Ljava/lang/String;[Ljava/lang/String;)V", "setBalance", "balance", "setFraudState", "setName", "name", "setPaymentScope", "showBraintreeError", "showErrorMessage", "showGeneralFundsConfirmation", "showHandling", "handling", "", "showSuccessMessage", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: ChooseFundsView.kt */
public interface ChooseFundsView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: ChooseFundsView.kt */
    public static final class DefaultImpls {
        public static void showProgress(ChooseFundsView chooseFundsView, boolean z) {
            BaseView.DefaultImpls.showProgress(chooseFundsView, z);
        }

        public static void updateNotificationMenu(ChooseFundsView chooseFundsView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(chooseFundsView, i);
        }
    }

    void displayCards(List<Card> list);

    @StateStrategyType(SkipStrategy.class)
    void executePayment();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void initPayment(String str);

    void initPaymentScope(String str, String[] strArr);

    void setBalance(String str);

    void setFraudState();

    void setName(String str);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void setPaymentScope(String str);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showBraintreeError();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorMessage();

    @StateStrategyType(SkipStrategy.class)
    void showGeneralFundsConfirmation();

    void showHandling(float f);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSuccessMessage();
}
