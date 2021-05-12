package com.forasoft.homewavvisitor.presentation.presenter.visits;

import android.content.Context;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.visits.VisitDetailsView;
import com.forasoft.homewavvisitor.toothpick.qualifier.VisitId;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\t\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BA\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\b\b\u0001\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010¢\u0006\u0002\u0010\u0011J\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0006H\u0002J\u0006\u0010\u0015\u001a\u00020\u0013J\u0006\u0010\u0016\u001a\u00020\u0013J\u0006\u0010\u0017\u001a\u00020\u0013J\u0006\u0010\u0018\u001a\u00020\u0013J\u0006\u0010\u0019\u001a\u00020\u0013J\b\u0010\u001a\u001a\u00020\u0013H\u0014J\u0006\u0010\u001b\u001a\u00020\u0013R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/visits/VisitDetailsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/visits/VisitDetailsView;", "context", "Landroid/content/Context;", "visitId", "", "router", "Lru/terrakok/cicerone/Router;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "visitDao", "Lcom/forasoft/homewavvisitor/dao/VisitDao;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "(Landroid/content/Context;Ljava/lang/String;Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/VisitDao;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;)V", "getInmateStatus", "", "inmateId", "getNotificationsCount", "onAddClicked", "onBackPressed", "onCancelConfirmed", "onCancelVisitClicked", "onFirstViewAttach", "onNotificationsClicked", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: VisitDetailsPresenter.kt */
public final class VisitDetailsPresenter extends BasePresenter<VisitDetailsView> {
    private final HomewavApi api;
    /* access modifiers changed from: private */
    public final Context context;
    private final HeartbeatRepository heartbeatRepository;
    private final NotificationDao notificationDao;
    /* access modifiers changed from: private */
    public final Router router;
    /* access modifiers changed from: private */
    public final VisitDao visitDao;
    /* access modifiers changed from: private */
    public final String visitId;

    @Inject
    public VisitDetailsPresenter(Context context2, @VisitId String str, Router router2, HomewavApi homewavApi, NotificationDao notificationDao2, VisitDao visitDao2, HeartbeatRepository heartbeatRepository2) {
        Intrinsics.checkParameterIsNotNull(context2, "context");
        Intrinsics.checkParameterIsNotNull(str, "visitId");
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(visitDao2, "visitDao");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        this.context = context2;
        this.visitId = str;
        this.router = router2;
        this.api = homewavApi;
        this.notificationDao = notificationDao2;
        this.visitDao = visitDao2;
        this.heartbeatRepository = heartbeatRepository2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.visitDao.getVisitById(this.visitId)).subscribe(new VisitDetailsPresenter$onFirstViewAttach$1(this), VisitDetailsPresenter$onFirstViewAttach$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "visitDao.getVisitById(vi…t)\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new VisitDetailsPresenter$getNotificationsCount$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.countAll…teNotificationMenu(it) })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    public final void onCancelVisitClicked() {
        ((VisitDetailsView) getViewState()).showConfirmDialog();
    }

    public final void onCancelConfirmed() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.api.cancelVisit(this.visitId)).subscribe(new VisitDetailsPresenter$onCancelConfirmed$1(this), new VisitDetailsPresenter$onCancelConfirmed$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.cancelVisit(visitId)…isit\")\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onAddClicked() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.visitDao.getVisitById(this.visitId)).subscribe(new VisitDetailsPresenter$onAddClicked$1(this), VisitDetailsPresenter$onAddClicked$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "visitDao.getVisitById(vi…t)\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onBackPressed() {
        this.router.exit();
    }

    /* access modifiers changed from: private */
    public final void getInmateStatus(String str) {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.heartbeatRepository.getHeartbeatState()).subscribe(new VisitDetailsPresenter$getInmateStatus$1(this, str));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "heartbeatRepository.hear…status)\n                }");
        DisposableKt.plusAssign(disposables, subscribe);
    }
}
