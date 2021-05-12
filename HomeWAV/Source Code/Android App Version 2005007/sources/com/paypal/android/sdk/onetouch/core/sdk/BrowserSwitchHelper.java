package com.paypal.android.sdk.onetouch.core.sdk;

import android.content.Intent;
import android.net.Uri;
import com.paypal.android.sdk.onetouch.core.Request;
import com.paypal.android.sdk.onetouch.core.Result;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.config.ConfigManager;
import com.paypal.android.sdk.onetouch.core.config.OtcConfiguration;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.Protocol;
import com.paypal.android.sdk.onetouch.core.fpti.TrackingPoint;

public class BrowserSwitchHelper {
    public static Intent getBrowserSwitchIntent(ContextInspector contextInspector, ConfigManager configManager, Request request) {
        OtcConfiguration config = configManager.getConfig();
        String browserSwitchUrl = request.getBrowserSwitchUrl();
        Recipe browserSwitchRecipe = request.getBrowserSwitchRecipe(config);
        for (String next : browserSwitchRecipe.getTargetPackagesInReversePriorityOrder()) {
            if (Recipe.isValidBrowserTarget(contextInspector.getContext(), browserSwitchUrl, next)) {
                request.trackFpti(contextInspector.getContext(), TrackingPoint.SwitchToBrowser, browserSwitchRecipe.getProtocol());
                return Recipe.getBrowserIntent(contextInspector.getContext(), browserSwitchUrl, next);
            }
        }
        return null;
    }

    /* renamed from: com.paypal.android.sdk.onetouch.core.sdk.BrowserSwitchHelper$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType;

        /* JADX WARNING: Can't wrap try/catch for region: R(6:0|1|2|3|4|(3:5|6|8)) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        static {
            /*
                com.paypal.android.sdk.onetouch.core.enums.ResultType[] r0 = com.paypal.android.sdk.onetouch.core.enums.ResultType.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType = r0
                com.paypal.android.sdk.onetouch.core.enums.ResultType r1 = com.paypal.android.sdk.onetouch.core.enums.ResultType.Error     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType     // Catch:{ NoSuchFieldError -> 0x001d }
                com.paypal.android.sdk.onetouch.core.enums.ResultType r1 = com.paypal.android.sdk.onetouch.core.enums.ResultType.Cancel     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.paypal.android.sdk.onetouch.core.enums.ResultType r1 = com.paypal.android.sdk.onetouch.core.enums.ResultType.Success     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.paypal.android.sdk.onetouch.core.sdk.BrowserSwitchHelper.AnonymousClass1.<clinit>():void");
        }
    }

    public static Result parseBrowserSwitchResponse(ContextInspector contextInspector, Request request, Uri uri) {
        Result parseBrowserResponse = request.parseBrowserResponse(uri);
        int i = AnonymousClass1.$SwitchMap$com$paypal$android$sdk$onetouch$core$enums$ResultType[parseBrowserResponse.getResultType().ordinal()];
        if (i == 1) {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Error, (Protocol) null);
        } else if (i == 2) {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Cancel, (Protocol) null);
        } else if (i == 3) {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Return, (Protocol) null);
        }
        return parseBrowserResponse;
    }
}
