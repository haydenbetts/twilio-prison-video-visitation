package com.forasoft.homewavvisitor.model.interactor.register;

import io.reactivex.Observable;
import io.reactivex.subjects.PublishSubject;
import javax.inject.Inject;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0007\b\u0007¢\u0006\u0002\u0010\u0002J\u0006\u0010\u0007\u001a\u00020\bJ\u0006\u0010\t\u001a\u00020\bJ\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00040\u000bR\u000e\u0010\u0003\u001a\u00020\u0004X\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0006X\u0004¢\u0006\u0002\n\u0000¨\u0006\f"}, d2 = {"Lcom/forasoft/homewavvisitor/model/interactor/register/RegisterStepsInteractor;", "", "()V", "currentStep", "", "stepChangedNotifierSubject", "Lio/reactivex/subjects/PublishSubject;", "moveToNextStep", "", "reset", "subscribeForStepChangedSuccessfully", "Lio/reactivex/Observable;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: RegisterStepsInteractor.kt */
public final class RegisterStepsInteractor {
    private int currentStep = 1;
    private final PublishSubject<Integer> stepChangedNotifierSubject;

    @Inject
    public RegisterStepsInteractor() {
        PublishSubject<Integer> create = PublishSubject.create();
        Intrinsics.checkExpressionValueIsNotNull(create, "PublishSubject.create()");
        this.stepChangedNotifierSubject = create;
    }

    public final Observable<Integer> subscribeForStepChangedSuccessfully() {
        reset();
        return this.stepChangedNotifierSubject;
    }

    public final void moveToNextStep() {
        PublishSubject<Integer> publishSubject = this.stepChangedNotifierSubject;
        int i = this.currentStep + 1;
        this.currentStep = i;
        publishSubject.onNext(Integer.valueOf(i));
    }

    public final void reset() {
        this.currentStep = 1;
        this.stepChangedNotifierSubject.onNext(1);
    }
}
