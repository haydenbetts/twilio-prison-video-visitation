package com.stripe.android.model;

import com.stripe.android.StripeTextUtils;
import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SourceReceiver extends StripeJsonModel {
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_AMOUNT_CHARGED = "amount_charged";
    private static final String FIELD_AMOUNT_RECEIVED = "amount_received";
    private static final String FIELD_AMOUNT_RETURNED = "amount_returned";
    private String mAddress;
    private long mAmountCharged;
    private long mAmountReceived;
    private long mAmountReturned;

    private SourceReceiver(String str, long j, long j2, long j3) {
        this.mAddress = str;
        this.mAmountCharged = j;
        this.mAmountReceived = j2;
        this.mAmountReturned = j3;
    }

    public String getAddress() {
        return this.mAddress;
    }

    public void setAddress(String str) {
        this.mAddress = str;
    }

    public long getAmountCharged() {
        return this.mAmountCharged;
    }

    public void setAmountCharged(long j) {
        this.mAmountCharged = j;
    }

    public long getAmountReceived() {
        return this.mAmountReceived;
    }

    public void setAmountReceived(long j) {
        this.mAmountReceived = j;
    }

    public long getAmountReturned() {
        return this.mAmountReturned;
    }

    public void setAmountReturned(long j) {
        this.mAmountReturned = j;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        if (!StripeTextUtils.isBlank(this.mAddress)) {
            hashMap.put(FIELD_ADDRESS, this.mAddress);
        }
        hashMap.put(FIELD_ADDRESS, this.mAddress);
        hashMap.put(FIELD_AMOUNT_CHARGED, Long.valueOf(this.mAmountCharged));
        hashMap.put(FIELD_AMOUNT_RECEIVED, Long.valueOf(this.mAmountReceived));
        hashMap.put(FIELD_AMOUNT_RETURNED, Long.valueOf(this.mAmountReturned));
        return hashMap;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_ADDRESS, this.mAddress);
        try {
            jSONObject.put(FIELD_AMOUNT_CHARGED, this.mAmountCharged);
            jSONObject.put(FIELD_AMOUNT_RECEIVED, this.mAmountReceived);
            jSONObject.put(FIELD_AMOUNT_RETURNED, this.mAmountReturned);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    public static SourceReceiver fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static SourceReceiver fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new SourceReceiver(StripeJsonUtils.optString(jSONObject, FIELD_ADDRESS), jSONObject.optLong(FIELD_AMOUNT_CHARGED), jSONObject.optLong(FIELD_AMOUNT_RECEIVED), jSONObject.optLong(FIELD_AMOUNT_RETURNED));
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof SourceReceiver) && typedEquals((SourceReceiver) obj));
    }

    private boolean typedEquals(SourceReceiver sourceReceiver) {
        return ObjectUtils.equals(this.mAddress, sourceReceiver.mAddress) && this.mAmountCharged == sourceReceiver.mAmountCharged && this.mAmountReceived == sourceReceiver.mAmountReceived && this.mAmountReturned == sourceReceiver.mAmountReturned;
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mAddress, Long.valueOf(this.mAmountCharged), Long.valueOf(this.mAmountReceived), Long.valueOf(this.mAmountReturned));
    }
}
