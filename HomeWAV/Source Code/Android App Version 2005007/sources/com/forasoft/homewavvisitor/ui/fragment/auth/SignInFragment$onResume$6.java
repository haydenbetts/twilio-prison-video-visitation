package com.forasoft.homewavvisitor.ui.fragment.auth;

import android.view.KeyEvent;
import android.widget.TextView;
import com.forasoft.homewavvisitor.presentation.presenter.auth.SignInPresenter;
import kotlin.Metadata;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000$\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u00062\u0015\u0010\u0007\u001a\u00110\b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\t2\u0017\u0010\n\u001a\u0013\u0018\u00010\u000b¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\f¢\u0006\u0002\b\r"}, d2 = {"<anonymous>", "", "p1", "Landroid/widget/TextView;", "Lkotlin/ParameterName;", "name", "view", "p2", "", "actionId", "p3", "Landroid/view/KeyEvent;", "event", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignInFragment.kt */
final /* synthetic */ class SignInFragment$onResume$6 extends FunctionReference implements Function3<TextView, Integer, KeyEvent, Boolean> {
    SignInFragment$onResume$6(SignInPresenter signInPresenter) {
        super(3, signInPresenter);
    }

    public final String getName() {
        return "onPasswordEnter";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SignInPresenter.class);
    }

    public final String getSignature() {
        return "onPasswordEnter(Landroid/widget/TextView;ILandroid/view/KeyEvent;)Z";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2, Object obj3) {
        return Boolean.valueOf(invoke((TextView) obj, ((Number) obj2).intValue(), (KeyEvent) obj3));
    }

    public final boolean invoke(TextView textView, int i, KeyEvent keyEvent) {
        return ((SignInPresenter) this.receiver).onPasswordEnter(textView, i, keyEvent);
    }
}
