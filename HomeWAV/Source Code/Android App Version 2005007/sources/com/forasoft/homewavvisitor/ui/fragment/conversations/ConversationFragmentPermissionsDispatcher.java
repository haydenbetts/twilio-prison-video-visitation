package com.forasoft.homewavvisitor.ui.fragment.conversations;

import androidx.fragment.app.Fragment;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.PermissionUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\u001a\u001a\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r\u001a\n\u0010\u000e\u001a\u00020\t*\u00020\n\u001a\n\u0010\u000f\u001a\u00020\t*\u00020\n\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"PERMISSION_RECORDVIDEO", "", "", "[Ljava/lang/String;", "PERMISSION_TAKEIMAGE", "REQUEST_RECORDVIDEO", "", "REQUEST_TAKEIMAGE", "onRequestPermissionsResult", "", "Lcom/forasoft/homewavvisitor/ui/fragment/conversations/ConversationFragment;", "requestCode", "grantResults", "", "recordVideoWithPermissionCheck", "takeImageWithPermissionCheck", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: ConversationFragmentPermissionsDispatcher.kt */
public final class ConversationFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_RECORDVIDEO = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    private static final String[] PERMISSION_TAKEIMAGE = {"android.permission.READ_EXTERNAL_STORAGE"};
    private static final int REQUEST_RECORDVIDEO = 0;
    private static final int REQUEST_TAKEIMAGE = 1;

    public static final void recordVideoWithPermissionCheck(ConversationFragment conversationFragment) {
        Intrinsics.checkParameterIsNotNull(conversationFragment, "$this$recordVideoWithPermissionCheck");
        String[] strArr = PERMISSION_RECORDVIDEO;
        if (PermissionUtils.hasSelfPermissions(conversationFragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            conversationFragment.recordVideo();
        } else {
            conversationFragment.requestPermissions(strArr, REQUEST_RECORDVIDEO);
        }
    }

    public static final void takeImageWithPermissionCheck(ConversationFragment conversationFragment) {
        Intrinsics.checkParameterIsNotNull(conversationFragment, "$this$takeImageWithPermissionCheck");
        String[] strArr = PERMISSION_TAKEIMAGE;
        if (PermissionUtils.hasSelfPermissions(conversationFragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            conversationFragment.takeImage();
        } else {
            conversationFragment.requestPermissions(strArr, REQUEST_TAKEIMAGE);
        }
    }

    public static final void onRequestPermissionsResult(ConversationFragment conversationFragment, int i, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(conversationFragment, "$this$onRequestPermissionsResult");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        if (i == REQUEST_RECORDVIDEO) {
            if (PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
                conversationFragment.recordVideo();
                return;
            }
            String[] strArr = PERMISSION_RECORDVIDEO;
            if (!PermissionUtils.shouldShowRequestPermissionRationale((Fragment) conversationFragment, (String[]) Arrays.copyOf(strArr, strArr.length))) {
                conversationFragment.showNeverAskForRecordVideo$app_release();
            } else {
                conversationFragment.showDeniedForRecordVideo$app_release();
            }
        } else if (i != REQUEST_TAKEIMAGE) {
        } else {
            if (PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
                conversationFragment.takeImage();
                return;
            }
            String[] strArr2 = PERMISSION_TAKEIMAGE;
            if (!PermissionUtils.shouldShowRequestPermissionRationale((Fragment) conversationFragment, (String[]) Arrays.copyOf(strArr2, strArr2.length))) {
                conversationFragment.showNeverAskForTakeImage$app_release();
            } else {
                conversationFragment.showDeniedFoTakeImage$app_release();
            }
        }
    }
}
