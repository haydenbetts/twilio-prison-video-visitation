package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.model.data.ErrorCause;
import com.forasoft.homewavvisitor.model.data.ValidationError;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyMethodPresenter.kt */
final class VerifyMethodPresenter$onEmailChanged$4<T> implements Consumer<Throwable> {
    final /* synthetic */ VerifyMethodPresenter this$0;

    VerifyMethodPresenter$onEmailChanged$4(VerifyMethodPresenter verifyMethodPresenter) {
        this.this$0 = verifyMethodPresenter;
    }

    public final void accept(Throwable th) {
        if (th instanceof ValidationError) {
            this.this$0.handleErrors(((ValidationError) th).getError());
        } else {
            this.this$0.handleErrors((ErrorCause) null);
        }
    }
}
