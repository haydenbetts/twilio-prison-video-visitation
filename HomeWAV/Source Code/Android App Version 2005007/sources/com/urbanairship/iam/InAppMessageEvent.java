package com.urbanairship.iam;

import com.urbanairship.UAirship;
import com.urbanairship.analytics.Event;
import com.urbanairship.json.JsonMap;
import com.urbanairship.json.JsonSerializable;
import com.urbanairship.json.JsonValue;

abstract class InAppMessageEvent extends Event {
    private static final String CAMPAIGNS = "campaigns";
    private static final String CONVERSION_METADATA = "conversion_metadata";
    private static final String CONVERSION_SEND_ID = "conversion_send_id";
    private static final String ID = "id";
    private static final String LOCALE = "locale";
    private static final String MESSAGE_ID = "message_id";
    private static final String SOURCE = "source";
    private static final String SOURCE_APP_DEFINED = "app-defined";
    private static final String SOURCE_URBAN_AIRSHIP = "urban-airship";
    private final JsonValue eventId;
    private final InAppMessage message;
    private final String source;

    InAppMessageEvent(String str, InAppMessage inAppMessage) {
        this.eventId = createEventId(str, inAppMessage);
        this.source = inAppMessage.getSource();
        this.message = inAppMessage;
    }

    InAppMessageEvent(JsonValue jsonValue, String str) {
        this.eventId = jsonValue;
        this.source = str;
        this.message = null;
    }

    public JsonMap getEventData() {
        String str = "app-defined";
        boolean equals = str.equals(this.source);
        JsonMap.Builder put = JsonMap.newBuilder().put("id", (JsonSerializable) this.eventId);
        if (!equals) {
            str = SOURCE_URBAN_AIRSHIP;
        }
        JsonMap.Builder putOpt = put.put("source", str).putOpt("conversion_send_id", UAirship.shared().getAnalytics().getConversionSendId()).putOpt("conversion_metadata", UAirship.shared().getAnalytics().getConversionMetadata());
        InAppMessage inAppMessage = this.message;
        return putOpt.putOpt(LOCALE, inAppMessage != null ? inAppMessage.getRenderedLocale() : null).build();
    }

    public boolean isValid() {
        return !this.eventId.isNull();
    }

    static JsonValue createEventId(String str, InAppMessage inAppMessage) {
        String source2 = inAppMessage.getSource();
        source2.hashCode();
        char c = 65535;
        switch (source2.hashCode()) {
            case -2115218223:
                if (source2.equals(InAppMessage.SOURCE_REMOTE_DATA)) {
                    c = 0;
                    break;
                }
                break;
            case -949613987:
                if (source2.equals("app-defined")) {
                    c = 1;
                    break;
                }
                break;
            case 2072105630:
                if (source2.equals(InAppMessage.SOURCE_LEGACY_PUSH)) {
                    c = 2;
                    break;
                }
                break;
        }
        switch (c) {
            case 0:
                return JsonMap.newBuilder().put("message_id", str).put(CAMPAIGNS, (JsonSerializable) inAppMessage.getCampaigns()).build().toJsonValue();
            case 1:
                return JsonMap.newBuilder().put("message_id", str).build().toJsonValue();
            case 2:
                return JsonValue.wrap(str);
            default:
                return JsonValue.NULL;
        }
    }
}
