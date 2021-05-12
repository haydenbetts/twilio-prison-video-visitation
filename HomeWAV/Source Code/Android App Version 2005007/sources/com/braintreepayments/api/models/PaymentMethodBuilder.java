package com.braintreepayments.api.models;

import android.content.Context;
import android.os.Parcel;
import com.braintreepayments.api.exceptions.BraintreeException;
import com.braintreepayments.api.internal.GraphQLConstants;
import org.json.JSONException;
import org.json.JSONObject;

public abstract class PaymentMethodBuilder<T> {
    private static final String GRAPHQL_CLIENT_SDK_METADATA_KEY = "clientSdkMetadata";
    protected static final String OPERATION_NAME_KEY = "operationName";
    protected static final String OPTIONS_KEY = "options";
    private static final String VALIDATE_KEY = "validate";
    private String mIntegration = getDefaultIntegration();
    private String mSessionId;
    private String mSource = getDefaultSource();
    private boolean mValidate;
    private boolean mValidateSet;

    /* access modifiers changed from: protected */
    public abstract void build(JSONObject jSONObject, JSONObject jSONObject2) throws JSONException;

    /* access modifiers changed from: protected */
    public abstract void buildGraphQL(Context context, JSONObject jSONObject, JSONObject jSONObject2) throws BraintreeException, JSONException;

    public abstract String getApiPath();

    /* access modifiers changed from: protected */
    public String getDefaultIntegration() {
        return "custom";
    }

    /* access modifiers changed from: protected */
    public String getDefaultSource() {
        return "form";
    }

    public abstract String getResponsePaymentMethodType();

    public PaymentMethodBuilder() {
    }

    public T integration(String str) {
        this.mIntegration = str;
        return this;
    }

    public T source(String str) {
        this.mSource = str;
        return this;
    }

    public T validate(boolean z) {
        this.mValidate = z;
        this.mValidateSet = true;
        return this;
    }

    public T setSessionId(String str) {
        this.mSessionId = str;
        return this;
    }

    public String build() {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put(MetadataBuilder.META_KEY, new MetadataBuilder().sessionId(this.mSessionId).source(this.mSource).integration(this.mIntegration).build());
            if (this.mValidateSet) {
                jSONObject2.put(VALIDATE_KEY, this.mValidate);
                jSONObject3.put(OPTIONS_KEY, jSONObject2);
            }
            build(jSONObject, jSONObject3);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    public String buildGraphQL(Context context, Authorization authorization) throws BraintreeException {
        JSONObject jSONObject = new JSONObject();
        JSONObject jSONObject2 = new JSONObject();
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject.put(GRAPHQL_CLIENT_SDK_METADATA_KEY, new MetadataBuilder().sessionId(this.mSessionId).source(this.mSource).integration(this.mIntegration).build());
            JSONObject jSONObject4 = new JSONObject();
            if (this.mValidateSet) {
                jSONObject4.put(VALIDATE_KEY, this.mValidate);
            } else if (authorization instanceof ClientToken) {
                jSONObject4.put(VALIDATE_KEY, true);
            } else if (authorization instanceof TokenizationKey) {
                jSONObject4.put(VALIDATE_KEY, false);
            }
            jSONObject2.put(OPTIONS_KEY, jSONObject4);
            jSONObject3.put(GraphQLConstants.Keys.INPUT, jSONObject2);
            buildGraphQL(context, jSONObject, jSONObject3);
            jSONObject.put(GraphQLConstants.Keys.VARIABLES, jSONObject3);
        } catch (JSONException unused) {
        }
        return jSONObject.toString();
    }

    protected PaymentMethodBuilder(Parcel parcel) {
        this.mIntegration = parcel.readString();
        this.mSource = parcel.readString();
        boolean z = true;
        this.mValidate = parcel.readByte() > 0;
        this.mValidateSet = parcel.readByte() <= 0 ? false : z;
        this.mSessionId = parcel.readString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mIntegration);
        parcel.writeString(this.mSource);
        parcel.writeByte(this.mValidate ? (byte) 1 : 0);
        parcel.writeByte(this.mValidateSet ? (byte) 1 : 0);
        parcel.writeString(this.mSessionId);
    }
}
