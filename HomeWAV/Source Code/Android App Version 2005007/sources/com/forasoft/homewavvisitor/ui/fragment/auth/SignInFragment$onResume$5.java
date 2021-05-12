package com.forasoft.homewavvisitor.ui.fragment.auth;

import android.view.View;
import com.forasoft.homewavvisitor.presentation.presenter.auth.SignInPresenter;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0015\u0010\u0002\u001a\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062\u0015\u0010\u0007\u001a\u00110\b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t¢\u0006\u0002\b\n"}, d2 = {"<anonymous>", "", "p1", "Landroid/view/View;", "Lkotlin/ParameterName;", "name", "view", "p2", "", "hasFocus", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignInFragment.kt */
final /* synthetic */ class SignInFragment$onResume$5 extends FunctionReference implements Function2<View, Boolean, Unit> {
    SignInFragment$onResume$5(SignInPresenter signInPresenter) {
        super(2, signInPresenter);
    }

    public final String getName() {
        return "onEditFieldFocusChanged";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SignInPresenter.class);
    }

    public final String getSignature() {
        return "onEditFieldFocusChanged(Landroid/view/View;Z)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((View) obj, ((Boolean) obj2).booleanValue());
        return Unit.INSTANCE;
    }

    public final void invoke(View view, boolean z) {
        Intrinsics.checkParameterIsNotNull(view, "p1");
        ((SignInPresenter) this.receiver).onEditFieldFocusChanged(view, z);
    }
}
