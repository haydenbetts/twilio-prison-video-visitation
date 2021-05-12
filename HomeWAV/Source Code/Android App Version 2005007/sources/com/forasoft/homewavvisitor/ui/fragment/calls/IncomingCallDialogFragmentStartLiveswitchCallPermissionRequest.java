package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.forasoft.homewavvisitor.model.UploadWorker;
import java.lang.ref.WeakReference;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.GrantableRequest;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0002\u0010\u0007J\b\u0010\n\u001a\u00020\u000bH\u0016J\b\u0010\f\u001a\u00020\u000bH\u0016J\b\u0010\r\u001a\u00020\u000bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\tX\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/forasoft/homewavvisitor/ui/fragment/calls/IncomingCallDialogFragmentStartLiveswitchCallPermissionRequest;", "Lpermissions/dispatcher/GrantableRequest;", "target", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/IncomingCallDialogFragment;", "callId", "", "pubid", "(Lcom/forasoft/homewavvisitor/ui/fragment/calls/IncomingCallDialogFragment;Ljava/lang/String;Ljava/lang/String;)V", "weakTarget", "Ljava/lang/ref/WeakReference;", "cancel", "", "grant", "proceed", "app_release"}, k = 1, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragmentPermissionsDispatcher.kt */
final class IncomingCallDialogFragmentStartLiveswitchCallPermissionRequest implements GrantableRequest {
    private final String callId;
    private final String pubid;
    private final WeakReference<IncomingCallDialogFragment> weakTarget;

    public IncomingCallDialogFragmentStartLiveswitchCallPermissionRequest(IncomingCallDialogFragment incomingCallDialogFragment, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(incomingCallDialogFragment, "target");
        Intrinsics.checkParameterIsNotNull(str, "callId");
        Intrinsics.checkParameterIsNotNull(str2, UploadWorker.KEY_PUB_ID);
        this.callId = str;
        this.pubid = str2;
        this.weakTarget = new WeakReference<>(incomingCallDialogFragment);
    }

    public void proceed() {
        IncomingCallDialogFragment incomingCallDialogFragment = (IncomingCallDialogFragment) this.weakTarget.get();
        if (incomingCallDialogFragment != null) {
            Intrinsics.checkExpressionValueIsNotNull(incomingCallDialogFragment, "weakTarget.get() ?: return");
            incomingCallDialogFragment.requestPermissions(IncomingCallDialogFragmentPermissionsDispatcher.PERMISSION_STARTLIVESWITCHCALL, IncomingCallDialogFragmentPermissionsDispatcher.REQUEST_STARTLIVESWITCHCALL);
        }
    }

    public void cancel() {
        IncomingCallDialogFragment incomingCallDialogFragment = (IncomingCallDialogFragment) this.weakTarget.get();
        if (incomingCallDialogFragment != null) {
            Intrinsics.checkExpressionValueIsNotNull(incomingCallDialogFragment, "weakTarget.get() ?: return");
            incomingCallDialogFragment.onDeniedCallPermissions();
        }
    }

    public void grant() {
        IncomingCallDialogFragment incomingCallDialogFragment = (IncomingCallDialogFragment) this.weakTarget.get();
        if (incomingCallDialogFragment != null) {
            Intrinsics.checkExpressionValueIsNotNull(incomingCallDialogFragment, "weakTarget.get() ?: return");
            incomingCallDialogFragment.startLiveswitchCall(this.callId, this.pubid);
        }
    }
}
