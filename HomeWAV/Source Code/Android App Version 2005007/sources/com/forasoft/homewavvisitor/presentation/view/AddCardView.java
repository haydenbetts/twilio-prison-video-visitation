package com.forasoft.homewavvisitor.presentation.view;

import com.braintreepayments.api.models.CardBuilder;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import com.stripe.android.model.Card;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0003H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0003H&J\u0010\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\rH&J\u0010\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0010H&¨\u0006\u0011"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/AddCardView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "addCardSystemWithIndex", "", "index", "", "clearCardSystems", "initPayment", "token", "", "showCardTypes", "tokenize", "cardBuilder", "Lcom/braintreepayments/api/models/CardBuilder;", "tokenizeStripe", "card", "Lcom/stripe/android/model/Card;", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: AddCardView.kt */
public interface AddCardView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: AddCardView.kt */
    public static final class DefaultImpls {
        public static void showProgress(AddCardView addCardView, boolean z) {
            BaseView.DefaultImpls.showProgress(addCardView, z);
        }

        public static void updateNotificationMenu(AddCardView addCardView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(addCardView, i);
        }
    }

    void addCardSystemWithIndex(int i);

    void clearCardSystems();

    void initPayment(String str);

    void showCardTypes();

    void tokenize(CardBuilder cardBuilder);

    void tokenizeStripe(Card card);
}
