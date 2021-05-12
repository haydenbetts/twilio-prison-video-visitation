package com.pusher.client.channel;

import java.util.Map;

public class PusherEvent {
    private Map<String, Object> eventData;

    public PusherEvent(Map<String, Object> map) {
        this.eventData = map;
    }

    public Object getProperty(String str) {
        return this.eventData.get(str);
    }

    public String getUserId() {
        return (String) this.eventData.get("user_id");
    }

    public String getChannelName() {
        return (String) this.eventData.get(Modules.CHANNEL_MODULE);
    }

    public String getEventName() {
        return (String) this.eventData.get("event");
    }

    public String getData() {
        return (String) this.eventData.get("data");
    }

    public String toString() {
        return this.eventData.toString();
    }
}
