package com.paypal.android.sdk.onetouch.core;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import com.paypal.android.sdk.onetouch.core.config.BillingAgreementRecipe;
import com.paypal.android.sdk.onetouch.core.config.OtcConfiguration;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;

public class BillingAgreementRequest extends CheckoutRequest {
    public static final Parcelable.Creator<BillingAgreementRequest> CREATOR = new Parcelable.Creator<BillingAgreementRequest>() {
        public BillingAgreementRequest[] newArray(int i) {
            return new BillingAgreementRequest[i];
        }

        public BillingAgreementRequest createFromParcel(Parcel parcel) {
            return new BillingAgreementRequest(parcel);
        }
    };
    private static final String TOKEN_QUERY_PARAM_KEY_BA_TOKEN = "ba_token";

    public BillingAgreementRequest() {
    }

    public BillingAgreementRequest pairingId(Context context, String str) {
        super.pairingId(context, str);
        return this;
    }

    public BillingAgreementRequest approvalURL(String str) {
        super.approvalURL(str);
        this.mTokenQueryParamKey = TOKEN_QUERY_PARAM_KEY_BA_TOKEN;
        return this;
    }

    public Recipe getRecipeToExecute(Context context, OtcConfiguration otcConfiguration) {
        for (BillingAgreementRecipe next : otcConfiguration.getBillingAgreementRecipes()) {
            if (RequestTarget.wallet == next.getTarget()) {
                if (next.isValidAppTarget(context)) {
                    return next;
                }
            } else if (RequestTarget.browser == next.getTarget() && next.isValidBrowserTarget(context, getBrowserSwitchUrl())) {
                return next;
            }
        }
        return null;
    }

    protected BillingAgreementRequest(Parcel parcel) {
        super(parcel);
    }
}
