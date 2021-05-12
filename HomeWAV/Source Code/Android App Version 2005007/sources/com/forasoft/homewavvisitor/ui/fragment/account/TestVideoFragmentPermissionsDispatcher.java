package com.forasoft.homewavvisitor.ui.fragment.account;

import androidx.fragment.app.Fragment;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.PermissionUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000*\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\u0003\u001a\u001a\u0010\b\u001a\u00020\t*\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\r\u001a\n\u0010\u000e\u001a\u00020\t*\u00020\n\u001a\n\u0010\u000f\u001a\u00020\t*\u00020\n\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"PERMISSION_PREPAREMICRECORDER", "", "", "[Ljava/lang/String;", "PERMISSION_SETUPCAMERA", "REQUEST_PREPAREMICRECORDER", "", "REQUEST_SETUPCAMERA", "onRequestPermissionsResult", "", "Lcom/forasoft/homewavvisitor/ui/fragment/account/TestVideoFragment;", "requestCode", "grantResults", "", "prepareMicRecorderWithPermissionCheck", "setupCameraWithPermissionCheck", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: TestVideoFragmentPermissionsDispatcher.kt */
public final class TestVideoFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_PREPAREMICRECORDER = {"android.permission.RECORD_AUDIO"};
    private static final String[] PERMISSION_SETUPCAMERA = {"android.permission.CAMERA", "android.permission.RECORD_AUDIO"};
    private static final int REQUEST_PREPAREMICRECORDER = 8;
    private static final int REQUEST_SETUPCAMERA = 9;

    public static final void setupCameraWithPermissionCheck(TestVideoFragment testVideoFragment) {
        Intrinsics.checkParameterIsNotNull(testVideoFragment, "$this$setupCameraWithPermissionCheck");
        String[] strArr = PERMISSION_SETUPCAMERA;
        if (PermissionUtils.hasSelfPermissions(testVideoFragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            testVideoFragment.setupCamera$app_release();
        } else {
            testVideoFragment.requestPermissions(strArr, REQUEST_SETUPCAMERA);
        }
    }

    public static final void prepareMicRecorderWithPermissionCheck(TestVideoFragment testVideoFragment) {
        Intrinsics.checkParameterIsNotNull(testVideoFragment, "$this$prepareMicRecorderWithPermissionCheck");
        String[] strArr = PERMISSION_PREPAREMICRECORDER;
        if (PermissionUtils.hasSelfPermissions(testVideoFragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            testVideoFragment.prepareMicRecorder$app_release();
        } else {
            testVideoFragment.requestPermissions(strArr, REQUEST_PREPAREMICRECORDER);
        }
    }

    public static final void onRequestPermissionsResult(TestVideoFragment testVideoFragment, int i, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(testVideoFragment, "$this$onRequestPermissionsResult");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        if (i == REQUEST_SETUPCAMERA) {
            if (PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
                testVideoFragment.setupCamera$app_release();
                return;
            }
            String[] strArr = PERMISSION_SETUPCAMERA;
            if (!PermissionUtils.shouldShowRequestPermissionRationale((Fragment) testVideoFragment, (String[]) Arrays.copyOf(strArr, strArr.length))) {
                testVideoFragment.onNeverAskCameraAndMicrophone$app_release();
            } else {
                testVideoFragment.onDeniedPermissionsToCameraAndMicrophone$app_release();
            }
        } else if (i != REQUEST_PREPAREMICRECORDER) {
        } else {
            if (PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
                testVideoFragment.prepareMicRecorder$app_release();
                return;
            }
            String[] strArr2 = PERMISSION_PREPAREMICRECORDER;
            if (!PermissionUtils.shouldShowRequestPermissionRationale((Fragment) testVideoFragment, (String[]) Arrays.copyOf(strArr2, strArr2.length))) {
                testVideoFragment.onNeverAskMicrophone$app_release();
            } else {
                testVideoFragment.onDeniedPermissionsToMicrophone$app_release();
            }
        }
    }
}
