package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.HomewavRouter;
import com.forasoft.homewavvisitor.dao.InmateDao;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.data.Inmate;
import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.data.auth.User;
import com.forasoft.homewavvisitor.model.repository.HeartbeatRepository;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.account.InmateChooserView;
import com.forasoft.homewavvisitor.toothpick.PrimitiveWrapper;
import io.reactivex.Flowable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u000e\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0006\u0010\u000f\u001a\u00020\u0010J\b\u0010\u0011\u001a\u00020\u0010H\u0014J\u000e\u0010\u0012\u001a\u00020\u00102\u0006\u0010\u0013\u001a\u00020\u0007R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/InmateChooserPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/account/InmateChooserView;", "router", "Lcom/forasoft/homewavvisitor/HomewavRouter;", "inmateWrapper", "Lcom/forasoft/homewavvisitor/toothpick/PrimitiveWrapper;", "Lcom/forasoft/homewavvisitor/model/data/Inmate;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "inmateDao", "Lcom/forasoft/homewavvisitor/dao/InmateDao;", "heartbeatRepository", "Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;", "(Lcom/forasoft/homewavvisitor/HomewavRouter;Lcom/forasoft/homewavvisitor/toothpick/PrimitiveWrapper;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/dao/InmateDao;Lcom/forasoft/homewavvisitor/model/repository/HeartbeatRepository;)V", "onBackPressed", "", "onFirstViewAttach", "onInmateClicked", "inmate", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: InmateChooserPresenter.kt */
public final class InmateChooserPresenter extends BasePresenter<InmateChooserView> {
    private final AuthHolder authHolder;
    /* access modifiers changed from: private */
    public final HeartbeatRepository heartbeatRepository;
    private final InmateDao inmateDao;
    /* access modifiers changed from: private */
    public final PrimitiveWrapper<Inmate> inmateWrapper;
    private final HomewavRouter router;

    @Inject
    public InmateChooserPresenter(HomewavRouter homewavRouter, PrimitiveWrapper<Inmate> primitiveWrapper, AuthHolder authHolder2, InmateDao inmateDao2, HeartbeatRepository heartbeatRepository2) {
        Intrinsics.checkParameterIsNotNull(homewavRouter, "router");
        Intrinsics.checkParameterIsNotNull(primitiveWrapper, "inmateWrapper");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        Intrinsics.checkParameterIsNotNull(inmateDao2, "inmateDao");
        Intrinsics.checkParameterIsNotNull(heartbeatRepository2, "heartbeatRepository");
        this.router = homewavRouter;
        this.inmateWrapper = primitiveWrapper;
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
        Flowable<R> map = inmateDao2.getInmates(user.getVisitor_id()).map(new InmateChooserPresenter$onFirstViewAttach$1(this));
        Intrinsics.checkExpressionValueIsNotNull(map, "inmateDao.getInmates(aut…mateWrapper.value?.id } }");
        Disposable subscribe = CommonKt.applyAsync(map).subscribe(new InmateChooserPresenter$onFirstViewAttach$2(this), InmateChooserPresenter$onFirstViewAttach$3.INSTANCE);
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "inmateDao.getInmates(aut… }\n                }, {})");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public final void onInmateClicked(Inmate inmate) {
        Intrinsics.checkParameterIsNotNull(inmate, "inmate");
        this.router.exitWithResult(-1, inmate);
    }

    public final void onBackPressed() {
        this.router.exitWithResult(0, (Object) null);
    }
}
