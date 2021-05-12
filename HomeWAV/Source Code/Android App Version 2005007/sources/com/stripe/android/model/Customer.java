package com.stripe.android.model;

import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.utils.ObjectUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Customer extends StripeJsonModel {
    private static final String FIELD_DATA = "data";
    private static final String FIELD_DEFAULT_SOURCE = "default_source";
    private static final String FIELD_HAS_MORE = "has_more";
    private static final String FIELD_ID = "id";
    private static final String FIELD_OBJECT = "object";
    private static final String FIELD_SHIPPING = "shipping";
    private static final String FIELD_SOURCES = "sources";
    private static final String FIELD_TOTAL_COUNT = "total_count";
    private static final String FIELD_URL = "url";
    private static final String VALUE_APPLE_PAY = "apple_pay";
    private static final String VALUE_CUSTOMER = "customer";
    private static final String VALUE_LIST = "list";
    private final String mDefaultSource;
    private final Boolean mHasMore;
    private final String mId;
    private final ShippingInformation mShippingInformation;
    private final List<CustomerSource> mSources;
    private final Integer mTotalCount;
    private final String mUrl;

    private Customer(String str, String str2, ShippingInformation shippingInformation, List<CustomerSource> list, Boolean bool, Integer num, String str3) {
        this.mId = str;
        this.mDefaultSource = str2;
        this.mShippingInformation = shippingInformation;
        this.mSources = list;
        this.mHasMore = bool;
        this.mTotalCount = num;
        this.mUrl = str3;
    }

    public String getId() {
        return this.mId;
    }

    public String getDefaultSource() {
        return this.mDefaultSource;
    }

    public ShippingInformation getShippingInformation() {
        return this.mShippingInformation;
    }

    public List<CustomerSource> getSources() {
        return this.mSources;
    }

    public Boolean getHasMore() {
        return this.mHasMore;
    }

    public Integer getTotalCount() {
        return this.mTotalCount;
    }

    public String getUrl() {
        return this.mUrl;
    }

    public CustomerSource getSourceById(String str) {
        for (CustomerSource next : this.mSources) {
            if (str.equals(next.getId())) {
                return next;
            }
        }
        return null;
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.putStringIfNotNull(jSONObject, "id", this.mId);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_OBJECT, VALUE_CUSTOMER);
        StripeJsonUtils.putStringIfNotNull(jSONObject, FIELD_DEFAULT_SOURCE, this.mDefaultSource);
        StripeJsonModel.putStripeJsonModelIfNotNull(jSONObject, FIELD_SHIPPING, this.mShippingInformation);
        JSONObject jSONObject2 = new JSONObject();
        StripeJsonUtils.putStringIfNotNull(jSONObject2, FIELD_OBJECT, VALUE_LIST);
        StripeJsonUtils.putBooleanIfNotNull(jSONObject2, FIELD_HAS_MORE, this.mHasMore);
        StripeJsonUtils.putIntegerIfNotNull(jSONObject2, FIELD_TOTAL_COUNT, this.mTotalCount);
        putStripeJsonModelListIfNotNull(jSONObject2, "data", (List<? extends StripeJsonModel>) this.mSources);
        StripeJsonUtils.putStringIfNotNull(jSONObject2, "url", this.mUrl);
        StripeJsonUtils.putObjectIfNotNull(jSONObject, FIELD_SOURCES, jSONObject2);
        return jSONObject;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("id", this.mId);
        hashMap.put(FIELD_OBJECT, VALUE_CUSTOMER);
        hashMap.put(FIELD_DEFAULT_SOURCE, this.mDefaultSource);
        StripeJsonModel.putStripeJsonModelMapIfNotNull(hashMap, FIELD_SHIPPING, this.mShippingInformation);
        HashMap hashMap2 = new HashMap();
        hashMap2.put(FIELD_HAS_MORE, this.mHasMore);
        hashMap2.put(FIELD_TOTAL_COUNT, this.mTotalCount);
        hashMap2.put(FIELD_OBJECT, VALUE_LIST);
        hashMap2.put("url", this.mUrl);
        StripeJsonModel.putStripeJsonModelListIfNotNull((Map<String, Object>) hashMap2, "data", (List<? extends StripeJsonModel>) this.mSources);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap2);
        hashMap.put(FIELD_SOURCES, hashMap2);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    public static Customer fromString(String str) {
        if (str == null) {
            return null;
        }
        try {
            return fromJson(new JSONObject(str));
        } catch (JSONException unused) {
            return null;
        }
    }

    public static Customer fromJson(JSONObject jSONObject) {
        String str;
        Integer num;
        Boolean bool;
        if (!VALUE_CUSTOMER.equals(StripeJsonUtils.optString(jSONObject, FIELD_OBJECT))) {
            return null;
        }
        String optString = StripeJsonUtils.optString(jSONObject, "id");
        String optString2 = StripeJsonUtils.optString(jSONObject, FIELD_DEFAULT_SOURCE);
        ShippingInformation fromJson = ShippingInformation.fromJson(jSONObject.optJSONObject(FIELD_SHIPPING));
        JSONObject optJSONObject = jSONObject.optJSONObject(FIELD_SOURCES);
        ArrayList arrayList = new ArrayList();
        if (optJSONObject == null || !VALUE_LIST.equals(StripeJsonUtils.optString(optJSONObject, FIELD_OBJECT))) {
            bool = null;
            num = null;
            str = null;
        } else {
            Boolean optBoolean = StripeJsonUtils.optBoolean(optJSONObject, FIELD_HAS_MORE);
            Integer optInteger = StripeJsonUtils.optInteger(optJSONObject, FIELD_TOTAL_COUNT);
            String optString3 = StripeJsonUtils.optString(optJSONObject, "url");
            JSONArray optJSONArray = optJSONObject.optJSONArray("data");
            for (int i = 0; i < optJSONArray.length(); i++) {
                try {
                    CustomerSource fromJson2 = CustomerSource.fromJson(optJSONArray.getJSONObject(i));
                    if (fromJson2 != null) {
                        if (!VALUE_APPLE_PAY.equals(fromJson2.getTokenizationMethod())) {
                            arrayList.add(fromJson2);
                        }
                    }
                } catch (JSONException unused) {
                }
            }
            num = optInteger;
            str = optString3;
            bool = optBoolean;
        }
        return new Customer(optString, optString2, fromJson, arrayList, bool, num, str);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof Customer) && typedEquals((Customer) obj));
    }

    private boolean typedEquals(Customer customer) {
        return ObjectUtils.equals(this.mId, customer.mId) && ObjectUtils.equals(this.mDefaultSource, customer.mDefaultSource) && ObjectUtils.equals(this.mShippingInformation, customer.mShippingInformation) && ObjectUtils.equals(this.mSources, customer.mSources) && ObjectUtils.equals(this.mHasMore, customer.mHasMore) && ObjectUtils.equals(this.mTotalCount, customer.mTotalCount) && ObjectUtils.equals(this.mUrl, customer.mUrl);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mId, this.mDefaultSource, this.mShippingInformation, this.mSources, this.mHasMore, this.mTotalCount, this.mUrl);
    }
}
