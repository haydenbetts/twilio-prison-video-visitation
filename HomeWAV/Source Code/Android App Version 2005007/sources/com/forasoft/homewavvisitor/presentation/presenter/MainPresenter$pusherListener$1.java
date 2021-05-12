package com.forasoft.homewavvisitor.presentation.presenter;

import com.forasoft.homewavvisitor.model.data.Call;
import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.forasoft.homewavvisitor.model.pusher.PusherObserver;
import com.forasoft.homewavvisitor.presentation.presenter.MainPresenter;
import com.forasoft.homewavvisitor.presentation.view.MainView;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000\u0017\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0016¨\u0006\u0006"}, d2 = {"com/forasoft/homewavvisitor/presentation/presenter/MainPresenter$pusherListener$1", "Lcom/forasoft/homewavvisitor/model/pusher/PusherObserver;", "onEvent", "", "event", "Lcom/forasoft/homewavvisitor/model/pusher/PusherEvent;", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: MainPresenter.kt */
public final class MainPresenter$pusherListener$1 implements PusherObserver {
    final /* synthetic */ MainPresenter this$0;

    MainPresenter$pusherListener$1(MainPresenter mainPresenter) {
        this.this$0 = mainPresenter;
    }

    public void onEvent(PusherEvent pusherEvent) {
        Intrinsics.checkParameterIsNotNull(pusherEvent, "event");
        int i = MainPresenter.WhenMappings.$EnumSwitchMapping$0[pusherEvent.getType().ordinal()];
        if (i != 1) {
            if (i == 2) {
                MainView mainView = (MainView) this.this$0.getViewState();
                Object value = pusherEvent.getValue();
                if (value != null) {
                    mainView.showCallDialog((Call) value);
                    return;
                }
                throw new TypeCastException("null cannot be cast to non-null type com.forasoft.homewavvisitor.model.data.Call");
            }
        } else if (!Intrinsics.areEqual(pusherEvent.getValue(), (Object) this.this$0.authHolder.getFcmToken())) {
            this.this$0.onLogout();
        }
    }
}
