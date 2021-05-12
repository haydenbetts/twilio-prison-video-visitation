package com.urbanairship.push;

import android.content.Context;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public interface PushProvider {
    public static final String ADM_DELIVERY_TYPE = "adm";
    public static final String FCM_DELIVERY_TYPE = "fcm";
    public static final String HMS_DELIVERY_TYPE = "hms";

    @Retention(RetentionPolicy.SOURCE)
    public @interface DeliveryType {
    }

    String getDeliveryType();

    int getPlatform();

    String getRegistrationToken(Context context) throws RegistrationException;

    boolean isAvailable(Context context);

    boolean isSupported(Context context);

    public static class RegistrationException extends Exception {
        private final boolean isRecoverable;

        public RegistrationException(String str, boolean z, Throwable th) {
            super(str, th);
            this.isRecoverable = z;
        }

        public RegistrationException(String str, boolean z) {
            super(str);
            this.isRecoverable = z;
        }

        public boolean isRecoverable() {
            return this.isRecoverable;
        }
    }
}
