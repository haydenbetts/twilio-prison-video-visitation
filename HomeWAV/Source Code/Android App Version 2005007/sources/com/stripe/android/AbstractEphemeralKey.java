package com.stripe.android;

import android.os.Parcel;
import android.os.Parcelable;
import com.stripe.android.model.StripeJsonModel;
import com.stripe.android.utils.ObjectUtils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

abstract class AbstractEphemeralKey extends StripeJsonModel implements Parcelable {
    static final String FIELD_ASSOCIATED_OBJECTS = "associated_objects";
    static final String FIELD_CREATED = "created";
    static final String FIELD_EXPIRES = "expires";
    static final String FIELD_ID = "id";
    static final String FIELD_LIVEMODE = "livemode";
    static final String FIELD_OBJECT = "object";
    static final String FIELD_SECRET = "secret";
    static final String FIELD_TYPE = "type";
    static final String NULL = "null";
    private final long mCreated;
    private final long mExpires;
    private final String mId;
    private final boolean mLiveMode;
    private final String mObject;
    final String mObjectId;
    private final String mSecret;
    private final String mType;

    public int describeContents() {
        return 0;
    }

    AbstractEphemeralKey(Parcel parcel) {
        this.mCreated = parcel.readLong();
        this.mObjectId = parcel.readString();
        this.mExpires = parcel.readLong();
        this.mId = parcel.readString();
        this.mLiveMode = parcel.readInt() != 1 ? false : true;
        this.mObject = parcel.readString();
        this.mSecret = parcel.readString();
        this.mType = parcel.readString();
    }

    AbstractEphemeralKey(long j, String str, long j2, String str2, boolean z, String str3, String str4, String str5) {
        this.mCreated = j;
        this.mObjectId = str;
        this.mExpires = j2;
        this.mId = str2;
        this.mLiveMode = z;
        this.mObject = str3;
        this.mSecret = str4;
        this.mType = str5;
    }

    AbstractEphemeralKey(JSONObject jSONObject) throws JSONException {
        this.mCreated = jSONObject.getLong(FIELD_CREATED);
        this.mExpires = jSONObject.getLong(FIELD_EXPIRES);
        this.mId = jSONObject.getString("id");
        this.mLiveMode = jSONObject.getBoolean(FIELD_LIVEMODE);
        this.mObject = jSONObject.getString(FIELD_OBJECT);
        this.mSecret = jSONObject.getString(FIELD_SECRET);
        JSONObject jSONObject2 = jSONObject.getJSONArray(FIELD_ASSOCIATED_OBJECTS).getJSONObject(0);
        this.mType = jSONObject2.getString("type");
        this.mObjectId = jSONObject2.getString("id");
    }

    public JSONObject toJson() {
        JSONObject jSONObject = new JSONObject();
        JSONArray jSONArray = new JSONArray();
        JSONObject jSONObject2 = new JSONObject();
        try {
            jSONObject.put(FIELD_CREATED, this.mCreated);
            jSONObject.put(FIELD_EXPIRES, this.mExpires);
            jSONObject.put(FIELD_OBJECT, this.mObject);
            jSONObject.put("id", this.mId);
            jSONObject.put(FIELD_SECRET, this.mSecret);
            jSONObject.put(FIELD_LIVEMODE, this.mLiveMode);
            jSONObject2.put("type", this.mType);
            jSONObject2.put("id", this.mObjectId);
            jSONArray.put(jSONObject2);
            jSONObject.put(FIELD_ASSOCIATED_OBJECTS, jSONArray);
            return jSONObject;
        } catch (JSONException unused) {
            throw new IllegalArgumentException("JSONObject creation exception thrown.");
        }
    }

