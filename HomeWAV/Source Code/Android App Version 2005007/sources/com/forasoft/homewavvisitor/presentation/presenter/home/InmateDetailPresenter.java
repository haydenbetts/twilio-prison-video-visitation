package com.forasoft.homewavvisitor.presentation.presenter.home;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.Analytics;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.account.ScheduledResponse;
import com.forasoft.homewavvisitor.model.data.account.ScheduledVisit;
import com.forasoft.homewavvisitor.model.interactor.account.AccountInteractor;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.model.server.response.ApiResponse;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.home.InmateDetailView;
import io.reactivex.Single;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import io.reactivex.subjects.BehaviorSubject;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import timber.log.Timber;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u0003\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BS\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0012\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00110\u0010\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\u0006\u0010\u0015\u001a\u00020\u0016J\b\u0010\u0017\u001a\u00020\u0018H\u0002J\u000e\u0010\u0019\u001a\u00020\u00162\u0006\u0010\u001a\u001a\u00020\u0018J\u0006\u0010\u001b\u001a\u00020\u0018J\u0006\u0010\u001c\u001a\u00020\u0016J\u0012\u0010\u001d\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u001e\u0010 \u001a\u00020\u00162\u0014\u0010\u001e\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u0011\u0018\u00010!H\u0002J\b\u0010\"\u001a\u00020\u0016H\u0016J\b\u0010#\u001a\u00020\u0016H\u0014J\u0006\u0010$\u001a\u00020\u0016J\u0012\u0010%\u001a\u00020\u00162\b\u0010&\u001a\u0004\u0018\u00010\u001fH\u0002J\u0006\u0010'\u001a\u00020\u0016J\u0006\u0010(\u001a\u00020\u0016J\u0012\u0010)\u001a\u00020\u00162\b\u0010\u001e\u001a\u0004\u0018\u00010\u001fH\u0002J\u0018\u0010*\u001a\u00020\u00162\u000e\u0010\u001e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010!H\u0002J\u0018\u0010+\u001a\u00020\u00162\u000e\u0010,\u001a\n\u0012\u0004\u0012\u00020-\u0018\u00010!H\u0002J\u0006\u0010.\u001a\u00020\u0016R\u000e\u0010\r\u001a\u00020\u000eX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00060\u00110\u0010X\u0004¢\u0006\u0002\n\u0000¨\u0006/"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/home/InmateDetailPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/home/InmateDetailView;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "analytics", "Lcom/forasoft/homewavvisitor/model/Analytics;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "accountInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;", "subject", "Lio/reactivex/subjects/BehaviorSubject;", "", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/model/data/Inmate;Lcom/forasoft/homewavvisitor/model/Analytics;Lcom/forasoft/homewavvisitor/dao/NotificationDao;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/interactor/account/AccountInteractor;Lio/reactivex/subjects/BehaviorSubject;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;)V", "getNotificationsCount", "", "isVisible", "", "onAllowCallsClicked", "allowCalls", "onBackPressed", "onDeleteConfirmed", "onDeleteFailed", "it", "", "onDeleteSuccess", "Lcom/forasoft/homewavvisitor/model/server/response/ApiResponse;", "onDestroy", "onFirstViewAttach", "onInmateDeleteClicked", "onLoadError", "error", "onMessageButtonClicked", "onNotificationsClicked", "onVisibilityChangeError", "onVisibilityChanged", "onVisitsLoaded", "response", "Lcom/forasoft/homewavvisitor/model/data/account/ScheduledResponse;", "refresh", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: InmateDetailPresenter.kt */
public final class InmateDetailPresenter extends BasePresenter<InmateDetailView> {
    private final AccountInteractor accountInteractor;
    /* access modifiers changed from: private */
    public final Analytics analytics;
    private final HeartbeatRepository heartbeatRepository;
    /* access modifiers changed from: private */
    public final Inmate inmate;
    /* access modifiers changed from: private */
    public final InmateDao inmateDao;
    private final NotificationDao notificationDao;
    /* access modifiers changed from: private */
    public final HomewavRouter router;
    /* access modifiers changed from: private */
    public final BehaviorSubject<List<Inmate>> subject;

