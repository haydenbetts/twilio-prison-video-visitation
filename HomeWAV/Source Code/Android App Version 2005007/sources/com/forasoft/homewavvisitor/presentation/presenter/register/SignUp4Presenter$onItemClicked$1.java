package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.presentation.view.register.SignUp4View;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "Lio/reactivex/disposables/Disposable;", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignUp4Presenter.kt */
final class SignUp4Presenter$onItemClicked$1<T> implements Consumer<Disposable> {
    final /* synthetic */ SignUp4Presenter this$0;

    SignUp4Presenter$onItemClicked$1(SignUp4Presenter signUp4Presenter) {
        this.this$0 = signUp4Presenter;
    }

    public final void accept(Disposable disposable) {
        ((SignUp4View) this.this$0.getViewState()).showProgress();
    }
}
