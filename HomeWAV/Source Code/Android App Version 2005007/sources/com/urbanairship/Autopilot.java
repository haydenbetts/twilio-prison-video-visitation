package com.urbanairship;

import android.app.Application;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import com.urbanairship.UAirship;

public class Autopilot implements UAirship.OnReadyCallback {
    public static final String AUTOPILOT_MANIFEST_KEY = "com.urbanairship.autopilot";
    private static final String TAG = "Airship Autopilot";
    private static Autopilot instance;
    private static boolean instanceCreationAttempted;

    public boolean allowEarlyTakeOff(Context context) {
        return true;
    }

    public AirshipConfigOptions createAirshipConfigOptions(Context context) {
        return null;
    }

    public boolean isReady(Context context) {
        return true;
    }

    public static void automaticTakeOff(Context context) {
        automaticTakeOff((Application) context.getApplicationContext(), false);
    }

    public static synchronized void automaticTakeOff(Application application) {
        synchronized (Autopilot.class) {
            automaticTakeOff(application, false);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:54:0x0085, code lost:
        return;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    static synchronized void automaticTakeOff(android.app.Application r4, boolean r5) {
        /*
            java.lang.Class<com.urbanairship.Autopilot> r0 = com.urbanairship.Autopilot.class
            monitor-enter(r0)
            boolean r1 = com.urbanairship.UAirship.isFlying()     // Catch:{ all -> 0x0086 }
            if (r1 != 0) goto L_0x0084
            boolean r1 = com.urbanairship.UAirship.isTakingOff()     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x0011
            goto L_0x0084
        L_0x0011:
            boolean r1 = instanceCreationAttempted     // Catch:{ all -> 0x0086 }
            if (r1 != 0) goto L_0x0047
            android.content.pm.PackageManager r1 = r4.getPackageManager()     // Catch:{ NameNotFoundException -> 0x003d }
            java.lang.String r2 = r4.getPackageName()     // Catch:{ NameNotFoundException -> 0x003d }
            r3 = 128(0x80, float:1.794E-43)
            android.content.pm.ApplicationInfo r1 = r1.getApplicationInfo(r2, r3)     // Catch:{ NameNotFoundException -> 0x003d }
            if (r1 == 0) goto L_0x0034
            android.os.Bundle r2 = r1.metaData     // Catch:{ NameNotFoundException -> 0x003d }
            if (r2 != 0) goto L_0x002a
            goto L_0x0034
        L_0x002a:
            com.urbanairship.Autopilot r1 = createAutopilotInstance(r1)     // Catch:{ all -> 0x0086 }
            instance = r1     // Catch:{ all -> 0x0086 }
            r1 = 1
            instanceCreationAttempted = r1     // Catch:{ all -> 0x0086 }
            goto L_0x0047
        L_0x0034:
            java.lang.String r4 = "Airship Autopilot"
            java.lang.String r5 = "Unable to load app info."
            android.util.Log.e(r4, r5)     // Catch:{ NameNotFoundException -> 0x003d }
            monitor-exit(r0)
            return
        L_0x003d:
            r4 = move-exception
            java.lang.String r5 = "Airship Autopilot"
            java.lang.String r1 = "Failed to get app info."
            android.util.Log.e(r5, r1, r4)     // Catch:{ all -> 0x0086 }
            monitor-exit(r0)
            return
        L_0x0047:
            com.urbanairship.Autopilot r1 = instance     // Catch:{ all -> 0x0086 }
            if (r1 != 0) goto L_0x004d
            monitor-exit(r0)
            return
        L_0x004d:
            if (r5 == 0) goto L_0x0057
            boolean r5 = r1.allowEarlyTakeOff(r4)     // Catch:{ all -> 0x0086 }
            if (r5 != 0) goto L_0x0057
            monitor-exit(r0)
            return
        L_0x0057:
            com.urbanairship.Autopilot r5 = instance     // Catch:{ all -> 0x0086 }
            boolean r5 = r5.isReady(r4)     // Catch:{ all -> 0x0086 }
            if (r5 != 0) goto L_0x0061
            monitor-exit(r0)
            return
        L_0x0061:
            com.urbanairship.Autopilot r5 = instance     // Catch:{ all -> 0x0086 }
            com.urbanairship.AirshipConfigOptions r5 = r5.createAirshipConfigOptions(r4)     // Catch:{ all -> 0x0086 }
            boolean r1 = com.urbanairship.UAirship.isFlying()     // Catch:{ all -> 0x0086 }
            if (r1 != 0) goto L_0x0073
            boolean r1 = com.urbanairship.UAirship.isTakingOff()     // Catch:{ all -> 0x0086 }
            if (r1 == 0) goto L_0x007a
        L_0x0073:
            java.lang.String r1 = "Airship Autopilot"
            java.lang.String r2 = "Airship is flying before autopilot is able to take off. Make sureAutopilot.onCreateAirshipConfig is not calling takeOff directly."
            android.util.Log.e(r1, r2)     // Catch:{ all -> 0x0086 }
        L_0x007a:
            com.urbanairship.Autopilot r1 = instance     // Catch:{ all -> 0x0086 }
            com.urbanairship.UAirship.takeOff(r4, r5, r1)     // Catch:{ all -> 0x0086 }
            r4 = 0
            instance = r4     // Catch:{ all -> 0x0086 }
            monitor-exit(r0)
            return
        L_0x0084:
            monitor-exit(r0)
            return
        L_0x0086:
            r4 = move-exception
            monitor-exit(r0)
            throw r4
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.Autopilot.automaticTakeOff(android.app.Application, boolean):void");
    }

    private static Autopilot createAutopilotInstance(ApplicationInfo applicationInfo) {
        String string = applicationInfo.metaData.getString(AUTOPILOT_MANIFEST_KEY);
        if (string == null) {
            return null;
        }
        try {
            return (Autopilot) Class.forName(string).newInstance();
        } catch (ClassNotFoundException unused) {
            Log.e(TAG, "Class not found: " + string);
            return null;
        } catch (InstantiationException unused2) {
            Log.e(TAG, "Unable to create class: " + string);
            return null;
        } catch (IllegalAccessException unused3) {
            Log.e(TAG, "Unable to access class: " + string);
            return null;
        }
    }

    public void onAirshipReady(UAirship uAirship) {
        Logger.debug("Autopilot - Airship ready!", new Object[0]);
    }
}
