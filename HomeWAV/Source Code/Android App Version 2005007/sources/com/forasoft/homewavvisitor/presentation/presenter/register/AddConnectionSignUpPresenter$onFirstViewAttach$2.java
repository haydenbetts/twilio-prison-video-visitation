package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.model.data.auth.User;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0010\u0000\u001a\u00020\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\n¢\u0006\u0002\b\u0004"}, d2 = {"<anonymous>", "", "it", "Lcom/forasoft/homewavvisitor/model/data/auth/User;", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: AddConnectionSignUpPresenter.kt */
final class AddConnectionSignUpPresenter$onFirstViewAttach$2<T> implements Consumer<User> {
    final /* synthetic */ AddConnectionSignUpPresenter this$0;

    AddConnectionSignUpPresenter$onFirstViewAttach$2(AddConnectionSignUpPresenter addConnectionSignUpPresenter) {
        this.this$0 = addConnectionSignUpPresenter;
    }

    public final void accept(User user) {
        this.this$0.onCompletedStateChanged();
    }
}
