package com.braintreepayments.api.models;

import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class PostalAddressParser {
    public static final String COUNTRY_CODE_ALPHA_2_KEY = "country";
    public static final String COUNTRY_CODE_KEY = "countryCode";
    public static final String COUNTRY_CODE_UNDERSCORE_KEY = "country_code";
    public static final String EXTENDED_ADDRESS_KEY = "street2";
    public static final String LINE_1_KEY = "line1";
    public static final String LINE_2_KEY = "line2";
    public static final String LOCALITY_KEY = "city";
    public static final String POSTAL_CODE_KEY = "postalCode";
    public static final String POSTAL_CODE_UNDERSCORE_KEY = "postal_code";
    public static final String RECIPIENT_NAME_KEY = "recipientName";
    public static final String RECIPIENT_NAME_UNDERSCORE_KEY = "recipient_name";
    public static final String REGION_KEY = "state";
    public static final String STREET_ADDRESS_KEY = "street1";
    public static final String USER_ADDRESS_ADDRESS_1_KEY = "address1";
    public static final String USER_ADDRESS_ADDRESS_2_KEY = "address2";
    public static final String USER_ADDRESS_ADDRESS_3_KEY = "address3";
    public static final String USER_ADDRESS_ADDRESS_4_KEY = "address4";
    public static final String USER_ADDRESS_ADDRESS_5_KEY = "address5";
    public static final String USER_ADDRESS_ADMINISTRATIVE_AREA_KEY = "administrativeArea";
    public static final String USER_ADDRESS_COUNTRY_CODE_KEY = "countryCode";
    public static final String USER_ADDRESS_LOCALITY_KEY = "locality";
    public static final String USER_ADDRESS_NAME_KEY = "name";
    public static final String USER_ADDRESS_PHONE_NUMBER_KEY = "phoneNumber";
    public static final String USER_ADDRESS_POSTAL_CODE_KEY = "postalCode";
    public static final String USER_ADDRESS_SORTING_CODE_KEY = "sortingCode";

    public static PostalAddress fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return new PostalAddress();
        }
        String optString = Json.optString(jSONObject, STREET_ADDRESS_KEY, (String) null);
        String optString2 = Json.optString(jSONObject, EXTENDED_ADDRESS_KEY, (String) null);
        String optString3 = Json.optString(jSONObject, "country", (String) null);
        if (optString == null) {
            optString = Json.optString(jSONObject, LINE_1_KEY, (String) null);
        }
        if (optString2 == null) {
            optString2 = Json.optString(jSONObject, LINE_2_KEY, (String) null);
        }
        if (optString3 == null) {
            optString3 = Json.optString(jSONObject, "countryCode", (String) null);
        }
        if (optString != null || Json.optString(jSONObject, "name", (String) null) == null) {
            return new PostalAddress().recipientName(Json.optString(jSONObject, RECIPIENT_NAME_KEY, (String) null)).streetAddress(optString).extendedAddress(optString2).locality(Json.optString(jSONObject, "city", (String) null)).region(Json.optString(jSONObject, "state", (String) null)).postalCode(Json.optString(jSONObject, "postalCode", (String) null)).countryCodeAlpha2(optString3);
        }
        return fromUserAddressJson(jSONObject);
    }

    public static PostalAddress fromUserAddressJson(JSONObject jSONObject) {
        PostalAddress postalAddress = new PostalAddress();
        postalAddress.recipientName(Json.optString(jSONObject, "name", "")).phoneNumber(Json.optString(jSONObject, USER_ADDRESS_PHONE_NUMBER_KEY, "")).streetAddress(Json.optString(jSONObject, USER_ADDRESS_ADDRESS_1_KEY, "")).extendedAddress(formatExtendedUserAddress(jSONObject)).locality(Json.optString(jSONObject, USER_ADDRESS_LOCALITY_KEY, "")).region(Json.optString(jSONObject, USER_ADDRESS_ADMINISTRATIVE_AREA_KEY, "")).countryCodeAlpha2(Json.optString(jSONObject, "countryCode", "")).postalCode(Json.optString(jSONObject, "postalCode", "")).sortingCode(Json.optString(jSONObject, USER_ADDRESS_SORTING_CODE_KEY, ""));
        return postalAddress;
    }

    private static String formatExtendedUserAddress(JSONObject jSONObject) {
        return ("" + Json.optString(jSONObject, USER_ADDRESS_ADDRESS_2_KEY, "") + "\n" + Json.optString(jSONObject, USER_ADDRESS_ADDRESS_3_KEY, "") + "\n" + Json.optString(jSONObject, USER_ADDRESS_ADDRESS_4_KEY, "") + "\n" + Json.optString(jSONObject, USER_ADDRESS_ADDRESS_5_KEY, "")).trim();
    }
}
