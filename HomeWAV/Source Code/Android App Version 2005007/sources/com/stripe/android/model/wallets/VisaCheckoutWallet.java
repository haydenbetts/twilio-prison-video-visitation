package com.stripe.android.model.wallets;

import com.stripe.android.model.StripeJsonUtils;
import com.stripe.android.model.wallets.Wallet;
import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

public final class VisaCheckoutWallet extends Wallet {
    private static final String FIELD_BILLING_ADDRESS = "billing_address";
    private static final String FIELD_EMAIL = "email";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_SHIPPING_ADDRESS = "shipping_address";
    public final Wallet.Address billingAddress;
    public final String email;
    public final String name;
    public final Wallet.Address shippingAddress;

    private VisaCheckoutWallet(Builder builder) {
        super(Wallet.Type.VisaCheckout, builder);
        this.billingAddress = builder.mBillingAddress;
        this.email = builder.mEmail;
        this.name = builder.mName;
        this.shippingAddress = builder.mShippingAddress;
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> getWalletTypeMap() {
        HashMap hashMap = new HashMap();
        Wallet.Address address = this.billingAddress;
        Map<String, Object> map = null;
        hashMap.put(FIELD_BILLING_ADDRESS, address != null ? address.toMap() : null);
        hashMap.put("email", this.email);
        hashMap.put("name", this.name);
        Wallet.Address address2 = this.shippingAddress;
        if (address2 != null) {
            map = address2.toMap();
        }
        hashMap.put(FIELD_SHIPPING_ADDRESS, map);
        return hashMap;
    }

    /* access modifiers changed from: package-private */
    public JSONObject getWalletTypeJson() {
        JSONObject jSONObject = new JSONObject();
        try {
            Wallet.Address address = this.billingAddress;
            JSONObject jSONObject2 = null;
            jSONObject.put(FIELD_BILLING_ADDRESS, address != null ? address.toJson() : null);
            jSONObject.put("email", this.email);
            jSONObject.put("name", this.name);
            Wallet.Address address2 = this.shippingAddress;
            if (address2 != null) {
                jSONObject2 = address2.toJson();
            }
            jSONObject.put(FIELD_SHIPPING_ADDRESS, jSONObject2);
        } catch (JSONException unused) {
        }
        return jSONObject;
    }

    static Builder fromJson(JSONObject jSONObject) {
        return new Builder().setBillingAddress(Wallet.Address.fromJson(jSONObject.optJSONObject(FIELD_BILLING_ADDRESS))).setEmail(StripeJsonUtils.optString(jSONObject, "email")).setName(StripeJsonUtils.optString(jSONObject, "name")).setShippingAddress(Wallet.Address.fromJson(jSONObject.optJSONObject(FIELD_SHIPPING_ADDRESS)));
    }

    public int hashCode() {
        return ObjectUtils.hash(this.billingAddress, this.email, this.name, this.shippingAddress);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof VisaCheckoutWallet) && typedEquals((VisaCheckoutWallet) obj));
    }

    private boolean typedEquals(VisaCheckoutWallet visaCheckoutWallet) {
        return ObjectUtils.equals(this.billingAddress, visaCheckoutWallet.billingAddress) && ObjectUtils.equals(this.email, visaCheckoutWallet.email) && ObjectUtils.equals(this.name, visaCheckoutWallet.name) && ObjectUtils.equals(this.shippingAddress, visaCheckoutWallet.shippingAddress);
    }

    public static final class Builder extends Wallet.Builder<VisaCheckoutWallet> {
        /* access modifiers changed from: private */
        public Wallet.Address mBillingAddress;
        /* access modifiers changed from: private */
        public String mEmail;
        /* access modifiers changed from: private */
        public String mName;
        /* access modifiers changed from: private */
        public Wallet.Address mShippingAddress;

        public /* bridge */ /* synthetic */ Wallet.Builder setDynamicLast4(String str) {
            return super.setDynamicLast4(str);
        }

        public Builder setBillingAddress(Wallet.Address address) {
            this.mBillingAddress = address;
            return this;
        }

        public Builder setEmail(String str) {
            this.mEmail = str;
            return this;
        }

        public Builder setName(String str) {
            this.mName = str;
            return this;
        }

        public Builder setShippingAddress(Wallet.Address address) {
            this.mShippingAddress = address;
            return this;
        }

        public VisaCheckoutWallet build() {
            return new VisaCheckoutWallet(this);
        }
    }
}
