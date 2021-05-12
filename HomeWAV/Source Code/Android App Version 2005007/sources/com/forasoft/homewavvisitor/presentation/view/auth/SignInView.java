package com.forasoft.homewavvisitor.presentation.view.auth;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import com.forasoft.homewavvisitor.ui.activity.ServerErrorDisplay;
import kotlin.Metadata;
import moxy.viewstate.strategy.AddToEndSingleStrategy;
import moxy.viewstate.strategy.SkipStrategy;
import moxy.viewstate.strategy.StateStrategyType;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\bg\u0018\u00002\u00020\u00012\u00020\u0002J\b\u0010\u0003\u001a\u00020\u0004H&J\b\u0010\u0005\u001a\u00020\u0004H&J\b\u0010\u0006\u001a\u00020\u0004H&J\u0010\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\tH&J\b\u0010\n\u001a\u00020\u0004H'¨\u0006\u000b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/auth/SignInView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "Lcom/forasoft/homewavvisitor/ui/activity/ServerErrorDisplay;", "hideError", "", "performLoginClick", "showError", "showLoading", "isLoading", "", "showServerError", "app_release"}, k = 1, mv = {1, 1, 16})
@StateStrategyType(AddToEndSingleStrategy.class)
/* compiled from: SignInView.kt */
public interface SignInView extends BaseView, ServerErrorDisplay {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: SignInView.kt */
    public static final class DefaultImpls {
        public static void showProgress(SignInView signInView, boolean z) {
            BaseView.DefaultImpls.showProgress(signInView, z);
        }

        public static void updateNotificationMenu(SignInView signInView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(signInView, i);
        }
    }

    void hideError();

    void performLoginClick();

    void showError();

    void showLoading(boolean z);

    @StateStrategyType(SkipStrategy.class)
    void showServerError();
}
