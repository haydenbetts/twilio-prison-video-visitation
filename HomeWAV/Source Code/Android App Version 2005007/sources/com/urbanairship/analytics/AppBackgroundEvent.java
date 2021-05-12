package com.urbanairship.analytics;

import com.urbanairship.UAirship;
import com.urbanairship.json.JsonMap;

class AppBackgroundEvent extends Event {
    static final String TYPE = "app_background";

    public final String getType() {
        return TYPE;
    }

    AppBackgroundEvent(long j) {
        super(j);
    }

    public final JsonMap getEventData() {
        return JsonMap.newBuilder().put("connection_type", getConnectionType()).put("connection_subtype", getConnectionSubType()).put("push_id", UAirship.shared().getAnalytics().getConversionSendId()).put(TtmlNode.TAG_METADATA, UAirship.shared().getAnalytics().getConversionMetadata()).build();
    }
}
