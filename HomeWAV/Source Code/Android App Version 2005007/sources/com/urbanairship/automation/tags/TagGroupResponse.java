package com.urbanairship.automation.tags;

import com.urbanairship.http.Response;
import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.util.Map;
import java.util.Set;

class TagGroupResponse implements JsonSerializable {
    private static final String LAST_MODIFIED_KEY = "last_modified";
    private static final String STATUS_KEY = "status";
    private static final String TAG_GROUPS_KEY = "tag_groups";
    final String lastModifiedTime;
    final int status;
    final Map<String, Set<String>> tags;

    TagGroupResponse(int i, Map<String, Set<String>> map, String str) {
        this.tags = map;
        this.lastModifiedTime = str;
        this.status = i;
    }

    static TagGroupResponse fromJsonValue(JsonValue jsonValue) {
        JsonMap optMap = jsonValue.optMap();
        return new TagGroupResponse(optMap.opt("status").getInt(0), TagGroupUtils.parseTags(optMap.opt(TAG_GROUPS_KEY)), optMap.opt(LAST_MODIFIED_KEY).getString());
    }

    static TagGroupResponse fromResponse(Response response) throws JsonException {
        if (response.getStatus() != 200) {
            return new TagGroupResponse(response.getStatus(), (Map<String, Set<String>>) null, (String) null);
        }
        JsonMap optMap = JsonValue.parseString(response.getResponseBody()).optMap();
        return new TagGroupResponse(response.getStatus(), TagGroupUtils.parseTags(optMap.opt(TAG_GROUPS_KEY)), optMap.opt(LAST_MODIFIED_KEY).getString());
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().putOpt(TAG_GROUPS_KEY, this.tags).put(LAST_MODIFIED_KEY, this.lastModifiedTime).put("status", this.status).build().toJsonValue();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        TagGroupResponse tagGroupResponse = (TagGroupResponse) obj;
        if (this.status != tagGroupResponse.status) {
            return false;
        }
        Map<String, Set<String>> map = this.tags;
        if (map == null ? tagGroupResponse.tags != null : !map.equals(tagGroupResponse.tags)) {
            return false;
        }
        String str = this.lastModifiedTime;
        String str2 = tagGroupResponse.lastModifiedTime;
        if (str != null) {
            return str.equals(str2);
        }
        if (str2 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        Map<String, Set<String>> map = this.tags;
        int i = 0;
        int hashCode = (map != null ? map.hashCode() : 0) * 31;
        String str = this.lastModifiedTime;
        if (str != null) {
            i = str.hashCode();
        }
        return ((hashCode + i) * 31) + this.status;
    }

    public String toString() {
        return "TagGroupResponse{tags=" + this.tags + ", lastModifiedTime='" + this.lastModifiedTime + '\'' + ", status=" + this.status + '}';
    }
}
