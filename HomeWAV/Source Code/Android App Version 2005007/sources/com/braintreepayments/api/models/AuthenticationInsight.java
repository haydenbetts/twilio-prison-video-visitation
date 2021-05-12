package com.braintreepayments.api.models;

import android.os.Parcel;
import android.os.Parcelable;
import com.braintreepayments.api.Json;
import org.json.JSONObject;

public class AuthenticationInsight implements Parcelable {
    public static final Parcelable.Creator<AuthenticationInsight> CREATOR = new Parcelable.Creator<AuthenticationInsight>() {
        public AuthenticationInsight createFromParcel(Parcel parcel) {
            return new AuthenticationInsight(parcel);
        }

        public AuthenticationInsight[] newArray(int i) {
            return new AuthenticationInsight[i];
        }
    };
    private static final String GRAPHQL_REGULATION_ENVIRONMENT_KEY = "customerAuthenticationRegulationEnvironment";
    private static final String REST_REGULATION_ENVIRONMENT_KEY = "regulationEnvironment";
    private String mRegulationEnvironment;

    public int describeContents() {
        return 0;
    }

    static AuthenticationInsight fromJson(JSONObject jSONObject) {
        String str;
        if (jSONObject == null) {
            return null;
        }
        AuthenticationInsight authenticationInsight = new AuthenticationInsight();
        if (jSONObject.has(GRAPHQL_REGULATION_ENVIRONMENT_KEY)) {
            str = Json.optString(jSONObject, GRAPHQL_REGULATION_ENVIRONMENT_KEY, (String) null);
        } else {
            str = Json.optString(jSONObject, REST_REGULATION_ENVIRONMENT_KEY, (String) null);
        }
        if ("psdtwo".equalsIgnoreCase(str)) {
            str = "psd2";
        }
        if (str != null) {
            str = str.toLowerCase();
        }
        authenticationInsight.mRegulationEnvironment = str;
        return authenticationInsight;
    }

    public String getRegulationEnvironment() {
        return this.mRegulationEnvironment;
    }

    public AuthenticationInsight() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mRegulationEnvironment);
    }

    private AuthenticationInsight(Parcel parcel) {
        this.mRegulationEnvironment = parcel.readString();
    }
}
