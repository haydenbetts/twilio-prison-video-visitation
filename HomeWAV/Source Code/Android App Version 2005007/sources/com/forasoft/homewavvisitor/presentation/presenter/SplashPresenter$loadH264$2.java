package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.presentation.view.SplashView;
import fm.liveswitch.IAction1;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0014\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u0018\u0010\u0002\u001a\u0014 \u0005*\n\u0018\u00010\u0003j\u0004\u0018\u0001`\u00040\u0003j\u0002`\u0004H\n¢\u0006\u0002\b\u0006"}, d2 = {"<anonymous>", "", "it", "Ljava/lang/Exception;", "Lkotlin/Exception;", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: SplashPresenter.kt */
final class SplashPresenter$loadH264$2<T> implements IAction1<Exception> {
    final /* synthetic */ SplashPresenter this$0;

    SplashPresenter$loadH264$2(SplashPresenter splashPresenter) {
        this.this$0 = splashPresenter;
    }

    public final void invoke(Exception exc) {
        ((SplashView) this.this$0.getViewState()).onLoadedLibrary();
    }
}
