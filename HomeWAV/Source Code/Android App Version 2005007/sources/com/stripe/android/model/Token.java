package com.stripe.android.model;

import android.text.TextUtils;
import com.stripe.android.utils.ObjectUtils;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Date;
import org.json.JSONException;
import org.json.JSONObject;

public class Token implements StripePaymentSource {
    private static final String FIELD_BANK_ACCOUNT = "bank_account";
    private static final String FIELD_CARD = "card";
    private static final String FIELD_CREATED = "created";
    private static final String FIELD_ID = "id";
    private static final String FIELD_LIVEMODE = "livemode";
    private static final String FIELD_TYPE = "type";
    private static final String FIELD_USED = "used";
    public static final String TYPE_ACCOUNT = "account";
    public static final String TYPE_BANK_ACCOUNT = "bank_account";
    public static final String TYPE_CARD = "card";
    public static final String TYPE_CVC_UPDATE = "cvc_update";
    public static final String TYPE_PII = "pii";
    private final BankAccount mBankAccount;
    private final Card mCard;
    private final Date mCreated;
    private final String mId;
    private final boolean mLivemode;
    private final String mType;
    private final boolean mUsed;

    @Retention(RetentionPolicy.SOURCE)
    public @interface TokenType {
    }

    public Token(String str, boolean z, Date date, Boolean bool, Card card) {
        this.mId = str;
        this.mType = "card";
        this.mCreated = date;
        this.mLivemode = z;
        this.mCard = card;
        this.mUsed = Boolean.TRUE.equals(bool);
        this.mBankAccount = null;
    }

    public Token(String str, boolean z, Date date, Boolean bool, BankAccount bankAccount) {
        this.mId = str;
        this.mType = "bank_account";
        this.mCreated = date;
        this.mLivemode = z;
        this.mCard = null;
        this.mUsed = Boolean.TRUE.equals(bool);
        this.mBankAccount = bankAccount;
    }

    public Token(String str, String str2, boolean z, Date date, Boolean bool) {
        this.mId = str;
        this.mType = str2;
        this.mCreated = date;
        this.mCard = null;
        this.mBankAccount = null;
        this.mUsed = Boolean.TRUE.equals(bool);
        this.mLivemode = z;
    }

    public Date getCreated() {
        return this.mCreated;
    }

    public String getId() {
        return this.mId;
    }

    public boolean getLivemode() {
        return this.mLivemode;
    }

    public boolean getUsed() {
        return this.mUsed;
    }

    public String getType() {
        return this.mType;
    }

    public Card getCard() {
        return this.mCard;
    }

    public BankAccount getBankAccount() {
        return this.mBankAccount;
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mId, this.mType, this.mCreated, Boolean.valueOf(this.mLivemode), Boolean.valueOf(this.mUsed), this.mBankAccount, this.mCard);
    }

    public boolean equals(Object obj) {
        return super.equals(obj) || ((obj instanceof Token) && typedEquals((Token) obj));
    }

    private boolean typedEquals(Token token) {
        return ObjectUtils.equals(this.mId, token.mId) && ObjectUtils.equals(this.mType, token.mType) && ObjectUtils.equals(this.mCreated, token.mCreated) && this.mLivemode == token.mLivemode && this.mUsed == token.mUsed && ObjectUtils.equals(this.mBankAccount, token.mBankAccount) && ObjectUtils.equals(this.mCard, token.mCard);
    }

    public static Token fromString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Token fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        String optString = StripeJsonUtils.optString(jSONObject, "id");
        Long optLong = StripeJsonUtils.optLong(jSONObject, FIELD_CREATED);
        Boolean optBoolean = StripeJsonUtils.optBoolean(jSONObject, FIELD_LIVEMODE);
        String asTokenType = asTokenType(StripeJsonUtils.optString(jSONObject, "type"));
        Boolean optBoolean2 = StripeJsonUtils.optBoolean(jSONObject, FIELD_USED);
        if (optString == null || optLong == null || optBoolean == null) {
            return null;
        }
        boolean equals = Boolean.TRUE.equals(optBoolean2);
        boolean equals2 = Boolean.TRUE.equals(optBoolean);
        Date date = new Date(optLong.longValue() * 1000);
        if ("bank_account".equals(asTokenType)) {
            JSONObject optJSONObject = jSONObject.optJSONObject("bank_account");
            if (optJSONObject == null) {
                return null;
            }
            return new Token(optString, equals2, date, Boolean.valueOf(equals), BankAccount.fromJson(optJSONObject));
        } else if ("card".equals(asTokenType)) {
            JSONObject optJSONObject2 = jSONObject.optJSONObject("card");
            if (optJSONObject2 == null) {
                return null;
            }
            return new Token(optString, equals2, date, Boolean.valueOf(equals), Card.fromJson(optJSONObject2));
        } else if (!TYPE_PII.equals(asTokenType) && !"account".equals(asTokenType) && !TYPE_CVC_UPDATE.equals(asTokenType)) {
            return null;
        } else {
            return new Token(optString, asTokenType, equals2, date, Boolean.valueOf(equals));
        }
    }

    private static String asTokenType(String str) {
        if (str != null && !TextUtils.isEmpty(str.trim())) {
            if ("card".equals(str)) {
                return "card";
            }
            if ("bank_account".equals(str)) {
                return "bank_account";
            }
            if (TYPE_PII.equals(str)) {
                return TYPE_PII;
            }
            if ("account".equals(str)) {
                return "account";
            }
            if (TYPE_CVC_UPDATE.equals(str)) {
                return TYPE_CVC_UPDATE;
            }
        }
        return null;
    }
}
