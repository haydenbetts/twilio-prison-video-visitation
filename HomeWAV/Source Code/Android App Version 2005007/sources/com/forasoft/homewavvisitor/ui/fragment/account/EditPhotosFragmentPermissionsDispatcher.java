package com.forasoft.homewavvisitor.ui.fragment.account;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import permissions.dispatcher.PermissionUtils;

@Metadata(bv = {1, 0, 3}, d1 = {"\u0000(\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0015\n\u0000\u001a\n\u0010\b\u001a\u00020\t*\u00020\n\u001a\n\u0010\u000b\u001a\u00020\t*\u00020\n\u001a\u001a\u0010\f\u001a\u00020\t*\u00020\n2\u0006\u0010\r\u001a\u00020\u00062\u0006\u0010\u000e\u001a\u00020\u000f\"\u0016\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0004¢\u0006\u0004\n\u0002\u0010\u0003\"\u000e\u0010\u0005\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0006XD¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"PERMISSION_ONAVATARFROMCAMERA", "", "", "[Ljava/lang/String;", "PERMISSION_ONIDFROMCAMERA", "REQUEST_ONAVATARFROMCAMERA", "", "REQUEST_ONIDFROMCAMERA", "onAvatarFromCameraWithPermissionCheck", "", "Lcom/forasoft/homewavvisitor/ui/fragment/account/EditPhotosFragment;", "onIdFromCameraWithPermissionCheck", "onRequestPermissionsResult", "requestCode", "grantResults", "", "app_release"}, k = 2, mv = {1, 1, 16})
/* compiled from: EditPhotosFragmentPermissionsDispatcher.kt */
public final class EditPhotosFragmentPermissionsDispatcher {
    private static final String[] PERMISSION_ONAVATARFROMCAMERA = {"android.permission.CAMERA"};
    private static final String[] PERMISSION_ONIDFROMCAMERA = {"android.permission.CAMERA"};
    private static final int REQUEST_ONAVATARFROMCAMERA = 2;
    private static final int REQUEST_ONIDFROMCAMERA = 3;

    public static final void onAvatarFromCameraWithPermissionCheck(EditPhotosFragment editPhotosFragment) {
        Intrinsics.checkParameterIsNotNull(editPhotosFragment, "$this$onAvatarFromCameraWithPermissionCheck");
        String[] strArr = PERMISSION_ONAVATARFROMCAMERA;
        if (PermissionUtils.hasSelfPermissions(editPhotosFragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            editPhotosFragment.onAvatarFromCamera();
        } else {
            editPhotosFragment.requestPermissions(strArr, REQUEST_ONAVATARFROMCAMERA);
        }
    }

    public static final void onIdFromCameraWithPermissionCheck(EditPhotosFragment editPhotosFragment) {
        Intrinsics.checkParameterIsNotNull(editPhotosFragment, "$this$onIdFromCameraWithPermissionCheck");
        String[] strArr = PERMISSION_ONIDFROMCAMERA;
        if (PermissionUtils.hasSelfPermissions(editPhotosFragment.getActivity(), (String[]) Arrays.copyOf(strArr, strArr.length))) {
            editPhotosFragment.onIdFromCamera();
        } else {
            editPhotosFragment.requestPermissions(strArr, REQUEST_ONIDFROMCAMERA);
        }
    }

    public static final void onRequestPermissionsResult(EditPhotosFragment editPhotosFragment, int i, int[] iArr) {
        Intrinsics.checkParameterIsNotNull(editPhotosFragment, "$this$onRequestPermissionsResult");
        Intrinsics.checkParameterIsNotNull(iArr, "grantResults");
        if (i == REQUEST_ONAVATARFROMCAMERA) {
            if (PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
                editPhotosFragment.onAvatarFromCamera();
            }
        } else if (i == REQUEST_ONIDFROMCAMERA && PermissionUtils.verifyPermissions(Arrays.copyOf(iArr, iArr.length))) {
            editPhotosFragment.onIdFromCamera();
        }
    }
}
