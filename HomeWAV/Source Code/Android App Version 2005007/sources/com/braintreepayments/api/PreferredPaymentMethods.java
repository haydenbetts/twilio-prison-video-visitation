package com.braintreepayments.api;

public class PreferredPaymentMethods {
    private static final int NO_FLAGS = 0;
    private static final String PAYPAL_APP_PACKAGE = "com.paypal.android.p2pmobile";
    private static final String VENMO_APP_PACKAGE = "com.venmo";

    /* JADX WARNING: Removed duplicated region for block: B:14:0x0033  */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0049  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static void fetchPreferredPaymentMethods(final com.braintreepayments.api.BraintreeFragment r6, final com.braintreepayments.api.interfaces.PreferredPaymentMethodsListener r7) {
        /*
            r0 = 1
            r1 = 0
            android.content.Context r2 = r6.getApplicationContext()     // Catch:{ NameNotFoundException -> 0x001e }
            android.content.pm.PackageManager r2 = r2.getPackageManager()     // Catch:{ NameNotFoundException -> 0x001e }
            java.lang.String r3 = "com.paypal.android.p2pmobile"
            android.content.pm.ApplicationInfo r2 = r2.getApplicationInfo(r3, r1)     // Catch:{ NameNotFoundException -> 0x001e }
            if (r2 == 0) goto L_0x0014
            r2 = 1
            goto L_0x0015
        L_0x0014:
            r2 = 0
        L_0x0015:
            android.content.Context r3 = r6.getApplicationContext()     // Catch:{ NameNotFoundException -> 0x001f }
            boolean r3 = com.braintreepayments.api.Venmo.isVenmoInstalled(r3)     // Catch:{ NameNotFoundException -> 0x001f }
            goto L_0x0020
        L_0x001e:
            r2 = 0
        L_0x001f:
            r3 = 0
        L_0x0020:
            java.lang.Object[] r4 = new java.lang.Object[r0]
            java.lang.Boolean r5 = java.lang.Boolean.valueOf(r3)
            r4[r1] = r5
            java.lang.String r1 = "preferred-payment-methods.venmo.app-installed.%b"
            java.lang.String r1 = java.lang.String.format(r1, r4)
            r6.sendAnalyticsEvent(r1)
            if (r2 == 0) goto L_0x0049
            java.lang.String r1 = "preferred-payment-methods.paypal.app-installed.true"
            r6.sendAnalyticsEvent(r1)
            com.braintreepayments.api.models.PreferredPaymentMethodsResult r6 = new com.braintreepayments.api.models.PreferredPaymentMethodsResult
            r6.<init>()
            com.braintreepayments.api.models.PreferredPaymentMethodsResult r6 = r6.isPayPalPreferred(r0)
            com.braintreepayments.api.models.PreferredPaymentMethodsResult r6 = r6.isVenmoPreferred(r3)
            r7.onPreferredPaymentMethodsFetched(r6)
            return
        L_0x0049:
            com.braintreepayments.api.internal.BraintreeGraphQLHttpClient r0 = r6.getGraphQLHttpClient()
            if (r0 != 0) goto L_0x0065
            java.lang.String r0 = "preferred-payment-methods.api-disabled"
            r6.sendAnalyticsEvent(r0)
            com.braintreepayments.api.models.PreferredPaymentMethodsResult r6 = new com.braintreepayments.api.models.PreferredPaymentMethodsResult
            r6.<init>()
            com.braintreepayments.api.models.PreferredPaymentMethodsResult r6 = r6.isPayPalPreferred(r2)
            com.braintreepayments.api.models.PreferredPaymentMethodsResult r6 = r6.isVenmoPreferred(r3)
            r7.onPreferredPaymentMethodsFetched(r6)
            return
        L_0x0065:
            com.braintreepayments.api.PreferredPaymentMethods$1 r1 = new com.braintreepayments.api.PreferredPaymentMethods$1
            r1.<init>(r3, r6, r7)
            java.lang.String r6 = "{ \"query\": \"query PreferredPaymentMethods { preferredPaymentMethods { paypalPreferred } }\" }"
            r0.post(r6, r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.PreferredPaymentMethods.fetchPreferredPaymentMethods(com.braintreepayments.api.BraintreeFragment, com.braintreepayments.api.interfaces.PreferredPaymentMethodsListener):void");
    }
}
