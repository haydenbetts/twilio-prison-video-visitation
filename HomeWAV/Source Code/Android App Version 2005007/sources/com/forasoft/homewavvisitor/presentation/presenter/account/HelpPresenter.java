package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.dao.NotificationDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.HelpView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001f\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\u000bH\u0014J\u0006\u0010\u000f\u001a\u00020\u000bR\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/HelpPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/HelpView;", "router", "Lru/terrakok/cicerone/Router;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "notificationDao", "Lcom/forasoft/homewavvisitor/dao/NotificationDao;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/dao/NotificationDao;)V", "getNotificationsCount", "", "onBackPressed", "", "onFirstViewAttach", "onNotificationsClicked", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: HelpPresenter.kt */
public final class HelpPresenter extends BasePresenter<HelpView> {
    private final HomewavApi api;
    private final NotificationDao notificationDao;
    private final Router router;

    @Inject
    public HelpPresenter(Router router2, HomewavApi homewavApi, NotificationDao notificationDao2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(notificationDao2, "notificationDao");
        this.router = router2;
        this.api = homewavApi;
        this.notificationDao = notificationDao2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.api.getSupportInfo()).doOnSubscribe(new HelpPresenter$onFirstViewAttach$1(this)).doOnEvent(new HelpPresenter$onFirstViewAttach$2(this)).subscribe(new HelpPresenter$onFirstViewAttach$3(this), new HelpPresenter$onFirstViewAttach$4(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getSupportInfo()\n   …     }\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void getNotificationsCount() {
        getDisposables().add(CommonKt.applyAsync(this.notificationDao.countAll()).subscribe(new HelpPresenter$getNotificationsCount$1(this)));
    }

    public final void onNotificationsClicked() {
        this.router.navigateTo(Screens.NotificationsScreen.INSTANCE);
    }

    public final boolean onBackPressed() {
        this.router.exit();
        return true;
    }
}
