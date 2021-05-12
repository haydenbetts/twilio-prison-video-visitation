package com.forasoft.homewavvisitor.ui.fragment.calls;

import com.forasoft.homewavvisitor.model.UploadWorker;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.GrantableRequest;
import permissions.dispatcher.PermissionUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u00002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0005\u001a\u001a\u0010\u000b\u001a\u00020\f*\u00020\r2\u0006\u0010\u000e\u001a\u00020\t2\u0006\u0010\u000f\u001a\u00020\u0010\u001a\u001a\u0010\u0011\u001a\u00020\f*\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005\u001a\u001a\u0010\u0014\u001a\u00020\f*\u00020\r2\u0006\u0010\u0012\u001a\u00020\u00052\u0006\u0010\u0013\u001a\u00020\u0005\"\u0010\u0010\u0000\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000\"\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0001X\u000e¢\u0006\u0002\n\u0000\"\u0016\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u0016\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0004¢\u0006\u0004\n\u0002\u0010\u0006\"\u000e\u0010\b\u001a\u00020\tXD¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\tXD¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"PENDING_STARTLIVESWITCHCALL", "Lpermissions/dispatcher/GrantableRequest;", "PENDING_STARTTWILIOCALL", "PERMISSION_STARTLIVESWITCHCALL", "", "", "[Ljava/lang/String;", "PERMISSION_STARTTWILIOCALL", "REQUEST_STARTLIVESWITCHCALL", "", "REQUEST_STARTTWILIOCALL", "onRequestPermissionsResult", "", "Lcom/forasoft/homewavvisitor/ui/fragment/calls/IncomingCallDialogFragment;", "requestCode", "grantResults", "", "startLiveswitchCallWithPermissionCheck", "callId", "pubid", "startTwilioCallWithPermissionCheck", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: IncomingCallDialogFragmentPermissionsDispatcher.kt */
public final class IncomingCallDialogFragmentPermissionsDispatcher {
    private static GrantableRequest PENDING_STARTLIVESWITCHCALL = null;
    private static GrantableRequest PENDING_STARTTWILIOCALL = null;
    /* access modifiers changed from: private */
    public static final String[] PERMISSION_STARTLIVESWITCHCALL = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    /* access modifiers changed from: private */
    public static final String[] PERMISSION_STARTTWILIOCALL = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    /* access modifiers changed from: private */
    public static final int REQUEST_STARTLIVESWITCHCALL = 4;
    /* access modifiers changed from: private */
    public static final int REQUEST_STARTTWILIOCALL = 5;

    public static final void startTwilioCallWithPermissionCheck(IncomingCallDialogFragment incomingCallDialogFragment, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(incomingCallDialogFragment, "$this$startTwilioCallWithPermissionCheck");
        Intrinsics.checkParameterIsNotNull(str, "callId");
        Intrinsics.checkParameterIsNotNull(str2, UploadWorker.KEY_PUB_ID);
        String[] strArr = PERMISSION_STARTTWILIOCALL;
        if (PermissionUtils.hasSelfPermissions(incomingCallDialogFragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            incomingCallDialogFragment.startTwilioCall(str, str2);
            return;
        }
        PENDING_STARTTWILIOCALL = new IncomingCallDialogFragmentStartTwilioCallPermissionRequest(incomingCallDialogFragment, str, str2);
        incomingCallDialogFragment.requestPermissions(strArr, REQUEST_STARTTWILIOCALL);
    }

    public static final void startLiveswitchCallWithPermissionCheck(IncomingCallDialogFragment incomingCallDialogFragment, String str, String str2) {
        Intrinsics.checkParameterIsNotNull(incomingCallDialogFragment, "$this$startLiveswitchCallWithPermissionCheck");
        Intrinsics.checkParameterIsNotNull(str, "callId");
        Intrinsics.checkParameterIsNotNull(str2, UploadWorker.KEY_PUB_ID);
        String[] strArr = PERMISSION_STARTLIVESWITCHCALL;
        if (PermissionUtils.hasSelfPermissions(incomingCallDialogFragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            incomingCallDialogFragment.startLiveswitchCall(str, str2);
            return;
        }
        PENDING_STARTLIVESWITCHCALL = new IncomingCallDialogFragmentStartLiveswitchCallPermissionRequest(incomingCallDialogFragment, str, str2);
        incomingCallDialogFragment.requestPermissions(strArr, REQUEST_STARTLIVESWITCHCALL);
    }

    public static final void onRequestPermissionsResult(IncomingCallDialogFragment incomingCallDialogFragment, int i, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(incomingCallDialogFragment, "$this$onRequestPermissionsResult");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        if (i == REQUEST_STARTTWILIOCALL) {
            if (PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
                GrantableRequest grantableRequest = PENDING_STARTTWILIOCALL;
                if (grantableRequest != null) {
                    grantableRequest.grant();
                }
            } else {
                incomingCallDialogFragment.onDeniedCallPermissions();
            }
            PENDING_STARTTWILIOCALL = null;
        } else if (i == REQUEST_STARTLIVESWITCHCALL) {
            if (PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
                GrantableRequest grantableRequest2 = PENDING_STARTLIVESWITCHCALL;
                if (grantableRequest2 != null) {
                    grantableRequest2.grant();
                }
            } else {
                incomingCallDialogFragment.onDeniedCallPermissions();
            }
            PENDING_STARTLIVESWITCHCALL = null;
        }
    }
}
