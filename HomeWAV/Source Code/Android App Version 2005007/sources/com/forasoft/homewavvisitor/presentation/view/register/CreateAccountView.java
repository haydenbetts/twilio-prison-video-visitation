package com.forasoft.homewavvisitor.presentation.view.register;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/register/CreateAccountView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "setStepperStep", "", "step", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CreateAccountView.kt */
public interface CreateAccountView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: CreateAccountView.kt */
    public static final class DefaultImpls {
        public static void showProgress(CreateAccountView createAccountView, boolean z) {
            BaseView.DefaultImpls.showProgress(createAccountView, z);
        }

        public static void updateNotificationMenu(CreateAccountView createAccountView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(createAccountView, i);
        }
    }

    void setStepperStep(int i);
}
