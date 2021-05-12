package com.urbanairship.iam;

import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import com.urbanairship.util.Checks;
import com.urbanairship.util.UAStringUtil;

public class MediaInfo implements JsonSerializable {
    private static final String DESCRIPTION_KEY = "description";
    public static final String TYPE_IMAGE = "image";
    private static final String TYPE_KEY = "type";
    public static final String TYPE_VIDEO = "video";
    public static final String TYPE_YOUTUBE = "youtube";
    private static final String URL_KEY = "url";
    /* access modifiers changed from: private */
    public final String description;
    /* access modifiers changed from: private */
    public final String type;
    /* access modifiers changed from: private */
    public final String url;

    private MediaInfo(Builder builder) {
        this.url = builder.url;
        this.description = builder.description;
        this.type = builder.type;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put("url", this.url).put(DESCRIPTION_KEY, this.description).put("type", this.type).build().toJsonValue();
    }

    public static MediaInfo fromJson(JsonValue jsonValue) throws JsonException {
        try {
            return newBuilder().setUrl(jsonValue.optMap().opt("url").optString()).setType(jsonValue.optMap().opt("type").optString()).setDescription(jsonValue.optMap().opt(DESCRIPTION_KEY).optString()).build();
        } catch (IllegalArgumentException e) {
            throw new JsonException("Invalid media object json: " + jsonValue, e);
        }
    }

    public String getUrl() {
        return this.url;
    }

    public String getType() {
        return this.type;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        MediaInfo mediaInfo = (MediaInfo) obj;
        String str = this.url;
        if (str == null ? mediaInfo.url != null : !str.equals(mediaInfo.url)) {
            return false;
        }
        String str2 = this.description;
        if (str2 == null ? mediaInfo.description != null : !str2.equals(mediaInfo.description)) {
            return false;
        }
        String str3 = this.type;
        String str4 = mediaInfo.type;
        if (str3 != null) {
            return str3.equals(str4);
        }
        if (str4 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        String str = this.url;
        int i = 0;
        int hashCode = (str != null ? str.hashCode() : 0) * 31;
        String str2 = this.description;
        int hashCode2 = (hashCode + (str2 != null ? str2.hashCode() : 0)) * 31;
        String str3 = this.type;
        if (str3 != null) {
            i = str3.hashCode();
        }
        return hashCode2 + i;
    }

    public String toString() {
        return toJsonValue().toString();
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(MediaInfo mediaInfo) {
        return new Builder();
    }

    public static class Builder {
        /* access modifiers changed from: private */
        public String description;
        /* access modifiers changed from: private */
        public String type;
        /* access modifiers changed from: private */
        public String url;

        private Builder() {
        }

        private Builder(MediaInfo mediaInfo) {
            this.url = mediaInfo.url;
            this.description = mediaInfo.description;
            this.type = mediaInfo.type;
        }

        public Builder setUrl(String str) {
            this.url = str;
            return this;
        }

        public Builder setType(String str) {
            this.type = str;
            return this;
        }

        public Builder setDescription(String str) {
            this.description = str;
            return this;
        }

        public MediaInfo build() {
            Checks.checkArgument(!UAStringUtil.isEmpty(this.url), "Missing URL");
            Checks.checkArgument(!UAStringUtil.isEmpty(this.type), "Missing type");
            Checks.checkArgument(!UAStringUtil.isEmpty(this.description), "Missing description");
            return new MediaInfo(this);
        }
    }
}
