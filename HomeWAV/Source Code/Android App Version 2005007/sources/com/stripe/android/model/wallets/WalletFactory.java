package com.stripe.android.model.wallets;

import com.stripe.android.model.StripeJsonUtils;
import com.stripe.android.model.wallets.Wallet;
import org.json.JSONObject;

public class WalletFactory {
    public Wallet create(JSONObject jSONObject) {
        Wallet.Type fromCode;
        if (jSONObject == null || (fromCode = Wallet.Type.fromCode(StripeJsonUtils.optString(jSONObject, "type"))) == null) {
            return null;
        }
        return create(fromCode, jSONObject);
    }

    private Wallet create(Wallet.Type type, JSONObject jSONObject) {
        Wallet.Builder builder;
        JSONObject optJSONObject = jSONObject.optJSONObject(type.code);
        if (optJSONObject == null) {
            return null;
        }
        switch (AnonymousClass1.$SwitchMap$com$stripe$android$model$wallets$Wallet$Type[type.ordinal()]) {
            case 1:
                builder = AmexExpressCheckoutWallet.fromJson(optJSONObject);
                break;
            case 2:
                builder = ApplePayWallet.fromJson(optJSONObject);
                break;
            case 3:
                builder = GooglePayWallet.fromJson(optJSONObject);
                break;
            case 4:
                builder = MasterpassWallet.fromJson(optJSONObject);
                break;
            case 5:
                builder = SamsungPayWallet.fromJson(optJSONObject);
                break;
            case 6:
                builder = VisaCheckoutWallet.fromJson(optJSONObject);
                break;
            default:
                return null;
        }
        return builder.setDynamicLast4(StripeJsonUtils.optString(jSONObject, "dynamic_last4")).build();
    }

    /* renamed from: com.stripe.android.model.wallets.WalletFactory$1  reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$stripe$android$model$wallets$Wallet$Type;

        /* JADX WARNING: Can't wrap try/catch for region: R(14:0|1|2|3|4|5|6|7|8|9|10|11|12|14) */
        /* JADX WARNING: Failed to process nested try/catch */
        /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x003e */
        /* JADX WARNING: Missing exception handler attribute for start block: B:3:0x0012 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:5:0x001d */
        /* JADX WARNING: Missing exception handler attribute for start block: B:7:0x0028 */
        /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0033 */
        static {
            /*
                com.stripe.android.model.wallets.Wallet$Type[] r0 = com.stripe.android.model.wallets.Wallet.Type.values()
                int r0 = r0.length
                int[] r0 = new int[r0]
                $SwitchMap$com$stripe$android$model$wallets$Wallet$Type = r0
                com.stripe.android.model.wallets.Wallet$Type r1 = com.stripe.android.model.wallets.Wallet.Type.AmexExpressCheckout     // Catch:{ NoSuchFieldError -> 0x0012 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0012 }
                r2 = 1
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0012 }
            L_0x0012:
                int[] r0 = $SwitchMap$com$stripe$android$model$wallets$Wallet$Type     // Catch:{ NoSuchFieldError -> 0x001d }
                com.stripe.android.model.wallets.Wallet$Type r1 = com.stripe.android.model.wallets.Wallet.Type.ApplePay     // Catch:{ NoSuchFieldError -> 0x001d }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x001d }
                r2 = 2
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x001d }
            L_0x001d:
                int[] r0 = $SwitchMap$com$stripe$android$model$wallets$Wallet$Type     // Catch:{ NoSuchFieldError -> 0x0028 }
                com.stripe.android.model.wallets.Wallet$Type r1 = com.stripe.android.model.wallets.Wallet.Type.GooglePay     // Catch:{ NoSuchFieldError -> 0x0028 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0028 }
                r2 = 3
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0028 }
            L_0x0028:
                int[] r0 = $SwitchMap$com$stripe$android$model$wallets$Wallet$Type     // Catch:{ NoSuchFieldError -> 0x0033 }
                com.stripe.android.model.wallets.Wallet$Type r1 = com.stripe.android.model.wallets.Wallet.Type.Masterpass     // Catch:{ NoSuchFieldError -> 0x0033 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0033 }
                r2 = 4
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0033 }
            L_0x0033:
                int[] r0 = $SwitchMap$com$stripe$android$model$wallets$Wallet$Type     // Catch:{ NoSuchFieldError -> 0x003e }
                com.stripe.android.model.wallets.Wallet$Type r1 = com.stripe.android.model.wallets.Wallet.Type.SamsungPay     // Catch:{ NoSuchFieldError -> 0x003e }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x003e }
                r2 = 5
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x003e }
            L_0x003e:
                int[] r0 = $SwitchMap$com$stripe$android$model$wallets$Wallet$Type     // Catch:{ NoSuchFieldError -> 0x0049 }
                com.stripe.android.model.wallets.Wallet$Type r1 = com.stripe.android.model.wallets.Wallet.Type.VisaCheckout     // Catch:{ NoSuchFieldError -> 0x0049 }
                int r1 = r1.ordinal()     // Catch:{ NoSuchFieldError -> 0x0049 }
                r2 = 6
                r0[r1] = r2     // Catch:{ NoSuchFieldError -> 0x0049 }
            L_0x0049:
                return
            */
            throw new UnsupportedOperationException("Method not decompiled: com.stripe.android.model.wallets.WalletFactory.AnonymousClass1.<clinit>():void");
        }
    }
}
