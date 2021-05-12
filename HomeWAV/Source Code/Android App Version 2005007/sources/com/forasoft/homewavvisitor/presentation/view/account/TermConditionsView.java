package com.forasoft.homewavvisitor.presentation.view.account;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&¨\u0006\u0006"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/account/TermConditionsView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "showTerms", "", "termsText", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: TermConditionsView.kt */
public interface TermConditionsView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: TermConditionsView.kt */
    public static final class DefaultImpls {
        public static void showProgress(TermConditionsView termConditionsView, boolean z) {
            BaseView.DefaultImpls.showProgress(termConditionsView, z);
        }

        public static void updateNotificationMenu(TermConditionsView termConditionsView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(termConditionsView, i);
        }
    }

    void showTerms(String str);
}
