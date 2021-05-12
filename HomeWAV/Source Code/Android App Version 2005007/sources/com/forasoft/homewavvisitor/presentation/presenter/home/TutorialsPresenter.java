package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.home.TutorialsView;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.rxkotlin.SubscribersKt;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0006\u0010\b\u001a\u00020\tJ\b\u0010\n\u001a\u00020\tH\u0014J\u000e\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\rR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/home/TutorialsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/home/TutorialsView;", "router", "Lru/terrakok/cicerone/Router;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "onBackPressed", "", "onFirstViewAttach", "onTutorialClicked", "url", "", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: TutorialsPresenter.kt */
public final class TutorialsPresenter extends BasePresenter<TutorialsView> {
    private final HomewavApi api;
    private final Router router;

    @Inject
    public TutorialsPresenter(Router router2, HomewavApi homewavApi) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        this.router = router2;
        this.api = homewavApi;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Single<ApiResponse<List<String>>> applyAsync = CommonKt.applyAsync(this.api.getTutorials());
        Intrinsics.checkExpressionValueIsNotNull(applyAsync, "api.getTutorials()\n                .applyAsync()");
        DisposableKt.plusAssign(disposables, SubscribersKt.subscribeBy(applyAsync, (Function1<? super Throwable, Unit>) new TutorialsPresenter$onFirstViewAttach$2(this), new TutorialsPresenter$onFirstViewAttach$1(this)));
    }

    public final void onTutorialClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "url");
        this.router.navigateTo(new Screens.TutorialScreen(str));
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
