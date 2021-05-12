package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.android.gms.wallet.ShippingAddressRequirements;
import com.google.android.gms.wallet.TransactionInfo;
import java.util.HashMap;
import org.json.JSONArray;
import org.json.JSONObject;

public class GooglePaymentRequest implements Parcelable {
    public static final Parcelable.Creator<GooglePaymentRequest> CREATOR = new Parcelable.Creator<GooglePaymentRequest>() {
        public GooglePaymentRequest createFromParcel(Parcel parcel) {
            return new GooglePaymentRequest(parcel);
        }

        public GooglePaymentRequest[] newArray(int i) {
            return new GooglePaymentRequest[i];
        }
    };
    private Boolean mAllowPrepaidCards;
    private HashMap<String, JSONArray> mAllowedAuthMethods;
    private HashMap<String, JSONArray> mAllowedCardNetworks;
    private HashMap<String, JSONObject> mAllowedPaymentMethods;
    private Integer mBillingAddressFormat;
    private Boolean mBillingAddressRequired;
    private Boolean mEmailRequired;
    private String mEnvironment;
    private String mGoogleMerchantId;
    private String mGoogleMerchantName;
    private boolean mPayPalEnabled;
    private Boolean mPhoneNumberRequired;
    private Boolean mShippingAddressRequired;
    private ShippingAddressRequirements mShippingAddressRequirements;
    private HashMap<String, JSONObject> mTokenizationSpecifications;
    private TransactionInfo mTransactionInfo;

    public int describeContents() {
        return 0;
    }

    public GooglePaymentRequest() {
        this.mEmailRequired = null;
        this.mPhoneNumberRequired = null;
        this.mBillingAddressRequired = null;
        this.mShippingAddressRequired = null;
        this.mAllowPrepaidCards = null;
        this.mPayPalEnabled = true;
        this.mAllowedPaymentMethods = new HashMap<>();
        this.mTokenizationSpecifications = new HashMap<>();
        this.mAllowedAuthMethods = new HashMap<>();
        this.mAllowedCardNetworks = new HashMap<>();
    }

    public GooglePaymentRequest transactionInfo(TransactionInfo transactionInfo) {
        this.mTransactionInfo = transactionInfo;
        return this;
    }

    public GooglePaymentRequest emailRequired(boolean z) {
        this.mEmailRequired = Boolean.valueOf(z);
        return this;
    }

    public GooglePaymentRequest phoneNumberRequired(boolean z) {
        this.mPhoneNumberRequired = Boolean.valueOf(z);
        return this;
    }

    public GooglePaymentRequest billingAddressRequired(boolean z) {
        this.mBillingAddressRequired = Boolean.valueOf(z);
        return this;
    }

    public GooglePaymentRequest billingAddressFormat(int i) {
        this.mBillingAddressFormat = Integer.valueOf(i);
        return this;
    }

    public GooglePaymentRequest shippingAddressRequired(boolean z) {
        this.mShippingAddressRequired = Boolean.valueOf(z);
        return this;
    }

    public GooglePaymentRequest shippingAddressRequirements(ShippingAddressRequirements shippingAddressRequirements) {
        this.mShippingAddressRequirements = shippingAddressRequirements;
        return this;
    }

    public GooglePaymentRequest allowPrepaidCards(boolean z) {
        this.mAllowPrepaidCards = Boolean.valueOf(z);
        return this;
    }

    public GooglePaymentRequest paypalEnabled(boolean z) {
        this.mPayPalEnabled = z;
        return this;
    }

    public GooglePaymentRequest setAllowedPaymentMethod(String str, JSONObject jSONObject) {
        this.mAllowedPaymentMethods.put(str, jSONObject);
        return this;
    }

    public GooglePaymentRequest setTokenizationSpecificationForType(String str, JSONObject jSONObject) {
        this.mTokenizationSpecifications.put(str, jSONObject);
        return this;
    }

    public GooglePaymentRequest setAllowedAuthMethods(String str, JSONArray jSONArray) {
        this.mAllowedAuthMethods.put(str, jSONArray);
        return this;
    }

    public GooglePaymentRequest setAllowedCardNetworks(String str, JSONArray jSONArray) {
        this.mAllowedCardNetworks.put(str, jSONArray);
        return this;
    }

    public GooglePaymentRequest googleMerchantId(String str) {
        this.mGoogleMerchantId = str;
        return this;
    }

