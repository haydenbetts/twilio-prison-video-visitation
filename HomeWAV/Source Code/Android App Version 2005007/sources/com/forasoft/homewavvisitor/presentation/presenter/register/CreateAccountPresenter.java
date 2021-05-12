package com.forasoft.homewavvisitor.presentation.presenter.register;

import com.forasoft.homewavvisitor.model.data.auth.AuthHolder;
import com.forasoft.homewavvisitor.model.interactor.register.RegisterStepsInteractor;
import com.forasoft.homewavvisitor.navigation.Screens;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.register.CreateAccountView;
import com.forasoft.homewavvisitor.toothpick.DI;
import com.forasoft.homewavvisitor.toothpick.qualifier.Global;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import moxy.InjectViewState;
import ru.terrakok.cicerone.Router;
import timber.log.Timber;
import toothpick.Toothpick;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B!\b\u0007\u0012\b\b\u0001\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0002\u0010\tJ\u0006\u0010\f\u001a\u00020\rJ\b\u0010\u000e\u001a\u00020\rH\u0014J\u0010\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002J\u000e\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u0011R\u000e\u0010\u0007\u001a\u00020\bX\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u000e¢\u0006\u0002\n\u0000¨\u0006\u0013"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/register/CreateAccountPresenter;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "Lcom/forasoft/homewavvisitor/presentation/view/register/CreateAccountView;", "router", "Lru/terrakok/cicerone/Router;", "registerStepsInteractor", "Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;", "authHolder", "Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;", "(Lru/terrakok/cicerone/Router;Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;Lcom/forasoft/homewavvisitor/model/data/auth/AuthHolder;)V", "warnCancelRegistration", "", "onBackPressed", "", "onFirstViewAttach", "onStepChanged", "step", "", "onStepClicked", "app_release"}, k = 1, mv = {1, 1, 16})
@InjectViewState
/* compiled from: CreateAccountPresenter.kt */
public final class CreateAccountPresenter extends BasePresenter<CreateAccountView> {
    private final AuthHolder authHolder;
    private final RegisterStepsInteractor registerStepsInteractor;
    private final Router router;
    private boolean warnCancelRegistration = true;

    public final void onStepClicked(int i) {
    }

    @Inject
    public CreateAccountPresenter(@Global Router router2, RegisterStepsInteractor registerStepsInteractor2, AuthHolder authHolder2) {
        Intrinsics.checkParameterIsNotNull(router2, "router");
        Intrinsics.checkParameterIsNotNull(registerStepsInteractor2, "registerStepsInteractor");
        Intrinsics.checkParameterIsNotNull(authHolder2, "authHolder");
        this.router = router2;
        this.registerStepsInteractor = registerStepsInteractor2;
        this.authHolder = authHolder2;
    }

    /* access modifiers changed from: protected */
    public void onFirstViewAttach() {
        Timber.d("onFirstViewAttach: %s", this.registerStepsInteractor.toString());
        this.router.replaceScreen(Screens.SignUp1Screen.INSTANCE);
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = this.registerStepsInteractor.subscribeForStepChangedSuccessfully().subscribe(new CreateAccountPresenter$sam$io_reactivex_functions_Consumer$0(new CreateAccountPresenter$onFirstViewAttach$1(this)));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "registerStepsInteractor.…ribe(this::onStepChanged)");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    /* access modifiers changed from: private */
    public final void onStepChanged(int i) {
        this.router.replaceScreen(Screens.INSTANCE.getSignUpSteps().get(i - 1));
        ((CreateAccountView) getViewState()).setStepperStep(i);
        this.warnCancelRegistration = true;
    }

    public final void onBackPressed() {
        if (this.warnCancelRegistration) {
            ((CreateAccountView) getViewState()).showMessage("Press again to exit registration");
            this.warnCancelRegistration = false;
            return;
        }
        try {
            Toothpick.closeScope(DI.ADD_CONNECTION_SCOPE);
        } catch (Exception unused) {
        }
        this.registerStepsInteractor.reset();
        this.authHolder.logout();
        this.router.exit();
    }
}
