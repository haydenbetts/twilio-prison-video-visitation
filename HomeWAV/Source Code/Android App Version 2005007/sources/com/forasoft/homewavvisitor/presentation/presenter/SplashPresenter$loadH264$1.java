package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.presentation.view.SplashView;
import com.getkeepsafe.relinker.ReLinker;
import fm.liveswitch.IAction1;
import fm.liveswitch.openh264.Utility;
import kotlin.Metadata;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0010\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n \u0004*\u0004\u0018\u00010\u00030\u0003H\n¢\u0006\u0002\b\u0005"}, d2 = {"<anonymous>", "", "it", "", "kotlin.jvm.PlatformType", "invoke"}, k = 3, mv = {1, 1, 16})
/* compiled from: SplashPresenter.kt */
final class SplashPresenter$loadH264$1<T> implements IAction1<Object> {
    final /* synthetic */ SplashPresenter this$0;

    SplashPresenter$loadH264$1(SplashPresenter splashPresenter) {
        this.this$0 = splashPresenter;
    }

    public final void invoke(Object obj) {
        ReLinker.loadLibrary(this.this$0.context, Utility.getLoadLibraryName());
        ((SplashView) this.this$0.getViewState()).onLoadedLibrary();
    }
}
