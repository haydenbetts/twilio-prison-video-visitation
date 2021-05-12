package com.forasoft.homewavvisitor.presentation.presenter.calls;

import air.HomeWAV.R;
import com.forasoft.homewavvisitor.extension.CommonKt;
import com.forasoft.homewavvisitor.model.pusher.PusherEvent;
import com.forasoft.homewavvisitor.model.pusher.PusherHolder;
import com.forasoft.homewavvisitor.model.server.apis.HomewavApi;
import com.forasoft.homewavvisitor.model.server.response.CallWrapper;
import com.forasoft.homewavvisitor.presentation.BasePresenter;
import com.forasoft.homewavvisitor.presentation.view.calls.CallView;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.rxkotlin.DisposableKt;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000;\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003*\u0001\f\b&\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\b\u0012\u0004\u0012\u0002H\u00010\u0003B\u001d\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0002\u0010\nJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u000e\u0010\u0011\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u0013J\b\u0010\u0014\u001a\u00020\u000fH\u0016J\b\u0010\u0015\u001a\u00020\u000fH&R\u000e\u0010\u0006\u001a\u00020\u0007X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\u000b\u001a\b\u0012\u0004\u0012\u00028\u00000\fX\u0004¢\u0006\u0004\n\u0002\u0010\rR\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Lcom/forasoft/homewavvisitor/presentation/presenter/calls/CallPresenter;", "V", "Lcom/forasoft/homewavvisitor/presentation/view/calls/CallView;", "Lcom/forasoft/homewavvisitor/presentation/BasePresenter;", "wrapper", "Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;", "api", "Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;", "pusherHolder", "Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;", "(Lcom/forasoft/homewavvisitor/model/server/response/CallWrapper;Lcom/forasoft/homewavvisitor/model/server/apis/HomewavApi;Lcom/forasoft/homewavvisitor/model/pusher/PusherHolder;)V", "pusherListener", "com/forasoft/homewavvisitor/presentation/presenter/calls/CallPresenter$pusherListener$1", "Lcom/forasoft/homewavvisitor/presentation/presenter/calls/CallPresenter$pusherListener$1;", "initPusherListener", "", "initUI", "onBugReportClicked", "problems", "", "onDestroy", "stopCall", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: CallPresenter.kt */
public abstract class CallPresenter<V extends CallView> extends BasePresenter<V> {
    /* access modifiers changed from: private */
    public final HomewavApi api;
    private final PusherHolder pusherHolder;
    private final CallPresenter$pusherListener$1 pusherListener = new CallPresenter$pusherListener$1(this);
    /* access modifiers changed from: private */
    public final CallWrapper wrapper;

    @Metadata(bv = {1, 0, 3}, k = 3, mv = {1, 1, 16})
    public final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[PusherEvent.Type.values().length];
            $EnumSwitchMapping$0 = iArr;
            iArr[PusherEvent.Type.call_disconnected.ordinal()] = 1;
            iArr[PusherEvent.Type.admin_warning.ordinal()] = 2;
            iArr[PusherEvent.Type.admin_message.ordinal()] = 3;
            iArr[PusherEvent.Type.visitor_login.ordinal()] = 4;
        }
    }

    public abstract void stopCall();

    public CallPresenter(CallWrapper callWrapper, HomewavApi homewavApi, PusherHolder pusherHolder2) {
        Intrinsics.checkParameterIsNotNull(callWrapper, "wrapper");
        Intrinsics.checkParameterIsNotNull(homewavApi, "api");
        Intrinsics.checkParameterIsNotNull(pusherHolder2, "pusherHolder");
        this.wrapper = callWrapper;
        this.api = homewavApi;
        this.pusherHolder = pusherHolder2;
    }

    public final void initUI() {
        if (Intrinsics.areEqual((Object) this.wrapper.getCall().getRecorded(), (Object) "0")) {
            ((CallView) getViewState()).setRecordingWarningText(R.string.call_not_recording_warning, R.string.call_not_recording_warning_es);
        } else {
            ((CallView) getViewState()).setRecordingWarningText(R.string.call_recording_warning, R.string.call_recording_warning_es);
        }
        String prison_id = this.wrapper.getCall().getPrison_id();
        if (prison_id != null) {
            CompositeDisposable disposables = getDisposables();
            Disposable subscribe = CommonKt.applyAsync(this.api.getFacility(prison_id)).subscribe(new CallPresenter$initUI$1(this), CallPresenter$initUI$2.INSTANCE);
            Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.getFacility(facility….e(it)\n                })");
            DisposableKt.plusAssign(disposables, subscribe);
        }
    }

    public final void initPusherListener() {
        this.pusherHolder.listenEvents(this.pusherListener);
    }

    public final void onBugReportClicked(String str) {
        Intrinsics.checkParameterIsNotNull(str, "problems");
        CompositeDisposable disposables = getDisposables();
        Disposable subscribe = CommonKt.applyAsync(this.api.reportError(str, this.wrapper.getCall().getId())).subscribe(new CallPresenter$onBugReportClicked$1(this), new CallPresenter$onBugReportClicked$2(this));
        Intrinsics.checkExpressionValueIsNotNull(subscribe, "api.reportError(problems…eport)\n                })");
        DisposableKt.plusAssign(disposables, subscribe);
    }

    public void onDestroy() {
        this.pusherHolder.stopListenEvents(this.pusherListener);
        super.onDestroy();
    }
}
