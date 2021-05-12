package com.forasoft.homewavvisitor.presentation.view.auth;

import com.forasoft.homewavvisitor.presentation.view.BaseView;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u0005H&¨\u0006\b"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/auth/VerifyMethodView;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "showEmail", "", "email", "", "showPhone", "phone", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VerifyMethodView.kt */
public interface VerifyMethodView extends BaseView {

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    /* compiled from: VerifyMethodView.kt */
    public static final class DefaultImpls {
        public static void showProgress(VerifyMethodView verifyMethodView, boolean z) {
            BaseView.DefaultImpls.showProgress(verifyMethodView, z);
        }

        public static void updateNotificationMenu(VerifyMethodView verifyMethodView, int i) {
            BaseView.DefaultImpls.updateNotificationMenu(verifyMethodView, i);
        }
    }

    void showEmail(String str);

    void showPhone(String str);
}
