package com.stripe.android.model;

import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public class SourceOwner extends StripeJsonModel {
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PHONE = "phone";
    private static final String FIELD_VERIFIED_ADDRESS = "verified_address";
    private static final String FIELD_VERIFIED_EMAIL = "verified_email";
    private static final String FIELD_VERIFIED_NAME = "verified_name";
    private static final String FIELD_VERIFIED_PHONE = "verified_phone";
    private static final String VERIFIED = "verified_";
    private Address mAddress;
    private String mEmail;
    private String mName;
    private String mPhone;
    private Address mVerifiedAddress;
    private String mVerifiedEmail;
    private String mVerifiedName;
    private String mVerifiedPhone;

    private SourceOwner(Address address, String str, String str2, String str3, Address address2, String str4, String str5, String str6) {
        this.mAddress = address;
        this.mEmail = str;
        this.mName = str2;
        this.mPhone = str3;
        this.mVerifiedAddress = address2;
        this.mVerifiedEmail = str4;
        this.mVerifiedName = str5;
        this.mVerifiedPhone = str6;
    }

    public Address getAddress() {
        return this.mAddress;
    }

    public String getEmail() {
        return this.mEmail;
    }

    public String getName() {
        return this.mName;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public Address getVerifiedAddress() {
        return this.mVerifiedAddress;
    }

    public String getVerifiedEmail() {
        return this.mVerifiedEmail;
    }

    public String getVerifiedName() {
        return this.mVerifiedName;
    }

    public String getVerifiedPhone() {
        return this.mVerifiedPhone;
    }

    /* access modifiers changed from: package-private */
    public void setAddress(Address address) {
        this.mAddress = address;
    }

    /* access modifiers changed from: package-private */
    public void setEmail(String str) {
        this.mEmail = str;
    }

    /* access modifiers changed from: package-private */
    public void setName(String str) {
        this.mName = str;
    }

    /* access modifiers changed from: package-private */
    public void setPhone(String str) {
        this.mPhone = str;
    }

    /* access modifiers changed from: package-private */
    public void setVerifiedAddress(Address address) {
        this.mVerifiedAddress = address;
    }

    /* access modifiers changed from: package-private */
    public void setVerifiedEmail(String str) {
        this.mVerifiedEmail = str;
    }

    /* access modifiers changed from: package-private */
    public void setVerifiedName(String str) {
        this.mVerifiedName = str;
    }

    /* access modifiers changed from: package-private */
    public void setVerifiedPhone(String str) {
        this.mVerifiedPhone = str;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        Address address = this.mAddress;
        if (address != null) {
            hashMap.put(FIELD_ADDRESS, address.toMap());
        }
        hashMap.put("email", this.mEmail);
        hashMap.put("name", this.mName);
        hashMap.put("phone", this.mPhone);
        Address address2 = this.mVerifiedAddress;
        if (address2 != null) {
            hashMap.put(FIELD_VERIFIED_ADDRESS, address2.toMap());
        }
        hashMap.put(FIELD_VERIFIED_EMAIL, this.mVerifiedEmail);
        hashMap.put(FIELD_VERIFIED_NAME, this.mVerifiedName);
        hashMap.put(FIELD_VERIFIED_PHONE, this.mVerifiedPhone);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        Address address = this.mAddress;
        JSONObject jSONObject2 = null;
        JSONObject json = address == null ? null : address.toJson();
        Address address2 = this.mVerifiedAddress;
        if (address2 != null) {
            jSONObject2 = address2.toJson();
        }
        if (json != null) {
            try {
                if (json.length() > 0) {
                    jSONObject.put(FIELD_ADDRESS, json);
                }
            } catch (JSONException unused) {
            }
        }
        StripeJsonUtils.putStringIfNotNull(jSONObject, "email", this.mEmail);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "name", this.mName);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "phone", this.mPhone);
        if (jSONObject2 != null && jSONObject2.length() > 0) {
            jSONObject.put(FIELD_VERIFIED_ADDRESS, jSONObject2);
        }
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_VERIFIED_EMAIL, this.mVerifiedEmail);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_VERIFIED_NAME, this.mVerifiedName);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_VERIFIED_PHONE, this.mVerifiedPhone);
        return jSONObject;
    }

    public static SourceOwner fromString(String str) {
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static SourceOwner fromJson(JSONObject jSONObject) {
        Address address = null;
        if (jSONObject == null) {
            return null;
        }
        JSONObject optJSONObject = jSONObject.optJSONObject(FIELD_ADDRESS);
        Address fromJson = optJSONObject != null ? Address.fromJson(optJSONObject) : null;
        String optString = StripeJsonUtils.optString(jSONObject, "email");
        String optString2 = StripeJsonUtils.optString(jSONObject, "name");
        String optString3 = StripeJsonUtils.optString(jSONObject, "phone");
        JSONObject optJSONObject2 = jSONObject.optJSONObject(FIELD_VERIFIED_ADDRESS);
        if (optJSONObject2 != null) {
            address = Address.fromJson(optJSONObject2);
        }
        return new SourceOwner(fromJson, optString, optString2, optString3, address, StripeJsonUtils.optString(jSONObject, FIELD_VERIFIED_EMAIL), StripeJsonUtils.optString(jSONObject, FIELD_VERIFIED_NAME), StripeJsonUtils.optString(jSONObject, FIELD_VERIFIED_PHONE));
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof SourceOwner) && typedEquals((SourceOwner) obj));
    }

    private boolean typedEquals(SourceOwner sourceOwner) {
        return ObjectUtils.equals(this.mAddress, sourceOwner.mAddress) && ObjectUtils.equals(this.mEmail, sourceOwner.mEmail) && ObjectUtils.equals(this.mName, sourceOwner.mName) && ObjectUtils.equals(this.mPhone, sourceOwner.mPhone) && ObjectUtils.equals(this.mVerifiedAddress, sourceOwner.mVerifiedAddress) && ObjectUtils.equals(this.mVerifiedEmail, sourceOwner.mVerifiedEmail) && ObjectUtils.equals(this.mVerifiedName, sourceOwner.mVerifiedName) && ObjectUtils.equals(this.mVerifiedPhone, sourceOwner.mVerifiedPhone);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mAddress, this.mEmail, this.mName, this.mPhone, this.mVerifiedAddress, this.mVerifiedEmail, this.mVerifiedName, this.mVerifiedPhone);
    }
}
