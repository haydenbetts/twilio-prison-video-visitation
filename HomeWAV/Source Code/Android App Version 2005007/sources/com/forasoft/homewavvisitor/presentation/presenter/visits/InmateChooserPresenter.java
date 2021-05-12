package com.forasoft.homewavvisitor.presentation.presenter.visits;

import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.visits.InmateChooserView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B'\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n¢\u0006\u0002\u0010\u000bJ\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\rH\u0014J\u000e\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/visits/InmateChooserPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/visits/InmateChooserView;", "router", "Lru/terrakok/cicerone/Router;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;)V", "onBackPressed", "", "onFirstViewAttach", "onInmateClicked", "inmate", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: InmateChooserPresenter.kt */
public final class InmateChooserPresenter extends BasePresenter<InmateChooserView> {
    private final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public final HeartbeatRepository heartbeatRepository;
    private final InmateDao inmateDao;
    private final Router router;

    @Inject
    public InmateChooserPresenter(Router router2, AuthHolder authHolder2, InmateDao inmateDao2, HeartbeatRepository heartbeatRepository2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        this.router = router2;
        this.authHolder = authHolder2;
        this.inmateDao = inmateDao2;
        this.heartbeatRepository = heartbeatRepository2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        super.onFirstViewAttach();
        CompositeDisposable disposables = getDisposables();
        InmateDao inmateDao2 = this.inmateDao;
        User user = this.authHolder.getUser();
        if (user == null) {
            Intrinsics.throwNpe();
        }
        Disposable subscribe = CommonKt.applyAsync(inmateDao2.getApprovedInmates(user.getVisitor_id())).subscribe(new InmateChooserPresenter$onFirstViewAttach$1(this), InmateChooserPresenter$onFirstViewAttach$2.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmateDao.getApprovedInm… }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onInmateClicked(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        this.router.navigateTo(new Screens.DateChooseScreen(inmate));
    }

    public final void onBackPressed() {
        this.router.exit();
    }
}
