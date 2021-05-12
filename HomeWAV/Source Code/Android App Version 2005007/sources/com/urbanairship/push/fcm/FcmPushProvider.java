package com.urbanairship.push.fcm;

import android.content.Context;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.urbanairship.AirshipVersionInfo;
import com.urbanairship.Logger;
import com.urbanairship.UAirship;
import com.urbanairship.google.PlayServicesUtils;
import com.urbanairship.push.PushProvider;
import com.urbanairship.util.UAStringUtil;
import java.util.Arrays;
import java.util.List;

public class FcmPushProvider implements PushProvider, AirshipVersionInfo {
    private static final List<String> INVALID_TOKENS = Arrays.asList(new String[]{"MESSENGER", "AP", "null"});

    public String getAirshipVersion() {
        return "14.0.2";
    }

    public String getDeliveryType() {
        return "fcm";
    }

    public String getPackageVersion() {
        return BuildConfig.SDK_VERSION;
    }

    public int getPlatform() {
        return 2;
    }

    public String getRegistrationToken(Context context) throws PushProvider.RegistrationException {
        try {
            FirebaseApp instance = FirebaseApp.getInstance();
            String senderId = getSenderId(instance);
            if (senderId == null) {
                Logger.error("The FCM sender ID is not set. Unable to register with FCM.", new Object[0]);
                return null;
            }
            FirebaseInstanceId instance2 = FirebaseInstanceId.getInstance(instance);
            String token = instance2.getToken(senderId, "FCM");
            if (token != null) {
                if (INVALID_TOKENS.contains(token) || UAirship.getPackageName().equals(token)) {
                    instance2.deleteToken(senderId, "FCM");
                    throw new PushProvider.RegistrationException("Invalid FCM token", true);
                }
            }
            return token;
        } catch (Exception e) {
            throw new PushProvider.RegistrationException("FCM error " + e.getMessage(), true, e);
        }
    }

    public boolean isAvailable(Context context) {
        try {
            if (PlayServicesUtils.isGooglePlayServicesAvailable(context) != 0) {
                Logger.info("Google Play services is currently unavailable.", new Object[0]);
                return false;
            } else if (getSenderId(FirebaseApp.getInstance()) != null) {
                return true;
            } else {
                Logger.error("The FCM sender ID is not set. Unable to register for FCM.", new Object[0]);
                return false;
            }
        } catch (Exception e) {
            Logger.error(e, "Unable to register with FCM.", new Object[0]);
            return false;
        }
    }

    public boolean isSupported(Context context) {
        return PlayServicesUtils.isGooglePlayStoreAvailable(context);
    }

    private String getSenderId(FirebaseApp firebaseApp) {
        String str = UAirship.shared().getAirshipConfigOptions().fcmSenderId;
        if (!UAStringUtil.isEmpty(str)) {
            return str;
        }
        return firebaseApp.getOptions().getGcmSenderId();
    }

    public String toString() {
        return "FCM Push Provider " + getAirshipVersion();
    }
}
