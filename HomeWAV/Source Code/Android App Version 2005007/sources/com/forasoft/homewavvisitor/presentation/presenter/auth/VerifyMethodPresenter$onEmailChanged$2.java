package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.model.data.auth.User;
import io.reactivex.functions.Action;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\b\n\u0000\n\u0002\u0010\u0002\n\u0000\u0010\u0000\u001a\u00020\u0001H\n¢\u0006\u0002\b\u0002"}, d2 = {"<anonymous>", "", "run"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyMethodPresenter.kt */
final class VerifyMethodPresenter$onEmailChanged$2 implements Action {
    final /* synthetic */ String $newEmail;
    final /* synthetic */ VerifyMethodPresenter this$0;

    VerifyMethodPresenter$onEmailChanged$2(VerifyMethodPresenter verifyMethodPresenter, String str) {
        this.this$0 = verifyMethodPresenter;
        this.$newEmail = str;
    }

    public final void run() {
        User user = this.this$0.authRepository.getUser();
        if (user != null) {
            user.setEmail(this.$newEmail);
        }
    }
}
