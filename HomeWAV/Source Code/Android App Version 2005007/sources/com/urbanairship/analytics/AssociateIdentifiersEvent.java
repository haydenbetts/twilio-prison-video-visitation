package com.urbanairship.analytics;

import com.urbanairship.Logger;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonValue;
import java.util.Map;

class AssociateIdentifiersEvent extends Event {
    private static final String TYPE = "associate_identifiers";
    private final Map<String, String> ids;

    public String getType() {
        return TYPE;
    }

    AssociateIdentifiersEvent(AssociatedIdentifiers associatedIdentifiers) {
        this.ids = associatedIdentifiers.getIds();
    }

    public boolean isValid() {
        boolean z;
        if (this.ids.size() > 100) {
            Logger.error("Associated identifiers exceeds %s", 100);
            z = false;
        } else {
            z = true;
        }
        for (Map.Entry next : this.ids.entrySet()) {
            if (((String) next.getKey()).length() > 255) {
                Logger.error("Associated identifiers key %s exceeds %s characters.", next.getKey(), 255);
                z = false;
            }
            if (((String) next.getValue()).length() > 255) {
                Logger.error("Associated identifiers for key %s exceeds %s characters.", next.getKey(), 255);
                z = false;
            }
        }
        return z;
    }

    public JsonMap getEventData() {
        return JsonValue.wrapOpt(this.ids).optMap();
    }
}
