package com.urbanairship.iam;

class DisplayEvent extends InAppMessageEvent {
    private static final String TYPE = "in_app_display";

    public final String getType() {
        return TYPE;
    }

    DisplayEvent(String str, InAppMessage inAppMessage) {
        super(str, inAppMessage);
    }
}