    public Map<String, Object> toMap() {
        HashMap hashMap = new HashMap();
        hashMap.put(FIELD_CREATED, Long.valueOf(this.mCreated));
        hashMap.put(FIELD_EXPIRES, Long.valueOf(this.mExpires));
        hashMap.put(FIELD_OBJECT, this.mObject);
        hashMap.put("id", this.mId);
        hashMap.put(FIELD_SECRET, this.mSecret);
        hashMap.put(FIELD_LIVEMODE, Boolean.valueOf(this.mLiveMode));
        ArrayList arrayList = new ArrayList();
        HashMap hashMap2 = new HashMap();
        hashMap2.put("id", this.mObjectId);
        hashMap2.put("type", this.mType);
        arrayList.add(hashMap2);
        hashMap.put(FIELD_ASSOCIATED_OBJECTS, arrayList);
        return hashMap;
    }

    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(this.mCreated);
        parcel.writeString(this.mObjectId);
        parcel.writeLong(this.mExpires);
        parcel.writeString(this.mId);
        parcel.writeInt(this.mLiveMode ? 1 : 0);
        parcel.writeString(this.mObject);
        parcel.writeString(this.mSecret);
        parcel.writeString(this.mType);
    }

    /* access modifiers changed from: package-private */
    public long getCreated() {
        return this.mCreated;
    }

    /* access modifiers changed from: package-private */
    public long getExpires() {
        return this.mExpires;
    }

    /* access modifiers changed from: package-private */
    public String getId() {
        return this.mId;
    }

    /* access modifiers changed from: package-private */
    public boolean isLiveMode() {
        return this.mLiveMode;
    }

    /* access modifiers changed from: package-private */
    public String getObject() {
        return this.mObject;
    }

    /* access modifiers changed from: package-private */
    public String getSecret() {
        return this.mSecret;
    }

    /* access modifiers changed from: package-private */
    public String getType() {
        return this.mType;
    }

    protected static <TEphemeralKey extends AbstractEphemeralKey> TEphemeralKey fromString(String str, Class cls) throws JSONException {
        if (str != null) {
            return fromJson(new JSONObject(str), cls);
        }
        throw new IllegalArgumentException("Attempted to instantiate " + cls.getSimpleName() + " with null raw key");
    }

    protected static <TEphemeralKey extends AbstractEphemeralKey> TEphemeralKey fromJson(JSONObject jSONObject, Class cls) {
        if (jSONObject != null) {
            try {
                return (AbstractEphemeralKey) cls.getConstructor(new Class[]{JSONObject.class}).newInstance(new Object[]{jSONObject});
            } catch (InstantiationException e) {
                throw new IllegalArgumentException("Exception instantiating " + cls.getSimpleName(), e);
            } catch (IllegalAccessException e2) {
                throw new IllegalArgumentException("Exception instantiating " + cls.getSimpleName(), e2);
            } catch (InvocationTargetException e3) {
                if (e3.getTargetException() != null) {
                    throw new IllegalArgumentException("Improperly formatted JSON for ephemeral key " + cls.getSimpleName() + " - " + e3.getTargetException().getMessage(), e3.getTargetException());
                }
                throw new IllegalArgumentException("Improperly formatted JSON for ephemeral key " + cls.getSimpleName(), e3);
            } catch (NoSuchMethodException e4) {
                throw new IllegalArgumentException("Class " + cls.getSimpleName() + " does not have an accessible (JSONObject) constructor", e4);
            }
        } else {
            throw new IllegalArgumentException("Exception instantiating " + cls.getSimpleName() + " null JSON");
        }
    }

    public boolean equals(Object obj) {
        return this == obj || ((obj instanceof AbstractEphemeralKey) && typedEquals((AbstractEphemeralKey) obj));
    }

    private boolean typedEquals(AbstractEphemeralKey abstractEphemeralKey) {
        return ObjectUtils.equals(this.mObjectId, abstractEphemeralKey.mObjectId) && this.mCreated == abstractEphemeralKey.mCreated && this.mExpires == abstractEphemeralKey.mExpires && ObjectUtils.equals(this.mId, abstractEphemeralKey.mId) && this.mLiveMode == abstractEphemeralKey.mLiveMode && ObjectUtils.equals(this.mObject, abstractEphemeralKey.mObject) && ObjectUtils.equals(this.mSecret, abstractEphemeralKey.mSecret) && ObjectUtils.equals(this.mType, abstractEphemeralKey.mType);
    }

    public int hashCode() {
        return ObjectUtils.hash(this.mObjectId, Long.valueOf(this.mCreated), Long.valueOf(this.mExpires), this.mId, Boolean.valueOf(this.mLiveMode), this.mObject, this.mSecret, this.mType);
    }
}
