package com.forasoft.homewavvisitor.presentation;

import io.reactivex.disposables.CompositeDisposable;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.MvpPresenter;
import moxy.MvpView;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0016\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u0005¢\u0006\u0002\u0010\u0004J\b\u0010\u000b\u001a\u00020\fH\u0016J\b\u0010\r\u001a\u00020\fH\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0007\u0010\b\"\u0004\b\t\u0010\n¨\u0006\u000e"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "V", "Lmoxy/MvpView;", "Lmoxy/MvpPresenter;", "()V", "disposables", "Lio/reactivex/disposables/CompositeDisposable;", "getDisposables", "()Lio/reactivex/disposables/CompositeDisposable;", "setDisposables", "(Lio/reactivex/disposables/CompositeDisposable;)V", "beforeDestroy", "", "onDestroy", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: BasePresenter.kt */
public class BasePresenter<V extends MvpView> extends MvpPresenter<V> {
    private CompositeDisposable disposables = new CompositeDisposable();

    public void beforeDestroy() {
    }

    /* access modifiers changed from: protected */
    public final CompositeDisposable getDisposables() {
        return this.disposables;
    }

    /* access modifiers changed from: protected */
    public final void setDisposables(CompositeDisposable compositeDisposable) {
        Intrinsics.checkParameterIsNotNull(compositeDisposable, "<set-?>");
        this.disposables = compositeDisposable;
    }

    public void onDestroy() {
        beforeDestroy();
        super.onDestroy();
        this.disposables.clear();
    }
}
