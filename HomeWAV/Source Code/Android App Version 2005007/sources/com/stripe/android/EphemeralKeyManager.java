package com.stripe.android;

import com.stripe.android.AbstractEphemeralKey;
import java.lang.ref.WeakReference;
import java.util.Calendar;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;

class EphemeralKeyManager<TEphemeralKey extends AbstractEphemeralKey> {
    private TEphemeralKey mEphemeralKey;
    private final Class<TEphemeralKey> mEphemeralKeyClass;
    private EphemeralKeyProvider mEphemeralKeyProvider;
    private KeyManagerListener mListener;
    private Calendar mOverrideCalendar;
    private final long mTimeBufferInSeconds;

    interface KeyManagerListener<TEphemeralKey extends AbstractEphemeralKey> {
        void onKeyError(String str, int i, String str2);

        void onKeyUpdate(TEphemeralKey tephemeralkey, String str, String str2, Map<String, Object> map);
    }

    EphemeralKeyManager(EphemeralKeyProvider ephemeralKeyProvider, KeyManagerListener keyManagerListener, long j, Calendar calendar, Class<TEphemeralKey> cls) {
        this.mEphemeralKeyClass = cls;
        this.mEphemeralKeyProvider = ephemeralKeyProvider;
        this.mListener = keyManagerListener;
        this.mTimeBufferInSeconds = j;
        this.mOverrideCalendar = calendar;
        retrieveEphemeralKey((String) null, (String) null, (Map<String, Object>) null);
    }

    /* access modifiers changed from: package-private */
    public void retrieveEphemeralKey(String str, String str2, Map<String, Object> map) {
        if (shouldRefreshKey(this.mEphemeralKey, this.mTimeBufferInSeconds, this.mOverrideCalendar)) {
            this.mEphemeralKeyProvider.createEphemeralKey("2017-06-05", new ClientKeyUpdateListener(this, str, str2, map));
        } else {
            this.mListener.onKeyUpdate(this.mEphemeralKey, str, str2, map);
        }
    }

    /* access modifiers changed from: package-private */
    public TEphemeralKey getEphemeralKey() {
        return this.mEphemeralKey;
    }

    /* access modifiers changed from: private */
    public void updateKey(String str, String str2, String str3, Map<String, Object> map) {
        if (str2 == null) {
            this.mListener.onKeyError(str, 500, "EphemeralKeyUpdateListener.onKeyUpdate was called with a null value");
            return;
        }
        try {
            TEphemeralKey fromString = AbstractEphemeralKey.fromString(str2, this.mEphemeralKeyClass);
            this.mEphemeralKey = fromString;
            this.mListener.onKeyUpdate(fromString, str, str3, map);
        } catch (JSONException e) {
            KeyManagerListener keyManagerListener = this.mListener;
            keyManagerListener.onKeyError(str, 500, "EphemeralKeyUpdateListener.onKeyUpdate was passed a value that could not be JSON parsed: [" + e.getLocalizedMessage() + "]. The raw body from Stripe's response should be passed");
        } catch (Exception e2) {
            KeyManagerListener keyManagerListener2 = this.mListener;
            keyManagerListener2.onKeyError(str, 500, "EphemeralKeyUpdateListener.onKeyUpdate was passed a JSON String that was invalid: [" + e2.getLocalizedMessage() + "]. The raw body from Stripe's response should be passed");
        }
    }

    /* access modifiers changed from: private */
    public void updateKeyError(String str, int i, String str2) {
        this.mEphemeralKey = null;
        this.mListener.onKeyError(str, i, str2);
    }

    static boolean shouldRefreshKey(AbstractEphemeralKey abstractEphemeralKey, long j, Calendar calendar) {
        if (abstractEphemeralKey == null) {
            return true;
        }
        if (calendar == null) {
            calendar = Calendar.getInstance();
        }
        if (abstractEphemeralKey.getExpires() < TimeUnit.MILLISECONDS.toSeconds(calendar.getTimeInMillis()) + j) {
            return true;
        }
        return false;
    }

    private static class ClientKeyUpdateListener implements EphemeralKeyUpdateListener {
        private final String mActionString;
        private final Map<String, Object> mArguments;
        private final WeakReference<EphemeralKeyManager> mEphemeralKeyManagerRef;
        private final String mOperationId;

        ClientKeyUpdateListener(EphemeralKeyManager ephemeralKeyManager, String str, String str2, Map<String, Object> map) {
            this.mEphemeralKeyManagerRef = new WeakReference<>(ephemeralKeyManager);
            this.mOperationId = str;
            this.mActionString = str2;
            this.mArguments = map;
        }

        public void onKeyUpdate(String str) {
            EphemeralKeyManager ephemeralKeyManager = (EphemeralKeyManager) this.mEphemeralKeyManagerRef.get();
            if (ephemeralKeyManager != null) {
                ephemeralKeyManager.updateKey(this.mOperationId, str, this.mActionString, this.mArguments);
            }
        }

        public void onKeyUpdateFailure(int i, String str) {
            EphemeralKeyManager ephemeralKeyManager = (EphemeralKeyManager) this.mEphemeralKeyManagerRef.get();
            if (ephemeralKeyManager != null) {
                ephemeralKeyManager.updateKeyError(this.mOperationId, i, str);
            }
        }
    }
}
