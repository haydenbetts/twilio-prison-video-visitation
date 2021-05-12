package com.urbanairship;

import android.content.Context;
import com.urbanairship.push.PushProvider;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PushProviders {
    private static final String ADM_PUSH_PROVIDER_CLASS = "com.urbanairship.push.adm.AdmPushProvider";
    private static final String FCM_PUSH_PROVIDER_CLASS = "com.urbanairship.push.fcm.FcmPushProvider";
    private static final String HMS_PUSH_PROVIDER_CLASS = "com.urbanairship.push.hms.HmsPushProvider";
    private final AirshipConfigOptions airshipConfigOptions;
    private final List<PushProvider> availableProviders = new ArrayList();
    private final List<PushProvider> supportedProviders = new ArrayList();

    private PushProviders(AirshipConfigOptions airshipConfigOptions2) {
        this.airshipConfigOptions = airshipConfigOptions2;
    }

    static PushProviders load(Context context, AirshipConfigOptions airshipConfigOptions2) {
        PushProviders pushProviders = new PushProviders(airshipConfigOptions2);
        pushProviders.init(context);
        return pushProviders;
    }

    private void init(Context context) {
        List<PushProvider> createProviders = createProviders();
        if (createProviders.isEmpty()) {
            Logger.warn("No push providers found!. Make sure to install either `urbanairship-fcm` or `urbanairship-adm`.", new Object[0]);
            return;
        }
        for (PushProvider next : createProviders) {
            if (isValid(next) && next.isSupported(context)) {
                this.supportedProviders.add(next);
                if (next.isAvailable(context)) {
                    this.availableProviders.add(next);
                }
            }
        }
    }

    private boolean isValid(PushProvider pushProvider) {
        if (pushProvider instanceof AirshipVersionInfo) {
            AirshipVersionInfo airshipVersionInfo = (AirshipVersionInfo) pushProvider;
            if (!UAirship.getVersion().equals(airshipVersionInfo.getAirshipVersion())) {
                Logger.error("Provider: %s version %s does not match the SDK version %s. Make sure all Airship dependencies are the same version.", pushProvider, airshipVersionInfo.getAirshipVersion(), UAirship.getVersion());
                return false;
            }
        }
        String deliveryType = pushProvider.getDeliveryType();
        deliveryType.hashCode();
        char c = 65535;
        switch (deliveryType.hashCode()) {
            case 96426:
                if (deliveryType.equals(PushProvider.ADM_DELIVERY_TYPE)) {
                    c = 0;
                    break;
                }
                break;
            case 101200:
                if (deliveryType.equals("fcm")) {
                    c = 1;
                    break;
                }
                break;
            case 103438:
                if (deliveryType.equals(PushProvider.HMS_DELIVERY_TYPE)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                if (pushProvider.getPlatform() != 1) {
                    Logger.error("Invalid Provider: %s. ADM delivery is only available for Amazon platforms.", pushProvider);
                    return false;
                }
                break;
            case 1:
            case 2:
                if (pushProvider.getPlatform() != 2) {
                    Logger.error("Invalid Provider: %s. %s delivery is only available for Android platforms.", pushProvider.getDeliveryType(), pushProvider);
                    return false;
                }
                break;
        }
        return true;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0045, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0046, code lost:
        r9 = r7;
        r7 = null;
        r4 = r9;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0051, code lost:
        r7 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0052, code lost:
        r9 = r7;
        r7 = null;
        r4 = r9;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Removed duplicated region for block: B:14:? A[ExcHandler: ClassNotFoundException (unused java.lang.ClassNotFoundException), SYNTHETIC, Splitter:B:7:0x002b] */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x005f  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x001c A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.urbanairship.push.PushProvider> createProviders() {
        /*
            r10 = this;
            java.lang.String r0 = "Unable to create provider %s"
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            com.urbanairship.AirshipConfigOptions r2 = r10.airshipConfigOptions
            com.urbanairship.push.PushProvider r2 = r2.customPushProvider
            if (r2 == 0) goto L_0x0014
            com.urbanairship.AirshipConfigOptions r2 = r10.airshipConfigOptions
            com.urbanairship.push.PushProvider r2 = r2.customPushProvider
            r1.add(r2)
        L_0x0014:
            java.util.List r2 = r10.createAllowedProviderClassList()
            java.util.Iterator r2 = r2.iterator()
        L_0x001c:
            boolean r3 = r2.hasNext()
            if (r3 == 0) goto L_0x0063
            java.lang.Object r3 = r2.next()
            java.lang.String r3 = (java.lang.String) r3
            r4 = 0
            r5 = 0
            r6 = 1
            java.lang.Class r7 = java.lang.Class.forName(r3)     // Catch:{ InstantiationException -> 0x0051, IllegalAccessException -> 0x0045, ClassNotFoundException -> 0x0043 }
            java.lang.Object r7 = r7.newInstance()     // Catch:{ InstantiationException -> 0x0051, IllegalAccessException -> 0x0045, ClassNotFoundException -> 0x0043 }
            com.urbanairship.push.PushProvider r7 = (com.urbanairship.push.PushProvider) r7     // Catch:{ InstantiationException -> 0x0051, IllegalAccessException -> 0x0045, ClassNotFoundException -> 0x0043 }
            java.lang.String r4 = "Found provider: %s"
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ InstantiationException -> 0x0041, IllegalAccessException -> 0x003f, ClassNotFoundException -> 0x0043 }
            r8[r5] = r7     // Catch:{ InstantiationException -> 0x0041, IllegalAccessException -> 0x003f, ClassNotFoundException -> 0x0043 }
            com.urbanairship.Logger.verbose(r4, r8)     // Catch:{ InstantiationException -> 0x0041, IllegalAccessException -> 0x003f, ClassNotFoundException -> 0x0043 }
            goto L_0x005c
        L_0x003f:
            r4 = move-exception
            goto L_0x0049
        L_0x0041:
            r4 = move-exception
            goto L_0x0055
        L_0x0043:
            goto L_0x001c
        L_0x0045:
            r7 = move-exception
            r9 = r7
            r7 = r4
            r4 = r9
        L_0x0049:
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r3
            com.urbanairship.Logger.error(r4, r0, r6)
            goto L_0x005c
        L_0x0051:
            r7 = move-exception
            r9 = r7
            r7 = r4
            r4 = r9
        L_0x0055:
            java.lang.Object[] r6 = new java.lang.Object[r6]
            r6[r5] = r3
            com.urbanairship.Logger.error(r4, r0, r6)
        L_0x005c:
            if (r7 != 0) goto L_0x005f
            goto L_0x001c
        L_0x005f:
            r1.add(r7)
            goto L_0x001c
        L_0x0063:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.urbanairship.PushProviders.createProviders():java.util.List");
    }

    public List<PushProvider> getAvailableProviders() {
        return Collections.unmodifiableList(this.availableProviders);
    }

    /* access modifiers changed from: package-private */
    public PushProvider getBestProvider(int i) {
        for (PushProvider next : this.availableProviders) {
            if (next.getPlatform() == i) {
                return next;
            }
        }
        for (PushProvider next2 : this.supportedProviders) {
            if (next2.getPlatform() == i) {
                return next2;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public PushProvider getBestProvider() {
        if (!this.availableProviders.isEmpty()) {
            return this.availableProviders.get(0);
        }
        if (!this.supportedProviders.isEmpty()) {
            return this.supportedProviders.get(0);
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public PushProvider getProvider(int i, String str) {
        for (PushProvider next : this.supportedProviders) {
            if (i == next.getPlatform() && str.equals(next.getClass().toString())) {
                return next;
            }
        }
        return null;
    }

    /* access modifiers changed from: package-private */
    public List<String> createAllowedProviderClassList() {
        ArrayList arrayList = new ArrayList();
        if (this.airshipConfigOptions.allowedTransports.contains("FCM")) {
            arrayList.add(FCM_PUSH_PROVIDER_CLASS);
        }
        if (this.airshipConfigOptions.allowedTransports.contains(AirshipConfigOptions.ADM_TRANSPORT)) {
            arrayList.add(ADM_PUSH_PROVIDER_CLASS);
        }
        if (this.airshipConfigOptions.allowedTransports.contains(AirshipConfigOptions.HMS_TRANSPORT)) {
            arrayList.add(HMS_PUSH_PROVIDER_CLASS);
        }
        return arrayList;
    }
}
