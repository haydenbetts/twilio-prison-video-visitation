package com.stripe.android.model.wallets;

import com.stripe.android.model.wallets.Wallet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class GooglePayWallet extends Wallet {
    private GooglePayWallet(Builder builder) {
        super(Wallet.Type.GooglePay, builder);
    }

    /* access modifiers changed from: package-private */
    public Map<String, Object> getWalletTypeMap() {
        return new HashMap();
    }

    /* access modifiers changed from: package-private */
    public JSONObject getWalletTypeJson() {
        return new JSONObject();
    }

    static Builder fromJson(JSONObject jSONObject) {
        return new Builder();
    }

    public static final class Builder extends Wallet.Builder<GooglePayWallet> {
        public /* bridge */ /* synthetic */ Wallet.Builder setDynamicLast4(String str) {
            return super.setDynamicLast4(str);
        }

        /* access modifiers changed from: package-private */
        public GooglePayWallet build() {
            return new GooglePayWallet(this);
        }
    }
}
