package com.urbanairship.remotedata;

import com.urbanairship.Logger;
import com.urbanairship.MessageCenterDataManager;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonList;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import com.urbanairship.util.DateUtils;
import java.text.ParseException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class RemoteDataPayload {
    public static final String METADATA_COUNTRY = "country";
    public static final String METADATA_LANGUAGE = "language";
    public static final String METADATA_SDK_VERSION = "sdk_version";
    private final JsonMap data;
    private final JsonMap metadata;
    private final long timestamp;
    private final String type;

    private RemoteDataPayload(Builder builder) {
        this.type = builder.type;
        this.timestamp = builder.timestamp;
        this.data = builder.data;
        this.metadata = builder.metadata == null ? JsonMap.EMPTY_MAP : builder.metadata;
    }

    static RemoteDataPayload emptyPayload(String str) {
        return newBuilder().setType(str).setTimeStamp(0).setData(JsonMap.EMPTY_MAP).build();
    }

    static RemoteDataPayload parsePayload(JsonValue jsonValue, JsonMap jsonMap) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        JsonValue opt = optMap.opt("type");
        JsonValue opt2 = optMap.opt(MessageCenterDataManager.MessageTable.COLUMN_NAME_TIMESTAMP);
        JsonValue opt3 = optMap.opt("data");
        try {
            if (!opt.isString() || !opt2.isString() || !opt3.isJsonMap()) {
                throw new JsonException("Invalid remote data payload: " + jsonValue.toString());
            }
            return newBuilder().setData(opt3.optMap()).setTimeStamp(DateUtils.parseIso8601(opt2.getString())).setType(opt.optString()).setMetadata(jsonMap).build();
        } catch (IllegalArgumentException | ParseException e) {
            throw new JsonException("Invalid remote data payload: " + jsonValue.toString(), e);
        }
    }

    static Set<RemoteDataPayload> parsePayloads(JsonValue jsonValue, JsonMap jsonMap) {
        JsonList optList = jsonValue.optList();
        try {
            HashSet hashSet = new HashSet();
            Iterator<JsonValue> it = optList.iterator();
            while (it.hasNext()) {
                hashSet.add(parsePayload(it.next(), jsonMap));
            }
            return hashSet;
        } catch (JsonException unused) {
            Logger.error("Unable to parse remote data payloads: %s", jsonValue.toString());
            return Collections.emptySet();
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        RemoteDataPayload remoteDataPayload = (RemoteDataPayload) obj;
        if (this.timestamp == remoteDataPayload.timestamp && this.type.equals(remoteDataPayload.type) && this.data.equals(remoteDataPayload.data)) {
            return this.metadata.equals(remoteDataPayload.metadata);
        }
        return false;
    }

    public int hashCode() {
        long j = this.timestamp;
        return (((((this.type.hashCode() * 31) + ((int) (j ^ (j >>> 32)))) * 31) + this.data.hashCode()) * 31) + this.metadata.hashCode();
    }

    public String toString() {
        return "RemoteDataPayload{type='" + this.type + '\'' + ", timestamp=" + this.timestamp + ", data=" + this.data + ", metadata=" + this.metadata + '}';
    }

    public final String getType() {
        return this.type;
    }

    public final long getTimestamp() {
        return this.timestamp;
    }

    public final JsonMap getData() {
        return this.data;
    }

    public final JsonMap getMetadata() {
        return this.metadata;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public JsonMap data;
        /* access modifiers changed from: private */
        public JsonMap metadata;
        /* access modifiers changed from: private */
        public long timestamp;
        /* access modifiers changed from: private */
        public String type;

        public Builder setType(String str) {
            this.type = str;
            return this;
        }

        public Builder setTimeStamp(long j) {
            this.timestamp = j;
            return this;
        }

        public Builder setData(JsonMap jsonMap) {
            this.data = jsonMap;
            return this;
        }

        public Builder setMetadata(JsonMap jsonMap) {
            this.metadata = jsonMap;
            return this;
        }

        public RemoteDataPayload build() {
            Checks.checkNotNull(this.type, "Missing type");
            Checks.checkNotNull(this.data, "Missing data");
            return new RemoteDataPayload(this);
        }
    }
}
