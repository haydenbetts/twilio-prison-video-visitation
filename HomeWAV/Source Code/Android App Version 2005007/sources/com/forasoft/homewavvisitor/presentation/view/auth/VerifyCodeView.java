package com.forasoft.homewavvisitor.presentation.view.auth;

import kotlin.Metadata;
import moxy.MvpView;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH&¨\u0006\n"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/view/auth/VerifyCodeView;", "Lmoxy/MvpView;", "showErrorMessage", "", "resId", "", "showSuccessMessage", "updateHint", "message", "", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: VerifyCodeView.kt */
public interface VerifyCodeView extends MvpView {
    void showErrorMessage(int i);

    void showSuccessMessage(int i);

    void updateHint(String str);
}
