package com.urbanairship.iam;

import com.urbanairship.json.JsonException;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public final class ResolutionInfo implements JsonSerializable {
    public static final String BUTTON_INFO_KEY = "button_info";
    public static final String RESOLUTION_BUTTON_CLICK = "button_click";
    public static final String RESOLUTION_MESSAGE_CLICK = "message_click";
    public static final String RESOLUTION_TIMED_OUT = "timed_out";
    public static final String RESOLUTION_USER_DISMISSED = "user_dismissed";
    public static final String TYPE_KEY = "type";
    private final ButtonInfo buttonInfo;
    private final String type;

    @Retention(RetentionPolicy.SOURCE)
    public @interface Type {
    }

    private ResolutionInfo(String str) {
        this.type = str;
        this.buttonInfo = null;
    }

    public JsonValue toJsonValue() {
        return JsonMap.newBuilder().put("type", getType()).putOpt(BUTTON_INFO_KEY, getButtonInfo()).build().toJsonValue();
    }

    public static ResolutionInfo fromJson(JsonValue jsonValue) throws JsonException {
        JsonMap optMap = jsonValue.optMap();
        String string = optMap.opt("type").getString();
        if (string != null) {
            ButtonInfo buttonInfo2 = null;
            if (optMap.opt(BUTTON_INFO_KEY).isJsonMap()) {
                buttonInfo2 = ButtonInfo.fromJson(optMap.opt(BUTTON_INFO_KEY));
            }
            return new ResolutionInfo(string, buttonInfo2);
        }
        throw new JsonException("ResolutionInfo must contain a type");
    }

    private ResolutionInfo(String str, ButtonInfo buttonInfo2) {
        this.type = str;
        this.buttonInfo = buttonInfo2;
    }

    public static ResolutionInfo buttonPressed(ButtonInfo buttonInfo2) {
        return new ResolutionInfo(RESOLUTION_BUTTON_CLICK, buttonInfo2);
    }

    public static ResolutionInfo messageClicked() {
        return new ResolutionInfo(RESOLUTION_MESSAGE_CLICK);
    }

    public static ResolutionInfo dismissed() {
        return new ResolutionInfo(RESOLUTION_USER_DISMISSED);
    }

    public static ResolutionInfo timedOut() {
        return new ResolutionInfo(RESOLUTION_TIMED_OUT);
    }

    public String getType() {
        return this.type;
    }

    public ButtonInfo getButtonInfo() {
        return this.buttonInfo;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ResolutionInfo resolutionInfo = (ResolutionInfo) obj;
        if (!this.type.equals(resolutionInfo.type)) {
            return false;
        }
        ButtonInfo buttonInfo2 = this.buttonInfo;
        ButtonInfo buttonInfo3 = resolutionInfo.buttonInfo;
        if (buttonInfo2 != null) {
            return buttonInfo2.equals(buttonInfo3);
        }
        if (buttonInfo3 == null) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = this.type.hashCode() * 31;
        ButtonInfo buttonInfo2 = this.buttonInfo;
        return hashCode + (buttonInfo2 != null ? buttonInfo2.hashCode() : 0);
    }
}
