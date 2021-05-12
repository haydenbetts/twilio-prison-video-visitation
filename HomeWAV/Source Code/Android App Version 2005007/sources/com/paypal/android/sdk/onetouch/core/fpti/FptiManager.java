package com.paypal.android.sdk.onetouch.core.fpti;

import android.os.Handler;
import android.os.Looper;
import com.braintreepayments.api.interfaces.HttpResponseCallback;
import com.microsoft.appcenter.Constants;
import com.paypal.android.sdk.data.collector.InstallationIdentifier;
import com.paypal.android.sdk.onetouch.core.base.ContextInspector;
import com.paypal.android.sdk.onetouch.core.base.DeviceInspector;
import com.paypal.android.sdk.onetouch.core.base.URLEncoderHelper;
import com.paypal.android.sdk.onetouch.core.enums.Protocol;
import com.paypal.android.sdk.onetouch.core.network.EnvironmentManager;
import com.paypal.android.sdk.onetouch.core.network.PayPalHttpClient;
import com.urbanairship.analytics.data.EventsStorage;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Random;
import org.json.JSONException;
import org.json.JSONObject;

public class FptiManager {
    private final ContextInspector mContextInspector;
    /* access modifiers changed from: private */
    public final PayPalHttpClient mHttpClient;
    private FptiToken mToken;

    public FptiManager(ContextInspector contextInspector, PayPalHttpClient payPalHttpClient) {
        this.mContextInspector = contextInspector;
        this.mHttpClient = payPalHttpClient;
    }

    public void trackFpti(TrackingPoint trackingPoint, String str, Map<String, String> map, Protocol protocol) {
        if (!EnvironmentManager.isMock(str)) {
            FptiToken fptiToken = this.mToken;
            if (fptiToken == null || !fptiToken.isValid()) {
                this.mToken = new FptiToken();
            }
            long currentTimeMillis = System.currentTimeMillis();
            String encode = URLEncoderHelper.encode(InstallationIdentifier.getInstallationGUID(this.mContextInspector.getContext()));
            StringBuilder sb = new StringBuilder();
            sb.append("mobile:otc:");
            sb.append(trackingPoint.getCd());
            sb.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            String str2 = "";
            sb.append(protocol != null ? protocol.name() : str2);
            String sb2 = sb.toString();
            StringBuilder sb3 = new StringBuilder();
            sb3.append(sb2);
            sb3.append(Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            sb3.append("Android:" + str + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            if (trackingPoint.hasError()) {
                str2 = "|error";
            }
            sb3.append(str2);
            String sb4 = sb3.toString();
            HashMap hashMap = new HashMap(map);
            hashMap.put("apid", DeviceInspector.getApplicationInfoName(this.mContextInspector.getContext()) + "|" + "3.14.2" + "|" + this.mContextInspector.getContext().getPackageName());
            hashMap.put("bchn", "otc");
            hashMap.put("bzsr", "mobile");
            hashMap.put("dsid", encode);
            hashMap.put("e", "im");
            hashMap.put("g", getGmtOffsetInMinutes());
            hashMap.put("lgin", "out");
            hashMap.put("mapv", "3.14.2");
            hashMap.put("mcar", DeviceInspector.getSimOperatorName(this.mContextInspector.getContext()));
            hashMap.put("mdvs", DeviceInspector.getDeviceName());
            hashMap.put("mosv", DeviceInspector.getOs());
            hashMap.put("page", sb4);
            hashMap.put("pgrp", sb2);
            hashMap.put("rsta", Locale.getDefault().toString());
            hashMap.put("srce", "otc");
            hashMap.put("sv", "mobile");
            hashMap.put("t", Long.toString(currentTimeMillis - ((long) getGMTOffset())));
            hashMap.put("vers", "Android:" + str + Constants.COMMON_SCHEMA_PREFIX_SEPARATOR);
            hashMap.put("vid", this.mToken.mToken);
            try {
                JSONObject jSONObject = new JSONObject();
                jSONObject.accumulate("tracking_visitor_id", encode);
                jSONObject.accumulate("tracking_visit_id", this.mToken.mToken);
                JSONObject jSONObject2 = new JSONObject();
                jSONObject2.accumulate("actor", jSONObject);
                jSONObject2.accumulate(Modules.CHANNEL_MODULE, "mobile");
                jSONObject2.accumulate("tracking_event", Long.toString(currentTimeMillis));
                jSONObject2.accumulate("event_params", getEventParams(hashMap));
                sendRequest(new JSONObject().accumulate(EventsStorage.Events.TABLE_NAME, jSONObject2).toString());
            } catch (JSONException unused) {
            }
        }
    }

    private JSONObject getEventParams(Map<String, String> map) throws JSONException {
        JSONObject jSONObject = new JSONObject();
        for (String next : map.keySet()) {
            jSONObject.accumulate(next, map.get(next));
        }
        return jSONObject;
    }

    /* access modifiers changed from: package-private */
    public void sendRequest(final String str) {
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() {
            public void run() {
                FptiManager.this.mHttpClient.post("tracking/events", str, (HttpResponseCallback) null);
            }
        }, (long) ((new Random().nextInt(190) + 10) * 1000));
    }

    private int getGMTOffset() {
        return new GregorianCalendar().getTimeZone().getRawOffset();
    }

    private String getGmtOffsetInMinutes() {
        return Integer.toString((getGMTOffset() / 1000) / 60);
    }
}
