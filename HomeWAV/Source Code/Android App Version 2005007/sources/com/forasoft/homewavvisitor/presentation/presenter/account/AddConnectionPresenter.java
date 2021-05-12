package com.forasoft.homewavvisitor.presentation.presenter.account;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.presentation.presenter.register.AddConnectionSignUpPresenter;
import com.forasoft.homewavvisitor.toothpick.DI;
import kotlin.Metadata;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0007\u0018\u00002\u00020\u0001BO\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016J\u0006\u0010\u0017\u001a\u00020\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/account/AddConnectionPresenter;", "Lcom/forasoft/homewavvisitor/presentation/presenter/register/AddConnectionSignUpPresenter;", "router", "Lru/terrakok/cicerone/Router;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "userDao", "Lcom/forasoft/homewavvisitor/dao/UserDao;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "notifier", "Lcom/forasoft/homewavvisitor/navigation/BusNotifier;", "context", "Landroid/content/Context;", "facilitiesSubjectWrapper", "Lcom/forasoft/homewavvisitor/toothpick/FacilitiesSubjectWrapper;", "registerDataInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/register/AddConnectionInteractor;", "registerStepsInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;Lcom/forasoft/homewavvisitor/dao/UserDao;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/navigation/BusNotifier;Landroid/content/Context;Lcom/forasoft/homewavvisitor/toothpick/FacilitiesSubjectWrapper;Lcom/forasoft/homewavvisitor/model/interactor/register/AddConnectionInteractor;Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;)V", "nextStep", "", "onBackPressed", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: AddConnectionPresenter.kt */
public final class AddConnectionPresenter extends AddConnectionSignUpPresenter {
    private final AuthHolder authHolder;
    private final Router router;

    /* JADX WARNING: Illegal instructions before constructor call */
    @javax.inject.Inject
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public AddConnectionPresenter(ru.terrakok.cicerone.Router r14, com.forasoft.homewavvisitor.model.data.auth.AuthHolder r15, com.forasoft.homewavvisitor.dao.UserDao r16, com.forasoft.homewavvisitor.model.server.apis.HomewavApi r17, com.forasoft.homewavvisitor.navigation.BusNotifier r18, android.content.Context r19, com.forasoft.homewavvisitor.toothpick.FacilitiesSubjectWrapper r20, com.forasoft.homewavvisitor.model.interactor.register.AddConnectionInteractor r21, com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor r22) {
        /*
            r13 = this;
            r10 = r13
            r11 = r14
            r12 = r15
            java.lang.String r0 = "router"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r14, r0)
            java.lang.String r0 = "authHolder"
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r15, r0)
            java.lang.String r0 = "userDao"
            r4 = r16
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r4, r0)
            java.lang.String r0 = "api"
            r5 = r17
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r5, r0)
            java.lang.String r0 = "notifier"
            r6 = r18
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r6, r0)
            java.lang.String r0 = "context"
            r1 = r19
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r1, r0)
            java.lang.String r0 = "facilitiesSubjectWrapper"
            r7 = r20
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r7, r0)
            java.lang.String r0 = "registerDataInteractor"
            r8 = r21
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r8, r0)
            java.lang.String r0 = "registerStepsInteractor"
            r9 = r22
            kotlin.jvm.internal.Intrinsics.checkParameterIsNotNull(r9, r0)
            r0 = r13
            r2 = r14
            r3 = r15
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9)
            r10.router = r11
            r10.authHolder = r12
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.forasoft.homewavvisitor.presentation.presenter.account.AddConnectionPresenter.<init>(ru.terrakok.cicerone.Router, com.forasoft.homewavvisitor.model.data.auth.AuthHolder, com.forasoft.homewavvisitor.dao.UserDao, com.forasoft.homewavvisitor.model.server.apis.HomewavApi, com.forasoft.homewavvisitor.navigation.BusNotifier, android.content.Context, com.forasoft.homewavvisitor.toothpick.FacilitiesSubjectWrapper, com.forasoft.homewavvisitor.model.interactor.register.AddConnectionInteractor, com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor):void");
    }

    public void nextStep() {
        Toothpick.closeScope(DI.ADD_CONNECTION_SCOPE);
        this.router.exit();
    }

    public final void onBackPressed() {
        Toothpick.closeScope(DI.ADD_CONNECTION_SCOPE);
        this.router.exit();
    }
}
