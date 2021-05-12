package com.twilio.video;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import com.forasoft.homewavvisitor.ui.views.AvatarUpdaterDialog;

class Camera2Utils {
    private static final Logger logger = Logger.getLogger(Camera2Utils.class);

    Camera2Utils() {
    }

    static boolean cameraIdSupported(Context context, String str) {
        try {
            for (String equals : ((CameraManager) context.getSystemService(AvatarUpdaterDialog.CAMERA)).getCameraIdList()) {
                if (str.equals(equals)) {
                    return true;
                }
            }
        } catch (CameraAccessException e) {
            logger.e(e.getMessage());
        }
        return false;
    }
}
