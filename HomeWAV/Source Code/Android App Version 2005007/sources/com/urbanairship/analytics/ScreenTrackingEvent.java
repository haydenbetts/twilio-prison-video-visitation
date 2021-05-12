package com.urbanairship.analytics;

import com.urbanairship.Logger;
import com.urbanairship.json.JsonMap;

class ScreenTrackingEvent extends Event {
    static final String DURATION_KEY = "duration";
    static final String PREVIOUS_SCREEN_KEY = "previous_screen";
    static final String SCREEN_KEY = "screen";
    static final int SCREEN_TRACKING_EVENT_MAX_CHARACTERS = 255;
    static final String START_TIME_KEY = "entered_time";
    static final String STOP_TIME_KEY = "exited_time";
    static final String TYPE = "screen_tracking";
    private final String previousScreen;
    private final String screen;
    private final long startTime;
    private final long stopTime;

    public String getType() {
        return TYPE;
    }

    ScreenTrackingEvent(String str, String str2, long j, long j2) {
        this.screen = str;
        this.startTime = j;
        this.stopTime = j2;
        this.previousScreen = str2;
    }

    public boolean isValid() {
        if (this.screen.length() > 255 || this.screen.length() <= 0) {
            Logger.error("Screen identifier string must be between 1 and 255 characters long.", new Object[0]);
            return false;
        } else if (this.startTime <= this.stopTime) {
            return true;
        } else {
            Logger.error("Screen tracking duration must be positive or zero.", new Object[0]);
            return false;
        }
    }

    public final JsonMap getEventData() {
        return JsonMap.newBuilder().put("screen", this.screen).put(START_TIME_KEY, Event.millisecondsToSecondsString(this.startTime)).put(STOP_TIME_KEY, Event.millisecondsToSecondsString(this.stopTime)).put("duration", Event.millisecondsToSecondsString(this.stopTime - this.startTime)).put(PREVIOUS_SCREEN_KEY, this.previousScreen).build();
    }
}
