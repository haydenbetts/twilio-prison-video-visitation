package com.braintreepayments.api.internal;

import android.net.Uri;
import com.braintreepayments.api.models.ThreeDSecureLookup;
import com.braintreepayments.api.models.ThreeDSecureRequest;
import com.braintreepayments.api.models.ThreeDSecureV1UiCustomization;

public class ThreeDSecureV1BrowserSwitchHelper {
    private static final String MOBILE_HOSTED_ASSETS_PATH = "mobile/three-d-secure-redirect/0.2.0";

    private ThreeDSecureV1BrowserSwitchHelper() {
    }

    public static String getUrl(String str, String str2, ThreeDSecureRequest threeDSecureRequest, ThreeDSecureLookup threeDSecureLookup) {
        Uri build = new Uri.Builder().scheme(str).authority("x-callback-url").appendEncodedPath("braintree/threedsecure?").build();
        Uri build2 = Uri.parse(str2).buildUpon().appendEncodedPath(MOBILE_HOSTED_ASSETS_PATH).appendEncodedPath("redirect.html").build();
        if (threeDSecureRequest != null) {
            ThreeDSecureV1UiCustomization v1UiCustomization = threeDSecureRequest.getV1UiCustomization();
            if (!(v1UiCustomization == null || v1UiCustomization.getRedirectButtonText() == null)) {
                build2 = build2.buildUpon().appendQueryParameter("b", v1UiCustomization.getRedirectButtonText()).build();
            }
            if (!(v1UiCustomization == null || v1UiCustomization.getRedirectDescription() == null)) {
                build2 = build2.buildUpon().appendQueryParameter("d", v1UiCustomization.getRedirectDescription()).build();
            }
        }
        Uri build3 = build2.buildUpon().appendQueryParameter("redirect_url", build.toString()).build();
        return Uri.parse(str2).buildUpon().appendEncodedPath(MOBILE_HOSTED_ASSETS_PATH).appendEncodedPath("index.html").appendQueryParameter("AcsUrl", threeDSecureLookup.getAcsUrl()).appendQueryParameter("PaReq", threeDSecureLookup.getPareq()).appendQueryParameter("MD", threeDSecureLookup.getMd()).appendQueryParameter("TermUrl", threeDSecureLookup.getTermUrl()).appendQueryParameter("ReturnUrl", build3.buildUpon().query(build3.getEncodedQuery()).build().toString()).build().toString();
    }
}
