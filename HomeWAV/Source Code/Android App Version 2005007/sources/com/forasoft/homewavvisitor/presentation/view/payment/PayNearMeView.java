package com.forasoft.homewavvisitor.presentation.view.payment;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;
import moxy.viewstate.strategy.OneExecutionStateStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\bf\u0018\u00002\u00020\u0001J\u0012\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005H&J\u0014\u0010\u0006\u001a\u00020\u00032\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\u0005H'J\b\u0010\b\u001a\u00020\u0003H'¨\u0006\t"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/payment/PayNearMeView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "setInitialEmail", "", "email", "", "showErrorMessage", "message", "showSuccessMessage", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: PayNearMeView.kt */
public interface PayNearMeView extends BaseView {
    void setInitialEmail(String str);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showErrorMessage(String str);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showSuccessMessage();

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: PayNearMeView.kt */
    public static final class DefaultImpls {
        public static void showProgress(PayNearMeView payNearMeView, boolean z) {
            BaseView.DefaultImpls.showProgress(payNearMeView, z);
        }

        public static void updateNotificationMenu(PayNearMeView payNearMeView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(payNearMeView, i);
        }

        public static /* synthetic */ void showErrorMessage$default(PayNearMeView payNearMeView, String str, int i, Object obj) {
            if (obj == null) {
                if ((i & 1) != 0) {
                    str = null;
                }
                payNearMeView.showErrorMessage(str);
                return;
            }
            throw new UnsupportedOperationException("Super calls with default arguments not supported in this target, function: showErrorMessage");
        }
    }
}
