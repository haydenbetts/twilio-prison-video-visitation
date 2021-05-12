package com.stripe.android.model.wallets;

import com.stripe.android.model.wallets.Wallet;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public final class SamsungPayWallet extends Wallet {
    private SamsungPayWallet(Builder builder) {
        super(Wallet.Type.SamsungPay, builder);
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

    public static final class Builder extends Wallet.Builder<SamsungPayWallet> {
        public /* bridge */ /* synthetic */ Wallet.Builder setDynamicLast4(String str) {
            return super.setDynamicLast4(str);
        }

        /* access modifiers changed from: package-private */
        public SamsungPayWallet build() {
            return new SamsungPayWallet(this);
        }
    }
}
