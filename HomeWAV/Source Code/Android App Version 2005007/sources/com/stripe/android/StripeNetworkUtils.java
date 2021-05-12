package com.stripe.android;

import android.content.Context;
import android.provider.Settings;
import android.text.TextUtils;
import com.stripe.android.model.BankAccount;
import com.stripe.android.model.Card;
import com.stripe.android.model.Token;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import lib.android.paypal.com.magnessdk.a.b;

public class StripeNetworkUtils {
    private static final String GUID = "guid";
    private static final String MUID = "muid";

    interface UidProvider {
        String getPackageName();

        String getUid();
    }

    static Map<String, Object> hashMapFromCard(Context context, Card card) {
        return hashMapFromCard((UidProvider) null, context, card);
    }

    static Map<String, Object> hashMapFromPersonalId(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("personal_id_number", str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(Token.TYPE_PII, hashMap);
        return hashMap2;
    }

    static Map<String, Object> mapFromCvc(String str) {
        HashMap hashMap = new HashMap();
        hashMap.put("cvc", str);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(Token.TYPE_CVC_UPDATE, hashMap);
        return hashMap2;
    }

    private static Map<String, Object> hashMapFromCard(UidProvider uidProvider, Context context, Card card) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("number", StripeTextUtils.nullIfBlank(card.getNumber()));
        hashMap2.put("cvc", StripeTextUtils.nullIfBlank(card.getCVC()));
        hashMap2.put("exp_month", card.getExpMonth());
        hashMap2.put("exp_year", card.getExpYear());
        hashMap2.put("name", StripeTextUtils.nullIfBlank(card.getName()));
        hashMap2.put("currency", StripeTextUtils.nullIfBlank(card.getCurrency()));
        hashMap2.put("address_line1", StripeTextUtils.nullIfBlank(card.getAddressLine1()));
        hashMap2.put("address_line2", StripeTextUtils.nullIfBlank(card.getAddressLine2()));
        hashMap2.put("address_city", StripeTextUtils.nullIfBlank(card.getAddressCity()));
        hashMap2.put("address_zip", StripeTextUtils.nullIfBlank(card.getAddressZip()));
        hashMap2.put("address_state", StripeTextUtils.nullIfBlank(card.getAddressState()));
        hashMap2.put("address_country", StripeTextUtils.nullIfBlank(card.getAddressCountry()));
        removeNullAndEmptyParams(hashMap2);
        hashMap.put("product_usage", card.getLoggingTokens());
        hashMap.put("card", hashMap2);
        addUidParams(uidProvider, context, hashMap);
        return hashMap;
    }

    static Map<String, Object> hashMapFromBankAccount(Context context, BankAccount bankAccount) {
        return hashMapFromBankAccount((UidProvider) null, context, bankAccount);
    }

    public static void removeNullAndEmptyParams(Map<String, Object> map) {
        Iterator it = new HashSet(map.keySet()).iterator();
        while (it.hasNext()) {
            String str = (String) it.next();
            if (map.get(str) == null) {
                map.remove(str);
            }
            if ((map.get(str) instanceof CharSequence) && TextUtils.isEmpty((CharSequence) map.get(str))) {
                map.remove(str);
            }
            if (map.get(str) instanceof Map) {
                removeNullAndEmptyParams((Map) map.get(str));
            }
        }
    }

    static void addUidParamsToPaymentIntent(UidProvider uidProvider, Context context, Map<String, Object> map) {
        if (map.containsKey("source_data") && (map.get("source_data") instanceof Map)) {
            addUidParams(uidProvider, context, (Map) map.get("source_data"));
        }
    }

    static void addUidParams(UidProvider uidProvider, Context context, Map<String, Object> map) {
        String str;
        String str2;
        if (uidProvider == null) {
            str = Settings.Secure.getString(context.getContentResolver(), b.f);
        } else {
            str = uidProvider.getUid();
        }
        if (!StripeTextUtils.isBlank(str)) {
            String shaHashInput = StripeTextUtils.shaHashInput(str);
            if (uidProvider == null) {
                str2 = context.getApplicationContext().getPackageName() + str;
            } else {
                str2 = uidProvider.getPackageName() + str;
            }
            String shaHashInput2 = StripeTextUtils.shaHashInput(str2);
            if (!StripeTextUtils.isBlank(shaHashInput)) {
                map.put(GUID, shaHashInput);
            }
            if (!StripeTextUtils.isBlank(shaHashInput2)) {
                map.put(MUID, shaHashInput2);
            }
        }
    }

    private static Map<String, Object> hashMapFromBankAccount(UidProvider uidProvider, Context context, BankAccount bankAccount) {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("country", bankAccount.getCountryCode());
        hashMap2.put("currency", bankAccount.getCurrency());
        hashMap2.put("account_number", bankAccount.getAccountNumber());
        hashMap2.put("routing_number", StripeTextUtils.nullIfBlank(bankAccount.getRoutingNumber()));
        hashMap2.put("account_holder_name", StripeTextUtils.nullIfBlank(bankAccount.getAccountHolderName()));
        hashMap2.put("account_holder_type", StripeTextUtils.nullIfBlank(bankAccount.getAccountHolderType()));
        removeNullAndEmptyParams(hashMap2);
        hashMap.put(Token.TYPE_BANK_ACCOUNT, hashMap2);
        addUidParams(uidProvider, context, hashMap);
        return hashMap;
    }
}
