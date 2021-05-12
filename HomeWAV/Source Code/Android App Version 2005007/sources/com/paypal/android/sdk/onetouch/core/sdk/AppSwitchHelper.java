package com.paypal.android.sdk.onetouch.core.sdk;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import com.braintreepayments.api.internal.SignatureVerification;
import com.paypal.android.sdk.data.collector.InstallationIdentifier;
import com.paypal.android.sdk.onetouch.core.CheckoutRequest;
import com.paypal.android.sdk.onetouch.core.Request;
import com.paypal.android.sdk.onetouch.core.Result;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.base.DeviceInspector;
import com.paypal.android.sdk.onetouch.core.config.ConfigManager;
import com.paypal.android.sdk.onetouch.core.config.Recipe;
import com.paypal.android.sdk.onetouch.core.enums.Protocol;
import com.paypal.android.sdk.onetouch.core.enums.ResponseType;
import com.paypal.android.sdk.onetouch.core.exception.ResponseParsingException;
import com.paypal.android.sdk.onetouch.core.exception.WalletSwitchException;
import com.paypal.android.sdk.onetouch.core.fpti.TrackingPoint;
import com.paypal.android.sdk.onetouch.core.network.EnvironmentManager;
import com.urbanairship.json.matchers.VersionMatcher;
import java.util.Locale;
import org.json.JSONException;
import org.json.JSONObject;

public class AppSwitchHelper {
    private static final String WALLET_APP_CERT_ISSUER = "O=Paypal";
    private static final String WALLET_APP_CERT_SUBJECT = "O=Paypal";
    private static final String WALLET_APP_PACKAGE = "com.paypal.android.p2pmobile";
    private static final int WALLET_APP_PUBLIC_KEY_HASH_CODE = 34172764;

    public static boolean isSignatureValid(Context context, String str) {
        return SignatureVerification.isSignatureValid(context, str, "O=Paypal", "O=Paypal", WALLET_APP_PUBLIC_KEY_HASH_CODE);
    }

    public static Intent createBaseIntent(String str, String str2) {
        return new Intent(str).setPackage(str2);
    }

    public static Intent getAppSwitchIntent(ContextInspector contextInspector, ConfigManager configManager, Request request, Recipe recipe) {
        Intent putExtra = createBaseIntent(recipe.getTargetIntentAction(), WALLET_APP_PACKAGE).putExtra(VersionMatcher.ALTERNATE_VERSION_KEY, recipe.getProtocol().getVersion()).putExtra("app_guid", InstallationIdentifier.getInstallationGUID(contextInspector.getContext())).putExtra("client_metadata_id", request.getClientMetadataId()).putExtra("client_id", request.getClientId()).putExtra("app_name", DeviceInspector.getApplicationInfoName(contextInspector.getContext())).putExtra("environment", request.getEnvironment()).putExtra("environment_url", EnvironmentManager.getEnvironmentUrl(request.getEnvironment()));
        putExtra.putExtra("response_type", "web").putExtra("webURL", ((CheckoutRequest) request).getBrowserSwitchUrl());
        return putExtra;
    }

    public static Result parseAppSwitchResponse(ContextInspector contextInspector, Request request, Intent intent) {
        Bundle extras = intent.getExtras();
        if (request.validateV1V2Response(extras)) {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Return, (Protocol) null);
            return processResponseIntent(extras);
        } else if (extras.containsKey("error")) {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Error, (Protocol) null);
            return new Result((Throwable) new WalletSwitchException(extras.getString("error")));
        } else {
            request.trackFpti(contextInspector.getContext(), TrackingPoint.Error, (Protocol) null);
            return new Result((Throwable) new ResponseParsingException("invalid wallet response"));
        }
    }

    private static Result processResponseIntent(Bundle bundle) {
        ResponseType responseType;
        String string = bundle.getString("error");
        if (!TextUtils.isEmpty(string)) {
            return new Result((Throwable) new WalletSwitchException(string));
        }
        String string2 = bundle.getString("environment");
        if ("code".equals(bundle.getString("response_type").toLowerCase(Locale.US))) {
            responseType = ResponseType.authorization_code;
        } else {
            responseType = ResponseType.web;
        }
        try {
            if (ResponseType.web == responseType) {
                return new Result(string2, responseType, new JSONObject().put("webURL", bundle.getString("webURL")), (String) null);
            }
            String string3 = bundle.getString("authorization_code");
            return new Result(string2, responseType, new JSONObject().put("code", string3), bundle.getString("email"));
        } catch (JSONException e) {
            return new Result((Throwable) new ResponseParsingException((Throwable) e));
        }
    }
}
