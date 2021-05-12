package com.forasoft.homewavvisitor.presentation.presenter.register;

import android.net.Uri;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReference;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KDeclarationContainer;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0010\u0000\u001a\u00020\u00012\u0017\u0010\u0002\u001a\u0013\u0018\u00010\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006¢\u0006\u0002\b\u0007"}, d2 = {"<anonymous>", "", "p1", "Landroid/net/Uri;", "Lkotlin/ParameterName;", "name", "imageUri", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: SignUp1Presenter.kt */
final /* synthetic */ class SignUp1Presenter$onPhotoIdImageClicked$1 extends FunctionReference implements Function1<Uri, Unit> {
    SignUp1Presenter$onPhotoIdImageClicked$1(SignUp1Presenter signUp1Presenter) {
        super(1, signUp1Presenter);
    }

    public final String getName() {
        return "onPhotoIdImagePicked";
    }

    public final KDeclarationContainer getOwner() {
        return Reflection.getOrCreateKotlinClass(SignUp1Presenter.class);
    }

    public final String getSignature() {
        return "onPhotoIdImagePicked(Landroid/net/Uri;)V";
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
        invoke((Uri) obj);
        return Unit.INSTANCE;
    }

    public final void invoke(Uri uri) {
        ((SignUp1Presenter) this.receiver).onPhotoIdImagePicked(uri);
    }
}
