package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.dao.CallDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.BaseView;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/ReportBugPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/BaseView;", "router", "Lru/terrakok/cicerone/Router;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "callDao", "Lcom/forasoft/homewavvisitor/dao/CallDao;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/dao/CallDao;)V", "onBackPressed", "", "onReportClicked", "problems", "", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: ReportBugPresenter.kt */
public final class ReportBugPresenter extends BasePresenter<BaseView> {
    /* access modifiers changed from: private */
    public final HomewavApi api;
    private final CallDao callDao;
    /* access modifiers changed from: private */
    public final Router router;

    @Inject
    public ReportBugPresenter(Router router2, HomewavApi homewavApi, CallDao callDao2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(callDao2, "callDao");
        this.router = router2;
        this.api = homewavApi;
        this.callDao = callDao2;
    }

    public final void onReportClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "problems");
        CompositeDisposable disposables = getDisposables();
        Single<R> flatMap = this.callDao.getLatestCall().flatMap(new ReportBugPresenter$onReportClicked$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(flatMap, "callDao.getLatestCall()\n…tError(problems, it.id) }");
        Disposable subscribe = CommonKt.applyAsync(flatMap).subscribe(new ReportBugPresenter$onReportClicked$2(this), new ReportBugPresenter$onReportClicked$3(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "callDao.getLatestCall()\n…exit()\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
