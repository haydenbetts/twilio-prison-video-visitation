package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.model.data.Card;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import java.util.List;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\bf\u0018\u00002\u00020\u0001J\u0016\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H&¨\u0006\u0007"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/PaymentMethodsView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "displayCards", "", "cards", "", "Lcom/forasoft/homewavvisitor/model/data/Card;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PaymentMethodsView.kt */
public interface PaymentMethodsView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: PaymentMethodsView.kt */
    public static final class DefaultImpls {
        public static void showProgress(PaymentMethodsView paymentMethodsView, boolean z) {
            BaseView.DefaultImpls.showProgress(paymentMethodsView, z);
        }

        public static void updateNotificationMenu(PaymentMethodsView paymentMethodsView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(paymentMethodsView, i);
        }
    }

    void displayCards(List<Card> list);
}
