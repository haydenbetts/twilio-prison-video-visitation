package com.urbanairship.iam;

import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;

class ResolutionEvent extends InAppMessageEvent {
    private static final String BUTTON_DESCRIPTION = "button_description";
    private static final String BUTTON_ID = "button_id";
    private static final String DISPLAY_TIME = "display_time";
    private static final String LEGACY_MESSAGE_DIRECT_OPEN = "direct_open";
    private static final String LEGACY_MESSAGE_REPLACED = "replaced";
    private static final int MAX_BUTTON_DESCRIPTION_LENGTH = 30;
    private static final String REPLACEMENT_ID = "replacement_id";
    private static final String RESOLUTION = "resolution";
    private static final String RESOLUTION_TYPE = "type";
    private static final String TYPE = "in_app_resolution";
    private final JsonMap resolutionData;

    public final String getType() {
        return TYPE;
    }

    ResolutionEvent(String str, InAppMessage inAppMessage, JsonMap jsonMap) {
        super(str, inAppMessage);
        this.resolutionData = jsonMap;
    }

    ResolutionEvent(JsonValue jsonValue, String str, JsonMap jsonMap) {
        super(jsonValue, str);
        this.resolutionData = jsonMap;
    }

    static ResolutionEvent legacyMessageReplaced(String str, String str2) {
        return new ResolutionEvent(JsonValue.wrap(str), InAppMessage.SOURCE_LEGACY_PUSH, JsonMap.newBuilder().put("type", LEGACY_MESSAGE_REPLACED).put(REPLACEMENT_ID, str2).build());
    }

    static ResolutionEvent legacyMessagePushOpened(String str) {
        return new ResolutionEvent(JsonValue.wrap(str), InAppMessage.SOURCE_LEGACY_PUSH, JsonMap.newBuilder().put("type", LEGACY_MESSAGE_DIRECT_OPEN).build());
    }

    static ResolutionEvent messageResolution(String str, InAppMessage inAppMessage, ResolutionInfo resolutionInfo, long j) {
        if (j <= 0) {
            j = 0;
        }
        JsonMap.Builder put = JsonMap.newBuilder().put("type", resolutionInfo.getType()).put(DISPLAY_TIME, millisecondsToSecondsString(j));
        if (ResolutionInfo.RESOLUTION_BUTTON_CLICK.equals(resolutionInfo.getType()) && resolutionInfo.getButtonInfo() != null) {
            String text = resolutionInfo.getButtonInfo().getLabel().getText();
            if (text != null && text.length() > 30) {
                text = text.substring(0, 30);
            }
            put.put(BUTTON_ID, resolutionInfo.getButtonInfo().getId()).put(BUTTON_DESCRIPTION, text);
        }
        return new ResolutionEvent(str, inAppMessage, put.build());
    }

    public JsonMap getEventData() {
        return JsonMap.newBuilder().putAll(super.getEventData()).put(RESOLUTION, (JsonSerializable) this.resolutionData).build();
    }
}
