package com.stripe.android.model;

import android.os.Parcel;
import android.os.Parcelable;
import com.stripe.android.StripeNetworkUtils;
import com.stripe.android.utils.ObjectUtils;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONObject;

public class ShippingInformation extends StripeJsonModel implements Parcelable {
    public static final Parcelable.Creator<ShippingInformation> CREATOR = new Parcelable.Creator<ShippingInformation>() {
        public ShippingInformation createFromParcel(Parcel parcel) {
            return new ShippingInformation(parcel);
        }

        public ShippingInformation[] newArray(int i) {
            return new ShippingInformation[i];
        }
    };
    private static final String FIELD_ADDRESS = "address";
    private static final String FIELD_NAME = "name";
    private static final String FIELD_PHONE = "phone";
    private final Address mAddress;
    private final String mName;
    private final String mPhone;

    public int describeContents() {
        return 0;
    }

    public ShippingInformation() {
        this((Address) null, (String) null, (String) null);
    }

    public ShippingInformation(Address address, String str, String str2) {
        this.mAddress = address;
        this.mName = str;
        this.mPhone = str2;
    }

    protected ShippingInformation(Parcel parcel) {
        this.mAddress = (Address) parcel.readParcelable(Address.class.getClassLoader());
        this.mName = parcel.readString();
        this.mPhone = parcel.readString();
    }

    public Address getAddress() {
        return this.mAddress;
    }

    public String getName() {
        return this.mName;
    }

    public String getPhone() {
        return this.mPhone;
    }

    public static ShippingInformation fromJson(JSONObject jSONObject) {
        if (jSONObject == null) {
            return null;
        }
        return new ShippingInformation(Address.fromJson(jSONObject.optJSONObject(FIELD_ADDRESS)), StripeJsonUtils.optString(jSONObject, "name"), StripeJsonUtils.optString(jSONObject, "phone"));
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        StripeJsonUtils.putStringIfNotNull(jSONObject, "name", this.mName);
        StripeJsonUtils.putStringIfNotNull(jSONObject, "phone", this.mPhone);
        putStripeJsonModelIfNotNull(jSONObject, FIELD_ADDRESS, this.mAddress);
        return jSONObject;
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put("name", this.mName);
        hashMap.put("phone", this.mPhone);
        putStripeJsonModelMapIfNotNull(hashMap, FIELD_ADDRESS, this.mAddress);
        StripeNetworkUtils.removeNullAndEmptyParams(hashMap);
        return hashMap;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(this.mAddress, i);
        parcel.writeString(this.mName);
        parcel.writeString(this.mPhone);
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof ShippingInformation) && typedEquals((ShippingInformation) obj));
    }

    private boolean typedEquals(ShippingInformation shippingInformation) {
        return ObjectUtils.equals(this.mAddress, shippingInformation.mAddress) && ObjectUtils.equals(this.mName, shippingInformation.mName) && ObjectUtils.equals(this.mPhone, shippingInformation.mPhone);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mAddress, this.mName, this.mPhone);
    }
}
