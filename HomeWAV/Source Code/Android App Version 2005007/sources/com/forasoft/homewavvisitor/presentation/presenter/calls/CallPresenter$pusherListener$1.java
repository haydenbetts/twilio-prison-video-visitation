package com.forasoft.homewavvisitor.presentation.presenter.calls;

import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.forasoft.homewavvisitor.model.pusher.PusherObserver;
import com.forasoft.homewavvisitor.presentation.presenter.calls.CallPresenter;
import com.forasoft.homewavvisitor.presentation.view.calls.CallView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/presentation/presenter/calls/CallPresenter$pusherListener$1", "Lcom/forasoft/homewavvisitor/model/pusher/PusherObserver;", "onEvent", "", "event", "Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CallPresenter.kt */
public final class CallPresenter$pusherListener$1 implements PusherObserver {
    final /* synthetic */ CallPresenter this$0;

    CallPresenter$pusherListener$1(CallPresenter callPresenter) {
        this.this$0 = callPresenter;
    }

    public void onEvent(PusherEvent pusherEvent) {
        Intrinsics.checkParameterIsNotNull(pusherEvent, "event");
        int i = CallPresenter.WhenMappings.$EnumSwitchMapping$0[pusherEvent.getType().ordinal()];
        if (i == 1) {
            CompositeDisposable access$getDisposables$p = this.this$0.getDisposables();
            Disposable subscribe = CommonKt.applyAsync(this.this$0.api.getCallStatus(this.this$0.wrapper.getCall().getId())).filter(CallPresenter$pusherListener$1$onEvent$1.INSTANCE).subscribe(new CallPresenter$pusherListener$1$onEvent$2(this), new CallPresenter$pusherListener$1$onEvent$3(this));
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getCallStatus(wrappe…                       })");
            DisposableKt.plusAssign(access$getDisposables$p, subscribe);
        } else if (i != 2) {
            if (i == 3) {
                Object value = pusherEvent.getValue();
                if (value != null) {
                    CallView callView = (CallView) this.this$0.getViewState();
                    if (value != null) {
                        callView.showAdminMessage((String) value);
                        return;
                    }
                    throw new TypeCastException("null cannot be cast to non-null type kotlin.String");
                }
            } else if (i == 4) {
                this.this$0.stopCall();
            }
        } else if (pusherEvent.getValue() == null) {
            ((CallView) this.this$0.getViewState()).hideWarningMessage();
        } else {
            ((CallView) this.this$0.getViewState()).showWarningMessage();
        }
    }
}