    public GooglePaymentRequest googleMerchantName(String str) {
        this.mGoogleMerchantName = str;
        return this;
    }

    public GooglePaymentRequest environment(String str) {
        String str2 = "PRODUCTION";
        if (!str2.equals(str.toUpperCase())) {
            str2 = "TEST";
        }
        this.mEnvironment = str2;
        return this;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(13:0|(4:2|(3:6|7|8)|9|10)|11|12|13|(8:17|18|19|(5:21|22|23|24|(1:26))|27|48|46|15)|47|29|(5:30|31|(1:33)|34|(1:36))|37|(3:39|40|(1:42))|43|45) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:2|(3:6|7|8)|9|10) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:21|22|23|24|(1:26)) */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:11:0x0047 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:23:0x00b7 */
    /* JADX WARNING: Missing exception handler attribute for start block: B:9:0x0040 */
    /* JADX WARNING: Removed duplicated region for block: B:17:0x0076  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x00d8 A[Catch:{ JSONException -> 0x00f7 }] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0109 A[Catch:{ JSONException -> 0x0125 }] */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x011c A[Catch:{ JSONException -> 0x0125 }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x0170 A[Catch:{ JSONException -> 0x0175 }] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String toJson() {
        /*
            r12 = this;
            java.lang.String r0 = "billingAddressParameters"
            java.lang.String r1 = "parameters"
            org.json.JSONObject r2 = new org.json.JSONObject
            r2.<init>()
            com.google.android.gms.wallet.TransactionInfo r3 = r12.getTransactionInfo()
            org.json.JSONArray r4 = new org.json.JSONArray
            r4.<init>()
            org.json.JSONObject r5 = new org.json.JSONObject
            r5.<init>()
            org.json.JSONArray r6 = new org.json.JSONArray
            r6.<init>()
            java.lang.Boolean r6 = r12.isShippingAddressRequired()
            boolean r6 = r6.booleanValue()
            java.lang.String r7 = "phoneNumberRequired"
            if (r6 == 0) goto L_0x0047
            com.google.android.gms.wallet.ShippingAddressRequirements r6 = r12.mShippingAddressRequirements
            java.util.ArrayList r6 = r6.getAllowedCountryCodes()
            if (r6 == 0) goto L_0x0040
            int r8 = r6.size()
            if (r8 <= 0) goto L_0x0040
            java.lang.String r8 = "allowedCountryCodes"
            org.json.JSONArray r9 = new org.json.JSONArray     // Catch:{ JSONException -> 0x0040 }
            r9.<init>(r6)     // Catch:{ JSONException -> 0x0040 }
            r5.put(r8, r9)     // Catch:{ JSONException -> 0x0040 }
        L_0x0040:
            java.lang.Boolean r6 = r12.isPhoneNumberRequired()     // Catch:{ JSONException -> 0x0047 }
            r5.put(r7, r6)     // Catch:{ JSONException -> 0x0047 }
        L_0x0047:
            java.lang.String r6 = r12.totalPriceStatusToString()     // Catch:{ JSONException -> 0x0066 }
            java.lang.String r8 = "totalPriceStatus"
            org.json.JSONObject r6 = r2.put(r8, r6)     // Catch:{ JSONException -> 0x0066 }
            java.lang.String r8 = "totalPrice"
            java.lang.String r9 = r3.getTotalPrice()     // Catch:{ JSONException -> 0x0066 }
            org.json.JSONObject r6 = r6.put(r8, r9)     // Catch:{ JSONException -> 0x0066 }
            java.lang.String r8 = "currencyCode"
            java.lang.String r3 = r3.getCurrencyCode()     // Catch:{ JSONException -> 0x0066 }
            r6.put(r8, r3)     // Catch:{ JSONException -> 0x0066 }
        L_0x0066:
            java.util.HashMap<java.lang.String, org.json.JSONObject> r3 = r12.mAllowedPaymentMethods
            java.util.Set r3 = r3.entrySet()
            java.util.Iterator r3 = r3.iterator()
        L_0x0070:
            boolean r6 = r3.hasNext()
            if (r6 == 0) goto L_0x00fa
            java.lang.Object r6 = r3.next()
            java.util.Map$Entry r6 = (java.util.Map.Entry) r6
            org.json.JSONObject r8 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00f7 }
            r8.<init>()     // Catch:{ JSONException -> 0x00f7 }
            java.lang.String r9 = "type"
            java.lang.Object r10 = r6.getKey()     // Catch:{ JSONException -> 0x00f7 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x00f7 }
            java.lang.Object r9 = r6.getValue()     // Catch:{ JSONException -> 0x00f7 }
            org.json.JSONObject r8 = r8.put(r1, r9)     // Catch:{ JSONException -> 0x00f7 }
            java.lang.String r9 = "tokenizationSpecification"
            java.util.HashMap<java.lang.String, org.json.JSONObject> r10 = r12.mTokenizationSpecifications     // Catch:{ JSONException -> 0x00f7 }
            java.lang.Object r11 = r6.getKey()     // Catch:{ JSONException -> 0x00f7 }
            java.lang.Object r10 = r10.get(r11)     // Catch:{ JSONException -> 0x00f7 }
            org.json.JSONObject r8 = r8.put(r9, r10)     // Catch:{ JSONException -> 0x00f7 }
            java.lang.Object r9 = r6.getKey()     // Catch:{ JSONException -> 0x00f7 }
            java.lang.String r10 = "CARD"
            if (r9 != r10) goto L_0x00f2
            java.lang.Object r6 = r6.getValue()     // Catch:{ JSONException -> 0x00b7 }
            org.json.JSONObject r6 = (org.json.JSONObject) r6     // Catch:{ JSONException -> 0x00b7 }
            r6.get(r0)     // Catch:{ JSONException -> 0x00b7 }
            goto L_0x00f2
        L_0x00b7:
            org.json.JSONObject r6 = r8.getJSONObject(r1)     // Catch:{ JSONException -> 0x00f7 }
            java.lang.String r9 = "billingAddressRequired"
            java.lang.Boolean r10 = r12.isBillingAddressRequired()     // Catch:{ JSONException -> 0x00f7 }
            org.json.JSONObject r9 = r6.put(r9, r10)     // Catch:{ JSONException -> 0x00f7 }
            java.lang.String r10 = "allowPrepaidCards"
            java.lang.Boolean r11 = r12.getAllowPrepaidCards()     // Catch:{ JSONException -> 0x00f7 }
            r9.put(r10, r11)     // Catch:{ JSONException -> 0x00f7 }
            java.lang.Boolean r9 = r12.isBillingAddressRequired()     // Catch:{ JSONException -> 0x00f7 }
            boolean r9 = r9.booleanValue()     // Catch:{ JSONException -> 0x00f7 }
            if (r9 == 0) goto L_0x00f2
            org.json.JSONObject r9 = new org.json.JSONObject     // Catch:{ JSONException -> 0x00f7 }
            r9.<init>()     // Catch:{ JSONException -> 0x00f7 }
            java.lang.String r10 = "format"
            java.lang.String r11 = r12.billingAddressFormatToString()     // Catch:{ JSONException -> 0x00f7 }
            org.json.JSONObject r9 = r9.put(r10, r11)     // Catch:{ JSONException -> 0x00f7 }
            java.lang.Boolean r10 = r12.isPhoneNumberRequired()     // Catch:{ JSONException -> 0x00f7 }
            org.json.JSONObject r9 = r9.put(r7, r10)     // Catch:{ JSONException -> 0x00f7 }
            r6.put(r0, r9)     // Catch:{ JSONException -> 0x00f7 }
        L_0x00f2:
            r4.put(r8)     // Catch:{ JSONException -> 0x00f7 }
            goto L_0x0070
        L_0x00f7:
            goto L_0x0070
        L_0x00fa:
            org.json.JSONObject r0 = new org.json.JSONObject
            r0.<init>()
            java.lang.String r1 = r12.getGoogleMerchantId()     // Catch:{ JSONException -> 0x0125 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ JSONException -> 0x0125 }
            if (r1 != 0) goto L_0x0112
            java.lang.String r1 = "merchantId"
            java.lang.String r3 = r12.getGoogleMerchantId()     // Catch:{ JSONException -> 0x0125 }
            r0.put(r1, r3)     // Catch:{ JSONException -> 0x0125 }
        L_0x0112:
            java.lang.String r1 = r12.getGoogleMerchantName()     // Catch:{ JSONException -> 0x0125 }
            boolean r1 = android.text.TextUtils.isEmpty(r1)     // Catch:{ JSONException -> 0x0125 }
            if (r1 != 0) goto L_0x0125
            java.lang.String r1 = "merchantName"
            java.lang.String r3 = r12.getGoogleMerchantName()     // Catch:{ JSONException -> 0x0125 }
            r0.put(r1, r3)     // Catch:{ JSONException -> 0x0125 }
        L_0x0125:
            org.json.JSONObject r1 = new org.json.JSONObject
            r1.<init>()
            java.lang.String r3 = "apiVersion"
            r6 = 2
            org.json.JSONObject r3 = r1.put(r3, r6)     // Catch:{ JSONException -> 0x0175 }
            java.lang.String r6 = "apiVersionMinor"
            r7 = 0
            org.json.JSONObject r3 = r3.put(r6, r7)     // Catch:{ JSONException -> 0x0175 }
            java.lang.String r6 = "allowedPaymentMethods"
            org.json.JSONObject r3 = r3.put(r6, r4)     // Catch:{ JSONException -> 0x0175 }
            java.lang.String r4 = "emailRequired"
            java.lang.Boolean r6 = r12.isEmailRequired()     // Catch:{ JSONException -> 0x0175 }
            org.json.JSONObject r3 = r3.put(r4, r6)     // Catch:{ JSONException -> 0x0175 }
            java.lang.String r4 = "shippingAddressRequired"
            java.lang.Boolean r6 = r12.isShippingAddressRequired()     // Catch:{ JSONException -> 0x0175 }
            org.json.JSONObject r3 = r3.put(r4, r6)     // Catch:{ JSONException -> 0x0175 }
            java.lang.String r4 = "environment"
            java.lang.String r6 = r12.mEnvironment     // Catch:{ JSONException -> 0x0175 }
            org.json.JSONObject r3 = r3.put(r4, r6)     // Catch:{ JSONException -> 0x0175 }
            java.lang.String r4 = "merchantInfo"
            org.json.JSONObject r0 = r3.put(r4, r0)     // Catch:{ JSONException -> 0x0175 }
            java.lang.String r3 = "transactionInfo"
            r0.put(r3, r2)     // Catch:{ JSONException -> 0x0175 }
            java.lang.Boolean r0 = r12.isShippingAddressRequired()     // Catch:{ JSONException -> 0x0175 }
            boolean r0 = r0.booleanValue()     // Catch:{ JSONException -> 0x0175 }
            if (r0 == 0) goto L_0x0175
            java.lang.String r0 = "shippingAddressParameters"
            r1.put(r0, r5)     // Catch:{ JSONException -> 0x0175 }
        L_0x0175:
            java.lang.String r0 = r1.toString()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.braintreepayments.api.models.GooglePaymentRequest.toJson():java.lang.String");
    }

    private String totalPriceStatusToString() {
        int totalPriceStatus = getTransactionInfo().getTotalPriceStatus();
        if (totalPriceStatus != 1) {
            return totalPriceStatus != 2 ? "FINAL" : "ESTIMATED";
        }
        return "NOT_CURRENTLY_KNOWN";
    }

    public String billingAddressFormatToString() {
        Integer num = this.mBillingAddressFormat;
        return (num == null || num.intValue() != 1) ? "MIN" : "FULL";
    }

    public TransactionInfo getTransactionInfo() {
        return this.mTransactionInfo;
    }

    public Boolean isEmailRequired() {
        return this.mEmailRequired;
    }

    public Boolean isPhoneNumberRequired() {
        return this.mPhoneNumberRequired;
    }

    public Boolean isBillingAddressRequired() {
        return this.mBillingAddressRequired;
    }

    public Integer getBillingAddressFormat() {
        return this.mBillingAddressFormat;
    }

    public Boolean isShippingAddressRequired() {
        return this.mShippingAddressRequired;
    }

    public ShippingAddressRequirements getShippingAddressRequirements() {
        return this.mShippingAddressRequirements;
    }

    public Boolean getAllowPrepaidCards() {
        return this.mAllowPrepaidCards;
    }

    public Boolean isPayPalEnabled() {
        return Boolean.valueOf(this.mPayPalEnabled);
    }

    public JSONObject getAllowedPaymentMethod(String str) {
        return this.mAllowedPaymentMethods.get(str);
    }

    public JSONObject getTokenizationSpecificationForType(String str) {
        return this.mTokenizationSpecifications.get(str);
    }

    public JSONArray getAllowedAuthMethodsForType(String str) {
        return this.mAllowedAuthMethods.get(str);
    }

    public JSONArray getAllowedCardNetworksForType(String str) {
        return this.mAllowedCardNetworks.get(str);
    }

    public String getEnvironment() {
        return this.mEnvironment;
    }

    public String getGoogleMerchantId() {
        return this.mGoogleMerchantId;
    }

    public String getGoogleMerchantName() {
        return this.mGoogleMerchantName;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mTransactionInfo, i);
        Boolean bool = this.mEmailRequired;
        int i2 = 2;
        parcel.writeByte((byte) (bool == null ? 0 : bool.booleanValue() ? 1 : 2));
        Boolean bool2 = this.mPhoneNumberRequired;
        parcel.writeByte((byte) (bool2 == null ? 0 : bool2.booleanValue() ? 1 : 2));
        Boolean bool3 = this.mBillingAddressRequired;
        parcel.writeByte((byte) (bool3 == null ? 0 : bool3.booleanValue() ? 1 : 2));
        if (this.mBillingAddressFormat == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(this.mBillingAddressFormat.intValue());
        }
        Boolean bool4 = this.mShippingAddressRequired;
        parcel.writeByte((byte) (bool4 == null ? 0 : bool4.booleanValue() ? 1 : 2));
        parcel.writeParcelable(this.mShippingAddressRequirements, i);
        Boolean bool5 = this.mAllowPrepaidCards;
        if (bool5 == null) {
            i2 = 0;
        } else if (bool5.booleanValue()) {
            i2 = 1;
        }
        parcel.writeByte((byte) i2);
        parcel.writeString(this.mEnvironment);
        parcel.writeString(this.mGoogleMerchantId);
        parcel.writeString(this.mGoogleMerchantName);
    }

    protected GooglePaymentRequest(Parcel parcel) {
        Boolean bool;
        Boolean bool2;
        Boolean bool3;
        Boolean bool4;
        Boolean bool5 = null;
        this.mEmailRequired = null;
        this.mPhoneNumberRequired = null;
        this.mBillingAddressRequired = null;
        this.mShippingAddressRequired = null;
        this.mAllowPrepaidCards = null;
        boolean z = true;
        this.mPayPalEnabled = true;
        this.mAllowedPaymentMethods = new HashMap<>();
        this.mTokenizationSpecifications = new HashMap<>();
        this.mAllowedAuthMethods = new HashMap<>();
        this.mAllowedCardNetworks = new HashMap<>();
        this.mTransactionInfo = parcel.readParcelable(TransactionInfo.class.getClassLoader());
        byte readByte = parcel.readByte();
        if (readByte == 0) {
            bool = null;
        } else {
            bool = Boolean.valueOf(readByte == 1);
        }
        this.mEmailRequired = bool;
        byte readByte2 = parcel.readByte();
        if (readByte2 == 0) {
            bool2 = null;
        } else {
            bool2 = Boolean.valueOf(readByte2 == 1);
        }
        this.mPhoneNumberRequired = bool2;
        byte readByte3 = parcel.readByte();
        if (readByte3 == 0) {
            bool3 = null;
        } else {
            bool3 = Boolean.valueOf(readByte3 == 1);
        }
        this.mBillingAddressRequired = bool3;
        if (parcel.readByte() == 0) {
            this.mBillingAddressFormat = null;
        } else {
            this.mBillingAddressFormat = Integer.valueOf(parcel.readInt());
        }
        byte readByte4 = parcel.readByte();
        if (readByte4 == 0) {
            bool4 = null;
        } else {
            bool4 = Boolean.valueOf(readByte4 == 1);
        }
        this.mShippingAddressRequired = bool4;
        this.mShippingAddressRequirements = parcel.readParcelable(ShippingAddressRequirements.class.getClassLoader());
        byte readByte5 = parcel.readByte();
        if (readByte5 != 0) {
            bool5 = Boolean.valueOf(readByte5 != 1 ? false : z);
        }
        this.mAllowPrepaidCards = bool5;
        this.mEnvironment = parcel.readString();
        this.mGoogleMerchantId = parcel.readString();
        this.mGoogleMerchantName = parcel.readString();
    }
}
