package com.urbanairship.analytics;

import com.urbanairship.json.JsonMap;

class InstallAttributionEvent extends Event {
    private static final String PLAY_STORE_REFERRER = "google_play_referrer";
    private static final String TYPE = "install_attribution";
    private final String referrer;

    public String getType() {
        return TYPE;
    }

    public InstallAttributionEvent(String str) {
        this.referrer = str;
    }

    public JsonMap getEventData() {
        return JsonMap.newBuilder().put(PLAY_STORE_REFERRER, this.referrer).build();
    }
}
