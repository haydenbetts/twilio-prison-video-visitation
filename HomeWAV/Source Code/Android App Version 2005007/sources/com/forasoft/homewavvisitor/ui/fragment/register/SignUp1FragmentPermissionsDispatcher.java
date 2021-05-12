package com.forasoft.homewavvisitor.ui.fragment.register;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.PermissionUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0000\u001a\n\u0010\b\u001a\u00020\t*\u00020\n\u001a\n\u0010\u000b\u001a\u00020\t*\u00020\n\u001a\u001a\u0010\f\u001a\u00020\t*\u00020\n2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"PERMISSION_ONAVATARFROMCAMERA", "", "", "[Ljava/lang/String;", "PERMISSION_ONIDFROMCAMERA", "REQUEST_ONAVATARFROMCAMERA", "", "REQUEST_ONIDFROMCAMERA", "onAvatarFromCameraWithPermissionCheck", "", "Lcom/forasoft/homewavvisitor/ui/fragment/register/SignUp1Fragment;", "onIdFromCameraWithPermissionCheck", "onRequestPermissionsResult", "requestCode", "grantResults", "", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: SignUp1FragmentPermissionsDispatcher.kt */
public final class SignUp1FragmentPermissionsDispatcher {
    private static final String[] PERMISSION_ONAVATARFROMCAMERA = {"android.permission.CAMERA"};
    private static final String[] PERMISSION_ONIDFROMCAMERA = {"android.permission.CAMERA"};
    private static final int REQUEST_ONAVATARFROMCAMERA = 6;
    private static final int REQUEST_ONIDFROMCAMERA = 7;

    public static final void onAvatarFromCameraWithPermissionCheck(SignUp1Fragment signUp1Fragment) {
        Intrinsics.checkParameterIsNotNull(signUp1Fragment, "$this$onAvatarFromCameraWithPermissionCheck");
        String[] strArr = PERMISSION_ONAVATARFROMCAMERA;
        if (PermissionUtils.hasSelfPermissions(signUp1Fragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            signUp1Fragment.onAvatarFromCamera();
        } else {
            signUp1Fragment.requestPermissions(strArr, REQUEST_ONAVATARFROMCAMERA);
        }
    }

    public static final void onIdFromCameraWithPermissionCheck(SignUp1Fragment signUp1Fragment) {
        Intrinsics.checkParameterIsNotNull(signUp1Fragment, "$this$onIdFromCameraWithPermissionCheck");
        String[] strArr = PERMISSION_ONIDFROMCAMERA;
        if (PermissionUtils.hasSelfPermissions(signUp1Fragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            signUp1Fragment.onIdFromCamera();
        } else {
            signUp1Fragment.requestPermissions(strArr, REQUEST_ONIDFROMCAMERA);
        }
    }

    public static final void onRequestPermissionsResult(SignUp1Fragment signUp1Fragment, int i, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(signUp1Fragment, "$this$onRequestPermissionsResult");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        if (i == REQUEST_ONAVATARFROMCAMERA) {
            if (PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
                signUp1Fragment.onAvatarFromCamera();
            }
        } else if (i == REQUEST_ONIDFROMCAMERA && PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
            signUp1Fragment.onIdFromCamera();
        }
    }
}
