package com.braintreepayments.api.exceptions;

import android.os.Parcel;
import android.os.Parcelable;
import com.braintreepayments.api.Json;
import com.braintreepayments.api.internal.GraphQLConstants;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class BraintreeError implements Parcelable {
    public static final Parcelable.Creator<BraintreeError> CREATOR = new Parcelable.Creator<BraintreeError>() {
        public BraintreeError createFromParcel(Parcel parcel) {
            return new BraintreeError(parcel);
        }

        public BraintreeError[] newArray(int i) {
            return new BraintreeError[i];
        }
    };
    private static final String FIELD_ERRORS_KEY = "fieldErrors";
    private static final String FIELD_KEY = "field";
    private static final String MESSAGE_KEY = "message";
    private String mField;
    private List<BraintreeError> mFieldErrors;
    private String mMessage;

    public int describeContents() {
        return 0;
    }

    public static List<BraintreeError> fromJsonArray(JSONArray jSONArray) {
        if (jSONArray == null) {
            jSONArray = new JSONArray();
        }
        ArrayList arrayList = new ArrayList();
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                arrayList.add(fromJson(jSONArray.getJSONObject(i)));
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }

    protected static List<BraintreeError> fromGraphQLJsonArray(JSONArray jSONArray) {
        ArrayList arrayList = new ArrayList();
        if (jSONArray == null) {
            return arrayList;
        }
        for (int i = 0; i < jSONArray.length(); i++) {
            try {
                JSONObject jSONObject = jSONArray.getJSONObject(i);
                JSONObject optJSONObject = jSONObject.optJSONObject(GraphQLConstants.Keys.EXTENSIONS);
                if (optJSONObject != null) {
                    if (GraphQLConstants.ErrorTypes.USER.equals(optJSONObject.optString(GraphQLConstants.Keys.ERROR_TYPE))) {
                        ArrayList arrayList2 = new ArrayList();
                        JSONArray jSONArray2 = optJSONObject.getJSONArray(GraphQLConstants.Keys.INPUT_PATH);
                        for (int i2 = 1; i2 < jSONArray2.length(); i2++) {
                            arrayList2.add(jSONArray2.getString(i2));
                        }
                        addGraphQLFieldError(arrayList2, jSONObject, arrayList);
                    }
                }
            } catch (JSONException unused) {
            }
        }
        return arrayList;
    }

    public static BraintreeError fromJson(JSONObject jSONObject) {
        BraintreeError braintreeError = new BraintreeError();
        braintreeError.mField = Json.optString(jSONObject, FIELD_KEY, (String) null);
        braintreeError.mMessage = Json.optString(jSONObject, "message", (String) null);
        braintreeError.mFieldErrors = fromJsonArray(jSONObject.optJSONArray(FIELD_ERRORS_KEY));
        return braintreeError;
    }

    private static void addGraphQLFieldError(List<String> list, JSONObject jSONObject, List<BraintreeError> list2) throws JSONException {
        String str = list.get(0);
        if (list.size() == 1) {
            BraintreeError braintreeError = new BraintreeError();
            braintreeError.mField = str;
            braintreeError.mMessage = jSONObject.getString("message");
            braintreeError.mFieldErrors = new ArrayList();
            list2.add(braintreeError);
            return;
        }
        BraintreeError braintreeError2 = null;
        List<String> subList = list.subList(1, list.size());
        for (BraintreeError next : list2) {
            if (next.mField.equals(str)) {
                braintreeError2 = next;
            }
        }
        if (braintreeError2 == null) {
            braintreeError2 = new BraintreeError();
            braintreeError2.mField = str;
            braintreeError2.mFieldErrors = new ArrayList();
            list2.add(braintreeError2);
        }
        addGraphQLFieldError(subList, jSONObject, braintreeError2.mFieldErrors);
    }

    public String getMessage() {
        return this.mMessage;
    }

    public String getField() {
        return this.mField;
    }

    public List<BraintreeError> getFieldErrors() {
        return this.mFieldErrors;
    }

    public BraintreeError errorFor(String str) {
        BraintreeError errorFor;
        List<BraintreeError> list = this.mFieldErrors;
        if (list == null) {
            return null;
        }
        for (BraintreeError next : list) {
            if (next.getField().equals(str)) {
                return next;
            }
            if (next.getFieldErrors() != null && (errorFor = next.errorFor(str)) != null) {
                return errorFor;
            }
        }
        return null;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("BraintreeError for ");
        sb.append(this.mField);
        sb.append(": ");
        sb.append(this.mMessage);
        sb.append(" -> ");
        List<BraintreeError> list = this.mFieldErrors;
        sb.append(list != null ? list.toString() : "");
        return sb.toString();
    }

    public BraintreeError() {
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.mField);
        parcel.writeString(this.mMessage);
        parcel.writeTypedList(this.mFieldErrors);
    }

    protected BraintreeError(Parcel parcel) {
        this.mField = parcel.readString();
        this.mMessage = parcel.readString();
        this.mFieldErrors = parcel.createTypedArrayList(CREATOR);
    }
}
