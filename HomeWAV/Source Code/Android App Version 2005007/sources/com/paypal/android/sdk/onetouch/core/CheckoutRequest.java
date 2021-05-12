package com.paypal.android.sdk.onetouch.core;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import com.paypal.android.sdk.onetouch.core.config.CheckoutRecipe;
import com.paypal.android.sdk.onetouch.core.config.OtcConfiguration;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.Protocol;
import com.paypal.android.sdk.onetouch.core.enums.RequestTarget;
import com.paypal.android.sdk.onetouch.core.enums.ResponseType;
import com.paypal.android.sdk.onetouch.core.exception.BrowserSwitchException;
import com.paypal.android.sdk.onetouch.core.exception.ResponseParsingException;
import com.paypal.android.sdk.onetouch.core.fpti.TrackingPoint;
import java.util.HashMap;
import org.json.JSONException;
import org.json.JSONObject;

public class CheckoutRequest extends Request<CheckoutRequest> implements Parcelable {
    public static final Parcelable.Creator<CheckoutRequest> CREATOR = new Parcelable.Creator<CheckoutRequest>() {
        public CheckoutRequest[] newArray(int i) {
            return new CheckoutRequest[i];
        }

        public CheckoutRequest createFromParcel(Parcel parcel) {
            return new CheckoutRequest(parcel);
        }
    };
    private static final String TOKEN_QUERY_PARAM_KEY_TOKEN = "token";
    protected String mApprovalUrl;
    private String mPairingId;
    protected String mTokenQueryParamKey;

    public CheckoutRequest() {
        this.mTokenQueryParamKey = TOKEN_QUERY_PARAM_KEY_TOKEN;
    }

    public String getPairingId() {
        return this.mPairingId;
    }

    public CheckoutRequest pairingId(Context context, String str) {
        this.mPairingId = str;
        clientMetadataId(PayPalOneTouchCore.getClientMetadataId(context, str));
        return this;
    }

    public CheckoutRequest approvalURL(String str) {
        this.mApprovalUrl = str;
        this.mTokenQueryParamKey = TOKEN_QUERY_PARAM_KEY_TOKEN;
        return this;
    }

    public String getBrowserSwitchUrl() {
        return this.mApprovalUrl;
    }

    public Recipe getBrowserSwitchRecipe(OtcConfiguration otcConfiguration) {
        return otcConfiguration.getBrowserCheckoutConfig();
    }

    public Result parseBrowserResponse(Uri uri) {
        if (!Uri.parse(getSuccessUrl()).getLastPathSegment().equals(uri.getLastPathSegment())) {
            return new Result();
        }
        String queryParameter = Uri.parse(this.mApprovalUrl).getQueryParameter(this.mTokenQueryParamKey);
        String queryParameter2 = uri.getQueryParameter(this.mTokenQueryParamKey);
        if (queryParameter2 == null || !TextUtils.equals(queryParameter, queryParameter2)) {
            return new Result((Throwable) new BrowserSwitchException("The response contained inconsistent data."));
        }
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("webURL", uri.toString());
            return new Result((String) null, ResponseType.web, jSONObject, (String) null);
        } catch (JSONException e) {
            return new Result((Throwable) new ResponseParsingException((Throwable) e));
        }
    }

    public boolean validateV1V2Response(Bundle bundle) {
        String queryParameter;
        String queryParameter2 = Uri.parse(this.mApprovalUrl).getQueryParameter(this.mTokenQueryParamKey);
        String string = bundle.getString("webURL");
        if (string == null || (queryParameter = Uri.parse(string).getQueryParameter(this.mTokenQueryParamKey)) == null || !TextUtils.equals(queryParameter2, queryParameter)) {
            return false;
        }
        return true;
    }

    public Recipe getRecipeToExecute(Context context, OtcConfiguration otcConfiguration) {
        for (CheckoutRecipe next : otcConfiguration.getCheckoutRecipes()) {
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

    public void trackFpti(Context context, TrackingPoint trackingPoint, Protocol protocol) {
        String queryParameter = Uri.parse(this.mApprovalUrl).getQueryParameter(this.mTokenQueryParamKey);
        HashMap hashMap = new HashMap();
        hashMap.put("fltk", queryParameter);
        hashMap.put("clid", getClientId());
        PayPalOneTouchCore.getFptiManager(context).trackFpti(trackingPoint, getEnvironment(), hashMap, protocol);
    }

    public void writeToParcel(Parcel parcel, int i) {
        super.writeToParcel(parcel, i);
        parcel.writeString(this.mApprovalUrl);
        parcel.writeString(this.mTokenQueryParamKey);
        parcel.writeString(this.mPairingId);
    }

    protected CheckoutRequest(Parcel parcel) {
        super(parcel);
        this.mApprovalUrl = parcel.readString();
        this.mTokenQueryParamKey = parcel.readString();
        this.mPairingId = parcel.readString();
    }
}