    @Inject
    public InmateDetailPresenter(HomewavRouter homewavRouter, Inmate inmate2, Analytics analytics2, NotificationDao notificationDao2, InmateDao inmateDao2, AccountInteractor accountInteractor2, BehaviorSubject<List<Inmate>> behaviorSubject, HeartbeatRepository heartbeatRepository2) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(inmate2, "inmate");
        Intrinsics.checkParameterIsNotNull(analytics2, Modules.ANALYTICS_MODULE);
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(accountInteractor2, "accountInteractor");
        Intrinsics.checkParameterIsNotNull(behaviorSubject, "subject");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        this.router = homewavRouter;
        this.inmate = inmate2;
        this.analytics = analytics2;
        this.notificationDao = notificationDao2;
        this.inmateDao = inmateDao2;
        this.accountInteractor = accountInteractor2;
        this.subject = behaviorSubject;
        this.heartbeatRepository = heartbeatRepository2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        InmateDetailView inmateDetailView = (InmateDetailView) getViewState();
        inmateDetailView.setName(this.inmate.getFirst_name() + ' ' + this.inmate.getLast_name());
        String credit_balance = this.inmate.getCredit_balance();
        String str = "0";
        if (credit_balance == null) {
            credit_balance = str;
        }
        inmateDetailView.setCredits(credit_balance);
        inmateDetailView.setId("ID: " + this.inmate.getIdentifier());
        inmateDetailView.setInvisible(isVisible());
        String credit_balance2 = this.inmate.getCredit_balance();
        if (credit_balance2 != null) {
            str = credit_balance2;
        }
        inmateDetailView.setMoney(str);
        String status = this.inmate.getStatus();
        if (status == null) {
            status = "red";
        }
        inmateDetailView.setStatus(status);
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.subject.map(new InmateDetailPresenter$onFirstViewAttach$2(this)).subscribe(new InmateDetailPresenter$onFirstViewAttach$3(this), InmateDetailPresenter$onFirstViewAttach$4.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "subject\n                … }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
        CompositeDisposable disposables2 = getDisposables();
        InmateDetailPresenter inmateDetailPresenter = this;
        Disposable subscribe2 = this.accountInteractor.getScheduledVisits().subscribe(new InmateDetailPresenter$sam$io_reactivex_functions_Consumer$0(new InmateDetailPresenter$onFirstViewAttach$5(inmateDetailPresenter)), new InmateDetailPresenter$sam$io_reactivex_functions_Consumer$0(new InmateDetailPresenter$onFirstViewAttach$6(inmateDetailPresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe2, "accountInteractor.getSch…oaded, this::onLoadError)");
        DisposableKt.plusAssign(disposables2, subscribe2);
    }

    /* access modifiers changed from: private */
    public final boolean isVisible() {
        return this.inmate.getInvisible() == null || Intrinsics.areEqual((Object) this.inmate.getInvisible(), (Object) "0");
    }

    /* access modifiers changed from: private */
    public final void onLoadError(Throwable th) {
        Timber.e(th);
        ((InmateDetailView) getViewState()).showVisitsNumber("? Visits");
        ((InmateDetailView) getViewState()).showVisitsText("Couldn't load visits");
    }

    public final void getNotificationsCount() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new InmateDetailPresenter$getNotificationsCount$1(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "notificationDao.countAll…ateNotificationMenu(it) }");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    /* access modifiers changed from: private */
    public final void onVisitsLoaded(ApiResponse<ScheduledResponse> apiResponse) {
        int i;
        ScheduledResponse body;
        int i2;
        ScheduledResponse body2;
        List<ScheduledVisit> scheduled;
        int i3 = 0;
        if (apiResponse == null || (body2 = apiResponse.getBody()) == null || (scheduled = body2.getScheduled()) == null) {
            i = 0;
        } else {
            Collection arrayList = new ArrayList();
            for (Object next : scheduled) {
                if (Intrinsics.areEqual((Object) ((ScheduledVisit) next).getInmate(), (Object) this.inmate.getFmt_name())) {
                    arrayList.add(next);
                }
            }
            i = ((List) arrayList).size();
        }
        ((InmateDetailView) getViewState()).showVisitsNumber(i + " Visits");
        ((InmateDetailView) getViewState()).showVisitsText("No visits scheduled");
        if (apiResponse != null && (body = apiResponse.getBody()) != null && body.getScheduled() != null) {
            Collection arrayList2 = new ArrayList();
            for (Object next2 : apiResponse.getBody().getScheduled()) {
                if (Intrinsics.areEqual((Object) ((ScheduledVisit) next2).getInmate(), (Object) this.inmate.getFmt_name())) {
                    arrayList2.add(next2);
                }
            }
            List list = (List) arrayList2;
            if (i != 0) {
                Iterable<ScheduledVisit> iterable = list;
                boolean z = iterable instanceof Collection;
                if (!z || !((Collection) iterable).isEmpty()) {
                    i2 = 0;
                    for (ScheduledVisit status : iterable) {
                        if (Intrinsics.areEqual((Object) status.getStatus(), (Object) "confirmed") && (i2 = i2 + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                } else {
                    i2 = 0;
                }
                if (!z || !((Collection) iterable).isEmpty()) {
                    for (ScheduledVisit status2 : iterable) {
                        if (Intrinsics.areEqual((Object) status2.getStatus(), (Object) "requested") && (i3 = i3 + 1) < 0) {
                            CollectionsKt.throwCountOverflow();
                        }
                    }
                }
                ((InmateDetailView) getViewState()).showVisitsText(i2 + " Scheduled, " + i3 + " Pending");
            }
        }
    }

    public final void onMessageButtonClicked() {
        this.router.navigateTo(new Screens.ConversationScreen(this.inmate.getId()));
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }

    public final void onInmateDeleteClicked() {
        ((InmateDetailView) getViewState()).showDeleteDialog(this.inmate.getFirst_name());
    }

    public void onDestroy() {
        super.onDestroy();
        getDisposables().dispose();
    }

    public final void onDeleteConfirmed() {
        CompositeDisposable disposables = getDisposables();
        InmateDetailPresenter inmateDetailPresenter = this;
        Disposable subscribe = this.accountInteractor.deleteInmate(this.inmate.getId()).subscribe(new InmateDetailPresenter$sam$io_reactivex_functions_Consumer$0(new InmateDetailPresenter$onDeleteConfirmed$1(inmateDetailPresenter)), new InmateDetailPresenter$sam$io_reactivex_functions_Consumer$0(new InmateDetailPresenter$onDeleteConfirmed$2(inmateDetailPresenter)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "accountInteractor.delete…ss, this::onDeleteFailed)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onDeleteFailed(Throwable th) {
        if (th != null) {
            String localizedMessage = th.getLocalizedMessage();
            Intrinsics.checkExpressionValueIsNotNull(localizedMessage, "it.localizedMessage");
            ((InmateDetailView) getViewState()).showMessage(localizedMessage);
            Timber.e(th);
        }
    }

    /* access modifiers changed from: private */
    public final void onDeleteSuccess(ApiResponse<? extends List<Inmate>> apiResponse) {
        if (apiResponse == null || !apiResponse.getOk()) {
            ((InmateDetailView) getViewState()).showMessage("Delete failed");
            return;
        }
        CompositeDisposable disposables = getDisposables();
        Single fromCallable = Single.fromCallable(new InmateDetailPresenter$onDeleteSuccess$1(this));
        Intrinsics.checkExpressionValueIsNotNull(fromCallable, "Single.fromCallable { in…deleteInmate(inmate.id) }");
        Disposable subscribe = CommonKt.applyAsync(fromCallable).subscribe(new InmateDetailPresenter$onDeleteSuccess$2(this), new InmateDetailPresenter$onDeleteSuccess$3(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "Single.fromCallable { in…\")\n                    })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onAllowCallsClicked(boolean z) {
        Timber.d("onAllowCallsClicked: new state=" + z + "; current=" + isVisible(), new Object[0]);
        if (isVisible() != z) {
            ((InmateDetailView) getViewState()).setCheckInactive();
            CompositeDisposable disposables = getDisposables();
            InmateDetailPresenter inmateDetailPresenter = this;
            Disposable subscribe = this.accountInteractor.changeVisibleToInmateState(this.inmate.getId()).subscribe(new InmateDetailPresenter$sam$io_reactivex_functions_Consumer$0(new InmateDetailPresenter$onAllowCallsClicked$1(inmateDetailPresenter)), new InmateDetailPresenter$sam$io_reactivex_functions_Consumer$0(new InmateDetailPresenter$onAllowCallsClicked$2(inmateDetailPresenter)));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "accountInteractor.change…:onVisibilityChangeError)");
            DisposableKt.plusAssign(disposables, subscribe);
        }
    }

    /* access modifiers changed from: private */
    public final void onVisibilityChangeError(Throwable th) {
        Timber.e(th);
        ((InmateDetailView) getViewState()).setCheckActive();
        ((InmateDetailView) getViewState()).setInvisible(isVisible());
    }

    /* access modifiers changed from: private */
    /* JADX WARNING: Code restructure failed: missing block: B:2:0x000c, code lost:
        r1 = r5.getBody();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onVisibilityChanged(com.forasoft.homewavvisitor.model.server.response.ApiResponse<com.forasoft.homewavvisitor.model.data.Inmate> r5) {
        /*
            r4 = this;
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r1 = "onVisibilityChanged: new state = "
            r0.append(r1)
            if (r5 == 0) goto L_0x0019
            java.lang.Object r1 = r5.getBody()
            com.forasoft.homewavvisitor.model.data.Inmate r1 = (com.forasoft.homewavvisitor.model.data.Inmate) r1
            if (r1 == 0) goto L_0x0019
            java.lang.String r1 = r1.getInvisible()
            goto L_0x001a
        L_0x0019:
            r1 = 0
        L_0x001a:
            r0.append(r1)
            java.lang.String r0 = r0.toString()
            r1 = 0
            java.lang.Object[] r1 = new java.lang.Object[r1]
            timber.log.Timber.d(r0, r1)
            moxy.MvpView r0 = r4.getViewState()
            com.forasoft.homewavvisitor.presentation.view.home.InmateDetailView r0 = (com.forasoft.homewavvisitor.presentation.view.home.InmateDetailView) r0
            r0.setCheckActive()
            if (r5 == 0) goto L_0x00c5
            java.lang.Object r0 = r5.getBody()
            com.forasoft.homewavvisitor.model.data.Inmate r0 = (com.forasoft.homewavvisitor.model.data.Inmate) r0
            if (r0 == 0) goto L_0x00c5
            com.forasoft.homewavvisitor.model.data.Inmate r0 = r4.inmate
            java.lang.Object r5 = r5.getBody()
            com.forasoft.homewavvisitor.model.data.Inmate r5 = (com.forasoft.homewavvisitor.model.data.Inmate) r5
            java.lang.String r5 = r5.getInvisible()
            r0.setInvisible(r5)
            moxy.MvpView r5 = r4.getViewState()
            com.forasoft.homewavvisitor.presentation.view.home.InmateDetailView r5 = (com.forasoft.homewavvisitor.presentation.view.home.InmateDetailView) r5
            boolean r0 = r4.isVisible()
            r5.setInvisible(r0)
            io.reactivex.subjects.BehaviorSubject<java.util.List<com.forasoft.homewavvisitor.model.data.Inmate>> r5 = r4.subject
            java.lang.Object r5 = r5.getValue()
            java.lang.String r0 = "subject.value"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r5, r0)
            java.lang.Iterable r5 = (java.lang.Iterable) r5
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.Iterator r5 = r5.iterator()
        L_0x006e:
            boolean r1 = r5.hasNext()
            if (r1 == 0) goto L_0x008f
            java.lang.Object r1 = r5.next()
            r2 = r1
            com.forasoft.homewavvisitor.model.data.Inmate r2 = (com.forasoft.homewavvisitor.model.data.Inmate) r2
            java.lang.String r2 = r2.getId()
            com.forasoft.homewavvisitor.model.data.Inmate r3 = r4.inmate
            java.lang.String r3 = r3.getId()
            boolean r2 = kotlin.jvm.internal.Intrinsics.areEqual((java.lang.Object) r2, (java.lang.Object) r3)
            if (r2 != 0) goto L_0x006e
            r0.add(r1)
            goto L_0x006e
        L_0x008f:
            java.util.List r0 = (java.util.List) r0
            java.util.Collection r0 = (java.util.Collection) r0
            java.util.List r5 = kotlin.collections.CollectionsKt.toMutableList(r0)
            com.forasoft.homewavvisitor.model.data.Inmate r0 = r4.inmate
            r5.add(r0)
            io.reactivex.subjects.BehaviorSubject<java.util.List<com.forasoft.homewavvisitor.model.data.Inmate>> r0 = r4.subject
            r0.onNext(r5)
            io.reactivex.disposables.CompositeDisposable r5 = r4.getDisposables()
            com.forasoft.homewavvisitor.presentation.presenter.home.InmateDetailPresenter$onVisibilityChanged$1 r0 = new com.forasoft.homewavvisitor.presentation.presenter.home.InmateDetailPresenter$onVisibilityChanged$1
            r0.<init>(r4)
            java.util.concurrent.Callable r0 = (java.util.concurrent.Callable) r0
            io.reactivex.Single r0 = io.reactivex.Single.fromCallable(r0)
            java.lang.String r1 = "Single.fromCallable { in…eDao.saveInmate(inmate) }"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            io.reactivex.Single r0 = com.forasoft.homewavvisitor.extension.CommonKt.applyAsync(r0)
            io.reactivex.disposables.Disposable r0 = r0.subscribe()
            java.lang.String r1 = "Single.fromCallable { in…             .subscribe()"
            kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(r0, r1)
            io.reactivex.rxkotlin.DisposableKt.plusAssign(r5, r0)
        L_0x00c5:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.home.InmateDetailPresenter.onVisibilityChanged(com.forasoft.homewavvisitor.model.server.response.ApiResponse):void");
    }

    public final void refresh() {
        this.heartbeatRepository.doHeartbeat();
    }
}
