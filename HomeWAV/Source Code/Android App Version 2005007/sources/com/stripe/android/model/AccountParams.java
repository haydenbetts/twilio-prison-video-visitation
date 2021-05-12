package com.stripe.android.model;

import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;

public class AccountParams {
    private static final String API_PARAM_LEGAL_ENTITY = "legal_entity";
    private static final String API_TOS_SHOWN_AND_ACCEPTED = "tos_shown_and_accepted";
    private Map<String, Object> mBusinessData;
    private final BusinessType mBusinessType;
    private Boolean mTosShownAndAccepted;

    private AccountParams(BusinessType businessType) {
        this.mBusinessType = businessType;
    }

    public static AccountParams createAccountParams(boolean z, Map<String, Object> map) {
        return new AccountParams((BusinessType) null).setTosShownAndAccepted(z).setLegalEntity(map);
    }

    public static AccountParams createAccountParams(boolean z, BusinessType businessType, Map<String, Object> map) {
        return new AccountParams(businessType).setTosShownAndAccepted(z).setLegalEntity(map);
    }

    public AccountParams setTosShownAndAccepted(boolean z) {
        this.mTosShownAndAccepted = Boolean.valueOf(z);
        return this;
    }

    public AccountParams setLegalEntity(Map<String, Object> map) {
        this.mBusinessData = map;
        return this;
    }

    public Map<String, Object> toParamMap() {
        HashMap hashMap = new HashMap();
        HashMap hashMap2 = new HashMap();
        Boolean bool = this.mTosShownAndAccepted;
        if (bool != null) {
            hashMap2.put(API_TOS_SHOWN_AND_ACCEPTED, bool);
        }
        Map<String, Object> map = this.mBusinessData;
        if (map != null) {
            BusinessType businessType = this.mBusinessType;
            if (businessType != null) {
                hashMap2.put(businessType.code, this.mBusinessData);
            } else {
                hashMap2.put(API_PARAM_LEGAL_ENTITY, map);
            }
        }
        hashMap.put("account", hashMap2);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mTosShownAndAccepted, this.mBusinessType, this.mBusinessData);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof AccountParams) && typedEquals((AccountParams) obj));
    }

    private boolean typedEquals(AccountParams accountParams) {
        return ObjectUtils.equals(this.mTosShownAndAccepted, accountParams.mTosShownAndAccepted) && ObjectUtils.equals(this.mBusinessType, accountParams.mBusinessType) && ObjectUtils.equals(this.mBusinessData, accountParams.mBusinessData);
    }

    public enum BusinessType {
        Individual(BankAccount.TYPE_INDIVIDUAL),
        Company("company");
        
        public final String code;

        private BusinessType(String str) {
            this.code = str;
        }
    }
}
