package com.forasoft.homewavvisitor.ui.fragment.auth;

import android.view.View;
import com.forasoft.homewavvisitor.presentation.presenter.auth.SignInPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignInFragment.kt */
final /* synthetic */ class SignInFragment$onResume$1 extends FunctionReference implements Function1<View, Unit> {
    SignInFragment$onResume$1(SignInPresenter signInPresenter) {
        super(1, signInPresenter);
    }

    public final String getName() {
        return "onForgotPasswordClicked";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SignInPresenter.class);
    }

    public final String getSignature() {
        return "onForgotPasswordClicked(Landroid/view/View;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((View) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(View view) {
        ((SignInPresenter) this.receiver).onForgotPasswordClicked(view);
    }
}
