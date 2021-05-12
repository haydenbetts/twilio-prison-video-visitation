package com.forasoft.homewavvisitor.presentation.presenter.auth;

import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.presentation.view.auth.VerifyMethodView;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: VerifyMethodPresenter.kt */
final class VerifyMethodPresenter$onFirstViewAttach$1<T> implements Consumer<User> {
    final /* synthetic */ VerifyMethodPresenter this$0;

    VerifyMethodPresenter$onFirstViewAttach$1(VerifyMethodPresenter verifyMethodPresenter) {
        this.this$0 = verifyMethodPresenter;
    }

    public final void accept(User user) {
        this.this$0.visitorId = user.getId();
        ((VerifyMethodView) this.this$0.getViewState()).showPhone(user.getPhone());
        ((VerifyMethodView) this.this$0.getViewState()).showEmail(user.getEmail());
    }
}
