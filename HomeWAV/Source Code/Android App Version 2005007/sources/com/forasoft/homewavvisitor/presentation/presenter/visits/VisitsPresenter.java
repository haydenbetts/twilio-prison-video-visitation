package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.dao.VisitDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.Response;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.visits.VisitsView;
import com.google.gson.stream.MalformedJsonException;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.schedulers.Schedulers;
import java.net.SocketTimeoutException;
import java.util.Date;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u001c\u0010\u0012\u001a\u00020\r2\u0012\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00160\u00150\u0014H\u0002J\u0006\u0010\u0017\u001a\u00020\u0018J\b\u0010\u0019\u001a\u00020\rH\u0014J\u0006\u0010\u001a\u001a\u00020\rJ\u0006\u0010\u001b\u001a\u00020\rJ\u000e\u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u0016R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/visits/VisitsPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/visits/VisitsView;", "router", "Lru/terrakok/cicerone/Router;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "visitDao", "Lcom/forasoft/homewavvisitor/dao/VisitDao;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/VisitDao;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;)V", "getNotificationsCount", "", "getVisits", "handleError", "error", "", "handleSuccess", "response", "Lcom/forasoft/homewavvisitor/model/server/response/Response;", "", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledVisit;", "onBackPressed", "", "onFirstViewAttach", "onNotificationsClicked", "onScheduleVisitClicked", "onVisitClicked", "visit", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: VisitsPresenter.kt */
public final class VisitsPresenter extends BasePresenter<VisitsView> {
    private final HomewavApi api;
    private final NotificationDao notificationDao;
    private final Router router;
    private final VisitDao visitDao;

    @Inject
    public VisitsPresenter(Router router2, NotificationDao notificationDao2, VisitDao visitDao2, HomewavApi homewavApi) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(visitDao2, "visitDao");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        this.router = router2;
        this.notificationDao = notificationDao2;
        this.visitDao = visitDao2;
        this.api = homewavApi;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        long time = new Date().getTime() / ((long) 1000);
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.visitDao.getScheduledVisits(time)).subscribe(new VisitsPresenter$onFirstViewAttach$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "visitDao.getScheduledVis…playScheduledVisits(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        Disposable subscribe2 = CommonKt.applyAsync(this.visitDao.getPendingVisits(time)).subscribe(new VisitsPresenter$onFirstViewAttach$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "visitDao.getPendingVisit…isplayPendingVisits(it) }");
        DisposableKt.plusAssign(disposables2, subscribe2);
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new VisitsPresenter$getNotificationsCount$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.countAll…ateNotificationMenu(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    public final void onVisitClicked(ScheduledVisit scheduledVisit) {
        Intrinsics.checkParameterIsNotNull(scheduledVisit, "visit");
        this.router.navigateTo(new Screens.VisitDetailsScreen(scheduledVisit.getSlotId()));
    }

    public final void onScheduleVisitClicked() {
        this.router.navigateTo(Screens.InmateChooseVisitsScreen.INSTANCE);
    }

    public final void getVisits() {
        CompositeDisposable disposables = getDisposables();
        VisitsPresenter visitsPresenter = this;
        Disposable subscribe = this.api.getVisits().map(VisitsPresenter$getVisits$1.INSTANCE).subscribeOn(Schedulers.io()).subscribe(new VisitsPresenter$sam$io_reactivex_functions_Consumer$0(new VisitsPresenter$getVisits$2(visitsPresenter)), new VisitsPresenter$sam$io_reactivex_functions_Consumer$0(new VisitsPresenter$getVisits$3(visitsPresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getVisits()\n        …ccess, this::handleError)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void handleSuccess(Response<? extends List<ScheduledVisit>> response) {
        if (response.getOk()) {
            Object body = response.getBody();
            if (body != null) {
                this.visitDao.saveVisits((List) body);
                return;
            }
            throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.List<com.forasoft.homewavvisitor.model.data.account.ScheduledVisit>");
        }
        handleError(new Throwable(response.getError()));
    }

    /* access modifiers changed from: private */
    public final void handleError(Throwable th) {
        Timber.d("onError " + th.getMessage(), new Object[0]);
        if ((th instanceof MalformedJsonException) || (th instanceof SocketTimeoutException)) {
            ((VisitsView) getViewState()).showServerError();
        }
    }
}
