package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.TestVideoView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import java.util.concurrent.TimeUnit;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004¢\u0006\u0002\u0010\u0005J\u0006\u0010\t\u001a\u00020\nJ\u0006\u0010\u000b\u001a\u00020\nJ\u0006\u0010\f\u001a\u00020\nR\u000e\u0010\u0006\u001a\u00020\u0007XD¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u000e¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/TestVideoPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/TestVideoView;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;)V", "maxLength", "", "videoLength", "onBackPressed", "", "onRecordingStarted", "onStopClicked", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: TestVideoPresenter.kt */
public final class TestVideoPresenter extends BasePresenter<TestVideoView> {
    /* access modifiers changed from: private */
    public final int maxLength = 5;
    private HomewavRouter router;
    /* access modifiers changed from: private */
    public int videoLength;

    @Inject
    public TestVideoPresenter(HomewavRouter homewavRouter) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        this.router = homewavRouter;
    }

    public final void onRecordingStarted() {
        getDisposables().clear();
        setDisposables(new CompositeDisposable());
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = Observable.interval(0, 1, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new TestVideoPresenter$onRecordingStarted$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Observable.interval(0, 1…oInt())\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        Disposable subscribe2 = Observable.timer((long) this.maxLength, TimeUnit.SECONDS).subscribeOn(Schedulers.newThread()).observeOn(AndroidSchedulers.mainThread()).subscribe(new TestVideoPresenter$onRecordingStarted$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "Observable.timer(maxLeng…cribe { onStopClicked() }");
        DisposableKt.plusAssign(disposables2, subscribe2);
    }

    public final void onStopClicked() {
        this.videoLength = 0;
        ((TestVideoView) getViewState()).stopRecording();
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
