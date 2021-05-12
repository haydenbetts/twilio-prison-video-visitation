package com.forasoft.homewavvisitor.presentation.presenter;

import io.reactivex.functions.Consumer;
import kotlin.Metadata;
import ru.terrakok.cicerone.Screen;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0003\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "accept"}, k = 3, mv = {1, 1, 16})
/* compiled from: SplashPresenter.kt */
final class SplashPresenter$checkAuthAndDispatch$subscription$3<T> implements Consumer<Throwable> {
    final /* synthetic */ boolean $isNeedAuth;
    final /* synthetic */ Screen $screen;
    final /* synthetic */ SplashPresenter this$0;

    SplashPresenter$checkAuthAndDispatch$subscription$3(SplashPresenter splashPresenter, boolean z, Screen screen) {
        this.this$0 = splashPresenter;
        this.$isNeedAuth = z;
        this.$screen = screen;
    }

    public final void accept(Throwable th) {
        if (!this.$isNeedAuth) {
            this.this$0.router.newRootScreen(this.$screen);
        } else {
            this.this$0.onNotLoggedIn(th);
        }
    }
}
