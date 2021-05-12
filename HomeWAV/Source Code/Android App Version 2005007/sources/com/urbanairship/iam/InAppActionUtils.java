package com.urbanairship.iam;

import com.urbanairship.actions.ActionRunRequest;
import com.urbanairship.actions.ActionRunRequestFactory;
import com.urbanairship.json.JsonValue;
import java.util.Map;

public abstract class InAppActionUtils {
    public static void runActions(ButtonInfo buttonInfo) {
        if (buttonInfo != null) {
            runActions(buttonInfo.getActions());
        }
    }

    public static void runActions(Map<String, JsonValue> map) {
        runActions(map, (ActionRunRequestFactory) null);
    }

    public static void runActions(Map<String, JsonValue> map, ActionRunRequestFactory actionRunRequestFactory) {
        if (map != null) {
            for (Map.Entry next : map.entrySet()) {
                String str = (String) next.getKey();
                (actionRunRequestFactory == null ? ActionRunRequest.createRequest(str) : actionRunRequestFactory.createActionRequest(str)).setValue(next.getValue()).run();
            }
        }
    }
}
